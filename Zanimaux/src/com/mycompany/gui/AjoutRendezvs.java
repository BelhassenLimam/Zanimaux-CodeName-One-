/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.entities.Cabinet;
import com.mycompany.entities.Rendezvs;
import static com.mycompany.gui.SignInForm.connectedUser;
import com.mycompany.services.RendezvsService;
import com.mycompany.services.UserService;

import java.util.Date;



/**
 *
 * @author Mariam
 */
public class AjoutRendezvs {
        
 private Resources theme;
   private Form current; 
 
   public static Cabinet cab = null;
  
  
   



  public AjoutRendezvs() {
        
    }

public void start(){
        if(current != null){
            current.show();
            return;
        }


    Form f=new Form("Prendre Rendez vs", BoxLayout.y());

                Command back = new Command("Back"){
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        System.out.println("Going back now");
                        AffichageCabinets ac = new AffichageCabinets();
                       ac.getF().show();
                    }
                    
                };
                
                f.getToolbar().setBackCommand(back);

Button ok=new Button("Confirmer");

Picker dateTimePicker = new Picker();
dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);

//d= dateTimePicker.setTime();



dateTimePicker.setDate(new Date());

//Label labelAdresseCabinet =new Label("Adresse");
//labelAdresseCabinet.setText("Adresse : " + cab.getAdresseCabinet());

Label labeldd=new Label("date de debut");

//f.add(labelAdresseCabinet);

f.add(labeldd);
f.add(dateTimePicker);
f.add(ok);


f.setScrollable(true);
     Label label_error = new Label(" ");
//     label_error.setVisible(false);
     f.add(label_error);
     
    ok.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
              //java.util.Date Heurerdv = new Date(dateTimePicker.getDate().getTime());
      
      UserService us=new UserService();
       System.out.println(SignInForm.connectedUser.getCin());
             System.out.println(cab.getImmatriculeCabinet());

         
            
//            java.util.Date dateDebut = java.util.Date.from(picker_debut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//            java.sql.Date sqlDateDebut = new java.sql.Date(dateDebut.getTime());
            
            //java.util.Date d = java.util.Date.from(dateTimePicker.getDate().toInstant());
               
           
        java.util.Calendar systemdate = java.util.Calendar.getInstance();
         systemdate.setTime(systemdate .getTime());
         long a = dateTimePicker.getDate().getTime();
         long h = systemdate.getTime().getTime();
                System.out.println(h);
                 System.out.println(a);
        
         ConnectionRequest con;
        con = new ConnectionRequest();
        con.setUrl("http://localhost/Mobile/verifrdv.php?cin=" +connectedUser.getCin());
        
    con.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
            public void actionPerformed(NetworkEvent evt) {
                
                String str = new String(con.getResponseData());
                System.out.println(str);
                if(str.equalsIgnoreCase("existe")){
                   ok.setVisible(false);
                   Dialog.show("Erreur", "Vous avez déja envoyer une demande", "OK", null);
                  
                     f.refreshTheme();
                }
                else if(a<h){
                 Dialog.show("Erreur", "Donnez une date future", "OK", null);
              
            }
                else{
                Rendezvs rdv =new Rendezvs(SignInForm.connectedUser.getCin(),cab.getImmatriculeCabinet(), dateTimePicker.getDate());
             
          RendezvsService rdvs= new  RendezvsService();
          rdvs.addrdv(rdv);
          Dialog.show("Success", "Votre demande est envoyé", "OK", null);
          ok.setVisible(false);
         

                }
                         
            }});

        NetworkManager.getInstance().addToQueue(con);  
           
//            
            
          
           
            
           
                
         
                  
           
         
       
            }
     });
      f.show();
        
     



 }

    
 
    
}
