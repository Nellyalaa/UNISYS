package com.Uni.UNI_sys.unipackage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradesRepo extends JpaRepository<Grades,Integer> {


    void deleteByStudent_cid(Student student);
}
