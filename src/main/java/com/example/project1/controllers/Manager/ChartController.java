package com.example.project1.controllers.Manager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project1.Repository.AddResidentRequestRepository;
import com.example.project1.Repository.RequestRepository;
import com.example.project1.Repository.ResidentHistoryRepository;
import com.example.project1.Repository.ResidentRepository;
import com.example.project1.Repository.RoomHistoryRepository;
import com.example.project1.Repository.RoomRepository;
import com.example.project1.entity.AddResidentRequest;
import com.example.project1.entity.Request;
import com.example.project1.entity.Resident;
import com.example.project1.entity.ResidentHistory;
import com.example.project1.entity.Room;
import com.example.project1.entity.RoomHistory;
import com.example.project1.service.serviceHistoryResident;
import com.example.project1.service.serviceResident;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;

@Controller
public class ChartController {

    @Autowired
    private ResidentRepository residentRepo;

    @GetMapping("/chart")
    public String showChart(Model model) {
        // Lấy dữ liệu cho biểu đồ từ database hoặc nơi khác
        List<Integer> dataPoints = getDataPoints();

        // Chuyển dữ liệu đến view
        model.addAttribute("dataPoints", dataPoints);

        return "chart";
    }

    private List<Integer> getDataPoints() {
        // Thực hiện logic để lấy dữ liệu từ nguồn nào đó
        // Ở đây chỉ là một ví dụ đơn giản
        return Arrays.asList(10, 20, 30, 40, 50);
    }

    private List<Integer> getLineChartPoints() {
        // Thực hiện logic để lấy dữ liệu từ nguồn nào đó
        // Ví dụ đơn giản
        return Arrays.asList(20, 40, 30, 60, 80);
    }
    
    @GetMapping("/line-chart")
    public String showLineChart(Model model) {
        List<Integer> lineChartPoints = getLineChartPoints();
        model.addAttribute("lineChartPoints", lineChartPoints);
    
        return "lineChart";
    }

    private List<Integer> getPieChartValues() {
        // Thực hiện logic để lấy dữ liệu từ nguồn nào đó
        // Ví dụ đơn giản
        return Arrays.asList(25, 15, 30, 20, 10);
    }
    
    @GetMapping("/pie-chart")
    public String showPieChart(Model model) {
        Integer male = 0;
        Integer female = 0;
        Integer other = 0;
        java.util.List<Resident> listResident = residentRepo.findAll();
        for (Resident res : listResident){
            if (res.getGender().equals("Male")){
                male++;
            } else if (res.getGender().equals("Female")){
                female++;
            } else {
                other++;
            }
        }
        List<Integer> pieChartValues = Arrays.asList(male, female, other);
        model.addAttribute("pieChartValues", pieChartValues);
    
        return "pieChart";
    }
}






