package com.sparta.g1.database;

import java.sql.ResultSet;
import java.sql.SQLException;
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

}
