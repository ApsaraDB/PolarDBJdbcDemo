package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main( String[] args )
    {
        /* Step1: setup emp list */
        List<Employee> Employees = new ArrayList<>();
        Employees.add(new Employee("Tom", 10000));
        Employees.add(new Employee("Susan", 12000));
        Employees.add(new Employee("Jerry", 13000));

        /* Step2: add emp list to database */
        EmployeeManager emp = new EmployeeManager();
        emp.addAllEmployees(Employees);

        /* Step3: get emp from database */
        emp.getAllEmployees();
    }
}
