package com.stahlferro.kusa.controllers.api;

import com.stahlferro.kusa.mappers.UserMapper;
import com.stahlferro.kusa.models.User;
import com.stahlferro.kusa.models.UserDto;
import com.stahlferro.kusa.repositories.UserRepository;
import com.stahlferro.kusa.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class APIUserController {

    private final UserService userService;

    @Autowired
    private UserMapper userMapper;

    public APIUserController(UserService userService) { this.userService = userService; }

    @GetMapping("/all")
    private List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") long id) {
        User user = userService.getUserByIdOrError(id);
        return user;
    }

    @GetMapping("/{id}/description")
    public String getUserStringByid(@PathVariable("id") long id) {
        User user = userService.getUserByIdOrError(id);
        return user.toString();
    }

    @GetMapping()
    public List<User> getUsersByName(@RequestParam("name") String name) {
        return userService.getUsersByName(name);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/nextid")
    public long getNextId() { return userService.getNextId(); }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> partialUpdateUser(@PathVariable("id") long id, @RequestBody UserDto userDto) {
        User user = userService.getUserByIdOrError(id);
        log.info(String.format("User: %s\nUserDto: %s", user.toString(), userDto.toString()));
        userService.update(user, userDto);
        return ResponseEntity.ok(user.toString() + " updated!");
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.getUserByIdOrError(id);
        String deletionMsg = userService.delete(user);
        return deletionMsg;
    }
}
