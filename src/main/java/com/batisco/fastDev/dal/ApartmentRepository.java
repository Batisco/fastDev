package com.batisco.fastDev.dal;

import com.batisco.fastDev.model.Apartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApartmentRepository {

    public void add(Apartment apartment);

    public void update(Apartment apartment);

    public boolean existsById(UUID id);

    public Optional<Apartment> getById(UUID id);

    public Optional<Apartment> getByFurnitureId(UUID furnitureId);

    public Page<Apartment> getByFilter(Pageable pageable,
                                       Optional<String> hasActualOrder,
                                       Optional<List<UUID>> exclude);

    public void flush();

}
