package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository,
                                    EmployeeRepository employeeRepository,
                                    SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    private boolean isValidProficiency(String level) {
        return level != null &&
               (level.equalsIgnoreCase("Beginner") ||
                level.equalsIgnoreCase("Intermediate") ||
                level.equalsIgnoreCase("Advanced") ||
                level.equalsIgnoreCase("Expert"));
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {
        // 1️⃣ Experience validation
        if (mapping.getYearsOfExperience() == null || mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Years of experience cannot be negative");
        }

        // 2️⃣ Proficiency validation
        if (!isValidProficiency(mapping.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency level");
        }

        // 3️⃣ Employee validation
        Employee employee = employeeRepository
                .findById(mapping.getEmployee().getId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        if (!Boolean.TRUE.equals(employee.getActive())) {
            throw new IllegalArgumentException("Inactive employee");
        }

        // 4️⃣ Skill validation
        Skill skill = skillRepository
                .findById(mapping.getSkill().getId())
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));
        if (!Boolean.TRUE.equals(skill.getActive())) {
            throw new IllegalArgumentException("Inactive skill");
        }

        mapping.setEmployee(employee);
        mapping.setSkill(skill);
        mapping.setActive(true);

        return employeeSkillRepository.save(mapping);
    }

    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill mapping) {
        EmployeeSkill existing = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("EmployeeSkill not found"));

        if (mapping.getYearsOfExperience() == null || mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Years of experience cannot be negative");
        }

        if (!isValidProficiency(mapping.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency level");
        }

        Employee employee = employeeRepository
                .findById(mapping.getEmployee().getId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        if (!Boolean.TRUE.equals(employee.getActive())) {
            throw new IllegalArgumentException("Inactive employee");
        }

        Skill skill = skillRepository
                .findById(mapping.getSkill().getId())
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));
        if (!Boolean.TRUE.equals(skill.getActive())) {
            throw new IllegalArgumentException("Inactive skill");
        }

        existing.setEmployee(employee);
        existing.setSkill(skill);
        existing.setProficiencyLevel(mapping.getProficiencyLevel());
        existing.setYearsOfExperience(mapping.getYearsOfExperience());
        existing.setActive(true);

        return employeeSkillRepository.save(existing);
    }

    @Override
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId) {
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        return employeeSkillRepository.findBySkillIdAndActiveTrue(skillId);
    }

    @Override
    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill es = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("EmployeeSkill not found"));
        es.setActive(false);
        employeeSkillRepository.save(es);
    }
}
