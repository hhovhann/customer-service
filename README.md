# customer-service
Demo project for customer information

#Endpoints

* GET       /customer                           - Get all customers
###### ```URL: http://localhost:8080/customer```

* GET       /customer/{id}                      - Get customer with the id defined
###### ```URL:  http://localhost/customer/1```

* POST      /customer                           - Create a customer
###### ```URL: http://localhost:8080/customer```
```
{
   "firstName":"Ali",
   "lastName":"Baba",
   "phoneNumber":"+37491919191",
   "email":"alibaba@gmail.com"
}

```
* POST      /customer/<id>/address              - Create an address for this customer
###### ```URL: http://localhost:8080/customer/1/address```
```
{
  "typeOfAddress": "HOME",
  "city": "Yerevan",
  "country" : "Armenia"
}
```
* DELETE    /customer/<id>/address/{address_id} - Delete the address of the customer
###### ```URL: http://localhost:8080/1/address/1```

* GET       /city/<name>                        - Get all customers from this city
###### ```URL: http://localhost:8080/city/Yerevan``` - NOTE: case sensitive

* GET       /phone/<prefix>                     - Get all customers starting with the following prefix phone number
###### ```URL: http://localhost:8080/city/+374```

# Build and Run application
From project folder execute ```./run.sh``` script

# Run from Terminal
* ```mvn clean package && java -jar ./target/customer-service-1.0.0-SNAPSHOT.jar```

# Stack
* Java 11
* Maven 3.6.1
* Spring Boot 2.3.2.RELEASE

# Build Project 
mvn clean package

# Run Tests
mvn clean test

# Nice to have
* More unit and integration tests with different scenarios
* For performance reasons getAllCustomers can be cacheable

 