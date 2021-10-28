/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.escuelaing.arsw.P2.Service;

import edu.eci.escuelaing.arsw.P2.Model.Result;
import java.io.IOException;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 *
 * @author ADMIN
 */
public interface ICovidService {

    /**
     *
     * @param country
     * @return
     * @throws CovidException
     * @throws UnirestException
     * @throws IOException
     */
    public  Result getCovidByCountry(String country) throws CovidException, UnirestException, IOException;
    public Result getCovid() throws  CovidException, UnirestException, IOException;
}
