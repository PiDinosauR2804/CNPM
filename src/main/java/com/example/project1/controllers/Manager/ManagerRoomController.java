package com.example.project1.controllers.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.Room;
import com.example.project1.service.serviceRoom;


@Controller
public class ManagerRoomController {
    @Autowired
    private RoomRepository RoomRepo;
    @Autowired
    private serviceRoom service;
    
    @GetMapping("/manager/index")
    //tìm kiếm theo keyword là 1 string
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword,
    		@RequestParam(name = "pageNo", defaultValue ="1") Integer pageNo) {
        Page <Room> listRoom = this.service.listAll(keyword,pageNo);
        model.addAttribute("totalPage",listRoom.getTotalPages());
        model.addAttribute("currentPage",pageNo);
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
    
}
