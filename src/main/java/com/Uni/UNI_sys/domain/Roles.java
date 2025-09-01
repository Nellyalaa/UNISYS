package com.Uni.UNI_sys.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Roles {

    //fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rid;
    private String name;

    //constructors

    public Roles() {
    }


    public Roles(Long rid, String name) {
        this.rid = rid;
        this.name = name;
    }

    // getters and setters


    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
