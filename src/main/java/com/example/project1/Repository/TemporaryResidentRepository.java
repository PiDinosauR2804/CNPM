package com.example.project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.TemporaryResident;

public interface TemporaryResidentRepository extends JpaRepository<TemporaryResident, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE temporary_resident", nativeQuery = true)
    void truncateTable();
}
