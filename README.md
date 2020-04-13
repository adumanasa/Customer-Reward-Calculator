# Customer-Reward-Calculator

## What you’ll need
IDE

JDK 8 or later

Install Maven on Windows/Linux/Mac OSX

Clone Project to local IDE

## Run project
To run this project run the following commands.
```
mvn clean package

java -jar CustomerRewardCalculator-1.0-SNAPSHOT.jar
```

## Code execution:
```
URL: http://localhost:8080/customer/rewards
Method Type: POST
Headers: Content-type: application/json
Sample Payload:
    [{
  "customerId": "1234",
  "transactions": [{
    "date": "04/09/2020",
    "amount": 125.5
  }, {
    "date": "04/04/2020",
    "amount": 60
  }, {
    "date": "02/04/2020",
    "amount": 30
  }, {
    "date": "03/05/2020",
    "amount": 165
  }]
}, {
  "customerId": "3762",
  "transactions": [{
    "date": "03/23/2020",
    "amount": 110
  }, {
    "date": "03/03/2019", 
    "amount": 60
  }, {
    "date": "02/24/2020",
    "amount": 30
  }, {
    "date": "03/21/2020",
    "amount": 165
  }]
}]
```
