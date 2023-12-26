package com.example.project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.MandatoryFee;

public interface MandatoryFeeHistoryRepository extends JpaRepository<MandatoryFee, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandatory_fee_history", nativeQuery = true)
    void truncateTable();
}
