package com.stahlferro.kusa.controllers.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIHomeController {
    @GetMapping("/")
    public String grassCall() {
        return "grass";
    }
}
