/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package susatisproject;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author turan
 */
public class GununSiparisleri {
      List<Siparis> ls = new ArrayList<>();
    
    public void dataGetir(){
        ls.clear();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String time = dtf.format(localDate);
        
        DB db = new DB();
        String query = "select * from siparisler where stime = '"+time+"' ";
        
        try {
            ResultSet rs = db.baglan().executeQuery(query);
            while (rs.next()) {                
                Siparis sp = new Siparis();
                sp.setId(rs.getString(""+MEnum.sid));
                sp.setAdi(rs.getString(""+MEnum.smusteriadi));
                sp.setSoyadi(rs.getString(""+MEnum.smusterisoyadi));
                sp.setDurum(rs.getString(""+MEnum.sdurum));
                sp.setAdres(rs.getString(""+MEnum.sadres));
                sp.setTel(rs.getString(""+MEnum.stelefon));
                sp.setTutar(rs.getString(""+MEnum.stutar));
                ls.add(sp);
                
            }
        } catch (Exception e) {
            System.err.println("Gunun datagetir hata: " + e);
        }finally{
            db.close();
        }
   
    }
    
    public DefaultTableModel listele(){
        dataGetir();
        
        DefaultTableModel table = new DefaultTableModel();
        
        table.addColumn("Id");
        table.addColumn("Ad");
        table.addColumn("Soyad");
        table.addColumn("Durum");
        table.addColumn("Adres");
        table.addColumn("Telefon");
        table.addColumn("Tutar");
        
        String durum = "";
        
        for (Siparis l : ls) {
            
            switch (l.getDurum()) {
                case "0":
                    durum="Hazirlaniyor";
                    break;
                case "1":
                    durum="Yolda";
                    break;
                case "2":
                    durum="Teslim edildi";
                    break;
                default:
                    break;
            }
            
            table.addRow(new String[]{l.getId(),l.getAdi(),l.getSoyadi(),durum,l.getAdres(),l.getTel(),l.getTutar()} );
            durum="";
            
        }
        
        
        return table;
    }
    
}
