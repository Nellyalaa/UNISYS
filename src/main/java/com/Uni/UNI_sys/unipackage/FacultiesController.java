package com.Uni.UNI_sys.unipackage;

import com.Uni.UNI_sys.dto.FacultiesDto;
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
    public List<FacultiesDto> getAllFaculties(

        @RequestParam(required = false) String name

        ){
        if (name != null){
            return FacultiesServices.getFaculties(name);
        }
        return FacultiesServices.getAllFaculties();

    }

    @PostMapping
    public ResponseEntity<FacultiesDto> addFac(@RequestBody FacultiesDto FacultiesDto){
        FacultiesDto newFac= FacultiesServices.addFaculty(FacultiesDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newFac);
    }

    @DeleteMapping
    public List<FacultiesDto> deletefacDto(
            @RequestParam (required = false) String name){

            if (name != null){
                FacultiesServices.deleteFac(name);
                return FacultiesServices.getAllFaculties();

    }
            return FacultiesServices.getAllFaculties();


    }



}

