/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gui;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Rendezvs;
import com.mycompany.entities.User;
import static com.mycompany.gui.SignInForm.connectedUser;
import com.mycompany.services.RendezvsService;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Mariam
 */
public class CalendarForm extends Form {
    StringBuffer str = new StringBuffer();
    int ch;
    public CalendarForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public CalendarForm(com.codename1.ui.util.Resources resourceObjectInstance) {
            Label notiflabel =new Label(" ");
            notiflabel.setVisible(false);
        ArrayList<User> userNotifList =new RendezvsService().getUserNotifNotVu();
            for(User u : userNotifList){
                
                if(u.getCin().equals(connectedUser.getCin())){
                    System.out.println(u.getCin());
                    notiflabel.setText("Un rendezvs est ajouté");
                  }
             
               
                    new RendezvsService().setUserNotifVu(connectedUser.getCin());
                
             
            }
            notiflabel.setVisible(true); 
        
        initGuiBuilderComponents(resourceObjectInstance);
        setLayout(BoxLayout.y());
        setScrollableY(true);
        getContentPane().setScrollVisible(false);
        getToolbar().setUIID("Container");
        Button b = new Button(" ");
        b.setUIID("Container");
        getToolbar().setTitleComponent(b);
        getTitleArea().setUIID("Container");
      //  installSidemenu(resourceObjectInstance);
        gui_Calendar_1.setTwoDigitMode(true);
       
        Picker p = new Picker();
        b.addActionListener(e -> {
            p.pressed(); 
            p.released();
        });
        p.addActionListener(e -> {
            gui_Calendar_1.setCurrentDate(p.getDate());
            gui_Calendar_1.setSelectedDate(p.getDate());
            gui_Calendar_1.setDate(p.getDate());
        });
        p.setFormatter(new SimpleDateFormat("MMMM"));
        p.setDate(new Date());
        p.setUIID("CalendarDateTitle");
        Container cnt = BoxLayout.encloseY(
                p,
                new Label(resourceObjectInstance.getImage("calendar-separator.png"), "CenterLabel")
        );
        
        BorderLayout bl = (BorderLayout)gui_Calendar_1.getLayout();
        Component combos = bl.getNorth();
        gui_Calendar_1.replace(combos, cnt, null);
          ConnectionRequest con = new ConnectionRequest();
          String immatriculecabinet = "1145";
        con.setUrl("http://localhost/Mobile/ShowRDV.php?immatriculecabinet="+immatriculecabinet);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                             ArrayList<Rendezvs> data = getListRDV(new String(con.getResponseData()));
                for (Rendezvs e : data) {
                    
                    
                       Util.register("user", User.class);
                    User uc = (User) Storage.getInstance().readObject("user");
                    if (uc != null) {
                        if (uc.getCin() == e.getCin()) {
                           // particip.setText("You're the owner");
                     //       refreshTheme();
                        ConnectionRequest cnx1 = new ConnectionRequest("http://localhost/Mobile/ShowrRdvdetail.php?idrdv="+e.getIdrdv()) {
                        @Override
                        protected void readResponse(InputStream input) throws IOException {

                          Rendezvs rdv = new Rendezvs();
                          add(createEntry(resourceObjectInstance, false, e.getHeurerdv(), e.getImmatriculecabinet()));
                    refreshTheme();
                            
                       
                        }

                        };
                    NetworkManager.getInstance().addToQueue(cnx1);
                      
                     
                        }}}
       }});
         NetworkManager.getInstance().addToQueue(con);}

    
    
        
    private Container createEntry(Resources res, boolean selected, Date Heurerdv, String immatriculecabinet) {
        String temps= Heurerdv.toString();
        Component time = new Label(temps, "CalendarHourUnselected");
        if(selected) {
            time.setUIID("CalendarHourSelected");
        }
        
        Container circleBox = BoxLayout.encloseY(new Label(res.getImage("label_round-selected.png")),
                new Label("-", "OrangeLine"),
                new Label("-", "OrangeLine")
        );
        
        Container cnt = new Container(BoxLayout.x());
   
        Container mainContent = BoxLayout.encloseY(
                BoxLayout.encloseY(
                        new Label(immatriculecabinet, "SmallLabel"),
                        new Label(temps, "SmallThinLabel")),
                       cnt
              
        );
        
//        Label redLabel = new Label("", "RedLabelRight");
//        FontImage.setMaterialIcon(redLabel, FontImage.MATERIAL_LOCATION_ON);
//        Container loc = BoxLayout.encloseX(
//                redLabel,
//                new Label("Location:", "TinyThinLabelRight"),
//                new Label(location, "TinyBoldLabel")
//        );
        
        mainContent= BorderLayout.center(mainContent).
                add(BorderLayout.WEST, circleBox);
        
        return BorderLayout.center(mainContent).
                add(BorderLayout.WEST, FlowLayout.encloseCenter(time)).
//                add(BorderLayout.SOUTH, loc).
        add(BorderLayout.NORTH,new Label("     ", "Separator"));
    }
    
//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Calendar gui_Calendar_1 = new com.codename1.ui.Calendar();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.GridLayout(2, 1));
        setTitle("");
        setName("CalendarForm");
        addComponent(gui_Calendar_1);
        gui_Calendar_1.setName("Calendar_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    protected boolean isCurrentCalendar() {
        return true;
    }

    @Override
    protected void initGlobalToolbar() {
        setToolbar(new Toolbar(true));
    }


      public ArrayList<Rendezvs> getListRDV(String json) {
        ArrayList<Rendezvs> liste = new ArrayList<>();
        System.out.println("JSON*************\n" + json);
        try {

            JSONParser j = new JSONParser();

            Map<String, Object> lie = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) lie.get("root");

            for (Map<String, Object> obj : list) {
               Rendezvs e = new Rendezvs();//id, json, status);
                e.setIdrdv((int) Integer.parseInt(obj.get("idrdv").toString()));
                e.setCin((obj.get("cin").toString()));
                e.setImmatriculecabinet(obj.get("immatriculecabinet").toString());
               
               // e.setHeurerdv(obj.valu);
                //e.setLieu(obj.get("lieu").toString());
               

                System.out.println(e.toString());
                liste.add(e);

            }

        } catch (IOException ex) {
        }
        return liste;

    }
        }



   
    

