package com.Uni.UNI_sys.dto;

import com.Uni.UNI_sys.unipackage.Student;
import com.Uni.UNI_sys.unipackage.courses;

public class GradeDto {
    //FIELDS
    private int grade;
    private courses courses;
    private Student student;

    //Constructor


    public GradeDto() {
    }

    public GradeDto(int grade, courses courses, Student student) {
        this.grade = grade;
        this.courses = courses;
        this.student = student;
    }

    //SETTERS AND GETTERS


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
