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
    private final SkillRepository skillRepository; 

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, SkillRepository skillRepository) {
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (employee.getActive() == null) employee.setActive(true);

        if (employee.getEmployeeSkills() != null) {
            for (EmployeeSkill es : employee.getEmployeeSkills()) {
                es.setEmployee(employee);

                Long skillId = es.getSkill().getId();
                es.setSkill(
                    skillRepository.findById(skillId)
                        .orElseThrow(() -> new IllegalArgumentException("Skill ID not found: " + skillId))
                );
            }
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
