# Multi-stage build for optimized image
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app

# Copy Maven configuration
COPY pom.xml .
COPY src ./src

# Build application - FIX: Use apk instead of apt-get for alpine
RUN apk add --no-cache maven \
    && mvn -B clean package -DskipTests

# Runtime stage - Security hardened
FROM eclipse-temurin:17-jre-alpine

LABEL security="hardened" \
      maintainer="DevSecOps Team"

# Install security tools and create non-root user
RUN apk add --no-cache dumb-init curl \
    && addgroup -g 1000 appuser \
    && adduser -D -u 1000 -G appuser appuser

WORKDIR /app

# Copy application and set ownership in one step (more efficient)
COPY --from=builder --chown=appuser:appuser /app/target/*.jar app.jar

USER appuser
EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
    CMD curl -f http://localhost:8080/health || exit 1

ENTRYPOINT ["/usr/bin/dumb-init", "--"]

CMD ["java", "-XX:+UnlockExperimentalVMOptions", \
     "-XX:+UseContainerSupport", \
     "-XX:MaxRAMPercentage=75.0", \
     "-Dspring.profiles.active=production", \
     "-jar", "app.jar"]