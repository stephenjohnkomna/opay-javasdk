package core;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionClient {
    private String baseUrl;
    private Map<String,String> headers= new HashMap<String,String>();

    public ConnectionClient(String baseUrl,HashMap<String,String> headers) {
        this.baseUrl = baseUrl;
        this.headers = headers;
    }


    public JSONObject makePostRequest(HashMap<String, Object> parameters,String endpoint) {
        try {
            HttpResponse<JsonNode> result =
                    Unirest.post(this.baseUrl+endpoint)
                           .headers(headers)
                    .body(Util.mapToJsonString(parameters))
                    .asJson();
            return result.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

  /*  *//**
     * Used to send a GET request to the Opay API
     *
     * @return - JSONObject containing the API response
     *//*
    public JSONObject makeGetRequest() {
        try {
            HttpResponse<JsonNode> result = Unirest.get(url)
                    .headers(headers)
                    .asJson();
            return result.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    *//**
     * Used to send a GET request to the Opay API
     *
     * @param parameters - Map containing parameters to send
     * @return - JSONObject containing API response
     *//*
    public JSONObject makeGetRequest(Map parameters) {
        try {
            HttpResponse<JsonNode> result = Unirest.get(url)
                    .headers(headers)
                    .queryString(parameters)
                    .asJson();
            return result.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    *//**
     * Used to send a GET request to the Opay API
     *
     * @param parameters - HashMap containing parameters to send
     * @return - JSONObject containing API response
     *//*
    public JSONObject makeGetRequest(HashMap<String, Object> parameters) {
        try {
            HttpResponse<JsonNode> result = Unirest.get(url)
                    .headers(headers)
                    .queryString(parameters)
                    .asJson();
            return result.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    *//**
     * Used to send a PUT request to the Opay API
     *
     * @param parameters - Map containing parameters to send
     * @return - JSONObject containing API response
     *//*
    public JSONObject makePutRequest(Map parameters) {
        try {
            HttpResponse<JsonNode> result = Unirest.put(url)
                    .headers(headers)
                    .fields(parameters)
                    .asJson();
            return result.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    *//**
     * Used to send a PUT request to the Opay API
     *
     * @param parameters - HashMap containing parameters to send
     * @return - JSONObject containing API response
     *//*
    public JSONObject makePutRequest(HashMap<String, Object> parameters) {
        try {
            HttpResponse<JsonNode> result = Unirest.get(url)
                    .headers(headers)
                    .queryString(parameters)
                    .asJson();
            return result.getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * Called to shut down the background event loop
     */
    public void shutDown() {
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
