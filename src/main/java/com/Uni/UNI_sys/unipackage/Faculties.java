package com.Uni.UNI_sys.unipackage;


import jakarta.persistence.*;

@Entity
public class Faculties {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id ;
    private String name;

    //Constructors

    public Faculties() {
    }

    public Faculties(int id, String name) {
        Id = id;
        name = name;
    }

    // getters & setters

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
