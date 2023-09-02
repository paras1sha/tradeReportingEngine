package com.trade.tradereportingengine.service;

import com.trade.tradereportingengine.Utils.LoggerInstance;
import com.trade.tradereportingengine.Utils.MessagingUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class TradeDataRunner {

    private final XmlParsingService xmlParsingService;
    private final MessagingUtils messagingUtils;
    @Value("${trade.directoryPath}")
    private String directoryPath;
    @Value("${trade.resourceDirectoryPath}")
    private String resourceDirectoryPath;
    @Value("${trade.eventXmlfilesPath}")
    private String eventFilesPath;

    public TradeDataRunner(XmlParsingService xmlParsingService, MessagingUtils messagingUtils) {
        this.xmlParsingService = xmlParsingService;
        this.messagingUtils = messagingUtils;
    }

    /**
     * Takes a list of filename and the fetch there absolute path which is then used to
     * parse the xml files and fetch the specific data to store in db
     * @param fileNames
     */
    public void fetchDataFromSpecificFiles(List<String> fileNames) {
        Path eventDirectoryPath = Paths.get(eventFilesPath);
        List<String> getAllEventFiles = fileNames.stream()
                .map(filename -> messagingUtils.getAbsoluteFIlePath(eventDirectoryPath, filename)).toList();
        LoggerInstance.logInfo("Retrieved all event files");
        xmlParsingService.parseAndStoreXmlFiles(getAllEventFiles);
    }
}
