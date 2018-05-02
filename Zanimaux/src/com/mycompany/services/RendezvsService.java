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
public class RendezvsService {
    public void addrdv(Rendezvs m){
        ConnectionRequest con = new ConnectionRequest();
        System.out.println(m.getIdrdv());
        con.setPost(false);
       con.setUrl("http://localhost/Mobile/insertrdv.php?cin="+m.getCin()
        +"&immatriculecabinet="+m.getImmatriculecabinet()+"&heurerdv="+m.getHeurerdv());
        NetworkManager.getInstance().addToQueue(con);
        ////////////////////
         ConnectionRequest con1 = new ConnectionRequest();
        con1.setUrl("http://localhost/Mobile/ShowRdv2.php?cin="+m.getCin());
      //ArrayList<notification_rdv> listan = new ArrayList<>();
        con1.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
           
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks;
                try {
                    tasks = jsonp.parseJSON(new CharArrayReader(new String(con1.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list3 = (List<Map<String, Object>>) tasks.get("root");
              int id=0;
                    for (Map<String, Object> obj : list3) {
              
                     
                       id =Integer.parseInt(obj.get("idrdv").toString());
                       m.setIdrdv(id);
                      // listan.add(m);
                    
                    }
                       } catch (IOException ex) {
                    System.out.println(" exception ");
                }
               
            }});
        NetworkManager.getInstance().addToQueueAndWait(con1);
         RendezvsService service= new RendezvsService();
        System.out.println(m.getIdrdv());
            service.createNotificationRdv(m.getIdrdv());
        
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
                            Date  heurerdv = format.parse(obj.get("heurerdv").toString());
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
 
    
        public void createNotificationRdv(int id) {
         ConnectionRequest con = new ConnectionRequest();
        
        con.setPost(false);
       con.setUrl("http://localhost/Mobile/createnotif.php?idrdv="+id +"&vu="+0);
            System.out.println("notif 1 crée");
       
        NetworkManager.getInstance().addToQueue(con);
        System.err.println("aaaaa");
        /////////////////////////////////////////////////////////////
        //ArrayList<notification_rdv> listnotif = new ArrayList<>();
        ConnectionRequest con1 = new ConnectionRequest();
        con1.setUrl("http://localhost/Mobile/SelectNotif.php");
      ArrayList<notification_rdv> listan = new ArrayList<>();
        con1.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
           
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks;
                try {
                    tasks = jsonp.parseJSON(new CharArrayReader(new String(con1.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list3 = (List<Map<String, Object>>) tasks.get("root");
              int idNotif=0;
                    for (Map<String, Object> obj : list3) {
                       notification_rdv n = new notification_rdv();
                       
                       idNotif =Integer.parseInt(obj.get("id").toString());
                       n.setId(idNotif);
                       listan.add(n);
                    
                    }
                       } catch (IOException ex) {
                    System.out.println(" exception ");
                }
               
            }});
        NetworkManager.getInstance().addToQueueAndWait(con1);
               
                      RendezvsService service = new RendezvsService();
        ArrayList<Rendezvs> listr = new ArrayList<Rendezvs>();
        UserService us = new UserService(); 
        Rendezvs v = service.findrdvByid(id);
        CabinetService cs = new CabinetService();
        String imm = v.getImmatriculecabinet();
        Cabinet c = cs.getCabinetByImm(imm);
        
        User u = us.getUserBycin(c.getCin());
                        
            ConnectionRequest con2 = new ConnectionRequest();
         for(notification_rdv notif2 : listan){
                    System.out.println(notif2.getId());
                    System.out.println(u.getCin());
        
       con2.setUrl("http://localhost/Mobile/createnotif2.php?cin="+u.getCin()
        +"&id_notif="+notif2.getId()+"&vu="+0);
          NetworkManager.getInstance().addToQueue(con2);
                    System.out.println("notif2 crée");
                         System.out.println("Notification rdv Ajoutee");
                         
       
     
      
       
    
        ////////////////////////////////////////////////
       
    
                    
        
    
            
        }}
        
    public ArrayList<User> getUserNotifNotVu(){
        ArrayList<notification_rdv> listn = new ArrayList<>();
        ConnectionRequest con4 = new ConnectionRequest();
        con4.setUrl("http://localhost/Mobile/ShowAllnotif.php");
   
        con4.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con4.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list1 = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list1) {
                        notification_rdv n = new notification_rdv();
                        int Id= Integer.parseInt(obj.get("id").toString());
                        int idrdv = Integer.parseInt(obj.get("idrdv").toString());
                        
                       n.setId(Id);
                        n.setIdrdv(idrdv);
                        
                        listn.add(n);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con4);
       
      
        
        ArrayList<User> userList=new ArrayList<>();
      ConnectionRequest con3 = new ConnectionRequest();
            for(notification_rdv notif : listn){
                
        con3.setUrl("http://localhost/Mobile/Selectnotif2.php?vu="+0+"&id_notif="+notif.getId());
    
        con3.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
               
                
          
                    //renvoi une map avec clé = root et valeur le reste
                  
                try {
                     JSONParser jsonp = new JSONParser();
                      Map<String, Object> tasks;
                    tasks = jsonp.parseJSON(new CharArrayReader(new String(con3.getResponseData()).toCharArray()));
                     System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list2 = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list2) {
                        UserService u = new UserService();
                  
                    //String immatriculecabinet= obj.get("immatricule")
                    User user= u.getUserBycin(obj.get("cin").toString());
                    userList.add(user);
                        
                    }} catch (IOException ex) {
                   
                }
                   
                       
                    
                      
                    
              

            }
        });
             NetworkManager.getInstance().addToQueueAndWait(con3);}
       
     
        return userList;
    }

    
    public void setUserNotifVu(String idUser) {
       
         ConnectionRequest con6 = new ConnectionRequest();
        con6.setUrl("http://localhost/Mobile/update.php?vu="+1+"&cin="+idUser);
        
         NetworkManager.getInstance().addToQueueAndWait(con6);
 
       
    }
     public void deleteRdv(int id){
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/deleteRdv.php?id="+id);
   
         NetworkManager.getInstance().addToQueueAndWait(con);
    }

   
}
