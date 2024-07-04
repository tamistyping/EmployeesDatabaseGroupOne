package com.sparta.g1;

public interface DatabaseQueries {
    String ID_SEARCH = "SELECT * FROM EmployeeData WHERE empId = ?";
    String NAME_SEARCH = "SELECT * FROM EmployeeData WHERE lastName LIKE ?";
    String GENDER_SEARCH = "SELECT * FROM EmployeeData WHERE gender = ?";
    String MIN_SALARY = "SELECT * FROM EmployeeData WHERE salary > ?";
    String MAX_SALARY = "SELECT * FROM EmployeeData WHERE salary < ?";
    String ALL_EMPLOYEES = "SELECT * FROM EmployeeData";
    String INSERT_EMPLOYEE = "INSERT INTO EmployeeData (employeeID, prefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_EMPLOYEE = "UPDATE EmployeeData SET prefix = ?, firstName = ?, middleInitial = ?, lastName = ?, gender = ?, email = ?, dateOfBirth = ?, dateOfJoining = ?, salary = ? WHERE employeeID = ?";
    String DELETE_EMPLOYEE = "DELETE FROM EmployeeData WHERE employeeID = ?";
    String COUNT_EMPLOYEES = "SELECT COUNT(*) FROM EmployeeData";
    String AVERAGE_SALARY = "SELECT AVG(salary) FROM EmployeeData";
    String EMPLOYEES_JOINED_AFTER = "SELECT * FROM EmployeeData WHERE dateOfJoining > ?";
    String EMPLOYEES_BORN_BEFORE = "SELECT * FROM EmployeeData WHERE dateOfBirth < ?";
}
