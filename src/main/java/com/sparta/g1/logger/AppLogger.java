package com.sparta.g1.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppLogger {

    private static final Logger logger = Logger.getLogger(AppLogger.class.getName());
    private static boolean handlersSet = false;

    public static Logger getLogger(Level fileLevel, Level consoleLevel, boolean append) {
        if (!handlersSet) {
            setupConsoleHandler(consoleLevel);
            setupFileHandler(fileLevel, append);
            handlersSet = true;
        }

        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL); // Ensure the logger accepts all levels

        return logger;
    }

    private static void setupFileHandler(Level level, boolean append) {
        try {
            FileHandler fileHandler = new FileHandler("src/main/resources/app.log", append);
            fileHandler.setLevel(level);
            fileHandler.setFormatter(new CustomFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception when setting up file handler", e);
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
