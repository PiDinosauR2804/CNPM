package com.example.project1.seeder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import com.example.project1.Repository.TypeDonationRepository;
import com.example.project1.controllers.Manager.ManagerFeeController;
import com.example.project1.controllers.Manager.ManagerResidentController;
import com.example.project1.controllers.Manager.ManagerRoomController;
import com.example.project1.entity.DonationFee;
import com.example.project1.entity.MandatoryFee;
import com.example.project1.entity.Resident;
import com.example.project1.entity.Room;
import com.example.project1.entity.RoomHistory;
import com.example.project1.entity.TypeDonation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class RoomDataSeeder implements CommandLineRunner {
    @Autowired
	RoomRepository RoomRepo;
    @Autowired
	RoomHistoryRepository RoomHistoryRepo;

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

    @Autowired
    TypeDonationRepository TypeDonationRepo;

    @Autowired
    private ManagerFeeController feeController;
    @Autowired
    private ManagerResidentController residentController;
    @Autowired
    private ManagerRoomController managerRoomController;

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

        RoomHistoryRepo.truncateTable();
		RoomRepo.truncateTable();

        TypeDonationRepo.truncateTable();
        enableForeignKeyChecks();
    }

    public String getTime() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String curDate_string = currentDate.format(dateFormatter);
        return curDate_string;
    }

    public void setTypeDonation() {
        TypeDonation a = new TypeDonation("Tiền khuyến học");
        TypeDonationRepo.save(a);
        TypeDonation b = new TypeDonation("Tiền thiên tai");
        TypeDonationRepo.save(b);
    }

    private void loadUserData() {
        Truncate();
        TypeDonation a_type = new TypeDonation("Tiền khuyến học");
        TypeDonationRepo.save(a_type);
        TypeDonation b_type = new TypeDonation("Tiền thiên tai");
        TypeDonationRepo.save(b_type);
        // Room 1
        Room a = new Room(0001, "123456783", "Ngô Đình Luyện", "0911052884", 1000000, 200000);
        a.generateKey();
        RoomRepo.save(a);

        RoomHistory roomHis = new RoomHistory(a.getKey(), a.getNoRoom(), a.getIdOwner(), a.getNameOwner(), a.getNumberPhoneOwner(), a.getDefaultFeeRoom(), a.getDefaultParkingFee(), getTime());
        RoomHistoryRepo.save(roomHis);

        Resident b = new Resident("001203000768", "Ngô Đình Luyện", "Male", "28/04/2003", "Hà Nội", "Student", "0911052885", "Chủ");
        a.addResident(b);
        b.setRoom(a);
        residentController.saveResidentInHistory(b);
        ResidentRepo.save(b);

        MandatoryFee a_fee = new MandatoryFee(12, 2023, 1000000, 1000000);
        a.addMandatoryFee(a_fee);
        a_fee.setRoom(a);
        RoomRepo.save(a);
        MandatoryFeeRepo.save(a_fee);

        
        DonationFee a_dfee = new DonationFee(12, 2023, 100000);
        a.addDonationFee(a_dfee);
        a_dfee.setRoom(a);
        a_dfee.setType_donation(a_type);
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
    }
}
