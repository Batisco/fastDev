package com.batisco.fastDev.dal;

import com.batisco.fastDev.model.Furniture;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FurnitureRepository {

    public void addFurniture(Furniture furniture);

    public void updateFurniture(Furniture furniture);

    public boolean existsById(UUID furnitureId);

    public Optional<Furniture> getById(UUID furnitureId);

    public List<Furniture> getAllFurniture();

    public void flush();

}
