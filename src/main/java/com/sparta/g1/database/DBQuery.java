package com.sparta.g1.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.sparta.g1.App.connection;

public class DBQuery implements DatabaseQueries {
    public static void getEmployeeById(String employeeID) {
        try {
            ResultSet query = DBUtility.executePreparedStatementQuery(
                    connection, DatabaseQueries.ID_SEARCH, employeeID);
            DBUtility.printResultSet(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getEmployeeByName(String employeeName) {
        String searchParameter = "%" + employeeName + "%";
        try {
            ResultSet query = DBUtility.executePreparedStatementQuery(
                    connection, DatabaseQueries.NAME_SEARCH, searchParameter);
            DBUtility.printResultSet(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getEmployeeByGender(String employeeGender) {
        String searchParameter = "";
        employeeGender = employeeGender.toLowerCase();
        if (employeeGender == "male" || employeeGender == "m") {
            searchParameter = "M";
        } else if (employeeGender == "female" || employeeGender == "f") {
            searchParameter = "F";
        }
        try {
            ResultSet query = DBUtility.executePreparedStatementQuery(
                    connection, DatabaseQueries.GENDER_SEARCH, searchParameter);
            DBUtility.printResultSet(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getEmployeeByMinimumSalary(String employeeSalary) {
        try {
            ResultSet query = DBUtility.executePreparedStatementQuery(
                    connection, DatabaseQueries.MIN_SALARY, employeeSalary);
            DBUtility.printResultSet(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getEmployeeByMaximumSalary(String employeeSalary) {
        try {
            ResultSet query = DBUtility.executePreparedStatementQuery(
                    connection, DatabaseQueries.MAX_SALARY, employeeSalary);
            DBUtility.printResultSet(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getAllEmployees() {
        try {
            ResultSet query = DBUtility.executePreparedStatementQuery(
                    connection, DatabaseQueries.ALL_EMPLOYEES);
            DBUtility.printResultSet(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getNumberOfEmployees() {
        try {
            ResultSet query = DBUtility.executePreparedStatementQuery(
                    connection, DatabaseQueries.COUNT_EMPLOYEES);
            DBUtility.printResultSet(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getAverageEmployeeSalary() {
        try {
            ResultSet query = DBUtility.executePreparedStatementQuery(
                    connection, DatabaseQueries.AVERAGE_SALARY);
            DBUtility.printResultSet(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getEmployeesWhoJoinedAfter(String startDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy][d/M/yyyy][dd/M/yyyy][d/M/yyyy]");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(startDate, inputFormatter);
        startDate = date.format(outputFormatter);
        try {
            ResultSet query = DBUtility.executePreparedStatementQuery(
                    connection, DatabaseQueries.EMPLOYEES_JOINED_AFTER, startDate);
            DBUtility.printResultSet(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getEmployeesBornBefore(String birthday) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy][d/M/yyyy][dd/M/yyyy][d/M/yyyy]");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(birthday, inputFormatter);
        birthday = date.format(outputFormatter);
        try {
            ResultSet query = DBUtility.executePreparedStatementQuery(
                    connection, DatabaseQueries.EMPLOYEES_BORN_BEFORE, birthday);
            DBUtility.printResultSet(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addEmployee(String employeeId, String prefix, String firstName, String middleInitial,
                                   String lastName, String gender, String email, String dateOfBirth, String startDate, String salary) {
        if (middleInitial == "" || middleInitial == null) {
            middleInitial = null;
        }
        DBUtility.executePreparedStatementUpdate(
                connection, DatabaseQueries.INSERT_EMPLOYEE, employeeId, prefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, startDate, salary);

    }

}
