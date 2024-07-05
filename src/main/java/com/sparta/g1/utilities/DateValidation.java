package com.sparta.g1.utilities;

import com.sparta.g1.logger.AppLogger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static java.time.format.ResolverStyle.STRICT;

public class DateValidation {


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
            String[] parts = date.split("/");

            int year = Integer.parseInt(parts[2]);
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);



            if (year < minYear || year > maxYear || !isValidDayOfMonth(month, day, year)) {
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]");
            LocalDate parsedDate = LocalDate.parse(date, formatter.withResolverStyle(STRICT));
            return false;
        }
    }
}
