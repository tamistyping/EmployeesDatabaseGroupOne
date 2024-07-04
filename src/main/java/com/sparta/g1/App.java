package com.sparta.g1;

import com.sparta.g1.database.DBUtility;

public class App {
    public static void main(String[] args) {
        DBUtility.insertEmployeeIntoDatabase(EmployeeFactory.getEmployees());
    }
}
