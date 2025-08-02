FROM openjdk:17-alpine

LABEL authors="wayne"

# Install netcat for Alpine
RUN apk add --no-cache netcat-openbsd

COPY target/golf-club-api-0.0.1-SNAPSHOT.jar /app/golf-club-api.jar
COPY wait-for-mysql.sh /app/wait-for-mysql.sh
RUN chmod +x /app/wait-for-mysql.sh

WORKDIR /app
CMD ["./wait-for-mysql.sh"]
