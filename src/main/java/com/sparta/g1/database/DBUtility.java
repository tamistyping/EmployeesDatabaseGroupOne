package com.sparta.g1.database;

import com.sparta.g1.EmployeeDataCleaner;

import java.sql.*;
import java.util.Set;

public class DBUtility {
    public static ResultSet executeQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public static void executePreparedStatement(Connection connection, String query, Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setString(i + 1, (String) params[i]);
        }
        preparedStatement.executeUpdate();
    }

    public static void dropTable() {
        String insertDropTableSQL = "DROP TABLE IF EXISTS EmployeeData;";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            executePreparedStatement(connection, insertDropTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createEmployeeTable() {
        String insertCreateTableSQL =
                ("CREATE TABLE EmployeeData (\n" +
                "    EmpID INT PRIMARY KEY,\n" +
                "    NamePrefix VARCHAR(10),\n" +
                "    FirstName VARCHAR(50),\n" +
                "    MiddleInitial CHAR(1),\n" +
                "    LastName VARCHAR(50),\n" +
                "    Gender CHAR(1),\n" +
                "    Email VARCHAR(100),\n" +
                "    DateOfBirth DATE,\n" +
                "    DateOfJoining DATE,\n" +
                "    Salary INT\n" +
                ");");
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            executePreparedStatement(connection, insertCreateTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertEmployeeIntoDatabase(Connection connection, Set<String> employees) {
        String insertEmployeeSQL = "INSERT INTO EmployeeData (EmpID, NamePrefix, FirstName, MiddleInitial, LastName, Gender, Email, DateOfBirth, DateOfJoining, Salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            for (String employee: employees) {
                String[] fields = employee.split(",");
                try {
                    String empId = fields[0];
                    String namePrefix = fields[1];
                    String firstName = fields[2];
                    String middleInitial = EmployeeDataCleaner.convertToDataType(fields[3], "char").toString();
                    String lastName = fields[4];
                    String gender = EmployeeDataCleaner.convertToDataType(fields[5], "char").toString();
                    String email = fields[6];
                    String dateOfBirth = EmployeeDataCleaner.convertToDataType(fields[7], "date").toString();
                    String dateOfJoining = EmployeeDataCleaner.convertToDataType(fields[8], "date").toString();
                    String salary = fields[9];

                    executePreparedStatement(connection, insertEmployeeSQL, empId, namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


    }
}
