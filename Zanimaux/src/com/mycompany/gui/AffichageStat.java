/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.mycompany.entities.Parc;
import com.mycompany.services.ParcService;
import com.codename1.components.ImageViewer;
import com.codename1.components.SliderBridge;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Avis;
import com.mycompany.entities.User;
import static com.mycompany.gui.SignInForm.connectedUser;
import com.mycompany.services.AvisService;
import com.mycompany.services.UserService;
import java.io.IOException;
import java.util.ArrayList;



import javafx.scene.control.TextField;

/**
 *
 * @author macbookpro
 */
public class AffichageStat 
{double e = 29;
    Container ctn = new Container();
    
    Form f = new Form("Statistique");
    
     private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{50, 60, 30, 20});
        renderer.setShowLabels(true);
        renderer.setAxesColor(16711935);
        renderer.setLabelsColor(000000);
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }
     public CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
         ParcService st=new ParcService();
       
           series.add("Chien", 2);
           series.add("Chevaux", 1);
           series.add("Autre", 1);
            
       

        return series;
    }
     

    public AffichageStat() {
    try {
        Toolbar tb = f.getToolbar();
        
        Container topBar = BorderLayout.centerAbsolute(new Label());
        Label menu = new Label("Menu");
        menu.getUnselectedStyle().setFgColor(0xffffff);
        
        topBar.add(BorderLayout.CENTER, menu);
        
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
        tb.addMaterialCommandToSideMenu("Accueil", FontImage.MATERIAL_HOME, e -> {});
        tb.addCommandToSideMenu("Parc", Image.createImage("/dressage.png").scaled(25,25), e -> {  AffichageParc FormProduit = new AffichageParc();
        FormProduit.getF().show();});
        tb.addCommandToSideMenu("Magasin", Image.createImage("/storeIcon.png").scaled(25,25), e -> {try {
            AffichageMagasin FormProduit = new AffichageMagasin();
            FormProduit.getF().show();
        } catch (IOException ex) {
            ;
        }
        });
        tb.addCommandToSideMenu("Veterinaire", Image.createImage("/doctorIcon.png").scaled(25,25), e -> {AffichageCabinets FormProduit = new AffichageCabinets();
        FormProduit.getF().show();});
        tb.addCommandToSideMenu("PetSitter", Image.createImage("/petsitter.png").scaled(25,25), e -> {AffichagePromenade FormProduit = new AffichagePromenade();
        FormProduit.getF().show();});
        tb.addCommandToSideMenu("Refuge", Image.createImage("/shelter.png").scaled(25,25), e -> {AffichageRefuge FormProduit = new AffichageRefuge();
        FormProduit.getF().show();});
        tb.addCommandToSideMenu("Evenement", Image.createImage("/event.png").scaled(25,25), e -> {try {
            afficherEvenement FormProduit = new afficherEvenement();
            FormProduit.getF().show();
        } catch (IOException ex) {
        }
        });
        tb.addCommandToSideMenu("Annonce", Image.createImage("/annonce.png").scaled(25,25), e -> {try {
            affichageAnnonce FormProduit = new affichageAnnonce();
            FormProduit.getF().show();
        } catch (IOException ex) {
        }
        });
        ctn.getUnselectedStyle().setBgColor(16711935);
        
        double[] values = new double[]{12, 14, 100, 10, 19};
        
        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.YELLOW, ColorUtil.BLACK};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);
        
        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);
        
        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);
        
        
        
        ctn.setLayout(new BorderLayout());
        ctn.addComponent(BorderLayout.CENTER, c);
        f.add(ctn);
    } catch (IOException ex) {
          }
             
        
     
        
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
     
    
}
