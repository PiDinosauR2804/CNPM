package com.example.project1.service;

import com.example.project1.Repository.ResidentRepository;
import com.example.project1.entity.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class serviceResident {
	@Autowired
	private ResidentRepository repo;

	//Tìm kiếm theo String keyword
	 public Page<Resident> listAll(String keyword,Integer pageNo){
		Pageable pageable = PageRequest.of(pageNo - 1, 10);//2 là size page
		if(keyword !=null) {
			return repo.findAll(keyword, pageable);
		}
		return this.repo.findAll(pageable);
	};
	//Tách lấy number
	 public String extractDisplayValue(String numberRoom) {
	        int indexOfSpace = numberRoom.indexOf(" ");
	        return (indexOfSpace != -1) ? numberRoom.substring(0, indexOfSpace) : numberRoom;
	 }
}