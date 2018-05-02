/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.services.UserService;
import java.io.IOException;








/**
 * GUI builder created Form
 *
 * @author BelhassenLimam
 */
public class Accueil2 extends com.codename1.ui.Form {
 String str;
    private Resources theme;

    public Accueil2() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());
         theme = UIManager.initFirstTheme("/theme");
         UserService u = new UserService();
           str = SignInForm.connectedUser.getRoles();
         
         gui_dres.setIcon(Image.createImage("/dressage.png").scaled(70,70));
          gui_dres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 AffichageParc FormProduit = new AffichageParc();
                FormProduit.getF().show();
            }}); 
         gui_mag.setIcon(Image.createImage("/storeIcon.png").scaled(70,70));
         gui_mag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    AffichageMagasin FormProduit = new AffichageMagasin();
                    FormProduit.getF().show();
                } catch (IOException ex) {
                     }
            }}); 
         gui_doc.setIcon(Image.createImage("/doctorIcon.png").scaled(70,70));
         gui_doc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 AffichageCabinets FormProduit = new AffichageCabinets();
                FormProduit.getF().show();
            }}); 
         gui_pet.setIcon(Image.createImage("/petsitter.png").scaled(70,70));
         gui_pet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 AffichagePromenade FormProduit = new AffichagePromenade();
                FormProduit.getF().show();
            }}); 
         gui_ref.setIcon(Image.createImage("/shelter.png").scaled(70,70));
         gui_ref.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 AffichageRefuge FormProduit = new AffichageRefuge();
                FormProduit.getF().show();
            }}); 
         gui_ev.setIcon(Image.createImage("/event.png").scaled(70,70));
         gui_ev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 
                try {
                   afficherEvenement FormProduit = new afficherEvenement();
                     FormProduit.getF().show();
                } catch (IOException ex) {
                    
                }
               
            }}); 
         Toolbar tb = getToolbar();
         
         Container topBar = BorderLayout.centerAbsolute(new Label());
         Label t = new Label("Menu");
t.getUnselectedStyle().setFgColor(0xffffff);

         topBar.add(BorderLayout.CENTER, t); 
         
         topBar.setUIID("SideCommand");
         tb.addComponentToSideMenu(topBar);
         tb.addMaterialCommandToSideMenu("Accueil", FontImage.MATERIAL_HOME, e -> {}); 
         tb.addCommandToSideMenu("Parc", Image.createImage("/dressage.png").scaled(25,25), e -> {  
             if(str.equals("a:1:{i:0;s:13:\"ROLE_DRESSEUR\";}")){
                 try {
                     Dresseur formp = new Dresseur();
                     formp.show();
                 } catch (IOException ex) {
                      }
             }else{
             AffichageParc FormProduit = new AffichageParc();
                FormProduit.getF().show();
             }});
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
    }
    
    public Accueil2(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
         theme = UIManager.initFirstTheme("/theme");
         
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.X_AXIS));
    private com.codename1.ui.Button gui_dres = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_mag = new com.codename1.ui.Button();
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.X_AXIS));
    private com.codename1.ui.Button gui_doc = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_pet = new com.codename1.ui.Button();
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.X_AXIS));
    private com.codename1.ui.Button gui_ref = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_ev = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.FlowLayout());
        setTitle("Accueil");
        setName("Accueil");
        ((com.codename1.ui.layouts.FlowLayout)getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        ((com.codename1.ui.layouts.FlowLayout)getLayout()).setValign(com.codename1.ui.Component.CENTER);
        addComponent(gui_Container_1);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Container_4);
        gui_Container_4.setName("Container_4");
        gui_Container_4.addComponent(gui_dres);
        gui_Container_4.addComponent(gui_mag);
        gui_dres.setName("dres");
        gui_mag.setName("mag");
        gui_Container_1.addComponent(gui_Container_3);
        gui_Container_3.setName("Container_3");
        gui_Container_3.addComponent(gui_doc);
        gui_Container_3.addComponent(gui_pet);
        gui_doc.setName("doc");
        gui_pet.setName("pet");
        gui_Container_1.addComponent(gui_Container_2);
        gui_Container_2.setName("Container_2");
        gui_Container_2.addComponent(gui_ref);
        gui_Container_2.addComponent(gui_ev);
        gui_ref.setName("ref");
        gui_ev.setName("ev");
        gui_Container_4.setName("Container_4");
        gui_Container_3.setName("Container_3");
        gui_Container_2.setName("Container_2");
        gui_Container_1.setName("Container_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
