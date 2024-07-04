package com.sparta.g1;

import java.time.LocalDate;

public record Employee(String employeeId,
                       String prefix,
                       String firstName,
                       String middleInitial,
                       String lastName,
                       String gender,
                       String email,
                       LocalDate dob,
                       LocalDate dateOfJoining,
                       String salary) {

    public Employee {
        if (employeeId == null || employeeId.isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty");
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", prefix='" + prefix + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", dateOfJoining=" + dateOfJoining +
                ", salary=" + salary +
                '}';
    }
}
