
# Auction Demo

API to add virtual bids for an auction. This project is using Spring Boot, Java 8, JUnit for the unit test and Maven to manage the dependencies.




## Authors

- [@soniag0125](https://github.com/soniag0125)


## API Reference

There are 3 endpoints:

#### Post add a new bidder

```http
  POST /api/virtualAuction/virtualBid/v1
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `bidderName` | `string` | **Required**. Bidder name |
| `startBid` | `number` | **Required**. Value to start the auction |
| `maxBid` | `number` | **Required**. Max value to bid in the auction |
| `autoIncrementAmount` | `number` | **Required**. Value to increment the bid |

#### addBid(bidder)
Add a new bidder in the list

#### Get bidders list

```http
  GET /api/virtualAuction/getBidders/v1
```

#### getBidders()
Returns the bidders list in the active auction.


#### Get auction winner

```http
  GET /api/virtualAuction/startAuction/v1
```

#### getWinner()

Take the bidders list and get the auction winner.


## Configuration
This project was created with Spring Boot 2.7.3 (https://start.spring.io/) and Java 8.

This API runs in 8580 port configured in applicattion.properties file.

To run the applicattion:

- Download [Spring Tool Suite 4](https://spring.io/tools)
- Download the main branch of this project.
- Import this maven project into the IDE.
- Start the embedded server in the IDE.
- In the browser or in an API tester using http://localhost:8580 validate the server running.
 
