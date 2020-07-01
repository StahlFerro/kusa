package com.stahlferro.kusa.controllers.api;

import com.stahlferro.kusa.models.User;
import com.stahlferro.kusa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/user")
public class APIUserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping(path="/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping()
    public List<User> getUsersByName(@RequestParam("name") String name) {
        return userRepository.findByName(name);
    }

    @PostMapping(path="/add")
    public User addUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping(path="/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        Optional<User> userSrch = userRepository.findById(id);
        if (userSrch.isPresent()) {
            User user = userSrch.get();
            String deletionMsg = String.format("Deleted %s", user.toString());
            userRepository.delete(user);
            return deletionMsg;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
