package com.sparta.g1;

import com.sparta.g1.logger.AppLogger;
import com.sparta.g1.utilities.MonthValidation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDataCleaner {

    private static final Logger logger = AppLogger.getLogger(Level.ALL, Level.INFO, true);

    private static int numberOfCorruptedEntries = 0;

    //- Emp ID (up to 6 digits)
    //- Prefix
    //- First Name
    //- Middle Initial
    //- Last Name
    //- Gender (M or F)
    //- Email (standard email format)
    //- Date of Birth (MM/DD/YYYY)
    //- Date of Joining (MM/DD/YYYY)
    //- Salary
    public static boolean isEmployeeIdValid(String empId){

        if (empId.length() == 6) {

            return true;
        } else {
            numberOfCorruptedEntries++;
            return false;
        }
    }
    public static boolean isPrefixValid(String prefix) {
        return true;
    }
    public static boolean isNameValid(String name) {
        return true;
    }
    public static boolean isGenderValid(String gender) {
        if (gender.equals("M") || gender.equals("F")) {
            return true;
        } else {
            numberOfCorruptedEntries++;
            return false;
        }
    }
    public static boolean isEmailValid(String email) {
        if (email.matches("^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$")){
            return true;
        }else{
            numberOfCorruptedEntries++;
            return false;
        }
    }
    public static boolean isDateOfBirthValid(String dateOfBirth) {
        String[] parts = dateOfBirth.split("/");
        if (parts.length != 3) {
            numberOfCorruptedEntries++;
            return false;
        }
        try {
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            if (month < 1 || month > 12 ||
                    day < 1 || day > 31 ||
                    year < 1924 || year > LocalDate.now().getYear()) {
                numberOfCorruptedEntries++;
                return false;
            }
            if (!MonthValidation.isValidDayOfMonth(month, day, year)) {
                numberOfCorruptedEntries++;
                return false;
            }
            return true;

        } catch (NumberFormatException | DateTimeParseException e) {
            numberOfCorruptedEntries++;
        }
        return false;
    }
    public static boolean isDateOfJoiningValid(String dateOfJoining) {
        String[] parts = dateOfJoining.split("/");
        if (isDojBeforeDob(employee)) {
            numberOfCorruptedEntries++;
            return false;
        }
        if (parts.length != 3) {
            numberOfCorruptedEntries++;
            return false;
        }
        try {
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            if (month < 1 || month > 12 ||
                    day < 1 || day > 31 ||
                    year < 1979 || year > LocalDate.now().getYear()) {
                numberOfCorruptedEntries++;
                return false;
            }
            if (!MonthValidation.isValidDayOfMonth(month, day, year)) {
                numberOfCorruptedEntries++;
                return false;
            }
            return true;

        } catch (NumberFormatException | DateTimeParseException e) {
            numberOfCorruptedEntries++;
        }
        return false;
    }
    public static boolean isValidSalary(String salary){
        if(Integer.parseInt(salary)<0){
            numberOfCorruptedEntries++;
            return false;
        }
        return true;
    }
    public static boolean isDoJBeforeDoB(Employee employee) {
        if (employee.dob().isBefore(employee.dateOfJoining())) {
            return false;
        } else {
            return true;
        }
    }

    public static int getNumberOfCorruptedEntries() {
        logger.log(Level.FINER, "Entered get number of corrupted employees method");
        logger.log(Level.FINER, "Exited get number of corrupted employees method");
        return numberOfCorruptedEntries;
    }

    public static DateTimeFormatter formatDates() {
        return DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]");
    }
}

