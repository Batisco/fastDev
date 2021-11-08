package com.batisco.fastDev.dal;

import com.batisco.fastDev.model.Furniture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FurnitureRepository {

    public void add(Furniture furniture);

    public void update(Furniture furniture);

    public boolean existsById(UUID furnitureId);

    public Optional<Furniture> getById(UUID furnitureId);

    public Page<Furniture> getByFilter(Pageable pageable,
                                       Optional<String> hasApartment,
                                       Optional<List<UUID>> exclude);

    public void flush();

}
