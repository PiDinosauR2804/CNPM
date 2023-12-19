package com.example.project1.controllers.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;


@Controller
public class UserRoomController {
    @GetMapping("/user/search")
    public String searchRoom(Model model) {
        return "user/search";
    }

    @GetMapping("/user/index")
    public String index(String key) {

        return "user/index";
    }
    
    
}
