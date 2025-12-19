package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
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
        validateMapping(mapping);
        return employeeSkillRepository.save(mapping);
    }

    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill mapping) {
        EmployeeSkill existing = employeeSkillRepository.findById(id).orElse(null);
        if (existing == null) {
            throw new ResourceNotFoundException("EmployeeSkill not found");
        }

        validateMapping(mapping);

        existing.setEmployee(mapping.getEmployee());
        existing.setSkill(mapping.getSkill());
        existing.setProficiencyLevel(mapping.getProficiencyLevel());
        existing.setYearsOfExperience(mapping.getYearsOfExperience());
        if (mapping.getActive() != null) {
            existing.setActive(mapping.getActive());
        }

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
        EmployeeSkill mapping = employeeSkillRepository.findById(id).orElse(null);
        if (mapping == null) {
            throw new ResourceNotFoundException("EmployeeSkill not found");
        }
        mapping.setActive(false);
        employeeSkillRepository.save(mapping);
    }

    private void validateMapping(EmployeeSkill mapping) {
   
        if (mapping.getYearsOfExperience() == null || mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years must be greater than or equal to 0");
        }

        if (mapping.getProficiencyLevel() == null) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        Employee emp = employeeRepository.findById(mapping.getEmployee().getId()).orElse(null);
        if (emp == null || Boolean.FALSE.equals(emp.getActive())) {
            throw new IllegalArgumentException("inactive employee");
        }
        Skill skill = skillRepository.findById(mapping.getSkill().getId()).orElse(null);
        if (skill == null || Boolean.FALSE.equals(skill.getActive())) {
            throw new IllegalArgumentException("inactive skill");
        }

        if (mapping.getActive() == null) {
            mapping.setActive(true);
        }
    }
}
