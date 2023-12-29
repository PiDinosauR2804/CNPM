package com.example.project1.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.MandatoryFee;

public interface MandatoryFeeRepository extends JpaRepository<MandatoryFee, Integer>{
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE Mandatory_Fee", nativeQuery = true)
    void truncateTable();

    @Query("SELECT r FROM MandatoryFee r WHERE r.room.noRoom = :noRoom")
    List<MandatoryFee> findByRoom(@Param("noRoom") int noRoom);

    @Query("SELECT r FROM MandatoryFee r WHERE r.no = :no")
    List<MandatoryFee> findByPK(@Param("no") int no);

    @Query("SELECT r FROM MandatoryFee r WHERE r.waterFeePaid = r.waterFee AND r.electricFee = r.electricFeePaid AND r.roomFeePaid = r.room.defaultFeeRoom AND r.parkingFeePaid = r.room.defaultParkingFee")
    List<MandatoryFee> findIfFeeComplete();

    @Modifying
    @Transactional
    @Query("UPDATE MandatoryFee mf SET mf.roomFeePaid = :roomFeePaid, mf.waterFee = :waterFee, mf.waterFeePaid = :waterFeePaid, mf.electricFee = :electricFee, mf.electricFeePaid = :electricFeePaid, mf.parkingFeePaid = :parkingFeePaid WHERE mf.no = :no")
    void updateAllMandatoryFee(@Param("no") int no,
                        @Param("roomFeePaid") int roomFeePaid, 
                        @Param("waterFee") int waterFee, 
                        @Param("waterFeePaid") int waterFeePaid,
                        @Param("electricFee") int electricFee, 
                        @Param("electricFeePaid") int electricFeePaid,
                        @Param("parkingFeePaid") int parkingFeePaid);

    @Query("SELECT mf FROM MandatoryFee mf JOIN FETCH mf.room r WHERE CONCAT(mf.month, mf.year, r.key, r.noRoom) LIKE %:keyword%")
    Page<MandatoryFee> findAll(@Param("keyword") String keyword, Pageable pageable);

    
}
