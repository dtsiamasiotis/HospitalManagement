package org.example.hospitalmanagement.api;

import jakarta.validation.Valid;
import org.example.hospitalmanagement.persistence.model.AdmissionFormData;
import org.example.hospitalmanagement.business.clinics.ClinicManagementService;
import org.example.hospitalmanagement.business.patients.AdmissionManagementService;
import org.example.hospitalmanagement.business.patients.PatientFromServer;
import org.example.hospitalmanagement.business.patients.PatientManagementService;
import org.example.hospitalmanagement.business.patients.VisitManagementService;
import org.example.hospitalmanagement.persistence.model.Patient;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    private final ClinicManagementService clinicManagementService;
    private PatientManagementService patientManagementService;
    private VisitManagementService visitManagementService;
    private AdmissionManagementService admissionManagementService;

    public PatientController (PatientManagementService patientManagementService, VisitManagementService visitManagementService, AdmissionManagementService admissionManagementService, ClinicManagementService clinicManagementService) {
        this.patientManagementService = patientManagementService;
        this.visitManagementService = visitManagementService;
        this.admissionManagementService = admissionManagementService;
        this.clinicManagementService = clinicManagementService;
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
    public String showVisitsOfPatient(@RequestParam Long id, Model model) {
        model.addAttribute("visits" ,visitManagementService.getVisitsByPatientId(id));
        model.addAttribute("patient", patientManagementService.getPatientById(id));
        return "patients/profile";
    }

    @RequestMapping(value="patients/profile",method = RequestMethod.POST, params = "showAdmissions")
    public String showAdmissionsOfPatient(@RequestParam Long id, Model model) {
        model.addAttribute("admissions" ,admissionManagementService.getAdmissionsByPatient(id));
        model.addAttribute("patient", patientManagementService.getPatientById(id));
        return "patients/profile";
    }

    @RequestMapping(value="patients/profile",method = RequestMethod.POST, params = "edit")
    public String editPatientProfile(@ModelAttribute PatientFromServer patient, Model model) {
        //if(visits) {
       //     model.addAttribute("visits" ,visitManagementService.getVisitsByPatientId(patient.getId()));
       // }
        model.addAttribute("patient", patient);
        return "patients/test";
    }

    @RequestMapping(value="patients/profile",method = RequestMethod.POST, params = "createAdmission")
    public String createAdmissionForPatient(@ModelAttribute PatientFromServer patient, Model model) {
        //if(visits) {
        //     model.addAttribute("visits" ,visitManagementService.getVisitsByPatientId(patient.getId()));
        // }
        model.addAttribute("patient", patient);
        model.addAttribute("clinics", clinicManagementService.getClinicsWithAvailableBeds());
        model.addAttribute("admission", AdmissionFormData.builder().patientId(patient.getId()).build());
        return "admissions/create";
    }

    @RequestMapping("patients/showCreateForm")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new PatientFromServer());
        model.addAttribute("patientFound", true);
        model.addAttribute("duplicateInsuranceNumber", false);
        return "patients/create";
    }


    @PostMapping(value="patients/addNewPatient", params = "getFromServer")
    public String getPatientFromServer(@Valid @ModelAttribute("patient") PatientFromServer patient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patientFound", true);
            return "patients/create";
        }

        PatientFromServer patientFromServer = patientManagementService.getPatientByInsuranceNumber(String.valueOf(patient.getInsuranceNumber()));

        if(patientFromServer == null) {
            model.addAttribute("patient", patient);
            model.addAttribute("patientFound", false);
            model.addAttribute("duplicateInsuranceNumber", false);
        }else {
            Patient localPatient = patientManagementService.transformPatientFromServertoPatient(patientFromServer);
            model.addAttribute("patient", localPatient);
            model.addAttribute("patientFound", true);
            model.addAttribute("duplicateInsuranceNumber", false);
        }
        return "patients/create";
    }

    @RequestMapping(value="patients/addNewPatient",method = RequestMethod.POST, params = "createNewPatient")
    public String createNewPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("duplicateInsuranceNumber", false);
            model.addAttribute("patientFound", true);
            return "patients/create";
        }
        try {
            patientManagementService.createNewPatient(patient);
        }catch (DataIntegrityViolationException e) {
            model.addAttribute("duplicateInsuranceNumber", true);
            model.addAttribute("patientFound", true);
            return "patients/create";
        }
        return "redirect:/patients/list";
    }
    
    @PostMapping(value="patients/updatePatient")
    public String updateProfile(@Valid @ModelAttribute("patient") Patient patient) {
        patientManagementService.updatePatient(patient);
        return "redirect:/patients/list";
    }
}
