package com.example.project1.controllers.Manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.project1.Repository.DonationFeeRepository;
import com.example.project1.Repository.MandatoryFeeRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.DonationFee;
import com.example.project1.entity.MandatoryFee;
import com.example.project1.entity.Room;

@Controller
public class ManagerFeeController {
    @Autowired
    MandatoryFeeRepository MandatoryFeeRepo;

    @Autowired
    RoomRepository RoomRepo;

    @Autowired
    DonationFeeRepository DonationFeeRepo;

    // Mandatory Fee

    @GetMapping("/manager/fee/index")
    public String index(Model model) {
        List<MandatoryFee> listFees = MandatoryFeeRepo.findAll();
        model.addAttribute("listFees", listFees);
        return "manager/fee/index";
    }

    @GetMapping("/manager/fee/createAll")
    public String createAll(Model model) {
        List<Room> rooms = RoomRepo.findAll();
        model.addAttribute("rooms", rooms);
        List<MandatoryFee> listFees = new ArrayList<MandatoryFee>();
        for (int i = 0; i < rooms.size(); i++) {
            MandatoryFee mandatoryFee = new MandatoryFee();
            listFees.add(mandatoryFee);
        }
        model.addAttribute("listFees", listFees);
        return "manager/fee/createAll";
    }
    
    
    @GetMapping("/manager/room/{roomNumber}/fees")
    public String detail(@PathVariable int roomNumber, Model model) {
        model.addAttribute("roomNumber", roomNumber);
        List<MandatoryFee> mandatoryFees = MandatoryFeeRepo.findByRoom(roomNumber);
        model.addAttribute("listFees", mandatoryFees);
        return "manager/fee/feesRoom";
    }

    @GetMapping("/manager/fee/{no}/fees")
    public String edit(@PathVariable int no, Model model) {
        model.addAttribute("no", no);
        List<MandatoryFee> fees = MandatoryFeeRepo.findByPK(no);
        model.addAttribute("fee", fees.get(0));
        return "manager/fee/edit";
    }


    @PostMapping("/manager/fee/save")
    public String save(@ModelAttribute("fee")MandatoryFee fee) {
        MandatoryFeeRepo.save(fee);
        return "redirect:/manager/fee/index";
    }

    @GetMapping("/manager/fee/update")
    public String update() {
        List<MandatoryFee> fees = MandatoryFeeRepo.findIfFeeComplete();
        for (int i = 0; i < fees.size(); i++) {
            MandatoryFeeRepo.delete(fees.get(i));
        }
        return "redirect:/manager/fee/index";
    }

    // Donation Feee
    @GetMapping("/manager/fee/donation/index")
    public String donation_index(Model model) {
        List<DonationFee> listFees = DonationFeeRepo.findAll();
        model.addAttribute("listFees", listFees);
        return "manager/fee/donation/index";
    }
}
