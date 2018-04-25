/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;



import java.util.Date;


/**
 *
 * @author Maroua
 */
public class Promenade {
    private String id;
    private String nomPromenade;
    private String typePromenade;
    private String lieuPromenade;
    private String DescriptionPromenade;
    private Date datedebutPromenade;
    private Date datefinPromenade;
    private String photoPromenade;
    private String cinPetsitter;

    public Promenade() {
    }

    public Promenade(String id, String nomPromenade, String typePromenade, String lieuPromenade, String DescriptionPromenade, Date datedebutPromenade, Date datefinPromenade, String photoPromenade, String cinPetsitter) {
        this.id = id;
        this.nomPromenade = nomPromenade;
        this.typePromenade = typePromenade;
        this.lieuPromenade = lieuPromenade;
        this.DescriptionPromenade = DescriptionPromenade;
        this.datedebutPromenade = datedebutPromenade;
        this.datefinPromenade = datefinPromenade;
        this.photoPromenade = photoPromenade;
        this.cinPetsitter = cinPetsitter;
    }

    public Promenade(String nomPromenade, String typePromenade, String lieuPromenade, String DescriptionPromenade, Date datedebutPromenade, Date datefinPromenade, String photoPromenade, String cinPetsitter) {
        this.nomPromenade = nomPromenade;
        this.typePromenade = typePromenade;
        this.lieuPromenade = lieuPromenade;
        this.DescriptionPromenade = DescriptionPromenade;
        this.datedebutPromenade = datedebutPromenade;
        this.datefinPromenade = datefinPromenade;
        this.photoPromenade = photoPromenade;
        this.cinPetsitter = cinPetsitter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomPromenade() {
        return nomPromenade;
    }

    public void setNomPromenade(String nomPromenade) {
        this.nomPromenade = nomPromenade;
    }

    public String getTypePromenade() {
        return typePromenade;
    }

    public void setTypePromenade(String typePromenade) {
        this.typePromenade = typePromenade;
    }

    public String getLieuPromenade() {
        return lieuPromenade;
    }

    public void setLieuPromenade(String lieuPromenade) {
        this.lieuPromenade = lieuPromenade;
    }

    public String getDescriptionPromenade() {
        return DescriptionPromenade;
    }

    public void setDescriptionPromenade(String DescriptionPromenade) {
        this.DescriptionPromenade = DescriptionPromenade;
    }

    public Date getDatedebutPromenade() {
        return datedebutPromenade;
    }

    public void setDatedebutPromenade(Date datedebutPromenade) {
        this.datedebutPromenade = datedebutPromenade;
    }

    public Date getDatefinPromenade() {
        return datefinPromenade;
    }

    public void setDatefinPromenade(Date datefinPromenade) {
        this.datefinPromenade = datefinPromenade;
    }

    public String getPhotoPromenade() {
        return photoPromenade;
    }

    public void setPhotoPromenade(String photoPromenade) {
        this.photoPromenade = photoPromenade;
    }

    public String getCinPetsitter() {
        return cinPetsitter;
    }

    public void setCinPetsitter(String cinPetsitter) {
        this.cinPetsitter = cinPetsitter;
    }

    @Override
    public String toString() {
        return "Promenade{" + "id=" + id + ", nomPromenade=" + nomPromenade + ", typePromenade=" + typePromenade + ", lieuPromenade=" + lieuPromenade + ", DescriptionPromenade=" + DescriptionPromenade + ", datedebutPromenade=" + datedebutPromenade + ", datefinPromenade=" + datefinPromenade + ", photoPromenade=" + photoPromenade + ", cinPetsitter=" + cinPetsitter + '}';
    }
    
    

}