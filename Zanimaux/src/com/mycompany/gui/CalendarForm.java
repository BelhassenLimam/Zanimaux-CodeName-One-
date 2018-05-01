/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Cabinet;
import com.mycompany.entities.Rendezvs;
import com.mycompany.entities.User;
import static com.mycompany.gui.SignInForm.connectedUser;
import com.mycompany.services.CabinetService;
import com.mycompany.services.RendezvsService;
import com.mycompany.services.UserService;
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
    
    public CalendarForm() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());
        this.setScrollable(true);
       
//     ImageViewer img = new ImageViewer(Image.createImage("/calendar-background.jpg"));
//    add(img);
    }
    
    public CalendarForm(com.codename1.ui.util.Resources resourceObjectInstance) throws IOException {
           
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
         add(notiflabel);
       
        
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
        p.setAlignment(CENTER);
        Container cnt = BoxLayout.encloseY(
                p,
                new Label(Image.createImage("/calendar-separator.png"), "CenterLabel")
        );
        
        BorderLayout bl = (BorderLayout)gui_Calendar_1.getLayout();
        Component combos = bl.getNorth();
        gui_Calendar_1.replace(combos, cnt, null);
          ConnectionRequest con = new ConnectionRequest();
//        
           UserService us = new UserService(); 
 
//        CabinetService cs = new CabinetService();
//        User u = SignInForm.connectedUser;
//        System.out.println(u.getCin());
//        System.out.println(u);
//        Cabinet c= new Cabinet();
//        c = cs.getCabinetByCin(u.getCin());
     
        //User client;
     String imm="1145";
        con.setUrl("http://localhost/Mobile/ShowRDV.php?immatriculecabinet="+imm);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                             ArrayList<Rendezvs> data = getListRDV(new String(con.getResponseData()));
                           
                             for (Rendezvs e : data) {
                    
//                        ConnectionRequest cnx1 = new ConnectionRequest("http://localhost/Mobile/ShowRdvdetail.php?idrdv="+e.getIdrdv()) {
//                        @Override
//                        protected void readResponse(InputStream input) throws IOException {
//                           Rendezvs r = new Rendezvs();
//                            r = findR(input);
//                            
                             User client= us.getUserBycin(e.getCin());
                             System.out.println(client);
                            System.out.println(e.getHeurerdv());
                                 try {
                                     add(createEntry(resourceObjectInstance,false ,e.getHeurerdv(),e.getImmatriculecabinet(),client.getNom(),client.getPrenom(),client.getTelephone()));
                                 } catch (IOException ex) {
                                    
                                 }
                    refreshTheme();
                    
                            
                       
//                        }
//
//                        };
//                    NetworkManager.getInstance().addToQueue(cnx1);
                     }
            }});
         NetworkManager.getInstance().addToQueue(con);
    }
                      
//                     
//                        
//       
//              ConnectionRequest  con1 = new ConnectionRequest() {
//                                @Override
//                                protected void postResponse() {
//                                    System.out.println(str.toString());
//
//                                   if (str.toString().trim().equals("OK")) {
//                                          str = new StringBuffer();
//                                          
//                                     ConnectionRequest cnx1 = new ConnectionRequest("http://localhost/Mobile/ShowRdvdetail.php?idrdv="+e.getIdrdv())  {
//                        @Override
//                        protected void readResponse(InputStream input) throws IOException {
//                      
//                          add(createEntry(resourceObjectInstance, false, e.getHeurerdv(), e.getImmatriculecabinet()));
//                    refreshTheme();
//                        }
//
//                    };
//                    NetworkManager.getInstance().addToQueue(cnx1);
//                                        
//                                    }
//                                }
//
//                                @Override
//                                protected void readResponse(InputStream input) throws IOException {
//                                    int i=0;
//                                
//                                    while ((ch = input.read()) != (-1)) {
//                                  
//                                        str.append((char) ch);
//                                        
//                                    }
//                             
//                                }
//
//                            };
                           
                            

    
    
        
    private Container createEntry(Resources res, boolean selected, Date Heurerdv,String immatriculecabinet, String nom, String prenom,  String telephone) throws IOException {
        
         SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
        format.applyPattern("dd/MM/yyyy");
        String temps= format.format(Heurerdv);
        System.out.println(temps);
        Component time = new Label(temps, "CalendarHourUnselected");
         if(selected) {
            time.setUIID("CalendarHourSelected");
        }
        
        Container circleBox;
      
            circleBox = BoxLayout.encloseY(new Label( Image.createImage("/label_round-selected.png")),
                    new Label("-", "OrangeLine"),
                    new Label("-", "OrangeLine")
            );
        
       
        Container cnt = new Container(BoxLayout.x());
//        for(String att : images) {
//            cnt.add(res.getImage(att));
//        }
        
        Container mainContent = BoxLayout.encloseY(
                BoxLayout.encloseX(
                        new Label(immatriculecabinet,"SmallLabel"),
                        new Label(telephone, "SmallLabel"), 
                        new Label(temps, "SmallLabel"), 
                        new Label("-", "SmallThinLabel"), 
                        new Label(nom, "SmallThinLabel"), 
                        new Label("-", "SmallThinLabel"),
                        new Label(prenom, "SmallThinLabel")),
                        cnt
        
                
         
                
        );
        
        
        
        mainContent= BorderLayout.center(mainContent).
                add(BorderLayout.WEST, circleBox);
        
      
           return BorderLayout.center(mainContent).
                add(BorderLayout.WEST, FlowLayout.encloseCenter(time));
        
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
  SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
            for (Map<String, Object> obj : list) {
               Rendezvs e = new Rendezvs();//id, json, status);
                e.setIdrdv((int) Integer.parseInt(obj.get("idrdv").toString()));
                e.setCin((obj.get("cin").toString()));
                e.setImmatriculecabinet(obj.get("immatriculecabinet").toString());
                
                Date  heurerdv;
                try {
                    heurerdv = format.parse(obj.get("heurerdv").toString());
                    e.setHeurerdv(heurerdv);
                } catch (ParseException ex) {
                    
                }
            
                //e.setLieu(obj.get("lieu").toString());
               

                System.out.println(e.toString());
                liste.add(e);

            }

        } catch (IOException ex) {
        }
        return liste;

    }
      
      
      public Rendezvs findR (InputStream in) {

        //    System.out.println("JSON***********\n"+json);
        Rendezvs e = new Rendezvs();
        try {
            JSONParser j = new JSONParser();
            Reader reader = new InputStreamReader(in, "UTF-8");
            Map<String, Object> data = j.parseJSON(reader);

//            Map<String, Object> task = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("*********:" + data);
            List<Map<String, Object>> d = (List<Map<String, Object>>) data.get("root");
           SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
            for (Map<String, Object> obj : d) {
             float id = Float.parseFloat(obj.get("idrdv").toString());
                e.setIdrdv((int)id);
                e.setImmatriculecabinet((obj.get("immatriculecabinet").toString()));
                e.setCin(obj.get("cin").toString());
                
               
                try {
                    Date heurerdv = format.parse(obj.get("heurerdv").toString());
                    e.setHeurerdv(heurerdv);
                   
                     } catch (ParseException ex) {
                            System.out.println("   erreurdate");
                     }
                      
                    }
                } catch (IOException ex) {
                }

        return e;

    }

        }



   
    

