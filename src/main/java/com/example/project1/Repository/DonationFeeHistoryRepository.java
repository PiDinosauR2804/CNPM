package com.example.project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.DonationFeeHistory;

public interface DonationFeeHistoryRepository extends JpaRepository<DonationFeeHistory, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE donation_fee_history", nativeQuery = true)
    void truncateTable();
}
