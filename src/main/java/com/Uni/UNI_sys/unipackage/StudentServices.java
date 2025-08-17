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
        return  new StudentDto(Student.getCid(),Student.getName(), Student.getEmail(), Student.getPhone_Number(), Student.getAcademic_Level(),Student.getFac());
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


    @Transactional
    public void deleteStudent( String stu){
        StudentRepo.deleteByName(stu);
    }

    @Transactional
    public StudentDto updateEmailById(Integer cid, String newEmail) {
        return StudentRepo.findById(cid)  // instance method
                .map(student -> {
                    student.setEmail(newEmail.trim());
                    Student updatedStudent = StudentRepo.save(student);
                    return toDto(updatedStudent);
                })
                .orElseThrow(() -> new RuntimeException("Student with ID " + cid + " not found!"));
    }

    @Transactional
    public StudentDto updateaddressById(Integer cid, String address) {
        return StudentRepo.findById(cid)  // instance method
                .map(student -> {
                    student.setAddress(address.trim());
                    Student updatedStudent = StudentRepo.save(student);
                    return toDto(updatedStudent);

                })
                .orElseThrow(() -> new RuntimeException("Student with ID " + cid + " not found!"));
    }

    @Transactional
    public StudentDto updateByPhone(Integer cid, String phone) {
        return StudentRepo.findById(cid)  // instance method
                .map(student -> {
                    student.setPhone_Number(phone.trim());
                    Student updatedStudent = StudentRepo.save(student);
                    return toDto(updatedStudent);
                })
                .orElseThrow(() -> new RuntimeException("Student with ID " + cid + " not found!"));
    }

}
