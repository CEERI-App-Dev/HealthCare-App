package com.example.healthcareapp;

public class EmployeeModel {
    private String company;
    private String name;
    private String email;
    private String phone;
    private boolean symptoms;
    private boolean absence;
    private boolean overseas;
    private boolean contact;
    private long temperature;
    private boolean visit;

    public EmployeeModel() {
    }

    public EmployeeModel(String company, String name, String email, String phone, boolean symptoms, boolean absence,
                         boolean overseas, boolean contact, long temperature, boolean visit) {
        this.company = company;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.symptoms = symptoms;
        this.absence = absence;
        this.overseas = overseas;
        this.contact = contact;
        this.temperature = temperature;
        this.visit = visit;
    }

    public String getCompany(String company) {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName(String name) {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail(String email) {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone(String phone) {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSymptoms(Boolean symptoms) {
        return this.symptoms;
    }

    public void setSymptoms(boolean symptoms) {
        this.symptoms = symptoms;
    }

    public boolean isAbsence(Boolean absence) {
        return this.absence;
    }

    public void setAbsence(boolean absence) {
        this.absence = absence;
    }

    public boolean isOverseas(Boolean overseas) {
        return this.overseas;
    }

    public void setOverseas(boolean overseas) {
        this.overseas = overseas;
    }

    public boolean isContact(Boolean contact) {
        return this.contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public long getTemperature(Long temperature) {
        return this.temperature;
    }

    public void setTemperature(long temperature) {
        this.temperature = temperature;
    }

    public boolean isVisit(Boolean visit) {
        return this.visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }
}
