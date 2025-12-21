package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    // Valid proficiency levels
    private static final List<String> VALID_PROFICIENCIES =
            List.of("Beginner", "Intermediate", "Advanced", "Expert");

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository,
                                    EmployeeRepository employeeRepository,
                                    SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    // CREATE EmployeeSkill
    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {
        // Validate experience
        if (mapping.getYearsOfExperience() == null || mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Invalid years of experience");
        }

        // Validate proficiency
        if (mapping.getProficiencyLevel() == null ||
            !VALID_PROFICIENCIES.contains(mapping.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency level");
        }

        // Check employee exists and active
        Employee employee = employeeRepository
                .findById(mapping.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (!Boolean.TRUE.equals(employee.getActive())) {
            throw new IllegalArgumentException("Employee is inactive");
        }

        // Check skill exists and active
        Skill skill = skillRepository
                .findById(mapping.getSkill().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
        if (!Boolean.TRUE.equals(skill.getActive())) {
            throw new IllegalArgumentException("Skill is inactive");
        }

        mapping.setEmployee(employee);
        mapping.setSkill(skill);
        mapping.setActive(true);

        return employeeSkillRepository.save(mapping);
    }

    // UPDATE EmployeeSkill
    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill mapping) {
        EmployeeSkill existing = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeSkill not found"));

        // Validate experience
        if (mapping.getYearsOfExperience() == null || mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Invalid years of experience");
        }

        // Validate proficiency
        if (mapping.getProficiencyLevel() == null ||
            !VALID_PROFICIENCIES.contains(mapping.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency level");
        }

        // Set updated values
        existing.setEmployee(mapping.getEmployee());
        existing.setSkill(mapping.getSkill());
        existing.setProficiencyLevel(mapping.getProficiencyLevel());
        existing.setYearsOfExperience(mapping.getYearsOfExperience());

        return employeeSkillRepository.save(existing);
    }

    // GET all active skills for an employee
    @Override
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId) {
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }

    // GET all active employees having a skill
    @Override
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        return employeeSkillRepository.findBySkillIdAndActiveTrue(skillId);
    }

    // Deactivate EmployeeSkill
    @Override
    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill employeeSkill = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeSkill not found"));

        employeeSkill.setActive(false);
        employeeSkillRepository.save(employeeSkill);
    }
}
