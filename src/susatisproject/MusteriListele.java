/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package susatisproject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author turan
 */
public class MusteriListele {
      List<Musteri> ls = new ArrayList<>();

    public void dataGetir() {

        ls.clear();
        DB db = new DB();

        String query = "select * from musteriler";
        try {
            ResultSet rs = db.baglan().executeQuery(query);

            while (rs.next()) {
                Musteri ms = new Musteri();
                ms.setId(rs.getString(1));
                ms.setAd(rs.getString(2));
                ms.setSoyad(rs.getString(3));
                ms.setTel(rs.getString(4));
                ms.setAdres(rs.getString(5));
                ls.add(ms);

            }
        } catch (Exception e) {
            System.err.println("Data Çekme Hatası: " + e);
        }finally{
            db.close();
        }
    }

    public DefaultTableModel listeleme() {

        dataGetir();

        // table in kullanici tarafindan edit edilmesini engellemek icin isCellEditable overide edilip false yapildi
        DefaultTableModel tabel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        
        tabel.addColumn("id");
        tabel.addColumn("İsim");
        tabel.addColumn("Soyisim");
        tabel.addColumn("Telefon");
        tabel.addColumn("Adres");

        for (Musteri l : ls) {

            tabel.addRow(new String[]{l.getId(), l.getAd(), l.getSoyad(), l.getTel(), l.getAdres()});

        }

        return tabel;

    }
    
}
