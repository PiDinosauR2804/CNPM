package com.example.project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entity.AccountManager;

public interface AccountManagerRepository extends JpaRepository<AccountManager, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE account_manager", nativeQuery = true)
    void truncateTable();
}
