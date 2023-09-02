package com.trade.tradereportingengine.service;

import com.trade.tradereportingengine.Utils.LoggerInstance;
import com.trade.tradereportingengine.exceptions.XmlParsingException;
import com.trade.tradereportingengine.model.TradeEntity;
import com.trade.tradereportingengine.repository.TradeRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XmlParsingService {
    private final TradeRepository tradeRepository;
    private final Map<String, String> xpathExpressions = new HashMap<>();
    public XmlParsingService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
        // Define XPath expressions for extracting data.
        // In future it can be moved to properties in case more are needed
        xpathExpressions.put("buyerParty", "//buyerPartyReference/@href");
        xpathExpressions.put("sellerParty", "//sellerPartyReference/@href");
        xpathExpressions.put("premiumAmount", "//paymentAmount/amount");
        xpathExpressions.put("premiumCurrency", "//paymentAmount/currency");
    }

    /**
     * Parse and store the xml files in DB using repository
     * @param filePaths
     */
    public void parseAndStoreXmlFiles(List<String> filePaths) {
        List<TradeEntity> tradeEntities = parseXmlFiles(filePaths);
        tradeRepository.saveAll(tradeEntities);
    }

    /**
     * Receive the list of xml file paths in absolute form
     * Use Simple parsing logic to fetch the specific fields using xpath expressions
     * @param filePaths
     * @return List<TradeEntity>
     */
    public List<TradeEntity> parseXmlFiles(List<String> filePaths) {
        List<TradeEntity> tradeEntities = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            for (String filePath : filePaths) {
                Document document = builder.parse(new File(filePath));
                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xpath = xPathFactory.newXPath();
                TradeEntity tradeEntity = new TradeEntity();
                // Extract data and create TradeEntity objects
                for (Map.Entry<String, String> entry : xpathExpressions.entrySet()) {
                    String fieldName = entry.getKey();
                    String expression = entry.getValue();

                    XPathExpression xpathExpression = xpath.compile(expression);
                    String nodeList = (String) xpathExpression.evaluate(document, XPathConstants.STRING);
                    System.out.printf(nodeList);

                    tradeEntity.setField(fieldName, nodeList);
                }
                tradeEntities.add(tradeEntity);
            }
        } catch (ParserConfigurationException | SAXException | XPathExpressionException | IOException |
                 NoSuchFieldException | IllegalAccessException e) {
            LoggerInstance.logError("XML parsing failed");
            // Handle exceptions and possibly throw custom exceptions
            throw new XmlParsingException("Error parsing XML files", e);
        }

        return tradeEntities;
    }
}
