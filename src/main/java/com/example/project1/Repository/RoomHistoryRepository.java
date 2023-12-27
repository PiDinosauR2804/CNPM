package com.example.project1.Repository;

import java.util.List;

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

    @Query("SELECT p FROM RoomHistory p WHERE p.key LIKE %?1%")
    public List<RoomHistory> findByKey(String key);
}
