package com.example.project1.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE resident", nativeQuery = true)
    void truncateTable();

    @Query("SELECT r FROM Resident r WHERE r.room.noRoom = :noRoom")
    List<Resident> findByRoom(@Param("noRoom") int noRoom);

    @Query("SELECT r FROM Resident r WHERE concat(r.id, r.name) LIKE %?1%")
    public Page<Resident> findAll(String keyword,Pageable pagaeble);

    @Modifying
    @Transactional
    @Query("UPDATE Resident r SET r.name = :name, r.gender = :gender, r.birthDate = :birthDate, r.birthPlace = :birthPlace, r.job = :job, r.phoneNumber = :phoneNumber, r.relationshipWithOwner = :relationshipWithOwner WHERE r.id = :id")
    void updateResident(@Param("id") String id, 
                        @Param("name") String name, 
                        @Param("gender") String gender,
                        @Param("birthDate") String birthDate, 
                        @Param("birthPlace") String birthPlace,
                        @Param("job") String job, 
                        @Param("phoneNumber") String phoneNumber,
                        @Param("relationshipWithOwner") String relationshipWithOwner);
                        
}
