package com.Uni.UNI_sys.unipackage;


import jakarta.persistence.*;

@Entity
public class Grades {

    //fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int gId;

    private int grade;

    @ManyToOne
    @JoinColumn(name="course_ID",referencedColumnName="course_Id")
    private courses courses;

    @ManyToOne
    @JoinColumn(name="Student_Id",referencedColumnName="cid")
    private Student student;


    //constructor

    public Grades() {
    }

    public Grades(int gId, int grade, courses courses, Student student) {
        this.gId = gId;
        this.grade = grade;
        this.courses = courses;
        this.student = student;
    }

    //getters and setters

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public courses getCourses() {
        return courses;
    }

    public void setCourses(courses courses) {
        this.courses = courses;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
