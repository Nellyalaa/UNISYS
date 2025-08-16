package com.Uni.UNI_sys.unipackage;

import jakarta.persistence.*;

@Entity
public class Student {

    //Fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;


    private String name;
    private String email;
    private String Phone_Number;
    private String address;
    private String academic_Level;


    @ManyToOne
    @JoinColumn(name = "Faculty_ID", referencedColumnName = "Id")
    private Faculties fac;

    //CONSTRUCTORS

    public Student() {
    }

    public Student(int cid, String name, String email, String phone_Number, String address, String academic_Level, Faculties fac) {
        this.cid = cid;
        this.name = name;
        this.email = email;
        Phone_Number = phone_Number;
        this.address = address;
        this.academic_Level = academic_Level;
        this.fac = fac;
    }

    //Setters and Getters

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAcademic_Level() {
        return academic_Level;
    }

    public void setAcademic_Level(String academic_Level) {
        this.academic_Level = academic_Level;
    }

    public Faculties getFac() {
        return fac;
    }

    public void setFac(Faculties fac) {
        this.fac = fac;
    }
}
