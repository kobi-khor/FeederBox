package com.lemonade.FeederService.service;

import com.lemonade.FeederService.models.URL;
import com.lemonade.FeederService.repository.URLRepository;
import org.springframework.stereotype.Service;

@Service
public class URLService {

    URLRepository urlRepository;

    public void save(URL url){
        urlRepository.save(url);
    }
}
