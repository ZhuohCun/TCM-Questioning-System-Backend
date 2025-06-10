package com.InnovativePractice.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class MedicalCase {


    private Integer caseId;

    private Integer patientId; // 患者ID，假设与User表中的ID关联

    private Date diagnosisDate;

    private String symptoms;

    private String diagnosis;

    private String treatmentPlan;

    private String followUpInstructions;

    private String notes;


}
