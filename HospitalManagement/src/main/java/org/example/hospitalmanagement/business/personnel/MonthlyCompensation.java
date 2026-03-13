package org.example.hospitalmanagement.business.personnel;

import lombok.Data;

@Data
public class MonthlyCompensation {
    private Long id;

    private Long personnelId;

    private Long totalAmount;

    private Long overtimeAmount;

    private int month;

    private Long netAmount;

    private Long taxAmount;

    private Long healthAmount;
}
