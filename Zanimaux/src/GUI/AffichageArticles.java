/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Entities.Articles;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author Mariam
 */
public class AffichageArticles {
     private Resources theme;
    Form f;
   
    
    
    public AffichageArticles(ArrayList<Articles>list) 
    { 
        theme = UIManager.initFirstTheme("/theme");

        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));
        for (int i =0;i<list.size();i++)
            
        {   Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            c.setHeight(200);
            c.setWidth(250);
            Style s = c.getAllStyles();
            
            SpanLabel lb = new SpanLabel("");
           
           
            ImageViewer iv = new ImageViewer(theme.getImage(list.get(i).getPiecejointe()).scaled(250, 200));
            Label t =new Label(list.get(i).getTitre());
            Label t1 = new Label(list.get(i).getDescription());
           
            f.add(iv);
            f.add(t);
            f.add(t1);
           
           
    
      
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
