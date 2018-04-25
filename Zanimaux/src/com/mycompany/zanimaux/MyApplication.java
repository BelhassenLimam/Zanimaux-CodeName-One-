
package com.mycompany.zanimaux;



import com.mycompany.gui.AffichageMagasin;
import  com.mycompany.gui.SignInForm;
import com.mycompany.gui.affichageAnnonce;
import  com.mycompany.gui.afficherEvenement;
import  com.mycompany.gui.AffichageParc;
import  com.mycompany.services.MagasinService;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }

        //AffichageMagasin a= new AffichageMagasin();
        
        //a.getF().show();
        SignInForm loginForm = new SignInForm(theme);
        // after splash
        new java.util.Timer().schedule( 
        new java.util.TimerTask() {
            @Override
            public void run() {
               loginForm.show();
            }
        }, 
        5000 
);

       /* AffichageMagasin a= new AffichageMagasin();

        AffichageParc a= new AffichageParc();

        
        a.getF().show();*/
     
      /* afficherEvenement e = new afficherEvenement();
       e.getF().show();*/
      
       /*affichageAnnonce a = new affichageAnnonce();
       a.getF().show();*/
       

    }

    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }

}
