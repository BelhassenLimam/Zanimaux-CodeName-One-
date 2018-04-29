/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Avis;
import com.mycompany.entities.Cabinet;
import com.mycompany.entities.Rendezvs;
import com.mycompany.entities.User;
import com.mycompany.entities.notification_rdv;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Mariam
 */
public class AvisService {
    public void addavis(Avis m){
        ConnectionRequest con = new ConnectionRequest();
        
        con.setPost(false);
       con.setUrl("http://localhost:8888/AjoutAvis.php?idParc="+m.getIdParc()
        +"&avis="+m.getAvis()+"&cinUser="+m.getCinUser());
        NetworkManager.getInstance().addToQueue(con);
        System.err.println("aaaaa");
    }
    
        public static Rendezvs vf;
    public Rendezvs findrdvByid (int id){
       ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/ShowRdvdetail.php?idrdv="+id);
    
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
                        Rendezvs rdv = new Rendezvs();
                        SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
                        try {
                            Date  heurerdv = format.parse(obj.get("Heurerdv").toString());
                            rdv.setCin(obj.get("cin").toString());
                       rdv.setImmatriculecabinet(obj.get("immatriculecabinet").toString());
                       rdv.setHeurerdv(heurerdv);
                       
                       
                       vf=rdv;
                       
                     } catch (ParseException ex) {
                            System.out.println("   erreurdate");
                     }
                      
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return vf;
 }
 

   
}
