package com.lemonade.HTMLFileWorker.config;


import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class URLFeederServiceCofig {

    @Value("${com.lemonade.html-worker.urlfeederservice.baseurl}")
    String baseurl;

    @Bean
    public Retrofit getClient(){
        return new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
    }
}
