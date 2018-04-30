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
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.location.Geofence;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 *
 * @author Azza
 */
public class AffichageRefuge {
    private Resources theme;
    Form f;
   
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
    public AffichageRefuge() { 
        theme = UIManager.initFirstTheme("/theme");
        
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));  
        f.setTitle("Nos refuges");
        RefugeService ms=new RefugeService();
        ArrayList<Refuge> lis=ms.getListRefuges();
        for (int i =0;i<lis.size();i++)
            
        {   Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container west= new Container(new FlowLayout(CENTER,CENTER));
            Container info = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container bout = new Container(new BoxLayout(BoxLayout.X_AXIS));

            SpanLabel lb = new SpanLabel("");
            Button b =new Button("Cosulter Refuge");
            Button bb =new Button("geo");
            //ImageViewer iv = new ImageViewer(theme.getImage("key.png").scaled(20, 20));
            ImageViewer iv = new ImageViewer(theme.getImage(lis.get(i).getPhotoRefuge()).scaled(350, 200));
            Label t =new Label("Adresse: "+lis.get(i).getAdresseRefuge()+" "+lis.get(i).getGouvernementRefuge()+", "+lis.get(i).getCodePostaleRefuge());
            t.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            t.getAllStyles().setFgColor(0x808080);
            adresse=lis.get(i).getAdresseRefuge()+" "+lis.get(i).getGouvernementRefuge();
            Refuge m = lis.get(i);
            info.add(lb);
            info.add(t); 
            west.add(info);
            c.add(iv);            
            c.add(west);
            c.add(b);
            //bout.add(b);
            //bout.add(bb);
            //c.add(bout);
          
            f.add(c);    
            lb.setText("Nom: "+lis.get(i).getNomRefuge());
            lb.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
            bb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Location locRef=new Location();
                locRef.setLatitude(getCoords(adresse).getLatitude());
                locRef.setLongitude(getCoords(adresse).getLongitude());
//                Location loc=new Location();
//                String adrUser=SignInForm.connectedUser.getAdresse()+" "+SignInForm.connectedUser.getVille();                
//                loc.setLatitude(getCoords(adrUser).getLatitude());
//                loc.setLongitude(getCoords(adrUser).getLongitude());
//                double distance = loc.getDistanceTo(locRef);
//                System.out.println(distance/1000);
//                Dialog.show("Distancein kilometers", String.valueOf(distance/1000), "OK", null);
Geofence gf = new Geofence("test", locRef, 100, 100000);
LocationManager.getLocationManager().addGeoFencing(GeofenceListenerImpl.class, gf);
            }
        });
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
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
