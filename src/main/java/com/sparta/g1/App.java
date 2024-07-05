package com.sparta.g1;

import com.sparta.g1.database.DBConnection;
import com.sparta.g1.database.DBQuery;
import com.sparta.g1.database.DBUtility;
import com.sparta.g1.employee.Employee;

import java.sql.Connection;
import java.time.LocalDate;

public class App {
    public static Connection connection = DBConnection.getInstance().getConnection();

    public static void main(String[] args) {


        DBUtility.dropTable();
        DBUtility.tableInit(connection);
        DBQuery.getEmployeeByGender("f");
        //DBQuery.addEmployee("744723", "Hon.", "Bibi", "H", "Paddock", "F", "bibi.paddock@yahoo.co.in", "1991-10-20", "2016-11-02", "87148");

        DBConnection.getInstance().closeConnection();

        System.out.println(EmployeeDataCleaner.getNumberOfCorruptedEntries());
    }
}
