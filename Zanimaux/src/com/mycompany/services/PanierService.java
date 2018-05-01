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
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.ContenuPanier;
import com.mycompany.entities.Magasin;
import com.mycompany.entities.Panier;
import com.mycompany.entities.Produit;
import static com.mycompany.gui.SignInForm.connectedUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author macbookpro
 */
public class PanierService {
    
    public void ajoutAuPanier(Produit p) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/zanimauxFinal/web/app_dev.php/api/ajoutProdPanier?cin=" +connectedUser.getCin()+ "&idProduit=" + p.getIdProduit()+"";
        con.setUrl(Url);

        

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
          public ArrayList<Panier> getPanier(){
        ArrayList<Panier> listPanier = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/zanimauxFinal/web/app_dev.php/api/affichePanier/"+connectedUser.getCin());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> panier= jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +panier.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) panier.get("root");

                    for (Map<String, Object> obj : list) {   
                        float somme = Float.parseFloat(obj.get("somme").toString());
                        Panier p = new Panier();
                        p.setSomme((int)somme);
                        p.setCin(connectedUser.getCin());
                        p.setSommeCommande((int)0);
                        listPanier.add(p);
                    }
                } catch (IOException ex) {
                }

            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPanier;
    }
          
          
              
          public ArrayList<ContenuPanier> getContenuPanier(){
        ArrayList<ContenuPanier> listcontenuPanier = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/WebServiceMobile/afficheContenuPanier.php?cin="+connectedUser.getCin());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> cpanier= jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +cpanier.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) cpanier.get("root");

                    for (Map<String, Object> obj : list) { 
                       float id=Float.parseFloat(obj.get("idProduit").toString());
                        float qt = Float.parseFloat(obj.get("quantite").toString());
                        //float cmd = Float.parseFloat(obj.get("commande").toString());
                        ContenuPanier p = new ContenuPanier();
                        p.setQuantite((int)qt);
                        p.setIdProduit((int)id);
                        
//                        p.setCommande((int)cmd);
//                        p.getDateCommande(list.get("dateCommande"))
                        listcontenuPanier.add(p);
                    }
                } catch (IOException ex) {
                }

            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcontenuPanier;
    }
          
          public void passerCommande() {
        
             ConnectionRequest con = new ConnectionRequest();   
             String Url = "http://localhost/zanimauxFinal/web/app_dev.php/api/passeCommande?cin=" +connectedUser.getCin();
             con.setUrl(Url);
              NetworkManager.getInstance().addToQueueAndWait(con);
              Message m = new Message("Body of message");
              
              //boolean success = m.sendMessageViaCloudSync("zanimo.esprit@gmail.com",  connectedUser.getEmail(), "", "Message Subject",
                          //  "Check out Codename One at https://www.codenameone.com/");
              Display.getInstance().sendMessage(new String[] {connectedUser.getEmail()}, "Subject of message", m);
             // System.out.println(success);
             
    }
          
          
}
