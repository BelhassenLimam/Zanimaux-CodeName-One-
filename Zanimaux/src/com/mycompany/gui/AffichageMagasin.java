/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entities.Magasin;
import com.mycompany.entities.Produit;
import com.mycompany.services.MagasinService;
import com.mycompany.services.ProduitService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Panier;
import com.mycompany.services.PanierService;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author macbookpro
 */
public class AffichageMagasin 
{
    private Resources theme;
    Form f;
   
    
    
    public AffichageMagasin() throws IOException { 
        theme = UIManager.initFirstTheme("/theme");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS)); 
                Toolbar tb = f.getToolbar();
         
         Container topBar = BorderLayout.centerAbsolute(new Label());
         
         Label a = new Label("Menu");
        a.getUnselectedStyle().setBgColor(0x3498D1);

        a.getUnselectedStyle().setFgColor(0xFFFFFF);
         topBar.add(BorderLayout.CENTER, a);
         topBar.setUIID("SideCommand");
         tb.addComponentToSideMenu(topBar);
         tb.addCommandToSideMenu("Parc", Image.createImage("/dressage.png").scaled(25,25), e -> {  AffichageParc FormProduit = new AffichageParc();
         FormProduit.getF().show();});
         tb.addCommandToSideMenu("Magasin", Image.createImage("/storeIcon.png").scaled(25,25), e -> {try {
             AffichageMagasin FormProduit = new AffichageMagasin();
             FormProduit.getF().show();
            } catch (IOException ex) {
               ;
            }
});
         tb.addCommandToSideMenu("Veterinaire", Image.createImage("/doctorIcon.png").scaled(25,25), e -> {AffichageCabinets FormProduit = new AffichageCabinets();
                FormProduit.getF().show();});
         tb.addCommandToSideMenu("PetSitter", Image.createImage("/petsitter.png").scaled(25,25), e -> {AffichagePromenade FormProduit = new AffichagePromenade();
                FormProduit.getF().show();});
         tb.addCommandToSideMenu("Refuge", Image.createImage("/shelter.png").scaled(25,25), e -> {AffichageRefuge FormProduit = new AffichageRefuge();
                FormProduit.getF().show();});
         tb.addCommandToSideMenu("Evenement", Image.createImage("/event.png").scaled(25,25), e -> { 
            try {
               afficherEvenement FormProduit = new afficherEvenement();
                FormProduit.getF().show();
            } catch (IOException ex) {
                
            }
                });
         tb.addCommandToSideMenu("Annonce", Image.createImage("/annonce.png").scaled(25,25), e -> { 
            try {
               affichageAnnonce FormProduit = new affichageAnnonce();
                FormProduit.getF().show();
            } catch (IOException ex) {
                
            }
                });
        
            Image panier= Image.createImage("/cart.png");
       
           
        f.getToolbar().addCommandToRightBar("", panier, (e) -> {AffichagePanier h=null;
            try {
                h = new AffichagePanier();
            } catch (IOException ex) {
            }
          h.getF().show();});
        Style stitle = f.getToolbar().getAllStyles();
        stitle.setBgColor(0xff);
        MagasinService ms=new MagasinService();
        ArrayList<Magasin> lis=ms.getAllMagasin();
        for (int i =0;i<lis.size();i++)
            
        {   Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            SpanLabel lb = new SpanLabel("");
            Button b =new Button("Cosulter Magasin");
           // ImageViewer iv = new ImageViewer(theme.getImage("cart.png").scaled(20, 20));
            ImageViewer iv = new ImageViewer(theme.getImage(lis.get(i).getPhotoMagasin()).scaled(350, 200));
            Label t =new Label(lis.get(i).getAdresseMagasin()+" "+lis.get(i).getVilleMagasin()+", "+lis.get(i).getCodePostaleMagasin());
            Magasin m = lis.get(i);
            b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    ProduitService ps = new ProduitService();
                    ArrayList<Produit> liste = ps.getAllProduit(m.getIdMagasin());
                    AffichageProduit FormProduit = new AffichageProduit(liste);
                    FormProduit.getF().show();
                } catch (IOException ex) {
                }
            }
        });
            c.add(iv);
            c.add(lb);
            c.add(t);
            c.add(b);
            f.add(c);    
            lb.setText(lis.get(i).getNomMagasin());
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
