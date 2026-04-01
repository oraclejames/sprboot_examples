package com.user.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController            // @Controller + @ResponseBody = JSON auto return
@RequestMapping("/api/users") // Base URL path
@RequiredArgsConstructor
public class UserController {

	private final UserService service; // ✅ FIXED

    // POST /api/users — CREATE
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        // @RequestBody = Postman లో Body లో పంపిన JSON → User object
        User saved = service.createUser(user);
        return ResponseEntity.status(201).body(saved); // 201 Created
    }

    // GET /api/users — READ ALL
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(service.getAllUsers()); // 200 OK
    }

    // GET /api/users/1 — READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        // @PathVariable = URL లో {id} ని Java variable కి bind చేస్తుంది
        return service.getUserById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build()); // 404
    }

    // PUT /api/users/1 — UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable Long id,
            @RequestBody User user) {
        return ResponseEntity.ok(service.updateUser(id, user));
    }

    // DELETE /api/users/1 — DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
    public UserController(UserService service) {
        this.service = service;
    }
}