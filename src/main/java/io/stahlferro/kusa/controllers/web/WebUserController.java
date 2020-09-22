package io.stahlferro.kusa.controllers.web;

import io.stahlferro.kusa.models.UserBase;
import io.stahlferro.kusa.repositories.UserBaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping(path = "/user")
public class WebUserController {

    @Autowired
    public UserBaseRepository userBaseRepository;

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<UserBase> userBases = userBaseRepository.findAll();
        model.addAttribute("users", userBases);
        return "users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        UserBase userBase = userBaseRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        ArrayList<UserBase> userBaseList = new ArrayList<>();
        userBaseList.add(userBase);
        model.addAttribute("users", userBaseList);
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") long id, Model model) {
        UserBase userBase = userBaseRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        model.addAttribute("user", userBase);
        return "user_edit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid UserBase userBase, BindingResult result, Model model) {
        log.info(userBase.getEmail());
        if (result.hasErrors()) {
            userBase.setId(id);
            return "user_edit";
        }
        userBaseRepository.save(userBase);
        return "redirect:/user/all";
    }
}
