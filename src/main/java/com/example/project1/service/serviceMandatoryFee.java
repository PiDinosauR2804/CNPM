package com.example.project1.service;

<<<<<<< HEAD
import java.util.List;
=======

>>>>>>> 174339fcce31fa19dc4e24efa2aeb7f327170cd0
import com.example.project1.Repository.MandatoryFeeRepository;
import com.example.project1.entity.MandatoryFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
<<<<<<< HEAD
import org.springframework.data.domain.Sort;
=======
>>>>>>> 174339fcce31fa19dc4e24efa2aeb7f327170cd0
import org.springframework.stereotype.Service;

@Service
public class serviceMandatoryFee {
	@Autowired
	private MandatoryFeeRepository repo;
	
	//Tìm kiếm theo String keyword
	 public Page<MandatoryFee> listAll(String keyword,Integer pageNo){
<<<<<<< HEAD
		Pageable pageable = PageRequest.of(pageNo - 1, 2);//2 là size page
=======
		Pageable pageable = PageRequest.of(pageNo - 1, 10);//2 là size page
>>>>>>> 174339fcce31fa19dc4e24efa2aeb7f327170cd0
		if(keyword !=null) {
			return repo.findAll(keyword,pageable);
		}
		return this.repo.findAll(pageable);
	};
}