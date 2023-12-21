package com.example.project1.Repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.example.project1.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>{

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE room", nativeQuery = true)
    void truncateTable();

    @Query("SELECT p FROM Room p WHERE p.key LIKE %?1%")
    public List<Room> findByKey(String key);

    @Query("SELECT p FROM Room p WHERE p.key LIKE %?1%")
    public List<Room> findByRoom(int room);
    // tìm kiếm theo idOwner -> phát triển ra concat để tìm được nhiều hơn
    @Query("SELECT r FROM Room r WHERE r.idOwner LIKE %?1%")
    public Page<Room> findAll(String keyword,Pageable pagaeble);
    
}
