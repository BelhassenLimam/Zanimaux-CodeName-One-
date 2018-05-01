/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.mycompany.entities.Parc;
import com.mycompany.services.ParcService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SliderBridge;
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
import com.mycompany.entities.User;
import static com.mycompany.gui.SignInForm.connectedUser;
import com.mycompany.services.AvisService;
import com.mycompany.services.UserService;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.TextField;

/**
 *
 * @author macbookpro
 */
public class AffichageParc 
{
    
   String str;
    private Resources theme;
    Form f;
   
    
    
    public AffichageParc() { 
       try {
           theme = UIManager.initFirstTheme("/theme");
           f = new Form(new BoxLayout(BoxLayout.Y_AXIS));
           ParcService ms=new ParcService();
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
           tb.addCommandToSideMenu("Evenement", Image.createImage("/event.png").scaled(25,25), e -> {try {
               afficherEvenement FormProduit = new afficherEvenement();
               FormProduit.getF().show();
               } catch (IOException ex) {
                    }
});
           tb.addCommandToSideMenu("Annonce", Image.createImage("/annonce.png").scaled(25,25), e -> {try {
               affichageAnnonce FormProduit = new affichageAnnonce();
               FormProduit.getF().show();
               } catch (IOException ex) {
                 }
});
           tb.addCommandToOverflowMenu("Mes parcs",Image.createImage("/event.png").scaled(25,25),e->{
               
               
            Form f2 = new Form(new BoxLayout(BoxLayout.Y_AXIS));   
           ms.getParcByCin(str);
           Toolbar tb2 = f2.getToolbar();
           tb2.addMaterialCommandToLeftBar("Retour",FontImage.MATERIAL_ARROW_BACK, e1->{
                        f.showBack();
                    });
            ArrayList<Parc> lis2=ms.getParcByCin(str);
            
            for (int i =0;i<lis2.size();i++)
               
           {
               Container co = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               co.getUnselectedStyle().setPadding(10, 5, 5, 5);
               
               Container cp2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
               
               Container cp3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               Label lbo = new Label();
               
               //ImageViewer iv = new ImageViewer(theme.getImage("key.png").scaled(20, 20));
               ImageViewer ivo = new ImageViewer(theme.getImage(lis2.get(i).getPhotoParc()).scaled(100, 100));
               Label ado = new Label("Adresse :");
               ado.getUnselectedStyle().setFgColor(0xf64139);
               Label to =new Label(lis2.get(i).getAdresseParc()+" "+lis2.get(i).getVilleParc()+", "+lis2.get(i).getCodePostaleParc());
               Parc m = lis2.get(i);
               
                cp2.add(ivo);
               cp3.add(lbo);
               cp3.add(ado);
               cp3.add(to);
               cp2.add(cp3);
               co.add(cp2);
              
               
               f2.add(co);
                lbo.setText(lis2.get(i).getNomParc());
           
           }
            f2.show();
              
               
           
              
           });
           UserService u = new UserService();
           str = SignInForm.connectedUser.getCin();
           AvisService a = new AvisService();
           
           ArrayList<Parc> lis=ms.getAllParc();
           
           for (int i =0;i<lis.size();i++)
               
           {
               
               
               Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               c.getUnselectedStyle().setPadding(10, 5, 5, 5);
               
               Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
               
               Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               Label lb = new Label();
               
               //ImageViewer iv = new ImageViewer(theme.getImage("key.png").scaled(20, 20));
               ImageViewer iv = new ImageViewer(theme.getImage(lis.get(i).getPhotoParc()).scaled(100, 100));
               Label ad = new Label("Adresse :");
               ad.getUnselectedStyle().setFgColor(0xf64139);
               Label t =new Label(lis.get(i).getAdresseParc()+" "+lis.get(i).getVilleParc()+", "+lis.get(i).getCodePostaleParc());
               Parc m = lis.get(i);
               
               
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
                                   AffichageParc loginForm = new AffichageParc();
                                   loginForm.getF().show();
                               }});
                           c.add(FlowLayout.encloseCenter(starRank));
                           c.add(b1);
                       };
                       
                   };
               });
               
               
               c2.add(iv);
               c3.add(lb);
               c3.add(ad);
               c3.add(t);
               c2.add(c3);
               c.add(c2);
               
               
               
               
               f.add(c);
               
               
               lb.setText(lis.get(i).getNomParc());
               
               
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
