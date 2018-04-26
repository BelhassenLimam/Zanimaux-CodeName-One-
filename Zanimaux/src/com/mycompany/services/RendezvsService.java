/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.mycompany.entities.Rendezvs;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Mariam
 */
public class RendezvsService {
    public void addrdv(Rendezvs m){
        ConnectionRequest con = new ConnectionRequest();
        
        con.setPost(false);
       con.setUrl("http://localhost/Mobile/insertrdv.php?cin="+m.getCin()
        +"&immatriculecabinet="+m.getImmatriculecabinet()+"&heurerdv="+m.getHeurerdv());
        NetworkManager.getInstance().addToQueue(con);
        System.err.println("aaaaa");
    }
    
}
