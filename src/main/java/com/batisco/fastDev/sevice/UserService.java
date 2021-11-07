package com.batisco.fastDev.sevice;

import com.batisco.fastDev.dal.UserRepository;
import com.batisco.fastDev.model.User;
import com.batisco.fastDev.model.exceptions.NotUniqueUserException;
import com.batisco.fastDev.model.exceptions.UnknownUserException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Page<User> getAll(Pageable pageable) {
        return userRepository.getByFilter(pageable);
    }

    @Transactional(readOnly = true)
    public User getById(UUID userId) {
        if(userId == null)
            throw new UnknownUserException("Unknown user with id = null");

        return userRepository.getById(userId).
                orElseThrow(() -> new UnknownUserException("Unknown user with id = " + userId));
    }

    @Transactional
    public User add(User userWithoutId) {
        try {
            userWithoutId.setId(UUID.randomUUID());

            userRepository.add(userWithoutId);
            userRepository.flush();

            return userWithoutId;
        } catch(DataIntegrityViolationException e) {
            throw new NotUniqueUserException("User with name " + userWithoutId.getName() + " already exists");
        }
    }

    @Transactional
    public User update(User user) {
        try {
            if(userRepository.existsById(user.getId())) {
                userRepository.update(user);
                userRepository.flush();
                return user;
            }
            throw new UnknownUserException("Unknown user with id = " + user.getId());
        } catch(DataIntegrityViolationException e) {
            throw new NotUniqueUserException("User with name " + user.getName() + " already exists");
        }
    }

}
