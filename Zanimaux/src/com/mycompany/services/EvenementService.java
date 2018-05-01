/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entities.Evenement;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Maroua
 */
public class EvenementService {
    
    public ArrayList<Evenement> getAllEvent(){
        ArrayList<Evenement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/WebServiceMobile/getAllEvent.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("rooot:" +tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    
                   


                    for (Map<String, Object> obj : list) {
                        
                 
                        Evenement event = new Evenement();
                        
                       int id = Integer.parseInt(obj.get("idEvt").toString());
                        int nbp = Integer.parseInt(obj.get("nb_place").toString());
                         int nbpp = Integer.parseInt(obj.get("nbParticipants").toString());
                         SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
                      Date  dateDb= format.parse(obj.get("dateDebut").toString());
                       System.out.println(dateDb);
                        Date  dateF= format.parse(obj.get("dateFin").toString());
                      System.out.println(dateF);
                        
                        event.setIdEvt(id);
                        event.setCinUser(obj.get("cin").toString());
                        event.setLieu(obj.get("lieu").toString());
                        event.setDateDebut(dateDb);
                        event.setDateFin(dateF);
                       event.setType(obj.get("type").toString());
                        event.setTitre(obj.get("titre").toString());
                        event.setDescription(obj.get("description").toString());
                        event.setImageEvt(obj.get("image_evt").toString());
                         event.setNbPlace(nbp);
                         event.setNbParticipants(nbpp);
                       

                        
                  
                    listTasks.add(event);

                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                   
                }

            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    public ArrayList<Evenement> getListEvenetById(int id) {
        ArrayList<Evenement> listEvent= new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/WebServiceMobile/getEventById.php?idEvt="+id);
    
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
                        Evenement event = new Evenement();
                         SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
                      Date  dateDb= format.parse(obj.get("dateDebut").toString());
                       System.out.println(dateDb);
                        Date  dateF= format.parse(obj.get("dateFin").toString());
                      System.out.println(dateF);
                        int nbp = Integer.parseInt(obj.get("nb_place").toString());
                        int nbpp = Integer.parseInt(obj.get("nbParticipants").toString());
                        event.setIdEvt(id);
                        event.setCinUser(obj.get("cin").toString());
                        event.setLieu(obj.get("lieu").toString());
                        event.setDateDebut(dateDb);
                        event.setDateFin(dateF);
                       event.setType(obj.get("type").toString());
                        event.setTitre(obj.get("titre").toString());
                        event.setDescription(obj.get("description").toString());
                        event.setImageEvt(obj.get("image_evt").toString());
                         event.setNbPlace(nbp);
                         event.setNbParticipants(nbpp);
                       
                  
                   
                        listEvent.add(event);

                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                    
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvent;
    }
 
    
}
