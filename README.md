# Stack:
- Config Service with Spring Config
- Service Discovery with Netflix Eureka
- API Gateway with Netflix Zuul
- Circuit Breaker with Feign, Netflix Hystrix and Netflix Hystrix Dashboard
- App LoadBalancer with Ribbon
- Swagger UI


# Build and Run:
```sh
$ git clone https://github.com/fabioviana/poc-microservices.git
$ cd poc-microservices
$ mvn package
$ docker-compose up
```


# Test
- API Gateway Proxy api docs: http://localhost/swagger-ui.html
- Svc A api docs: http://localhost/api-svca-service/swagger-ui.html
- Svc B api docs: http://localhost/api-svcb-service/swagger-ui.html
- API Gateway List of profiles stored in svca + svcb: http://localhost/api/v1/socialProfiles/names
- API Gateway List of profiles stored in a specific service: http://localhost/api/v1/socialProfiles/names/{service}
- Svc A info name: http://localhost/api-svca-service/api/v1/info/name
- Svc B info name: http://localhost/api-svcb-service/api/v1/info/name
- Svc A info name v2 (concat info name of svcb): http://localhost/api-svca-service/api/v2/info/name
- Svc A Data Rest to entity SocialProfile: http://localhost/api-svca-service/api/v1/socialProfiles
- Svc B Data Rest to entity SocialProfile: http://localhost/api-svcb-service/api/v1/socialProfiles
- Config Server: http://localhost:8888/{app_name}/master
- Service Discovery (Netflix Eureka): http://localhost:8761
- Hystrix Dashboard: http://localhost:8080
- Turbine Hystrix Stream: http://localhost:8080/turbine.stream