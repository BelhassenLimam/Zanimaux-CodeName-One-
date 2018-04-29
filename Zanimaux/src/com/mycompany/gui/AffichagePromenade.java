/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entities.Magasin;
import com.mycompany.entities.Parc;
import com.mycompany.entities.Produit;
import com.mycompany.entities.Promenade;
import com.mycompany.services.MagasinService;
import com.mycompany.services.ParcService;
import com.mycompany.services.ProduitService;
import com.mycompany.services.PromenadeService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public class AffichagePromenade 
{
    private Resources theme;
    Form f;
   
    
    
    public AffichagePromenade() { 
        theme = UIManager.initFirstTheme("/theme");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));       
        PromenadeService ms=new PromenadeService();
        ArrayList<Promenade> lis=ms.getAllPromenade();
        for (int i =0;i<lis.size();i++)
         {   Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label lb = new Label();
            Button b =new Button("Cosulter Parc");
            //ImageViewer iv = new ImageViewer(theme.getImage("key.png").scaled(20, 20));
            ImageViewer iv = new ImageViewer(theme.getImage(lis.get(i).getPhotoPromenade()).scaled(100, 100));
            Label t =new Label(lis.get(i).getLieuPromenade());
            Label t1 =new Label(lis.get(i).getTypePromenade());
             SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
            format.applyPattern("dd/MM/yyyy");
            Label t2 =new Label(format.format(lis.get(i).getDatedebutPromenade())+"  "+format.format(lis.get(i).getDatefinPromenade()));
            Promenade m = lis.get(i);
            
            c2.add(iv);
            c3.add(lb);
            c3.add(t1);
            c3.add(t2);
            c3.add(t);
            c2.add(c3);
            c.add(c2);
            c.add(b);
            
            f.add(c);  
            
            lb.setText(lis.get(i).getNomPromenade());
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
