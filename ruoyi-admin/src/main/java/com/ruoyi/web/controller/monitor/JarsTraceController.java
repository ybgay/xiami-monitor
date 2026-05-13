package com.ruoyi.web.controller.monitor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.core.config.JaegerConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/monitor/jars/trace")
public class JarsTraceController extends BaseController
{
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private JaegerConfig jaegerConfig;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PreAuthorize("@ss.hasPermi('monitor:jars:list')")
    @GetMapping("/config")
    public AjaxResult getConfig()
    {
        Map<String, Object> info = new HashMap<>();
        info.put("baseUrl", normalizeBaseUrl(jaegerConfig.getBaseUrl()));
        return success(info);
    }

    @PreAuthorize("@ss.hasPermi('monitor:jars:edit')")
    @PostMapping("/config")
    public AjaxResult updateConfig(@RequestBody Map<String, Object> body)
    {
        String baseUrl = String.valueOf(body.getOrDefault("baseUrl", "")).trim();
        if (StringUtils.isBlank(baseUrl))
        {
            return error("Jaeger 地址不能为空");
        }
        if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://"))
        {
            baseUrl = "http://" + baseUrl;
        }
        jaegerConfig.setBaseUrl(baseUrl);
        return success("Jaeger 地址已更新");
    }

    @PreAuthorize("@ss.hasPermi('monitor:jars:list')")
    @GetMapping("/query")
    public AjaxResult query(
            @RequestParam(required = false) String service,
            @RequestParam(required = false) String endpoint,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) Integer minDurationMs,
            @RequestParam(required = false) Integer maxDurationMs,
            @RequestParam(required = false, defaultValue = "50") Integer limit)
    {
        try
        {
            int size = limit == null ? 50 : Math.max(10, Math.min(200, limit));
            String svc = StringUtils.defaultIfBlank(service, "").trim();
            String endpointFilter = StringUtils.defaultIfBlank(endpoint, "").trim();
            LocalDateTime end = parseOrDefault(endTime, LocalDateTime.now());
            LocalDateTime start = parseOrDefault(startTime, end.minusMinutes(30));

            String url = UriComponentsBuilder
                    .fromHttpUrl(normalizeBaseUrl(jaegerConfig.getBaseUrl()) + "/api/traces")
                    .queryParam("start", toMicros(start))
                    .queryParam("end", toMicros(end))
                    .queryParam("lookback", "custom")
                    .queryParam("limit", size)
                    .queryParam("service", svc)
                    .build()
                    .encode()
                    .toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
            JsonNode root = objectMapper.readTree(resp.getBody());

            List<Map<String, Object>> records = new ArrayList<>();
            JsonNode data = root.path("data");
            if (data.isArray())
            {
                for (JsonNode traceNode : data)
                {
                    Map<String, Object> item = parseTrace(traceNode, svc, endpointFilter);
                    if (item != null)
                    {
                        records.add(item);
                    }
                }
            }

            if (minDurationMs != null)
            {
                records = records.stream()
                        .filter(r -> ((Number) r.getOrDefault("durationMs", 0)).intValue() >= minDurationMs)
                        .collect(Collectors.toList());
            }
            if (maxDurationMs != null)
            {
                records = records.stream()
                        .filter(r -> ((Number) r.getOrDefault("durationMs", 0)).intValue() <= maxDurationMs)
                        .collect(Collectors.toList());
            }

            records = records.stream()
                    .sorted((a, b) -> String.valueOf(b.get("startTime")).compareTo(String.valueOf(a.get("startTime"))))
                    .limit(size)
                    .collect(Collectors.toList());

            Map<String, Object> result = new HashMap<>();
            result.put("records", records);
            result.put("trend", buildTrend(records));
            result.put("service", svc);
            result.put("endpoint", endpointFilter);
            result.put("startTime", start.format(FMT));
            result.put("endTime", end.format(FMT));
            result.put("jaegerBaseUrl", normalizeBaseUrl(jaegerConfig.getBaseUrl()));
            return success(result);
        }
        catch (Exception e)
        {
            return error("查询 Jaeger trace 失败: " + e.getMessage());
        }
    }

    private Map<String, Object> parseTrace(JsonNode traceNode, String defaultService, String endpointFilter)
    {
        String traceId = traceNode.path("traceID").asText();
        JsonNode spans = traceNode.path("spans");
        if (!spans.isArray() || spans.size() == 0)
        {
            return null;
        }

        List<JsonNode> spanList = new ArrayList<>();
        spans.forEach(spanList::add);
        spanList.sort(Comparator.comparingLong(s -> s.path("startTime").asLong()));

        JsonNode rootSpan = spanList.stream()
                .filter(s -> !s.path("references").isArray() || s.path("references").size() == 0)
                .findFirst().orElse(spanList.get(0));

        String endpoint = extractEndpoint(rootSpan);
        if (StringUtils.isNotBlank(endpointFilter)
                && !endpoint.toLowerCase(Locale.ROOT).contains(endpointFilter.toLowerCase(Locale.ROOT)))
        {
            return null;
        }

        long totalMicros = rootSpan.path("duration").asLong();
        long dbMicrosRaw = sumSpanMicros(spanList, "db");
        long downstreamMicrosRaw = sumSpanMicros(spanList, "client");

        long remainMicros = Math.max(0L, totalMicros);
        long dbMicros = Math.min(dbMicrosRaw, remainMicros);
        remainMicros -= dbMicros;
        long downstreamMicros = Math.min(downstreamMicrosRaw, remainMicros);
        remainMicros -= downstreamMicros;
        long businessMicros = remainMicros;

        Map<String, Long> phase = new LinkedHashMap<>();
        phase.put("HTTP入口", toMs(totalMicros));
        phase.put("业务处理", toMs(businessMicros));
        phase.put("数据库", toMs(dbMicros));
        phase.put("下游调用", toMs(downstreamMicros));
        phase.put("其他", 0L);

        Map<String, Object> row = new HashMap<>();
        row.put("traceId", traceId);
        row.put("startTime", formatMicros(rootSpan.path("startTime").asLong()));
        row.put("durationMs", Math.max(1, Math.round(totalMicros / 1000.0)));
        row.put("status", extractStatus(rootSpan));
        row.put("service", defaultService);
        row.put("endpoint", endpoint);
        row.put("phaseCost", phase);
        return row;
    }

    private long sumSpanMicros(List<JsonNode> spans, String type)
    {
        long sum = 0L;
        for (JsonNode span : spans)
        {
            String operation = span.path("operationName").asText("").toLowerCase(Locale.ROOT);
            Map<String, String> tags = toTagMap(span.path("tags"));
            String kind = tags.getOrDefault("span.kind", "").toLowerCase(Locale.ROOT);

            boolean matched = false;
            if ("db".equals(type))
            {
                matched = tags.containsKey("db.system") || operation.startsWith("select ") || operation.startsWith("insert ")
                        || operation.startsWith("update ") || operation.startsWith("delete ");
            }
            else if ("client".equals(type))
            {
                matched = "client".equals(kind) && !tags.containsKey("db.system");
            }

            if (matched)
            {
                sum += span.path("duration").asLong(0L);
            }
        }
        return sum;
    }

    private Map<String, String> toTagMap(JsonNode tags)
    {
        Map<String, String> map = new HashMap<>();
        if (!tags.isArray())
        {
            return map;
        }
        for (JsonNode tag : tags)
        {
            map.put(tag.path("key").asText(), tag.path("value").asText());
        }
        return map;
    }

    private String extractStatus(JsonNode rootSpan)
    {
        String code = toTagMap(rootSpan.path("tags")).getOrDefault("http.response.status_code", "200");
        try
        {
            return Integer.parseInt(code) >= 400 ? "ERROR" : "SUCCESS";
        }
        catch (Exception e)
        {
            return "SUCCESS";
        }
    }

    private String extractEndpoint(JsonNode rootSpan)
    {
        Map<String, String> tags = toTagMap(rootSpan.path("tags"));
        String route = tags.getOrDefault("http.route", "");
        String method = tags.getOrDefault("http.request.method", "");
        if (StringUtils.isNotBlank(method) && StringUtils.isNotBlank(route))
        {
            return method + " " + route;
        }
        return rootSpan.path("operationName").asText("-");
    }

    private List<Map<String, Object>> buildTrend(List<Map<String, Object>> records)
    {
        List<Map<String, Object>> trend = new ArrayList<>();
        if (records.isEmpty())
        {
            return trend;
        }

        List<Map<String, Object>> asc = new ArrayList<>(records);
        java.util.Collections.reverse(asc);
        int bucket = 12;
        for (int i = 0; i < asc.size(); i += bucket)
        {
            List<Map<String, Object>> part = asc.subList(i, Math.min(i + bucket, asc.size()));
            List<Integer> durations = new ArrayList<>();
            for (Map<String, Object> r : part)
            {
                durations.add(((Number) r.get("durationMs")).intValue());
            }
            durations.sort(Integer::compareTo);

            int sum = 0;
            for (Integer d : durations)
            {
                sum += d;
            }

            Map<String, Object> point = new HashMap<>();
            String t = String.valueOf(part.get(0).get("startTime"));
            point.put("time", t.substring(11, 16));
            point.put("avg", Math.round((float) sum / durations.size()));
            point.put("p99", durations.get(Math.max(0, (int) Math.ceil(durations.size() * 0.99) - 1)));
            trend.add(point);
        }
        return trend;
    }

    private LocalDateTime parseOrDefault(String text, LocalDateTime defaultTime)
    {
        try
        {
            return StringUtils.isBlank(text) ? defaultTime : LocalDateTime.parse(text.trim(), FMT);
        }
        catch (Exception ex)
        {
            return defaultTime;
        }
    }

    private String normalizeBaseUrl(String raw)
    {
        String value = StringUtils.defaultIfBlank(raw, "http://192.168.31.34:32686").trim();
        if (!value.startsWith("http://") && !value.startsWith("https://"))
        {
            value = "http://" + value;
        }
        while (value.endsWith("/"))
        {
            value = value.substring(0, value.length() - 1);
        }
        return value;
    }

    private long toMicros(LocalDateTime dateTime)
    {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() * 1000;
    }

    private String formatMicros(long micros)
    {
        Instant instant = Instant.ofEpochMilli(micros / 1000);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).format(FMT);
    }

    private long toMs(long micros)
    {
        return Math.max(0L, Math.round(micros / 1000.0));
    }
}
