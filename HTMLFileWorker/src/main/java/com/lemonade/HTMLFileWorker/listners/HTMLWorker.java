package com.lemonade.HTMLFileWorker.listners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HTMLWorker {

    @KafkaListener(topics = "html_topic", groupId = "A1")
    public void consume(String message){
        System.out.println("Received Message:" + message);
    }
}
