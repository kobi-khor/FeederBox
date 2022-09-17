package com.lemonade.HTMLFileWorker.listners;

import com.lemonade.HTMLFileWorker.service.URLProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
public class HTMLWorker {

    private static final Logger LOG = LoggerFactory.getLogger(HTMLWorker.class);

    @Autowired
    private URLProcessor urlProcessor;

    @KafkaListener(topics = "html_topic", groupId = "A1")
    public void consume(String message){
        LOG.info("Received Message:" + message);
        urlProcessor.process(message);
    }
}
