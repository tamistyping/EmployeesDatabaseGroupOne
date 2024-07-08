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
//        DBUtility.dropTable();
        DBUtility.tableInit(connection);



        DBQuery.getEmployeeById("112115");
        String employeeId = "112115";
        String prefix = "Mr.";
        String firstName = "Bob";
        String middleInitial = "F";
        String lastName = "Parlak";
        String gender = "M";
        String email = "gene.sebastian@yahoo.ca";
        String dateOfBirth = "1988-04-22";
        String startDate = "2015-03-19";
        String salary = "139501";

        DBQuery.updateEmployee(employeeId, prefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, startDate, salary);


        DBConnection.getInstance().closeConnection();
        System.out.println(EmployeeDataCleaner.getNumberOfCorruptedEntries());
    }
}