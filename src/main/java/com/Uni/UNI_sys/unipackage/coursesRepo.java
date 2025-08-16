package com.Uni.UNI_sys.unipackage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface coursesRepo extends JpaRepository<courses,Integer> {

    Optional<Faculties> findByName(String name);
    void deleteByName(String name);
}
