package com.example.project1.controllers.Manager;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.project1.Repository.AddResidentRequestRepository;
import com.example.project1.Repository.DonationFeeRepository;
import com.example.project1.Repository.MandatoryFeeRepository;
import com.example.project1.Repository.RequestRepository;
import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.Repository.TypeDonationRepository;
import com.example.project1.entity.DonationFee;
import com.example.project1.entity.MandatoryFee;
import com.example.project1.entity.Request;
import com.example.project1.entity.Resident;
import com.example.project1.entity.Room;
import com.example.project1.entity.TypeDonation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String change_info_index(Model model) {
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
                        rooms.get(0).setIdOwner(a.getContentChanged());
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
                        resident.setId(a.getContentChanged());
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
                            DonationFeeRepo.save(donationFee);
                            TypeDonationRepo.save(typeDonation);
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
    
}
