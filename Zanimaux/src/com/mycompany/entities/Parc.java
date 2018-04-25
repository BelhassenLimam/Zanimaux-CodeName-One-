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
public class Parc {
    private String id;
    private String nomParc;
    private String CategorieDressage;
    private String adresseParc;
    private String villeParc;
    private int   codePostaleParc;
    private String photoParc;
    private String cinDresseur;

    public Parc() {
    }

    public Parc(String id, String nomParc, String CategorieDressage, String adresseParc, String villeParc, int codePostaleParc, String photoParc, String cinDresseur) {
        this.id = id;
        this.nomParc = nomParc;
        this.CategorieDressage = CategorieDressage;
        this.adresseParc = adresseParc;
        this.villeParc = villeParc;
        this.codePostaleParc = codePostaleParc;
        this.photoParc = photoParc;
        this.cinDresseur = cinDresseur;
    }

    
    public Parc(String nomParc, String CategorieDressage, String adresseParc, String villeParc, int codePostaleParc, String photoParc, String cinDresseur) {
        
        this.nomParc = nomParc;
        this.CategorieDressage = CategorieDressage;
        this.adresseParc = adresseParc;
        this.villeParc = villeParc;
        this.codePostaleParc = codePostaleParc;
        this.photoParc = photoParc;
        this.cinDresseur = cinDresseur;
    }

    public Parc(String nomParc, String CategorieDressage, String adresseParc, String villeParc, int codePostaleParc, String photoParc) {
        this.nomParc = nomParc;
        this.CategorieDressage = CategorieDressage;
        this.adresseParc = adresseParc;
        this.villeParc = villeParc;
        this.codePostaleParc = codePostaleParc;
        this.photoParc = photoParc;
    }
    
    

   
    
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomParc() {
        return nomParc;
    }

    public void setNomParc(String nomParc) {
        this.nomParc = nomParc;
    }

    public String getCategorieDressage() {
        return CategorieDressage;
    }

    public void setCategorieDressage(String CategorieDressage) {
        this.CategorieDressage = CategorieDressage;
    }

    public String getAdresseParc() {
        return adresseParc;
    }

    public void setAdresseParc(String adresseParc) {
        this.adresseParc = adresseParc;
    }

    public String getVilleParc() {
        return villeParc;
    }

    public void setVilleParc(String villeParc) {
        this.villeParc = villeParc;
    }

    public int getCodePostaleParc() {
        return codePostaleParc;
    }

    public void setCodePostaleParc(int codePostaleParc) {
        this.codePostaleParc = codePostaleParc;
    }

    public String getPhotoParc() {
        return photoParc;
    }

    public void setPhotoParc(String photoParc) {
        this.photoParc = photoParc;
    }

    public String getCinDresseur() {
        return cinDresseur;
    }

    public void setCinDresseur(String cinDresseur) {
        this.cinDresseur = cinDresseur;
    }


    
}
