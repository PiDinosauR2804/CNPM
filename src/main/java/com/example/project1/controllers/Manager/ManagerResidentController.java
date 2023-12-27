package com.example.project1.controllers.Manager;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.Resident;
import com.example.project1.entity.Room;
import com.example.project1.service.serviceResident;
import com.example.project1.service.serviceRoom;

@Controller
public class ManagerResidentController {
    @Autowired
    private ResidentRepository residentRepo;
    @Autowired
    private serviceResident service;
    @Autowired
    private RoomRepository roomRepository;
    public int roomNumber = 0;
    //xem các thành viên trong phòng
    @GetMapping("/manager/room/{noRoom}")
    public String viewRoomDetails(@PathVariable String noRoom, Model model) {
        roomNumber = Integer.parseInt(noRoom);
        java.util.List<Resident> listResidentRoom = residentRepo.findByRoom(roomNumber);
        model.addAttribute("noRoom", noRoom);
        model.addAttribute("listResidentRoom", listResidentRoom);
        return "manager/room/residentRoom";
    }
    //Khi thêm 1 thành viên mới vào phòng
    @GetMapping("/manager/room/{roomNumber}/addresident")
    public String viewRoomDetails(@PathVariable int roomNumber, Model model) {
        model.addAttribute("roomNumber", roomNumber);
        model.addAttribute("Resident", new Resident());
        return "manager/resident/create";
    }
    
    //Sau khi thêm thành viên mới vào một phòng -> lưu
    @PostMapping("/manager/room/{roomNumber}/addresident/save")
    public String save(@PathVariable int roomNumber, Resident resident) {
        java.util.List<Room> room = roomRepository.findByRoom(roomNumber);
        if (!room.isEmpty()) {
            resident.setRoom(room.get(0));
            room.get(0).addResident(resident);
            residentRepo.save(resident);
        }
        return "redirect:/manager/room/{roomNumber}";
    }
    //Xem tất cả người dân hiện tại, tìm kiếm được theo tên hoặc id
    @GetMapping ("/manager/resident/residentIndex")
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword,
    		@RequestParam(name = "pageNo", defaultValue ="1") Integer pageNo) {
        Page <Resident> listResident = this.service.listAll(keyword,pageNo);
        model.addAttribute("totalPage",listResident.getTotalPages());
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("listResident", listResident);
        return "manager/resident/residentIndex";
    }
    
 

    //  người dùng chọn phòng và nhấn nút "Next"
    @GetMapping("/manager/resident/createDontKnowRoom")
    public String addResident(Model model) {
    	List<Room> rooms = roomRepository.findAll();
    	model.addAttribute("rooms", rooms);
    	return "manager/resident/createDontKnowRoom";
    }

    // Xử lý sau khi người dùng nhấn nút "Next" trong trang chọn phòng
    @PostMapping("/manager/resident/createDontKnowRoom/next")
    public String processSelectedRoom(@RequestParam("numberRoom") String numberRoom) {
    	String displayValue = service.extractDisplayValue(numberRoom);
    	try {
            Integer.parseInt(numberRoom);
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            return "redirect:/manager/resident/findNumberRoom?error=invalidInput";
        }
         return "redirect:/manager/room/"+displayValue+"/addresident";
    }
}
