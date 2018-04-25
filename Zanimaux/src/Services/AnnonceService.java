/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Annonce;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import static java.lang.Math.round;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Maroua
 */
public class AnnonceService {
    
     public ArrayList<Annonce> getAllAnnonce(){
        ArrayList<Annonce> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/zanimauxFinal/web/app_dev.php/api/afficheAnnonce");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("rooot:" +tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                   


                    for (Map<String, Object> obj : list) {
                        

                                           
                       Double idA = (Double) (obj.get("idAnnonce"));
                        int a = (int) round(idA);
                       Annonce a1 = new Annonce();
                       
                    
                       a1.setIdAnnonce(a);
                       a1.setCinUser(obj.get("cin").toString());
                       a1.setTitre(obj.get("titre").toString());
                       a1.setType(obj.get("type").toString());
                       a1.setDescription(obj.get("description").toString());
//                       a1.setPhotoAnimal(obj.get("photoAnimal").toString());
                    
                                             
                  
                    listTasks.add(a1);

                    }
                } catch (IOException ex) {
                }

            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    
}
