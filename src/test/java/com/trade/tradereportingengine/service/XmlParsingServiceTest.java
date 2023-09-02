package com.trade.tradereportingengine.service;

import com.trade.tradereportingengine.exceptions.XmlParsingException;
import com.trade.tradereportingengine.model.TradeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
public class XmlParsingServiceTest {

    @Test
    public void testParseXmlFile_ValidXml() {

        XmlParsingService xmlParsingService = new XmlParsingService(null);

        List<String> filePathList = new ArrayList<>();
        // Mock XML content
        String filePath1 = "./src/test/resources/mockTrueData_Valid1.xml";

        filePathList.add(filePath1);

        // Test the parseXmlFile method
        List<TradeEntity> resultedData = xmlParsingService.parseXmlFiles(filePathList);

        assertEquals("EMU_BANK", resultedData.get(0).getBuyerParty());
        assertEquals("BISON_BANK", resultedData.get(0).getSellerParty());
        assertEquals("AUD", resultedData.get(0).getPremiumCurrency());
        assertEquals("150.00", resultedData.get(0).getPremiumAmount().toString());
    }

    @Test
    public void testParseXmlFile_InvalidXml() {
        XmlParsingService xmlParsingService = new XmlParsingService(null);
        List<String> filePathList = new ArrayList<>();
        // Mock XML content
        String filePath1 = "./src/test/resources/mockTrueData_Invalid.xml";

        filePathList.add(filePath1);

        assertThrows(XmlParsingException.class, () -> xmlParsingService.parseXmlFiles(filePathList));
    }

    @Test
    public void testAllParasXmlFiles() {
        XmlParsingService xmlParsingService = new XmlParsingService(null);
        // Mock XML file paths
        String filePath1 = "./src/test/resources/mockTrueData_Valid1.xml";
        String filePath2 = "./src/test/resources/mockTrueData_Valid2.xml";
        String filePath3 = "./src/test/resources/mockTrueData_Valid3.xml";
        List<String> xmlFilePaths = List.of(filePath1, filePath2, filePath3);

        // Test the parseXmlFiles method
        List<TradeEntity> resultedData = xmlParsingService.parseXmlFiles(xmlFilePaths);

        // Assertions
        assertNotNull(resultedData);
        assertEquals(3, resultedData.size());
    }

}