package io.stahlferro.kusa.services;

import io.stahlferro.kusa.mappers.KeyCardMapper;
import io.stahlferro.kusa.models.KeyCard;
import io.stahlferro.kusa.models.KeyCardDto;
import io.stahlferro.kusa.repositories.KeyCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class KeyCardService {
    @Autowired
    public KeyCardRepository repository;
    @Autowired
    public KeyCardMapper mapper;
    @Autowired
    public RandomService randomService;

    public List<KeyCard> getAll() {
        return repository.findAll();
    }

    public Optional<KeyCard> getKeyCardById(long id) {
        return repository.findById(id);
    }
    public KeyCard getKeyCardByIdOrError(long id) {
        KeyCard keyCard = getKeyCardById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid KeyCard Id: " + id));
        return keyCard;
    }
    public KeyCard add(KeyCard keyCard) {
        keyCard.setUuid(randomService.generateRandomUUID());
        repository.save(keyCard);
        return keyCard;
    }

    public void update(KeyCard keyCard, KeyCardDto keyCardDto) {
        mapper.updateKeyCardFromDto(keyCardDto, keyCard);
        repository.save(keyCard);
    }

    public String delete(KeyCard keyCard) {
        String deletionMsg = String.format("Deleted %s", keyCard.toString());
        repository.delete(keyCard);
        return deletionMsg;
    }
}
