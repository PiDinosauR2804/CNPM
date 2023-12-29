package com.example.project1.service;

import com.example.project1.Repository.TypeDonationRepository;
import com.example.project1.entity.TypeDonation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class serviceTypeFee {
	@Autowired
	private TypeDonationRepository repo;
	
	//Tìm kiếm theo String keyword
	 public Page<TypeDonation> listAll(String keyword,Integer pageNo){
		Pageable pageable = PageRequest.of(pageNo - 1, 10);//2 là size page
		if(keyword !=null) {
			return repo.findAll(keyword,pageable);
		}
		return this.repo.findAll(pageable);
	};
}