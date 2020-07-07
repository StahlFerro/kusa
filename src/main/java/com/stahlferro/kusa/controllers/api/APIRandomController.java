package com.stahlferro.kusa.controllers.api;

import com.stahlferro.kusa.services.RandomService;
import com.stahlferro.kusa.utils.RegexUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(path = "/api/random")
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
    public String randUUID() throws Exception {
        return randomService.generateRandomUUID();
    }
}
