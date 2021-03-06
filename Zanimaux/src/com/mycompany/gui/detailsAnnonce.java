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
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Annonce;
import static com.mycompany.gui.SignInForm.connectedUser;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Maroua
 */
public class detailsAnnonce {
    
    private Resources theme;
    Form f;
   public detailsAnnonce(ArrayList<Annonce> liste) throws IOException{
        theme = UIManager.initFirstTheme("/theme");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));  
        Container cButton = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Button partageF = new Button("",Image.createImage("/facebookIcon.png").scaled(150,150));
            partageF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
String token = "EAACEdEose0cBAK5tfJQiPjjRLwf9AVnuUmnbS0rzIpiO2ykC0yHl2i6EPOuNJczjRXD4dWu8RhJB05uw8SSMBWvnyBPoUDAs5j9XSsHWZC6uohApF7tZAA94h1KPv1kUyFwN5IexeK6jcw5LAG5kiUsMWTUIom07mkooqaGg7nDPdrb8nz2zlIexLFGfoElvFQh3USiQyXf4Q8H9tUxql7FngfuMYZD";
               FacebookClient fb = new DefaultFacebookClient(token);
                FacebookType  r = fb.publish("me/feed", FacebookType.class, Parameter.with("message","Annonce "+"\n"+"Type : "+liste.get(0).getType() +"\n"+ "Titre : " + liste.get(0).getTitre()+"\n"+"Qui porte sur :  "+ liste.get(0).getDescription()));
                                  }
        });
         
         Command cmd = new Command("Back",Image.createImage("/left-arrow.png")){
             @Override
             public void actionPerformed(ActionEvent evt) {
                  
                 try {
                   affichageAnnonce  ff = new affichageAnnonce();
                       ff.getF().showBack();
                 } catch (IOException ex) {
                   
                 }
               
             }
         };
         f.getToolbar().addCommandToLeftBar(cmd);
        int id=liste.get(0).getIdAnnonce();
        Label like = new Label("");
        Label dislike = new Label("");
        FontImage.setMaterialIcon(like, FontImage.MATERIAL_FAVORITE);
         FontImage.setMaterialIcon(dislike, FontImage.MATERIAL_FAVORITE_BORDER);
         like.setVisible(false);
         dislike.setVisible(false);
         like.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 ConnectionRequest con;
        con = new ConnectionRequest();
        con.setUrl("http://localhost/WebServiceMobile/like.php?cin=" +connectedUser.getCin()+ "&idA=" +id+"");
        
    con.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
            public void actionPerformed(NetworkEvent evt) {
                
                String str = new String(con.getResponseData());
                System.out.println(str);
                dislike.setVisible(true);
                like.setVisible(false);
                f.refreshTheme();
                

                }
                         
     });

        NetworkManager.getInstance().addToQueue(con); 
                
            }
        });
         
        
         dislike.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                 ConnectionRequest con;
        con = new ConnectionRequest();
        con.setUrl("http://localhost/WebServiceMobile/dislike.php?cin=" +connectedUser.getCin()+ "&idA=" +id+"");
        
    con.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
            public void actionPerformed(NetworkEvent evt) {
                
                String str = new String(con.getResponseData());
                 System.out.println(str);
                dislike.setVisible(false);
                like.setVisible(true);
                f.refreshTheme();
                

                }
                         
     });

        NetworkManager.getInstance().addToQueue(con); 
                
                
            }
        });
         
         
         
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            SpanLabel lb = new SpanLabel("");
            ImageViewer iv = new ImageViewer(theme.getImage(liste.get(0).getPieceJointe()).scaled(950, 650));
            SpanLabel t =new SpanLabel("Titre: "+liste.get(0).getTitre()+"\n"+"Type :"+liste.get(0).getType()+"\n"+"Description : "+liste.get(0).getDescription());
            
             ConnectionRequest con;
        con = new ConnectionRequest();
        con.setUrl("http://localhost/WebServiceMobile/existAnnonce.php?cin=" +connectedUser.getCin()+ "&idA=" +id+"");
        
    con.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
            public void actionPerformed(NetworkEvent evt) {
                
                String str = new String(con.getResponseData());
                System.out.println(str);
                if(str.equalsIgnoreCase("existe")){
                    dislike.setVisible(true);
                     f.refreshTheme();
                }
                else {
                    
                    like.setVisible(true);
                    f.refreshTheme();
                }
                

                }
                         
     });

        NetworkManager.getInstance().addToQueue(con);  

          
            
            c.add(iv);
           
            c.add(t);
             cButton.add(like);
            cButton.add(dislike);
            cButton.add(partageF);
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
