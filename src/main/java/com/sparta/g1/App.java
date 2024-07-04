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
//        DBUtility.dropTable();
//        DBUtility.createEmployeeTable();
//        DBUtility.insertEmployeeIntoDatabase(connection, EmployeeFactory.getEmployees());



        String query = DatabaseQueries.DELETE_EMPLOYEE;

        String empId = "744722";
        String namePrefix = "Dr.";
        String firstName = "Henry";
        String middleInitial = "H";
        String lastName = "McFarlane";
        String gender = "F";
        String email = "bibi.lewis@yahoo.co.in";
        String dateOfBirth = "1988-01-01";
        String dateOfJoining = "2015-01-01";
        String salary = "100000";


//        DBUtility.executePreparedStatementUpdate(connection, query, namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary, empId);
        DBUtility.executePreparedStatementUpdate(connection, query, empId);




        DBConnection.getInstance().closeConnection();
    }
}
