package com.Uni.UNI_sys.unipackage;


import com.Uni.UNI_sys.dto.FacultiesDto;
import com.Uni.UNI_sys.dto.coursesDto;
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

    //toDTO
    public coursesDto toDto(courses courses ){
        return new coursesDto(courses.getAcademic_Level(),courses.getName(),courses.getFac());
    }

    //toEntity
    public courses toEntity(coursesDto dto) {
        courses cour = new courses();
        cour.setFac(dto.getFac());
        cour.setName(dto.getName());
        cour.setAcademic_Level(dto.getAcademic_Level());
        return cour;
    }

    public List<coursesDto> getallcourses(){
        return coursesRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
    public List<coursesDto> getcoursesByName(String name){
        return  coursesRepo.findAll().stream().filter(courses -> courses.getName().toLowerCase().contains(name.toLowerCase())).map(this::toDto).collect(Collectors.toList());
    }
    public List<coursesDto> getcoursesByAcl(String academic_Level){
        return  coursesRepo.findAll().stream().filter(courses -> courses.getAcademic_Level().toLowerCase().contains(academic_Level.toLowerCase())).map(this::toDto).collect(Collectors.toList());
    }
    public List<coursesDto> getcoursesByfac(Faculties fac){
        return coursesRepo.findAll().stream().filter(courses -> courses.getFac().equals(fac)).map(this::toDto).collect(Collectors.toList());
    }
    public coursesDto addcourses(coursesDto coursesDto){
        courses cour =toEntity(coursesDto);
        courses saved = coursesRepo.save(cour);
        return toDto(saved);
    }


    @Transactional
    public void deleteStudent( String course){

        coursesRepo.deleteByName(course);
    }

    @Transactional
    public coursesDto updatebyname(Integer course_Id, String name) {
        return coursesRepo.findById(course_Id)  // instance method
                .map(courses -> {
                    courses.setName(name.trim());
                    courses updatedCourse = coursesRepo.save(courses);
                    return toDto(updatedCourse);

                })
                .orElseThrow(() -> new RuntimeException("course with ID " + course_Id + " not found!"));
    }

    @Transactional
    public coursesDto updateacdemiclevel(Integer course_Id, String academic_Level) {
        return coursesRepo.findById(course_Id)  // instance method
                .map(courses -> {
                    courses.setAcademic_Level(academic_Level.trim());
                   courses updatedCourse = coursesRepo.save(courses);
                    return toDto(updatedCourse);
                })
                .orElseThrow(() -> new RuntimeException("Course with ID " + course_Id + " not found!"));
    }



}

