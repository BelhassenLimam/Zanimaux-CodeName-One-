/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entities.Magasin;
import com.mycompany.entities.Parc;
import com.mycompany.entities.Produit;
import com.mycompany.entities.Promenade;
import com.mycompany.services.MagasinService;
import com.mycompany.services.ParcService;
import com.mycompany.services.ProduitService;
import com.mycompany.services.PromenadeService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Avis;
import com.mycompany.services.AvisService;
import com.mycompany.services.UserService;
import java.io.IOException;
import java.util.ArrayList;



/**
 *
 * @author macbookpro
 */
public class AffichagePromenade 
{
    private Resources theme;
    Form f;
   String str;
    
    
    public AffichagePromenade() { 
        try {
            theme = UIManager.initFirstTheme("/theme");
            f = new Form(new BoxLayout(BoxLayout.Y_AXIS));
            Toolbar tb = f.getToolbar();
            
            Container topBar = BorderLayout.centerAbsolute(new Label());
            Label menu = new Label("Menu");
            menu.getUnselectedStyle().setFgColor(0xffffff);
            
            topBar.add(BorderLayout.CENTER, menu);
            
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
            tb.addCommandToSideMenu("Evenement", Image.createImage("/event.png").scaled(25,25), e -> {afficherEvenement FormProduit = new afficherEvenement();
            FormProduit.getF().show();});
            tb.addCommandToSideMenu("Annonce", Image.createImage("/annonce.png").scaled(25,25), e -> {affichageAnnonce FormProduit = new affichageAnnonce();
            FormProduit.getF().show();});
            
            PromenadeService ms=new PromenadeService();
            UserService u = new UserService();
            str = SignInForm.connectedUser.getCin();
             tb.addCommandToOverflowMenu("Mes parcs",Image.createImage("/event.png").scaled(25,25),e->{
               
               
            Form f2 = new Form(new BoxLayout(BoxLayout.Y_AXIS));   
           ms.getPromenadeByCin(str);
           Toolbar tb2 = f2.getToolbar();
           tb2.addMaterialCommandToLeftBar("Retour",FontImage.MATERIAL_ARROW_BACK, e1->{
                        f.showBack();
                    });
            ArrayList<Promenade> lis2=ms.getPromenadeByCin(str);
            
            for (int i =0;i<lis2.size();i++)
               
           {
               Container co = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               co.getUnselectedStyle().setPadding(10, 5, 5, 5);
               
               Container cp2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
               
               Container cp3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               Label lbo = new Label();
               
               //ImageViewer iv = new ImageViewer(theme.getImage("key.png").scaled(20, 20));
               ImageViewer iv = new ImageViewer(theme.getImage(lis2.get(i).getPhotoPromenade()).scaled(100, 100));
                Label ad = new Label("Lieu :");
                ad.getUnselectedStyle().setFgColor(0xf64139);
                Label t =new Label(lis2.get(i).getLieuPromenade());
                Label ad2 = new Label("Type :");
                ad2.getUnselectedStyle().setFgColor(0xf64139);
                Label t1 =new Label(lis2.get(i).getTypePromenade());
               
                cp2.add(iv);
                cp3.add(lbo);
                cp3.add(ad2);
                cp3.add(t1);
                cp3.add(ad);
                cp3.add(t);
                cp2.add(cp3);
                co.add(cp2);
                
                
                f.add(co);
                
                lbo.setText(lis2.get(i).getNomPromenade());
                
           }
            f2.show();
              
               
           
              
           });
            
            AvisService a = new AvisService();
            
            ArrayList<Promenade> lis=ms.getAllPromenade();
            for (int i =0;i<lis.size();i++)
            {
                Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                
                Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Label lb = new Label();
                
                //ImageViewer iv = new ImageViewer(theme.getImage("key.png").scaled(20, 20));
                ImageViewer iv = new ImageViewer(theme.getImage(lis.get(i).getPhotoPromenade()).scaled(100, 100));
                Label ad = new Label("Lieu :");
                ad.getUnselectedStyle().setFgColor(0xf64139);
                Label t =new Label(lis.get(i).getLieuPromenade());
                Label ad2 = new Label("Type :");
                ad2.getUnselectedStyle().setFgColor(0xf64139);
                Label t1 =new Label(lis.get(i).getTypePromenade());
                
                Promenade m = lis.get(i);
                
                ConnectionRequest con;
                con = new ConnectionRequest();
                con.setUrl("http://localhost:8888/WebServiceMobile/VerifAvis.php?idParc=" +m.getId()+ "&cinUser=" +str+"");
                NetworkManager.getInstance().addToQueue(con);
                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        String av2 = new String(con.getResponseData());
                        System.out.println(av2);
                        if(av2.equalsIgnoreCase("n existe pas")){
                            
                            Slider starRank = new Slider();
                            starRank.setEditable(true);
                            starRank.setMinValue(0);
                            starRank.setMaxValue(5);
                            Font fnt = Font.createTrueTypeFont("native:mainLight", "native:mainLight").
                                    derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
                            Style s = new Style(0xffff33, 0, fnt, (byte)0);
                            Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
                            s.setOpacity(100);
                            s.setFgColor(0);
                            Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
                            initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
                            initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
                            initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
                            initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
                            starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
                            Button b1 = new Button("Evaluer");
                            
                            b1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    
                                    System.out.println(starRank.getProgress());
                                    System.out.println(m.getId());
                                    System.out.println(str);
                                    Avis a1 = new Avis(m.getId(),starRank.getProgress(),str);
                                    a.addavis(a1);
                                    AffichagePromenade loginForm = new AffichagePromenade();
                                    loginForm.getF().show();
                                }});
                            c.add(FlowLayout.encloseCenter(starRank));
                            c.add(b1);
                        };
                        
                    };
                });
                
                c2.add(iv);
                c3.add(lb);
                c3.add(ad2);
                c3.add(t1);
                c3.add(ad);
                c3.add(t);
                c2.add(c3);
                c.add(c2);
                
                
                f.add(c);
                
                lb.setText(lis.get(i).getNomPromenade());
                
            }
        } catch (IOException ex) {
               }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}
}
