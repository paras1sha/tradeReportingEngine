package com.example.tradereportingengine.Utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerInstance {
    private static final Logger logger = Logger.getLogger(LoggerInstance.class.getName());

    static {
        // Set the logging level (you can adjust this according to your needs)
        logger.setLevel(Level.INFO);

        // Create a console handler and set its level
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL); // Log all levels to console

        // Add the console handler to the logger
        logger.addHandler(consoleHandler);
    }

    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    public static void logWarning(String message) {
        logger.log(Level.WARNING, message);
    }

    public static void logError(String message) {
        logger.log(Level.SEVERE, message);
    }

    public static void main(String[] args) {
        logInfo("This is an information message.");
        logWarning("This is a warning message.");
        logError("This is an error message.");
    }


}
