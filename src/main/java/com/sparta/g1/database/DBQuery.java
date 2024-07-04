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

}
