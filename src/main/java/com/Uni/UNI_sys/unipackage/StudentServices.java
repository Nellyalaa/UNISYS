package com.Uni.UNI_sys.unipackage;


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


    public List<Student> getAllStudents(){
        return StudentRepo.findAll();
    }
    public List<Student> getStudentByName(String name){
        return  StudentRepo.findAll().stream().filter(Student -> Student.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
    public List<Student> getStudentByAcl(String academic_Level){
        return  StudentRepo.findAll().stream().filter(Student -> Student.getAcademic_Level().toLowerCase().contains(academic_Level.toLowerCase())).collect(Collectors.toList());
    }
    public List<Student> getStudentByfac(Faculties fac){
        return StudentRepo.findAll().stream().filter(Student -> Student.getFac().equals(fac)).collect(Collectors.toList());
    }
    public Student addStudent(Student stu){
        return StudentRepo.save(stu);
    }


    @Transactional
    public void deleteStudent( String stu){
        StudentRepo.deleteByName(stu);
    }

    @Transactional
    public Student updateEmailById(Integer cid, String newEmail) {
        return StudentRepo.findById(cid)  // instance method
                .map(student -> {
                    student.setEmail(newEmail.trim());
                    return StudentRepo.save(student);  // instance method
                })
                .orElseThrow(() -> new RuntimeException("Student with ID " + cid + " not found!"));
    }

    @Transactional
    public Student updateaddressById(Integer cid, String address) {
        return StudentRepo.findById(cid)  // instance method
                .map(student -> {
                    student.setAddress(address.trim());
                    return StudentRepo.save(student);  // instance method
                })
                .orElseThrow(() -> new RuntimeException("Student with ID " + cid + " not found!"));
    }

    @Transactional
    public Student updateByPhone(Integer cid, String phone) {
        return StudentRepo.findById(cid)  // instance method
                .map(student -> {
                    student.setPhone_Number(phone.trim());
                    return StudentRepo.save(student);  // instance method
                })
                .orElseThrow(() -> new RuntimeException("Student with ID " + cid + " not found!"));
    }

}
