package com.batisco.fastDev.dal.impl;

import com.batisco.fastDev.dal.UserRepository;
import com.batisco.fastDev.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public Optional<User> getById(UUID userId) {
        return Optional.ofNullable(entityManager.find(User.class, userId));
    }

    @Override
    public List<User> getAllUser() {
        return entityManager.createNativeQuery(
                "select * from users",
                User.class
        ).getResultList();
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

}
