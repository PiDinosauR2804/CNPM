package com.example.project1.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.DonationFeeHistory;

public interface DonationFeeHistoryRepository extends JpaRepository<DonationFeeHistory, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE donation_fee_history", nativeQuery = true)
    void truncateTable();
    @Query("SELECT dh FROM DonationFeeHistory dh JOIN FETCH dh.roomHistory rh WHERE CONCAT(dh.month, dh.year, dh.amount, dh.typeMoney, rh.noRoom) LIKE %:keyword%")
    Page<DonationFeeHistory> findAll(@Param("keyword") String keyword, Pageable pageable);
}
