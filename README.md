# Trade Reporting Engine

This service offers a robust solution for processing XML event data, making it accessible and filterable
via a well-documented API endpoint.

## Usage Prereuisite

* Read design implementation document before to learn more about the code implementation.
* Install JDK 17
* Install Maven 3.9.2
* Install Postman

## Steps to execute the code

* Clone the master branch to your local
* Open the project using Intelij or other software
* Navigate to TradeReportingEngineApplication.Java and just start the service.
* Once the service is started, it will read the xml files stored in resources/static/event XML directory. Once read it will fetch the specific fields from the xml files like buyerParty, sellerParty, premiumAmount and premimumCurrency and store
them into database
* To see the entries being done. You can check them by accessing the H2Console at http://localhost:8080/h2-console
* url = jdbc:h2:mem:tradereportingenginedb, username = sa, password = passowrd
* Query : SELECT * FROM TRADE_ENTITY
* To filter this data and fetch in a response, Either curl or use postman to hit the request
* TYPE: GET, URL: http://localhost:8080/trade/reportingEngine/filtered
* This will fetch the filtered data

## Assumption made on this project

There are couple of assumption I have made to create this project. It was not sure if the whole XML needs to be read and stored into the DB or just required fields in project specification. Also, It was also not sure what will be
the trigerring point for the xml to read, parse, and store in the db. 

* What I chosed to overcome above:
* I decided to take application start as triggering point to read, parse and store db.
* Plus, I also decided to just take the specific fields from the provided xml files. But the code is easy expandable in case more fields needed to be added in future.

