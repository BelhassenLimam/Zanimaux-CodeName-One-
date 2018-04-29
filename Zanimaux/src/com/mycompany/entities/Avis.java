/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;



/**
 *
 * @author Azza
 */
public class Avis {
    private String id;
    private String idParc;
    private double avis;
    private String cinUser;

    public Avis() {
    }

    public Avis(String id, String idParc, double avis, String cinUser) {
        this.id = id;
        this.idParc = idParc;
        this.avis = avis;
        this.cinUser = cinUser;
    }

    public Avis(String idParc, double avis, String cinUser) {
        this.idParc = idParc;
        this.avis = avis;
        this.cinUser = cinUser;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdParc() {
        return idParc;
    }

    public void setIdParc(String idParc) {
        this.idParc = idParc;
    }

    public double getAvis() {
        return avis;
    }

    public void setAvis(double avis) {
        this.avis = avis;
    }

    public String getCinUser() {
        return cinUser;
    }

    public void setCinUser(String cinUser) {
        this.cinUser = cinUser;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", idParc=" + idParc + ", avis=" + avis + ", cinUser=" + cinUser + '}';
    }

    
    
}
