package com.example.tradereportingengine.Utils;

import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class MessagingUtils {

    public String getAbsoluteFIlePath (Path resourceDirectory, String relativePath) {
        // Combine the resource directory path with the relative file path
        Path filePath = resourceDirectory.resolve(relativePath);
        // Get the absolute path of the file
        return filePath.toAbsolutePath().toString();
    }

    public List<String> getAbsolutePathOfFilesInsideDirectory(String directoryPath) {
        List<String> filePaths = new ArrayList<>();
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        filePaths.add(file.getAbsolutePath());
                    }
                }
            }
        } else {
            System.err.println("Invalid directory path: " + directoryPath);
        }
        return filePaths;
    }
}
