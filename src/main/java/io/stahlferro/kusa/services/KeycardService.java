package io.stahlferro.kusa.services;

import io.stahlferro.kusa.mappers.KeycardMapper;
import io.stahlferro.kusa.models.Keycard;
import io.stahlferro.kusa.models.KeycardDto;
import io.stahlferro.kusa.repositories.KeycardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KeycardService {
    @Autowired
    public KeycardRepository repository;
    @Autowired
    public KeycardMapper mapper;
    @Autowired
    public RandomService randomService;

    public List<Keycard> getAll() {
        return repository.findAll();
    }

    public Optional<Keycard> getKeycardById(UUID id) {
        return repository.findById(id);
    }
    public Keycard getKeycardByIdOrError(UUID id) {
        Keycard keycard = getKeycardById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid KeyCard Id: " + id));
        return keycard;
    }
    public Keycard add(Keycard keycard) {
//        keycard.setUuid(randomService.generateRandomUUID());
        repository.save(keycard);
        return keycard;
    }

    public Keycard create(KeycardDto dto) {
        Keycard keycard = mapper.createKeycardFromDto(dto);
        return repository.save(keycard);
    }

    public void update(Keycard keycard, KeycardDto keycardDto) {
        mapper.updateKeycardFromDto(keycardDto, keycard);
        repository.save(keycard);
    }

    public String delete(Keycard keycard) {
        String deletionMsg = String.format("Deleted %s", keycard.toString());
        repository.delete(keycard);
        return deletionMsg;
    }
}
