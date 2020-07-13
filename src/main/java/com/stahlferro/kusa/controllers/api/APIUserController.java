package com.stahlferro.kusa.controllers.api;

import com.stahlferro.kusa.mappers.UserMapper;
import com.stahlferro.kusa.models.User;
import com.stahlferro.kusa.models.UserDto;
import com.stahlferro.kusa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class APIUserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserMapper userMapper;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User Id: " + id));
        return user;
//        Optional<User> user = userRepository.findById(id);
//        if (user.isPresent()) {
//            return user.get();
//        }
//        else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//        }
    }

    @GetMapping("/{id}/description")
    public String getUserStringByid(@PathVariable("id") long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User Id: " + id));
        return user.toString();
    }

    @GetMapping()
    public List<User> getUsersByName(@RequestParam("name") String name) {
        return userRepository.findByName(name);
    }

    @PostMapping("/add")
    public User addUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/nextid")
    public long getNextId() { return userRepository.getNextId(); }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> partialUpdateUser(@PathVariable("id") long id, @RequestBody UserDto newUser) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User Id: " + id));
        userMapper.updateUserFromDto(newUser, user);
        userRepository.save(user);
        return ResponseEntity.ok(user.toString() + " updated!");
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User Id: " + id));
        String deletionMsg = String.format("Deleted %s", user.toString());
        userRepository.delete(user);
        return deletionMsg;
    }
}
