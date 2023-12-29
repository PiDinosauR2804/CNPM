package com.example.project1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Integer>{
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE Request", nativeQuery = true)
    void truncateTable();

    @Query("SELECT r FROM Request r WHERE r.approved = :approved")
    List<Request> findRequestByApproved(@Param("approved") int approved);

    @Query("SELECT r FROM Request r WHERE r.no = :no")
    List<Request> findByNo(@Param("no") int no);
}
