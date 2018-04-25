/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;



/**
 *
 * @author Maroua
 */
public class Annonce {
    private int idAnnonce;
    private String cinUser;
    private String type;
    private String titre;
    private String description;
    private String photoAnimal;

    public Annonce(int idAnnonce, String cinUser, String type, String titre, String description, String photoAnimal) {
        this.idAnnonce = idAnnonce;
        this.cinUser = cinUser;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.photoAnimal = photoAnimal;
    }

    public Annonce(String cinUser,String type, String titre, String description, String photoAnimal) {
        this.cinUser = cinUser;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.photoAnimal = photoAnimal;
    }

    public Annonce() {
    }

   
    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getCinUser() {
        return cinUser;
    }

    public void setCinUser(String cinUser) {
        this.cinUser = cinUser;
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

    public String getPhotoAnimal() {
        return photoAnimal;
    }

    public void setPhotoAnimal(String pieceJointe) {
        this.photoAnimal = photoAnimal;
    }

    @Override
    public String toString() {
        return "Annonce{" + "idAnnonce=" + idAnnonce + ", cinUser=" + cinUser + ", type=" + type + ", titre=" + titre + ", description=" + description + ", pieceJointe=" + photoAnimal + '}';
    }

  
    
    
    
}
