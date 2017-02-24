/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lego.proxy.client;

import java.util.Map;
import java.util.Set;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author edgar
 */
public class ProxyClient<T> {

    private final RestTemplate restTemplate;
    private static String URL = "http://localhost:8080/";

    public ProxyClient(String api) {
        restTemplate = new RestTemplate();
        URL = URL.concat(api);
    }

    public <L> L executeGet(Class<L> type) {
        return restTemplate.getForObject(URL, type);
    }

    public T executeGet(Class<T> type, Map<String, ?> params) {
        StringBuilder buffer = new StringBuilder(URL);
        Set<String> keys = params.keySet();
        keys.forEach((key) -> {
            buffer.append("/{").append(key).append("}");
        });
        return restTemplate.getForObject(buffer.toString(), type, params);
    }

    public T executePost(T request, Class<T> type) {
        return restTemplate.postForObject(URL, request, type);
    }

    public void executePut(T request, Map<String, ?> params) {
        StringBuilder buffer = new StringBuilder(URL);
        if (params != null && params.size() > 0) {            
            Set<String> keys = params.keySet();
            keys.forEach((key) -> {
                buffer.append("/{").append(key).append("}");
            });
        }
        restTemplate.put(buffer.toString(), request,params);
    }
    
    public void executeDelete(Map<String, ?> params){
        StringBuilder buffer = new StringBuilder(URL);
        if (params != null && params.size() > 0) {            
            Set<String> keys = params.keySet();
            keys.forEach((key) -> {
                buffer.append("/{").append(key).append("}");
            });
        }
        restTemplate.delete(buffer.toString(), params);
    }

}
