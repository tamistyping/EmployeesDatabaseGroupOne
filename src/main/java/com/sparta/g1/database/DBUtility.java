package com.sparta.g1.database;

import com.sparta.g1.EmployeeDataCleaner;

import java.sql.*;
import java.util.List;
import java.util.Set;

public class DBUtility {
    public static ResultSet executeQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public static ResultSet executePreparedStatement(Connection connection, String query, Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setString(i + 1, (String) params[i]);
        }
        return preparedStatement.executeQuery();
    }

    public static void insertEmployeeIntoDatabase(Set<String> employees) {
        String insertEmployeeSQL = "INSERT INTO Employee (EmpID, NamePrefix, FirstName, MiddleInitial, LastName, Gender, Email, DateOfBirth, DateOfJoining, Salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            for (String employee: employees) {
                String[] fields = employee.split(",");
                try {
                    int empId = (int) EmployeeDataCleaner.convertToDataType(fields[0], "int");
                    String namePrefix = fields[1];
                    String firstName = fields[2];
                    char middleInitial = (char) EmployeeDataCleaner.convertToDataType(fields[3], "char");
                    String lastName = fields[4];
                    char gender = (char) EmployeeDataCleaner.convertToDataType(fields[5], "char");
                    String email = fields[6];
                    Date dateOfBirth = (Date) EmployeeDataCleaner.convertToDataType(fields[7], "date");
                    Date dateOfJoining = (Date) EmployeeDataCleaner.convertToDataType(fields[8], "date");
                    int salary = (int) EmployeeDataCleaner.convertToDataType(fields[9], "int");

                    executePreparedStatement(connection, insertEmployeeSQL, empId, namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
