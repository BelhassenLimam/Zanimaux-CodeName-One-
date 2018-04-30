/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.ContenuPanier;
import com.mycompany.entities.Magasin;
import com.mycompany.entities.Panier;
import com.mycompany.entities.Produit;
import com.mycompany.services.MagasinService;
import com.mycompany.services.PanierService;
import com.mycompany.services.ProduitService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public class AffichagePanier 
{
    private Resources theme;
    Form f;
   
    
    
    public AffichagePanier() throws IOException{ 
        theme = UIManager.initFirstTheme("/theme");
        f = new Form(new BoxLayout(BoxLayout.Y_AXIS));  
        
            
            Image logo= Image.createImage("/logo.png").scaled(50, 50);
       
           
        f.getToolbar().addCommandToLeftBar("", logo, (e) -> Log.p("Clicked"));
        Style stitle = f.getToolbar().getAllStyles();
        stitle.setBgColor(0xff);
        PanierService ms=new PanierService();
        ArrayList<Panier> lis=ms.getPanier();
        ArrayList<ContenuPanier>cp = ms.getContenuPanier();
        double somme = lis.get(0).getSomme();
        System.out.println(somme);
        Label l = new Label("PANIER");
        for(int i =0;i<cp.size();i++)
        {
            
        }
        
        }
    


    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
}
