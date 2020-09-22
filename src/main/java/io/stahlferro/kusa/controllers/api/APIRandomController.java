package io.stahlferro.kusa.controllers.api;

import io.stahlferro.kusa.services.RandomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/random")
public class APIRandomController {
    private final RandomService randomService;

    public APIRandomController(RandomService service) {
        this.randomService = service;
    }

    @RequestMapping("/number")
    public int randNumber() {
        return randomService.generateRandomNumber();
    }

    @RequestMapping("/uuid")
    public UUID randUUID() throws Exception {
        return randomService.generateRandomUUID();
    }
}
