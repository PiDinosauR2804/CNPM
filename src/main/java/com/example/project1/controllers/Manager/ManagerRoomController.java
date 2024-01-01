package com.example.project1.controllers.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.project1.Repository.AddResidentRequestRepository;
import com.example.project1.Repository.RequestRepository;
import com.example.project1.Repository.RoomHistoryRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.AddResidentRequest;
import com.example.project1.entity.Request;
import com.example.project1.entity.DonationFee;
import com.example.project1.entity.DonationFeeHistory;
import com.example.project1.entity.MandatoryFee;
import com.example.project1.entity.MandatoryFeeHistory;
import com.example.project1.entity.Resident;
import com.example.project1.entity.ResidentHistory;
import com.example.project1.entity.Room;
import com.example.project1.entity.RoomHistory;
import com.example.project1.service.serviceHistoryRoom;
import com.example.project1.service.serviceRoom;


@Controller
public class ManagerRoomController {
    @Autowired
    private RoomRepository RoomRepo;
    @Autowired
    private RoomHistoryRepository RoomHistoryRepo;

    @Autowired
    private RequestRepository RequestRepo;

    @Autowired
    private AddResidentRequestRepository AddResidentRequestRepo;

    @Autowired
    private serviceRoom service;
    @Autowired
	private serviceHistoryRoom serviceHR;

    @Autowired
    private ManagerFeeController feeController;
    @Autowired
    private ManagerResidentController residentController;
    
