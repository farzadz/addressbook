# Spring AddressBook

This application is a simple ReST API developed with **Spring Boot** and **Java 11** and provides the following functionalities:

* User can create address books
* User can add people to multiple address books
* Each contact might have multiple phone numbers with descriptions
* User can retrieve all their contacts sorted by name
* User can modify or delete resources (address books, people, and contact information)
* User can check two address books for their unique containing people (union of relative complements)

## Basic usage

For example requests see `example-calls.sh`, for the full API documentation see `localhost:8080/api/swagger` or
just `locahost:8080`.

## Build and Run

For running tests:

```aidl
./mvnw clean test
```

For building an executable jar:

```aidl
./mvnw clean package spring-boot:repackage
```

For running the application (on default port 8080):

```aidl
java -jar target/addressbook-0.0.1-SNAPSHOT.jar
```