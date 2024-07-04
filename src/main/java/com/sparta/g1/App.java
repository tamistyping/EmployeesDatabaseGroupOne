package com.sparta.g1;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            EmployeeFactory.getEmployees();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
