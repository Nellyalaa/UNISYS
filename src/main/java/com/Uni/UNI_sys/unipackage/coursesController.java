package com.Uni.UNI_sys.unipackage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cour")
public class coursesController {

    //fields
    private final coursesServices coursesServices;

    //constructor
    @Autowired
    public coursesController(coursesServices coursesServices) {
        this.coursesServices = coursesServices;
    }

    //methods

    @GetMapping
    public List<courses> getCourses(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String academic_Level,
            @RequestParam(required = false) Faculties fac
            ){
        if(name != null){
            return coursesServices.getcoursesByName(name);
        } else if ((academic_Level != null)) {
            return coursesServices.getcoursesByAcl(academic_Level);

        }
        else if (fac != null){
            return coursesServices.getcoursesByfac(fac);
        }
        return coursesServices.getallcourses();
    }

    @PostMapping
    public ResponseEntity<courses> addcour(@RequestBody courses courses) {
        courses newcourses = coursesServices.addcourses(courses);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newcourses);
    }

    @DeleteMapping
    public List<courses> deleteCour(@RequestParam(required = false) String name){
        if(name != null){
            coursesServices.deleteStudent(name);
            return coursesServices.getallcourses();
        }
        return coursesServices.getallcourses();
    }

    @PutMapping("/name/{course_Id}")
    public courses updatename(@PathVariable("course_Id") Integer course_Id,
                              @RequestParam String name){
        return coursesServices.updatebyname(course_Id,name);
    }

    @PutMapping("/academic_Level/{course_Id}")
    public courses updateAcl(@PathVariable("course_Id") Integer course_Id,
                             @RequestParam String academic_Level ){
        return coursesServices.updateacdemiclevel(course_Id,academic_Level);
    }

}
