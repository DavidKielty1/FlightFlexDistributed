FROM openjdk:17-jdk-slim

WORKDIR /app

COPY gradlew gradlew.bat settings.gradle build.gradle /app/
COPY gradle /app/gradle


# VOLUME /root/.gradle

# Pre-cache dependencies to speed up builds
RUN ./gradlew dependencies --no-daemon --stacktrace || true

COPY src /app/src

EXPOSE 8080

CMD ["./gradlew", "bootRun", "--no-daemon"]
