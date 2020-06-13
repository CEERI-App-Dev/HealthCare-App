package com.example.healthcareapp;

public class CustomerModel { private int id;
    private String company;
    private String name;
    private String email;
    private String phone;
    private boolean symptoms;
    private boolean absence;
    private boolean overseas;
    private boolean contact;
    private double temp;
    private boolean visit;

    //Constructors

    public CustomerModel(int id, String company, String name, String email, String phone, boolean symptoms,
                         boolean absence, boolean overseas, boolean contact, double temp, boolean visit) {
        this.id = id;
        this.company = company;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.symptoms = symptoms;
        this.absence = absence;
        this.overseas = overseas;
        this.contact = contact;
        this.temp = temp;
        this.visit = visit;
    }

    public CustomerModel() {
    }

    //toString()

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", symptoms=" + symptoms +
                ", absence=" + absence +
                ", overseas=" + overseas +
                ", contact=" + contact +
                ", temp=" + temp +
                ", visit=" + visit +
                '}';
    }

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSymptoms() {
        return symptoms;
    }

    public void setSymptoms(boolean symptoms) {
        this.symptoms = symptoms;
    }

    public boolean isAbsence() {
        return absence;
    }

    public void setAbsence(boolean absence) {
        this.absence = absence;
    }

    public boolean isOverseas() {
        return overseas;
    }

    public void setOverseas(boolean overseas) {
        this.overseas = overseas;
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public boolean isVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }
}
