# LocalStack Demo

This is a simple demo application for using LocalStack.

## LocalStack

LocalStack is set up in the `localstack` directory.

The `docker-compose.yml` file defines which services we want to run, among other things. Start LocalStack by executing `docker-compose up` in the `localstack` directory.

The `localstack/bin` folder contains the shell scripts that we want to be executed when LocalStack has started. We can use this to set up our services.

The `localstack/data` folder contains the recorded API class, which LocalStack will re-run upon startup, so that our changes are persisted.

## The Spring App

Run `mvn spring-boot:run`, or run the `LocalStackDemoApplication` in your IDE to start the app.

The `S3Service` has methods for finding available buckets, and the `EmailService` for sending e-mails.

Access the app through `localhost:8080`.

View buckets through `localhost:8080/buckets`.

Send an e-mail through `localhost:8080/emails`.