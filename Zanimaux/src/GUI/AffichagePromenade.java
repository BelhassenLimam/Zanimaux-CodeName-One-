/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Magasin;
import Entities.Parc;
import Entities.Produit;
import Entities.Promenade;
import Services.MagasinService;
import Services.ParcService;
import Services.ProduitService;
import Services.PromenadeService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
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
        ArrayList<Promenade> lis=ms.getAllPromenade();/*
        for (int i =0;i<lis.size();i++)
            
        {   Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            SpanLabel lb = new SpanLabel("");
            Button b =new Button("Cosulter Parc");
            //ImageViewer iv = new ImageViewer(theme.getImage("key.png").scaled(20, 20));
            
            Label t =new Label(lis.get(i).getAdresseParc()+" "+lis.get(i).getVilleParc()+", "+lis.get(i).getCodePostaleParc());
            Parc m = lis.get(i);
            
            
            c.add(lb);
            c.add(t);
            c.add(b);
            f.add(c);    
            lb.setText(lis.get(i).getNomParc());
        }*/
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
