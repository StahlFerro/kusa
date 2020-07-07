package com.stahlferro.kusa.controllers.api;

import com.stahlferro.kusa.models.KeyCard;
import com.stahlferro.kusa.models.User;
import com.stahlferro.kusa.repositories.KeyCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/keycard")
public class APIKeyCardController {
    @Autowired
    public KeyCardRepository keyCardRepository;

    @GetMapping(path="/all")
    public List<KeyCard> getAllUsers() {
        return keyCardRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public KeyCard getKeyCardById(@PathVariable("id") long id) {
        Optional<KeyCard> keyCard = keyCardRepository.findById(id);
        if (keyCard.isPresent()) {
            return keyCard.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KeyCard not found");
        }
    }

    @PostMapping(path="/add")
    public KeyCard addKeyCard(@Valid @RequestBody KeyCard keyCard) {
        return keyCardRepository.save(keyCard);
    }

}
