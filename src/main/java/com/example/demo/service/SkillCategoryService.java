package com.example.demo.service;

import java.util.*;
import com.example.demo.model.SkillCategory;

public interface EmployeeService {
    Employee createEmployee(Employee emp);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
}
