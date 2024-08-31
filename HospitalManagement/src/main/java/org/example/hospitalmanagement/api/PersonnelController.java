package org.example.hospitalmanagement.api;

import org.example.hospitalmanagement.business.personnel.PersonnelManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonnelController {

    private PersonnelManagementService personnelManagementService;

    public PersonnelController(PersonnelManagementService personnelManagementService) {
        this.personnelManagementService = personnelManagementService;
    }


    @RequestMapping("personnel/list")
    public String listPersonnel(Model model) {
        model.addAttribute("personnel",personnelManagementService.getAllPersonnel());
        return "personnel/list";
    }

    @GetMapping("personnel/profile")
    public String patientProfile(@RequestParam("id") long id, Model model) {
        model.addAttribute("personnel", personnelManagementService.getPersonnelById(id));
        return "personnel/profile";
    }
}
