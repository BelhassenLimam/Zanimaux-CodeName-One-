/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entities.Animal;
import com.mycompany.entities.Commentaires;
import com.mycompany.services.CommentaireService;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Refuge;
import com.mycompany.services.RefugeService;
import com.mycompany.services.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.chart.PieChart;


/**
 *
 * @author Azza
 */
public class AffichageAnimaux {
    private Resources theme;
    Form f;
    public static String adresse;
    public static String nomRef;
   //  public static int id;
    public static ArrayList<Animal> listeA;
     
    public AffichageAnimaux(ArrayList<Animal> liste) throws IOException {
      listeA=liste; 
     SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
        theme = UIManager.initFirstTheme("/theme");
       
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));
            Command cmd = new Command("Back",Image.createImage("/left-arrow(1).png")){
             @Override
             public void actionPerformed(ActionEvent evt) {
                 AffichageRefuge ff=new AffichageRefuge();
                 ff.getF().showBack();
             }
         };
         f.getToolbar().addCommandToLeftBar(cmd);
        for (int i =0;i<liste.size();i++)
            
        {   Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container west= new Container(new FlowLayout(CENTER,CENTER));
            SpanLabel lb = new SpanLabel("");
            SpanLabel lb2 = new SpanLabel("  ");
            //ImageViewer iv = new ImageViewer(theme.getImage("key.png").scaled(20, 20));
            ImageViewer iv = new ImageViewer(theme.getImage(liste.get(i).getPhotoAnimal()).scaled(350, 200));
            Label t =new Label(liste.get(i).getType()+" "+liste.get(i).getRace()+" "+liste.get(i).getEtat());
            c.add(iv);
            c.add(lb);
            c.add(t);
            c.add(lb2);
            west.add(c);
            //f.add(c);  
            f.add(west);
            lb.setText("Nom: "+liste.get(i).getNomAnimal());
        }
         Container cc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Label l=new Label("Vos avis...");
         Label ComAjout=new Label();
          
          TextField textcom= new TextField("","Ajouter un commentaire..");
          textcom.setPreferredSize(new Dimension(120,40));
         // textcom.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
          Button ajouter=new Button("Ajouter");
          ajouter.setPreferredSize(new Dimension(120,40));
         Button editAjout = new Button("",Image.createImage("/pencil16.png").scaled(16,16));
         Button supAjout = new Button("",Image.createImage("/delete16.png").scaled(16,16));
         editAjout.setPreferredSize(new Dimension(50,20));
         supAjout.setPreferredSize(new Dimension(50,20));
         Label nomComAjout=new Label();
         Container comentAjoute = new Container(new BoxLayout(BoxLayout.X_AXIS));
         Container kom=new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Container ordh1=new Container(new BoxLayout(BoxLayout.X_AXIS));
         MultiButton com=new MultiButton();
          format.applyPattern("dd/MM/yyyy");
           Label t =new Label();
           t.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
           
            Label n=new Label();
            n.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            n.getAllStyles().setFgColor(0x0000FF);
            ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CommentaireService cs=new CommentaireService();
                UserService us=new UserService();
                Commentaires co=new Commentaires();
                co.setCin(SignInForm.connectedUser.getCin());
                co.setContenant(textcom.getText());
                
               ComAjout.setText(textcom.getText());
                nomComAjout.setText(us.getUserBycin(SignInForm.connectedUser.getCin()).getNom());
                t.setText(textcom.getText());
                t.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
                n.setText(nomComAjout.getText()+"      "+"à l'instant");
                n.getAllStyles().setFgColor(0x0000FF);
                n.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
              
             ordh1.add(t);
             ordh1.add(supAjout);
             ordh1.add(editAjout);
              kom.add(ordh1);
              kom.add(n);
            comentAjoute.add(kom);
              
             textcom.setText("");
                co.setRefuge(liste.get(0).getRefuge());
                cs.ajoutCom(co);
                f.refreshTheme();
                
            }
        });
          supAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 CommentaireService cs =new CommentaireService();
                    cs.deleteComByContent(textcom.getText());
                   comentAjoute.removeComponent(kom);
                   
                   f.refreshTheme();
            }
        });
          editAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                comentAjoute.removeComponent(kom);
               
                TextField text=new TextField(t.getText());
                text.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                Button modifier=new Button("modifier");
                comentAjoute.add(text);
                comentAjoute.add(modifier);
                f.refreshTheme();
                modifier.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                   CommentaireService cs=new CommentaireService();
                
                cs.editComByContent(ComAjout.getText(), text.getText());
                t.setText(text.getText());
                comentAjoute.removeComponent(text);
                comentAjoute.removeComponent(modifier);
                 comentAjoute.add(t);
              n.setText(nomComAjout.getText()+"      "+"à l'instant");
              n.getAllStyles().setFgColor(0x0000FF);
              n.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
              comentAjoute.add(kom);            
                f.refreshTheme();
                    }
                });
                   
            }
        });
       
       
          Container coment = new Container(new BoxLayout(BoxLayout.X_AXIS));
          coment.add(textcom);
          coment.add(ajouter);
         cc.add(coment);
         
        
         cc.add(l);
           
        cc.add(comentAjoute);
        
         f.add(cc);
         CommentaireService cs = new CommentaireService();
          ArrayList<Commentaires> listcom ;
         listcom=cs.getComByRef(liste.get(0).getRefuge());
    
         for (int i =0;i<listcom.size();i++)
            
        {  
           
            Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container kommm=new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container ordh= new Container(new BoxLayout(BoxLayout.X_AXIS));
            UserService us= new UserService();
            String nom= us.getUserBycin(listcom.get(i).getCin()).getPrenom();  
            //MultiButton commentaire=new MultiButton(listcom.get(i).getContenant());
            //commentaire.setTextLine2(nom+"      "+format.format(listcom.get(i).getDate()));
            Label tt =new Label(listcom.get(i).getContenant());
            tt.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            Label nn=new Label(nom+"      "+format.format(listcom.get(i).getDate()));
            nn.getAllStyles().setFgColor(0x0000FF);
            nn.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            // ImageViewer sup = new ImageViewer(theme.getImage("delete.png").scaled(24, 24));
            Button edit = new Button(Image.createImage("/pencil16.png").scaled(16,16));
            edit.setPreferredSize(new Dimension(50,20));
            int id=listcom.get(i).getId();
            Button sup = new Button(Image.createImage("/delete16.png").scaled(16,16));
            sup.setPreferredSize(new Dimension(50,20));
            sup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    CommentaireService cs =new CommentaireService();
                    cs.deleteCom(id);
                    
                   c.removeComponent(kommm);
                  
                   f.refreshTheme();
                }
            });
            
           
            
            edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                c.removeComponent(kommm);
              
                TextField text=new TextField(tt.getText());
                text.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                Button modifier=new Button("modifier");
                c.add(text);
                c.add(modifier);
                f.refreshTheme();
                modifier.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                CommentaireService cs=new CommentaireService();
                Commentaires com=new Commentaires();
                com.setContenant(text.getText());
                com.setId(id);
               
                cs.editCom(com);
                tt.setText(text.getText());
               nn.setText(nom+"      à l'instant");
               nn.getAllStyles().setFgColor(0x0000FF);
                c.removeComponent(text);
                c.removeComponent(modifier);
                
                
                c.add(kommm);
              
                f.refreshTheme();
                    }
                });
                
            }
        });
      ordh.add(tt);
       if(SignInForm.connectedUser.getCin().equals(listcom.get(i).getCin())) 
       { ordh.add(sup);
        ordh.add(edit);}
        kommm.add(ordh);
        kommm.add(nn);
       
        c.add(kommm);
     

        f.add(c); 
        
         
        
        }
         Button b=new Button("Voir sur carte",Image.createImage("/maps.png").scaled(16,16));
         b.setPreferredSize(new Dimension(90,50));
         b.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            RefugeService rs=new RefugeService();
            Refuge refuge= rs.getRefugeByImm(liste.get(0).getRefuge());
           adresse=refuge.getAdresseRefuge()+" "+refuge.getGouvernementRefuge();
           nomRef=refuge.getNomRefuge();
             GoogleMaps gm=new GoogleMaps();
             gm.getF().show();
         }
     });
        f.add(b);
        
   }
   
    
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
   
}
