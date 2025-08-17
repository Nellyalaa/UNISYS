package com.Uni.UNI_sys.unipackage;


import com.Uni.UNI_sys.dto.GradeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/grade")
public class GradesController {
    //fields
    private final GradesServices GradesServices;

    //constructors
    @Autowired
    public GradesController(GradesServices gradesServices) {
        GradesServices = gradesServices;
    }

    //methods
//get
    @GetMapping
    public List<GradeDto> getGrades(
            @RequestParam(required = false) Integer grade,
            @RequestParam(required = false) Student stu,
            @RequestParam(required = false) courses cour)
    {
       if (grade !=null){
           return GradesServices.getGrade(grade);
       } else if (stu != null) {
           return GradesServices.getGradesBystudentId(stu) ;
       } else if (cour != null) {
           return GradesServices.getGradesByCourseId(cour);
       }
        return GradesServices.getallGrades();
    }
    @PostMapping
    public ResponseEntity<GradeDto> addgrade(@RequestBody GradeDto GradeDto) {
        GradeDto newGrade = GradesServices.addGrade(GradeDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newGrade);
    }



    @PutMapping("/grade/{gId}")
    public GradeDto updateGrade(@PathVariable("gId") Integer gId,
                              @RequestParam Integer grade){
        return GradesServices.updateGrade(gId,grade);
    }


    @DeleteMapping("/{gId}")
    public ResponseEntity<String> deleteGrade(@PathVariable Integer gId) {
        GradesServices.Deletegrade(gId);
        return ResponseEntity.ok("Grade with ID " + gId + " deleted successfully!");
    }


}
