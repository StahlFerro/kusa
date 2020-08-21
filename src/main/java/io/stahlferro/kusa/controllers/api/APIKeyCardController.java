package io.stahlferro.kusa.controllers.api;

import io.stahlferro.kusa.mappers.KeyCardMapper;
import io.stahlferro.kusa.models.KeyCard;
import io.stahlferro.kusa.models.KeyCardDto;
import io.stahlferro.kusa.services.KeyCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/keycard")
public class APIKeyCardController {

    private KeyCardService keyCardService;

    @Autowired
    private KeyCardMapper keyCardMapper;

    private APIKeyCardController(KeyCardService service) { this.keyCardService = service; }

    @GetMapping("/all")
    public List<KeyCard> getAllKeyCards() {
        return keyCardService.getAll();
    }

    @GetMapping("/{id}")
    public KeyCard getKeyCardById(@PathVariable("id") long id) {
        KeyCard keyCard = keyCardService.getKeyCardByIdOrError(id);
        return keyCard;
    }

    @GetMapping("/{id}/description")
    public String getKeyCardStringByid(@PathVariable("id") long id) {
        KeyCard keyCard = keyCardService.getKeyCardByIdOrError(id);
        return keyCard.toString();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addKeyCard(@Valid @RequestBody KeyCard keyCard) {
        keyCardService.add(keyCard);
        return new ResponseEntity<>(keyCard, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> partialUpdateKeyCard(@PathVariable("id") long id, @RequestBody KeyCardDto keyCardDto) {
        KeyCard keyCard = keyCardService.getKeyCardByIdOrError(id);
        log.info(String.format("KeyCard: %s\nKeyCardDto: %s", keyCard.toString(), keyCardDto.toString()));
        keyCardService.update(keyCard, keyCardDto);
        return ResponseEntity.ok(keyCard.toString() + " updated!");
    }

    @DeleteMapping("/delete/{id}")
    public String deleteKeyCard(@PathVariable("id") long id) {
        KeyCard keyCard = keyCardService.getKeyCardByIdOrError(id);
        String deletionMsg = keyCardService.delete(keyCard);
        return deletionMsg;
    }
}
