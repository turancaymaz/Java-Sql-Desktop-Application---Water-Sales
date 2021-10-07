package susatisproject;

import javax.swing.JOptionPane;

/**
 *
 * @author turan
 */
public class MusteriEkle {

    private final String PHONE_PATTERN = "(([\\+]90?)|([0]?))([ ]?)((\\([0-9]{3}\\))|([0-9]{3}))([ ]?)([0-9]{3})(\\s*[\\-]?)([0-9]{2})(\\s*[\\-]?)([0-9]{2})";

    public boolean ekle(String ad, String soyAd, String tel, String adres) {
        if (ad.equals("") || soyAd.equals("") || tel.equals("") || adres.equals("")) {
            JOptionPane.showMessageDialog(null, "Lütfen boş alanları doldurun!");
            return false;
        } else if (!tel.matches(PHONE_PATTERN)) {
            JOptionPane.showMessageDialog(null, "Lütfen formata uygun telefon giriniz");
            return false;
        } else {
            String query = "insert into musteriler values (null,'" + ad + "','" + soyAd + "','" + tel + "','" + adres + "')";
            DB db = new DB();
            try {

                int ekle = db.baglan().executeUpdate(query);
                if (ekle > 0) {
                    MusteriListele ml = new MusteriListele();
                    AnaEkran.tblMusteri.setModel(ml.listeleme());
                    JOptionPane.showMessageDialog(null, "Müşteri eklendi!");

                }
            } catch (Exception e) {
                System.err.println("Ekleme hatasi:" + e);
            } finally {
                db.close();
            }
            return true;
        }

    }

}
