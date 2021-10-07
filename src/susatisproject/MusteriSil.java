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
public class MusteriSil {
    public void sil(String id){
        
        String query = "delete from musteriler where mid = '"+id+"'";
        DB db = new DB();
        try {
            if (id.equals("")) {
                JOptionPane.showMessageDialog(null, "Lütfen bir satır seçiniz");
                
            }
            else{
                db.baglan().executeUpdate(query);
            }
            
        } catch (Exception e) {
            System.err.println("Müşteri silme hatası: " + e);
        }finally{
            db.close();
        }
        
    }

    
}
