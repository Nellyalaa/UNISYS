package com.Uni.UNI_sys.unipackage;

import com.Uni.UNI_sys.dto.GradeDto;
import com.Uni.UNI_sys.dto.coursesDto;
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

    //toDTO
    public GradeDto toDto(Grades Grades ){
        return new GradeDto(Grades.getGrade(),Grades.getCourses(),Grades.getStudent());
    }

    //toEntity
    public Grades toEntity(GradeDto dto) {
        Grades Grades = new Grades();
        Grades.setGrade(dto.getGrade());
        Grades.setCourses(dto.getCourses());
        Grades.setStudent(dto.getStudent());
        return Grades;
    }
    //get
    public List<GradeDto> getallGrades(){
        return GradesRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
    public List<GradeDto> getGrade(Integer grade) {
        return GradesRepo.findAll()
                .stream()
                .filter(g -> g.getGrade() == grade)
                .map(this::toDto).collect(Collectors.toList());
    }

    public List<GradeDto> getGradesBystudentId(Student stu){
        return GradesRepo.findAll().stream().filter(Grades -> Grades.getStudent().equals(stu)).map(this::toDto).collect(Collectors.toList());
    }
    public List<GradeDto> getGradesByCourseId(courses cour){
        return GradesRepo.findAll().stream().filter(Grades -> Grades.getCourses().equals(cour)).map(this::toDto).collect(Collectors.toList());
    }
    //add
    public GradeDto addGrade( GradeDto GradeDto){
        Grades grade =toEntity(GradeDto);
        Grades saved = GradesRepo.save(grade);
        return toDto(saved);
    }
    //delete
   public void Deletegrade(Integer Id){
         GradesRepo.deleteById(Id);
   }
    //update
    public GradeDto updateGrade(Integer gId, Integer grade){

        return GradesRepo.findById(gId)  // instance method
                .map(Grades -> {
                    Grades.setGrade(grade);
                    Grades updatedGrade = GradesRepo.save(Grades);
                    return toDto(updatedGrade);  // instance method
                })
                .orElseThrow(() -> new RuntimeException("Grade with ID " + gId + " not found!"));
    }

}
