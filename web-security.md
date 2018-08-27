Simple security, add the Spring Boot Security starter

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

Then set application.properties, e.g.:

    security.user.password=password
    security.user.name=user
    security.basic.enabled=true

see: http://www.baeldung.com/spring-boot-security-autoconfiguration and https://springframework.guru/spring-boot-web-application-part-2-using-thymeleaf/

More control over Users (AuthN) and Roles (AuthZ), see: https://spring.io/guides/gs/securing-web/ and https://springframework.guru/spring-boot-web-application-part-5-spring-security/