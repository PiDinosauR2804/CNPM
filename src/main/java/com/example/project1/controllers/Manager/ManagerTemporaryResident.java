package com.example.project1.controllers.Manager;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project1.Repository.TemporaryResidentRepository;
import com.example.project1.entity.TemporaryResident;
import com.example.project1.service.serviceTemp;

@Controller
public class ManagerTemporaryResident {
	@Autowired
	private TemporaryResidentRepository repo;
	@Autowired
	private serviceTemp service;
	//Xem tất cả bảng tạm trú, tìm kiếm được theo tên hoặc id, hoặc tìm kiếm được cả trong 1 khoảng tg
	@GetMapping ("/manager/temporary_resident/index")
	public String index(Model model,
	                   @RequestParam(required = false) String keyword,
	                   @RequestParam(required = false) String startDate,
	                   @RequestParam(required = false) String endDate,
	                   @RequestParam(defaultValue ="1") Integer pageNo) {
	    Page <TemporaryResident> listTemporaryResident = this.service.listAll(keyword, startDate, endDate, pageNo);
	    model.addAttribute("keyword",keyword);
	    model.addAttribute("startDate",startDate);
	    model.addAttribute("endDate",endDate);
	    model.addAttribute("totalPage", listTemporaryResident.getTotalPages());
	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("listTemporaryResident", listTemporaryResident);
	    //System.out.println("List Temporary Resident: " + listTemporaryResident.getContent());
	    return "manager/temporary_resident/index";
	}

    //Thêm người vào tạm trú
	@GetMapping("/manager/temporary_resident/create")
    public String create(Model model) {
        model.addAttribute("TemporaryResident", new TemporaryResident());
        return "manager/temporary_resident/create";
    }

    @PostMapping("/manager/temporary_resident/save")
    public String save(TemporaryResident temp) {
    	TemporaryResident save = repo.save(temp);
        repo.save(save);
        return "redirect:/manager/temporary_resident/index";
    }

}