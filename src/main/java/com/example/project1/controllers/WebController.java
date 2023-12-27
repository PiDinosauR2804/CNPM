package com.example.project1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class WebController {
    @GetMapping("/greeting")
	public String greeting(Model model, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    model.addAttribute("username", cookie.getValue());
                }
            }
        }
		return "greeting";
	}
	
    @GetMapping("/landing")
	public String landing(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "landing";
	}

	public String SayHello() {
        return "Hiiiii";
    }
}
