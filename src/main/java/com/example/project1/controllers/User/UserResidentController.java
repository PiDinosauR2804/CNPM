package com.example.project1.controllers.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.Room;

import ch.qos.logback.core.model.Model;


@Controller
public class UserResidentController {
    @Autowired
    private RoomRepository RoomRepo;
    @Autowired
    private ResidentRepository ResidentRepo;


    @GetMapping("/user/index")
    public String index(Model model, String key) {
        return "User/index";
    }
    
}
