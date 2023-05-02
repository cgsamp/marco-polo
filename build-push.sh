mvn clean \
spring-boot:build-image \
  -Ddocker.publishRegistry.domain=$(cat secrets/registry.json | jq -r '.registry.domain') \
  -Ddocker.publishRegistry.project=$(cat secrets/registry.json | jq -r '.registry.project') \
  -Ddocker.publishRegistry.repository=$(cat secrets/registry.json | jq -r '.registry.repository') \
  -Ddocker.publishRegistry.username=$(cat secrets/registry.json | jq -r '.registry.username') \
  -Ddocker.publishRegistry.password=$(cat secrets/registry.json | jq -r '.registry.password') \
  -Dspring-boot.build-image.publish=true

