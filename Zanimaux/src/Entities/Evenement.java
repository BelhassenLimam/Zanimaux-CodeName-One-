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
public class Evenement {
    private int idEvt;
    private String cinUser;
    private String lieu;
    private Date dateDebut;
    private Date dateFin;
    private String type;
    private String titre;
    private String description;
    private String imageEvt;
    private int nbPlace=0;
    private int nbParticipants=0;

    public Evenement(int idEvt, String cinUser, String lieu, Date dateDebut, Date dateFin, String type, String titre, String description, String imageEvt,int nbPlace) {
        this.idEvt = idEvt;
        this.cinUser = cinUser;
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.imageEvt = imageEvt;
        this.nbPlace = nbPlace;
    }

    public Evenement(String cinUser,String lieu, Date dateDebut, Date dateFin, String type, String titre, String description, int nbPlace,String imageEvt) {
        this.cinUser=cinUser;
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.nbPlace = nbPlace;
        this.imageEvt = imageEvt;
    }

    public Evenement() {
    }

    

 

  
    

    public int getIdEvt() {
        return idEvt;
    }

    public void setIdEvt(int idEvt) {
        this.idEvt = idEvt;
    }

    public String getCinUser() {
        return cinUser;
    }

    public void setCinUser(String cinUser) {
        this.cinUser = cinUser;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageEvt() {
        return imageEvt;
    }

    public void setImageEvt(String imageEvt) {
        this.imageEvt = imageEvt;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public int getNbParticipants() {
        return nbParticipants;
    }

    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvt=" + idEvt + ", cinUser=" + cinUser + ", lieu=" + lieu + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", type=" + type + ", titre=" + titre + ", description=" + description + ", imageEvt=" + imageEvt + ", nbPlace=" + nbPlace + ", nbParticipants=" + nbParticipants + '}';
    }

       
  



}