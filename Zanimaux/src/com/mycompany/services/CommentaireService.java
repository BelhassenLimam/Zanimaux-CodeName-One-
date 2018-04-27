/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entities.Commentaires;
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
 * @author Azza
 */
public class CommentaireService {
     public ArrayList<Commentaires> getComByRef(String im) {
        ArrayList<Commentaires> listcom = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/GetComByRefuge.php?refuge="+im);
    
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
                        Commentaires animal = new Commentaires();
                        int id=Integer.parseInt(obj.get("id").toString());
                         SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
                        Date  date= format.parse(obj.get("date").toString());
                         animal.setDate(date);
                        animal.setRefuge(im);
                        animal.setId(id);
                        animal.setCin(obj.get("cin").toString());
                        animal.setContenant(obj.get("contenant").toString());
                       
                   
                        listcom.add(animal);

                    }
                } catch (IOException ex) {
                } catch (ParseException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcom;
    }

      public void editCom(Commentaires com){
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/editCom.php?id="+com.getId()+"&contenant="+com.getContenant());
        System.out.println("Services.CommentaireService.editCom()");
         NetworkManager.getInstance().addToQueueAndWait(con);
    }
       public void editComByContent(String old,String content){
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/editComByCont.php?old="+old+"&contenant="+content);
        System.out.println("Services.CommentaireService.editCom()");
         NetworkManager.getInstance().addToQueueAndWait(con);
    }
      public void deleteComByContent(String contenant){
           ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/deleteComByContent.php?contenant="+contenant);
        System.out.println("Services.CommentaireService.deleteCom()");
         NetworkManager.getInstance().addToQueueAndWait(con);
      }
    public void deleteCom(int id){
         ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/deleteCom.php?id="+id);
        System.out.println("Services.CommentaireService.deleteCom()");
         NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void ajoutCom(Commentaires com) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Mobile/AddCom.php?cin="+com.getCin()
        +"&contenant="+com.getContenant()+"&refuge="+com.getRefuge();
        con.setUrl(Url);

        System.out.println("tt");

        
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
