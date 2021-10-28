/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.escuelaing.arsw.P2.Service;

/**
 *
 * @author ADMIN
 */
import org.json.JSONObject;

public interface IHttpConnection {
    JSONObject getWeatherByCountry(String city) throws CovidException;
    JSONObject getAll(String city) throws CovidException;
}
