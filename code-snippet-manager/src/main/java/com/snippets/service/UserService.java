package com.snippets.service;

import com.snippets.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}