import request from '@/utils/request'

export function injectRun(data) {
  return request({
    url: '/monitor/inject/run',
    method: 'post',
    data: data
  })
}
