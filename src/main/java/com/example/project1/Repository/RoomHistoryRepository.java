package com.example.project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.RoomHistory;

public interface RoomHistoryRepository extends JpaRepository<RoomHistory, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE room_history", nativeQuery = true)
    void truncateTable();
}
