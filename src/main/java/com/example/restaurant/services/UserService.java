package com.example.restaurant.services;

import com.example.restaurant.models.ChangeInfoUserForm;
import com.example.restaurant.models.User;
import com.example.restaurant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException(
                    String.format("Username '%s' already exists", user.getUsername()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateByUsername(String username, ChangeInfoUserForm form) {
        User foundedUser = userRepository.findUserByUsername(username).orElseThrow(
                () -> new IllegalArgumentException(
                        String.format("The username %s does not exist", username)));

        if (form.getAddress() != null && !form.getAddress().isBlank()) {
            foundedUser.setAddress(form.getAddress());
        }

        if (form.getEmail() != null && !form.getEmail().isBlank()) {
            foundedUser.setEmail(form.getEmail());
        }

        if (form.getMobile() != null && !form.getMobile().isBlank()) {
            foundedUser.setMobile(form.getMobile());
        }

        if (form.getFullName() != null && !form.getFullName().isBlank()) {
            foundedUser.setFullName(form.getFullName());
        }

        return userRepository.save(foundedUser);
    }

    public User changePassword(String username, String oldPassword, String newPassword) {
        User foundedUser = userRepository.findUserByUsername(username).orElseThrow(
                () -> new IllegalArgumentException(
                        String.format("The username %s does not exist", username)));

        if (passwordEncoder.matches(oldPassword, foundedUser.getPassword())) {
            foundedUser.setPassword(passwordEncoder.encode(newPassword));
            foundedUser.setIsUpdatedPassword((byte) 1);
            userRepository.save(foundedUser);
        }
        return foundedUser;
    }

    public User deleteUserByUsername(String username) {
        User foundedUser = userRepository.findUserByUsername(username).orElseThrow(
                () -> new IllegalArgumentException(
                        String.format("Username %s does not exists", username)));
        userRepository.deleteById(foundedUser.getId());
        return foundedUser;
    }
}
