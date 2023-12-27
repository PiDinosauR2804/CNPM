package com.example.project1.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.project1.entity.TemporaryResident;
import com.example.project1.Repository.TemporaryResidentRepository;

@Service
public class serviceTemp {

    @Autowired
    private TemporaryResidentRepository repo;

    public Page<TemporaryResident> listAll(String keyword, String startDate, String endDate, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 2);

        if (keyword != null && !(startDate == "" || startDate==null)&& !(endDate==null||endDate == "")) {
            return repo.findByKeywordAndDateRange(keyword, startDate, endDate, pageable);
        } else if (keyword != null && !(startDate == "" || startDate==null)) {
            return repo.findByKeywordAndstartDate(keyword, startDate, pageable);
        } else if (keyword != null && !(endDate==null||endDate == "")) {
            return repo.findByKeywordAndendDate(keyword, endDate, pageable);
        } else if (!(startDate == "" || startDate==null) &&!(endDate==null||endDate == "")) {
            return repo.findByDateRange(startDate, endDate, pageable);
        } else if (keyword != null) {
            return repo.findAll(keyword, pageable);
        } else if (!(startDate == "" || startDate==null)) {
            return repo.findBystartDate(startDate, pageable);
        } else if (!(endDate==null||endDate == "")) {
            return repo.findByendDate(endDate, pageable);
        } else {
            return repo.findAll(pageable);
        }
    }
}