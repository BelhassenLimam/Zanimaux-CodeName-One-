/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gui;


import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Articles;
import com.mycompany.entities.Cabinet;
import com.mycompany.entities.Rendezvs;
import com.mycompany.services.ArticleService;
import com.mycompany.services.CabinetService;
import com.mycompany.services.RendezvsService;
import java.util.ArrayList;

/**
 *
 * @author Mariam
 */
public class AffichageCabinets {
     private Resources theme;
    Form f;
   
    
    
    public AffichageCabinets() { 
        theme = UIManager.initFirstTheme("/theme");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));       
        CabinetService ms=new CabinetService();
        ArrayList<Cabinet> lis=ms.getAllCabinet();
        Container c1=new Container(new GridLayout(2, 2));
        for (int i =0;i<lis.size();i++)
            
        {   Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            SpanLabel lb = new SpanLabel("");
            Button b =new Button("Consulter Articles");
            Button b2 =new Button("Prendre Rendezvs");
            ImageViewer iv = new ImageViewer(theme.getImage("photovet1.png").scaled(20, 20));
            //ImageViewer iv = new ImageViewer(theme.getImage(lis.get(i).getPhotovet()).scaled(20,20));
            Label t =new Label(lis.get(i).getAdresseCabinet()+" "+lis.get(i).getVilleCabinet()+", "+lis.get(i).getCodePostaleCabinet());
            Cabinet m = lis.get(i);
            b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArticleService ps = new ArticleService();
                ArrayList<Articles> liste = ps.getAllArticles(m.getCin());
                AffichageArticles FormArticles = new AffichageArticles(liste);
                FormArticles.getF().show();
            }
        });
               b2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            System.out.println(b2.getText());
                            AjoutRendezvs resV = new AjoutRendezvs();
                            AjoutRendezvs.cab = m;
                            resV.start();
                        }
                    });
            
            c.add(iv);
            c.add(lb);
            c.add(t);
            c.add(b);
            c.add(b2);
            //f.add(c);    
            lb.setText(lis.get(i).getVilleCabinet());
            c1.add(c);
        }
        
        f.add(c1);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
