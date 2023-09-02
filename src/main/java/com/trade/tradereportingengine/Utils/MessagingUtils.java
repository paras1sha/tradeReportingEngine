package com.trade.tradereportingengine.Utils;

import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class MessagingUtils {
    public String getAbsoluteFIlePath (Path resourceDirectory, String relativePath) {
        // Combine the resource directory path with the relative file path
        Path filePath = resourceDirectory.resolve(relativePath);
        // Get the absolute path of the file
        return filePath.toAbsolutePath().toString();
    }
}
