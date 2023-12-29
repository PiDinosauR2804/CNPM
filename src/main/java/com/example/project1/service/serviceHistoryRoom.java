package com.example.project1.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.project1.entity.RoomHistory;
import com.example.project1.Repository.RoomHistoryRepository;

@Service
public class serviceHistoryRoom {

    @Autowired
    private RoomHistoryRepository repo;

    public Page<RoomHistory> listAll(String keyword, String startDate, String endDate, Integer pageNo) {
<<<<<<< HEAD
        Pageable pageable = PageRequest.of(pageNo - 1, 2);
=======
        Pageable pageable = PageRequest.of(pageNo - 1, 10);
>>>>>>> 174339fcce31fa19dc4e24efa2aeb7f327170cd0

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
