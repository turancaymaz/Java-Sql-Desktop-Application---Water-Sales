/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package susatisproject;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author turan
 */
public class SiparisGetir {
     ArrayList<Siparis> ls = new ArrayList<>();

    public void SiparisleriGetir() {

        ls.clear();
        DB db = new DB();
        try {
            String query = "select *from siparisler";
            ResultSet rs = db.baglan().executeQuery(query);
            while (rs.next()) {
                Siparis ks = new Siparis();
                ks.setId(rs.getString("" + MEnum.sid));
                ks.setAdi(rs.getString("" + MEnum.smusteriadi));
                ks.setSoyadi(rs.getString("" + MEnum.smusterisoyadi));
                ks.setDurum(rs.getString(""+MEnum.sdurum));
                ks.setAdres(rs.getString("" + MEnum.sadres));
                ks.setTel(rs.getString("" + MEnum.stelefon));
                ks.setTutar(rs.getString("" + MEnum.stutar));

                ls.add(ks);

            }
        } catch (Exception e) {
            System.err.println("Data Getirme Hatası :" + e);
        }finally{
            db.close();
        }

        

    }

    public DefaultTableModel siparisListele() {
        SiparisleriGetir();
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }            
        };
       
        dtm.addColumn("id");
        dtm.addColumn("İsim");
        dtm.addColumn("Soyisim");
        dtm.addColumn("Durum");
        dtm.addColumn("Adres");
        dtm.addColumn("Telefon");
        dtm.addColumn("Tutar");
        String durum="";
        for (Siparis l : ls) {
            switch (l.getDurum()) {
                case "0":
                    durum = "Hazırlanıyor";
                    break;
                case "1":
                    durum = "Yolda";
                    break;
                case "2":
                    durum = "Teslim Edildi";
                    break;
                default:
                    break;
            }
            dtm.addRow(new String[]{l.getId(), l.getAdi(), l.getSoyadi(),durum, l.getAdres(),l.getTel(), l.getTutar()});
            durum = "";

        }
        return(dtm);

    }
    
}
