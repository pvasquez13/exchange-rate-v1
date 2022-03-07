# ExchangeRateV1 API - BCP
API that allows you to calculate and perform currency exchange.

## 1. Build Project
Use the following maven command to create the artifact of the project.
```bash
$ cd project
$ mvn clean package
```

## 2. Run Project
Once the artifact is created in step Build Project. Run the project with the following command.
```bash
$ cd project
$ java -jar target/business-exchange-rate-v1-1.0-SNAPSHOT.jar
```

## 3. Endpoints

## 3.1 User register

The endpoints of this API are protected with spring security and JWT, so it is necessary to register a user.

| Method             | URI    |
|-------------------|-------------|
| GET   | /api/auth/register    | 

### Payload request example
```json
{
  "name": "Peter",
  "username": "pvasquez",
  "email": "pvasquez@gmail.com",
  "password": "password",
  "role": "ADMIN"
}
```

### Payload response example
none.

## 3.2 Generate JWT

This endpoint allows to generate the JWT of a registered user.

| Method             | URI    |
|-------------------|-------------|
| GET   | /api/auth/login    | 

### Payload request example
```json
{
  "usernameOrEmail": "pvasquez",
  "password": "password"
}
```
### Payload response example
```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwdmFzcXVlekBnbWFpbC5jb20iLCJpYXQiOjE2NDY2NjYxNDAsImV4cCI6MTY0NjY2Njc0MH0.e9uS5klYKrhfGX4u6Xt93wX8-bbWrwPh_lCZW_VxtIp5W_Vk81n2u8JQKO5VMFOX_z5R0DhfmsuXFzqOjLrfZw",
  "tokenType": "Bearer"
}
```

## 3.3 Create an Exchange Rate
Endpoint that allows creating an exchange rate.

| Method             | URI    |
|--------------------|-------------|
| POST   | /api/bcp/v1/exchange-rate    | 

### Payload request example
```json
{
  "originCurrency": "PEN",
  "destinationCurrency": "USD",
  "sellingRate": "3.81",
  "purchaseRate": "3.75"
}
```

### Payload response example
```json
{
  "exchangeRateId": 1
}
```

## 3.4 List Exchange Rate

Endpoint showing list of exchange rate.

| Method             | URI    |
|-------------------|-------------|
| GET   | /api/bcp/v1/exchange-rate    | 

### Payload request example
none.

### Payload response example
```json
[
  {
    "exchangeRateId": 1,
    "originCurrency": "PEN",
    "destinationCurrency": "USD",
    "sellingRate": 3.81,
    "purchaseRate": 3.75
  }
]
```

## 3.5 Update an Exchange Rate
Endpoint that updates an exchange rate.

| Method             | URI    |
|--------------------|-------------|
| PATCH   | /api/bcp/v1/exchange-rate/{exchangeRateId}    | 

### Payload request example
```json
{
  "originCurrency": "PEN",
  "destinationCurrency": "USDD",
  "sellingRate": "3.81",
  "purchaseRate": "3.75"
}
```

### Payload response example
none.

## 3.6 Calculate exchange rate
Endpoint that calculates exchange rate.

| Method             | URI    |
|--------------------|-------------|
| POST   | /api/bcp/v1/exchange-rate/calculate    | 

### Payload request example
```json
{
  "originCurrency": "PEN",
  "destinationCurrency": "USD",
  "amount": "1500.00"
}
```

### Payload response example
```json
{
  "exchangeRateId": "8YGpIbU8A3",
  "chargeAmount": 1500.00,
  "chargeCurrency": "PEN",
  "depositAmount": 393.70,
  "depositCurrency": "USD",
  "rate": 3.81,
  "ratePurchase": 3.75,
  "rateSale": 3.81,
  "registerDate": "2022-03-07 10:25:11",
  "dueDate": "2022-03-07 10:30:11"
}
```

## 3.7 Process exchange rate
Endpoint that process exchange rate.

| Method             | URI    |
|--------------------|-------------|
| POST   | /api/bcp/v1/exchange-rate/exchange-order    | 

### Payload request example
```json
{
  "exchangeRateId": "jEUiw2WKwO",
  "chargeAccountId": "",
  "depositAccountId": ""
}
```

### Payload response example
```json
{
  "operationNumber": "1",
  "operationDateTime": "2022-03-07 10:29:51"
}
```

## Swagger

Available while the Project is running.

[Show Swagger](http://localhost:8080/swagger-ui/)