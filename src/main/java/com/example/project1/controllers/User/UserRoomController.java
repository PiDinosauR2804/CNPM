package com.example.project1.controllers.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.Resident;
import com.example.project1.entity.Room;



@Controller
public class UserRoomController {
    @Autowired
    private RoomRepository RoomRepo;
    @Autowired
    private ResidentRepository ResidentRepo;

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
        }
        return "user/Room/index";
    }

    @GetMapping("/user")
    public String show(Model model) {
        java.util.List<Resident> listResident = ResidentRepo.findAll();
        model.addAttribute("listResident", listResident);
        return "user/index";
    }

    
    
}
