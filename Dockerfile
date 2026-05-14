FROM docker.lms.run/library/eclipse-temurin:24

WORKDIR /app

COPY ruoyi-admin/target/ruoyi-admin.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]