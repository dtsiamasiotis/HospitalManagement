package org.example.hospitalmanagement.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {



    @RequestMapping("dashboard")
    public String showDashboard() {
        return "dashboard";
    }
}
