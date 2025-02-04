import java.sql.*;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Baglanti {
	public static Connection Bagla()
	{
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection Yol = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Mertroot\\Desktop\\GUI2024\\Data\\MyDatabase.db");
			//System.out.println("Bağlanti Başarırlı...");
			//JOptionPane.showMessageDialog(null, "Bağlanti Başarırlı...");
			return Yol;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
