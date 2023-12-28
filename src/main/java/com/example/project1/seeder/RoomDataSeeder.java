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
    private ManagerRoomController roomController;

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
        a_dfee.setTypeDonation(a_type);
        RoomRepo.save(a);
        DonationFeeRepo.save(a_dfee);

        // Room 1 History

        Room room1 = new Room(0002, "234567891", "Nguyễn Văn A", "0912345678", 1200000, 250000);
        room1.generateKey();
        RoomRepo.save(room1);

        RoomHistory roomHis1 = new RoomHistory(room1.getKey(), room1.getNoRoom(), room1.getIdOwner(), room1.getNameOwner(), room1.getNumberPhoneOwner(), room1.getDefaultFeeRoom(), room1.getDefaultParkingFee(), getTime());
        RoomHistoryRepo.save(roomHis1);

        Resident resident1 = new Resident("001203000769", "Nguyễn Văn A", "Male", "20/05/1995", "Hồ Chí Minh", "Professional", "0912345679", "Chủ");
        room1.addResident(resident1);
        resident1.setRoom(room1);
        residentController.saveResidentInHistory(resident1);
        ResidentRepo.save(resident1);

        MandatoryFee fee1 = new MandatoryFee(11, 2023, 1200000, 1200000);
        room1.addMandatoryFee(fee1);
        fee1.setElectricFeePaid(1200000);
        fee1.setParkingFeePaid(room1.getDefaultParkingFee());
        fee1.setRoomFeePaid(room1.getDefaultFeeRoom());
        fee1.setWaterFeePaid(1200000);

        fee1.setRoom(room1);
        RoomRepo.save(room1);
        MandatoryFeeRepo.save(fee1);

        DonationFee dfee1 = new DonationFee(11, 2023, 80000);
        room1.addDonationFee(dfee1);
        dfee1.setRoom(room1);
        dfee1.setTypeDonation(a_type);  // Chắc chắn biến a_type đã được khởi tạo
        RoomRepo.save(room1);
        DonationFeeRepo.save(dfee1);

        roomController.closeRoom(room1);

        // Room 2 History

        Room room2 = new Room(0003, "345678912", "Trần Thị B", "0913456789", 1100000, 220000);
        room2.generateKey();
        RoomRepo.save(room2);

        RoomHistory roomHis2 = new RoomHistory(room2.getKey(), room2.getNoRoom(), room2.getIdOwner(), room2.getNameOwner(), room2.getNumberPhoneOwner(), room2.getDefaultFeeRoom(), room2.getDefaultParkingFee(), getTime());
        RoomHistoryRepo.save(roomHis2);

        Resident resident2 = new Resident("001203000770", "Trần Thị B", "Female", "15/08/1990", "Đà Nẵng", "Employee", "0913456790", "Chủ");
        room2.addResident(resident2);
        resident2.setRoom(room2);
        residentController.saveResidentInHistory(resident2);
        ResidentRepo.save(resident2);

        MandatoryFee fee2 = new MandatoryFee(10, 2023, 1100000, 1100000);
        room2.addMandatoryFee(fee2);
        fee2.setElectricFeePaid(1100000);
        fee2.setParkingFeePaid(room2.getDefaultParkingFee());
        fee2.setRoomFeePaid(room2.getDefaultFeeRoom());
        fee2.setWaterFeePaid(1100000);
        fee2.setRoom(room2);
        RoomRepo.save(room2);
        MandatoryFeeRepo.save(fee2);

        DonationFee dfee2 = new DonationFee(10, 2023, 90000);
        room2.addDonationFee(dfee2);
        dfee2.setRoom(room2);
        dfee2.setTypeDonation(a_type);
        RoomRepo.save(room2);
        DonationFeeRepo.save(dfee2);

        roomController.closeRoom(room2);

        // Room 3 History

        Room room3 = new Room(0004, "456789123", "Lê Văn C", "0914567890", 1300000, 270000);
        room3.generateKey();
        RoomRepo.save(room3);

        RoomHistory roomHis3 = new RoomHistory(room3.getKey(), room3.getNoRoom(), room3.getIdOwner(), room3.getNameOwner(), room3.getNumberPhoneOwner(), room3.getDefaultFeeRoom(), room3.getDefaultParkingFee(), getTime());
        RoomHistoryRepo.save(roomHis3);

        Resident resident3 = new Resident("001203000771", "Lê Văn C", "Male", "03/12/1985", "Nha Trang", "Businessman", "0914567891", "Chủ");
        room3.addResident(resident3);
        resident3.setRoom(room3);
        residentController.saveResidentInHistory(resident3);
        ResidentRepo.save(resident3);

        MandatoryFee fee3 = new MandatoryFee(9, 2023, 1300000, 1300000);
        room3.addMandatoryFee(fee3);
        fee3.setElectricFeePaid(1300000);
        fee3.setParkingFeePaid(room3.getDefaultParkingFee());
        fee3.setRoomFeePaid(room3.getDefaultFeeRoom());
        fee3.setWaterFeePaid(1300000);
        fee3.setRoom(room3);
        RoomRepo.save(room3);
        MandatoryFeeRepo.save(fee3);

        DonationFee dfee3 = new DonationFee(9, 2023, 95000);
        room3.addDonationFee(dfee3);
        dfee3.setRoom(room3);
        dfee3.setTypeDonation(a_type);
        RoomRepo.save(room3);
        DonationFeeRepo.save(dfee3);

        roomController.closeRoom(room3);

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
