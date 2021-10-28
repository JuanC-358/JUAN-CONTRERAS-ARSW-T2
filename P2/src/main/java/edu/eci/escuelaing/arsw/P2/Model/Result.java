/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.escuelaing.arsw.P2.Model;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Result implements Serializable{
    private String city;
    private String province;
    private String country;
    private String lastUpdate;
    private String keyId;
    private int confirmed;
    private int deaths;
    private int recovered;
    public Result(String city, String province, String country,String lastUpdate,String keyId,int confirmed,int deaths,int recovered){
        this.SetCity(city);
        this.SetProvince(province);
        this.SetCountry(country);
        this.SetLastUpdate(lastUpdate);
        this.SetKeyId(keyId);
        this.SetConfirmed(confirmed);
        this.SetDeaths(deaths);
        this.SetRecovered(recovered);
    }
    public Result(){
    };
    public void SetCity(String c){
        this.city=c;
    }
    public String getCity(String c){
        return this.city;
    }
    public void SetProvince(String c){
        this.province=c;
    }
    public String getProvince(String c){
        return this.province;
    }
    public void SetCountry(String c){
        this.country=c;
    }
    public String getCountry(String c){
        return this.country;
    }
    public void SetLastUpdate(String c){
        this.lastUpdate=c;
    }
    public String getLastUpdate(String c){
        return this.lastUpdate;
    }
    public void SetKeyId(String c){
        this.keyId=c;
    }
    public String getKeyId(String c){
        return this.keyId;
    }
    public void SetConfirmed(int c){
        this.confirmed=c;
    }
    public int getConfirmed(String c){
        return this.confirmed;
    }
    public void SetDeaths(int c){
        this.deaths=c;
    }
    public int getDeaths(String c){
        return this.deaths;
    }
    public void SetRecovered(int c){
        this.recovered=c;
    }
    public int getRecovered(String c){
        return this.recovered;
    }
}
