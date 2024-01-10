package com.example.project1.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.ResidentHistory;

public interface ResidentHistoryRepository extends JpaRepository<ResidentHistory, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE resident_history", nativeQuery = true)
    void truncateTable();

    @Query("SELECT r FROM ResidentHistory r WHERE r.no = :no")
    List<ResidentHistory> findByNo(@Param("no") int no);

    @Query("SELECT r FROM ResidentHistory r WHERE r.id = :no")
    List<ResidentHistory> findByIdRes(@Param("no") String no);

    // tìm kiếm theo idOwner -> phát triển ra concat để tìm được nhiều hơn
    @Query("SELECT r FROM ResidentHistory r WHERE concat(r.id, r.name, r.roomHistory.key) LIKE %?1%")
    public Page<ResidentHistory> findAll(String keyword, Pageable pageable);

    //Tìm kiếm trong một khoảng thời gian
    @Query(value = "SELECT * FROM resident_history WHERE STR_TO_DATE(day_in, '%Y-%m-%d') BETWEEN STR_TO_DATE(:startDate, '%Y-%m-%d') AND STR_TO_DATE(:endDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<ResidentHistory> findByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate, Pageable pageable);

    // Tìm kiếm theo keyword và khoảng thời gian
    @Query(value = "SELECT * FROM resident_history r WHERE concat(r.name, r.id, r.job, r.birth_place, r.birth_date) LIKE %:keyword% AND STR_TO_DATE(day_in, '%Y-%m-%d') BETWEEN STR_TO_DATE(:startDate, '%Y-%m-%d') AND STR_TO_DATE(:endDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<ResidentHistory> findByKeywordAndDateRange(@Param("keyword") String keyword, @Param("startDate") String startDate, @Param("endDate") String endDate, Pageable pageable);

    // Tìm kiếm theo keyword và ngày bắt đầu
    @Query(value = "SELECT * FROM resident_history r WHERE concat(name, id, job,birth_place,birth_date) LIKE %:keyword% AND STR_TO_DATE(day_in, '%Y-%m-%d') >= STR_TO_DATE(:startDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<ResidentHistory> findByKeywordAndstartDate(@Param("keyword") String keyword, @Param("startDate") String startDate, Pageable pageable);

    // Tìm kiếm theo keyword và ngày kết thúc
    @Query(value = "SELECT * FROM resident_history r WHERE concat(name, id, job,birth_place,birth_date) LIKE %:keyword% AND STR_TO_DATE(day_out, '%Y-%m-%d') <= STR_TO_DATE(:endDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<ResidentHistory> findByKeywordAndendDate(@Param("keyword") String keyword, @Param("endDate") String endDate, Pageable pageable);
    
    // Tìm kiếm theo ngày bắt đầu
    @Query(value = "SELECT * FROM resident_history WHERE STR_TO_DATE(day_in, '%Y-%m-%d') >= STR_TO_DATE(:startDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<ResidentHistory> findBystartDate(@Param("startDate") String startDate, Pageable pageable);

    // Tìm kiếm theo ngày kết thúc
    @Query(value = "SELECT * FROM resident_history WHERE STR_TO_DATE(day_out, '%Y-%m-%d') <= STR_TO_DATE(:endDate, '%Y-%m-%d')", nativeQuery = true)
    public Page <ResidentHistory> findByendDate(@Param("endDate") String endDate, Pageable pageable);
}
