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

    // ✅ Validate proficiency level
    private boolean isValidProficiency(String level) {
        if (level == null) return false;
        switch (level.toLowerCase()) {
            case "beginner":
            case "intermediate":
            case "advanced":
            case "expert":
                return true;
            default:
                return false;
        }
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {

        // 1️⃣ Experience validation
        if (mapping.getYearsOfExperience() == null || mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience cannot be negative");
        }

        // 2️⃣ Proficiency validation
        if (!isValidProficiency(mapping.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        // 3️⃣ Employee validation
        if (mapping.getEmployee() == null || mapping.getEmployee().getId() == null) {
            throw new IllegalArgumentException("Employee required");
        }
        Employee employee = employeeRepository.findById(mapping.getEmployee().getId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        if (Boolean.FALSE.equals(employee.getActive())) {
            throw new IllegalArgumentException("Cannot assign skill to inactive employee");
        }

        // 4️⃣ Skill validation
        if (mapping.getSkill() == null || mapping.getSkill().getId() == null) {
            throw new IllegalArgumentException("Skill required");
        }
        Skill skill = skillRepository.findById(mapping.getSkill().getId())
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));
        if (Boolean.FALSE.equals(skill.getActive())) {
            throw new IllegalArgumentException("Cannot assign inactive skill");
        }

        // 5️⃣ Set active flag and save
        mapping.setActive(true);
        return employeeSkillRepository.save(mapping);
    }

    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill mapping) {
        EmployeeSkill existing = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("EmployeeSkill not found"));

        // Validate updated data
        if (mapping.getYearsOfExperience() == null || mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience cannot be negative");
        }
        if (!isValidProficiency(mapping.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        Employee employee = employeeRepository.findById(mapping.getEmployee().getId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        if (Boolean.FALSE.equals(employee.getActive())) {
            throw new IllegalArgumentException("Cannot assign skill to inactive employee");
        }

        Skill skill = skillRepository.findById(mapping.getSkill().getId())
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));
        if (Boolean.FALSE.equals(skill.getActive())) {
            throw new IllegalArgumentException("Cannot assign inactive skill");
        }

        // Update fields
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
