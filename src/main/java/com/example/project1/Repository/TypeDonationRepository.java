package com.example.project1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.TypeDonation;

public interface TypeDonationRepository extends JpaRepository<TypeDonation, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE type_donation", nativeQuery = true)
    void truncateTable();

    // tìm kiếm theo id và type
    @Query("SELECT r FROM TypeDonation r WHERE concat(r.idMoney, r.type) LIKE %?1%")
    public Page<TypeDonation> findAll(String keyword, Pageable pageable);
    

    @Query("SELECT r FROM TypeDonation r WHERE r.idMoney = :idMoney")
    List<TypeDonation> findByNo(@Param("idMoney") int idMoney);
}
