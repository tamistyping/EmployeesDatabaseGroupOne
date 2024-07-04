package com.sparta.g1;
import com.sparta.g1.logger.AppLogger;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeFactory {

    private static final Logger logger = AppLogger.getLogger(Level.ALL, Level.SEVERE, false);

    public static Set<String> getEmployees(){
        Set<String> result = new HashSet<>();

        try (BufferedReader f = new BufferedReader(new FileReader("src/main/resources/employees.csv"))) {
            String employeeLine;
            while ((employeeLine = f.readLine()) != null) {
                result.add(employeeLine);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int count = 0;

        Set<String> cleanedResult = EmployeeDataCleaner.cleanData(result);
        for (String data : cleanedResult) {
            logger.log(Level.INFO, "Cleaned Data: " + data);
            count++;
        }

        logger.log(Level.INFO, "Number of cleaned entries: " + count);

        return result;
    }
}
