package com.Uni.UNI_sys.unipackage;


import com.Uni.UNI_sys.dto.StudentDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServices {
    private  final  StudentRepo StudentRepo;

    public StudentServices(StudentRepo studentRepo) {
        StudentRepo = studentRepo;
    }
    //toDTO
    public StudentDto toDto(Student Student ){
        return  new StudentDto(Student.getCid(),Student.getName(), Student.getEmail(), Student.getPhone_Number(),Student.getAddress(), Student.getAcademic_Level(),Student.getFac());
    }

    //TOEntity
    public Student toEntity( StudentDto StudentDto){
        Student Student=new Student();
        Student.setCid(StudentDto.getCid());
        Student.setPhone_Number(StudentDto.getPhone_Number());
        Student.setName(StudentDto.getName());
        Student.setEmail(StudentDto.getEmail());
        Student.setAcademic_Level(StudentDto.getAcademic_Level());
        Student.setFac(StudentDto.getFac());;
        Student.setAddress(StudentDto.getAddress());
        return Student ;
    }


    public List<StudentDto> getAllStudents(){
        return StudentRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
    public List<StudentDto> getStudentByName(String name){
        return  StudentRepo.findAll().stream().filter(Student -> Student.getName().toLowerCase().contains(name.toLowerCase())).map(this::toDto).collect(Collectors.toList());
    }
    public List<StudentDto> getStudentByAcl(String academic_Level){
        return  StudentRepo.findAll().stream().filter(Student -> Student.getAcademic_Level().toLowerCase().contains(academic_Level.toLowerCase())).map(this::toDto).collect(Collectors.toList());
    }
    public List<StudentDto> getStudentByfac(Faculties fac){
        return StudentRepo.findAll().stream().filter(Student -> Student.getFac().equals(fac)).map(this::toDto).collect(Collectors.toList());
    }
    public StudentDto addStudent(StudentDto stu){
        Student Student=toEntity(stu);
        Student Studentsaved=StudentRepo.save(Student);
        return toDto(Studentsaved);
    }


    // @Transactional
  //  public void deleteStudent( String stu){
    //    StudentRepo.deleteByName(stu);
    //}

    @Transactional
    public StudentDto updateStudent(Integer cid, StudentDto updatedStudent) {
        Student student = StudentRepo.findById(cid)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setAddress(updatedStudent.getAddress());
        student.setPhone_Number(updatedStudent.getPhone_Number());
        student.setAcademic_Level(updatedStudent.getAcademic_Level());
        student.setFac(updatedStudent.getFac());

        StudentRepo.save(student);

        return toDto(student); // use your mapping method
    }

    @Transactional
    public void deleteStudentById(Integer cid){
        StudentRepo.deleteById(cid);
    }

}
