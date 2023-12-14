FROM maven:3.8.1-openjdk-17-slim AS build
COPY . .
RUN mvn clean package

FROM bellsoft/liberica-runtime-container:jdk-17-musl

RUN apk update && apk add --no-cache fontconfig ttf-dejavu wget

ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF8 -XX:MaxRAMPercentage=96"

COPY --from=build target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]