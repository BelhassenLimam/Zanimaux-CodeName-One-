/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.entities.Produit;
import static com.mycompany.gui.SignInForm.connectedUser;

/**
 *
 * @author macbookpro
 */
public class PanierService {
    
    public void ajoutAuPanier(Produit p) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8888/zanimauxWeb/web/app_dev.php/api/ajoutProdPanier?cin=" +connectedUser.getCin()+ "&idProduit=" + p.getIdProduit()+"";
        con.setUrl(Url);

        

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
