package org.example.hospitalmanagement.business.personnel;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.hospitalmanagement.persistence.model.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class PersonnelManagementService {

    @Value("${peopleManagement.url}")
    private String peopleManagementUrl;

    private RestTemplate restTemplate;

    public PersonnelManagementService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Personnel> getAllPersonnel() {
        ResponseEntity<Personnel[]> response = restTemplate.getForEntity(peopleManagementUrl  + "/listPersonnel", Personnel[].class);
        return Arrays.stream(response.getBody()).toList();
    }

    public Personnel getPersonnelById(Long id) {
        return restTemplate.getForObject(peopleManagementUrl + "/getPersonnelById?id=" + id, Personnel.class);
    }

    @Scheduled(cron="${compensation.cron.expression}")
    public void calculateMonthlyCompensation() {
        restTemplate.getForObject(peopleManagementUrl + "/calculateMonthlyCompensation", String.class);
    }

    public void createNewPersonnel(Personnel personnel) {
        restTemplate.postForObject(peopleManagementUrl + "/createPersonnel", personnel, Personnel.class);
    }

    public String[] getOccupationValues() {
        return restTemplate.getForObject(peopleManagementUrl + "/getOccupationValues", String[].class);
    }

    public void createSalaryPdf(Personnel personnel) throws Exception {
        MonthlyCompensation compensation = getMonthlyCompensation(personnel);
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("//home//dtsiam-lpt2//salary.pdf"));
        document.open();
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Chunk chunk = new Chunk(personnel.getFirstName() + " " + personnel.getLastName(), font);
        PdfPTable table = new PdfPTable(2);
        table.addCell("Total Amount");
        table.addCell(String.valueOf(compensation.getTotalAmount()));
        table.addCell("Base Salary");
        table.addCell(String.valueOf(personnel.getBaseSalary()));
        table.addCell("Overtime Amount");
        table.addCell(String.valueOf(compensation.getOvertimeAmount()));
        table.addCell("Health Insurance Amount");
        table.addCell(String.valueOf(compensation.getHealthAmount()));
        table.addCell("Tax Amount");
        table.addCell(String.valueOf(compensation.getTaxAmount()));
        table.addCell("Net Amount");
        table.addCell(String.valueOf(compensation.getNetAmount()));
        document.add(chunk);
        document.add(table);
        document.close();
    }

    private MonthlyCompensation getMonthlyCompensation(Personnel personnel) {
        return restTemplate.getForObject(peopleManagementUrl + "/getMonthlyCompensationByPersonnelId?id=" + personnel.getId(), MonthlyCompensation.class);
    }
}
