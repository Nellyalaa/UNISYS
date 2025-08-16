package com.Uni.UNI_sys.unipackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("api/v1/fac")
public class FacultiesController {

    private final FacultiesServices FacultiesServices;

    @Autowired

    public FacultiesController(FacultiesServices facultiesServices) {
        FacultiesServices = facultiesServices;
    }


    @GetMapping
    public List<Faculties> getAllFaculties(

        @RequestParam(required = false) String name

        ){
        if (name != null){
            return FacultiesServices.getFaculties(name);
        }
        return FacultiesServices.getAllFaculties();

    }

    @PostMapping
    public ResponseEntity<Faculties> addFac(@RequestBody Faculties Faculties){
        Faculties newFac= FacultiesServices.addFaculty(Faculties);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newFac);
    }

    @DeleteMapping
    public List<Faculties> deletefac(
            @RequestParam (required = false) String name){

            if (name != null){
                FacultiesServices.deleteFac(name);
                return FacultiesServices.getAllFaculties();

    }
            return FacultiesServices.getAllFaculties();


    }



}

