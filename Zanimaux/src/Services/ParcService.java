/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Parc;
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
public class ParcService {
     

        public ArrayList<Parc> getAllParc(){
        ArrayList<Parc> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8888/zanimauxFinal2/web/app_dev.php/afficheParc");
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
                        float cdp = Float.parseFloat(obj.get("codePostaleParc").toString());
                       
                        Parc m = new Parc();
                        m.setId(obj.get("id").toString());
                        m.setNomParc(obj.get("nomParc").toString());
                        m.setCategorieDressage(obj.get("CategorieDressage").toString());
                        m.setAdresseParc(obj.get("adresseParc").toString());
                        m.setVilleParc(obj.get("villeParc").toString());
                        m.setCodePostaleParc((int)cdp);
                        m.setPhotoParc(obj.get("photoParc").toString());
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
