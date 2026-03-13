package org.example.hospitalmanagement.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {



    @GetMapping("dashboard")
    public String showDashboard() {
        return "dashboard";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
