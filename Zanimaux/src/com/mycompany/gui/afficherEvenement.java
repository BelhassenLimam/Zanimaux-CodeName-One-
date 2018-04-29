/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entities.Evenement;
import com.mycompany.services.EvenementService;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;



/**
 *
 * @author Maroua
 */
public class afficherEvenement {
    
    Form f;
    Resources theme;
    Label lb;
    Label lb2;
    Container contv;
    Container conth;
     Container contPage;
      Container contG;
     int nbPages = 0;
     int nbEl=3;
     int IndexPages = 0;
    
    
      public afficherEvenement(){ 
        theme = UIManager.initFirstTheme("/theme");
        f = new Form();
        contv = new Container(BoxLayout.y());
         contG = new Container(BoxLayout.y());
        SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
       
               
        EvenementService es=new EvenementService();
        ArrayList<Evenement> lis=es.getAllEvent();
        
        
        int nb = lis.size();
        if ((nb % nbEl) == 0) {
            nbPages = nb / nbEl;            
        } else {
            nbPages = ((nb / nbEl) + 1);            
            
        }
        
        contPage = new Container(BoxLayout.x());
        
        for(int i=1;i<=nbPages;i++){
            Button nbP = new Button(Integer.toString(i));
            nbP.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
            contv.removeAll();
          IndexPages= Integer.parseInt (nbP.getText());
          IndexPages=IndexPages-1;
         for (int j= IndexPages * nbEl;j<lis.size();j++)
            {
                 if (j <= (IndexPages * nbEl + (nbEl - 1))){
               
            conth = new Container(BoxLayout.x());
            ImageViewer imgv=new ImageViewer();
            imgv.setImage(theme.getImage(lis.get(j).getImageEvt()).scaled(300, 300));
            Image img= imgv.getImage();
          
            format.applyPattern("dd/MM/yyyy");
            MultiButton evt1 = new MultiButton(lis.get(j).getTitre());
            
            evt1.setIcon(img);
            evt1.setTextLine2(format.format(lis.get(j).getDateDebut())+" - "+ format.format(lis.get(j).getDateFin()));
            Evenement e = lis.get(j);
            evt1.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent evt) {
                     EvenementService ess=new EvenementService();
                 ArrayList<Evenement> lis= ess.getListEvenetById(e.getIdEvt());
                 
                  
                    try {
                        detailsEvent de = new detailsEvent(lis);
                        de.getF().show();
                    } catch (IOException ex) {
                        
                    }
                 
                }
            });
          
          conth.add(evt1);
        
        contv.add(conth);
          }
            }
                    
           f.refreshTheme();
          }
           
            });
           contPage.add(nbP);
          //contG.add(contPage);
        }
        
        //f.add(contG);
        
        System.out.println(lis.size());
        Label titreInterface= new Label("Liste des evenements");
        FontImage.setMaterialIcon(titreInterface, FontImage.MATERIAL_EVENT);
        
       contG.add(titreInterface);
        for (int i =0;i<lis.size();i++)
        {
             if (i <= (0 * nbEl + (nbEl - 1))){
            conth = new Container(BoxLayout.x());
            ImageViewer imgv=new ImageViewer();
            imgv.setImage(theme.getImage(lis.get(i).getImageEvt()).scaled(300, 300));
            Image img= imgv.getImage();
          
            format.applyPattern("dd/MM/yyyy");
            MultiButton evt = new MultiButton(lis.get(i).getTitre());
            
            evt.setIcon(img);
            evt.setTextLine2(format.format(lis.get(i).getDateDebut())+" - "+ format.format(lis.get(i).getDateFin()));
            Evenement e = lis.get(i);
            evt.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent evt) {
                     EvenementService ess=new EvenementService();
                 ArrayList<Evenement> lis= ess.getListEvenetById(e.getIdEvt());
                 
                  
                    try {
                        detailsEvent de = new detailsEvent(lis);
                        de.getF().show();
                    } catch (IOException ex) {
                        
                    }
                 
                }
            });
          
          conth.add(evt);
         
        contv.add(conth);
          
     }}
        contG.add(contv);
        contG.add(contPage);
       f.add(contG);
        
         }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
    
}
