package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    public static void main(String[] args) {

        // JDBC URL, username, and password of the MySQL server
        String url = "jdbc:mysql://localhost:3306/students_db";  // Change 'students_db' to your DB name
        String user = "root";  // Replace with your MySQL username
        String password = "root";  // Replace with your MySQL password

        // Initialize connection object
        Connection connection = null;

        try {
            // Load the MySQL JDBC driver (optional, depending on Java version)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection to the database
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to the database was successful!");

            // Create a statement object to execute the query
            Statement statement = connection.createStatement();

            // Execute a SQL SELECT query
            String query = "SELECT * FROM students";  // Change 'students' to your table name
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set
            System.out.println("Student Data:");
            while (resultSet.next()) {
                // Assuming your table has columns 'id', 'name', and 'age'
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String age = resultSet.getString("course");

                // Print each row of data
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }0

            // Close the result set and statement
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception: " + e.getMessage());
        } finally {
            try {
                // Close the connection if it was successfully created
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                System.out.println("Error while closing the connection: " + e.getMessage());
            }
        }
    }
}
