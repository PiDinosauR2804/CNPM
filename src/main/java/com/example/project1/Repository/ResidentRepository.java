package com.example.project1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE resident", nativeQuery = true)
    void truncateTable();

    @Query("SELECT r FROM Resident r WHERE r.room.noRoom = :noRoom")
    List<Resident> findByRoom(@Param("noRoom") int noRoom);
}
