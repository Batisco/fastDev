package com.batisco.fastDev.dal.impl;

import com.batisco.fastDev.dal.ApartmentRepository;
import com.batisco.fastDev.model.Apartment;

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
public class ApartmentRepositoryImpl implements ApartmentRepository {

    private final EntityManager entityManager;
    private final DSLContext dsl;

    @Autowired
    public ApartmentRepositoryImpl(EntityManager entityManager,
                                   DSLContext dsl) {
        this.entityManager = entityManager;
        this.dsl = dsl;
    }

    @Override
    public void add(Apartment apartment) {
        entityManager.persist(apartment);
    }

    @Override
    public void update(Apartment apartment) {
        entityManager.merge(apartment);
    }

    @Override
    public boolean existsById(UUID id) {
        return id != null && entityManager.find(Apartment.class, id) != null;
    }

    @Override
    public Optional<Apartment> getById(UUID id) {
        return Optional.ofNullable(entityManager.find(Apartment.class, id));
    }

    @Override
    public Optional<Apartment> getByFurnitureId(UUID furnitureId) {

        return Optional.empty();
    }

    @Override
    public Page<Apartment> getByFilter(Pageable pageable, Optional<String> hasActualOrder, Optional<List<UUID>> exclude) {
        final Condition condition = hasActualOrder.map(s ->
                trueCondition().
                        and(field("orders_to_apartments.a_id").isNull()).
                        or(field("apartments.apartment_id").in(
                                select(DSL.field("apartments.apartment_id")).
                                        from("apartments").
                                        join("orders_to_apartments").
                                            on(field("orders_to_apartments.a_id").
                                                    eq(field("apartments.apartment_id"))).
                                        join("orders").
                                            on(field("orders.order_id").
                                                    eq(field("orders_to_apartments.o_id"))).
                                            and(field("orders.state").eq(inline("COMPLETED")).
                                                    or(field("orders.state").eq(inline("CANCEL"))))
                        ))
        ).orElse(DSL.trueCondition());

        Condition resultCondition = exclude.map(list ->
                condition.and(
                        field("apartments.apartment_id").notIn(list.stream().map(DSL::inline).toList())
                )
        ).orElse(condition);

        List<Apartment> apartments = entityManager.createNativeQuery(
                dsl.select(table("apartments").fields()).
                        from("apartments").
                        leftJoin("orders_to_apartments").
                            on(field("orders_to_apartments.a_id").eq(field("apartments.apartment_id"))).
                        where(resultCondition).
                        orderBy(
                                field("apartments.address"),
                                field("apartments.number"),
                                field("apartments.apartment_id")
                        ).
                        limit(inline(pageable.getPageSize())).
                        offset(inline(pageable.getOffset())).
                        getSQL(),
                Apartment.class
        ).getResultList();

        long count = ((Number) entityManager.createNativeQuery(
                dsl.select(countDistinct(field("apartments.apartment_id"))).
                        from("apartments").
                        leftJoin("orders_to_apartments").
                            on(field("orders_to_apartments.a_id").eq(field("apartments.apartment_id"))).
                        where(resultCondition).
                        getSQL()
        ).getSingleResult()).longValue();

        return new PageImpl<>(apartments, pageable, count);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

}
