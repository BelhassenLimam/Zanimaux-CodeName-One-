/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.Callback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 *
 * @author Azza
 */
public class GoogleMapsGeocodeTest {
     private static final String MAPS_KEY = "AIzaSyBa5cI9VpsAqX1i6Wddu4EWTdDu6L7usf4"; // Your maps key here
   private Form current;

    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }
        Form hi = new Form("Maps Geocode Test");
        hi.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        TextField textField = new TextField("");
        textField.setHint("Address/Coord");
        hi.add(textField);

        Button address = new Button("Get Address");
        address.addActionListener(evt -> {
            String text = textField.getText();
          //  String formattedAddress = getFormattedAddress(new Coord(Double.parseDouble(text.split(",")[0]), Double.parseDouble(text.split(",")[1])));
          //  ToastBar.showErrorMessage(formattedAddress);
        });
        hi.add(address);

        Button coord = new Button("Get Coord");
        coord.addActionListener(evt -> {
            Coord coords = getCoords(textField.getText());
            ToastBar.showErrorMessage(coords.toString());
        });

        hi.add(coord);
        hi.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {
    }


    public static String getFormattedAddress(Coord coord) {
        String ret = "";
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", MAPS_KEY);
            request.addArgument("latlng", coord.getLatitude() + "," + coord.getLongitude());

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0)
                    ret = (String) ((LinkedHashMap) results.get(0)).get("formatted_address");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void getFormattedAddressAsync(Coord coord, Callback callback) {
        ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false) {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                String ret = "";
                Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(input, "UTF-8"));
                if (response.get("results") != null) {
                    ArrayList results = (ArrayList) response.get("results");
                    if (results.size() > 0)
                        ret = (String) ((LinkedHashMap) results.get(0)).get("formatted_address");
                }
                callback.onSucess(ret);
            }


        };
        request.addArgument("key", MAPS_KEY);
        request.addArgument("latlng", coord.getLatitude() + "," + coord.getLongitude());

        NetworkManager.getInstance().addToQueue(request);
    }

    public static Coord getCoords(String address) {
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", MAPS_KEY);
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

    public static void getCoordsAsync(String address, Callback callback) {
        ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false) {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                Coord ret = null;
                Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(input, "UTF-8"));
                if (response.get("results") != null) {
                    ArrayList results = (ArrayList) response.get("results");
                    if (results.size() > 0) {
                        LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) results.get(0)).get("geometry")).get("location");
                        ret = new Coord((double) location.get("lat"), (double) location.get("lng"));
                    }
                }
                callback.onSucess(ret);
            }


        };
        request.addArgument("key", MAPS_KEY);
        request.addArgument("address", address);

        NetworkManager.getInstance().addToQueue(request);
}
}
