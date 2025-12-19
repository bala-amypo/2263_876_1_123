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

    // ----------------------------------------------------

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {

        validateEmployeeSkill(mapping);

        mapping.setActive(true);
        return employeeSkillRepository.save(mapping);
    }

    // ----------------------------------------------------

    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill mapping) {

        EmployeeSkill existing = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("EmployeeSkill not found"));

        validateEmployeeSkill(mapping);

        existing.setExperience(mapping.getExperience());
        existing.setProficiency(mapping.getProficiency());
        existing.setEmployee(mapping.getEmployee());
        existing.setSkill(mapping.getSkill());

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

        EmployeeSkill mapping = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("EmployeeSkill not found"));

        mapping.setActive(false);
        employeeSkillRepository.save(mapping);
    }


    private void validateEmployeeSkill(EmployeeSkill mapping) {

        if (mapping.getExperience() < 0) {
            throw new IllegalArgumentException("Experience must be non-negative");
        }

        if (mapping.getProficiency() == null) {
            throw new IllegalArgumentException("Proficiency must not be null");
        }

        Long employeeId = mapping.getEmployee().getId();
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        if (!employee.isActive()) {
            throw new IllegalArgumentException("Employee is inactive");
        }

        Long skillId = mapping.getSkill().getId();
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));

        if (!skill.isActive()) {
            throw new IllegalArgumentException("Skill is inactive");
        }
    }
}
