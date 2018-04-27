/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.services;


import com.mycompany.entities.Cabinet;
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

/**
 *
 * @author Mariam
 */
public class CabinetService {
    
        public ArrayList<Cabinet> getAllCabinet(){
        ArrayList<Cabinet> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/ShowAllCabinets.php");
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
                        float tel = Float.parseFloat(obj.get("telephoneCabinet").toString());
                         float fax = Float.parseFloat(obj.get("faxCabinet").toString());
                        float cdp = Float.parseFloat(obj.get("codePostaleCabinet").toString());
                       Cabinet m = new Cabinet();
                        m.setImmatriculeCabinet(obj.get("immatriculecabinet").toString());
                        m.setEmailCabinet(obj.get("emailCabinet").toString());
                        m.setAdresseCabinet(obj.get("adresseCabinet").toString());
                        m.setCodePostaleCabinet((int)cdp);
                        m.setVilleCabinet(obj.get("villeCabinet").toString());
                        m.setTelephoneCabinet((int)tel);
                         m.setFaxCabinet((int)fax);
                        m.setCin(obj.get("cin").toString());
                        m.setPhotovet(obj.get("photovet").toString());
                        listTasks.add(m);
                    }
                } catch (IOException ex) {
                }

            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;}
}
