#!/bin/bash

# create new address book
printf '\n create new address book\n'
curl -X POST "http://localhost:8080/api/addressbook" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"description\": \"First Address book\", \"name\": \"ADB1\"}"

# create new person for address book 1
printf '\n create new person for address book 1\n'

curl -X POST "http://localhost:8080/api/people" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"contactInfos\": [ { \"description\": \"Home\", \"phone\": \"123456\" } ], \"description\": \"Classmate\", \"name\": \"Farzad\",\"addressBooks\": [1]}"

# get all address books
printf '\n get all address books\n'
curl -X GET "http://localhost:8080/api/addressbook" -H "accept: application/json"

# get first addrssbook's info
printf '\n get first addrssbook info\n'
curl -X GET "http://localhost:8080/api/addressbook/1" -H "accept: application/json"

# add new contact for person with id = 1
eprintf '\n add new contact for person with id = 1\n'
curl -X POST "http://localhost:8080/api/contact/person/1" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"description\": \"Mobile\", \"phone\": \"67890\"}"


# get person with id = 1 contact information
printf '\n get person with id = 1 contact information\n'
curl -X GET "http://localhost:8080/api/contact/1" -H "accept: application/json"


# create another addressbook
printf '\n create another addressbook\n'
curl -X POST "http://localhost:8080/api/addressbook" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"description\": \"Second Address book\", \"name\": \"ADB2\"}"

# create person for addressbook 2
printf '\n create person for addressbook 2\n'
curl -X POST "http://localhost:8080/api/people" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"contactInfos\": [ { \"description\": \"Home\", \"phone\": \"55555\" } ], \"description\": \"Agent\", \"name\": \"Jack\",\"addressBooks\": [2]}"

# create person in both adressbook 1 and 2
printf '\n create person in both adressbook 1 and 2\n'
curl -X POST "http://localhost:8080/api/people" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"contactInfos\": [ { \"description\": \"Home\", \"phone\": \"66666\" } ], \"description\": \"Friend\", \"name\": \"Jill\",\"addressBooks\": [1,2]}"

# get people who are either in addressbook 1 or 2 but not both
printf '\n get people who are either in addressbook 1 or 2 but not both\n'
curl -X GET "http://localhost:8080/api/people/1/2" -H "accept: application/json"


# get all people in all address books (sorted by name)
printf '\n get all people in all address books (sorted by name)\n'
curl -X GET "http://localhost:8080/api/people" -H "accept: application/json"
