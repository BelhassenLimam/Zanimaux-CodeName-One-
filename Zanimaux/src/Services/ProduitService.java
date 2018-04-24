/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Magasin;
import Entities.Produit;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author macbookpro
 */
public class ProduitService {
    
        public ArrayList<Produit> getAllProduit(int idMagasin){
        ArrayList<Produit> listProduit = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8888/zanimauxWeb/web/app_dev.php/api/afficheProduit/" + idMagasin);
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
                        float id = Float.parseFloat(obj.get("idProduit").toString());
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        float quantite = Float.parseFloat(obj.get("quantite").toString());
                        Produit p = new Produit();
                        p.setIdProduit((int)id);
                        p.setIdMagasin(idMagasin);
                        p.setLibelle(obj.get("libelle").toString());
                        if(obj.get("description")==null){
                            p.setDescription("");
                        }
                        else{
                        p.setDescription(obj.get("description").toString());}
                        p.setMarque(obj.get("marque").toString());
                        p.setType(obj.get("type").toString());
                        p.setPhotoProduit(obj.get("photoProduit").toString());
                        p.setPrix(prix);
                        p.setQuantite((int)quantite);
                        listProduit.add(p);
                    }
                } catch (IOException ex) {
                }

            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduit;
    }
}
