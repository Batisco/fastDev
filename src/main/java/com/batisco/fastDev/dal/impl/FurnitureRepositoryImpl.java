package com.batisco.fastDev.dal.impl;

import com.batisco.fastDev.dal.FurnitureRepository;
import com.batisco.fastDev.model.Furniture;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.*;

@Repository
public class FurnitureRepositoryImpl implements FurnitureRepository {

    private final EntityManager entityManager;
    private final DSLContext dsl;

    @Autowired
    public FurnitureRepositoryImpl(EntityManager entityManager,
                                   DSLContext dsl) {
        this.entityManager = entityManager;
        this.dsl = dsl;
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
        final Condition condition = hasApartment.map(s ->
                trueCondition().
                        and(field("furnitures.apartment_id").isNull())
        ).orElse(trueCondition());

        Condition resultCondition = exclude.map(list ->
                condition.and(field("furnitures.furniture_id").notIn(list.stream().map(DSL::inline).toList()))
        ).orElse(condition);

        List<Furniture> furnitures = entityManager.createNativeQuery(
                dsl.select().
                        from(table("furnitures")).
                        leftJoin(table("apartments")).
                            on(field("furnitures.apartment_id").eq(field("apartments.apartment_id"))).
                        where(resultCondition).
                        orderBy(
                                field("furnitures.type"),
                                field("furnitures.furniture_id")
                        ).
                        limit(inline(pageable.getPageSize())).
                        offset(inline(pageable.getOffset())).
                        getSQL(),
                Furniture.class
        ).getResultList();

        long count = ((Number) entityManager.createNativeQuery(
                dsl.select(countDistinct(field("furnitures.furniture_id"))).
                        from(table("furnitures")).
                        leftJoin(table("apartments")).
                            on(field("furnitures.apartment_id").eq(field("apartments.apartment_id"))).
                        where(resultCondition).
                        getSQL()
        ).getSingleResult()).longValue();

        return new PageImpl<>(furnitures, pageable, count);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

}
