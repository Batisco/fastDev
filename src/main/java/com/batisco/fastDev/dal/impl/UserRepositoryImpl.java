package com.batisco.fastDev.dal.impl;

import com.batisco.fastDev.dal.UserRepository;
import com.batisco.fastDev.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public boolean existsById(UUID userId) {
        return userId != null && entityManager.find(User.class, userId) != null;
    }

    @Override
    public Optional<User> getById(UUID userId) {
        return Optional.ofNullable(entityManager.find(User.class, userId));
    }

    @Override
    public Page<User> getByFilter(Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> queryUser = cb.createQuery(User.class);
        Root<User> user = queryUser.from(User.class);
        queryUser.orderBy(cb.asc(user.get("name")));

        TypedQuery<User> tq = entityManager.createQuery(queryUser).
                setFirstResult((int)pageable.getOffset()).
                setMaxResults(pageable.getPageSize());

        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        queryCount.select(cb.count(queryCount.from(User.class)));
        Long count = entityManager.createQuery(queryCount).getSingleResult();

        return new PageImpl<>(tq.getResultList(), pageable, count);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

}
