package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceimpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;   // constructor injection only

    // constructor signature must be exactly (EmployeeRepository)
    public EmployeeServiceimpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        // unique email check, simple if
        Employee existing = employeeRepository.findByEmail(employee.getEmail()).orElse(null);
        if (existing != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        // default active = true if not set
        if (employee.getActive() == null) {
            employee.setActive(true);
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = employeeRepository.findById(id).orElse(null);
        if (existing == null) {
            throw new ResourceNotFoundException("Employee not found");
        }

        existing.setFullName(employee.getFullName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setJobTitle(employee.getJobTitle());

        if (employee.getActive() != null) {
            existing.setActive(employee.getActive());
        }

        return employeeRepository.save(existing);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee emp = employeeRepository.findById(id).orElse(null);
        if (emp == null) {
            throw new ResourceNotFoundException("Employee not found");
        }
        return emp;
    }

    // CHANGE HERE: return all employees, including inactive
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void deactivateEmployee(Long id) {
        Employee employee = getEmployeeById(id);   // throws if not found
        employee.setActive(false);                 // just update flag
        employeeRepository.save(employee);                       // UPDATE, not DELETE
    }
}
