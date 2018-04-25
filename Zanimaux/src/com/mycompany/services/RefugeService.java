/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entities.Animal;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.entities.Refuge;

/**
 *
 * @author Azza
 */
public class RefugeService {
 public ArrayList<Refuge> getListRefuges() {
        ArrayList<Refuge> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/ShowAllRefuge.php");
    // con.setUrl("http://localhost/zanimauxFinalGit/web/app_dev.php/api/AficherRefuge") ;
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                        Refuge refuge = new Refuge();
                        int tel=Integer.parseInt(obj.get("telephoneRefuge").toString());
                        int fax=Integer.parseInt(obj.get("faxRefuge").toString());
                        int cp=Integer.parseInt(obj.get("codePostaleRefuge").toString());
                        refuge.setImmatriculation(obj.get("immatriculation").toString());
                        refuge.setCin(obj.get("cin").toString());
                        refuge.setNomRefuge(obj.get("nomRefuge").toString());
                        refuge.setEmailRefuge(obj.get("emailRefuge").toString());
                        refuge.setTelephoneRefuge(tel);
                        refuge.setFaxRefuge(fax);
                        refuge.setAdresseRefuge(obj.get("adresseRefuge").toString());
                        refuge.setCodePostaleRefuge( cp);
                        refuge.setGouvernementRefuge(obj.get("gouvernementrefuge").toString());
                        refuge.setPhotoRefuge(obj.get("photorefuge").toString());
                        refuge.setChat(obj.get("chat").toString());
                        refuge.setChien(obj.get("chien").toString());
                        refuge.setRongeur(obj.get("rongeur").toString());
                        refuge.setAutre(obj.get("autre").toString());
                        listTasks.add(refuge);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
 public ArrayList<Animal> getListAnimauxByRef(String im) {
        ArrayList<Animal> listAnimaux = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/GetAnimauxByRefuge.php?immatriculation="+im);
    
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                        Animal animal = new Animal();
                        int id=Integer.parseInt(obj.get("idAnimal").toString());
                        int age=Integer.parseInt(obj.get("age").toString());
                        animal.setRefuge(im);
                        animal.setIdAnimal(id);
                        animal.setType(obj.get("type").toString());
                        animal.setEtat(obj.get("etat").toString());
                        animal.setNomAnimal(obj.get("nomAnimal").toString());
                        animal.setPhotoAnimal(obj.get("photoanimal").toString());
                        animal.setRace(obj.get("race").toString());
                        animal.setAge(age);
                   
                        listAnimaux.add(animal);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnimaux;
    }
 
}
