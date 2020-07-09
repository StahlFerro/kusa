package com.stahlferro.kusa.controllers.web;

import com.stahlferro.kusa.models.User;
import com.stahlferro.kusa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/user")
public class WebUserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping(path="/all")
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(path="/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        model.addAttribute("users", userList);
        return "users";
    }

}
