package com.Uni.UNI_sys.unipackage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Stu")
public class StudentController
{
    //field
    private final  StudentServices StudentServices;

    //constructor
    @Autowired
    public StudentController(StudentServices studentServices) {
        StudentServices = studentServices;
    }

    @GetMapping
    public List<Student> getStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String academic_Level,
            @RequestParam(required = false)Faculties fac
    ){
        if(name!= null && academic_Level == null && fac == null ) {
            return StudentServices.getStudentByName(name);
        } else if ( academic_Level!=null) {
            return StudentServices.getStudentByAcl(academic_Level);
        } else if (fac != null) {
            return StudentServices.getStudentByfac(fac);
        }
        return StudentServices.getAllStudents();


    }
    @PostMapping
    public ResponseEntity<Student> addFac(@RequestBody Student Student){
        Student newStudent= StudentServices.addStudent(Student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newStudent);
    }
    @DeleteMapping
    public List<Student> deletestu( @RequestParam(required = false) String name){
        if(name != null){
            StudentServices.deleteStudent(name);
            return StudentServices.getAllStudents();
        }
        return StudentServices.getAllStudents();
    }
    @PutMapping("/email/{cid}")
    public Student updateEmail(@PathVariable("cid") Integer cid,
                               @RequestParam String newEmail) {
        return StudentServices.updateEmailById(cid, newEmail);
    }

    @PutMapping("/address/{cid}")
    public Student updateaddress(@PathVariable("cid") Integer cid,
                                 @RequestParam String address) {
        return StudentServices.updateaddressById(cid, address);
    }

    @PutMapping("/Phone/{cid}")
    public  Student updatebyphone( @PathVariable("cid") Integer cid, @RequestParam String phone){
         return StudentServices.updateByPhone(cid,phone);
    }


}
