/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entities.Evenement;
import com.mycompany.services.EvenementService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import javafx.scene.control.ListView;

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
    
    
      public afficherEvenement(){ 
        theme = UIManager.initFirstTheme("/theme");
        f = new Form();
        contv = new Container(BoxLayout.y());
        
               
        EvenementService es=new EvenementService();
        ArrayList<Evenement> lis=es.getAllEvent();
        System.out.println(lis.size());
        for (int i =0;i<lis.size();i++)
        {
            
            conth = new Container(BoxLayout.x());
            ImageViewer img=new ImageViewer();
            img.setImage(theme.getImage(lis.get(i).getImageEvt()).scaled(150, 150));
            lb = new Label("\n");
            lb.setWidth(20);
             lb.setText(lis.get(i).getLieu());
            System.out.println(lis.get(i).getLieu());
            lb2=new Label("\n");
            lb2.setWidth(20);
            lb2.setText(lis.get(i).getTitre());
            System.out.println(lis.get(i).getTitre());
            conth.add(img);
            conth.add(lb);
           // conth.add(lb2);
            contv.add(conth);
            // f.add(img);
        }
     
       f.add(contv);
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
    
}
