package com.lemonade.FeederService.controllers;

import com.lemonade.FeederService.models.URL;
import com.lemonade.FeederService.service.URLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.UUID;

import static com.lemonade.FeederService.common.Constants.URL_UUID_PREFIX;

@RestController
public class URLResurce {

    private static final Logger LOG = LoggerFactory.getLogger(URLResurce.class);

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
        long startTime = System.currentTimeMillis();
        url.setId(URL_UUID_PREFIX + UUID.randomUUID().toString());
        url.setCreated_date(new Timestamp(System.currentTimeMillis()));
        url.setTimesProcessed(0);
        LOG.info("URL received: {}",url.getUrl());
        urlService.save(url);
        LOG.info("Request processed in {} mills",(System.currentTimeMillis() - startTime));
        return ResponseEntity.ok().build();
    }

}