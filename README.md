# TODO API in [Java]

## Using this project

```bash
gradle run -PjavaVersion=17 -Dorg.gradle.java.home="/usr/local/opt/openjdk@17"
```

## h2Database, Mybatis 
①build.gradleのdependenciesに依存関係追加
- implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.2'
- developmentOnly 'org.springframework.boot:spring-boot-devtools'
- runtimeOnly 'com.h2database:h2'

②application.propertiesに設定追加

```bash
http://localhost:8080/h2-console
```

## Environment

```
Spring Boot 3.1.2
↓Spring Initializr
https://start.spring.io/

$ java -version
openjdk version "17"

$ gradle -v
Gradle 8.4

openAPI 3.0.0
org.openapi.generator: version 5.3.0
https://spec.openapis.org/oas/v3.0.0.html#schema
```