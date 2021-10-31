package com.batisco.fastDev.controller;

import com.batisco.fastDev.dto.AddedApartmentDto;
import com.batisco.fastDev.dto.ApartmentDto;
import com.batisco.fastDev.model.Apartment;
import com.batisco.fastDev.sevice.ApartmentService;
import com.batisco.fastDev.sevice.DtoMapperService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    private static final Logger logger = LoggerFactory.getLogger(ApartmentController.class.getName());


    private final ApartmentService apartmentService;
    private final DtoMapperService mapper;

    @Autowired
    public ApartmentController(ApartmentService apartmentService,
                               DtoMapperService mapper) {
        this.apartmentService = apartmentService;
        this.mapper = mapper;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ApartmentDto>> getAll() {
        logger.info("Get all apartments");
        List<ApartmentDto> response = mapper.mapApartmentsToDto(apartmentService.getAll());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getById")
    public ResponseEntity<ApartmentDto> getById(@RequestParam("id") UUID apartmentId) {
        logger.info("Get apartment by id " + apartmentId);
        ApartmentDto response = mapper.mapApartmentToDto(apartmentService.getById(apartmentId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<ApartmentDto> addApartment(@RequestBody AddedApartmentDto dto) {
        logger.info("Add new apartment " + dto);
        Apartment apartment = apartmentService.add(mapper.mapToApartment(dto));
        ApartmentDto response = mapper.mapApartmentToDto(apartment);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ApartmentDto> updateApartment(@RequestBody ApartmentDto dto) {
        logger.info("Update apartment " + dto);
        Apartment apartment = apartmentService.update(mapper.mapToApartment(dto));
        ApartmentDto response = mapper.mapApartmentToDto(apartment);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
