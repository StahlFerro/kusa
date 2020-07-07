package com.stahlferro.kusa.services;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class RandomService {
    public int generateRandomNumber() {
        Random R = new Random();
        int min = 10;
        int max = 1000;
        return R.nextInt(max - min) + min;
    }
    public String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}
