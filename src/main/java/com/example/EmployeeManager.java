package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmployeeManager implements EmployeeManagerInterface {

    private Connection connection;
    private final String url = "jdbc:polardb2://11.239.69.226:51235/postgres";
    private final String createTableEmployee = "CREATE TABLE Employee(id bigint GENERATED BY DEFAULT AS IDENTITY, name varchar(20) UNIQUE, salary integer)";
    private PreparedStatement addEmployeeStmt;
    private PreparedStatement deleteAllEmployeesStmt;
    private PreparedStatement getAllEmployeesStmt;
    private Statement statement;

    /* main class */
    public EmployeeManager() {
        try {
            Properties props = new Properties();
            props.put("user", "postgres");
            props.put("password", "postgres");

            connection = DriverManager.getConnection(url, props);
            statement = connection.createStatement();
            ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
            boolean tableExists = false;
            while (rs.next()) {
                if ("Employee".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    tableExists = true;
                    break;
                }
            }

            if (!tableExists)
                statement.executeUpdate(createTableEmployee);

            addEmployeeStmt = connection.prepareStatement("INSERT INTO Employee (name, salary) VALUES (?, ?)");
            deleteAllEmployeesStmt = connection.prepareStatement("DELETE FROM Employee");
            getAllEmployeesStmt = connection.prepareStatement("SELECT id, name, salary FROM Employee");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Connection getConnection() {
        return connection;
    }

    void clearEmployees() {
        try {
            deleteAllEmployeesStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addEmployee(Employee Employee) {
        int count = 0;
        try {
            addEmployeeStmt.setString(1, Employee.getName());
            addEmployeeStmt.setInt(2, Employee.getSalary());

            count = addEmployeeStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> Employees = new ArrayList<Employee>();

        try {
            ResultSet rs = getAllEmployeesStmt.executeQuery();

            while (rs.next()) {
                Employee p = new Employee();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setSalary(rs.getInt("salary"));
                Employees.add(p);

                /* Print all employee information */
                p.displayEmployeeInformation();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Employees;
    }

    @Override
    public void addAllEmployees(List<Employee> Employees) {

        try {
            connection.setAutoCommit(false);
            for (Employee Employee : Employees) {
                addEmployeeStmt.setString(1, Employee.getName());
                addEmployeeStmt.setInt(2, Employee.getSalary());
                addEmployeeStmt.executeUpdate();
            }
            connection.commit();

        } catch (SQLException exception) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}