package com.sparta.g1;

import com.sparta.g1.database.DBConnection;
import com.sparta.g1.database.DBQuery;
import com.sparta.g1.database.DBUtility;
import java.sql.Connection;

public class App {
    public static Connection connection = DBConnection.getInstance().getConnection();

    public static void main(String[] args) {
        //DBUtility.dropTable();
        DBUtility.tableInit(connection);
      
        //DBQuery.getEmployeeByGender("f");

        DBQuery.addEmployee("1", "Mr", "John", "D", "Doe", "M", "asdfg@gmail.com", "1999-10-10", "1995-10-10", "11111");
        DBConnection.getInstance().closeConnection();

        System.out.println(EmployeeDataCleaner.getNumberOfCorruptedEntries());
    }
}