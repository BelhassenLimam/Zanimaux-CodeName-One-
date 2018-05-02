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
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
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
            Style s = quantite.getAllStyles();
            Dimension d = new Dimension(20, 20);
            quantite.setPreferredSize(d);
            Button plus= new Button("+");
            Button minus= new Button("-");
            Label suppr = new Label("");
            FontImage.setMaterialIcon(suppr, FontImage.MATERIAL_DELETE);
            Button sup = new Button("",Image.createImage("/delete.png").scaled(100,100));
            Container qt= new Container(new BoxLayout(BoxLayout.X_AXIS));
            qt.add(minus);
            qt.add(quantite);
            qt.add(plus);
            qt.add(suppr);
                    
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
            suppr.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            ms.suprimerProduitPanier(idCP, Integer.parseInt(quantite.getText()), px);
            f.show();
            

                
            }
        });
        }
        Button passerCommande= new Button("passer commande");
        f.add(passerCommande);
        passerCommande.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                    ms.passerCommande();
                    try {
                    AffichageMagasin m = new AffichageMagasin();
                    m.getF().show();
                } catch (IOException ex) {
                }
            }
        });
      
             Command cmd1 = new Command("Back",Image.createImage("/left-arrow_1.png")){
             @Override
             public void actionPerformed(ActionEvent evt) {
                 try {
                     AffichageMagasin ff=new AffichageMagasin();
                     ff.getF().showBack();
                 } catch (IOException ex) {
                 }
             }
         };
         f.getToolbar().addCommandToLeftBar(cmd1);
                        
}
        
        
    


    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

   
}
