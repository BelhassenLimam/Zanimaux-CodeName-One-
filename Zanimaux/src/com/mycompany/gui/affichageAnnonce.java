/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.mycompany.entities.Annonce;
import com.mycompany.services.AnnonceService;
import com.mycompany.services.EvenementService;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evenement;
import java.util.ArrayList;

/**
 *
 * @author Maroua
 */
public class affichageAnnonce {
    
    Form f;
    Resources theme;
    Label lb;
    Label lb2;
    Container contv;
    Container conth;
    
    
        public affichageAnnonce(){ 
        theme = UIManager.initFirstTheme("/theme");
        f = new Form();
        contv = new Container(BoxLayout.y());
                     
        AnnonceService as=new AnnonceService();
        ArrayList<Annonce> lis=as.getAllAnnonce();
        System.out.println(lis.size());
        
        for (int i =0;i<lis.size();i++)
        {
            conth = new Container(BoxLayout.x());
            ImageViewer imgv=new ImageViewer();
            imgv.setImage(theme.getImage(lis.get(i).getPieceJointe()).scaled(300, 300));
            Image img= imgv.getImage();
          
           MultiButton evt = new MultiButton(lis.get(i).getTitre());
            Annonce a = lis.get(i);
            evt.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent evt) {
                     AnnonceService ess=new AnnonceService();
                 ArrayList<Annonce> lis= ess.getListAnnonceById(a.getIdAnnonce());
                 
                 detailsAnnonce de =new detailsAnnonce(lis);
                 de.getF().show();
                }
            });
           
          conth.add(evt);
        contv.add(conth);
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
