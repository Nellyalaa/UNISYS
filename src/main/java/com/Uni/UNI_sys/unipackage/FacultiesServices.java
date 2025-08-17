package com.Uni.UNI_sys.unipackage;

import com.Uni.UNI_sys.dto.FacultiesDto;
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

    //toDTO
    public FacultiesDto toDto(Faculties Faculties ){
        return new FacultiesDto(Faculties.getId(),Faculties.getName() );
    }

    //toEntity
    public Faculties toEntity(FacultiesDto dto) {
        Faculties fac = new Faculties();
        fac.setId(dto.getId());
        fac.setName(dto.getName());
        return fac;
    }


    public List<FacultiesDto> getAllFaculties(){
        return facultyRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
    public List<FacultiesDto> getFaculties(String searchName){
        return facultyRepo.findAll().stream().filter(Faculties -> Faculties.getName().toLowerCase().contains(searchName.toLowerCase())).map(this::toDto).collect(Collectors.toList());
    }

    public FacultiesDto addFaculty(FacultiesDto facdto){
        Faculties fac =toEntity(facdto);
        Faculties saved = facultyRepo.save(fac);
       return toDto(saved);

    }

    @Transactional
    public void deleteFac( String Fac){
        facultyRepo.deleteByName(Fac);
    }

}

