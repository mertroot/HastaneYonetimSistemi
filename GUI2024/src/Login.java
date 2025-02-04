import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    Connection conn = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        conn = Baglanti.Bagla(); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 988, 533);
        contentPane = new JPanel();
        contentPane.setBackground(Color.ORANGE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblHastaneYnetimSistemi = new JLabel("Hastane Sistemi");
        lblHastaneYnetimSistemi.setHorizontalAlignment(SwingConstants.CENTER);
        lblHastaneYnetimSistemi.setForeground(Color.BLACK);
        lblHastaneYnetimSistemi.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 35));
        lblHastaneYnetimSistemi.setBounds(246, 25, 407, 87);
        contentPane.add(lblHastaneYnetimSistemi);

        JLabel lblUsername = new JLabel("Kullanıcı Adı");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblUsername.setBounds(227, 135, 115, 37);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Şifre");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblPassword.setBounds(246, 247, 115, 34);
        contentPane.add(lblPassword);

        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(366, 138, 251, 37);
        contentPane.add(textFieldUsername);
        textFieldUsername.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(366, 249, 251, 37);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("Giriş Yap");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textFieldUsername.getText();
                String password = new String(passwordField.getPassword());

                try {
                    String sql = "SELECT * FROM Account WHERE AccountName = ? AND AccountPassword = ?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1, username);
                    pst.setString(2, password);

                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        // Giriş başarılı
                        javax.swing.JOptionPane.showMessageDialog(null, "Giriş başarılı!");
                        HomeForm homeform = new HomeForm();
                        homeform.setVisible(true);
                        dispose();
                    } else {
                        // Giriş başarısız
                        javax.swing.JOptionPane.showMessageDialog(null, "Geçersiz kullanıcı adı veya şifre.");
                    }

                    rs.close();
                    pst.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnLogin.setBounds(186, 369, 156, 78);
        contentPane.add(btnLogin);

        JButton btnClear = new JButton("Temizlemek");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldUsername.setText("");
                passwordField.setText("");
            }
        });
        btnClear.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnClear.setBounds(461, 369, 156, 78);
        contentPane.add(btnClear);
    }
}
