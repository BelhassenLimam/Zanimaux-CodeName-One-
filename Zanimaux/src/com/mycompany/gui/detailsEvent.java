/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evenement;
import static com.mycompany.gui.SignInForm.connectedUser;
import com.mycompany.services.UserService;
import java.io.IOException;
import java.util.ArrayList;
import com.codename1.ui.FontImage;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;



/**
 *
 * @author Maroua
 */
public class detailsEvent {
   private Resources theme;
    Form f;
   public detailsEvent(ArrayList<Evenement> liste) throws IOException{
        theme = UIManager.initFirstTheme("/theme");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));       
         Container cButton = new Container(new BoxLayout(BoxLayout.X_AXIS));
          SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
             format.applyPattern("dd-MM-yyyy");
            Label lbl = new Label(format.format(liste.get(0).getDateDebut())); 
            Label lb2 = new Label(format.format(liste.get(0).getDateFin()));
           
            Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
             int id=liste.get(0).getIdEvt();
             
              Command cmd = new Command("Back",Image.createImage("/left-arrow.png")){
             @Override
             public void actionPerformed(ActionEvent evt) {
                 
                 try {
                     afficherEvenement ff = new afficherEvenement();
                     ff.getF().showBack();
                 } catch (IOException ex) {
                     
                 }
                 
             }
         };
         f.getToolbar().addCommandToLeftBar(cmd);
            
            SpanLabel lb = new SpanLabel("");
            Button participer = new Button("Participer");
            Button annuler = new Button("Annuler");
            Label partage = new Label("");
            
            FontImage.setMaterialIcon(partage, FontImage.MATERIAL_SHARE);
            partage.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    
       String token = "EAACEdEose0cBANx9EJr0CLsNtlWuoCtOkCI3clc8Sli7jhR0B6coZBKunWzamNw4ftp7BDwbBy2ZCuHCZBhYaC7nMiVMoSyORRTpSd9b2uY4iZBl6sunTGrhKTLx4Qug2rKp41kjBowTCNaxjFRJ2oFdLeZA7ZC1ZCXJbyl1IiOz9lFatFHImqxPGKQmZAf03jHFNopnRJvvNT03pGWIJf4kR8gtcyTWA84ZD";
               FacebookClient fb = new DefaultFacebookClient(token);
                FacebookType  r = fb.publish("me/feed", FacebookType.class, Parameter.with("message","Evenement "+"\n"+"Type : "+liste.get(0).getType() +"\n"+ "Titre : " + liste.get(0).getTitre()+"\n"+ " aura lieu le " + lbl.getText()+"\n" + " jusqu'au " + lb2.getText() +"\n"+" A : "+ liste.get(0).getLieu()+"\n"+"Qui porte sur :  "+ liste.get(0).getDescription()));
                   
            }
        });
            
            participer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 ConnectionRequest con;
        con = new ConnectionRequest();
        con.setUrl("http://localhost/WebServiceMobile/participer.php?cin=" +connectedUser.getCin()+ "&idEvt=" +id+"");
        
    con.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
            public void actionPerformed(NetworkEvent evt) {
                
                String str = new String(con.getResponseData());
                System.out.println(str);
                annuler.setVisible(true);
                participer.setVisible(false);
                f.refreshTheme();
                

                }
                         
     });

        NetworkManager.getInstance().addToQueue(con); 
                
            }
        });
            
                annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 ConnectionRequest con;
        con = new ConnectionRequest();
        con.setUrl("http://localhost/WebServiceMobile/annuler.php?cin=" +connectedUser.getCin()+ "&idEvt=" +id+"");
        
    con.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
            public void actionPerformed(NetworkEvent evt) {
                
                String str = new String(con.getResponseData());
                System.out.println(str);
                annuler.setVisible(false);
                participer.setVisible(true);
                f.refreshTheme();
                

                }
                         
     });

        NetworkManager.getInstance().addToQueue(con); 
                
            }
        });
            
            
            
            
            
         
            ImageViewer iv = new ImageViewer(theme.getImage(liste.get(0).getImageEvt()).scaled(350, 200));
            SpanLabel t =new SpanLabel("Type : "+liste.get(0).getType()+"\n"+"Lieu : "+liste.get(0).getLieu()+"\n"+"Description :"+liste.get(0).getDescription());
         
           int nbPlace=liste.get(0).getNbPlace();
           int nbParticipants=liste.get(0).getNbParticipants();
           
            Label plein = new Label ("Pleiiiiiiiiiiiiiin");
            plein.setVisible(false);
            participer.setVisible(false);
           
            annuler.setVisible(false);
           
    System.out.println(nbPlace);
    System.out.println(nbParticipants);
            lb.setText("Titre : "+liste.get(0).getTitre());
            if (nbPlace == nbParticipants){
                
                    plein.setVisible(true);
                    participer.setVisible(false);
                     annuler.setVisible(false);
            
            }
            else{
         ConnectionRequest con;
        con = new ConnectionRequest();
        con.setUrl("http://localhost/WebServiceMobile/exist.php?cin=" +connectedUser.getCin()+ "&idEvt=" +id+"");
        
    con.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
            public void actionPerformed(NetworkEvent evt) {
                
                String str = new String(con.getResponseData());
                System.out.println(str);
                if(str.equalsIgnoreCase("existe")){
                    annuler.setVisible(true);
                     f.refreshTheme();
                }
                else {
                    
                    participer.setVisible(true);
                    f.refreshTheme();
                }
                

                }
                         
     });

        NetworkManager.getInstance().addToQueue(con);  
            }//else
             c.add(iv);
            c.add(lb);
            c.add(lbl);
            c.add(lb2);
            c.add(t);
            c.add(partage);
            cButton.add(participer);
            cButton.add(annuler);
            cButton.add(plein);
           
            c.add(cButton);
            f.add(c);     
        
    }

        
      
    
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
