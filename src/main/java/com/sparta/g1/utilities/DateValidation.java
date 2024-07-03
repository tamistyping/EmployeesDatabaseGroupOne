package com.sparta.g1.utilities;

import com.sparta.g1.logger.AppLogger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateValidation {

    private static final Logger logger = AppLogger.getLogger(Level.ALL, Level.INFO, false);

    public static boolean isValidDayOfMonth(int month, int day, int year) {
        if (month < 1 || month > 12 || day < 1) {
            return false;
        }
        return switch (month) {
            case 2 -> isLeapYear(year) ? day <= 29 : day <= 28;
            case 4, 6, 9, 11 -> day <= 30;
            default -> day <= 31;
        };
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    public static boolean isDateValid(String date, int minYear, int maxYear, String dateType) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]");
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            int month = parsedDate.getMonthValue();
            int day = parsedDate.getDayOfMonth();
            int year = parsedDate.getYear();

            if (year < minYear || year > maxYear || !isValidDayOfMonth(month, day, year)) {
                logger.log(Level.WARNING, "Invalid " + dateType + ": " + date);
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            logger.log(Level.WARNING, "Invalid " + dateType + " format: " + date, e);
            return false;
        }
    }
}
