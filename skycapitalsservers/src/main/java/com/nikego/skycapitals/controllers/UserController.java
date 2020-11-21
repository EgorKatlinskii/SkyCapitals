package com.nikego.skycapitals.controllers;

import com.nikego.skycapitals.models.User;
import com.nikego.skycapitals.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<User>> readAll() {
        final List<User> users = userService.readAll();

        return users != null && !users.isEmpty()
                ? ResponseEntity.status(HttpStatus.OK).body(users)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(users);
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody User user) {
        try {
            userService.create(user);
            return ResponseEntity.status(HttpStatus.OK).body(new AbstractMap.SimpleEntry<>("id", user.getUserId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AbstractMap.SimpleEntry<>("ПричинаОтказа:","Данный аккаунт уже существует!"));
        }
    }

    @GetMapping(value = "/users/{userId}",  consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> read(@RequestBody int id) {
        final User user = userService.read(id);
        return user != null
                ? ResponseEntity.status(HttpStatus.OK).body("Успешно получено! " + user)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/users/update/{userId}", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> update(@RequestBody User user, @PathVariable int userId) {
        userService.update(user, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Успешно!");
    }

    @PostMapping(value = "/delete/{userId}",  consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestBody int id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Успешно удалено!");
    }
}
