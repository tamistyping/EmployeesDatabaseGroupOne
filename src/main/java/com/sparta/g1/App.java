package com.sparta.g1;

import com.sparta.g1.database.DBConnection;
import com.sparta.g1.database.DBUtility;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) {
        DBUtility.insertEmployeeIntoDatabase(EmployeeFactory.getEmployees());
    }
}
