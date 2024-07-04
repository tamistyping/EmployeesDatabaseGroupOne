package com.sparta.g1;
import java.io.*;
import java.util.*;

public class EmployeeFactory {

        public static String[] getEmployees(int numEmployees) throws IOException {
            if (numEmployees < 1 || numEmployees > 1000)
                throw new IllegalArgumentException("Argument 'numEmployees' must be between 1 and 1000");
            List<String> result = new ArrayList<>();

            try (BufferedReader f = new BufferedReader(new FileReader("EmployeesProjectG1/src/main/resources/employees.csv"))) {
                String employeeLine;
                while ((employeeLine = f.readLine()) != null)
                    result.add(employeeLine);
            }

            if (result.size() < numEmployees) {
                throw new IllegalArgumentException("The file does not contain enough records.");
            }

            Collections.shuffle(result);
            return result.subList(0, numEmployees).toArray(new String[0]);
        }
}

