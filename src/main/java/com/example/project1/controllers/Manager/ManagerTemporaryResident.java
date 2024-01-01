package com.example.project1.controllers.Manager;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.example.project1.Repository.AddResidentRequestRepository;
import com.example.project1.Repository.RequestRepository;
import com.example.project1.Repository.TemporaryResidentRepository;
import com.example.project1.entity.AddResidentRequest;
import com.example.project1.entity.Request;
import com.example.project1.entity.TemporaryResident;
import com.example.project1.service.serviceTemp;

@Controller
public class ManagerTemporaryResident {
	@Autowired
	private TemporaryResidentRepository repo;
	@Autowired
	private serviceTemp service;

	@Autowired
	private RequestRepository RequestRepo;

	@Autowired
	private AddResidentRequestRepository AddResidentRequestRepo;
	//Xem tất cả bảng tạm trú, tìm kiếm được theo tên hoặc id, hoặc tìm kiếm được cả trong 1 khoảng tg
	@GetMapping ("/manager/temporary_resident/index")
	public String index(Model model,
	                   @RequestParam(name = "keyword", required = false) String keyword,
	                   @RequestParam(name = "startDate", required = false) String startDate,
	                   @RequestParam(name = "endDate", required = false) String endDate,
	                   @RequestParam(name = "pageNo", defaultValue ="1") Integer pageNo) {
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
	    Page <TemporaryResident> listTemporaryResident = this.service.listAll(keyword, startDate, endDate, pageNo);

	    model.addAttribute("totalPage", listTemporaryResident.getTotalPages());
	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("listTemporaryResident", listTemporaryResident);
	    //System.out.println("List Temporary Resident: " + listTemporaryResident.getContent());
	    return "manager/temporary_resident/index";
	}

    //Thêm người vào tạm trú
	@GetMapping("/manager/temporary_resident/create")
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