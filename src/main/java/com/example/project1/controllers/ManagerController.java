package com.example.project1.controllers;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.Room;


@Controller
public class ManagerController {
    @Autowired
    private RoomRepository RoomRepo;

    @GetMapping("/manager/index")
    public String index(Model model) {
        java.util.List<Room> listRoom = RoomRepo.findAll();
        model.addAttribute("listRoom", listRoom);
        return "manager/index";
    }
    
    @GetMapping("/manager/createRoom")
    public String create(Model model) {
        model.addAttribute("room", new Room());
        return "manager/create";
    }

    @PostMapping("/manager/save")
    public String save(Room room) {
        Room save = RoomRepo.save(room);
        save.generateKey();
        RoomRepo.save(save);
        return "redirect:/manager/index";
    }
}
