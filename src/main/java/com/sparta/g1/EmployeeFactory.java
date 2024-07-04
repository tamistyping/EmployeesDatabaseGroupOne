package com.sparta.g1;
import java.io.*;
import java.util.*;

public class EmployeeFactory {

    public static Set<String> getEmployees() throws IOException {
        Set<String> result = new HashSet<>();

        try (BufferedReader f = new BufferedReader(new FileReader("src/main/resources/employees.csv"))) {
            String employeeLine;
            while ((employeeLine = f.readLine()) != null) {
                result.add(employeeLine);
            }
        }

        return result;
    }
}
