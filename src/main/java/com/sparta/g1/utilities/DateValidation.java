package com.sparta.g1.utilities;

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
}
