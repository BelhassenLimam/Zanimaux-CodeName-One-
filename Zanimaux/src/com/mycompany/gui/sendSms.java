/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author Mariam
 */
public class sendSms {
    public Form f;
public Button envoyer;
public TextField message;
public Resources themes;
SpanLabel sp;
    //private Resources theme;

    public sendSms(Resources theme) {
  
        
  
          UIBuilder ui = new UIBuilder();
//     f= ui.createContainer(theme, "GUI 1").getComponentForm();
       
       // hi.add(accueil);
       //this.add(hi);
     
     envoyer=new Button("Envoyer");message=new TextField();
//     f.add(envoyer);f.add(message);
     message.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                    sendSms ms = new sendSms(theme);
       
                ms.sendMessageCode("27424929",message.getText());
               sendMessageCode("27424929",message.getText());
                 
             }
         });
     
    }
    
    public String sendMessageCode(String to,String codeTele) {
        
  
      String myURL="https://rest.nexmo.com/sms/json?api_key=c99cb56c&api_secret=Ay7Lh9i4HWA6G2HR&to=216"+
                to+"&from=55087305&text="+codeTele;
     
        System.out.println(myURL);
        StringBuilder sb = new StringBuilder();
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(myURL); 
        Dialog.show("bien joué", "Message envoyé", "ok",null);
        NetworkManager.getInstance().addToQueue(con);

       return sb.toString();
    }
    
}
