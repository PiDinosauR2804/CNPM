package com.example.project1.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.Resident;
import com.example.project1.entity.Room;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class RoomDataSeeder implements CommandLineRunner {
    @Autowired
	RoomRepository RoomRepo;
    @Autowired
    ResidentRepository ResidentRepo;

	@Override
    @Transactional
	public void run(String... args) throws Exception {
		loadUserData();
	}

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void disableForeignKeyChecks() {
        entityManager.createNativeQuery("SET foreign_key_checks = 0").executeUpdate();
    }

    @Transactional
    public void enableForeignKeyChecks() {
        entityManager.createNativeQuery("SET foreign_key_checks = 1").executeUpdate();
    }

    @Transactional
    public void Truncate() {
        disableForeignKeyChecks();
        ResidentRepo.truncateTable();
		RoomRepo.truncateTable();
        enableForeignKeyChecks();
    }

    private void loadUserData() {
        Truncate();
        Room a;
        a = RoomRepo.save(new Room(0001, "123456789", "Phan Trọng Cường", "0911052881"));
        a.generateKey();
        a = RoomRepo.save(a);
        Resident b = ResidentRepo.save(new Resident("001203000768", "Ngô Đình Luyện", "Male", "28/04/2003", "Hà Nội", "Student", "0911052885", a.getKey(), a.getNoRoom(), "Chủ", a.getIdOwner()));
        ResidentRepo.save(b);

        a = RoomRepo.save(new Room(0002, "123456781", "Hoàng Đức Huy", "0911052882"));
        a.generateKey();
        RoomRepo.save(a);

        a = RoomRepo.save(new Room(0003, "123456782", "Lê Đình Trí Tuệ", "0911052883"));
        a.generateKey();
        RoomRepo.save(a);
        a = RoomRepo.save(new Room(0004, "123456783", "Ngô Đình Luyện", "0911052884"));
        a.generateKey();
        RoomRepo.save(a);
        a = RoomRepo.save(new Room(0005, "123456784", "Nguyễn Xuân Phúc", "0911052885"));
        a.generateKey();
        RoomRepo.save(a);
    }
}
