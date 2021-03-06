/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entities.Parc;
import com.mycompany.entities.Promenade;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author macbookpro
 */
public class PromenadeService {
     

        public ArrayList<Promenade> getAllPromenade(){
        ArrayList<Promenade> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8888/zanimauxFinal2/web/app_dev.php/affichePromenade");
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
                         Promenade m = new Promenade();
                        m.setId(obj.get("id").toString());
                        m.setNomPromenade(obj.get("nomPromenade").toString());
                        m.setTypePromenade(obj.get("typePromenade").toString());
                        m.setLieuPromenade(obj.get("lieuPromenade").toString());
                        m.setDescriptionPromenade(obj.get("descriptionPromenade").toString());
                        /*m.setDateDebutPromenade(Date.);
                        m.setDateFinPromenade(Date.parse(df));*/
                        m.setPhotoPromenade(obj.get("photoPromenade").toString());
                        listTasks.add(m);
                    }
                } catch (IOException ex) {
                }

            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
   }
        
              public ArrayList<Promenade>  getPromenadeByCin(String cin){
             ArrayList<Promenade> listTasks = new ArrayList<>();
       ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8888/WebServiceMobile/GetPromenadeByCin.php?cinPetsitter="+cin);
    
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
                        
                        Promenade promenade = new Promenade();
                        
                       
                        promenade.setId(obj.get("id").toString());
                        promenade.setNomPromenade(obj.get("nompromenade").toString());
                        promenade.setTypePromenade(obj.get("typePromenade").toString());
                        promenade.setLieuPromenade(obj.get("lieuPromenade").toString());
                        promenade.setDescriptionPromenade(obj.get("descriptionPromenade").toString());
                        /*m.setDateDebutPromenade(Date.);
                        m.setDateFinPromenade(Date.parse(df));*/
                        promenade.setPhotoPromenade(obj.get("photoPromenade").toString());
                        promenade.setCinPetsitter(obj.get("cinPetsitter").toString());
                        
                      listTasks.add(promenade);
                
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
          return listTasks;
 }
    
}
