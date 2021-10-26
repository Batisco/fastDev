package com.batisco.fastDev.dal;

import com.batisco.fastDev.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    public void addUser(User user);

    public void updateUser(User user);

    public Optional<User> getById(UUID userId);

    public List<User> getAllUser();

    public void flush();

}
