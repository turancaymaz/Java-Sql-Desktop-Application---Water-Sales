/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package susatisproject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author turan
 */
public class SiparisEkle {
      public boolean ekle(String ad, String soyad, String adres, String tel, String tutar) {
        
        if (ad.equals("") || soyad.equals("") || adres.equals("") || tel.equals("") || tutar.equals("")  ) {
            JOptionPane.showMessageDialog(null, "Lütfen boş alanları doldurun!");
            return false;
            
        }else{
              DB db = new DB();
        String durum = "0";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        
        String time = dtf.format(localDate);
        
        String query ="insert into siparisler values(null,'"+ad+"','"+soyad+"','"+durum+"','"+adres+"','"+tel+"','"+tutar+"','"+time+"')";
        try {
            int ekle = db.baglan().executeUpdate(query);
            if (ekle > 0) {
                JOptionPane.showMessageDialog(null, "Sipariş eklendi!");
                SiparisGetir sg = new SiparisGetir();
               
                AnaEkran.tblSiparisler.setModel(sg.siparisListele());
            }
        } catch (Exception e) {
            System.err.println("Sipariş ekleme hatası: " + e);
        }finally{
            db.close();
        }
        return true;
        }

    }
    
}
