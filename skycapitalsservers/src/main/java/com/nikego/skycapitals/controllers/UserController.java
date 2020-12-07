package com.nikego.skycapitals.controllers;

import com.nikego.skycapitals.models.User;
import com.nikego.skycapitals.services.UserService;
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

    /*успешный логин =вся инфа*/
    @GetMapping(value = "/authorization/{ostoffice}/{password}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> authorization(@PathVariable(name="ostoffice") String ostoffice,@PathVariable(name="password") Integer password) {
        final User user = userService.accountVerification(ostoffice, password);
        return user!=null
                ? ResponseEntity.status(HttpStatus.OK).body(user)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new AbstractMap.SimpleEntry<>("СтатусОперации:", "Ошибка!"));
    }

    @GetMapping(value = "/users", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<User>> readAll() {
        final List<User> users = userService.readAll();

        return users != null && !users.isEmpty()
                ? ResponseEntity.status(HttpStatus.OK).body(users)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody User user) {
        try {
            userService.create(user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(new AbstractMap.SimpleEntry<>("ПричинаОтказа:", "Данный аккаунт уже существует!"));
        }
    }

    @GetMapping(value = "/users/{userId}", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> read(@PathVariable int userId) {
        final User user = userService.read(userId);
        return user != null
                ? ResponseEntity.status(HttpStatus.OK).body(new AbstractMap.SimpleEntry<>("Успешно:", user))
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/users/update/{userId}", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> update(@RequestBody User user, @PathVariable int userId) {
        userService.update(user, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Успешно!");
    }

    @PostMapping(value = "/delete/{userId}", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable int userId) {
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).body("Успешно удалено!");
    }
}
