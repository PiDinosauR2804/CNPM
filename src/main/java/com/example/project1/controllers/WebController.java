package com.example.project1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    @GetMapping("/greeting")
	public String greeting(@RequestParam(required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
    @GetMapping("/landing")
	public String landing(@RequestParam(required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "landing";
	}

	public String SayHello() {
        return "Hiiiii";
    }
}
