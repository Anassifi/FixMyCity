package com.anassifi.fixmycity.services;

import com.anassifi.fixmycity.repositories.UserRepository;
import com.anassifi.fixmycity.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService implements Services<User> {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public User findByUsername(String login) {
        try {
            return userRepository.findByLogin(login).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean delete(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User login(User user) {
        return userRepository.findByLogin(user.getLogin()).get();
    }

    public User resetPassword(User user) {
        return userRepository.findByLogin(user.getLogin()).get();
    }
}
