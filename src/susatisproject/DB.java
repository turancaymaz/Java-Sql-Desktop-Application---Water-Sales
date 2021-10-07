package susatisproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author turan
 */
public class DB {

    private final String driver = "org.sqlite.JDBC";
    private final String url = "jdbc:sqlite:db/susatis.db";

    Connection conn = null;
    Statement st = null;

    public Statement baglan() {

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            st = conn.createStatement();
            System.out.println("Bağlantı başarılı.");
        } catch (Exception e) {
            System.err.println("Bağlantı hatası." + e);
        }

        return st;
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            if (st != null) {
                st.close();
            }
        } catch (Exception e) {
            System.out.println("Close Error " + e);
        }
    }
}
