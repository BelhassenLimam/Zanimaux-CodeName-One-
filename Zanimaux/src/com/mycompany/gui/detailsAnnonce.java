/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Annonce;
import java.util.ArrayList;

/**
 *
 * @author Maroua
 */
public class detailsAnnonce {
    
    private Resources theme;
    Form f;
   public detailsAnnonce(ArrayList<Annonce> liste){
        theme = UIManager.initFirstTheme("/theme");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));       
        
        for (int i =0;i<liste.size();i++)
            
        {   
            Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            SpanLabel lb = new SpanLabel("");
            ImageViewer iv = new ImageViewer(theme.getImage(liste.get(i).getPieceJointe()).scaled(350, 200));
            Label t =new Label(liste.get(i).getType()+"\n"+liste.get(i).getType()+" "+liste.get(i).getDescription());
            c.add(iv);
            c.add(lb);
            c.add(t);
            f.add(c);    
            lb.setText("Nom: "+liste.get(i).getTitre());
        }
      }
    
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
