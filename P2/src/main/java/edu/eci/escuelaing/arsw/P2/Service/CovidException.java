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
public class CovidException extends Exception{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CovidException(String message){
        super(message);
    }
}
