package com.batisco.fastDev.dal.impl;

import com.batisco.fastDev.dal.UserRepository;
import com.batisco.fastDev.model.User;

import org.jooq.DSLContext;
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
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;
    private final DSLContext dsl;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager,
                              DSLContext dsl) {
        this.entityManager = entityManager;
        this.dsl = dsl;
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
        List<User> users = entityManager.createNativeQuery(
                dsl.select().
                        from(table("users")).
                        limit(inline(pageable.getPageSize())).
                        offset(inline(pageable.getOffset())).
                        getSQL(),
                User.class
        ).getResultList();

        long count = ((Number)entityManager.createNativeQuery(
                dsl.selectCount().
                        from(table("users")).
                        getSQL()
        ).getSingleResult()).longValue();

        return new PageImpl<>(users, pageable, count);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

}
