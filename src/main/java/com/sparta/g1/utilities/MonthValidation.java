package com.sparta.g1.utilities;

public class MonthValidation {
    public static boolean isValidDayOfMonth(int month, int day, int year) {
        if (((month == 4 || month == 6 || month == 9
                || month == 11) && day > 30)) {
            return false;
        }
        if (month == 2) {
            if (isLeapYear(year) && day > 29) {
                return false;
            } else if (!isLeapYear(year) && day > 28) {
                return false;
            }
        }
        return true;
    }
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
    }
}
