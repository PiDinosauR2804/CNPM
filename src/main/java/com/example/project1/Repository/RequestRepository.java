package com.example.project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Integer>{
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE Request", nativeQuery = true)
    void truncateTable();
}
