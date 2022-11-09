package com.lemonade.FeederService.service;

import com.lemonade.FeederService.codec.URLSerializationCodec;
import com.lemonade.FeederService.models.URL;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    private static final Logger LOG = LoggerFactory.getLogger(CacheService.class);

    @Value("${com.lemonade.urlfeeder.service.cache.ttl}")
    private Integer ttl;

    @Value("${com.lemonade.urlfeeder.service.cache.url}")
    private String url;

    private RedisClient redisClient = null;

    private StatefulRedisConnection<String, URL> statefulRedisConnection = null;

    /**
     * get URL from cache if exits
     * @param key, String
     * @return url
     */
    public URL get(String key){
        URL url = statefulRedisConnection.sync().get(key);
        if (url != null){
            LOG.info("Serving from cach, for key: {}",key);
        } else {
            LOG.info("Cache missed, for key: {}",key);
        }
        return url;
    }

    /**
     * sets ttl for received URL
     * @param url, URL
     * @return void
     */
    public void set(URL url){
        long ttlSeconds = TimeUnit.DAYS.toSeconds(this.ttl);
        statefulRedisConnection.sync().setex(url.getUrl(), ttlSeconds, url);
    }

    @PostConstruct
    private void init(){
        LOG.info("Post init called");
        redisClient = RedisClient.create(url);
        statefulRedisConnection = redisClient.connect(new URLSerializationCodec());
    }

    @PreDestroy
    private void destroy(){
        if (statefulRedisConnection != null){
            statefulRedisConnection.close();
        }
        if (redisClient != null){
            redisClient.shutdown();
        }

    }
}
