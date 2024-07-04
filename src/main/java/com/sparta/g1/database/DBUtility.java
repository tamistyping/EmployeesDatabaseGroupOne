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

    public static void insertEmployeeIntoDatabase(Set<String> employees) {
        String insertEmployeeSQL = "INSERT INTO EmployeeData (EmpID, NamePrefix, FirstName, MiddleInitial, LastName, Gender, Email, DateOfBirth, DateOfJoining, Salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            for (String employee: employees) {
                String[] fields = employee.split(",");
                try {
                    String empId = EmployeeDataCleaner.convertToDataType(fields[0], "int").toString();
                    String namePrefix = fields[1];
                    String firstName = fields[2];
                    String middleInitial = EmployeeDataCleaner.convertToDataType(fields[3], "char").toString();
                    String lastName = fields[4];
                    String gender = EmployeeDataCleaner.convertToDataType(fields[5], "char").toString();
                    String email = fields[6];
                    String dateOfBirth = EmployeeDataCleaner.convertToDataType(fields[7], "date").toString();
                    String dateOfJoining = EmployeeDataCleaner.convertToDataType(fields[8], "date").toString();
                    String salary = EmployeeDataCleaner.convertToDataType(fields[9], "int").toString();

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
