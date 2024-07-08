package com.sparta.g1;

import com.sparta.g1.database.DBUtility;
import com.sparta.g1.database.DatabaseQueries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ExecutePreparedStatementTest {
    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    public void testExecutePreparedStatementQuery() throws SQLException {
        String query = "SELECT FROM employees WHERE name LIKE ?";
        String param = "%John%";

        ResultSet resultSet = DBUtility.executePreparedStatementQuery(mockConnection, query, param);


        verify(mockConnection).prepareStatement(query);

        verify(mockPreparedStatement).setString(1, param);

        verify(mockPreparedStatement).executeQuery();

        assertEquals(mockResultSet, resultSet);
    }
    @Test
    public void testExecutePreparedStatementQueryWithMultipleParams() throws SQLException {
        String query = "SELECT * FROM employees WHERE first_name = ? AND last_name = ?";
        String param1 = "John";
        String param2 = "Doe";

        ResultSet resultSet = DBUtility.executePreparedStatementQuery(mockConnection, query, param1, param2);

        verify(mockConnection).prepareStatement(query);
        verify(mockPreparedStatement).setString(1, param1);
        verify(mockPreparedStatement).setString(2, param2);
        verify(mockPreparedStatement).executeQuery();
        assertEquals(mockResultSet, resultSet);
    }

    @Test
    public void testExecutePreparedStatementQuerySQLException() throws SQLException {
        String query = "SELECT * FROM employees WHERE name LIKE ?";
        String param = "%John%";

        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException());

        assertThrows(SQLException.class, () -> {
            DBUtility.executePreparedStatementQuery(mockConnection, query, param);
        });

        verify(mockConnection).prepareStatement(query);
    }
}