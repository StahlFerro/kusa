package com.stahlferro.kusa.controllers.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomService {
    public int RandomNumber() {
        Random R = new Random();
        int min = 10;
        int max = 1000;
        return R.nextInt(max - min) + min;
    }
}
