/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
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



dateTimePicker.setDate(new Date());

//Label labelAdresseCabinet =new Label("Adresse");
//labelAdresseCabinet.setText("Adresse : " + cab.getAdresseCabinet());

Label labeldd=new Label("date de debut");

//f.add(labelAdresseCabinet);

f.add(labeldd);
f.add(dateTimePicker);
f.add(ok);

f.setScrollable(false);
     
     
    ok.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
              //java.util.Date Heurerdv = new Date(dateTimePicker.getDate().getTime());
      
      UserService us=new UserService();
       //System.out.println(connectedUser.getCin());
             System.out.println(cab.getImmatriculeCabinet());

         Rendezvs rdv =new Rendezvs("11201120",cab.getImmatriculeCabinet(), dateTimePicker.getDate());
            
          RendezvsService rdvs= new  RendezvsService();
          rdvs.addrdv(rdv);
                  
           
         
       
         }
     });
      f.show();
        
     ;



 }

    
 
    
}
