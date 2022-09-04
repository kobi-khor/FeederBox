package com.lemonade.FeederService.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.lemonade.FeederService.models.URL;
import com.lemonade.FeederService.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.UUID;

import static com.lemonade.FeederService.common.Constants.URL_UUD_PREFIX;

@RestController
public class URLResurce {

    @Autowired
    private URLService urlService;

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    /**
     * submit url and save it on DB then returns ok response
     * @param url
     * @return ResponseEntity.ok().build() response
     */
    @PostMapping
    public ResponseEntity<?> submitURL(@RequestBody URL url){
        url.setId(URL_UUD_PREFIX + UUID.randomUUID().toString());
        urlService.save(url);
        System.out.println(url);
        return ResponseEntity.ok().build();

    }

}