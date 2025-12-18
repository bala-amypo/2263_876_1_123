package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SearchQueryRecordRepository;
import com.example.demo.service.SearchQueryService;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    @Autowired
    private EmployeeSkillRepository employeeSkillRepo;

    @Autowired
    private SearchQueryRecordRepository searchQueryRecordRepo;

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {

        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("must not be empty");
        }

        List<Employee> employees =
                employeeSkillRepo.findEmployeesByAllSkillNames(skills);

        SearchQueryRecord record = new SearchQueryRecord(
                userId,
                String.join(",", skills),
                employees.size()
        );

        searchQueryRecordRepo.save(record);

        return employees;
    }
}