    @GetMapping("/manager/index")
    //tìm kiếm theo keyword là 1 string
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword,
    		@RequestParam(name = "pageNo", defaultValue ="1") Integer pageNo) {
        Page <Room> listRoom = this.service.listAll(keyword,pageNo);
        java.util.List<Request> listRequest1 = RequestRepo.findAll();
		java.util.List<AddResidentRequest> listRequest2 = AddResidentRequestRepo.findAll();
		int num1 = 0;
		for (Request req : listRequest1) {
			if (req.getApproved() == 1) {num1 ++;}
		}
		int num2 = 0;
		for (AddResidentRequest Addreq : listRequest2) {
			if (Addreq.getApproved() == 1) {num2 ++;}
		}
		int numNoti = num1 + num2;
		model.addAttribute("numNoti", numNoti);
        model.addAttribute("totalPage",listRoom.getTotalPages());
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("listRoom", listRoom);
        return "manager/index";
    }

    // public String index1(Model model) {
    //     java.util.List<Room> listRoom = RoomRepo.findAll();
    //     model.addAttribute("listRoom", listRoom);
    //     return "manager/index";
    // }
    
    @GetMapping("/manager/createRoom")
    public String create(Model model) {
        java.util.List<Request> listRequest1 = RequestRepo.findAll();
		java.util.List<AddResidentRequest> listRequest2 = AddResidentRequestRepo.findAll();
		int num1 = 0;
		for (Request req : listRequest1) {
			if (req.getApproved() == 1) {num1 ++;}
		}
		int num2 = 0;
		for (AddResidentRequest Addreq : listRequest2) {
			if (Addreq.getApproved() == 1) {num2 ++;}
		}
		int numNoti = num1 + num2;
		model.addAttribute("numNoti", numNoti);
        model.addAttribute("room", new Room());
        return "manager/room/create";
    }

    @GetMapping("/manager/room/edit/{key}")
    public String edit(@PathVariable String key, Model model) {
        java.util.List<Request> listRequest1 = RequestRepo.findAll();
		java.util.List<AddResidentRequest> listRequest2 = AddResidentRequestRepo.findAll();
		int num1 = 0;
		for (Request req : listRequest1) {
			if (req.getApproved() == 1) {num1 ++;}
		}
		int num2 = 0;
		for (AddResidentRequest Addreq : listRequest2) {
			if (Addreq.getApproved() == 1) {num2 ++;}
		}
		int numNoti = num1 + num2;
		model.addAttribute("numNoti", numNoti);
        Room room = RoomRepo.findByKey(key).get(0);
        model.addAttribute("room", room);
        model.addAttribute("key", key);
        return "manager/room/edit";
    }

    @PostMapping("/manager/room/update/{key}")
    public String update(@PathVariable String key, @ModelAttribute Room room, Model model) {
        Room a = RoomRepo.findByKey(key).get(0);
        a.setNoRoom(room.getNoRoom());
        a.setNumberPhoneOwner(room.getNumberPhoneOwner());
        a.setNameOwner(room.getNameOwner());
        a.setIdOwner(room.getIdOwner());
        a.setDefaultParkingFee(room.getDefaultParkingFee());
        a.setDefaultFeeRoom(room.getDefaultFeeRoom());
        int noRoom = a.getNoRoom();
        RoomRepo.save(a);
        java.util.List<Request> listRequest1 = RequestRepo.findAll();
		java.util.List<AddResidentRequest> listRequest2 = AddResidentRequestRepo.findAll();
		int num1 = 0;
		for (Request req : listRequest1) {
			if (req.getApproved() == 1) {num1 ++;}
		}
		int num2 = 0;
		for (AddResidentRequest Addreq : listRequest2) {
			if (Addreq.getApproved() == 1) {num2 ++;}
		}
		int numNoti = num1 + num2;
		model.addAttribute("numNoti", numNoti);
        model.addAttribute("noRoom", noRoom);
        model.addAttribute("listResidentRoom", a.getResidents());
        model.addAttribute("listRoom", RoomRepo.findByKey(key));
        return "manager/room/residentRoom";
    }

    @PostMapping("/manager/save")
    public String save(Room room) {
        Room save = RoomRepo.save(room);
        save.generateKey();
        RoomRepo.save(save);

        saveRoomInHistory(save);

        return "redirect:/manager/index";
    }

    @GetMapping("/manager/room/delete/{noRoom}")
    public String delete(@PathVariable String noRoom, Model model) {
        int roomNumber = Integer.parseInt(noRoom);
        Room a = RoomRepo.findByRoom(roomNumber).get(0);
        closeRoom(a);
        return "redirect:/manager/index";
    }
    // Room History

    @GetMapping("/manager/history/room/index")
    public String his_index(Model model,
	                   @RequestParam(required = false) String keyword,
	                   @RequestParam(required = false) String startDate,
	                   @RequestParam(required = false) String endDate,
	                   @RequestParam(defaultValue ="1") Integer pageNo) {
	    Page<RoomHistory> listRoomHistory = this.serviceHR.listAll(keyword, startDate, endDate, pageNo);
	    model.addAttribute("keyword",keyword);
	    model.addAttribute("startDate",startDate);
	    model.addAttribute("endDate",endDate);
	    model.addAttribute("totalPage", listRoomHistory.getTotalPages());
	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("listRoomHistory", listRoomHistory);
        java.util.List<Request> listRequest1 = RequestRepo.findAll();
		java.util.List<AddResidentRequest> listRequest2 = AddResidentRequestRepo.findAll();
		int num1 = 0;
		for (Request req : listRequest1) {
			if (req.getApproved() == 1) {num1 ++;}
		}
		int num2 = 0;
		for (AddResidentRequest Addreq : listRequest2) {
			if (Addreq.getApproved() == 1) {num2 ++;}
		}
		int numNoti = num1 + num2;
		model.addAttribute("numNoti", numNoti);
	    return "manager/room/his_index";
	}

    @GetMapping("/manager/history/room/detail/{key}")
    public String his_detail(@PathVariable String key ,Model model) {
        RoomHistory room = RoomHistoryRepo.findByKey(key).get(0);
        List<ResidentHistory> residents = room.getResidents();
        model.addAttribute("room", room);
        model.addAttribute("residents", residents); 
        java.util.List<Request> listRequest1 = RequestRepo.findAll();
		java.util.List<AddResidentRequest> listRequest2 = AddResidentRequestRepo.findAll();
		int num1 = 0;
		for (Request req : listRequest1) {
			if (req.getApproved() == 1) {num1 ++;}
		}
		int num2 = 0;
		for (AddResidentRequest Addreq : listRequest2) {
			if (Addreq.getApproved() == 1) {num2 ++;}
		}
		int numNoti = num1 + num2;
		model.addAttribute("numNoti", numNoti);
        return "manager/room/his_index";
    }

    public void saveRoomInHistory(Room room) {
        RoomHistory roomHis = new RoomHistory(room.getKey(), room.getNoRoom(), room.getIdOwner(), room.getNameOwner(), room.getNumberPhoneOwner(), room.getDefaultFeeRoom(), room.getDefaultParkingFee(), getTime());
        RoomHistoryRepo.save(roomHis);
    }

    public void closeRoom(Room room) {
        List<Resident> residents = room.getResidents();
        List<MandatoryFee> mandatoryFees = room.getMandatoryFees();
        List<DonationFee> donationFees = room.getDonationFees();
        for (int i = 0; i < donationFees.size(); i++) {
            feeController.eraseDonationFee(donationFees.get(i));
        }
        for (int i = 0; i < mandatoryFees.size(); i++) {
            feeController.eraseMandatoryFee(mandatoryFees.get(i));
        }
        for (int i = 0; i < residents.size(); i++) {
            residentController.eraseResident(residents.get(i));
        }
        updateCloseTimeInRoomHistory(room);
        RoomRepo.delete(room);
        return;
    }

    public void updateCloseTimeInRoomHistory(Room room) {
        RoomHistory a = RoomHistoryRepo.findByKey(room.getKey()).get(0);
        a.setdayOut(getTime());
        RoomHistoryRepo.save(a);
    }

    public String getTime() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String curDate_string = currentDate.format(dateFormatter);
        return curDate_string;
    }

    @GetMapping("/manager/history/room/full_detail/{key}")
    public String history_detail_room(@PathVariable("key") String key, Model model) {
        List<RoomHistory> rooms = RoomHistoryRepo.findByKey(key);
        java.util.List<Request> listRequest1 = RequestRepo.findAll();
		java.util.List<AddResidentRequest> listRequest2 = AddResidentRequestRepo.findAll();
		int num1 = 0;
		for (Request req : listRequest1) {
			if (req.getApproved() == 1) {num1 ++;}
		}
		int num2 = 0;
		for (AddResidentRequest Addreq : listRequest2) {
			if (Addreq.getApproved() == 1) {num2 ++;}
		}
		int numNoti = num1 + num2;
		model.addAttribute("numNoti", numNoti);
        if (!rooms.isEmpty()) {
            RoomHistory room = rooms.get(0);
            model.addAttribute("room", room);
            List<ResidentHistory> listResident = room.getResidents();
            model.addAttribute("listResident", listResident);
            List<MandatoryFeeHistory> listFees = room.getMandatoryFees();
            model.addAttribute("listFees", listFees);
            List<DonationFeeHistory> listDonationFees = room.getDonationFees();
            model.addAttribute("listDonationFees", listDonationFees);
            return "manager/room/history_detail";
        } else {
            return "404";
        }
    }    
}
