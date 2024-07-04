package com.sparta.g1;

public interface DatabaseQueries {
    String ID_SEARCH = "SELECT * FROM employees WHERE employeeID = ?";
    String NAME_SEARCH = "SELECT * FROM employees WHERE lastName LIKE ?";
    String GENDER_SEARCH = "SELECT * FROM employees WHERE gender = ?";
    String MIN_SALARY = "SELECT * FROM employees WHERE salary > ?";
    String MAX_SALARY = "SELECT * FROM employees WHERE salary < ?";
    String ALL_EMPLOYEES = "SELECT * FROM employees";
    String INSERT_EMPLOYEE = "INSERT INTO employees (employeeID, prefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_EMPLOYEE = "UPDATE employees SET prefix = ?, firstName = ?, middleInitial = ?, lastName = ?, gender = ?, email = ?, dateOfBirth = ?, dateOfJoining = ?, salary = ? WHERE employeeID = ?";
    String DELETE_EMPLOYEE = "DELETE FROM employees WHERE employeeID = ?";
    String COUNT_EMPLOYEES = "SELECT COUNT(*) FROM employees";
    String AVERAGE_SALARY = "SELECT AVG(salary) FROM employees";
    String EMPLOYEES_JOINED_AFTER = "SELECT * FROM employees WHERE dateOfJoining > ?";
    String EMPLOYEES_BORN_BEFORE = "SELECT * FROM employees WHERE dateOfBirth < ?";
}
