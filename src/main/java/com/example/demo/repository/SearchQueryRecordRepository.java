package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SearchQueryRecord;

@Repository
public interface SearchQueryRecordRepository
        extends JpaRepository<SearchQueryRecord, Long> {

    List<SearchQueryRecord> findBySearcherId(Long searcherId);
}
