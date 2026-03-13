package org.example.hospitalmanagement.api;

import jakarta.validation.Valid;
import org.example.hospitalmanagement.business.patients.PatientManagementService;
import org.example.hospitalmanagement.persistence.model.AdmissionFormData;
import org.example.hospitalmanagement.business.clinics.ClinicManagementService;
import org.example.hospitalmanagement.business.patients.AdmissionManagementService;
import org.example.hospitalmanagement.persistence.model.Admission;
import org.example.hospitalmanagement.persistence.model.AdmissionStatus;
import org.example.hospitalmanagement.persistence.model.Patient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class AdmissionController {

    private final PatientManagementService patientManagementService;
    private AdmissionManagementService admissionManagementService;
      private ClinicManagementService clinicManagementService;
   // private VisitManagementService visitManagementService;

    public AdmissionController(AdmissionManagementService admissionManagementService, ClinicManagementService clinicManagementService, PatientManagementService patientManagementService) {
        this.admissionManagementService = admissionManagementService;
        this.clinicManagementService = clinicManagementService;
        this.patientManagementService = patientManagementService;
    }


  /*  @RequestMapping("patients/list")
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
    }*/
    @RequestMapping("admissions/list")
    public String listAdmissions(Model model) {
      model.addAttribute("admissions", admissionManagementService.getAllAdmissions());
      return "admissions/list";
    }

    @RequestMapping("admissions/showCreateForm")
    public String showCreateForm(Model model) {
        model.addAttribute("admission", AdmissionFormData.builder().build());
        model.addAttribute("clinics", clinicManagementService.getClinicsWithAvailableBeds());
        return "admissions/create";
    }

    @PostMapping("admissions/addNewAdmission")
    public String createAdmission(@ModelAttribute AdmissionFormData admissionFormData) {
        Admission admission = new Admission();
        admission.setPatient(patientManagementService.getPatientById(admissionFormData.getPatientId()));
        admission.setClinic(clinicManagementService.getClinicById(admissionFormData.getClinicId()).orElseThrow());
        admission.setStartDate(admissionFormData.getStartDateTime());
        admission.setStatus(AdmissionStatus.ONGOING);
        admissionManagementService.addAdmission(admission);
        return "redirect:/admissions/list";
    }

    @PostMapping(value = "admissions/completeAdmission", params = "completeAdmission")
    public String completeAdmission(@RequestParam("admissionId") Long id, Model model) {
        Admission storedAdmission = admissionManagementService.getAdmissionById(id);
        storedAdmission.setStatus(AdmissionStatus.COMPLETED);
        storedAdmission.setEndDate(LocalDateTime.now());
        storedAdmission.getPatient().getBed().setPatient(null);
        storedAdmission.getPatient().setBed(null);
        admissionManagementService.updateAdmission(storedAdmission);
        model.addAttribute("admissions" ,admissionManagementService.getAdmissionsByPatient(storedAdmission.getPatient().getId()));
        model.addAttribute("patient", storedAdmission.getPatient());
        return "/patients/profile";
    }

}
