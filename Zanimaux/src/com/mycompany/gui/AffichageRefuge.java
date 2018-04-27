/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entities.Animal;
import com.mycompany.services.RefugeService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import java.util.ArrayList;
import com.mycompany.entities.Refuge;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;


/**
 *
 * @author Azza
 */
public class AffichageRefuge {
    private Resources theme;
    Form f;
   
    
    
    public AffichageRefuge() { 
        theme = UIManager.initFirstTheme("/theme");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));       
        RefugeService ms=new RefugeService();
        ArrayList<Refuge> lis=ms.getListRefuges();
        for (int i =0;i<lis.size();i++)
            
        {   Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container west= new Container(new FlowLayout(CENTER,CENTER));
            Container info = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            SpanLabel lb = new SpanLabel("");
            Button b =new Button("Cosulter Refuge");
            //ImageViewer iv = new ImageViewer(theme.getImage("key.png").scaled(20, 20));
            ImageViewer iv = new ImageViewer(theme.getImage(lis.get(i).getPhotoRefuge()).scaled(1200, 900));
            Label t =new Label(lis.get(i).getAdresseRefuge()+" "+lis.get(i).getGouvernementRefuge()+", "+lis.get(i).getCodePostaleRefuge());
            Refuge m = lis.get(i);
            info.add(lb);
            info.add(t); 
            west.add(info);
            c.add(iv);            
            c.add(west);
            c.add(b);
            f.add(c);    
            lb.setText("Nom: "+lis.get(i).getNomRefuge());
            b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    RefugeService rs=new RefugeService();
                    ArrayList<Animal> lis= rs.getListAnimauxByRef(m.getImmatriculation());
                    AffichageAnimaux AA =new AffichageAnimaux(lis);
                    AA.getF().show();
                } catch (IOException ex) {
                   
                }
            }
        });
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
