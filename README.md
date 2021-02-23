# User pets

There is a simple CRUD [application](https://user-pets.cfapps.eu10.hana.ondemand.com/api/v1/) project with 4 entities:
1. User
2. Pet
3. Dog
4. Cat

[There](https://github.com/myfosse/user-pets/blob/pets/UML_DB.png) is a DB scheme.

## Test project with DB for SAP Cloud Platform

You can check all available fuctional by using postman [collection](https://github.com/myfosse/user-pets/blob/pets/User-Pets.postman_collection.json)

## Used Technologies

- Java 11
- Maven 3.6.3
- H2
- Spring Boot 2.4.2

## How to start application

1. Go to the root of the project
2. Run next command to build .jar: **mvn clean package**
3. After successful build sign in at SAP CP: **cf login** (then write your email and password)
4. After successful log in push project to SAP CP: **cf push**
