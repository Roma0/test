package com.ascending.repository;

import com.ascending.model.Employee;

import java.util.List;

public interface EmployeeDao {
    Employee save(Employee employee);
    Employee updateEmployeeAddress(Employee employee, String address);
    List<Employee> getEmployees();
    boolean delete(String employeeName);
}
