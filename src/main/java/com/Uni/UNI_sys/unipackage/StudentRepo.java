package com.Uni.UNI_sys.unipackage;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Integer> {

   Optional <Student> findByName(String name);



   // Optional<Student> findByFac(Faculties fac);
 //   Optional<Student> findByAcl(String academic_Level);

    void deleteByName(String name);
}
