package com.example;

import java.util.List;

public interface EmployeeManagerInterface {
	/* ADD one employee */
	public int addEmployee(Employee employee);

	/* get all persons */
	public List<Employee> getAllEmployees();

	/* batch insert - transactional */
	public void addAllEmployees(List<Employee> employee);
}