/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.mycompany.gui.SignInForm.connectedUser;
import com.mycompany.services.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author macbookpro
 */
public class connexion {
    private Resources theme;
    Form f;
    Database db=Database.openOrCreate("Mobile");
   
    public connexion() throws IOException
    {
        theme = UIManager.initFirstTheme("/theme");
        f = new Form( new FlowLayout(CENTER,TOP));  
        f.getToolbar().setVisible(false);
        Image icon = Image.createImage("/user.png");
        Container cnt=new Container(new BorderLayout());
        cnt.getAllStyles().setPadding(0, 0, 0, 10);
        final DefaultListModel<String> options = new DefaultListModel<>();
        AutoCompleteTextField username = new AutoCompleteTextField(options) {
         @Override
            protected boolean filter(String text) {
         if(text.length() == 0) {
             return false;
         }
         List<String> l = searchUsername(text);
         if(l == null || l.size() == 0) {
             return false;
         }

         options.removeAll();
         for(String s : l) {
             options.addItem(s);
         }
         return true;
            }
        };
        username.setHint("nom d'utilisateur");
        System.out.println(username.getText());
        username.setMinimumElementsShownInPopup(1);
        username.getUnselectedStyle().setBorder(Border.createCompoundBorder(Border.createEmpty(),Border.getDefaultBorder(),Border.createEmpty(),Border.createEmpty()));  
        cnt.add(BorderLayout.CENTER, username).add(BorderLayout.WEST,icon);
        //username.getAllStyles().setBorder(Border.createEmpty());
        TextField password = new TextField ("","mot de passe",20,TextField.PASSWORD );
         Image icon1 = Image.createImage("/key.png");
        Container cnt1=new Container(new BorderLayout());
        cnt1.getAllStyles().setPadding(0, 0, 0, 10);
        cnt1.add(BorderLayout.CENTER, password).add(BorderLayout.WEST,icon1);
        password.getUnselectedStyle().setBorder(Border.createCompoundBorder(Border.createEmpty(),Border.getDefaultBorder(),Border.createEmpty(),Border.createEmpty()));    
        //password.getAllStyles().setBorder(Border.createEmpty());
        CheckBox cbox1= new CheckBox("se rappeler de moi");
        Style s1 =cbox1.getUnselectedStyle();
        s1.setBgColor(0xff);
        cbox1.setPreferredSize(new Dimension(220,40));
        Button connexion= new Button("se connecter"); 
        connexion.setPreferredSize(new Dimension(220,40));
        ImageViewer iv = new ImageViewer(Image.createImage("/lock.png"));
        
       options.addSelectionListener((oldid, newid)-> 
       {
       username.addListListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    
                    Cursor a = db.executeQuery("SELECT * FROM user WHERE username LIKE'"+options.getItemAt(newid)+"'");
                    while((a.next())){
                        
                        password.setText(a.getRow().getString(1));
                        
                    }
                } catch (IOException ex) {
                }
            }
        });
               }
               
       );
           
        f.add(iv);
        f.add(cnt);
        f.add(cnt1);
        f.add(cbox1);
        f.add(connexion);


        connexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            
            ConnectionRequest con;
            con = new ConnectionRequest();
            con.setUrl("http://localhost:8888/MobileServiceWeb/loginW.php?username="+username.getText()+"&pwd="+password.getText()+"");
        
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
                        if(cbox1.isSelected())
                        {
                             try {
                            
                             db.execute("Create table if not exists user (username TEXT, password TEXT)");
                             } catch (IOException ex) {
                             }
                             boolean exist=false;
                             Cursor c = db.executeQuery("Select * from user");
                                while((c.next())&& (exist==false)){
                                    System.out.println(c.getRow().getString(1));
                                    if(c.getRow().getString(0)==username.getText())
                                    {
                                        exist=true;
                                    }
                            }
                                if(exist==false)
                                {
                                    db.execute("Insert into user(username,password) values('"+username.getText()+"','"+password.getText()+"')");

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
        });
        
    }
    
        public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

 
   List<String> searchUsername(String text) {
    try {
        int i=0;
        List<String> res = new ArrayList<>();
        if(text.length() > 0) {
          
                    Cursor a = db.executeQuery("SELECT * FROM user WHERE username LIKE'"+text+'%'+"'");
                     while((a.next())){
                         res.add(a.getRow().getString(0));
                         }
           
            return res;
        }
    } catch(Exception err) {
    }
    return null;
}
    
}



