package com.batisco.fastDev.controller;

import com.batisco.fastDev.dto.furniture.FurnitureRequestDto;
import com.batisco.fastDev.dto.furniture.FurnitureResponseDto;
import com.batisco.fastDev.model.Furniture;
import com.batisco.fastDev.sevice.DtoMapperService;
import com.batisco.fastDev.sevice.FurnitureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/furnitures")
public class FurnitureController {

    private static final Logger logger = LoggerFactory.getLogger(FurnitureController.class.getName());


    private FurnitureService furnitureService;
    private DtoMapperService mapper;

    @Autowired
    public FurnitureController(FurnitureService furnitureService,
                               DtoMapperService mapper) {
        this.furnitureService = furnitureService;
        this.mapper = mapper;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FurnitureResponseDto>> getAll() {
        logger.info("Get all furnitures");
        List<Furniture> furnitures = furnitureService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.mapFurnituresToDto(furnitures));
    }

    @GetMapping("/getById")
    public ResponseEntity<FurnitureResponseDto> getById(@RequestParam("id") UUID furnitureId) {
        logger.info("Get furniture by id " + furnitureId);
        Furniture response = furnitureService.getById(furnitureId);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.mapFurnitureToDto(response));
    }

    @PostMapping("/add")
    public ResponseEntity<FurnitureResponseDto> addFurniture(@RequestBody FurnitureRequestDto dto) {
        logger.info("Add new furniture " + dto);
        Furniture furniture = mapper.mapToFurniture(dto);
        FurnitureResponseDto response = mapper.mapFurnitureToDto(furnitureService.add(furniture));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<FurnitureResponseDto> updateFurniture(@RequestBody FurnitureRequestDto dto) {
        logger.info("Update furniture " + dto);
        Furniture furniture = mapper.mapToFurniture(dto);
        FurnitureResponseDto response = mapper.mapFurnitureToDto(furnitureService.update(furniture));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
