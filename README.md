# muuvy rest-api

[![Build Status](https://travis-ci.com/muuvy/app-backend.svg?branch=dev)](https://travis-ci.com/muuvy/app-backend) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=muuvy_app-backend&metric=alert_status)](https://sonarcloud.io/dashboard?id=muuvy_app-backend)

### Defintions

* Throntail Version: `2.4.0.Final`
* Config files in `YAML` format

### Project Setup

__Requirements__

* Install Maven 3.3+ https://maven.apache.org/guides/getting-started/

To install the dependencies, first run `mvn install`. Run the application with this command:
```
$ mvn package && java -jar target/muuvy-app-backend-thorntail.jar -Slocal
```

Verify if the server is up and running, by visit this url: http://localhost:8080/hello

### Configuration

All configurations are located in `src/main/resources/config`. Each environment has its own config file. 

__Config selection__

| Type | Example | Docs |
|----------------------|--------------------------------------------|----------------------------------------------------------------------------------------|
| Environment Variable | JAVA_OPTS="-Dthorntail.project.stage=prod" | https://github.com/cloudfoundry/java-buildpack/blob/master/docs/framework-java_opts.md |
| Start Command | java -jar muuvy-app-backend.jar -Slocal |  |
