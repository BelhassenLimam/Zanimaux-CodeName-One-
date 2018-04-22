/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Magasin;
import Services.MagasinService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public class AffichageMagasin {
    
    Form f;
    SpanLabel lb;
    
    public AffichageMagasin(){ 
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        MagasinService ms=new MagasinService();
        ArrayList<Magasin> lis=ms.getAllMagasin();
        System.out.println(lis.size());
        for (int i =0;i<lis.size();i++)
        {
            System.out.println(lis.get(i).getAdresseMagasin());
        }
     
        lb.setText(lis.toString());
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
