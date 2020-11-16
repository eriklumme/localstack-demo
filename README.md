# LocalStack Demo

This is a simple demo application for using LocalStack.

## Prerequisites

* Docker
* Java 8+

## Running

1. Start LocalStack by running `docker-compose up` in the `localstack` directory.
2. Start the application by running `./mvnw spring-boot:run` or by running the `LocalStackDemoApplication` in your IDE.

* Access the app through `localhost:8080`.
* View buckets through `localhost:8080/buckets`.
* Send a test e-mail through `localhost:8080/emails`.

## LocalStack

LocalStack is set up in the `localstack` directory.

* The `docker-compose.yml` file defines which services we want to run, and some other Docker configuration.
* The `localstack/bin` folder contains the shell scripts that we want to be executed when LocalStack has started. We can use this to set up our services.
* The `localstack/data` folder contains the recorded API calls, which LocalStack will re-run upon startup, so that our changes are persisted.

## The Spring App

* The `S3Service` has methods for finding available buckets, and the `EmailService` for sending e-mails.
* Requests are served by the `LocalStackController`.

