package com.example.project1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.MandatoryFee;

public interface MandatoryFeeRepository extends JpaRepository<MandatoryFee, Integer>{
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandatory_fee", nativeQuery = true)
    void truncateTable();

    @Query("SELECT r FROM MandatoryFee r WHERE r.room.noRoom = :noRoom")
    List<MandatoryFee> findByRoom(@Param("noRoom") int noRoom);

    @Query("SELECT r FROM MandatoryFee r WHERE r.no = :no")
    List<MandatoryFee> findByPK(@Param("no") int no);

    @Query("SELECT r FROM MandatoryFee r WHERE r.waterFeePaid = r.waterFee AND r.electricFee = r.electricFeePaid AND r.roomFeePaid = r.room.defaultFeeRoom AND r.parkingFeePaid = r.room.defaultParkingFee")
    List<MandatoryFee> findIfFeeComplete();
}
