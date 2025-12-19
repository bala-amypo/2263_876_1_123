package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeSkillServiceimpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeSkillServiceimpl(EmployeeSkillRepository employeeSkillRepository,
                                    EmployeeRepository employeeRepository,
                                    SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {

        // Experience validation
        if (mapping.getYearsOfExperience() == null || mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years");
        }

        // Proficiency validation
        if (mapping.getProficiencyLevel() == null) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        // Employee active check
        Employee employee = employeeRepository
                .findById(mapping.getEmployee().getId())
                .orElseThrow();

        if (!Boolean.TRUE.equals(employee.getActive())) {
            throw new IllegalArgumentException("inactive employee");
        }

        // Skill active check
        Skill skill = skillRepository
                .findById(mapping.getSkill().getId())
                .orElseThrow();

        if (!Boolean.TRUE.equals(skill.getActive())) {
            throw new IllegalArgumentException("inactive skill");
        }

        return employeeSkillRepository.save(mapping);
    }

    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill mapping) {

        EmployeeSkill existing = employeeSkillRepository.findById(id)
                .orElseThrow();

        // Same validations
        if (mapping.getYearsOfExperience() == null || mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years");
        }

        if (mapping.getProficiencyLevel() == null) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        existing.setEmployee(mapping.getEmployee());
        existing.setSkill(mapping.getSkill());
        existing.setProficiencyLevel(mapping.getProficiencyLevel());
        existing.setYearsOfExperience(mapping.getYearsOfExperience());

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
        EmployeeSkill es = employeeSkillRepository.findById(id).orElseThrow();
        es.setActive(false);
        employeeSkillRepository.save(es);
    }
}
