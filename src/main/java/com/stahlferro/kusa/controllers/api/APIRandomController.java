package com.stahlferro.kusa.controllers.api;

import com.stahlferro.kusa.controllers.services.RandomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIRandomController {
    private final RandomService randomService;

    public APIRandomController(RandomService service) {
        this.randomService = service;
    }

    @RequestMapping("/randnum")
    public int RandNum() {
        return randomService.RandomNumber();
    }
}
