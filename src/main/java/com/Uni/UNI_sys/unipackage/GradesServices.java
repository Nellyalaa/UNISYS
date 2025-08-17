package com.Uni.UNI_sys.unipackage;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradesServices {
    //fields
    private final GradesRepo GradesRepo;

    //constructor
    public GradesServices(GradesRepo gradesRepo) {
        GradesRepo = gradesRepo;
    }

    //methods
    //get
    public List<Grades> getallGrades(){
        return GradesRepo.findAll();
    }
    public List<Grades> getGrade(Integer grade) {
        return GradesRepo.findAll()
                .stream()
                .filter(g -> g.getGrade() == grade)
                .collect(Collectors.toList());
    }

    public List<Grades> getGradesBystudentId(Student stu){
        return GradesRepo.findAll().stream().filter(Grades -> Grades.getStudent().equals(stu)).collect(Collectors.toList());
    }
    public List<Grades> getGradesByCourseId(courses cour){
        return GradesRepo.findAll().stream().filter(Grades -> Grades.getCourses().equals(cour)).collect(Collectors.toList());
    }
    //add
    public Grades addGrade( Grades Grades){
        return GradesRepo.save(Grades);
    }
    //delete
   public void Deletegrade(Integer Id){
         GradesRepo.deleteById(Id);
   }
    //update
    public Grades updateGrade(Integer gId, Integer grade){

        return GradesRepo.findById(gId)  // instance method
                .map(Grades -> {
                    Grades.setGrade(grade);
                    return GradesRepo.save(Grades);  // instance method
                })
                .orElseThrow(() -> new RuntimeException("Grade with ID " + gId + " not found!"));
    }

}
