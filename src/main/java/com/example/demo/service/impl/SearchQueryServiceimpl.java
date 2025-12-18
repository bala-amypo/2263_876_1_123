package com.example.demo.service.implementation;

import java.util.List;
import java.util.Optional;

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
    private SearchQueryRecordRepository searchRepo;

    @Autowired
    private EmployeeSkillRepository employeeSkillRepo;

    @Override
    public SearchQueryRecord saveQuery(SearchQueryRecord query) {
        return searchRepo.save(query);
    }

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {

        if (skills == null || skills.isEmpty())
            throw new IllegalArgumentException("must not be empty");

        List<Employee> result =
                employeeSkillRepo.findEmployeesByAllSkillNames(skills, userId);

        searchRepo.save(
            new SearchQueryRecord(userId, skills.toString(), result.size())
        );

        return result;
    }

    @Override
    public SearchQueryRecord getQueryById(Long id) {

        Optional<SearchQueryRecord> record = searchRepo.findById(id);

        if (record.isPresent())
            return record.get();
        else
            throw new RuntimeException("SearchQueryRecord not found");
    }

    @Override
    public List<SearchQueryRecord> getQueriesForUser(Long userId) {
        return searchRepo.findBySearcherId(userId);
    }
}
