package com.example.demo.controller;

import com.example.demo.dto.EmployeeSearchRequest;
import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.service.SearchQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@Tag(name = "Search Queries", description = "Search employees by skills")
public class SearchQueryController {

    private final SearchQueryService searchQueryService;

    // Constructor injection (TestNG-compliant)
    public SearchQueryController(SearchQueryService searchQueryService) {
        this.searchQueryService = searchQueryService;
    }

    // POST /api/search/employees
    @PostMapping("/employees")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestBody EmployeeSearchRequest request) {
        List<Employee> employees = searchQueryService.searchEmployeesBySkills(
                request.getSkills(),
                request.getUserId()
        );
        return ResponseEntity.ok(employees);
    }

    // GET /api/search/{id}
    @GetMapping("/{id}")
    public ResponseEntity<SearchQueryRecord> getQueryById(@PathVariable Long id) {
        SearchQueryRecord record = searchQueryService.getQueryById(id);
        return ResponseEntity.ok(record);
    }

    // GET /api/search/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SearchQueryRecord>> getQueriesForUser(@PathVariable Long userId) {
        List<SearchQueryRecord> records = searchQueryService.getQueriesForUser(userId);
        return ResponseEntity.ok(records);
    }
}
