/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Animal;
import Entities.Commentaires;
import Entities.Refuge;
import Services.CommentaireService;
import Services.RefugeService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author Azza
 */
public class AffichageAnimaux {
     private Resources theme;
    Form f;
   public AffichageAnimaux(ArrayList<Animal> liste){
        theme = UIManager.initFirstTheme("/theme");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));       
        
        for (int i =0;i<liste.size();i++)
            
        {   Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            SpanLabel lb = new SpanLabel("");
            //ImageViewer iv = new ImageViewer(theme.getImage("key.png").scaled(20, 20));
            ImageViewer iv = new ImageViewer(theme.getImage(liste.get(i).getPhotoAnimal()).scaled(350, 200));
            Label t =new Label(liste.get(i).getType()+" "+liste.get(i).getRace()+" "+liste.get(i).getEtat());
            c.add(iv);
            c.add(lb);
            c.add(t);
            f.add(c);    
            lb.setText("Nom: "+liste.get(i).getNomAnimal());
        }
         Container cc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Label l=new Label("Vos avis...");
          SpanLabel lb2 = new SpanLabel("      ");
          
         cc.add(l);
         f.add(lb2);
         f.add(cc);
         CommentaireService cs = new CommentaireService();
          ArrayList<Commentaires> listcom = new ArrayList<>();
         listcom=cs.getComByRef(liste.get(0).getRefuge());
         for (int i =0;i<listcom.size();i++)
            
        {   Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
           
            Label t =new Label(listcom.get(i).getContenant()+" "+listcom.get(i).getCin());
            c.add(t);
            f.add(c);    
        }
   }
    
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
