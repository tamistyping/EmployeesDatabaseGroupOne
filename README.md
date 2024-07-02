# Employee Data Management System

## Overview
This project involves reading employee data from a corrupted CSV file, parsing and cleaning the data, and storing it into a MySQL database. The data should be formatted according to specific fields and any corrupted records should be logged.

## Requirements
The data in the CSV file should be parsed and cleaned to match the following format:
- Emp ID (up to 6 digits)
- Prefix
- First Name
- Middle Initial
- Last Name
- Gender (M or F)
- Email (standard email format)
- Date of Birth (MM/DD/YYYY)
- Date of Joining (MM/DD/YYYY)
- Salary

Corrupted data should be identified and logged appropriately.

## Database Setup
Create a new MySQL database and table to store the cleaned employee data. Ensure appropriate data types are used for each column.

## Bonus Task
Investigate methods to optimize the speed of data insertion into the MySQL database.

## DAO Class
Provide a DAO (Data Access Object) class to handle CRUD operations on the employees in the database.

## Testing
Plan and perform testing to ensure:
- Data parsing and cleaning are accurate.
- Corrupted records are properly logged.
- Data insertion into the database is efficient and error-free.
- DAO class functions correctly for CRUD operations.
