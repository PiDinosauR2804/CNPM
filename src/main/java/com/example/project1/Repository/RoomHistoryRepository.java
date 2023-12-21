package com.example.project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.RoomHisotry;

public interface RoomHistoryRepository extends JpaRepository<RoomHisotry, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE roomHistory", nativeQuery = true)
    void truncateTable();
}
