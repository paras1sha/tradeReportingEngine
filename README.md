# Trade Reporting Engine

This service offers a robust solution for processing XML event data, making it accessible and filterable
via a well-documented API endpoint.

## Usage Prerequisite
* Read design implementation document before to learn more about the code implementation. [Design Implementation](Design%20Implementation)
* Get families with API request by reading [TradeReportingEngine_Swagger.yaml](src%2Fmain%2Fresources%2FTradeReportingEngine_Swagger.yaml)
* Install JDK 17
* Install Maven 3.9.2
* Install Postman 
* Import collection -> [Testing Collection.postman_collection.json](src%2Fmain%2Fresources%2Fstatic%2FTesting%20Collection.postman_collection.json)

## Steps to execute the code

* Clone the master branch to your local
* Open the project using IntelliJ or other software
* Navigate to TradeReportingEngineApplication.Java and just start the service.
* Open the postman collection provided in /src/main/resources/static/ -> testing collection
* Hit the Post request (Save All Data) with the existing body, Body can be modified to add or remove xml files names.
* The response should say -> All entries saved
* To see the entries being done. You can check them by accessing the H2Console at http://localhost:8080/h2-console
* url = jdbc:h2:mem:tradereportingenginedb, username = sa, password = passowrd
* Query : SELECT * FROM TRADE_ENTITY
* You can also run the second request (Fetch All Without Filter) -> Response should should all entries saved in entries
* To filter this data and fetch in a response, use 3rd request (Filter and fetch) 
* Response should bring the filtered response.

* Test Files:  [TradeFilterServiceTest.java](src%2Ftest%2Fjava%2Fcom%2Ftrade%2Ftradereportingengine%2Fservice%2FTradeFilterServiceTest.java)
[XmlParsingServiceTest.java](src%2Ftest%2Fjava%2Fcom%2Ftrade%2Ftradereportingengine%2Fservice%2FXmlParsingServiceTest.java)
[TradeReportingEngineIntegrationTest.java](src%2Ftest%2Fjava%2Fcom%2Ftrade%2Ftradereportingengine%2FTradeReportingEngineIntegrationTest.java)

## Assumption made on this project

There were couple assumption I have made to create this project. It was not sure if the whole XML needs to be read and stored into the DB or just required fields in project specification. Also, It was also not sure what will be
the triggering point for the xml to read, parse, and store in the db. 

* What I chose to overcome above:
* I decided to take API request as a  triggering point to read, parse and store db.
* Plus, I also decided to just take the specific fields from the provided xml files. But the code is easy expandable in case more fields needed to be added in the future.

