package com.batisco.fastDev.dal;

import com.batisco.fastDev.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    public void add(User user);

    public void update(User user);

    public boolean existsById(UUID userId);

    public Optional<User> getById(UUID userId);

    public Page<User> getByFilter(Pageable pageable);

    public void flush();

}
