package com.Uni.UNI_sys.unipackage;


import jakarta.persistence.*;

@Entity
public class courses {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  course_Id;

    private String academic_Level;
    private String name;

    @ManyToOne
    @JoinColumn(name = "Faculty_ID", referencedColumnName = "Id")
    private Faculties fac;

    //Constructors

    public courses() {
    }

    public courses(int course_Id, String academic_Level, String name, Faculties fac) {
        this.course_Id = course_Id;
        this.academic_Level = academic_Level;
        this.name = name;
        this.fac = fac;
    }
    //Setters and getters

    public int getCourse_Id() {
        return course_Id;
    }

    public void setCourse_Id(int course_Id) {
        this.course_Id = course_Id;
    }

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
