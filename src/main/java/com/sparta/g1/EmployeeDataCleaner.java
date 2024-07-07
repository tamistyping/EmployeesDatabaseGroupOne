package com.sparta.g1;

import com.sparta.g1.logger.AppLogger;
import com.sparta.g1.utilities.DateValidation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDataCleaner {

    private static final Logger logger = AppLogger.getLogger(Level.INFO, Level.INFO, true);

    private static int numberOfCorruptedEntries;
    private static Set<String> employeeIds = new HashSet<>();

    public static boolean isEmployeeIdValid(String empId) {
        if (empId.matches("\\d{6}")) {
            if (employeeIds.contains(empId)) {
                numberOfCorruptedEntries++;
                logger.log(Level.WARNING, "Duplicate Employee ID: " + empId);
                return false;
            } else {
                employeeIds.add(empId);
                return true;
            }
        } else {
            numberOfCorruptedEntries++;
            logger.log(Level.WARNING, "Invalid Employee ID format: " + empId);
            return false;
        }
    }

    public static boolean isNameValid(String name) {
        String nameRegex = "^[a-zA-Z\\-]+$";
        if (name.matches(nameRegex)) {
            return true;
        }
        logger.log(Level.WARNING, "Invalid name: " + name);
        numberOfCorruptedEntries++;
        return false;
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

    public static boolean isDateOfBirthValid(String dateOfBirth) {
        boolean isValid = DateValidation.isDateValid(dateOfBirth, 1924, LocalDate.now().getYear(), "Date of Birth");
        if (!isValid) {
            numberOfCorruptedEntries++;
            logger.log(Level.WARNING, "Invalid Date of Birth: " + dateOfBirth);
        }
        return isValid;
    }

    public static boolean isDateOfJoiningValid(String dateOfJoining, String dateOfBirth) {
        if (!DateValidation.isDateValid(dateOfJoining, 1995, LocalDate.now().getYear(), "Date of Joining")) {
            logger.log(Level.WARNING, "Date of Joining is before company was founded or in the future " + dateOfJoining);
            numberOfCorruptedEntries++;
            return false;
        }
        if (!isDojAfterDob(dateOfJoining, dateOfBirth)) {
            logger.log(Level.WARNING, "Date of Joining is before or same as Date of Birth " + dateOfJoining + ", " + dateOfBirth);
            numberOfCorruptedEntries++;
            return false;
        }
        return true;
    }

    public static boolean isDojAfterDob(String dateOfJoining, String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]");
        try {
            LocalDate doj = LocalDate.parse(dateOfJoining, formatter);
            LocalDate dob = LocalDate.parse(dateOfBirth, formatter);
            return doj.isAfter(dob);
        } catch (DateTimeParseException e) {
            logger.log(Level.WARNING, "Invalid date format in Doj or Dob: " + dateOfJoining + ", " + dateOfBirth, e);
            return false;
        }
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


    public static int getNumberOfCorruptedEntries() {
        logger.log(Level.INFO, "Number of corrupted entries: " + numberOfCorruptedEntries);
        return numberOfCorruptedEntries;
    }

    public static Set<String> cleanData(Set<String> employeeData) {
        LinkedHashSet<String> cleanedData = new LinkedHashSet<>();
        for (String line : employeeData) {
            String[] parts = line.split(",");
            if (parts.length != 10) {
                logger.log(Level.WARNING, "Invalid data format: " + line);
                continue;
            }

            String empID = parts[0];
            String prefix = parts[1];
            String firstName = parts[2];
            String middleInitial = parts[3];
            String lastName = parts[4];
            String gender = parts[5];
            String email = parts[6];
            String dateOfBirth = parts[7];
            String dateOfJoining = parts[8];
            String salary = parts[9];

            if (isEmployeeIdValid(empID) &&
                    isNameValid(firstName) &&
                    isNameValid(lastName) &&
                    isGenderValid(gender) &&
                    isEmailValid(email) &&
                    isDateOfBirthValid(dateOfBirth) &&
                    isDateOfJoiningValid(dateOfJoining, dateOfBirth) &&
                    isValidSalary(salary)) {
                cleanedData.add(line);
            } else {
                logger.log(Level.INFO, "Invalid data: " + line);
            }
        }

        return cleanedData;
    }

    public static boolean checkNewEmployee(String employeeId, String prefix, String firstName, String middleInitial,
                                           String lastName, String gender, String email, String dateOfBirth, String startDate, String salary) {

        if (isEmployeeIdValid(employeeId) &&
                isNameValid(firstName) &&
                isNameValid(lastName) &&
                isGenderValid(gender) &&
                isEmailValid(email) &&
                isDateOfBirthValid(dateOfBirth) &&
                isDateOfJoiningValid(startDate, dateOfBirth) &&
                isValidSalary(salary)) {
            return true;
        } else {
            return false;
        }

    }

    public static Object convertToDataType(String value, String dataType) {
        switch (dataType.toLowerCase()) {
            case "date":
                try {
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]");
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(value, inputFormatter);
                    return date.format(outputFormatter);
                } catch (IllegalArgumentException e) {

                    throw new IllegalArgumentException("Invalid date format for value: " + value + ". Expected format: YYYY-MM-DD", e);
                }
            case "char":
                if (value.length() == 1) {
                    return value.charAt(0);
                } else {
                    throw new IllegalArgumentException("Invalid char format for value: " + value + ". Expected single character.");
                }
            default:
                return value;
        }
    }

}

