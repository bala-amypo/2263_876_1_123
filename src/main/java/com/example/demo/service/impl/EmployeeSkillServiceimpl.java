package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.service.EmployeeSkillService;

@Service
public class EmployeeSkillServiceimpl implements EmployeeSkillService {

    @Autowired
    EmployeeSkillRepository repo;

    @Override
    public EmployeeSkill createData(EmployeeSkill es) {
        return repo.save(es);
    }

    @Override
    public List<EmployeeSkill> fetchAll() {
        return repo.findAll();
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

    @Override
    public void deactivate(Long id) {
        Optional<EmployeeSkill> es = repo.findById(id);

        if (es.isPresent()) {
            es.get().setActive(false);
            repo.save(es.get());
        }
    }
}
