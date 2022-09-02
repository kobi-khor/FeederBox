package com.lemonade.FeederService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLResurce {

    @GetMapping
    public String ping(){
        return "pong";
    }

}