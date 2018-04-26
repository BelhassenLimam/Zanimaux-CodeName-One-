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
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.services.UserService;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Azza
 */
public class AffichageAnimaux {
     private Resources theme;
    Form f;
   //  public static int id;
    
     
    public AffichageAnimaux(ArrayList<Animal> liste) throws IOException {
     
        theme = UIManager.initFirstTheme("/theme");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));
            Command cmd = new Command("Back",Image.createImage("/left-arrow.png")){
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
            ImageViewer iv = new ImageViewer(theme.getImage(liste.get(i).getPhotoAnimal()).scaled(1000, 700));
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
          SpanLabel lb2 = new SpanLabel("      ");
          SpanLabel lb3 = new SpanLabel("      ");
          TextField textcom= new TextField("","Ajouter un commentaire..");
          Button ajouter=new Button("Ajouter");
          
         Button editAjout = new Button("",Image.createImage("/pencil.png").scaled(100,100));
         Button supAjout = new Button("",Image.createImage("/delete.png").scaled(100,100));
         Label nomComAjout=new Label();
         Container comentAjoute = new Container(new BoxLayout(BoxLayout.X_AXIS));
         
         
           
          ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CommentaireService cs=new CommentaireService();
                UserService us=new UserService();
                Commentaires co=new Commentaires();
                co.setCin(us.getUserBycin("07212632").getCin());
                co.setContenant(textcom.getText());
                
               ComAjout.setText(textcom.getText()+"  ");
                nomComAjout.setText(us.getUserBycin("07212632").getNom());
               comentAjoute.add(ComAjout);
               comentAjoute.add(nomComAjout);
              comentAjoute.add(supAjout);
             comentAjoute.add(editAjout);
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
                    System.out.println(".deeeleeetteeeactionPerformed()");
                   comentAjoute.removeComponent(ComAjout);
                   comentAjoute.removeComponent(nomComAjout);
                   comentAjoute.removeComponent(supAjout);
                   comentAjoute.removeComponent(editAjout);
                   f.refreshTheme();
            }
        });
          editAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                comentAjoute.removeComponent(ComAjout);
                comentAjoute.removeComponent(nomComAjout);
                comentAjoute.removeComponent(supAjout);
                comentAjoute.removeComponent(editAjout);
                TextField text=new TextField(ComAjout.getText());
                Button modifier=new Button("modifier");
                comentAjoute.add(text);
                comentAjoute.add(modifier);
                f.refreshTheme();
                modifier.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                   CommentaireService cs=new CommentaireService();
                
                cs.editComByContent(ComAjout.getText(), text.getText());
                ComAjout.setText(text.getText()+"  ");
                comentAjoute.removeComponent(text);
                comentAjoute.removeComponent(modifier);
                 comentAjoute.add(ComAjout);
               comentAjoute.add(nomComAjout);
              comentAjoute.add(supAjout);
             comentAjoute.add(editAjout);
                f.refreshTheme();
                    }
                });
                   
            }
        });
       
       
          Container coment = new Container(new BoxLayout(BoxLayout.X_AXIS));
          coment.add(textcom);
          coment.add(ajouter);
         cc.add(coment);
         
         cc.add(lb3);
         
         cc.add(l);
           
        cc.add(comentAjoute);
         f.add(lb2);
         f.add(cc);
         CommentaireService cs = new CommentaireService();
          ArrayList<Commentaires> listcom = new ArrayList<>();
         listcom=cs.getComByRef(liste.get(0).getRefuge());
         for (int i =0;i<listcom.size();i++)
            
        {   try {
            Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
            
            UserService us= new UserService();
            String nom= us.getUserBycin(listcom.get(i).getCin()).getPrenom();          
            Label t =new Label(listcom.get(i).getContenant()+"  ");
            Label n=new Label(nom);
            // ImageViewer sup = new ImageViewer(theme.getImage("delete.png").scaled(24, 24));
             Button edit = new Button("",Image.createImage("/pencil.png").scaled(100,100));
           int id=listcom.get(i).getId();
            Button sup = new Button("",Image.createImage("/delete.png").scaled(100,100));
            sup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    CommentaireService cs =new CommentaireService();
                    cs.deleteCom(id);
                    System.out.println(".deeeleeetteeeactionPerformed()");
                   c.removeComponent(t);
                   c.removeComponent(n);
                   c.removeComponent(sup);
                   c.removeComponent(edit);
                   f.refreshTheme();
                }
            });
            
           
            
            edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                c.removeComponent(t);
                c.removeComponent(n);
                c.removeComponent(sup);
                c.removeComponent(edit);
                TextField text=new TextField(t.getText());
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
                t.setText(text.getText()+"  ");
                c.removeComponent(text);
                c.removeComponent(modifier);
                c.add(t);
                c.add(n);
                c.add(sup);
                c.add(edit);
                f.refreshTheme();
                    }
                });
                
            }
        });
      
        
        c.add(t);
        c.add(n);
        c.add(sup);
        c.add(edit);

        f.add(c);    
          } catch (IOException ex) {
             
          }
        }
   }
    
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
   
}
