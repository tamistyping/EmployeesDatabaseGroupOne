package com.sparta.g1;

import com.sparta.g1.database.DBConnection;
import com.sparta.g1.database.DBUtility;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class App {
    public static Connection connection = DBConnection.getInstance().getConnection();

    public static void main(String[] args) {
        DBUtility.tableInit(connection);
        try {
            ResultSet query = DBUtility.executePreparedStatementQuery(
                    connection, DatabaseQueries.NAME_SEARCH, "Cl%");
            DBUtility.printResultSet(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConnection.getInstance().closeConnection();
    }
}
