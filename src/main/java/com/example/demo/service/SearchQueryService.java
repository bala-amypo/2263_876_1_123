package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;

public interface SearchQueryService {

    SearchQueryRecord saveQuery(SearchQueryRecord query);

    List<Employee> searchEmployeesBySkills(List<String> skills, Long userId);

    SearchQueryRecord getQueryById(Long id);

    List<SearchQueryRecord> getQueriesForUser(Long userId);
}
