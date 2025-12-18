package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSearchRequest;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.service.SearchQueryService;

@RestController
@RequestMapping("/api/search")
public class SearchQueryController {

    private final SearchQueryService service;
    public SearchQueryController(SearchQueryService service) {
        this.service = service;
    }

    @PostMapping("/employees")
    public List<Employee> searchEmployees(
            @RequestBody EmployeeSearchRequest request) {

        return service.searchEmployeesBySkills(
                request.getSkills(),
                request.getUserId()
        );
    }

    @GetMapping("/{id}")
    public SearchQueryRecord getById(@PathVariable Long id) {
        return service.getQueryById(id);
    }

    @GetMapping("/user/{userId}")
    public List<SearchQueryRecord> getByUser(@PathVariable Long userId) {
        return service.getQueriesForUser(userId);
    }
}
