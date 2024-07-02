package com.sparta.g1;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppLogger {

    private static final Logger logger = Logger.getLogger(AppLogger.class.getName());

    public static Logger getLogger(Level fileLevel, Level consoleLevel, boolean append) {
        setupConsoleHandler(consoleLevel);
        setupFileHandler(fileLevel, append);

        logger.setUseParentHandlers(false);
        logger.setLevel(fileLevel);

        return logger;
    }

    private static void setupFileHandler(Level level, boolean append) {
        try {
            FileHandler fileHandler = new FileHandler("EmployeesProjectG1/src/main/resources/log.log", append);
            fileHandler.setLevel(level);
            fileHandler.setFormatter(new CustomFormatter());
            logger.addHandler(fileHandler);
        } catch(Exception e) {
            logger.log(Level.SEVERE, "Exception when setting up file handler");
            throw new RuntimeException(e);
        }

    }

    private static void setupConsoleHandler(Level level) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(level);
        consoleHandler.setFormatter(new CustomFormatter());
        logger.addHandler(consoleHandler);
    }

}
