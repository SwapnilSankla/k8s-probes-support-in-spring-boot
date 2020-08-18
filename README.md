Liveness and readiness probes are first class citizens in spring boot from 2.3.0.

Mongo probe is just a configuration. Wrote a custom probe for kafka. We can add custom details.

### Endpoints:

http://localhost:8080/actuator/health
http://localhost:8080/actuator/health/liveness
http://localhost:8080/actuator/health/readiness

### Configuration:

#### Enable probes:
management.endpoint.health.probes.enabled=true
management.endpoint.health.show-details=always
management.health.livenessstate.enabled=true
management.health.readinessstate.enabled=true

#### Configure probes:
management.endpoint.health.group.liveness.include=livenessProbe,kafkaProbe,mongo
management.endpoint.health.group.readiness.include==readinessProbe,kafkaProbe,mongo

#### Enable Mongo health
management.health.mongo.enabled=true