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
public class MusteriDuzenle {
    
      public boolean duzenle(String id ,String ad, String soyAd, String tel, String adres){
        
      
        if (id.equals("") || ad.equals("") || soyAd.equals("") || tel.equals("") || adres.equals("") ) {
               JOptionPane.showMessageDialog(null, "Lütfen boş alan bırakmayın!");
               return true;
        }else{
             String query = "UPDATE musteriler SET madi = '"+ad+"', msoyadi= '"+soyAd+"', mtelefon= '"+tel+"', madres='"+adres+"'   WHERE mid = '"+id+"'";
        DB db= new DB();
        try {
            int ekle = db.baglan().executeUpdate(query);
            if (ekle>0) {
                JOptionPane.showMessageDialog(null, "Musteri güncellendi!");
                MusteriListele mlist = new MusteriListele();
                AnaEkran.tblMusteri.setModel(mlist.listeleme());
                
                
            }
        } catch (Exception e) {
            System.err.println("Update hata: " + e);
        }finally{
            db.close();
        }
          
        
        }
        
        return false;
      
        
    }
    
}
