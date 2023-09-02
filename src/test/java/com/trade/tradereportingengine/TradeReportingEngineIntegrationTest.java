package com.trade.tradereportingengine;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class TradeReportingEngineIntegrationTest {

    @LocalServerPort
    private int port;

    private static String baseUrl;


    @Test
    @Order(1)
    public void testGetAllData_NoValue() {
        baseUrl = "http://localhost:" + port + "/trade/reportingEngine";
        // Create a RestTemplate to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        // Make a sample GET request
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/fetchAll", String.class);

        // Assert the response status code is 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("[]", response.getBody());
    }

    @Test
    @Order(2)
    public void testSaveAllData() {
        baseUrl = "http://localhost:" + port + "/trade/reportingEngine";
        // Create a RestTemplate to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        List<String> dataRequest = new ArrayList<>();
        dataRequest.add("event0.xml"); //meet filter criteria
        dataRequest.add("event5.xml"); //does not meet filter criteria
        dataRequest.add("event1.xml"); //does not meet filter criteria

        // Create HttpHeaders and set Content-Type to application/json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity with the List<String> as the request body
        HttpEntity<List<String>> requestEntity = new HttpEntity<>(dataRequest, headers);

        // Define the endpoint URL
        String endpointUrl = baseUrl + "/saveAll";

        // Send the POST request
        ResponseEntity<String> response = restTemplate.exchange(
                endpointUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        // Assert the response status code is 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("All Entries Saved", response.getBody());
    }

    @Test
    @Order(3)
    public void testGetAllData_WithValues() {
        baseUrl = "http://localhost:" + port + "/trade/reportingEngine";
        // Create a RestTemplate to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        // Make a sample GET request
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/fetchAll", String.class);
        // Parse the JSON response into a list of objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> responseObjectList;
        try {
            responseObjectList = objectMapper.readValue(response.getBody(), List.class);
        } catch (Exception e) {
            responseObjectList = null;
        }

        // Check the size of the response list
        int responseSize = responseObjectList != null ? responseObjectList.size() : 0;

        // Assert the response status code is 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, responseSize);
    }

    @Test
    @Order(4)
    public void testGetAllData_WithFilter() {
        baseUrl = "http://localhost:" + port + "/trade/reportingEngine";
        // Create a RestTemplate to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        // Make a sample GET request
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/filter/fetchAll", String.class);
        // Parse the JSON response into a list of objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> responseObjectList;
        try {
            responseObjectList = objectMapper.readValue(response.getBody(), List.class);
        } catch (Exception e) {
            responseObjectList = null;
        }

        // Check the size of the response list
        int responseSize = responseObjectList != null ? responseObjectList.size() : 0;

        // Assert the response status code is 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, responseSize);
    }

}

