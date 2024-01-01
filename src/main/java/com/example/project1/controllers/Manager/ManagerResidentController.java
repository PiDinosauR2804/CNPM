package com.example.project1.controllers.Manager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project1.Repository.AddResidentRequestRepository;
import com.example.project1.Repository.RequestRepository;
import com.example.project1.Repository.ResidentHistoryRepository;
import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomHistoryRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.AddResidentRequest;
import com.example.project1.entity.Request;
import com.example.project1.entity.Resident;
import com.example.project1.entity.ResidentHistory;
import com.example.project1.entity.Room;
import com.example.project1.entity.RoomHistory;
import com.example.project1.service.serviceHistoryResident;
import com.example.project1.service.serviceResident;

@Controller
public class ManagerResidentController {
    @Autowired
    private ResidentRepository residentRepo;
    @Autowired
    private ResidentHistoryRepository residentHistoryRepo;

    @Autowired
    private RoomRepository roomRepo;
    @Autowired
    private RoomHistoryRepository roomHistoryRepo;

    @Autowired
    private serviceResident service;
    @Autowired
    private serviceHistoryResident serviceHR;

    @Autowired
	private RequestRepository RequestRepo;

	@Autowired
	private AddResidentRequestRepository AddResidentRequestRepo;

    public int roomNumber = 0;

    @GetMapping ("/manager/resident/index")
    public String index(Model model, @RequestParam(required = false) String keyword,
    		@RequestParam(defaultValue ="1") Integer pageNo) {
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
        Page <Resident> listResident = this.service.listAll(keyword,pageNo);
        model.addAttribute("keyword",keyword);
        model.addAttribute("totalPage",listResident.getTotalPages());
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("listResident", listResident);
        return "manager/resident/index";
    }

    @GetMapping("/manager/resident/create_in_index")
    public String addResident(Model model) {
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
    	java.util.List<Room> rooms = roomRepo.findAll();
    	model.addAttribute("rooms", rooms);
    	return "manager/resident/create_in_index";
    }

    @PostMapping("/manager/resident/redirect/create")
    public String redirectToCreateResidentInRoom(@RequestParam("numberRoom") String numberRoom) {
    	String displayValue = service.extractDisplayValue(numberRoom);
    	try {
            Integer.parseInt(numberRoom);
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            return "redirect:/manager/resident/findNumberRoom?error=invalidInput";
        }
         return "redirect:/manager/room/"+displayValue+"/addresident";
    }    

    @GetMapping("/manager/resident/edit/{id}")
    public String changeInfo(@PathVariable String id, Model model) {
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
        java.util.List<Resident> listResident = residentRepo.findAll();
        Resident resident = new Resident();
        boolean flag = false;
        for (Resident res : listResident) {
            if (res.getIdRes().equals(id)) {
                resident = res;
                flag = true;
                break;
            }
        }
        if (!flag) {
            return "404";
        }
        model.addAttribute("Resident", resident);
        return "manager/resident/edit";
    }

    @PostMapping("/manager/changeInfo/{id}/save")
    public String save(@PathVariable String id, Resident resident) {
        residentRepo.updateResident(id, resident.getName(),
                resident.getGender(),
                resident.getBirthDate(),
                resident.getBirthPlace(),
                resident.getJob(),
                resident.getPhoneNumber(),
                resident.getRelationshipWithOwner());
        return "redirect:/manager/resident/edit/{id}";
    }

    @GetMapping("/manager/room/{noRoom}")
    public String viewRoomDetails(@PathVariable String noRoom, Model model) {
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
        roomNumber = Integer.parseInt(noRoom);
        java.util.List<Resident> listResidentRoom = residentRepo.findByRoom(roomNumber);
        java.util.List<Room> listRoom = roomRepo.findByRoom(roomNumber);
        model.addAttribute("noRoom", noRoom);
        model.addAttribute("listResidentRoom", listResidentRoom);
        model.addAttribute("listRoom", listRoom);
        return "manager/room/residentRoom";
    }

    @GetMapping("/manager/room/{roomNumber}/addresident")
    public String viewRoomDetails(@PathVariable int roomNumber, Model model) {
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
        model.addAttribute("roomNumber", roomNumber);
        model.addAttribute("Resident", new Resident());
        return "manager/resident/create";
    }

    @PostMapping("/manager/room/{roomNumber}/addresident/save")
    public String save(@PathVariable int roomNumber, Resident resident) {
        java.util.List<Room> room = roomRepo.findByRoom(roomNumber);
        if (!room.isEmpty()) {
            resident.setRoom(room.get(0));
            room.get(0).addResident(resident);

            saveResidentInHistory(resident);

            residentRepo.save(resident);
            roomRepo.save(room.get(0));
        }
        return "redirect:/manager/room/{roomNumber}";
    }

    // Resident History

    @GetMapping ("/manager/history/resident/index")
    public String his_ndex(Model model,
  	                   @RequestParam(required = false) String keyword,
  	                   @RequestParam(required = false) String startDate,
  	                   @RequestParam(required = false) String endDate,
  	                   @RequestParam(defaultValue ="1") Integer pageNo) {
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
  	    Page<ResidentHistory> listResidentHistory = this.serviceHR.listAll(keyword, startDate, endDate, pageNo);
  	    model.addAttribute("keyword",keyword);
  	    model.addAttribute("startDate",startDate);
  	    model.addAttribute("endDate",endDate);
  	    model.addAttribute("totalPage", listResidentHistory.getTotalPages());
  	    model.addAttribute("currentPage", pageNo);
  	    model.addAttribute("listResidentHistory", listResidentHistory);
  	    return "manager/resident/his_index";
  	}

    public void saveResidentInHistory(Resident resident) {
        ResidentHistory resi = new ResidentHistory(resident.getIdRes(), resident.getName(), resident.getGender(), resident.getBirthDate(), resident.getBirthPlace(), resident.getJob(), resident.getPhoneNumber(), resident.getRelationshipWithOwner(), getTime());
        RoomHistory a = roomHistoryRepo.findByKey(resident.getkey()).get(0);
        resi.setRoom(a);
        a.addResident(resi);
        residentHistoryRepo.save(resi);
        roomHistoryRepo.save(a);
    }

    public String getTime() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String curDate_string = currentDate.format(dateFormatter);
        return curDate_string;
    }
    
    public void updateCloseTimeInResidentHistory(Resident resi) {
        java.util.List<ResidentHistory> a = residentHistoryRepo.findByNo(resi.getNo());
        if (!a.isEmpty()) {
            a.get(0).setDayOut(getTime());
            residentHistoryRepo.save(a.get(0));
        }
    }

    public void eraseResident(Resident resi) {
        updateCloseTimeInResidentHistory(resi);
        residentRepo.delete(resi);
    }
}
