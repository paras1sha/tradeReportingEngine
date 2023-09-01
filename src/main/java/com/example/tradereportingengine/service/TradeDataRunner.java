package com.example.tradereportingengine.service;

import com.example.tradereportingengine.Utils.LoggerInstance;
import com.example.tradereportingengine.Utils.MessagingUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Component
public class TradeDataRunner implements CommandLineRunner {

    private final XmlParsingService xmlParsingService;
    private final MessagingUtils messagingUtils;
    @Value("${trade.directoryPath}")
    private String directoryPath;
    @Value("${trade.resourceDirectoryPath}")
    private String resourceDirectoryPath;

    public TradeDataRunner(XmlParsingService xmlParsingService, MessagingUtils messagingUtils) {
        this.xmlParsingService = xmlParsingService;
        this.messagingUtils = messagingUtils;
    }

    @Override
    public void run(String... args) {
        Path resourceDirectory = Paths.get(resourceDirectoryPath);
        String absoluteFilePath = messagingUtils.getAbsoluteFIlePath(resourceDirectory, directoryPath);
        List<String> getAllEventsFiles = messagingUtils.getAbsolutePathOfFilesInsideDirectory(absoluteFilePath);
        LoggerInstance.logInfo("Retrieved all event files");
        xmlParsingService.parseAndStoreXmlFiles(getAllEventsFiles);
    }
}
