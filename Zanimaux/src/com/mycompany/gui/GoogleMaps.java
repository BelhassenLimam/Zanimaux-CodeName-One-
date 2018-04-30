/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Azza
 */
public class GoogleMaps {
  private static final String HTML_API_KEY = "AIzaSyBa5cI9VpsAqX1i6Wddu4EWTdDu6L7usf4";
    private Form current;
public static Coord getCoords(String address) {
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", HTML_API_KEY);
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
    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
            Display.getInstance().setCommandBehavior(Display.COMMAND_BEHAVIOR_SIDE_NAVIGATION);
            UIManager.getInstance().getLookAndFeel().setMenuBarClass(SideMenuBar.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
 Form hi = new Form(AffichageAnimaux.nomRef+" sur carte");
  
    public GoogleMaps() {
         Command cmd;
      try {
          cmd = new Command("Back",Image.createImage("/left-arrow(1).png")){
              @Override
              public void actionPerformed(ActionEvent evt) {
                  AffichageAnimaux ff;
                  try {
                      ff = new AffichageAnimaux(AffichageAnimaux.listeA);
                      ff.getF().showBack();
                  } catch (IOException ex) {
                  }
                  
              }
          };
      
         hi.getToolbar().addCommandToLeftBar(cmd);
      } catch (IOException ex) {
      }
        if (current != null) {
            current.show();
            return;
        }
        
       
        hi.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        //cnt.setCameraPosition(getCoords("Mourouj 1"));
       

        Button btnMoveCamera = new Button("Move Camera");
        btnMoveCamera.addActionListener(e->{
            cnt.setCameraPosition(new Coord(getCoords(AffichageAnimaux.adresse).getLatitude(), getCoords(AffichageAnimaux.adresse).getLongitude()));
        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
       
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));
        
        
        cnt.setCameraPosition(new Coord(getCoords(AffichageAnimaux.adresse).getLatitude(), getCoords(AffichageAnimaux.adresse).getLongitude()));
        cnt.zoom(getCoords(AffichageAnimaux.adresse), 15);   
        cnt.addMarker(
                    EncodedImage.createFromImage(markerImg, false),
                    cnt.getCameraPosition(),
                    "Hi marker",
                    "Optional long description",
                     evt -> {
                             ToastBar.showMessage(AffichageAnimaux.nomRef, FontImage.MATERIAL_PLACE);
                     }
            );
        Button btnAddMarker = new Button(AffichageAnimaux.nomRef);
        btnAddMarker.addActionListener(e->{

            cnt.setCameraPosition(new Coord(getCoords(AffichageAnimaux.adresse).getLatitude(), getCoords(AffichageAnimaux.adresse).getLongitude()));
           cnt.zoom(getCoords(AffichageAnimaux.adresse), 15);
            cnt.addMarker(
                    EncodedImage.createFromImage(markerImg, false),
                    cnt.getCameraPosition(),
                    "Hi marker",
                    "Optional long description",
                     evt -> {
                             ToastBar.showMessage("Refuge", FontImage.MATERIAL_PLACE);
                     }
            );

        });

        Button btnAddPath = new Button("Add Path");
        btnAddPath.addActionListener(e->{

//            cnt.addPath(
//                    cnt.getCameraPosition(),
//                    new Coord(-33.866, 151.195), // Sydney
//                    new Coord(-18.142, 178.431),  // Fiji
//                    new Coord(21.291, -157.821),  // Hawaii
//                    new Coord(37.423, -122.091)  // Mountain View
//            );
        });

        Button btnClearAll = new Button("Clear All");
        btnClearAll.addActionListener(e->{
            cnt.clearMapLayers();
        });

//        cnt.addTapListener(e->{
//            TextField enterName = new TextField();
//            Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
//            InteractionDialog dlg = new InteractionDialog("Add Marker");
//            dlg.getContentPane().add(wrapper);
//            enterName.setDoneListener(e2->{
//                String txt = enterName.getText();
//                cnt.addMarker(
//                        EncodedImage.createFromImage(markerImg, false),
//                        cnt.getCoordAtPosition(e.getX(), e.getY()),
//                        enterName.getText(),
//                        "",
//                        e3->{
//                                ToastBar.showMessage("You clicked "+txt, FontImage.MATERIAL_PLACE);
//                        }
//                );
//                dlg.dispose();
//            });
//            dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
//            enterName.startEditingAsync();
//        });

        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.south(
                        FlowLayout.encloseBottom( btnAddMarker)
                )
        );

        hi.add(BorderLayout.CENTER, root);
       // hi.show();

    }
      public Form getF() {
        return hi;
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {
    }
}
