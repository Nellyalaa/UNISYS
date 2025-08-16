package com.Uni.UNI_sys.unipackage;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultiesServices {

    private FacultyRepo facultyRepo;

    public FacultiesServices(FacultyRepo facultyRepo) {
        this.facultyRepo = facultyRepo;
    }
    public List<Faculties> getAllFaculties(){
        return facultyRepo.findAll();
    }
    public List<Faculties> getFaculties(String searchName){
        return facultyRepo.findAll().stream().filter(Faculties -> Faculties.getName().toLowerCase().contains(searchName.toLowerCase())).collect(Collectors.toList());
    }

    public Faculties addFaculty(Faculties fac){
       return facultyRepo.save(fac);

    }

    @Transactional
    public void deleteFac( String Fac){
        facultyRepo.deleteByName(Fac);
    }

}

