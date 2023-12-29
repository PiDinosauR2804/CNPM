package com.example.project1.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.example.project1.entity.RoomHistory;


public interface RoomHistoryRepository extends JpaRepository<RoomHistory, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE room_history", nativeQuery = true)
    void truncateTable();

    @Query("SELECT p FROM RoomHistory p WHERE p.key LIKE %?1%")
    public List<RoomHistory> findByKey(String key);
<<<<<<< HEAD
    
 // tìm kiếm theo
=======

    // tìm kiếm theo
>>>>>>> 174339fcce31fa19dc4e24efa2aeb7f327170cd0
    @Query("SELECT r FROM RoomHistory r WHERE concat(r.nameOwner, r.key, r.idOwner) LIKE %?1%")
    public Page<RoomHistory> findAll(String keyword,Pageable pageable);
    //Tìm kiếm trong một khoảng thời gian
    @Query(value = "SELECT * FROM room_history WHERE STR_TO_DATE(day_in, '%Y-%m-%d') BETWEEN STR_TO_DATE(:startDate, '%Y-%m-%d') AND STR_TO_DATE(:endDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<RoomHistory> findByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate, Pageable pageable);

    // Tìm kiếm theo keyword và khoảng thời gian
    @Query(value = "SELECT * FROM room_history WHERE concat(name_owner, id_owner, no_room) LIKE %:keyword% AND STR_TO_DATE(day_in, '%Y-%m-%d') BETWEEN STR_TO_DATE(:startDate, '%Y-%m-%d') AND STR_TO_DATE(:endDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<RoomHistory> findByKeywordAndDateRange(@Param("keyword") String keyword, @Param("startDate") String startDate, @Param("endDate") String endDate, Pageable pageable);

    // Tìm kiếm theo keyword và ngày bắt đầu
    @Query(value = "SELECT * FROM room_history WHERE concat(name_owner, id_owner,no_room) LIKE %:keyword% AND STR_TO_DATE(day_in, '%Y-%m-%d') >= STR_TO_DATE(:startDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<RoomHistory> findByKeywordAndstartDate(@Param("keyword") String keyword, @Param("startDate") String startDate, Pageable pageable);

    // Tìm kiếm theo keyword và ngày kết thúc
    @Query(value = "SELECT * FROM room_history WHERE concat(name_owner, id_owner,no_room) LIKE %:keyword% AND STR_TO_DATE(day_out, '%Y-%m-%d') <= STR_TO_DATE(:endDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<RoomHistory> findByKeywordAndendDate(@Param("keyword") String keyword, @Param("endDate") String endDate, Pageable pageable);
 // Tìm kiếm theo ngày bắt đầu
    @Query(value = "SELECT * FROM room_history WHERE STR_TO_DATE(day_in, '%Y-%m-%d') >= STR_TO_DATE(:startDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<RoomHistory> findBystartDate(@Param("startDate") String startDate, Pageable pageable);

    // Tìm kiếm theo ngày kết thúc
    @Query(value = "SELECT * FROM room_history WHERE STR_TO_DATE(day_out, '%Y-%m-%d') <= STR_TO_DATE(:endDate, '%Y-%m-%d')", nativeQuery = true)
    public Page <RoomHistory> findByendDate(@Param("endDate") String endDate, Pageable pageable);
}
