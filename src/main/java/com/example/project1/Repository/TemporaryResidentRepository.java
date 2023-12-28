package com.example.project1.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.TemporaryResident;

public interface TemporaryResidentRepository extends JpaRepository<TemporaryResident, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE temporary_resident", nativeQuery = true)
    void truncateTable();

     // tìm kiếm theo idOwner -> phát triển ra concat để tìm được nhiều hơn
    @Query("SELECT r FROM TemporaryResident r WHERE concat(r.id, r.name) LIKE %?1%")
    public Page<TemporaryResident> findAll(String keyword,Pageable pagaeble);
    //Tìm kiếm trong một khoảng thời gian
    @Query(value = "SELECT * FROM temporary_resident WHERE STR_TO_DATE(day_in, '%Y-%m-%d') BETWEEN STR_TO_DATE(:startDate, '%Y-%m-%d') AND STR_TO_DATE(:endDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<TemporaryResident> findByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate, Pageable pageable);

    // Tìm kiếm theo keyword và khoảng thời gian
    @Query(value = "SELECT * FROM temporary_resident WHERE concat(id, name) LIKE %:keyword% AND STR_TO_DATE(day_in, '%Y-%m-%d') BETWEEN STR_TO_DATE(:startDate, '%Y-%m-%d') AND STR_TO_DATE(:endDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<TemporaryResident> findByKeywordAndDateRange(@Param("keyword") String keyword, @Param("startDate") String startDate, @Param("endDate") String endDate, Pageable pageable);

    // Tìm kiếm theo keyword và ngày bắt đầu
    @Query(value = "SELECT * FROM temporary_resident WHERE concat(id, name) LIKE %:keyword% AND STR_TO_DATE(day_in, '%Y-%m-%d') >= STR_TO_DATE(:startDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<TemporaryResident> findByKeywordAndstartDate(@Param("keyword") String keyword, @Param("startDate") String startDate, Pageable pageable);

    // Tìm kiếm theo keyword và ngày kết thúc
    @Query(value = "SELECT * FROM temporary_resident WHERE concat(id, name) LIKE %:keyword% AND STR_TO_DATE(day_out, '%Y-%m-%d') <= STR_TO_DATE(:endDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<TemporaryResident> findByKeywordAndendDate(@Param("keyword") String keyword, @Param("endDate") String endDate, Pageable pageable);
 // Tìm kiếm theo ngày bắt đầu
    @Query(value = "SELECT * FROM temporary_resident WHERE STR_TO_DATE(day_in, '%Y-%m-%d') >= STR_TO_DATE(:startDate, '%Y-%m-%d')", nativeQuery = true)
    public Page<TemporaryResident> findBystartDate(@Param("startDate") String startDate, Pageable pageable);

    // Tìm kiếm theo ngày kết thúc
    @Query(value = "SELECT * FROM temporary_resident WHERE STR_TO_DATE(day_out, '%Y-%m-%d') <= STR_TO_DATE(:endDate, '%Y-%m-%d')", nativeQuery = true)
    public Page <TemporaryResident> findByendDate(@Param("endDate") String endDate, Pageable pageable);
}
