package com.management.travel_management.model;

import jakarta.persistence.Entity;

import java.sql.Date;

@Entity
public class User {

    private Long id;
    private int empNo;
    private String empName;
    private Date startDate;
    private Date endDate;
    private String destination;
    private String purpose;

    public User(Long id, int empNo, String empName, Date startDate, Date endDate, String destination, String purpose) {
        this.id = id;
        this.empNo = empNo;
        this.empName = empName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.destination = destination;
        this.purpose = purpose;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
