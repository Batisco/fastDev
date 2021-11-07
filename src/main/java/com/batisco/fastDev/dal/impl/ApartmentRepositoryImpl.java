package com.batisco.fastDev.dal.impl;

import com.batisco.fastDev.dal.ApartmentRepository;
import com.batisco.fastDev.model.Apartment;
import com.batisco.fastDev.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public class ApartmentRepositoryImpl implements ApartmentRepository {

    private EntityManager entityManager;

    @Autowired
    public ApartmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Apartment> queryApartment = cb.createQuery(Apartment.class);
        Root<Apartment> apartment = queryApartment.from(Apartment.class);
        Root<Order> order = queryApartment.from(Order.class);
        queryApartment.
                select(apartment).
                where(cb.and(
                        Stream.of(
                                hasActualOrder.map(s -> cb.and(
                                        cb.not(apartment.<UUID>get("id").in(order.get("apartments").<UUID>get("id"))),
                                        cb.or(
                                                cb.equal(order.get("state"), "BOOKED"),
                                                cb.equal(order.get("state"), "USED_BY")
                                        )
                                )),
                                exclude.map(list -> cb.not(apartment.get("id").in(list)))
                        ).filter(Optional::isPresent).
                                map(Optional::get).
                                toArray(Predicate[]::new)
                )).
                orderBy(cb.asc(apartment.get("address")), cb.asc(apartment.get("number")), cb.asc(apartment.get("id")));

        TypedQuery<Apartment> tq = entityManager.createQuery(queryApartment).
                setFirstResult((int)pageable.getOffset()).
                setMaxResults(pageable.getPageSize());

        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        queryCount.select(cb.count(queryCount.from(Apartment.class)));
        long count = entityManager.createQuery(queryCount).getSingleResult();

        return new PageImpl<>(tq.getResultList(), pageable, count);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

}
