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
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import static java.lang.Math.round;
import static java.lang.Math.round;
import static java.lang.Math.round;
import static java.lang.Math.round;
import java.util.ArrayList;
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
        con.setUrl("http://localhost/zanimauxFinal/web/app_dev.php/api/afficherEvenement");
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
                        

                                           
                        Double idE = (Double) (obj.get("idEvt"));
                        int a = (int) round(idE);
                        System.out.println(a);
                           Double nbp = (Double) (obj.get("nbPlace"));
                        int b = (int) round(nbp);
                        System.out.println(a);
                        Evenement e1 = new Evenement();
                       
                    
                       e1.setIdEvt(a);
                        e1.setCinUser(obj.get("cin").toString());
                        e1.setLieu(obj.get("lieu").toString());
                       // e1.setDateDebut(obj.get("dateDebut"));
                        //e1.setDateFin(obj.get("dateFin"));
                        e1.setType(obj.get("type").toString());
                        e1.setTitre(obj.get("titre").toString());
                        e1.setDescription(obj.get("description").toString());
                       e1.setImageEvt(obj.get("imageEvt").toString());
                      e1.setNbPlace(b);
                        //System.out.println(e1.getLieu());
                        System.out.println(obj.get("lieu").toString());
                  
                    listTasks.add(e1);

                    }
                } catch (IOException ex) {
                }

            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
}
