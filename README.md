# Spring AddressBook

This application is a simple ReST API developed with **Spring Boot** and **Java 11** and provides the following
functionalities:

* User can create address books
* User can add people to multiple address books
* Each contact might have multiple phone numbers with descriptions
* User can retrieve all their contacts sorted by name
* User can modify or delete resources (address books, people, and contact information)
* User can check two address books for their unique containing people (union of relative complements)

## Build and Run

The latest version of the app uses postgresql9.6 as its datasource, and expects it to be running on port `5440` (Even
for running some of the tests). 
### Quickstart

Using `docker-compose`:

```aidl
docker-compose -f compose.yml up
```

This will download the pre-built images and runs the application on port 8080.

### Step by step build

Providing postgresql using docker:

```aidl
docker run -p 5440:5432 -e POSTGRES_PASSWORD=password postgres:9.6
```

For running tests:

```aidl
./mvnw clean test
```

For building an executable jar:

```aidl
./mvnw clean package
```

For running the application (on default port 8080):

```aidl
java -jar target/addressbook-0.0.1-SNAPSHOT.jar
```

## Basic API usage

For example requests you can run `bash example-calls.sh` this populates the app with sample data, for the full API
documentation see `localhost:8080/api/swagger` or just `locahost:8080`.