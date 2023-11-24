package com.example;

public class Employee {
    private long id;
    private String name;
    private int salary;

    public Employee() {
    }

    public Employee(String name, int salary) {
        super();
        this.name = name;
        this.salary = salary;
    }

    // ID 
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // NAME
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // SALARY
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void displayEmployeeInformation() {
        System.out.println("Emp ID:" + this.id);
        System.out.println("Emp NAME:" + this.name);
        System.out.println("Emp SALARY:" + this.salary);
    }
}