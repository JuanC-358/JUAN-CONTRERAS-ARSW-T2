/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.escuelaing.arsw.P2.Controller;

import edu.eci.escuelaing.arsw.P2.Service.ICovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
public class P2Controller {
  @Autowired
    ICovidService cs;

    @RequestMapping(value = "/{country}", method = RequestMethod.GET)
    public ResponseEntity<?> getWeatherByCountry(@PathVariable(name = "country") String country) {
        try {
            return new ResponseEntity<>(cs.getCovidByCountry(country), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getWeatherByCountry( ) {
        try {
            return new ResponseEntity<>(cs.getCovid(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.FORBIDDEN);
        }
    }
}
