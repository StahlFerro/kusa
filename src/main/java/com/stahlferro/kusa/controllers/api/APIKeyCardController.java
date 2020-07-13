package com.stahlferro.kusa.controllers.api;

import com.stahlferro.kusa.mappers.KeyCardMapper;
import com.stahlferro.kusa.models.KeyCard;
import com.stahlferro.kusa.models.KeyCardDto;
import com.stahlferro.kusa.models.User;
import com.stahlferro.kusa.repositories.KeyCardRepository;
import com.stahlferro.kusa.services.RandomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/keycard")
public class APIKeyCardController {

    @Autowired
    private KeyCardRepository keyCardRepository;

    @Autowired
    private KeyCardMapper keyCardMapper;

    private final RandomService randomService;

    private APIKeyCardController(RandomService service) {
        this.randomService = service;
    }

    @GetMapping("/all")
    public List<KeyCard> getAllUsers() {
        return keyCardRepository.findAll();
    }

    @GetMapping("/{id}")
    public KeyCard getKeyCardById(@PathVariable("id") long id) {
        KeyCard keyCard = keyCardRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "KeyCard not found"));
        return keyCard;
    }

    @GetMapping("/{id}/description")
    public String getKeyCardStringByid(@PathVariable("id") long id) {
        KeyCard keyCard = keyCardRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "KeyCard not found"));
        return keyCard.toString();
    }

    @PostMapping("/add")
    public KeyCard addKeyCard(@Valid @RequestBody KeyCard keyCard) {
        keyCard.setUuid(randomService.generateRandomUUID());
        return keyCardRepository.save(keyCard);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity partialUpdateKeyCard(@PathVariable("id") long id, @RequestBody KeyCardDto keyCardDto) {
        KeyCard keyCard = keyCardRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid KeyCard Id: " + id));
        log.info(String.format("KeyCard: %s\nKeyCardDto: %s", keyCard.toString(), keyCardDto.toString()));
        keyCardMapper.updateKeyCardFromDto(keyCardDto, keyCard);
        keyCardRepository.save(keyCard);
        return ResponseEntity.ok(keyCard.toString() + " updated!");
    }
}
