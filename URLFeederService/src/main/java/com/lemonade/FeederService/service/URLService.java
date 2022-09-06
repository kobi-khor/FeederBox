package com.lemonade.FeederService.service;

import com.lemonade.FeederService.models.URL;
import com.lemonade.FeederService.repository.URLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Optional;

@Service
public class URLService {
    private static final Logger LOG = LoggerFactory.getLogger(URLService.class);

    @Value("#{${com.lemonade.FeederService.topics}}")
    Map<String, String> kafkaTopics;
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
        try {
            Optional<String> opt = getTopicByPath(url.getUrl());
            if (opt.isEmpty()){
                LOG.warn("Content type not found for URL: {}", url.getUrl());
                return;
            }
            String topic = opt.get();
            LOG.info("URL: {}, sending to topic: {}", url.getUrl(),topic);
//            urlRepository.save(url);
//            kafkaService.send("html_topic", url.getUrl());
        } catch (IOException exception){
            LOG.error("Excrption: {}", exception);
        }

    }

    /**
     * function gets path(url) and makes HttpUrl connection , sets request method to 'HEAD' then determines
     * which content type is the url
     * @param path
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
        LOG.info("Key: {}", contentType);
        if (kafkaTopics.containsKey(contentType)){
            return Optional.of(kafkaTopics.get(contentType));
        }
        return Optional.empty();
    }


}
