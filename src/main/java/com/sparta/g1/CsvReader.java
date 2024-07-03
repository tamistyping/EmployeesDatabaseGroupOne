package com.sparta.g1;

import com.sparta.g1.logger.AppLogger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvReader {

    private static final Logger logger = AppLogger.getLogger(Level.ALL, Level.INFO, true);

    public static void EmployeeDataReader() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/employees.csv"));
            int counter = 0;
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                logger.log(Level.INFO, "Added line to bufferedReader");
                System.out.println(line);
                counter++;
                //DTO
            }
            bufferedReader.close();
            System.out.println(counter);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
