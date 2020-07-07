package com.stahlferro.kusa.controllers.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class APIHomeController {
    @GetMapping("/grass")
    public String grassCall() {
        return "grass";
    }
}
