package com.Uni.UNI_sys.dto;

import com.Uni.UNI_sys.unipackage.Faculties;

public class StudentDto {

    //fields
    private int cid;
    private String name;
    private String email;
    private String Phone_Number;
    private String academic_Level;
    private Faculties fac;

    //constructors

    public StudentDto() {
    }

    public StudentDto(int cid, String name, String email, String phone_Number, String academic_Level, Faculties fac) {
        this.cid = cid;
        this.name = name;
        this.email = email;
        Phone_Number = phone_Number;
        this.academic_Level = academic_Level;
        this.fac = fac;
    }

    //getters and setters
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Faculties getFac() {
        return fac;
    }

    public void setFac(Faculties fac) {
        this.fac = fac;
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

    public String getAcademic_Level() {
        return academic_Level;
    }

    public void setAcademic_Level(String academic_Level) {
        this.academic_Level = academic_Level;
    }
}
