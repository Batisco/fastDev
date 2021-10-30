package com.batisco.fastDev.dal;

import com.batisco.fastDev.model.Apartment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {

}
