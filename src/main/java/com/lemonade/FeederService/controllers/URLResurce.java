package com.lemonade.FeederService.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.lemonade.FeederService.models.URL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLResurce {

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    @PostMapping
    public ResponseEntity<?> submitURL(@RequestBody URL url){
        System.out.println(url);
        return ResponseEntity.ok().build();

    }

}