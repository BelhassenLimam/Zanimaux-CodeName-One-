/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.gui;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;

import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;

import com.codename1.ui.events.ActionListener;

import com.mycompany.entities.User;
import com.mycompany.gui.AffichageMagasin;
import com.mycompany.services.UserService;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * GUI builder created Form
 *
 * @author Shai Almog
 */
public class SignInForm extends com.codename1.ui.Form {
    StringBuffer str = new StringBuffer();
    public static User connectedUser;
    Database db;
    int ch;
    public SignInForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());   
   
    }
    
    public SignInForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
    
        getContentPane().setUIID("SignInForm");
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private CheckBox gui_check = new CheckBox();
   

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_2.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if(sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == gui_Button_2) {
                onButton_2ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Sign In");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Component_Group_1.addComponent(gui_check);
        gui_check.setText("Remember me");
        gui_check.setUIID("CheckBox");
             Util.register("user", User.class);
        User u = (User) Storage.getInstance().readObject("user");
if(u!=null){
gui_Text_Field_2.setText(u.getUsername());
gui_Text_Field_1.setText(u.getPassword());
gui_check.setSelected(true);
}
        gui_Text_Field_2.setHint("username");
        gui_Text_Field_2.setName("Text_Field_2");
        gui_Text_Field_2.setUIID("TextForm");
        gui_Text_Field_1.setHint("password");
        gui_Text_Field_1.setName("Text_Field_1");
        gui_Text_Field_1.setUIID("TextForm");
        gui_Container_1.addComponent(gui_Button_2);
        gui_Container_1.addComponent(gui_Button_3);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("Sans titre - 2.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Sign In");
        gui_Button_2.setName("Button_2");

        gui_Button_3.setText("Forgot Your Password");
        gui_Button_3.setUIID("CenterLabelSmall");
        gui_Button_3.setName("Button_3");
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
          
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        ConnectionRequest con;
        con = new ConnectionRequest();
        con.setUrl("http://localhost:8888/MobileServiceWeb/loginW.php?username="+gui_Text_Field_2.getText()+"&pwd="+gui_Text_Field_1.getText()+"");
        
    con.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
            public void actionPerformed(NetworkEvent evt) {
                
                String str = new String(con.getResponseData());
                if(str.equalsIgnoreCase("MOT DE PASSE INVALIDE")|| str.equalsIgnoreCase("NOM D'UTILISATEUR INVALIDE"))
                {
                    Dialog.show("Error", str, "Ok", "Cancel");


                }
                else{
                    
                    
                    try {
                        UserService u = new UserService();
                        connectedUser = u.getConnectedUser(str);
                        if(gui_check.isSelected())
                        {
                             try {
                             db = Database.openOrCreate("pidev");
                             db.execute("Create table if not exists user (username TEXT, password TEXT)");
                             } catch (IOException ex) {
                             }
                             boolean exist=false;
                             Cursor c = db.executeQuery("Select * from user");
                                while((c.next())&& (!exist)){
                                    if(c.getRow().getString(0)==gui_Text_Field_2.getText())
                                    {
                                        System.out.println(c.getRow().getString(0));
                                        exist=true;
                                    }
                            }
                                if(!exist)
                                {
                                    db.execute("Insert into user(username,password) values('"+gui_Text_Field_2.getText()+"','"+gui_Text_Field_1.getText()+"')");

                                }
                            
                        }
                        //System.out.println(connectedUser.getCin());
                        Accueil2 form= new Accueil2();
                        form.show();
                    } catch (IOException ex) {
                        
                    }

                    }

                }
                

           
     });

        NetworkManager.getInstance().addToQueue(con);  
        
        
        
    }

}
