package com.lemonade.FeederService.service;

import com.lemonade.FeederService.models.URL;
import com.lemonade.FeederService.repository.URLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class URLService {
    private static final Logger LOG = LoggerFactory.getLogger(URLService.class);

    @Value("#{${com.lemonade.urlfeederservice.topics}}")
    Map<String, String> kafkaTopics;
    @Autowired
    URLRepository urlRepository;

    @Autowired
    KafkaService kafkaService;

    @Value("#{${com.lemonade.FeederService.service.cooldown}}")
    Integer cooldown;

    /**
     * saves url on DB and sends it to kafkaService
     * @param url URL
     */
    @Async
    public void save(URL url){
        try {
            LOG.info("------- {} -------", Thread.currentThread().getName());
            URL existingURL = urlRepository.findByUrl(url.getUrl());
            Optional<String> optContentType = Optional.empty();
            if (existingURL != null){
                // Allow processing if the url has been processed more than 7 days ago (cooldown value from properties)
                if (existingURL.getLastProcessed().getTime() + TimeUnit.DAYS.toMillis(cooldown) > System.currentTimeMillis()){
                    LOG.info("URL {} already processed on {}", existingURL.getUrl(),existingURL.getLastProcessed().getTime());
                    return;
                }
                url = existingURL;
                optContentType = Optional.of(existingURL.getContentType());
            }
            url.setLastProcessed(new Timestamp(System.currentTimeMillis()));
            url.setTimesProcessed(url.getTimesProcessed() + 1);
            if (optContentType.isEmpty()) {
                optContentType = getContentType(url.getUrl());
            }
            if (optContentType.isEmpty()) {
                LOG.warn("Content type not found for URL: {}", url.getUrl());
                return;
            }
            Optional<String> optTopic = getTopicByContentType(optContentType.get());
            if (optTopic.isEmpty()) {
                LOG.warn("Content type {} not mapped", optContentType.get());
                return;
            }
            String topic = optTopic.get();
            if (url.getContentType() == null || url.getContentType().isEmpty()) {
                url.setContentType(optContentType.get());
            }
            LOG.info("URL: {}, sending to topic: {}", url.getUrl(), topic);
            kafkaService.send(topic, url.getUrl());
            urlRepository.save(url);
        } catch (IOException ex) {
            LOG.error("Exception: ", ex);
        }

    }

    /**
     * function gets path(url) and makes HttpUrl connection , sets request method to 'HEAD' then determines
     * the topic of given path to kafka topics
     * @param path String
     * @return Optional-> contentType or empty()/null
     */
    private Optional<String> getTopicByPath(String path) throws IOException{
        java.net.URL url = new java.net.URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
        connection.connect();
        String rawContentType = connection.getContentType();
        LOG.info("URL : {} has content Type: {}", path, rawContentType);
        String contentType = rawContentType.split(";")[0];
        LOG.info("Key: {}", path);
        if (kafkaTopics.containsKey(contentType)){
            return Optional.of(kafkaTopics.get(contentType));
        }
        return Optional.empty();
    }

    /**
     * function gets path(url) and makes HttpUrl connection , sets request method to 'HEAD' then determines
     * which content type is the url
     * @param path String
     * @return Optional-> contentType or empty()/null
     */
    private Optional<String> getContentType(String path) throws IOException {
        java.net.URL url = new java.net.URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
        connection.connect();
        return Optional.of(connection.getContentType());
    }

    /**
     * function gets raw content type and returns the topic from kafka topics
     * @param rawContentType String
     * @return Optional-> contentType or empty()/null
     */
    private Optional<String> getTopicByContentType(String rawContentType) throws IOException {
        String contentType = rawContentType.split(";")[0];
        LOG.info("Key: {}", contentType);
        if (kafkaTopics.containsKey(contentType)) {
            return Optional.of(kafkaTopics.get(contentType));
        }
        LOG.info("Key: {} not found in existing topics", contentType);
        return Optional.empty();
    }

}
