package com.batisco.fastDev.sevice;

import com.batisco.fastDev.dal.ApartmentRepository;

import com.batisco.fastDev.model.Apartment;
import com.batisco.fastDev.model.exceptions.UnknownApartmentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Transactional(readOnly = true)
    public Page<Apartment> getAll(Pageable pageable,
                                  Optional<String> hasOwner,
                                  Optional<List<UUID>> exclude) {
        return apartmentRepository.getByFilter(pageable, hasOwner, exclude);
    }

    @Transactional(readOnly = true)
    public Apartment getById(UUID apartmentId) {
        if(apartmentId == null)
            throw new UnknownApartmentException("Unknown apartment with id=" + apartmentId);

        return apartmentRepository.getById(apartmentId).
                orElseThrow(() -> new UnknownApartmentException("Unknown apartment with id=" + apartmentId));
    }

    @Transactional(readOnly = true)
    public Optional<Apartment> getByFurnitureId(UUID furnitureId) {
        return apartmentRepository.getByFurnitureId(furnitureId);
    }

    @Transactional
    public Apartment add(Apartment apartmentWithoutDto) {
        try {
            apartmentWithoutDto.setId(UUID.randomUUID());

            apartmentRepository.add(apartmentWithoutDto);
            apartmentWithoutDto.getFurnitures().forEach(furniture -> furniture.setApartment(apartmentWithoutDto));
            apartmentRepository.flush();

            return apartmentWithoutDto;
        } catch(DataIntegrityViolationException e) {
            throw new UnknownApartmentException(
                    "Apartment with address " + apartmentWithoutDto.getAddress() +
                            " and number " + apartmentWithoutDto.getNumber() +
                            " already exists"
            );
        }
    }

    @Transactional
    public Apartment update(Apartment apartment) {
        try {
            if(apartment.getId() != null && apartmentRepository.existsById(apartment.getId())) {
                apartmentRepository.update(apartment);
                apartmentRepository.flush();

                return apartment;
            }
            throw new UnknownApartmentException("Unknown apartment with id=" + apartment.getId());
        } catch(DataIntegrityViolationException e) {
            throw new UnknownApartmentException(
                    "Apartment with address " + apartment.getAddress() +
                            " and number " + apartment.getNumber() +
                            " already exists"
            );
        }
    }

}
