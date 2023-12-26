package com.example.project1.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.Repository.AbsentResidentRepository;
import com.example.project1.Repository.DonationFeeHistoryRepository;
import com.example.project1.Repository.DonationFeeRepository;
import com.example.project1.Repository.MandatoryFeeHistoryRepository;
import com.example.project1.Repository.MandatoryFeeRepository;
import com.example.project1.Repository.ResidentHistoryRepository;
import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomHistoryRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.Repository.TemporaryResidentRepository;
import com.example.project1.entity.DonationFee;
import com.example.project1.entity.MandatoryFee;
import com.example.project1.entity.Resident;
import com.example.project1.entity.Room;
import com.example.project1.entity.RoomHistory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class RoomDataSeeder implements CommandLineRunner {
    @Autowired
	RoomRepository RoomRepo;
    @Autowired
	RoomHistoryRepository RoomHisotryRepo;

    @Autowired
    ResidentRepository ResidentRepo;
    @Autowired
    ResidentHistoryRepository ResidentHistoryRepo;

    @Autowired
    MandatoryFeeRepository MandatoryFeeRepo;
    @Autowired
    MandatoryFeeHistoryRepository MandatoryFeeHistoryRepo;

    @Autowired
    DonationFeeRepository DonationFeeRepo;
    @Autowired
    DonationFeeHistoryRepository DonationFeeHistoryRepo;

    @Autowired
    TemporaryResidentRepository TemporaryResidentRepo;

    @Autowired
    AbsentResidentRepository AbsentResidentRepo;

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
        ResidentHistoryRepo.truncateTable();

        MandatoryFeeRepo.truncateTable();
        MandatoryFeeHistoryRepo.truncateTable();

        DonationFeeHistoryRepo.truncateTable();
        DonationFeeRepo.truncateTable();

        TemporaryResidentRepo.truncateTable();
        AbsentResidentRepo.truncateTable();

        RoomHisotryRepo.truncateTable();
		RoomRepo.truncateTable();
        enableForeignKeyChecks();
    }

    private void loadUserData() {
        Truncate();
        // Room 1
        Room a = new Room(0001, "123456783", "Ngô Đình Luyện", "0911052884", 1000000, 200000);
        a.generateKey();
        RoomRepo.save(a);

        Resident b = new Resident("001203000768", "Ngô Đình Luyện", "Male", "28/04/2003", "Hà Nội", "Student", "0911052885", "Chủ");
        a.addResident(b);
        b.setRoom(a);
        ResidentRepo.save(b);

        MandatoryFee a_fee = new MandatoryFee(12, 2023, 1000000, 1000000);
        a.addMandatoryFee(a_fee);
        a_fee.setRoom(a);
        RoomRepo.save(a);
        MandatoryFeeRepo.save(a_fee);

        DonationFee a_dfee = new DonationFee(12, 2023, 1, 111110);
        a.addDonationFee(a_dfee);
        a_dfee.setRoom(a);
        RoomRepo.save(a);
        DonationFeeRepo.save(a_dfee);

        // Room 2
        Room c = new Room(0002, "123456781", "Hoàng Đức Huy", "0911052882", 1000000, 200000);
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

        MandatoryFee b_fee = new MandatoryFee(12, 2023, 12345678, 1546328);
        c.addMandatoryFee(b_fee);
        b_fee.setRoom(c);
        RoomRepo.save(c);
        MandatoryFeeRepo.save(b_fee);

        DonationFee b_dfee = new DonationFee(12, 2023, 1, 44555669);
        c.addDonationFee(b_dfee);
        b_dfee.setRoom(c);
        RoomRepo.save(c);
        DonationFeeRepo.save(b_dfee);

        // Room 3
        Room e = new Room(0003, "123456782", "Lê Đình Trí Tuệ", "0911052883", 1000000, 200000);
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

        MandatoryFee c_fee = new MandatoryFee(12, 2023, 32165478, 1536974);
        e.addMandatoryFee(c_fee);
        c_fee.setRoom(e);
        RoomRepo.save(e);
        MandatoryFeeRepo.save(c_fee);

        DonationFee c_dfee = new DonationFee(12, 2023, 1, 5555550);
        e.addDonationFee(c_dfee);
        c_dfee.setRoom(e);
        RoomRepo.save(e);
        DonationFeeRepo.save(c_dfee);

        // Room 4
        Room g = new Room(0004, "123456789", "Phan Trọng Cường", "0911052881", 1000000, 200000);
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

        MandatoryFee d_fee = new MandatoryFee(12, 2023, 5555555, 9999999);
        g.addMandatoryFee(d_fee);
        d_fee.setRoom(g);
        RoomRepo.save(g);
        MandatoryFeeRepo.save(d_fee);

        DonationFee d_dfee = new DonationFee(12, 2023, 1, 36458788);
        g.addDonationFee(d_dfee);
        d_dfee.setRoom(g);
        RoomRepo.save(g);
        DonationFeeRepo.save(d_dfee);

        // Room 5
        Room i = new Room(0005, "123456784", "Nguyễn Xuân Phúc", "0911052885", 1000000, 200000);
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

        MandatoryFee e_fee = new MandatoryFee(12, 2023, 36544444, 89878888);
        i.addMandatoryFee(e_fee);
        e_fee.setRoom(i);
        RoomRepo.save(i);
        MandatoryFeeRepo.save(e_fee);

        DonationFee e_dfee = new DonationFee(12, 2023, 1, 23333330);
        i.addDonationFee(e_dfee);
        e_dfee.setRoom(i);
        RoomRepo.save(i);
        DonationFeeRepo.save(e_dfee);        
    }
}
