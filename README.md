# Account Enquiry Service
This is the microservice which used to retrieve the account and transaction details.

# Getting Started

## Technologies Used
The following technologies has been used to develop this service.
* Java 11
* SpringBoot
* MySql
* Flyway
* JUnit5
* RestAssured

## How to Run
### Run the docker compose to start the database
Need to move inside the ```/ms-accounts-enquiry/docker``` location and run the following command. 

```shell
docker compose up
```

### Start the application
To start the application with sample data, use the ```dev``` spring profile and run the application. Use the corresponding
spring profile to run the application (e.g.,: ```prod``` for the production environment).

```properties
spring.profiles.active=dev
```

### Check API Documentation
Once the application is up and running, go to the following URLs for the API documentation.
<br><a href="http://localhost:8080/account-enquiry-docs" target="_blank">/account-enquiry-docs</a>


To view the API documentation in interactive way, use the below swagger URL. This can be used to try-out existing APIs. 
<br><a href="http://localhost:8080/swagger-ui/index.html" target="_blank">/swagger-ui/index.html</a>


### Assumption
The following assumptions has been taken for the development of this service.
* Authentication and authorisation considered as out of scope. Hence, user validation for the requests is not considered.
* Amounts of the accounts and transactions are taken directly from the database without any calculations.