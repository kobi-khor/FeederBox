package com.lemonade.FeederService.service;

import com.lemonade.FeederService.models.URL;
import com.lemonade.FeederService.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class URLService {

    @Autowired
    URLRepository urlRepository;
    @Autowired
    KafkaService kafkaService;

    /**
     * saves url on DB and sends it to kafkaService
     * @param url
     * @return void
     */
    public void save(URL url){
        urlRepository.save(url);
        kafkaService.send("html_topic", url.getUrl());
    }
}
