package com.example.project1.controllers.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.project1.Repository.AddResidentRequestRepository;
import com.example.project1.Repository.RequestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ManagerRequestController {
    @Autowired
    RequestRepository RequestRepo;

    @Autowired
    AddResidentRequestRepository AddResidentRequestRepo;

    @GetMapping("/manager/request/change_information/index")
    public String change_info_index(Model model) {
        return "manager/request/change_information/index";
    }
    
}
