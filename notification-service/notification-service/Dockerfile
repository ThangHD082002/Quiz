# Sử dụng OpenJDK làm base image
FROM openjdk:17-jdk-alpine

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép tệp JAR vào container
COPY target/notification-service-0.0.1-SNAPSHOT.jar /app/demo.jar

# Đặt cổng mặc định cho ứng dụng
EXPOSE 4003

# Chạy ứng dụng Spring Boot
ENTRYPOINT ["sh", "-c", "java -jar /app/demo.jar"]



