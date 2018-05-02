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
import java.io.IOException;


/**
 * GUI builder created Form
 *
 * @author BelhassenLimam
 */
public class Dresseur extends com.codename1.ui.Form {
 private Resources theme;
    public Dresseur() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());
         theme = UIManager.initFirstTheme("/theme");
         
    
         gui_Button_1.setIcon(Image.createImage("/display.png").scaled(80,80));
     gui_Button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 AffichageParc FormProduit = new AffichageParc();
                FormProduit.getF().show();
            }}); 
          
          
          gui_Button_2.setIcon(Image.createImage("/stat.png").scaled(80,80));
          gui_Button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 AffichageStat FormProduit = new AffichageStat();
                FormProduit.getF().show();
            }}); 
          
          Toolbar tb = getToolbar();
         
         Container topBar = BorderLayout.centerAbsolute(new Label());
         Label t = new Label("Menu");
t.getUnselectedStyle().setFgColor(0xffffff);

         topBar.add(BorderLayout.CENTER, t); 
         
         topBar.setUIID("SideCommand");
         tb.addComponentToSideMenu(topBar);
         tb.addMaterialCommandToSideMenu("Accueil", FontImage.MATERIAL_HOME, e -> {}); 
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
    }
    
    public Dresseur(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.X_AXIS));
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.FlowLayout());
        setTitle("Dresseur");
        setName("Dresseur");
        ((com.codename1.ui.layouts.FlowLayout)getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        ((com.codename1.ui.layouts.FlowLayout)getLayout()).setValign(com.codename1.ui.Component.CENTER);
        addComponent(gui_Container_1);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Container_2);
        gui_Container_2.setName("Container_2");
        gui_Container_2.addComponent(gui_Button_2);
        gui_Container_2.addComponent(gui_Button_1);
        gui_Button_2.setText("");
        gui_Button_2.setName("Button_2");
        gui_Button_1.setText("");
        gui_Button_1.setName("Button_1");
        gui_Container_2.setName("Container_2");
        gui_Container_1.setName("Container_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
