package com.revature.pokemondb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientServiceImpl implements WebClientService {
    @Autowired
    private WebClient client;

    /**
     * Send Get Request to External API
     * https://reflectoring.io/spring-webclient/
     * @param url
     * @return
     */
    public String getRequestJSON(String url) {
        return client.get().uri(url).retrieve().bodyToMono(String.class).block();
    }
}
