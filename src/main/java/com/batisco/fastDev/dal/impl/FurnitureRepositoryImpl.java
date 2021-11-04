package com.batisco.fastDev.dal.impl;

import com.batisco.fastDev.dal.FurnitureRepository;
import com.batisco.fastDev.model.Furniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FurnitureRepositoryImpl implements FurnitureRepository {

    private EntityManager entityManager;

    @Autowired
    public FurnitureRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addFurniture(Furniture furniture) {
        entityManager.persist(furniture);
    }

    @Override
    public void updateFurniture(Furniture furniture) {
        entityManager.merge(furniture);
    }

    @Override
    public boolean existsById(UUID furnitureId) {
        return furnitureId != null && entityManager.find(Furniture.class, furnitureId) != null;
    }

    @Override
    public Optional<Furniture> getById(UUID furnitureId) {
        return Optional.ofNullable(entityManager.find(Furniture.class, furnitureId));
    }

    @Override
    public List<Furniture> getAllFurniture() {
        return entityManager.createNativeQuery(
                "select * from furnitures;",
                Furniture.class
        ).getResultList();
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

}
