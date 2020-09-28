package io.stahlferro.kusa.controllers.api;

import io.stahlferro.kusa.models.Keycard;
import io.stahlferro.kusa.models.KeycardDto;
import io.stahlferro.kusa.services.KeycardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/keycard")
public class APIKeycardController {

    private KeycardService keycardService;

    private APIKeycardController(KeycardService service) { this.keycardService = service; }

    @GetMapping("/all")
    public List<Keycard> getAllKeyCards() {
        return keycardService.getAll();
    }

    @GetMapping("/{id}")
    public Keycard getKeyCardById(@PathVariable("id") UUID id) {
        Keycard keycard = keycardService.getKeycardByIdOrError(id);
        return keycard;
    }

    @GetMapping("/{id}/description")
    public String getKeyCardStringByid(@PathVariable("id") UUID id) {
        Keycard keycard = keycardService.getKeycardByIdOrError(id);
        return keycard.toString();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addKeyCard(@Valid @RequestBody KeycardDto dto) {
        Keycard keycard = keycardService.create(dto);
        return new ResponseEntity<>(keycard, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> partialUpdateKeyCard(@PathVariable("id") UUID id, @RequestBody KeycardDto keycardDto) {
        Keycard keycard = keycardService.getKeycardByIdOrError(id);
        log.info(String.format("KeyCard: %s\nKeyCardDto: %s", keycard.toString(), keycardDto.toString()));
        keycardService.update(keycard, keycardDto);
        return ResponseEntity.ok(keycard.toString() + " updated!");
    }

    @DeleteMapping("/delete/{id}")
    public String deleteKeyCard(@PathVariable("id") UUID id) {
        Keycard keycard = keycardService.getKeycardByIdOrError(id);
        String deletionMsg = keycardService.delete(keycard);
        return deletionMsg;
    }
}
