package com.example.project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.AddResidentRequest;

public interface AddResidentRequestRepository extends JpaRepository<AddResidentRequest, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE add_resident_request", nativeQuery = true)
    void truncateTable();  
}
