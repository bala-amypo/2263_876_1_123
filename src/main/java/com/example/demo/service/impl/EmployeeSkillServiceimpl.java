package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.repository.EmployeeSkillRepository;

@Service
public class EmployeeSkillServiceimpl implements EmployeeSkillService {

    @Autowired
    private EmployeeSkillRepository repo;

    @Override
    public EmployeeSkill createData(EmployeeSkill es) {
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
}
