package com.sparta.g1;
import com.sparta.g1.logger.AppLogger;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeFactory {

    private static final Logger logger = AppLogger.getLogger(Level.SEVERE, Level.SEVERE, false);

    public static Set<String> getEmployees(String path){
        Set<String> result = new HashSet<>();
        try (BufferedReader f = new BufferedReader(new FileReader(path))) {
            String employeeLine;
            while ((employeeLine = f.readLine()) != null) {
                result.add(employeeLine);
            }
        } catch (IOException e) {
            try {
                BufferedReader f = new BufferedReader(new FileReader("src/main/resources/employees.csv"));
                String employeeLine;
                while ((employeeLine = f.readLine()) != null) {
                    result.add(employeeLine);
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "IOException thrown when reading default path employees.csv" +
                        " - it is likely that src/main/resources/employees.csv is wrong or missing");
            }
            logger.log(Level.SEVERE, "IOException thrown when reading employees.csv");
        }
        return EmployeeDataCleaner.cleanData(result);
    }
}
