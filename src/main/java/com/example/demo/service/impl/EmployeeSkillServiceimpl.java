package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;

@Service
public class EmployeeSkillServiceimpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeSkillServiceimpl(
            EmployeeSkillRepository employeeSkillRepository,
            EmployeeRepository employeeRepository,
            SkillRepository skillRepository) {

        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {

        // Validate experience
        if (mapping.getYearsOfExperience() == null ||
            mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years");
        }

        // Validate proficiency
        String p = mapping.getProficiencyLevel();
        if (!(p.equals("Beginner") || p.equals("Intermediate")
                || p.equals("Advanced") || p.equals("Expert"))) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        // Validate employee active
        Employee emp = employeeRepository
                .findById(mapping.getEmployee().getId())
                .orElseThrow();
        if (!emp.getActive()) {
            throw new IllegalArgumentException("inactive employee");
        }

        // Validate skill active
        Skill skill = skillRepository
                .findById(mapping.getSkill().getId())
                .orElseThrow();
        if (!skill.getActive()) {
            throw new IllegalArgumentException("inactive skill");
        }

        mapping.setActive(true);
        return employeeSkillRepository.save(mapping);
    }

    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill mapping) {

        EmployeeSkill existing = employeeSkillRepository
                .findById(id)
                .orElseThrow();

        mapping.setId(id);
        return createEmployeeSkill(mapping);
    }

    @Override
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId) {
        return employeeSkillRepository
                .findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        return employeeSkillRepository
                .findBySkillIdAndActiveTrue(skillId);
    }

    @Override
    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill es = employeeSkillRepository
                .findById(id)
                .orElseThrow();
        es.setActive(false);
        employeeSkillRepository.save(es);
    }
}
