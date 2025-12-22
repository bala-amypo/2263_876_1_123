package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // CREATE EMPLOYEE
    @Override
    public Employee createEmployee(Employee employee) {

        // Check email already exists
        Employee existingEmployee =
                employeeRepository.findByEmail(employee.getEmail()).orElse(null);

        if (existingEmployee != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Default active = true
        if (employee.getActive() == null) {
            employee.setActive(true);
        }

        //  Set employee reference in EmployeeSkill
        if (employee.getEmployeeSkills() != null) {
            for (EmployeeSkill skill : employee.getEmployeeSkills()) {
                skill.setEmployee(employee);
            }
        }

        return employeeRepository.save(employee);
    }

    // UPDATE EMPLOYEE
    @Override
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existing =
                employeeRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Employee not found"));

        existing.setFullName(employee.getFullName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setJobTitle(employee.getJobTitle());

        if (employee.getActive() != null) {
            existing.setActive(employee.getActive());
        }

        return employeeRepository.save(existing);
    }

    // GET EMPLOYEE BY ID
    @Override
    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));
    }

    // GET ALL EMPLOYEES
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // DEACTIVATE EMPLOYEE
    @Override
    public void deactivateEmployee(Long id) {

        Employee employee = getEmployeeById(id);
        employee.setActive(false);
        employeeRepository.save(employee);
    }
}
