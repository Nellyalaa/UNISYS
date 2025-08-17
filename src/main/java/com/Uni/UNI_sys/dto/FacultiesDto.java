package com.Uni.UNI_sys.dto;

import com.Uni.UNI_sys.unipackage.Faculties;

public class FacultiesDto {

    //fields
    private int id;
    private String name;

    //constructors

    public FacultiesDto() {
    }

    public FacultiesDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
