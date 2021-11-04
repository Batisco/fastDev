package com.batisco.fastDev.sevice;

import com.batisco.fastDev.dal.ApartmentRepository;

import com.batisco.fastDev.model.Apartment;
import com.batisco.fastDev.model.exceptions.UnknownApartmentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public List<Apartment> getAll() {
        return apartmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Apartment getById(UUID apartmentId) {
        if(apartmentId == null)
            throw new UnknownApartmentException("Unknown apartment with id=" + apartmentId);

        return Optional.ofNullable(apartmentRepository.getById(apartmentId)).
                orElseThrow(() -> new UnknownApartmentException("Unknown apartment with id=" + apartmentId));
    }

    @Transactional(readOnly = true)
    public Optional<Apartment> getByFurnitureId(UUID furnitureId) {
        return Optional.empty();
    }

    @Transactional
    public Apartment add(Apartment apartmentWithoutDto) {
        try {
            apartmentWithoutDto.setId(UUID.randomUUID());

            apartmentRepository.saveAndFlush(apartmentWithoutDto);

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
                apartmentRepository.saveAndFlush(apartment);
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
