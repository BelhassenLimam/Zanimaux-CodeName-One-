/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entities.Magasin;
import com.mycompany.entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author macbookpro
 */
public class UserService {
    User m;
     public static User u;
    public User getConnectedUser(String rep)
    {
       
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(rep.toCharArray()));
                    System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {   
                        
                        m = new User();
                        m.setCin(obj.get("cin").toString());
                        m.setRoles(obj.get("roles").toString());
                        m.setTelephone(obj.get("telephone").toString());
                       
                    }
                } catch (IOException ex) {
                }

        
        
        return m;
    }
    public User getUserBycin(String cin) {
       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/GetUserByCin.php?cin="+cin);
    
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
                        User user = new User();
                        
                        int cp=Integer.parseInt(obj.get("codePostale").toString());
                        user.setCin(obj.get("cin").toString());
                        user.setAdresse(obj.get("adresse").toString());
                        user.setUsername(obj.get("username").toString());
                        user.setEmail(obj.get("email").toString());
                        user.setNom(obj.get("nom").toString());
                        user.setPrenom(obj.get("prenom").toString());
                        user.setVille(obj.get("ville").toString());
                        user.setCodePostale(cp);
                  u=user;
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return u;
    }
    
}
