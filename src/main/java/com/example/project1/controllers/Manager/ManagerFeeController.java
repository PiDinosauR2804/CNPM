package com.example.project1.controllers.Manager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.project1.Repository.DonationFeeHistoryRepository;
import com.example.project1.Repository.DonationFeeRepository;
import com.example.project1.Repository.MandatoryFeeHistoryRepository;
import com.example.project1.Repository.MandatoryFeeRepository;
import com.example.project1.Repository.RoomHistoryRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.Repository.TypeDonationRepository;
import com.example.project1.entity.DonationFee;
import com.example.project1.entity.DonationFeeHistory;
import com.example.project1.entity.MandatoryFee;
import com.example.project1.entity.MandatoryFeeHistory;
import com.example.project1.entity.Room;
import com.example.project1.entity.RoomHistory;
import com.example.project1.entity.TypeDonation;
import com.example.project1.service.serviceDonationFee;
import com.example.project1.service.serviceDonationFeeHistory;
import com.example.project1.service.serviceMandatoryFee;
import com.example.project1.service.serviceMandatoryFeeHistory;
import com.example.project1.service.serviceTypeFee;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ManagerFeeController {
    @Autowired
    MandatoryFeeRepository MandatoryFeeRepo;
    @Autowired
    serviceMandatoryFee serviceMF;
    
    @Autowired
    MandatoryFeeHistoryRepository MandatoryFeeHistoryRepo;
    @Autowired
    serviceMandatoryFeeHistory serviceMFH;

    @Autowired
    RoomRepository RoomRepo;
    @Autowired
    RoomHistoryRepository RoomHistoryRepo;

    @Autowired
    DonationFeeRepository DonationFeeRepo;
    @Autowired
    serviceDonationFee serviceDF;

    @Autowired
    DonationFeeHistoryRepository DonationFeeHistoryRepo;
    @Autowired
    serviceDonationFeeHistory serviceDFH;

    @Autowired
    TypeDonationRepository TypeDonationRepo;
    @Autowired
    serviceTypeFee serviceTF;  
    // Mandatory Fee

    @GetMapping("/manager/fee/index")
    public String index(@RequestParam(required = false) String keyword, Model model,
    		@RequestParam(defaultValue ="1") Integer pageNo) {
        Page <MandatoryFee> listFees = this.serviceMF.listAll(keyword,pageNo);
        model.addAttribute("keyword",keyword);
        model.addAttribute("totalPage",listFees.getTotalPages());
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("listFees", listFees);
        return "manager/fee/index";
    }

    @GetMapping("/manager/fee/mandatory/createAll")
    public String createAll() {
        List<Room> rooms = RoomRepo.findAll();
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);
        for (int i = 0; i < rooms.size(); i++) {
            MandatoryFee a = new MandatoryFee(currentMonth, currentYear, 0, 0);
            a.setRoom(rooms.get(i));
            rooms.get(i).addMandatoryFee(a);
            MandatoryFeeRepo.save(a);
            RoomRepo.save(rooms.get(i));
        }
        return "redirect:/manager/fee/index";
    }
    
    
    @GetMapping("/manager/room/{roomNumber}/fees")
    public String detail(@PathVariable int roomNumber, Model model) {
        model.addAttribute("roomNumber", roomNumber);
        Room a = RoomRepo.findByRoom(roomNumber).get(0);
        model.addAttribute("listFees", a.getMandatoryFees());
        model.addAttribute("listDonationFees", a.getDonationFees());
        return "manager/fee/feesRoom";
    }

    @GetMapping("/manager/fee/edit/{no}")
    public String edit(@PathVariable int no, Model model) {
        model.addAttribute("no", no);
        List<MandatoryFee> fees = MandatoryFeeRepo.findByPK(no);
        model.addAttribute("fee", fees.get(0));
        return "manager/fee/edit";
    }


    @PostMapping("/manager/fee/save/{no}")
    public String save(@PathVariable int no ,@ModelAttribute("fee") MandatoryFee fee) {
        MandatoryFee a = MandatoryFeeRepo.findByPK(no).get(0);
        a.setMonth(fee.getMonth());
        a.setYear(fee.getYear());
        a.setElectricFee(fee.getElectricFee());
        a.setWaterFee(fee.getWaterFee());
        MandatoryFeeRepo.save(a);   
        return "redirect:/manager/fee/index";
    }

    @GetMapping("/manager/fee/update")
    public String update() {
        List<MandatoryFee> fees = MandatoryFeeRepo.findIfFeeComplete();
        for (int i = 0; i < fees.size(); i++) {
            // Room a = RoomRepo.findByRoom(fees.get(i).getNoRoom()).get(0);
            eraseMandatoryFee(fees.get(i));
            MandatoryFeeRepo.delete(fees.get(i));
        }
        return "redirect:/manager/fee/index";
    }

    @GetMapping("/manager/fee/mandatory/multiple_edit")
    public String multipleEditMandatoryFee(Model model) {
        List<MandatoryFee> listFees = MandatoryFeeRepo.findAll();
        model.addAttribute("listFees", listFees);
        return "manager/fee/multiple_edit";
    }

    @PostMapping("/manager/fee/mandatory/saveAll")
    public String saveAllMandatory(HttpServletRequest request) {
        List<MandatoryFee> listFee_check = MandatoryFeeRepo.findAll();
        String[] noRooms = request.getParameterValues("noRooms");
        String[] roomFeePaids = request.getParameterValues("roomFeePaids");
        String[] waterFeePaids = request.getParameterValues("waterFeePaids");
        String[] waterFees = request.getParameterValues("waterFees");
        String[] electricFeePaids = request.getParameterValues("electricFeePaids");
        String[] electricFees = request.getParameterValues("electricFees");
        String[] parkingFeePaids = request.getParameterValues("parkingFeePaids");


        for (int i = 0; i < noRooms.length; i++) {
            for (MandatoryFee fee_check : listFee_check) {
                if (noRooms[i].equals(String.valueOf(fee_check.getNoRoom()))) {
                    MandatoryFeeRepo.updateAllMandatoryFee(fee_check.getNo(),
                            Integer.parseInt(roomFeePaids[i]),
                            Integer.parseInt(waterFees[i]),
                            Integer.parseInt(waterFeePaids[i]),
                            Integer.parseInt(electricFees[i]),
                            Integer.parseInt(electricFeePaids[i]),
                            Integer.parseInt(parkingFeePaids[i]));
                    break;
                }
            }
        }


        return "redirect:/manager/fee/mandatory/multiple_edit";
    }

    // Donation Fee
    @GetMapping("/manager/fee/donation/index")
    public String donation_index(@RequestParam(required = false) String keyword, Model model,
    		@RequestParam(defaultValue ="1") Integer pageNo) {
        Page <DonationFee> listFees = serviceDF.listAll(keyword,pageNo);
        model.addAttribute("listFees", listFees);
        model.addAttribute("totalPage",listFees.getTotalPages());
        model.addAttribute("currentPage",pageNo);
        return "manager/fee/donation/index";
    }

    @GetMapping("/manager/fee/donation/edit/{no}")
    public String editDonation(@PathVariable int no, Model model) {
        model.addAttribute("no", no);
        List<DonationFee> fees = DonationFeeRepo.findByNo(no);
        model.addAttribute("fee", fees.get(0));
        return "manager/fee/donation/edit";
    }


    @PostMapping("/manager/fee/donation/save/{no}")
    public String saveDonation(@PathVariable int no ,@ModelAttribute("fee") DonationFee fee) {
        DonationFee a = DonationFeeRepo.findByNo(no).get(0);
        a.setMonth(fee.getMonth());
        a.setYear(fee.getYear());
        a.setAmount(fee.getAmount());
        DonationFeeRepo.save(a);   
        return "redirect:/manager/fee/donation/index";
    }

    // Type Donation

    @GetMapping("/manager/fee/donation/type")
    public String donation_type_index(@RequestParam(required = false) String keyword, Model model,
    		@RequestParam(defaultValue ="1") Integer pageNo) {
        Page <TypeDonation> listTypes = this.serviceTF.listAll(keyword,pageNo);
        model.addAttribute("keyword",keyword);
        model.addAttribute("listTypes", listTypes);
        model.addAttribute("totalPage",listTypes.getTotalPages());
        model.addAttribute("currentPage",pageNo);
        return "manager/fee/donation/type";
    }

    @GetMapping("/manager/fee/donation/type/{id}/close")
    public String donation_type_close(@PathVariable int id) {
        List<TypeDonation> listTypes = TypeDonationRepo.findByNo(id);
        if (!listTypes.isEmpty()) {
            List<DonationFee> listDonationFees = DonationFeeRepo.findByNo(id);
            for (int i = 0; i < listDonationFees.size(); i++) {
                eraseDonationFee(listDonationFees.get(i));
            }
            TypeDonationRepo.delete(listTypes.get(0));
        }
        return "redirect:/manager/fee/donation/type";
    }

    @GetMapping("/manager/fee/donation/type/create")
    public String createType(Model model) {
        TypeDonation new_type = new TypeDonation();
        model.addAttribute("new_type", new_type);
        return "manager/fee/donation/createType";
    }

    @PostMapping("/manager/fee/donation/type/save")
    public String saveType(@ModelAttribute("typeDonation") TypeDonation typeDonation) {
        TypeDonationRepo.save(typeDonation);
        return "redirect:/manager/fee/donation/type";
    }
    
    @GetMapping("/manager/fee/donation/type/edit/{no}")
    public String editTypeDonation(@PathVariable int no, Model model) {
        model.addAttribute("no", no);
        List<TypeDonation> types = TypeDonationRepo.findByNo(no);
        model.addAttribute("new_type", types.get(0));
        return "manager/fee/donation/edit_type";
    }


    @PostMapping("/manager/fee/donation/type/save/{no}")
    public String updateTypeDonation(@PathVariable int no ,@ModelAttribute("new_type") TypeDonation new_type) {
        TypeDonation a = TypeDonationRepo.findByNo(no).get(0);
        a.setType(new_type.getType());
        TypeDonationRepo.save(a);   
        return "redirect:/manager/fee/donation/type";
    }

    // History Fee

    @GetMapping("/manager/history/fee/index")
    public String his_mdt_index(@RequestParam(required = false) String keyword, Model model,
    		@RequestParam(defaultValue ="1") Integer pageNo) {
    	Page <MandatoryFeeHistory> listFees = serviceMFH.listAll(keyword,pageNo);
        model.addAttribute("keyword",keyword);
        model.addAttribute("listFees", listFees);
        model.addAttribute("totalPage",listFees.getTotalPages());
        model.addAttribute("currentPage",pageNo);
        return "manager/fee/his_index";
    }

    @GetMapping("/manager/history/fee/donation/index")
    public String his_dnt_index(@RequestParam(required = false) String keyword, Model model,
    		@RequestParam(defaultValue ="1") Integer pageNo) {
        Page <DonationFeeHistory> listFees = serviceDFH.listAll(keyword,pageNo);
        model.addAttribute("keyword",keyword);
        model.addAttribute("listFees", listFees);
        model.addAttribute("totalPage",listFees.getTotalPages());
        model.addAttribute("currentPage",pageNo);
        return "manager/fee/donation/his_index";
    }

    public void eraseMandatoryFee(MandatoryFee fee) {
        if (fee.getElectricFeePaid() + fee.getParkingFeePaid() + fee.getRoomFeePaid() + fee.getWaterFeePaid() > 0) {
            createMandatoryFeeHistory(fee);
            MandatoryFeeRepo.delete(fee);
        }
    }

    public void createMandatoryFeeHistory(MandatoryFee fee) {
        MandatoryFeeHistory a = new MandatoryFeeHistory(fee.getMonth(), fee.getYear(), fee.getWaterFeePaid(), fee.getWaterFee(), fee.getElectricFeePaid(), fee.getElectricFee(), fee.getParkingFeePaid(), fee.getRoomFeePaid());
        RoomHistory b = RoomHistoryRepo.findByKey(fee.getKey()).get(0);
        a.setRoom(b);
        b.addMandatoryFee(a);
        MandatoryFeeHistoryRepo.save(a);
        RoomHistoryRepo.save(b);
    }

        // Donation History Fee
    public void eraseDonationFee(DonationFee fee) {
        createDonationFeeFeeHistory(fee);
        DonationFeeRepo.delete(fee);
    }

    public void createDonationFeeFeeHistory(DonationFee fee) {
        DonationFeeHistory a = new DonationFeeHistory(fee.getMonth(), fee.getYear(), fee.getAmount(), fee.getType());
        RoomHistory b = RoomHistoryRepo.findByKey(fee.getRoom().getKey()).get(0);
        a.setRoom(b);
        b.addDonationFee(a);
        DonationFeeHistoryRepo.save(a);
        RoomHistoryRepo.save(b);
    }
}
