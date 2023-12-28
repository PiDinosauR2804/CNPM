package com.example.project1.controllers.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.DonationFee;
import com.example.project1.entity.MandatoryFee;
import com.example.project1.entity.Resident;
import com.example.project1.entity.Room;



@Controller
public class UserRoomController {
    @Autowired
    private RoomRepository RoomRepo;

    @GetMapping("/user/search")
    public String searchRoom(@RequestParam(name = "key", required = false, defaultValue = "") String key, Model model) {
        model.addAttribute("key", key);
        return "user/search";
    }
    

    @GetMapping("/user/index/")
    public String index(@RequestParam("key") String key, Model model) {
        List<Room> rooms = RoomRepo.findByKey(key);
        if (!rooms.isEmpty()) {
            Room room = rooms.get(0);
            model.addAttribute("room", room);
            List<Resident> listResident = room.getResidents();
            model.addAttribute("listResident", listResident);
            List<MandatoryFee> listFees = room.getMandatoryFees();
            model.addAttribute("listFees", listFees);
            List<DonationFee> listDonationFees = room.getDonationFees();
            model.addAttribute("listDonationFees", listDonationFees);
        }
        return "user/Room/index";
    }

    @GetMapping("/user/index/{key}")
    public String index1(@PathVariable("key") String key, Model model) {
        List<Room> rooms = RoomRepo.findByKey(key);
        if (!rooms.isEmpty()) {
            Room room = rooms.get(0);
            model.addAttribute("room", room);
            List<Resident> listResident = room.getResidents();
            model.addAttribute("listResident", listResident);
            List<MandatoryFee> listFees = room.getMandatoryFees();
            model.addAttribute("listFees", listFees);
            List<DonationFee> listDonationFees = room.getDonationFees();
            model.addAttribute("listDonationFees", listDonationFees);
        }
        return "user/Room/index";
    }
    
    
}
