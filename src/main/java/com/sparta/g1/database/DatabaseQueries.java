package com.sparta.g1.database;

public interface DatabaseQueries {
    String ID_SEARCH = "SELECT * FROM EmployeeData WHERE empId = ?";
    String NAME_SEARCH = "SELECT * FROM EmployeeData WHERE CONCAT(lastName, ' ', firstName) LIKE ?";
    String GENDER_SEARCH = "SELECT * FROM EmployeeData WHERE gender = ?";
    String MIN_SALARY = "SELECT * FROM EmployeeData WHERE salary >= ?";
    String MAX_SALARY = "SELECT * FROM EmployeeData WHERE salary <= ?";
    String ALL_EMPLOYEES = "SELECT * FROM EmployeeData";
    String INSERT_EMPLOYEE = "INSERT INTO EmployeeData (empId, NamePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_EMPLOYEE = "UPDATE EmployeeData SET NamePrefix = ?, firstName = ?, middleInitial = ?, lastName = ?, gender = ?, email = ?, dateOfBirth = ?, dateOfJoining = ?, salary = ? WHERE empId = ?";
    String DELETE_EMPLOYEE = "DELETE FROM EmployeeData WHERE empId = ?";
    String COUNT_EMPLOYEES = "SELECT COUNT(*) FROM EmployeeData";
    String AVERAGE_SALARY = "SELECT AVG(salary) FROM EmployeeData";
    String EMPLOYEES_JOINED_AFTER = "SELECT * FROM EmployeeData WHERE dateOfJoining >= ?";
    String EMPLOYEES_BORN_BEFORE = "SELECT * FROM EmployeeData WHERE dateOfBirth < ?";
}
