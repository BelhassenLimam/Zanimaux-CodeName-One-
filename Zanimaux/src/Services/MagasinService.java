/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Magasin;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author macbookpro
 */
public class MagasinService {
     

        public ArrayList<Magasin> getAllMagasin(){
        ArrayList<Magasin> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8888/ZanimauxFinal%202/web/app_dev.php/api/afficheMagasin");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {   
                        float id = Float.parseFloat(obj.get("idMagasin").toString());
                        float cdp = Float.parseFloat(obj.get("codePostaleMagasin").toString());
                        Magasin m = new Magasin();
                        m.setIdMagasin((int)id);
                        m.setNomMagasin(obj.get("nomMagasin").toString());
                        m.setAdresseMagasin(obj.get("adresseMagasin").toString());
                        m.setCodePostaleMagasin((int)cdp);
                        m.setVilleMagasin(obj.get("villeMagasin").toString());
                        m.setNumRC(obj.get("numRC").toString());
                        m.setCinProprietaireMagasin(obj.get("cinProprietaireMagasin").toString());
                        m.setPhotoMagasin(obj.get("photoMagasin").toString());
                        listTasks.add(m);
                    }
                } catch (IOException ex) {
                }

            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
}
