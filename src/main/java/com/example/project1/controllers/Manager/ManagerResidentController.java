package com.example.project1.controllers.Manager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.project1.Repository.ResidentHistoryRepository;
import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomHistoryRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.Resident;
import com.example.project1.entity.ResidentHistory;
import com.example.project1.entity.Room;
import com.example.project1.entity.RoomHistory;

@Controller
public class ManagerResidentController {
    @Autowired
    private ResidentRepository residentRepo;
    @Autowired
    private ResidentHistoryRepository residentHistoryRepo;

    @Autowired
    private RoomRepository roomRepo;
    @Autowired
    private RoomHistoryRepository roomHistoryRepo;

    public int roomNumber = 0;

    @GetMapping("/manager/room/{noRoom}")
    public String viewRoomDetails(@PathVariable String noRoom, Model model) {
        roomNumber = Integer.parseInt(noRoom);
        java.util.List<Resident> listResidentRoom = residentRepo.findByRoom(roomNumber);
        model.addAttribute("noRoom", noRoom);
        model.addAttribute("listResidentRoom", listResidentRoom);
        return "manager/room/residentRoom";
    }

    @GetMapping("/manager/room/{roomNumber}/addresident")
    public String viewRoomDetails(@PathVariable int roomNumber, Model model) {
        model.addAttribute("roomNumber", roomNumber);
        model.addAttribute("Resident", new Resident());
        return "manager/resident/create";
    }

    @PostMapping("/manager/room/{roomNumber}/addresident/save")
    public String save(@PathVariable int roomNumber, Resident resident) {
        java.util.List<Room> room = roomRepo.findByRoom(roomNumber);
        if (!room.isEmpty()) {
            resident.setRoom(room.get(0));
            room.get(0).addResident(resident);

            saveResidentInHistory(resident);

            residentRepo.save(resident);
            roomRepo.save(room.get(0));
        }
        return "redirect:/manager/room/{roomNumber}";
    }

    // Resident History

    public void saveResidentInHistory(Resident resident) {
        ResidentHistory resi = new ResidentHistory(resident.getId(), resident.getName(), resident.getGender(), resident.getBirthDate(), resident.getBirthPlace(), resident.getJob(), resident.getPhoneNumber(), resident.getRelationshipWithOwner(), getTime());
        RoomHistory a = roomHistoryRepo.findByKey(resident.getkey()).get(0);
        resi.setRoom(a);
        a.addResident(resi);
        residentHistoryRepo.save(resi);
        roomHistoryRepo.save(a);
    }

    public String getTime() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String curDate_string = currentDate.format(dateFormatter);
        return curDate_string;
    }
    
    public void updateCloseTimeInResidentHistory(Resident resi) {
        ResidentHistory a = residentHistoryRepo.findByNo(resi.getNo()).get(0);
        a.setdayOut(getTime());
        residentHistoryRepo.save(a);
    }

    public void eraseResident(Resident resi) {
        updateCloseTimeInResidentHistory(resi);
        residentRepo.delete(resi);
    }
}
