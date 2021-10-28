/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.escuelaing.arsw.P2.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class HttpConnection implements IHttpConnection{

    @Override
    public JSONObject getWeatherByCountry(String city) throws CovidException {
        try {
            HttpResponse<String> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=colombia")
	.header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
	.header("x-rapidapi-key", "4e9d34e50fmshe7e9fd5d34de6fap19b8c9jsnba923529e479")
	.asString();
            return new JSONObject(response.getBody());
        } catch (Exception e) {
            throw new CovidException("Error to make request to the API");
        }
        
    }
    @Override
    public JSONObject getAll(String city) throws CovidException {
        try {
            HttpResponse<String> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/")
	.header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
	.header("x-rapidapi-key", "4e9d34e50fmshe7e9fd5d34de6fap19b8c9jsnba923529e479")
	.asString();
            return new JSONObject(response.getBody());
        } catch (Exception e) {
            throw new CovidException("Error to make request to the API");
        }
        
    }

    
}
