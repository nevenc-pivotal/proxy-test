# proxy-test

SpringBoot application that tests call to another URL. Can be used to configure Proxy authentication.

## Build Application

```
mvn clean package
```

## Run Application

* You can run application locally, using `mvn` target, e.g.
```
mvn -Dspring.profiles.active=proxy   spring-boot:run 
mvn -Dspring.profiles.active=default spring-boot:run 
mvn -Dtest.url=http://yahoo.com      spring-boot:run 
mvn -Dtest.url=https://yahoo.com     spring-boot:run 
```

* You can run application locally, using compiled application, e.g.
```
java -Dspring.profiles.active=proxy   -jar target/proxy-test-0.0.1-SNAPSHOT.jar
java -Dspring.profiles.active=default -jar target/proxy-test-0.0.1-SNAPSHOT.jar
java -Dtest.url=http://yahoo.com      -jar target/proxy-test-0.0.1-SNAPSHOT.jar
java -Dtest.url=https://yahoo.com     -jar target/proxy-test-0.0.1-SNAPSHOT.jar
```
* You can turn on/off proxy configuratiaon by enabling Spring profile (as above), or permanently in `src/main/resources/application.properties`, e.g.
```
spring.profiles.active=proxy
```


## Configure Proxy settings

* Proxy configuration settings are managed in `src/main/resources/application-proxy.yml`:

```
spring:
  my:
    proxy:
      host: my.examples.cf
      port: 8080
      user: proxyuser
      password: proxypassword
      skip: localhost|127.*|[::1]
```

## Test the application

* Testing URL is configured in `src/main/resources/application.properties`, e.g.
```
test.url=http://www.google.com
```
* You can pass `url` parameter to override testing URL, e.g.
```
http://localhost:8080/?url=http://yahoo.com
http://localhost:8080/?url=https://yahoo.com
```

