package com.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service                   // Spring container లో Bean గా register అవుతుంది
@RequiredArgsConstructor   // Lombok: constructor injection automatic
public class UserService {

    private   final UserRepository userRepository;
    // ↑ final = Dependency Injection. Spring automatic గా inject చేస్తుంది

    // CREATE — కొత్త user save చేయడం
    public User createUser(User user) {
        // Business Logic: email already ఉందా check
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }
        return userRepository.save(user);
    }

    // READ — అందరు users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // READ — ఒక user
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // UPDATE — user update చేయడం
    public User updateUser(Long id, User updatedUser) {
        User existing = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found!"));
        existing.setName(updatedUser.getName());
        existing.setEmail(updatedUser.getEmail());
        return userRepository.save(existing);
    }

    // DELETE — user తొలగించడం
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}