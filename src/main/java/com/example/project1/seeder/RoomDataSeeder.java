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
        // Room 1
        Room a = new Room(0004, "123456783", "Ngô Đình Luyện", "0911052884");
        a.generateKey();
        RoomRepo.save(a);

        Resident b = new Resident("001203000768", "Ngô Đình Luyện", "Male", "28/04/2003", "Hà Nội", "Student", "0911052885", "Chủ");
        a.addResident(b);
        b.setRoom(a);
        ResidentRepo.save(b);

        // Room 2
        Room c = new Room(0002, "123456781", "Hoàng Đức Huy", "0911052882");
        c.generateKey();
        RoomRepo.save(c);

        Resident d = new Resident("004206000238", "Người Thương Của Huy", "Male", "12/04/2003", "Hà Nội", "Student", "0344859662", "Chủ");
        c.addResident(d);
        d.setRoom(c);
        ResidentRepo.save(d);

        Resident d1 = new Resident("004206000240", "Huy", "Male", "04/12/2003", "Hà Nội", "Dượng", "0344859629", "Phò");
        c.addResident(d1);
        d1.setRoom(c);
        ResidentRepo.save(d1);

        // Room 3
        Room e = new Room(0003, "123456782", "Lê Đình Trí Tuệ", "0911052883");
        e.generateKey();
        RoomRepo.save(e);

        Resident f = new Resident("002304000568", "Lê Đình Trí Tuệ", "Male", "20/09/2003", "Hà Nội", "Student", "0233846110", "Chủ");
        e.addResident(f);
        f.setRoom(e);
        ResidentRepo.save(f);

        Resident f1 = new Resident("002304000570", "Người Thương Của Tuệ", "Female", "09/20/2003", "Hà Nội", "Student", "0233846115", "Con nuôi");
        e.addResident(f1);
        f1.setRoom(e);
        ResidentRepo.save(f1);

        // Room 4
        Room g = new Room(0001, "123456789", "Phan Trọng Cường", "0911052881");
        g.generateKey();
        RoomRepo.save(g);

        Resident h = new Resident("005602000683", "Phan Trọng Cường", "Male", "31/01/2003", "Hà Nội", "Student", "0344712997", "Chủ");
        g.addResident(h);
        h.setRoom(g);
        ResidentRepo.save(h);

        Resident h1 = new Resident("005602000685", "Người Thương Của Cường", "Female", "01/31/2003", "Hà Nội", "Student", "0344712990", "Vợ");
        g.addResident(h1);
        h1.setRoom(g);
        ResidentRepo.save(h1);

        // Room 5
        Room i = new Room(0005, "123456784", "Nguyễn Xuân Phúc", "0911052885");
        i.generateKey();
        RoomRepo.save(i);

        Resident j = new Resident("002305000329", "Nguyễn Xuân Phúc", "Male", "25/11/2003", "Hà Nội", "Student", "03467597002", "Chủ");
        i.addResident(j);
        j.setRoom(i);
        ResidentRepo.save(j);

        // Các cư dân khác trong Room 5
        Resident k = new Resident("002305000321", "Hồng Nhật", "Female", "11/25/2003", "Hà Nội", "Student", "03467597003", "Vợ cả");
        i.addResident(k);
        k.setRoom(i);
        ResidentRepo.save(k);

        Resident l = new Resident("002305000322", "Minh Quang", "Female", "12/11/2003", "Hà Nội", "Student", "03467597004", "Vợ đáy xã hội");
        i.addResident(l);
        l.setRoom(i);
        ResidentRepo.save(l);

        Resident m = new Resident("002305000323", "Việt Anh", "Female", "14/11/2003", "Hà Nội", "Student", "03467597005", "Vợ hai");
        i.addResident(m);
        m.setRoom(i);
        ResidentRepo.save(m);
    }
}
