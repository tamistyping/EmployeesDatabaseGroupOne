package com.sparta.g1;

import com.sparta.g1.logger.AppLogger;
import com.sparta.g1.utilities.DateValidation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDataCleaner {

    private static final Logger logger = AppLogger.getLogger(Level.ALL, Level.INFO, true);

    private static int numberOfCorruptedEntries = 0;

    public static boolean isEmployeeIdValid(String empId) {
        if (empId.matches("\\d{6}")) {
            return true;
        } else {
            numberOfCorruptedEntries++;
            logger.log(Level.WARNING, "Invalid Employee ID: " + empId);
            return false;
        }
    }

    public static void isPrefixValid(String prefix) {
    }
    public static boolean isNameValid(String name) {
        String nameRegex = "^[a-zA-Z\\-]+$";
        return name.matches(nameRegex);
    }
    public static boolean isGenderValid(String gender) {
        if (gender.equals("M") || gender.equals("F")) {
            return true;
        } else {
            numberOfCorruptedEntries++;
            logger.log(Level.WARNING, "Invalid Gender: " + gender);
            return false;
        }
    }
    public static boolean isEmailValid(String email) {
        if (email.matches("^[\\w.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            return true;
        } else {
            numberOfCorruptedEntries++;
            logger.log(Level.WARNING, "Invalid Email: " + email);
            return false;
        }
    }

    private static boolean isDateValid(String date, int minYear, int maxYear, String dateType) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            int year = parsedDate.getYear();
            int month = parsedDate.getMonthValue();
            int day = parsedDate.getDayOfMonth();

            if (year < minYear || year > maxYear || !DateValidation.isValidDayOfMonth(month, day, year)) {
                numberOfCorruptedEntries++;
                logger.log(Level.WARNING, "Invalid " + dateType + ": " + date);
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            numberOfCorruptedEntries++;
            logger.log(Level.WARNING, "Invalid " + dateType + " format: " + date, e);
            return false;
        }
    }




    public static boolean isDateOfBirthValid(String dateOfBirth) {
        boolean isValid = isDateValid(dateOfBirth, 1924, LocalDate.now().getYear(), "Date of Birth");
        if (!isValid) {
            numberOfCorruptedEntries++;
            logger.log(Level.WARNING, "Invalid Date of Birth: " + dateOfBirth);
        }
        return isValid;
    }




    public static boolean isDateOfJoiningValid(String dateOfJoining, String dateOfBirth) {
        if (!isDateValid(dateOfJoining, 1979, LocalDate.now().getYear(), "Date of Joining")) {
            return false;
        }
        if (isDojAfterDob(dateOfJoining, dateOfBirth)) {
            return true;
        }
        numberOfCorruptedEntries++;
        logger.log(Level.WARNING, "Date of Joining is before or same as Date of Birth");
        return false;
    }



    public static boolean isValidSalary(String salary) {
        try {
            if (Integer.parseInt(salary) >= 0) {
                return true;
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Invalid Salary: " + salary, e);
        }
        numberOfCorruptedEntries++;
        return false;
    }

    public static boolean isDojAfterDob(String dateOfJoining, String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            LocalDate doj = LocalDate.parse(dateOfJoining, formatter);
            LocalDate dob = LocalDate.parse(dateOfBirth, formatter);
            return doj.isAfter(dob);
        } catch (DateTimeParseException e) {
            logger.log(Level.WARNING, "Invalid date format in Doj or Dob: " + dateOfJoining + ", " + dateOfBirth, e);
            return false;
        }
    }


    public static int getNumberOfCorruptedEntries() {
        logger.log(Level.INFO, "Number of corrupted entries: " + numberOfCorruptedEntries);
        return numberOfCorruptedEntries;
    }

    public static DateTimeFormatter formatDates() {
        return DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]");
    }

}

