/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entities.Animal;
import com.mycompany.services.RefugeService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import java.util.ArrayList;
import com.mycompany.entities.Refuge;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.gif.GifImage;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.location.Geofence;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getResourceAsStream;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.UserService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;



/**
 *
 * @author Azza
 */
public class AffichageRefuge {
    private Resources theme;
    Form f;
   public static String RefugePlusProche;
   public static String NomRefProche;
    public static Coord getCoords(String address) {
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", "AIzaSyBa5cI9VpsAqX1i6Wddu4EWTdDu6L7usf4");
            request.addArgument("address", address);

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0) {
                    LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) results.get(0)).get("geometry")).get("location");
                    ret = new Coord((double) location.get("lat"), (double) location.get("lng"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public String adresse;
    public double distance;
    public double min;
    public Location locRef=new Location();
    public AffichageRefuge() { 
        theme = UIManager.initFirstTheme("/theme");
        
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));  
        f.setTitle("Nos refuges");
        try {
            Container gif=new Container(new BorderLayout());
            gif.add(CENTER, new ScaleImageLabel(GifImage.decode(getResourceAsStream("/nearYou.gif"), 1177720)));
     
            f.add(gif);
            
        } catch (IOException ex) {
        }
        Command cmd;
        try {
            cmd = new Command("Back",Image.createImage("/left-arrow(1).png")){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Accueil2 ff;
                    try {
                        ff = new Accueil2();
                          ff.show();
                    } catch (IOException ex) {
                    }
                  
                }
            };
             f.getToolbar().addCommandToLeftBar(cmd);
        } catch (IOException ex) {
        }
        
       Label rpp=new Label("Le plus proche de chez vous");
       rpp.animate();
       rpp.getAllStyles().setFgColor(0x000080);
       rpp.getAllStyles().setAlignment(CENTER);
       rpp.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_UNDERLINED, Font.SIZE_LARGE));
       rpp.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                RefPlusProche rp=new RefPlusProche();
                     rp.getF().show();
            }
        });
       f.add(rpp);
        ArrayList<Double> listeDist=new ArrayList<>();
        ArrayList<String> listeAdr=new ArrayList<>();
        ArrayList<String> listeNomRef=new ArrayList<>();
        RefugeService ms=new RefugeService();
        
        ArrayList<Refuge> lis=ms.getListRefuges();
        for (int i =0;i<lis.size();i++)
            
        {   Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label lb = new Label("");
            lb.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
             lb.getAllStyles().setFgColor(0x808080);
            lb.getAllStyles().setAlignment(CENTER);
            Button b =new Button("Cosulter Refuge");
            b.setPreferredSize(new Dimension(90,50));
            ImageViewer iv = new ImageViewer(theme.getImage(lis.get(i).getPhotoRefuge()).scaled(350, 200));
            Label t =new Label("Adresse: "+lis.get(i).getAdresseRefuge()+" "+lis.get(i).getGouvernementRefuge()+", "+lis.get(i).getCodePostaleRefuge());
            t.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            t.getAllStyles().setFgColor(0xC0C0C0);
            t.getAllStyles().setAlignment(CENTER);
            adresse=lis.get(i).getAdresseRefuge()+" "+lis.get(i).getGouvernementRefuge();
            Refuge m = lis.get(i);
            
            c.add(lb);
            c.add(iv);            
            c.add(t);
            c.add(b);
            
          
            f.add(c);    
            lb.setText(lis.get(i).getNomRefuge());
                locRef.setLatitude(getCoords(adresse).getLatitude());
                locRef.setLongitude(getCoords(adresse).getLongitude());
                Location loc=new Location();
                UserService us=new UserService();
                User user=us.getUserBycin(SignInForm.connectedUser.getCin());
                String adrUser=user.getAdresse()+" "+user.getVille();                
                loc.setLatitude(getCoords(adrUser).getLatitude());
                loc.setLongitude(getCoords(adrUser).getLongitude());
                listeAdr.add(adresse);
                listeNomRef.add(lis.get(i).getNomRefuge());
                distance = loc.getDistanceTo(locRef);
                 listeDist.add(distance);
                
                
         
                
                
               
            
            b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    RefugeService rs=new RefugeService();
                    ArrayList<Animal> lis= rs.getListAnimauxByRef(m.getImmatriculation());
                    AffichageAnimaux AA =new AffichageAnimaux(lis);
                    AA.getF().show();
                } catch (IOException ex) {
                   
                }
            }
        });
        }
         min = listeDist.get(0);
        for(int j=1;j<listeDist.size();j++){
            if(listeDist.get(j)<min){
             min=listeDist.get(j);
            }
            
                      }
            RefugePlusProche = listeAdr.get(listeDist.indexOf(min));
        NomRefProche=listeNomRef.get(listeDist.indexOf(min));
      
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
