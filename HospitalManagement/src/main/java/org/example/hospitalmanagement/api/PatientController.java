package org.example.hospitalmanagement.api;

import org.example.hospitalmanagement.business.patients.Patient;
import org.example.hospitalmanagement.business.patients.PatientManagementService;
import org.example.hospitalmanagement.business.patients.VisitManagementService;
import org.example.hospitalmanagement.persistence.model.Visit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    private PatientManagementService patientManagementService;
    private VisitManagementService visitManagementService;

    public PatientController (PatientManagementService patientManagementService, VisitManagementService visitManagementService) {
        this.patientManagementService = patientManagementService;
        this.visitManagementService = visitManagementService;
    }


    @RequestMapping("patients/list")
    public String listPatients(Model model) {
        model.addAttribute("patients",patientManagementService.getAllPatients());
        return "patients/list";
    }

    @GetMapping("patients/profile")
    public String patientProfile(@RequestParam Long id, Model model) {
        model.addAttribute("patient", patientManagementService.getPatientById(id));
        return "patients/profile";
    }

    @RequestMapping(value="patients/profile",method = RequestMethod.POST, params = "showVisits")
    public String showVisitsOfPatient(@ModelAttribute Patient patient, Model model) {
        model.addAttribute("visits" ,visitManagementService.getVisitsByPatientId(patient.getId()));
        model.addAttribute("patient", patient);
        return "patients/profile";
    }

    @RequestMapping(value="patients/profile",method = RequestMethod.POST, params = "edit")
    public String editPatientProfile(@ModelAttribute Patient patient, Model model) {
        //if(visits) {
       //     model.addAttribute("visits" ,visitManagementService.getVisitsByPatientId(patient.getId()));
       // }
        model.addAttribute("patient", patient);
        return "patients/test";
    }

    @RequestMapping("patients/showCreateForm")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/create";
    }

    @PostMapping("patients/addNewPatient")
    public String createPatient(@ModelAttribute Patient patient) {
        patientManagementService.addPatient(patient);
        return "redirect:/patients/list";
    }


}
