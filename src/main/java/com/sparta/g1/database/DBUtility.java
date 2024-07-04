package com.sparta.g1.database;

import com.sparta.g1.EmployeeDataCleaner;
import com.sparta.g1.EmployeeFactory;

import java.sql.*;
import java.util.List;
import java.util.Set;

public class DBUtility {
    public static ResultSet executeQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public static void executePreparedStatementUpdate(Connection connection, String query, Object... params) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < params.length; i++) {
            try {
                assert preparedStatement != null;
                preparedStatement.setString(i + 1, (String) params[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            assert preparedStatement != null;
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executePreparedStatementQuery(Connection connection, String query, String... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setString(i + 1, params[i]);
        }
        return preparedStatement.executeQuery();
    }

    public static void dropTable() {
        String insertDropTableSQL = "DROP TABLE IF EXISTS EmployeeData;";
        Connection connection = DBConnection.getInstance().getConnection();
        executePreparedStatementUpdate(connection, insertDropTableSQL);
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
        executePreparedStatementUpdate(connection, insertCreateTableSQL);

    }
    private static boolean doesTableExist(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName, new String[]{"TABLE"})) {
            return resultSet.next();
        }
    }

    public static void tableInit(Connection connection) {
        try {
            if (!doesTableExist(connection, "EmployeeData")) {
                createEmployeeTable();
                insertEmployeeIntoDatabase(connection, EmployeeFactory.getEmployees("src/main/resources/employees.csv"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

                    executePreparedStatementUpdate(connection, insertEmployeeSQL, empId, namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


    }


    public static void printResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) System.out.print(" || ");
            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) System.out.print(" || ");
                System.out.print(resultSet.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }
}
