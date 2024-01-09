package com.example.project1.seeder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.Repository.AccountManagerRepository;
import com.example.project1.Repository.AddResidentRequestRepository;
import com.example.project1.Repository.AbsentResidentRepository;
import com.example.project1.Repository.DonationFeeHistoryRepository;
import com.example.project1.Repository.DonationFeeRepository;
import com.example.project1.Repository.MandatoryFeeHistoryRepository;
import com.example.project1.Repository.MandatoryFeeRepository;
import com.example.project1.Repository.RequestRepository;
import com.example.project1.Repository.ResidentHistoryRepository;
import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomHistoryRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.Repository.TemporaryResidentRepository;
import com.example.project1.Repository.TypeDonationRepository;
import com.example.project1.controllers.Manager.ManagerFeeController;
import com.example.project1.controllers.Manager.ManagerResidentController;
import com.example.project1.controllers.Manager.ManagerRoomController;
import com.example.project1.entity.AbsentResident;
import com.example.project1.entity.AccountManager;
import com.example.project1.entity.AddResidentRequest;
import com.example.project1.entity.DonationFee;
import com.example.project1.entity.MandatoryFee;
import com.example.project1.entity.Request;
import com.example.project1.entity.Resident;
import com.example.project1.entity.Room;
import com.example.project1.entity.AccountManager;
import com.example.project1.entity.RoomHistory;
import com.example.project1.entity.TemporaryResident;
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
    AccountManagerRepository AccountManagerRepo;

    @Autowired
    private ManagerFeeController feeController;
    @Autowired
    private ManagerResidentController residentController;
    @Autowired
    private ManagerRoomController roomController;

    @Autowired
    private RequestRepository RequestRepo;

    @Autowired
    private AddResidentRequestRepository AddResidentRequestRepo;

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
        RequestRepo.truncateTable();
        AddResidentRequestRepo.truncateTable();

        AccountManagerRepo.truncateTable();
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
        a_fee.setElectricFeePaid(1000000);
        a_fee.setParkingFeePaid(200000);
        a_fee.setRoomFeePaid(1000000);
        a_fee.setWaterFeePaid(1000000);
        a.addMandatoryFee(a_fee);
        a_fee.setRoom(a);
        RoomRepo.save(a);
        MandatoryFeeRepo.save(a_fee);
        
        DonationFee a_dfee = new DonationFee(12, 2023, 100000);
        a.addDonationFee(a_dfee);
        a_dfee.setRoom(a);
        a_dfee.setTypeDonation(a_type);
        a_type.addDonationFee(a_dfee);
        RoomRepo.save(a);
        TypeDonationRepo.save(a_type);
        DonationFeeRepo.save(a_dfee);

        Request re_a = new Request(1, 1, "012334598");
        RequestRepo.save(re_a);

        Request re_b = new Request(4, 6, "123456789");
        re_b.setObjectId("001203000768");
        re_b.setObjectName(ResidentRepo.findByIdResident(re_b.getObjectId()).get(0).getName());
        RequestRepo.save(re_b);

        AddResidentRequest add_a = new AddResidentRequest("123123123", 1, "Manh" , "Male", "28-11-2023", "Ha Noi", "Pho", "0922374112", "Bo");
        AddResidentRequestRepo.save(add_a);
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

        // Room 3 Hítory

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
        // Room 4
        Room room4 = new Room(0004, "765432109", "Lê Văn D", "0965432109", 1100000, 210000);
        room4.generateKey();
        RoomRepo.save(room4);

        // RoomHistory 4
        RoomHistory roomHis4 = new RoomHistory(room4.getKey(), room4.getNoRoom(), room4.getIdOwner(), room4.getNameOwner(), room4.getNumberPhoneOwner(), room4.getDefaultFeeRoom(), room4.getDefaultParkingFee(), getTime());
        RoomHistoryRepo.save(roomHis4);

        // Resident 4
        Resident resident4 = new Resident("001203000771", "Lê Văn D", "Male", "10/10/1980", "Huế", "Engineer", "0965432110", "Chủ");
        room4.addResident(resident4);
        resident4.setRoom(room4);
        residentController.saveResidentInHistory(resident4);
        ResidentRepo.save(resident4);

        Resident resident41 = new Resident("001203000888", "Hoàng Đức Huy", "Male", "10/10/1999", "Huế", "Engineer", "0965432110", "Con");
        room4.addResident(resident41);
        resident41.setRoom(room4);
        residentController.saveResidentInHistory(resident41);
        ResidentRepo.save(resident4);

        // MandatoryFee 4
        MandatoryFee a_fee4 = new MandatoryFee(12, 2023, 800000, 800000);
        a_fee4.setElectricFeePaid(800000);
        a_fee4.setParkingFeePaid(210000);
        a_fee4.setWaterFeePaid(800000);
        a_fee4.setRoomFeePaid(1100000);
        room4.addMandatoryFee(a_fee4);
        a_fee4.setRoom(room4);
        RoomRepo.save(room4);
        MandatoryFeeRepo.save(a_fee4);

        // DonationFee 4
        DonationFee a_dfee4 = new DonationFee(6, 2023, 30000);
        room4.addDonationFee(a_dfee4);
        a_dfee4.setRoom(room4);
        a_dfee4.setTypeDonation(b_type);
        b_type.addDonationFee(a_dfee4);
        RoomRepo.save(room4);
        TypeDonationRepo.save(b_type);
        DonationFeeRepo.save(a_dfee4);

        // Request 4
        Request re_a4 = new Request(4, 4, "0123456789");
        RequestRepo.save(re_a4);

        // AddResidentRequest 4
        AddResidentRequest add_a4 = new AddResidentRequest("456456456", 4, "Linh" , "Female", "15-05-2023", "Quảng Ninh", "Ngách", "0977333444", "Em");
        AddResidentRequestRepo.save(add_a4);


        // Room 5
        Room room5 = new Room(0005, "654321098", "Phạm Thị E", "0954321098", 1300000, 230000);
        room5.generateKey();
        RoomRepo.save(room5);

        // RoomHistory 5
        RoomHistory roomHis5 = new RoomHistory(room5.getKey(), room5.getNoRoom(), room5.getIdOwner(), room5.getNameOwner(), room5.getNumberPhoneOwner(), room5.getDefaultFeeRoom(), room5.getDefaultParkingFee(), getTime());
        RoomHistoryRepo.save(roomHis5);

        // Resident 5
        Resident resident5 = new Resident("001203000772", "Phạm Thị E", "Female", "02/07/1992", "Đồng Tháp", "Nurse", "0954321099", "Chủ");
        room5.addResident(resident5);
        resident5.setRoom(room5);
        residentController.saveResidentInHistory(resident5);
        ResidentRepo.save(resident5);

        // MandatoryFee 5
        MandatoryFee a_fee5 = new MandatoryFee(12, 2023, 700000, 700000);
        a_fee5.setElectricFeePaid(700000);
        a_fee5.setParkingFeePaid(230000);
        a_fee5.setWaterFeePaid(700000);
        room5.addMandatoryFee(a_fee5);
        a_fee5.setRoom(room5);
        RoomRepo.save(room5);
        MandatoryFeeRepo.save(a_fee5);

        // DonationFee 5
        DonationFee a_dfee5 = new DonationFee(4, 2023, 20000);
        room5.addDonationFee(a_dfee5);
        a_dfee5.setRoom(room5);
        a_dfee5.setTypeDonation(b_type);
        b_type.addDonationFee(a_dfee5);
        RoomRepo.save(room5);
        TypeDonationRepo.save(b_type);
        DonationFeeRepo.save(a_dfee5);

        // Request 5
        Request re_a5 = new Request(5, 5, "0123456789");
        RequestRepo.save(re_a5);

        // AddResidentRequest 5
        AddResidentRequest add_a5 = new AddResidentRequest("567567567", 5, "Quang" , "Male", "10-09-2023", "Bình Dương", "Hẻm", "0988444555", "Anh");
        AddResidentRequestRepo.save(add_a5);

        // TemporaryResident
        TemporaryResident temporaryResident = new TemporaryResident("Phan Trọng Cường", "01203000341", "4", "21090", "2023-01-01", "2023-02-01");
        TemporaryResidentRepo.save(temporaryResident);

        TemporaryResident temporaryResident1 = new TemporaryResident("Phan Trọng Lan", "01203089212", "5", "10980", "2023-01-01", "2023-02-01");
        TemporaryResidentRepo.save(temporaryResident1);

        // AbsentResident
        AbsentResident absentResident = new AbsentResident("Nguyễn Văn A", "0123456789", "5", "10980", "2023-03-01", "2023-03-10");
        AbsentResidentRepo.save(absentResident);

        AbsentResident absentResident1 = new AbsentResident("Ngô Văn Hải", "001204888361", "4", "21090", "2023-03-01", "2023-03-10");
        AbsentResidentRepo.save(absentResident1);

        // Room 6
        Room room6 = new Room(0006, "987654321", "Trần Văn F", "0987654321", 1400000, 240000);
        room6.generateKey();
        RoomRepo.save(room6);

        // RoomHistory 6
        RoomHistory roomHis6 = new RoomHistory(room6.getKey(), room6.getNoRoom(), room6.getIdOwner(), room6.getNameOwner(), room6.getNumberPhoneOwner(), room6.getDefaultFeeRoom(), room6.getDefaultParkingFee(), getTime());
        RoomHistoryRepo.save(roomHis6);

        // Resident 6
        Resident resident6 = new Resident("001203000773", "Trần Văn F", "Male", "15/09/1985", "Bình Dương", "Engineer", "0987654322", "Chủ");
        room6.addResident(resident6);
        resident6.setRoom(room6);
        residentController.saveResidentInHistory(resident6);
        ResidentRepo.save(resident6);

        Resident resident61 = new Resident("001203000831", "Trần Văn Z", "Female", "15/09/2003", "Hà Nội", "IT", "0987654366", "Con trai");
        room6.addResident(resident61);
        resident61.setRoom(room6);
        residentController.saveResidentInHistory(resident61);
        ResidentRepo.save(resident61);

        // MandatoryFee 6
        MandatoryFee a_fee6 = new MandatoryFee(12, 2023, 800000, 800000);
        a_fee6.setElectricFeePaid(800000);
        a_fee6.setParkingFeePaid(240000);
        a_fee6.setWaterFeePaid(800000);
        a_fee6.setRoomFeePaid(1400000);
        room6.addMandatoryFee(a_fee6);
        a_fee6.setRoom(room6);
        RoomRepo.save(room6);
        MandatoryFeeRepo.save(a_fee6);

        // Room 7
        Room room7 = new Room(0007, "112233445", "Lê Thị H", "0987654321", 1500000, 250000);
        room7.generateKey();
        RoomRepo.save(room7);

        // RoomHistory 7
        RoomHistory roomHis7 = new RoomHistory(room7.getKey(), room7.getNoRoom(), room7.getIdOwner(), room7.getNameOwner(), room7.getNumberPhoneOwner(), room7.getDefaultFeeRoom(), room7.getDefaultParkingFee(), getTime());
        RoomHistoryRepo.save(roomHis7);

        // Resident 7
        Resident resident7 = new Resident("001203000775", "Lê Thị H", "Female", "05/12/1995", "Hồ Chí Minh", "Accountant", "0987654322", "Chủ");
        room7.addResident(resident7);
        resident7.setRoom(room7);
        residentController.saveResidentInHistory(resident7);
        ResidentRepo.save(resident7);

        // MandatoryFee 7
        MandatoryFee a_fee7 = new MandatoryFee(12, 2023, 900000, 900000);
        a_fee7.setElectricFeePaid(900000);
        a_fee7.setParkingFeePaid(250000);
        a_fee7.setWaterFeePaid(900000);
        a_fee7.setRoomFeePaid(1500000);
        room7.addMandatoryFee(a_fee7);
        a_fee7.setRoom(room7);
        RoomRepo.save(room7);
        MandatoryFeeRepo.save(a_fee7);



        // Account 1
        AccountManager account1 = new AccountManager("password", "admin");
        AccountManagerRepo.save(account1);

        // Account 2
        AccountManager account2 = new AccountManager("accc2hihotuee", "32165478");
        AccountManagerRepo.save(account2);
    }
}
