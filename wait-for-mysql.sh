#!/bin/sh
echo "Waiting for MySQL..."

while ! nc -z mysql 3306; do
  echo "Still waiting for MySQL..."
  sleep 1
done

echo "MySQL is up — starting app"
exec java -jar /app/golf-club-api.jar
