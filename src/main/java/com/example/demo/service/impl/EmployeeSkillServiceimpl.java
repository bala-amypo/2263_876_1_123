package com.example.demo.service.impl;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Employee;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeSkillServiceimpl implements EmployeeSkillService {

    private final EmployeeSkillRepository repo;
    private final EmployeeRepository employeeRepo;
    private final SkillRepository skillRepo;

    @Autowired
    public EmployeeSkillServiceimpl(EmployeeSkillRepository repo,
                                    EmployeeRepository employeeRepo,
                                    SkillRepository skillRepo) {
        this.repo = repo;
        this.employeeRepo = employeeRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public EmployeeSkill createData(EmployeeSkill es) {
        validate(es);
        return repo.save(es);
    }

    @Override
    public Optional<EmployeeSkill> fetchById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<EmployeeSkill> fetchByEmployee(Long employeeId) {
        return repo.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> fetchBySkill(Long skillId) {
        return repo.findBySkillIdAndActiveTrue(skillId);
    }

    private void validate(EmployeeSkill es) {
        if (es.getYearsOfExperience() == null || es.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years must be greater than or equal to 0");
        }

        if (es.getProficiencyLevel() == null) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        Optional<Employee> emp = employeeRepo.findById(es.getEmployee().getId());
        if (emp.isEmpty() || Boolean.FALSE.equals(emp.get().getActive())) {
            throw new IllegalArgumentException("inactive employee");
        }

        Optional<Skill> skill = skillRepo.findById(es.getSkill().getId());
        if (skill.isEmpty() || Boolean.FALSE.equals(skill.get().getActive())) {
            throw new IllegalArgumentException("inactive skill");
        }

        if (es.getActive() == null) {
            es.setActive(true);
        }
    }
}
