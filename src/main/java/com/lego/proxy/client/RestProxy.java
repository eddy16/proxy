/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lego.proxy.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author edgar
 */
public class RestProxy {

    String URL = "http://localhost:8080";

    HttpClient client;

    HttpGet get;
    HttpPost post;
    HttpPut put;
    HttpDelete delete;

    public RestProxy() {
        client = HttpClientBuilder.create().build();
    }

    public HttpResponse executeGet(String... params) throws IOException {
        StringBuilder buffer = new StringBuilder(URL);

        for (String param : params) {
            buffer.append("/").append(param);
        }
        get = new HttpGet(buffer.toString());
        HttpResponse response = client.execute(get);

        return response;
    }

    public String execute(String method, String operation, String payload, String... params) throws IOException, Exception {

        URL = URL.concat("/").concat(operation);
        HttpResponse response = chooseMethod(method, params);

        if (response == null)
            return "Servicio temporalmente fuera de servicio";

        int status = response.getStatusLine().getStatusCode();

        if (status >= 500) 
            throw new Exception(response.getStatusLine().getReasonPhrase());
        
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        
        return result.toString();
    }

    private HttpResponse chooseMethod(String method, String... params) throws IOException {
        HttpResponse response = null;
        switch (method) {
            case "GET":
                response = executeGet(params);
                break;
            case "POST":
            case "PUT":
            case "DELETE":
        }
        return response;
    }
    
}
