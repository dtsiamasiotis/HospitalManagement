package org.example.hospitalmanagement.api;

import jakarta.validation.Valid;
import org.example.hospitalmanagement.business.patients.PatientFromServer;
import org.example.hospitalmanagement.business.personnel.Personnel;
import org.example.hospitalmanagement.business.personnel.PersonnelManagementService;
import org.example.hospitalmanagement.persistence.model.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String personnelProfile(@RequestParam("id") long id, Model model) {
        model.addAttribute("personnel", personnelManagementService.getPersonnelById(id));
        return "personnel/profile";
    }

    @RequestMapping("personnel/showCreateForm")
    public String showCreateForm(Model model) {
        model.addAttribute("occupationValues", personnelManagementService.getOccupationValues());
        model.addAttribute("personnel", new Personnel());
        return "personnel/create";
    }

    @RequestMapping(value="personnel/addNewPersonnel",method = RequestMethod.POST, params = "createNewPersonnel")
    public String createNewPersonnel(@Valid @ModelAttribute("personnel") Personnel personnel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("occupationValues", personnelManagementService.getOccupationValues());
            return "personnel/create";
        }
        personnelManagementService.createNewPersonnel(personnel);
        return "redirect:/personnel/list";
    }

    @RequestMapping(value="personnel/createSalaryPdf", method = RequestMethod.POST)
    public String createSalaryPdf(@ModelAttribute Personnel personnel) {
        try {
            personnelManagementService.createSalaryPdf(personnel);
        }catch (Exception e){}
        return "redirect:/personnel/list";
    }
}
