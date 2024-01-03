package com.example.project1.controllers.Manager;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.project1.Repository.AddResidentRequestRepository;
import com.example.project1.Repository.RequestRepository;
import com.example.project1.Repository.AddResidentRequestRepository;
import com.example.project1.Repository.DonationFeeRepository;
import com.example.project1.Repository.MandatoryFeeRepository;
import com.example.project1.Repository.RequestRepository;
import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.Repository.TypeDonationRepository;
import com.example.project1.entity.AddResidentRequest;
import com.example.project1.entity.Request;
import com.example.project1.entity.AddResidentRequest;
import com.example.project1.entity.DonationFee;
import com.example.project1.entity.MandatoryFee;
import com.example.project1.entity.Request;
import com.example.project1.entity.Resident;
import com.example.project1.entity.Room;
import com.example.project1.entity.TypeDonation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class ManagerRequestController {
    @Autowired
    RequestRepository RequestRepo;

    @Autowired
    RoomRepository RoomRepo;

    @Autowired
    ResidentRepository ResidentRepo;

    @Autowired
    MandatoryFeeRepository MandatoryFeeRepo;

    @Autowired
    DonationFeeRepository DonationFeeRepo;

    @Autowired
    TypeDonationRepository TypeDonationRepo;

    @Autowired
    AddResidentRequestRepository AddResidentRequestRepo;

    @GetMapping("/manager/request/change_information/index")
    public String change_info_index(Model model, HttpServletRequest request) {
        boolean flag1 = false;
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
					flag1 = true;
                }
            }
        }
		if (!flag1) return "404";
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
        List<Request> requests = RequestRepo.findRequestByApproved(1);
        model.addAttribute("requests", requests);
        return "manager/request/change_information/index";
    }

    @GetMapping("/manager/request/change_information/accept/{no}")
    public String change_info_accept(@PathVariable int no) {
        List<Request> requests = RequestRepo.findByNo(no);
        if (!requests.isEmpty()) {
            Request a = requests.get(0);
            a.setApproved(2);
            if (a.getIdRequest() < 6) {
                List<Room> rooms = RoomRepo.findByRoom(a.getNoRoom());
                if (!rooms.isEmpty()) {
                    Room room = rooms.get(0);
                    if (a.getIdRequest() == 1) {
                        room.setIdOwner(a.getContentChanged());
                    } else if (a.getIdRequest() == 2) {
                        room.setNameOwner(a.getContentChanged());
                    } else if (a.getIdRequest() == 3) {
                        room.setNumberPhoneOwner(a.getContentChanged());
                    } else if (a.getIdRequest() == 4) {
                        int aa = Integer.parseInt(a.getContentChanged());
                        room.setDefaultFeeRoom(aa);
                    } else if (a.getIdRequest() == 5) {
                        int aa = Integer.parseInt(a.getContentChanged());
                        room.setDefaultParkingFee(aa);
                    }
                    RoomRepo.save(room);
                }
            } else if (a.getIdRequest() < 13) {
                List<Resident> residents = ResidentRepo.findByIdResident(a.getObjectId());
                if (!residents.isEmpty()) {
                    Resident resident = residents.get(0);
                    if (a.getIdRequest() == 6) {
                        resident.setIdRes(a.getContentChanged());
                    } else if (a.getIdRequest() == 7) {
                        resident.setName(a.getContentChanged());
                    } else if (a.getIdRequest() == 8) {
                        resident.setPhoneNumber(a.getContentChanged());
                    } else if (a.getIdRequest() == 9) {
                        resident.setGender(a.getContentChanged());
                    } else if (a.getIdRequest() == 10) {
                        resident.setBirthPlace(a.getContentChanged());
                    } else if (a.getIdRequest() == 11) {
                        resident.setJob(a.getContentChanged());
                    } else if (a.getIdRequest() == 12) {
                        resident.setRelationshipWithOwner(a.getContentChanged());
                    }
                    ResidentRepo.save(resident);
                }
            } else if (a.getIdRequest() < 17) {
                List<Room> rooms = RoomRepo.findByRoom(a.getNoRoom());
                if (!rooms.isEmpty()) {
                    Room room = rooms.get(0);
                    List<MandatoryFee> mandatoryFees = MandatoryFeeRepo.findByRoom(room.getNoRoom());
                    if (!mandatoryFees.isEmpty()) {
                        MandatoryFee mandatoryFee = mandatoryFees.get(0);
                        int amount = Integer.parseInt(a.getContentChanged());
                        if (a.getIdRequest() == 13) {
                            mandatoryFee.setRoomFeePaid(amount);
                        } else if (a.getIdRequest() == 14) {
                            mandatoryFee.setRoomFeePaid(amount);
                        } else if (a.getIdRequest() == 15) {
                            mandatoryFee.setElectricFeePaid(amount);
                        } else if (a.getIdRequest() == 16) {
                            mandatoryFee.setParkingFeePaid(amount);
                        }
                        MandatoryFeeRepo.save(mandatoryFee);
                    }
                }
            } else if (a.getIdRequest() > 20) {
                List<Room> rooms = RoomRepo.findByRoom(a.getNoRoom());
                if (!rooms.isEmpty()) {
                    Room room = rooms.get(0);
                    List<DonationFee> donationFees = DonationFeeRepo.findByRoomAndByType(room.getNoRoom(), a.getDonationName());
                    if (!donationFees.isEmpty()) {
                        DonationFee donationFee = donationFees.get(0);
                        int amount = Integer.parseInt(a.getContentChanged());
                        donationFee.setAmount(amount);
                        DonationFeeRepo.save(donationFee);
                    } else {
                        LocalDate currentDate = LocalDate.now();
                        int amount = Integer.parseInt(a.getContentChanged());
                        int currentMonth = currentDate.getMonthValue();
                        int currentYear = currentDate.getYear();
                        DonationFee donationFee = new DonationFee(currentMonth, currentYear, amount);
                        List<TypeDonation> typeDonations = TypeDonationRepo.findByName(a.getDonationName());
                        if (!typeDonations.isEmpty()) {
                            TypeDonation typeDonation = typeDonations.get(0);
                            donationFee.setTypeDonation(typeDonation);
                            typeDonation.addDonationFee(donationFee);
                            donationFee.setRoom(room);
                            room.addDonationFee(donationFee);
                            DonationFeeRepo.save(donationFee);
                            TypeDonationRepo.save(typeDonation);
                            RoomRepo.save(room);
                        }
                    }
                }
            }
            RequestRepo.save(a);
        }
        return "redirect:/manager/request/change_information/index";
    }

    @GetMapping("/manager/request/change_information/decline/{no}")
    public String change_info_decline(@PathVariable int no) {
        List<Request> requests = RequestRepo.findByNo(no);
        if (!requests.isEmpty()) {
            Request a = requests.get(0);
            a.setApproved(0);
            RequestRepo.save(a);
        }
        return "redirect:/manager/request/change_information/index";
    }
    
    @GetMapping("/manager/request/change_information/history")
    public String change_info_history(Model model, HttpServletRequest request) {
        boolean flag1 = false;
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
					flag1 = true;
                }
            }
        }
		if (!flag1) return "404";
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
        List<Request> requests = RequestRepo.findHistoryRequest();
        model.addAttribute("requests", requests);
        return "manager/request/change_information/history";
    }

    @GetMapping("/manager/request/add_resident/index")
    public String add_resident_index(Model model, HttpServletRequest request) {
        boolean flag1 = false;
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
					flag1 = true;
                }
            }
        }
		if (!flag1) return "404";
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
        List<AddResidentRequest> requests = AddResidentRequestRepo.findRequestByApproved(1);
        model.addAttribute("requests", requests);
        return "manager/request/add_resident/index";
    }

    @GetMapping("/manager/request/add_resident/accept/{no}")
    public String add_resident_accept(@PathVariable int no) {
        List<AddResidentRequest> requests = AddResidentRequestRepo.findByNo(no);
        if (!requests.isEmpty()) {
            AddResidentRequest request = requests.get(0);
            request.setApproved(2);
            AddResidentRequestRepo.save(request);
            List<Room> rooms = RoomRepo.findByRoom(request.getNoRoom());
            if (!rooms.isEmpty()) {
                Room room = rooms.get(0);
                Resident new_resident = new Resident(request.getId(), request.getName(), request.getGender(), request.getBirthDate(), request.getBirthPlace(), request.getJob(), request.getPhoneNumber(), request.getRelationshipWithOwner());
                new_resident.setRoom(room);
                room.addResident(new_resident);
                RoomRepo.save(room);
                ResidentRepo.save(new_resident);
            }
        }
        return "redirect:/manager/request/add_resident/index";
    }

    @GetMapping("/manager/request/add_resident/decline/{no}")
    public String add_resident_decline(@PathVariable int no) {
        List<AddResidentRequest> requests = AddResidentRequestRepo.findByNo(no);
        if (!requests.isEmpty()) {
            AddResidentRequest request = requests.get(0);
            request.setApproved(0);
            AddResidentRequestRepo.save(request);
        }
        return "redirect:/manager/request/add_resident/index";
    }

    @GetMapping("/manager/request/add_resident/history")
    public String add_resident_history(Model model, HttpServletRequest request) {
        boolean flag1 = false;
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
					flag1 = true;
                }
            }
        }
		if (!flag1) return "404";
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
        List<AddResidentRequest> requests = AddResidentRequestRepo.findHistoryRequest();
        model.addAttribute("requests", requests);
        return "manager/request/add_resident/history";
    }
}
