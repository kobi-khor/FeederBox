package com.lemonade.FeederService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * invoke send() function from KafkaTemplate
     * @param String topic, String massage
     * @return void
     */
    public void send(String topic, String massage){
        kafkaTemplate.send(topic,massage);
    }
}
