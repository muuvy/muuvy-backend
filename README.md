# muuvy rest-api

[![Build Status](https://travis-ci.org/muuvy/muuvy-backend.svg?branch=master)](https://travis-ci.org/muuvy/muuvy-backend)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=org.muuvy.backend%3Amuuvy-backend&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.muuvy.backend%3Amuuvy-backend)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.muuvy.backend%3Amuuvy-backend&metric=coverage)](https://sonarcloud.io/dashboard?id=org.muuvy.backend%3Amuuvy-backend)

## Defintions

* Throntail Version: `2.4.0.Final`
* Config files in `YAML` format

## Project Setup

### Requirements

* Install Lombok Plugin [Intellij Plugin](https://plugins.jetbrains.com/plugin/6317-lombok) / [Jar](https://search.maven.org/search?q=g:org.projectlombok%20AND%20a:lombok&core=gav) / [VS Code Plugin](https://marketplace.visualstudio.com/items?itemName=GabrielBB.vscode-lombok)
* Install Maven 3.3+ from [here](https://maven.apache.org/guides/getting-started/)
* Start docker-compose `mongo db` using these [steps](https://github.com/muuvy/database)
* download and extract Prometheus:
```
wget https://github.com/prometheus/prometheus/releases/download/v2.10.0/prometheus-2.10.0.windows-amd64.tar.gz
tar -xvf  prometheus-2.10.0.windows-amd64.tar.gz prometheus-2.10.0.windows-amd64/
```    

To install the dependencies, first run `mvn install`. Run the application with this command:

``` bash
$ mvn thorntail:run
Thorntail is ready
```

Verify if the server is up and running, by visit this [url](http://localhost:8080/api/user)

## Configuration

All thorntail configurations are located in `src/main/resources/project-local.yml`. Each environment has its own config file.

- Prometheus: copy the file and put this inside the extracted file: https://github.com/muuvy/docs/tree/master/prometheus
-- Run prometheus.exe and you can call this URL: localhost:9090

## Components

### Thorntail

* JAX RS
* LOG
* CDI

### JBoss

* Logger

### Misc

* Lombok
* Junit Jupiter
* Mongo Java Driver
* Hibernate OGM Mongo
