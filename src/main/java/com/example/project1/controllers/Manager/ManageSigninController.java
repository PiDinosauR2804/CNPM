package com.example.project1.controllers.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.project1.Repository.AccountManagerRepository;
import com.example.project1.Repository.ResidentHistoryRepository;
import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.AccountManager;
import com.example.project1.entity.ResidentHistory;
import com.example.project1.entity.Resident;
import com.example.project1.entity.Room;
import com.example.project1.service.serviceRoom;

@Controller
public class ManageSigninController {
    @Autowired
    private RoomRepository RoomRepo;
    @Autowired
    private ResidentRepository ResidentRepo;
    @Autowired
    private ResidentHistoryRepository ResidentHistoryRepo;
    @Autowired
    private AccountManagerRepository AccountManagerRepo;
    @Autowired
    private serviceRoom service;
    
    public String username_public;

    @GetMapping("/signin")
    public String index(Model model, HttpServletRequest request)  {
        model.addAttribute("AccountManager", new AccountManager());
        return "manager/account/signin";
    }

    @GetMapping("/manager/signin/check")
    public String check(@ModelAttribute AccountManager account, Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        AccountManager account_N = new AccountManager();
        java.util.List<AccountManager> listAccount = AccountManagerRepo.findAll();
        boolean flag = false;
        for (AccountManager acc : listAccount) {
            if (acc.getUsername().equals(account.getUsername())) {
                if (acc.getPassword().equals(account.getPassword())) {
                    account_N = acc;
                    flag = true;
                }
            }
        }
        if (flag) {
            username_public = account_N.getUsername();
            Cookie cookie = new Cookie("username", account_N.getUsername());
            cookie.setPath("/");
            response.addCookie(cookie);
            model.addAttribute("username", account_N.getUsername());
            redirectAttributes.addFlashAttribute("username", account_N.getUsername());
            return "redirect:/greeting";
        } else {
            String message = "Thông tin tên đăng nhập hoặc mật khẩu sai";
            model.addAttribute("AccountManager", new AccountManager());
            model.addAttribute("message", message);
            redirectAttributes.addFlashAttribute("AccountManager", new AccountManager());
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/signin";
        }
    }

    @GetMapping("/signout")
    public String signout(HttpServletResponse response)  {
        Cookie cookie = new Cookie("username", null); 
        cookie.setMaxAge(0); 
        cookie.setPath("/");
        response.addCookie(cookie); 
        return "redirect:/greeting";
    }

}