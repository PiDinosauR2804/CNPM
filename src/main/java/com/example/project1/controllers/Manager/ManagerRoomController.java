package com.example.project1.controllers.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import com.example.project1.Repository.ResidentHistoryRepository;
import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.ResidentHistory;
import com.example.project1.entity.Resident;
import com.example.project1.entity.Room;
import com.example.project1.service.serviceRoom;


@Controller
public class ManagerRoomController {
    @Autowired
    private RoomRepository RoomRepo;
    @Autowired
    private ResidentRepository ResidentRepo;
    @Autowired
    private ResidentHistoryRepository ResidentHistoryRepo;
    @Autowired
    private serviceRoom service;

    @GetMapping("/manager/index")
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword,
    		@RequestParam(name = "pageNo", defaultValue ="1") Integer pageNo) {
        Page <Room> listRoom = this.service.listAll(keyword,pageNo);
        model.addAttribute("totalPage",listRoom.getTotalPages());
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("listRoom", listRoom);
        return "manager/index";
    }

    public String index1(Model model) {
        java.util.List<Room> listRoom = RoomRepo.findAll();
        model.addAttribute("listRoom", listRoom);
        return "manager/index";
    }
    
    @GetMapping("/manager/createRoom")
    public String create(Model model) {
        model.addAttribute("room", new Room());
        return "manager/room/create";
    }

    @PostMapping("/manager/save")
    public String save(Room room) {
        Room save = RoomRepo.save(room);
        save.generateKey();
        RoomRepo.save(save);
        return "redirect:/manager/index";
    }
    
    @GetMapping("/manager/changeInfo/{key}")
    public String changeInfo(@PathVariable String key, Model model) {
        java.util.List<Resident> listResident = ResidentRepo.findAll();
        Resident resident = new Resident();
        boolean flag = false;
        for (Resident res : listResident) {
            if (res.getId().equals(key)) {
                resident = res;
                flag = true;
                break;
            }
        }
        if (!flag) {
            return "Error/error";
        }
        model.addAttribute("Resident", resident);
        return "manager/resident/changeResidentinfo";
    }

    @PostMapping("/manager/changeInfo/{key}/save")
    public String save(@PathVariable String key, Resident resident) {
        ResidentRepo.updateResident(key, resident.getName(),
                resident.getGender(),
                resident.getBirthDate(),
                resident.getBirthPlace(),
                resident.getJob(),
                resident.getPhoneNumber(),
                resident.getRelationshipWithOwner());
        return "redirect:/manager/changeInfo/{key}";
    }

}
