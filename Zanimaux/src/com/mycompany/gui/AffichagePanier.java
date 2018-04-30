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
import com.codename1.ui.TextField;
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
import static com.mycompany.gui.SignInForm.connectedUser;
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
        f.add(l);
        for(int i =0;i<cp.size();i++)
        {   Container cH= new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container cV= new Container(new BoxLayout(BoxLayout.Y_AXIS));
            TextField quantite= new TextField(String.valueOf(cp.get(i).getQuantite()));
            quantite.setWidth(50);
            Button plus= new Button("+");
            Button minus= new Button("-");
            Container qt= new Container(new BoxLayout(BoxLayout.X_AXIS));
            qt.add(minus);
            qt.add(quantite);
            qt.add(plus);
            if(cp.get(i).getQuantite()==0){
                minus.setEnabled(false);
            }
            ProduitService ps= new ProduitService();
            Produit p =ps.getProduitById(cp.get(i).getIdProduit()).get(0);
            Label libelle = new Label(p.getLibelle());
            Label prix= new Label(String.valueOf(p.getPrix())+"DT");
            ImageViewer iv = new ImageViewer(theme.getImage(p.getPhotoProduit()).scaled(100, 100));
            cV.add(libelle);
            cV.add(prix);
            cV.add(qt);
            cH.add(iv);
            cH.add(cV);
            f.add(cH);
            int idCP = cp.get(i).getIdContenuPanier();
            int idP = p.getIdProduit();
            double px=p.getPrix();
            plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                quantite.setText(String.valueOf(Integer.parseInt(quantite.getText())+1));
                ms.modifierQuantite(idCP,idP,1,px,connectedUser.getCin());
            }
        });
            minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if(Integer.parseInt(quantite.getText())==0){
                minus.setEnabled(false);
            }else
                {
                quantite.setText(String.valueOf(Integer.parseInt(quantite.getText())-1));
                ms.modifierQuantite(idCP,idP,-1,px,connectedUser.getCin());
                }
            }
        });            
        }
        Button passerCommande= new Button("passer commande");
        f.add(passerCommande);
        passerCommande.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ms.passerCommande();
            }
        });
        
        }
    


    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
}
