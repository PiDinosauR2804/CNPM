package com.example.project1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.DonationFee;

public interface DonationFeeRepository extends JpaRepository<DonationFee, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE donation_fee", nativeQuery = true)
    void truncateTable();

    @Query("SELECT r FROM DonationFee r WHERE r.typeDonation.id = :id")
    List<DonationFee> findByNo(@Param("id") int id);
}
