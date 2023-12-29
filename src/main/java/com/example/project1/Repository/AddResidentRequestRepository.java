package com.example.project1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.AddResidentRequest;

public interface AddResidentRequestRepository extends JpaRepository<AddResidentRequest, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE add_resident_request", nativeQuery = true)
    void truncateTable();  

    @Query("SELECT r FROM AddResidentRequest r WHERE r.approved = :approved")
    List<AddResidentRequest> findRequestByApproved(@Param("approved") int approved);

    @Query("SELECT r FROM AddResidentRequest r WHERE r.approved != 1")
    List<AddResidentRequest> findHistoryRequest();

    @Query("SELECT r FROM AddResidentRequest r WHERE r.no = :no")
    List<AddResidentRequest> findByNo(@Param("no") int no);
}
