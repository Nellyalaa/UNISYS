package com.Uni.UNI_sys.unipackage;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class coursesServices {
    //fields
    public final coursesRepo coursesRepo;

//constructors
    public coursesServices(coursesRepo coursesRepo) {
        this.coursesRepo = coursesRepo;
    }

    //methods

    public List<courses> getallcourses(){
        return coursesRepo.findAll();
    }
    public List<courses> getcoursesByName(String name){
        return  coursesRepo.findAll().stream().filter(courses -> courses.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
    public List<courses> getcoursesByAcl(String academic_Level){
        return  coursesRepo.findAll().stream().filter(courses -> courses.getAcademic_Level().toLowerCase().contains(academic_Level.toLowerCase())).collect(Collectors.toList());
    }
    public List<courses> getcoursesByfac(Faculties fac){
        return coursesRepo.findAll().stream().filter(courses -> courses.getFac().equals(fac)).collect(Collectors.toList());
    }
    public courses addcourses(courses courses){
        return coursesRepo.save(courses);
    }


    @Transactional
    public void deleteStudent( String course){

        coursesRepo.deleteByName(course);
    }

    @Transactional
    public courses updatebyname(Integer course_Id, String name) {
        return coursesRepo.findById(course_Id)  // instance method
                .map(courses -> {
                    courses.setName(name.trim());
                    return coursesRepo.save(courses);  // instance method
                })
                .orElseThrow(() -> new RuntimeException("course with ID " + course_Id + " not found!"));
    }

    @Transactional
    public courses updateacdemiclevel(Integer course_Id, String academic_Level) {
        return coursesRepo.findById(course_Id)  // instance method
                .map(courses -> {
                    courses.setAcademic_Level(academic_Level.trim());
                    return coursesRepo.save(courses);  // instance method
                })
                .orElseThrow(() -> new RuntimeException("Course with ID " + course_Id + " not found!"));
    }



}

