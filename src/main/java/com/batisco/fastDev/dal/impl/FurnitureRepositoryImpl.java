package com.batisco.fastDev.dal.impl;

import com.batisco.fastDev.dal.FurnitureRepository;
import com.batisco.fastDev.model.Furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public class FurnitureRepositoryImpl implements FurnitureRepository {

    private EntityManager entityManager;

    @Autowired
    public FurnitureRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Furniture furniture) {
        entityManager.persist(furniture);
    }

    @Override
    public void update(Furniture furniture) {
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
    public Page<Furniture> getByFilter(Pageable pageable,
                                       Optional<String> hasApartment,
                                       Optional<List<UUID>> exclude) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Furniture> queryFurniture = cb.createQuery(Furniture.class);
        Root<Furniture> furniture = queryFurniture.from(Furniture.class);
        queryFurniture.
                where(cb.and(
                        Stream.of(
                                hasApartment.map(s -> cb.isNull(furniture.get("apartment"))),
                                exclude.map(list -> cb.not(furniture.get("id").in(list)))
                        ).filter(Optional::isPresent).
                                map(Optional::get).
                                toArray(Predicate[]::new)
                )).
                orderBy(cb.asc(furniture.get("type")), cb.asc(furniture.get("id")));

        TypedQuery<Furniture> tq = entityManager.createQuery(queryFurniture).
                setFirstResult((int)pageable.getOffset()).
                setMaxResults(pageable.getPageSize());

        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        queryCount.select(cb.count(queryCount.from(Furniture.class)));
        Long count = entityManager.createQuery(queryCount).getSingleResult();

        return new PageImpl<>(tq.getResultList(), pageable, count);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

}
