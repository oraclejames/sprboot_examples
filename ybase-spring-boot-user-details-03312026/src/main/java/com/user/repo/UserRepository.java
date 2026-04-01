package com.user.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.User;

@Repository               // "ఇది Data layer" అని Spring కి చెప్తుంది
public interface UserRepository
    extends JpaRepository<User, Long> {
    // ↑ User = Entity type, Long = ID type

    // ఇవి Spring automatic గా implement చేస్తుంది!
    // save(user) → INSERT INTO users...
    // findAll() → SELECT * FROM users
    // findById(1L) → SELECT * FROM users WHERE id=1
    // deleteById(1L) → DELETE FROM users WHERE id=1

    // Custom queries — method name వల్ల SQL auto తయారవుతుంది!
    Optional<User> findByEmail(String email);
    // ↑ Spring దీన్ని: SELECT * FROM users WHERE email=? గా convert చేస్తుంది

    List<User> findByNameContaining(String name);
    // ↑ Spring దీన్ని: SELECT * FROM users WHERE name LIKE '%name%' చేస్తుంది
}
