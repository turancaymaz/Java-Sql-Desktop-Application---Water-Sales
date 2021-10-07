/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package susatisproject;

import javax.swing.JOptionPane;

/**
 *
 * @author turan
 */
public class SiparisSil {
       public void sil(String id){
        DB db = new DB();
        
        String querry = "delete from siparisler where sid ='"+id+"'";
        try {
            if (id.equals("")) {
                JOptionPane.showMessageDialog(null,"Lütfen satır seçiniz");
            }
            else{
            db.baglan().executeUpdate(querry);
            }
        } catch (Exception e) {
            System.err.println("Siparis silme hatasi : " + e);
        }finally{
            db.close();
        }
        
    
    }
    
}
