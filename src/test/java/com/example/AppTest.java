package com.example;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    EmployeeManager EmpManager = new EmployeeManager();

    private final static String NAME_1 = "AAAAAA";
    private final static int SALARY_1 = 10000;

    private final static String NAME_2 = "Bbbbbb";
    private final static int SALARY_2 = 12000;

    private final static String NAME_3 = "CCCCC";
    private final static int SALARY_3 = 13000;

    private final static String NAME_4 = "DDDDD";
    private final static int SALARY_4 = 11000;

    Employee emp1 = new Employee(NAME_1, SALARY_1);
    Employee emp2 = new Employee(NAME_2, SALARY_2);
    Employee emp3 = new Employee(NAME_3, SALARY_3);
    Employee emp4 = new Employee(NAME_4, SALARY_4);

    @Test
    public void CheckConnection() {
        Assert.assertNotNull(EmpManager.getConnection());
    }

    @Test
    public void CheckAdding() {
        Employee emp = new Employee(NAME_1, SALARY_1);

        EmpManager.clearEmployees();
        Assert.assertEquals(1, EmpManager.addEmployee(emp));

        List<Employee> empList = EmpManager.getAllEmployees();
        Employee EmpRetrieved = empList.get(0);

        Assert.assertEquals(NAME_1, EmpRetrieved.getName());
        Assert.assertEquals(SALARY_1, EmpRetrieved.getSalary());
    }

    @Test
    public void CheckAll() {
        EmpManager.clearEmployees();

        List<Employee> empList = new ArrayList<>();
        empList.add(emp1);
        empList.add(emp2);
        empList.add(emp3);
        empList.add(emp4);

        EmpManager.addAllEmployees(empList);
        int size = EmpManager.getAllEmployees().size();

        Assert.assertEquals(size, 4);
    }
}
