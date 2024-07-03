package com.sparta.g1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;

public class DataCleanerTests {
    private Employee bibi;
    private Employee eric;
    private Employee Renetta;
    private Employee john;
    private Employee jane;
    private Employee bob;
    private Employee alice;


    @BeforeEach
    void setup() {
        bibi = new Employee("744723", "Hon.", "Bibi", "H", "Paddock", "F", "bibi.paddock@yahoo.co.in", LocalDate.of(1991, 10, 20), LocalDate.of(2016, 11, 2), "87148");
        eric = new Employee("423093", "Mr.", "Eric", "O", "Manning", "M", "eric.manning@yahoo.com", LocalDate.of(1980, 10, 20), LocalDate.of(2002, 11, 2), "149363");
        Renetta = new Employee("207808", "Ms.", "Renetta", "T", "Hafner", "F", "renetta.hafner@aol.com", LocalDate.of(1975, 10, 20), LocalDate.of(1998, 11, 2), "189363");
        // Incorrect gender
        john = new Employee("123456", "Mr.", "John", "D", "Doe", "X", "john.doe@gmail.com", LocalDate.of(1980, 1, 1), LocalDate.of(2000, 1, 1), "123456");
        // Incorrect email
        jane = new Employee("654321", "Ms.", "Jane", "D", "Doe", "F", "jane.doegmail.com", LocalDate.of(1985, 1, 1), LocalDate.of(2005, 1, 1), "654321");
        // Incorrect employee ID length
        bob = new Employee("7890", "Mr.", "Bob", "B", "Builder", "M", "bob.builder@gmail.com", LocalDate.of(1970, 1, 1), LocalDate.of(1990, 1, 1), "789012");
        // Date of birth is not before date of joining
        alice = new Employee("987654", "Ms.", "Alice", "A", "Adams", "F", "alice.adams@gmail.com", LocalDate.of(2000, 1, 1), LocalDate.of(1990, 1, 1), "987654");

    }


    @Test
    @DisplayName("Given an employee has a negative salary then false should be returned")
    void givenNegativeSalaryReturnFalse() {
        //Arrange
        boolean expected = false;

        //Act
        boolean actual = EmployeeDataCleaner.isValidSalary("-100000");

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if valid email string returns true")
    public void checkIsValidEmailReturnsTrueOnValidEmailInput() {
        String input = "email@email.com";
        boolean expected = true;
        boolean actual = EmployeeDataCleaner.isEmailValid(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkIdIsValidLength() {
        boolean expected = true;
        boolean actual = EmployeeDataCleaner.isEmployeeIdValid(bibi.empId());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkValidGender() {
        boolean expected = true;
        boolean actual = EmployeeDataCleaner.isGenderValid(bibi.gender());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void isValidEmail() {
        boolean expected = true;
        boolean actual = EmployeeDataCleaner.isEmailValid(bibi.email());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkDobIsBeforeDoj() {
        boolean expected = true;
        boolean actual = EmployeeDataCleaner.isDoJBeforeDoB(bibi);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkValidGenderForJohn() {
        boolean expected = false;
        boolean actual = EmployeeDataCleaner.isGenderValid(john.gender());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void isValidEmailForJane() {
        boolean expected = false;
        boolean actual = EmployeeDataCleaner.isEmailValid(jane.email());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkEmpIdIsCorrectLengthForBob() {
        boolean expected = false;
        boolean actual = EmployeeDataCleaner.isEmployeeIdValid(bob.empId());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkDobIsBeforeDojForAlice() {
        boolean expected = false;
        boolean actual = EmployeeDataCleaner.isDoJBeforeDoB(alice);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check Valid Date of Birth Returns True")
    void checkValidDateOfBirthReturnsTrue() {
        String input = "03/12/1970";
        Boolean expected = true;
        boolean actual = EmployeeDataCleaner.isDateOfBirthValid(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check Invalid Date of Birth Returns False")
    void checkInvalidDateOfBirthReturnsFalse() {
        String input = "03/12/1000";
        Boolean expected = false;
        boolean actual = EmployeeDataCleaner.isDateOfBirthValid(input);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Check Valid Date of Joining Returns True")
    void checkValidDateOfJoiningReturnsTrue() {
        String input = "03/12/2000";
        Boolean expected = true;
        boolean actual = EmployeeDataCleaner.isDateOfJoiningValid(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check Invalid Date of Joining Returns False")
    void checkInvalidDateOfJoiningReturnsFalse() {
        String input = "03/12/2025";
        Boolean expected = false;
        boolean actual = EmployeeDataCleaner.isDateOfJoiningValid(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check Invalid Day Of Month On Leap Year Returns False")
    void checkInvalidDayOfMonthOnLeapYearReturnsFalse() {
        String input = "02/30/2020";
        Boolean expected = false;
        boolean actual = EmployeeDataCleaner.isDateOfJoiningValid(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("check Invalid Day Of Month Returns False For 30 Day Month")
    void checkInvalidDayOfMonthReturnsFalseFor30DayMonth() {
        String input = "4/31/2020";
        Boolean expected = false;
        boolean actual = EmployeeDataCleaner.isDateOfJoiningValid(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("check Valid Day Of Month On Leap Year Returns True")
    void checkValidDayOfMonthOnLeapYearReturnsTrue() {
        String input = "02/29/2020";
        Boolean expected = true;
        boolean actual = EmployeeDataCleaner.isDateOfJoiningValid(input);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    @DisplayName("check Valid Day Of Month Returns True For 30 Day Month")
    void checkValidDayOfMonthReturnsTrueFor30DayMonth() {
        String input = "04/30/2020";
        Boolean expected = true;
        boolean actual = EmployeeDataCleaner.isDateOfJoiningValid(input);
        Assertions.assertEquals(expected, actual);
    }

}
