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
<<<<<<< HEAD
=======
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
>>>>>>> fc591e646f896febc419af6176f26a30df34e3e4
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.TextField;

/**
 *
 * @author macbookpro
 */
public class AffichageParc 
{
    private Resources theme;
    Form f;
   
    
    
    public AffichageParc() { 
        theme = UIManager.initFirstTheme("/theme");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));       
        ParcService ms=new ParcService();
<<<<<<< HEAD
=======
         UserService u = new UserService();
         str = SignInForm.connectedUser.getCin();
         AvisService a = new AvisService();
         
>>>>>>> fc591e646f896febc419af6176f26a30df34e3e4
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
            Label t =new Label(lis.get(i).getAdresseParc()+" "+lis.get(i).getVilleParc()+", "+lis.get(i).getCodePostaleParc());
            Parc m = lis.get(i);
       
         
      ConnectionRequest con;
        con = new ConnectionRequest();
        con.setUrl("http://localhost:8888/VerifAvis.php?idParc=" +m.getId()+ "&cinUser=" +str+"");
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
<<<<<<< HEAD
=======
                System.out.println(str);
                Avis a1 = new Avis(m.getId(),starRank.getProgress(),str);
                a.addavis(a1);
                AffichageParc loginForm = new AffichageParc();
                loginForm.getF().show();
>>>>>>> fc591e646f896febc419af6176f26a30df34e3e4
            }}); 
                c.add(FlowLayout.encloseCenter(starRank));
                c.add(b1);
                };
               
            };
    });
            

            c2.add(iv);
            c3.add(lb);
            c3.add(t);
            c2.add(c3);
            c.add(c2);
           
            
            
            
            f.add(c); 
            
        
            lb.setText(lis.get(i).getNomParc());
        
        
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
