package org.example.hospitalmanagement.api;

import org.example.hospitalmanagement.business.patients.Patient;
import org.example.hospitalmanagement.business.patients.PatientManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {



    @PostMapping("dashboard")
    public String showDashboard() {
        return "dashboard";
    }
}
