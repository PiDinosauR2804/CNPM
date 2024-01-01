package com.example.project1.controllers.Manager;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.project1.Repository.AddResidentRequestRepository;
import com.example.project1.Repository.RequestRepository;
import com.example.project1.Repository.AbsentResidentRepository;
import com.example.project1.entity.AddResidentRequest;
import com.example.project1.entity.Request;
import com.example.project1.entity.AbsentResident;
import com.example.project1.service.serviceAbsent;

@Controller
public class ManagerAbsentResident {
	@Autowired
	private AbsentResidentRepository repo;
	@Autowired
	private serviceAbsent service;

	@Autowired
	private RequestRepository RequestRepo;

	@Autowired
	private AddResidentRequestRepository AddResidentRequestRepo;

	// Xem tất cả bảng tạm trú, tìm kiếm được theo tên hoặc id, hoặc tìm kiếm được
	// cả trong 1 khoảng tg
	@GetMapping("/manager/absent_resident/index")
	public String index(Model model,
			@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam(defaultValue = "1") Integer pageNo) {
		java.util.List<Request> listRequest1 = RequestRepo.findAll();
		java.util.List<AddResidentRequest> listRequest2 = AddResidentRequestRepo.findAll();
		int num1 = 0;
		for (Request req : listRequest1) {
			if (req.getApproved() == 1) {num1 ++;}
		}
		int num2 = 0;
		for (AddResidentRequest Addreq : listRequest2) {
			if (Addreq.getApproved() == 1) {num2 ++;}
		}
		int numNoti = num1 + num2;
		model.addAttribute("numNoti", numNoti);
		Page<AbsentResident> listAbsentResident = this.service.listAll(keyword, startDate, endDate, pageNo);
		model.addAttribute("keyword", keyword);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("totalPage", listAbsentResident.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("listAbsentResident", listAbsentResident);
		return "manager/absent_resident/index";
	}

	// Thêm người vào tạm trú
	@GetMapping("/manager/absent_resident/create")
	public String create(Model model) {
		java.util.List<Request> listRequest1 = RequestRepo.findAll();
		java.util.List<AddResidentRequest> listRequest2 = AddResidentRequestRepo.findAll();
		int num1 = 0;
		for (Request req : listRequest1) {
			if (req.getApproved() == 1) {num1 ++;}
		}
		int num2 = 0;
		for (AddResidentRequest Addreq : listRequest2) {
			if (Addreq.getApproved() == 1) {num2 ++;}
		}
		int numNoti = num1 + num2;
		model.addAttribute("numNoti", numNoti);
		model.addAttribute("AbsentResident", new AbsentResident());
		return "manager/absent_resident/create";
	}

	@PostMapping("/manager/absent_resident/create")
	public String save(AbsentResident temp) {
		AbsentResident save = repo.save(temp);
		repo.save(save);
		return "redirect:/manager/absent_resident/index";
	}

}
