# Country service

RESTful API service for retrieving country's information all over the world

## Prerequisites

* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or later
* [Maven](https://maven.apache.org/)

## Installation instruction

* Clone this repository
* Go to the project

```
cd country-service
```

* Install dependencies

```
./mvnw clean install
```

* Start the server:

```bash
./mvnw spring-boot:run
```

The server has been configured to be run on port 8080. Therefore, the base URL is http://localhost:8080

**Note: If the project is opened with IntelliJ IDEA, the dependencies installation and starting server can easily be
done without the command above**

## API endpoints and documentation

#### Overview

```bash
[GET] /countries (Get all the country codes)
[GET] /countries/{country_name} (Get country details by a country name)
```

#### For the API documentation, Swagger specification is configured and deployed to the following paths:

```bash
- Swagger UI specification: "/swagger-ui.html"
- OpenAPI description: "/v3/api-docs"
```

## Try the service locally

After starting the server. Please click one of those URLs below to try the service endpoints

#### API endpoints

* [Retrieve all countries](http://localhost:8080/countries)
* [Retrieve the country details of Finland](http://localhost:8080/countries/Finland)

#### Documentation

* [Open Swagger UI specification](http://localhost:8080/swagger-ui.html)
* [Open OpenAPI description](http://localhost:8080/v3/api-docs)

## Integration service

[https://restcountries.com](https://restcountries.com)

## Country client

A web application for browsing the details of all countries. The application consumes this country-service RESTful API.

Please follow this [Country client's README.md](https://github.com/AnhNguyenMaiHoang219/country-client) for more
information and installation guide
