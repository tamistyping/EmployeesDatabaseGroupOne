package com.sparta.g1;

import com.sparta.g1.database.DBConnection;
import com.sparta.g1.database.DBUtility;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static Connection connection = DBConnection.getInstance().getConnection();

    public static void main(String[] args) {
        DBUtility.dropTable();
        DBUtility.createEmployeeTable();
        DBUtility.insertEmployeeIntoDatabase(connection, EmployeeFactory.getEmployees());

        try {
            ResultSet query = DBUtility.executePreparedStatementQuery(connection, DatabaseQueries.ID_SEARCH, "121372");
            DBUtility.printResultSet(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        DBConnection.getInstance().closeConnection();
    }
}
