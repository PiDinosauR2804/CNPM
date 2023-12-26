package com.example.project1.Repository;

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

    @Modifying
    @Transactional
    @Query("UPDATE DonationFee df SET df.amount = :amount, df.month = :month, df.year = :year WHERE df.room.noRoom = :no_room")
    void updateAllDonationFee(@Param("no_room") int no_room,
                        @Param("amount") int amount, 
                        @Param("month") int month, 
                        @Param("year") int year);
}
