package com.sparta.g1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {
    public static void EmployeeDataReader() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/employees.csv"));
            int counter = 0;
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
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
