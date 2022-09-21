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
import java.util.HashSet;
import java.util.Set;
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
     * submit batch url and save it on DB then returns ok response
     * @param urlSet Set<URL>
     * @return ResponseEntity.ok().build() response
     */
    @PostMapping("/batch")
    public ResponseEntity<Void> submitBatchURL(@RequestBody Set<URL> urlSet){
        long startTime = System.currentTimeMillis();
        LOG.info("Batch request received: {}", urlSet);
        urlSet.forEach( url -> {
            url.setId(URL_UUID_PREFIX + UUID.randomUUID().toString());
            url.setCreated_date(new Timestamp(System.currentTimeMillis()));
            url.setTimesProcessed(0);
        });
        urlService.save(urlSet);
        LOG.info("Request processed in {} mills",(System.currentTimeMillis() - startTime));
        return ResponseEntity.ok().build();
    }


    /**
     * submit url and save it on DB then returns ok response
     * @param url URL
     * @return ResponseEntity.ok().build() response
     */
    @PostMapping
    public ResponseEntity<Void> submitURL(@RequestBody URL url){
        long startTime = System.currentTimeMillis();
        url.setId(URL_UUID_PREFIX + UUID.randomUUID().toString());
        url.setCreated_date(new Timestamp(System.currentTimeMillis()));
        url.setTimesProcessed(0);
        LOG.info("URL received: {}",url.getUrl());
        urlService.save(new HashSet<>(){{
            add(url);
        }});
        LOG.info("Request processed in {} mills",(System.currentTimeMillis() - startTime));
        return ResponseEntity.ok().build();
    }

}