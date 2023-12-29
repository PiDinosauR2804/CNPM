package com.example.project1.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.MandatoryFeeHistory;

public interface MandatoryFeeHistoryRepository extends JpaRepository<MandatoryFeeHistory, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandatory_fee_history", nativeQuery = true)
    void truncateTable();

    @Query("SELECT mfh FROM MandatoryFeeHistory mfh JOIN FETCH mfh.roomHistory rh WHERE CONCAT(mfh.month, mfh.year, rh.key, rh.noRoom) LIKE %:keyword%")
    Page<MandatoryFeeHistory> findAll(@Param("keyword") String keyword, Pageable pageable);
}
