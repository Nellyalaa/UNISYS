package com.Uni.UNI_sys.dto;

import com.Uni.UNI_sys.unipackage.Faculties;

public class coursesDto {

//Fields
    private String academic_Level;
    private String name;
    private Faculties fac;

    //constructors

    public coursesDto() {
    }

    public coursesDto(String academic_Level, String name, Faculties fac) {
        this.academic_Level = academic_Level;
        this.name = name;
        this.fac = fac;
    }

    //SETTERS AND GETTERS

    public String getAcademic_Level() {
        return academic_Level;
    }

    public void setAcademic_Level(String academic_Level) {
        this.academic_Level = academic_Level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculties getFac() {
        return fac;
    }

    public void setFac(Faculties fac) {
        this.fac = fac;
    }
}
