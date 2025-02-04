import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Patient extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;    // Hasta ID
    private JTextField textField_1; // İsim
    private JTextField textField_2; // Telefon
    private JTextField textField_3; // Yas
    private JTextField textField_4; // Adres
    private JTextField textField_7; // Patoloji
    private JTable table;           // Hasta Listesi Tablosu
    private JComboBox<String> comboBox;    // Cinsiyet
    private JComboBox<String> comboBox_1;  // Kan Grubu
    private Connection conn = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Patient frame = new Patient();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor
    public Patient() {
        conn = Baglanti.Bagla();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1103, 812);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblId = new JLabel("id:");
        lblId.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblId.setBounds(10, 155, 88, 26);
        contentPane.add(lblId);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 17));
        textField.setBounds(116, 155, 177, 26);
        contentPane.add(textField);

        JLabel lblName = new JLabel("İsim");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblName.setBounds(10, 204, 88, 26);
        contentPane.add(lblName);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        textField_1.setBounds(116, 204, 177, 26);
        contentPane.add(textField_1);

        JLabel lblTelefon = new JLabel("Telefon");
        lblTelefon.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblTelefon.setBounds(377, 155, 88, 26);
        contentPane.add(lblTelefon);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.BOLD, 17));
        textField_2.setBounds(483, 155, 177, 26);
        contentPane.add(textField_2);

        JLabel lblYas = new JLabel("Yaşı:");
        lblYas.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblYas.setBounds(377, 204, 88, 26);
        contentPane.add(lblYas);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.BOLD, 17));
        textField_3.setBounds(483, 204, 177, 26);
        contentPane.add(textField_3);

        JLabel lblAdres = new JLabel("Adresi");
        lblAdres.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblAdres.setBounds(10, 258, 88, 26);
        contentPane.add(lblAdres);

        textField_4 = new JTextField();
        textField_4.setFont(new Font("Tahoma", Font.BOLD, 17));
        textField_4.setBounds(116, 258, 177, 26);
        contentPane.add(textField_4);

        JLabel lblCinsiyet = new JLabel("Cinsiyeti:");
        lblCinsiyet.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblCinsiyet.setBounds(377, 258, 88, 26);
        contentPane.add(lblCinsiyet);

        comboBox = new JComboBox<>(new String[]{"Erkek", "Kadın"});
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        comboBox.setBounds(483, 258, 177, 32);
        contentPane.add(comboBox);

        JLabel lblKanGrubu = new JLabel("Kan Grubu");
        lblKanGrubu.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblKanGrubu.setBounds(719, 155, 109, 26);
        contentPane.add(lblKanGrubu);

        comboBox_1 = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "0+", "0-"});
        comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        comboBox_1.setBounds(838, 155, 177, 32);
        contentPane.add(comboBox_1);

        JLabel lblPatoloji = new JLabel("Patoloji");
        lblPatoloji.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblPatoloji.setBounds(732, 204, 88, 26);
        contentPane.add(lblPatoloji);

        textField_7 = new JTextField();
        textField_7.setFont(new Font("Tahoma", Font.BOLD, 17));
        textField_7.setBounds(838, 204, 177, 26);
        contentPane.add(textField_7);

        // Ekle Butonu
        JButton btnAdd = new JButton("Ekle");
        btnAdd.setBackground(new Color(0, 128, 128));
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnAdd.addActionListener(e -> AddPatient());
        btnAdd.setBounds(100, 407, 116, 52);
        contentPane.add(btnAdd);

        // Güncelle Butonu
        JButton btnUpdate = new JButton("Güncelle");
        btnUpdate.setBackground(new Color(0, 128, 128));
        btnUpdate.setForeground(new Color(255, 255, 255));
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnUpdate.addActionListener(e -> UpdatePatient());
        btnUpdate.setBounds(100, 328, 116, 52);
        contentPane.add(btnUpdate);

        // Sil Butonu
        JButton btnDelete = new JButton("Sil");
        btnDelete.setBackground(new Color(0, 128, 128));
        btnDelete.setForeground(new Color(255, 255, 255));
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnDelete.addActionListener(e -> DeletePatient());
        btnDelete.setBounds(352, 367, 116, 52);
        contentPane.add(btnDelete);

        // Temizle Butonu
        JButton btnClear = new JButton("Temizle");
        btnClear.setBackground(new Color(0, 128, 128));
        btnClear.setForeground(new Color(255, 255, 255));
        btnClear.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnClear.addActionListener(e -> ClearFields());
        btnClear.setBounds(564, 367, 116, 52);
        contentPane.add(btnClear);

        // Hasta Listesi Tablosu
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 470, 1089, 295);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(176, 196, 222));
        panel.setBounds(0, 0, 1089, 122);
        contentPane.add(panel);
        
        JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemi");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblNewLabel.setBounds(428, 5, 263, 26);
        panel.add(lblNewLabel);
        
        JLabel lblHastalarYnet = new JLabel("Hastalari Yönetme Merkezi");
        lblHastalarYnet.setHorizontalAlignment(SwingConstants.CENTER);
        lblHastalarYnet.setForeground(Color.WHITE);
        lblHastalarYnet.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblHastalarYnet.setBounds(378, 66, 333, 26);
        panel.add(lblHastalarYnet);
        
        JButton Backbtn = new JButton("Ana Ekran");
        Backbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HomeForm homeform = new HomeForm();
        		homeform.setVisible(true);
        		dispose();
        	}
        });
        Backbtn.setForeground(new Color(255, 255, 255));
        Backbtn.setFont(new Font("Tahoma", Font.BOLD, 17));
        Backbtn.setBackground(new Color(0, 128, 128));
        Backbtn.setBounds(778, 367, 162, 52);
        contentPane.add(Backbtn);

        Selectional(); // Hasta listesini doldur
    }

    private void Selectional() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PatientInf");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AddPatient() {
        try {
            String sql = "INSERT INTO PatientInf (PatId, Isim, Telefon, Yas, Adres, Cinsiyet, KanGrubu, Patoloji) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(textField.getText()));
            pst.setString(2, textField_1.getText());
            pst.setString(3, textField_2.getText());
            pst.setInt(4, Integer.parseInt(textField_3.getText()));
            pst.setString(5, textField_4.getText());
            pst.setString(6, comboBox.getSelectedItem().toString());
            pst.setString(7, comboBox_1.getSelectedItem().toString());
            pst.setString(8, textField_7.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Hasta başarıyla eklendi!");
            Selectional();
            ClearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Hasta eklenirken hata oluştu!");
        }
    }

    private void UpdatePatient() {
        try {
            String sql = "UPDATE PatientInf SET Isim=?, Telefon=?, Yas=?, Adres=?, Cinsiyet=?, KanGrubu=?, Patoloji=? WHERE PatId=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, textField_1.getText());
            pst.setString(2, textField_2.getText());
            pst.setInt(3, Integer.parseInt(textField_3.getText()));
            pst.setString(4, textField_4.getText());
            pst.setString(5, comboBox.getSelectedItem().toString());
            pst.setString(6, comboBox_1.getSelectedItem().toString());
            pst.setString(7, textField_7.getText());
            pst.setInt(8, Integer.parseInt(textField.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Hasta başarıyla güncellendi!");
            Selectional();
            ClearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Hasta güncellenirken hata oluştu!");
        }
    }

    private void DeletePatient() {
        try {
            String sql = "DELETE FROM PatientInf WHERE PatId=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(textField.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Hasta başarıyla silindi!");
            Selectional();
            ClearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Hasta silinirken hata oluştu!");
        }
    }

    private void ClearFields() {
        textField.setText("");
        textField_1.setText("");
        textField_2.setText("");
        textField_3.setText("");
        textField_4.setText("");
        textField_7.setText("");
        comboBox.setSelectedIndex(0);
        comboBox_1.setSelectedIndex(0);
    }
}
