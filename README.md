http://localhost:8761 | Eureka service.
http://localhost:9090/routes | Zuul route definitions
http://localhost:9090/api/echo-service/echo | Echo service through Zuul api gateway, looked up from Eureka registry
http://localhost:9090/api/echo-service/echo/remote-echo | Echo service calling remote echo services
http://localhost:9090/api/echo-service-by-dns/echo/remote-echo | Echo service through Zuul api gateway, located by DNS entry http://echo-service:9098 