package com.lemonade.HTMLFileWorker.service;


import com.lemonade.HTMLFileWorker.listners.HTMLWorker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class URLProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(URLProcessor.class);

    @Async
    public void process(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                String path = link.attr("href");
                LOG.info("Extracted: {}", path);
            }
        }catch (IOException ex){
            LOG.error("Exception: {}",ex);
        }
    }



}