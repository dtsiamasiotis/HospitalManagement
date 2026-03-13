package org.example.hospitalmanagement.api;

import org.example.hospitalmanagement.business.clinics.ClinicManagementService;
import org.example.hospitalmanagement.business.patients.AdmissionManagementService;
import org.example.hospitalmanagement.business.patients.PatientManagementService;
import org.example.hospitalmanagement.persistence.model.Admission;
import org.example.hospitalmanagement.persistence.model.AdmissionFormData;
import org.example.hospitalmanagement.persistence.model.AdmissionStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClinicController {

    private ClinicManagementService clinicManagementService;


    public ClinicController(ClinicManagementService clinicManagementService) {
        this.clinicManagementService = clinicManagementService;
    }

    @RequestMapping("clinics/list")
    public String listAdmissions(Model model) {
      model.addAttribute("clinics", clinicManagementService.getAllClinics());
      return "clinics/list";
    }


}
