package com.example.project1.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.Resident;
import com.example.project1.entity.Room;

@Component
public class RoomDataSeeder implements CommandLineRunner {
    @Autowired
	RoomRepository RoomRepo;
    ResidentRepository ResidentRepo;

	@Override
	public void run(String... args) throws Exception {
		loadUserData();
	}
    private void loadUserData() {
		RoomRepo.truncateTable();
        ResidentRepo.truncateTable();
        Room a;
        a = RoomRepo.save(new Room(0001, "123456789", "Phan Trọng Cường", "0911052881"));
        a.generateKey();
        a = RoomRepo.save(a);
        Resident b = ResidentRepo.save(new Resident("001203000768", "Ngô Đình Luyện", "Male", "Hà Nội", "Student", "0911052885", a.getKey(), a.getNoRoom(), "Chủ nhà", a.getIdOwner()));
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
