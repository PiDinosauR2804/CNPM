package com.example.project1.controllers.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.project1.Repository.AddResidentRequestRepository;
import com.example.project1.Repository.RequestRepository;
import com.example.project1.entity.AddResidentRequest;
import com.example.project1.entity.Request;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserRequestController {
    @Autowired
    RequestRepository RequestRepo;

    @Autowired
    AddResidentRequestRepository AddResidentRequestRepo;

    @GetMapping("/user/request/{key}/add_resident")
    public String requestAddResident(@PathVariable String key ,Model model) {
        AddResidentRequest resident = new AddResidentRequest();
        model.addAttribute("key", key);
        model.addAttribute("resident", resident);
        return "user/Request/add_resident";
    }

    @PostMapping("/user/request/{key}/save_resident")
    public String requestSaveResident(@PathVariable String key, AddResidentRequest request) {
        AddResidentRequestRepo.save(request);
        return "redirect:/user/index/{key}";
    }

    @GetMapping("/user/request/{key}/room_info")
    public String changeRoomInfo(@PathVariable String key, Model model) {
        Request request = new Request();
        model.addAttribute("request", request);
        return "user/Request/room_info";
    }

    @PostMapping("/user/request/{key}/room_info/save")
    public String saveRoomInfo(@PathVariable String key, Request request) {
        RequestRepo.save(request);
        return "redirect:/user/index/{key}";
    }

    @GetMapping("/user/request/{key}/resident_info")
    public String changeResidentInfo(@PathVariable String key, Model model) {
        Request request = new Request();
        model.addAttribute("request", request);
        return "user/Request/resident_info";
    }

    @PostMapping("/user/request/{key}/resident_info/save")
    public String saveResidentInfo(@PathVariable String key, Request request) {
        RequestRepo.save(request);
        return "redirect:/user/index/{key}";
    }

    @GetMapping("/user/request/{key}/update_fee")
    public String updateFee(@PathVariable String key, Model model) {
        Request request = new Request();
        model.addAttribute("request", request);
        return "user/Request/update_fee";
    }

    @PostMapping("/user/request/{key}/update_fee/save")
    public String sa(@PathVariable String key, Request request) {
        RequestRepo.save(request);
        return "redirect:/user/index/{key}";
    }
    
}
