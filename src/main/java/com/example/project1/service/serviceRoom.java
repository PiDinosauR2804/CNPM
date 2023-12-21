package com.example.project1.service;

import java.util.List;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@Service
public class serviceRoom {
	@Autowired
	private RoomRepository repo;
	
	//Tìm kiếm theo String keyword
	 public Page<Room> listAll(String keyword,Integer pageNo){
		Pageable pageable = PageRequest.of(pageNo - 1, 2);//2 là size page
		if(keyword !=null) {
			return repo.findAll(keyword,pageable);
		}
		return this.repo.findAll(pageable);
	};
}
