package com.Uni.UNI_sys.unipackage;


import com.Uni.UNI_sys.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Stu")
@CrossOrigin(origins = "http://localhost:4210")
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
    public List<StudentDto> getStudents(
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
    public ResponseEntity<StudentDto> addFac(@RequestBody StudentDto StudentDto){
        StudentDto newStudent= StudentServices.addStudent(StudentDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newStudent);
    }
    //@DeleteMapping
  //  public List<StudentDto> deletestu( @RequestParam(required = false) String name){
      //  if(name != null){
          //  StudentServices.deleteStudent(name);
           // return StudentServices.getAllStudents();
       // }
     //  return StudentServices.getAllStudents();
   // }
    @PutMapping("/{cid}")
    public ResponseEntity<StudentDto> updateStudent(
            @PathVariable("cid") Integer cid,
            @RequestBody StudentDto updatedStudent
    ) {
        StudentDto saved = StudentServices.updateStudent(cid, updatedStudent);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{cid}")
    public void deleteStudentById(@PathVariable Integer cid) {
        StudentServices.deleteStudentById(cid);
    }





}
