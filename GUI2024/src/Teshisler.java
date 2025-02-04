import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Teshisler extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_ID;
    private JTextField textField_Name;
    private JTextField textField_Symptoms;
    private JTextField textField_Diagnoses;
    private JTextField textField_Medications;
    private JTable table;
    private JComboBox<String> comboBox_PatID;
    private Connection conn;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Teshisler frame = new Teshisler();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Teshisler() {
        // Veritabanı bağlantısını başlat
        conn = Baglanti.Bagla();

        // JFrame ayarları
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Üst panel başlığı
        JPanel panel = new JPanel();
        panel.setBackground(new Color(176, 196, 222));
        panel.setBounds(0, 0, 1089, 91);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("Hastane Yönetim Sistemi");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblTitle.setBounds(429, 10, 360, 26);
        panel.add(lblTitle);

        JLabel lblSubtitle = new JLabel("Teşhisleri Yönetme Merkezi");
        lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitle.setForeground(Color.WHITE);
        lblSubtitle.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblSubtitle.setBounds(463, 54, 326, 26);
        panel.add(lblSubtitle);

        // Bileşenleri ekle
        addComponents();

        // Veritabanından hasta ID'lerini ve teşhis bilgilerini yükle
        loadPatientIDs();
        loadTableData();
    }

    private void addComponents() {
        // Etiketler ve metin alanları
        JLabel lblID = new JLabel("ID");
        lblID.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblID.setBounds(10, 117, 88, 26);
        contentPane.add(lblID);

        JLabel lblPatID = new JLabel("PatID");
        lblPatID.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblPatID.setBounds(10, 166, 88, 26);
        contentPane.add(lblPatID);

        JLabel lblName = new JLabel("İsim");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblName.setBounds(10, 226, 88, 26);
        contentPane.add(lblName);

        JLabel lblSymptoms = new JLabel("Semptomlar");
        lblSymptoms.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblSymptoms.setBounds(675, 166, 107, 26);
        contentPane.add(lblSymptoms);

        JLabel lblDiagnoses = new JLabel("Teşhisler");
        lblDiagnoses.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblDiagnoses.setBounds(337, 118, 88, 26);
        contentPane.add(lblDiagnoses);

        JLabel lblMedications = new JLabel("İlaçlar");
        lblMedications.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblMedications.setBounds(337, 189, 88, 26);
        contentPane.add(lblMedications);

        textField_ID = new JTextField();
        textField_ID.setFont(new Font("Tahoma", Font.BOLD, 17));
        textField_ID.setBounds(116, 117, 177, 26);
        contentPane.add(textField_ID);

        comboBox_PatID = new JComboBox<>();
        comboBox_PatID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        comboBox_PatID.setBounds(116, 164, 177, 32);
        contentPane.add(comboBox_PatID);

        textField_Name = new JTextField();
        textField_Name.setFont(new Font("Tahoma", Font.BOLD, 17));
        textField_Name.setBounds(108, 226, 177, 26);
        contentPane.add(textField_Name);

        textField_Symptoms = new JTextField();
        textField_Symptoms.setFont(new Font("Tahoma", Font.BOLD, 17));
        textField_Symptoms.setBounds(798, 117, 217, 135);
        contentPane.add(textField_Symptoms);

        textField_Diagnoses = new JTextField();
        textField_Diagnoses.setFont(new Font("Tahoma", Font.BOLD, 17));
        textField_Diagnoses.setBounds(443, 118, 177, 26);
        contentPane.add(textField_Diagnoses);

        textField_Medications = new JTextField();
        textField_Medications.setFont(new Font("Tahoma", Font.BOLD, 17));
        textField_Medications.setBounds(443, 189, 177, 26);
        contentPane.add(textField_Medications);

        // Butonlar
        JButton btnAdd = new JButton("Ekle");
        btnAdd.setBackground(new Color(0, 128, 128));
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnAdd.setBounds(90, 275, 116, 52);
        btnAdd.addActionListener(e -> addDiagnosis());
        contentPane.add(btnAdd);

        JButton btnUpdate = new JButton("Güncelle");
        btnUpdate.setBackground(new Color(0, 128, 128));
        btnUpdate.setForeground(new Color(255, 255, 255));
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnUpdate.setBounds(90, 338, 116, 52);
        btnUpdate.addActionListener(e -> updateDiagnosis());
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Sil");
        btnDelete.setBackground(new Color(0, 128, 128));
        btnDelete.setForeground(new Color(255, 255, 255));
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnDelete.setBounds(309, 296, 116, 52);
        btnDelete.addActionListener(e -> deleteDiagnosis());
        contentPane.add(btnDelete);

        JButton btnClear = new JButton("Temizle");
        btnClear.setBackground(new Color(0, 128, 128));
        btnClear.setForeground(new Color(255, 255, 255));
        btnClear.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnClear.setBounds(518, 296, 116, 52);
        btnClear.addActionListener(e -> clearFields());
        contentPane.add(btnClear);

        // Tablo
        model = new DefaultTableModel(new Object[]{"ID", "PatID", "İsim", "Semptomlar", "Teşhisler", "İlaçlar"}, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 400, 1050, 300);
        contentPane.add(scrollPane);
        
        JButton btnAnaEkran = new JButton("Ana Ekran");
        btnAnaEkran.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HomeForm homeform = new HomeForm();
                homeform.setVisible(true);
                dispose();
        	}
        });
        btnAnaEkran.setBackground(new Color(0, 128, 128));
        btnAnaEkran.setForeground(new Color(255, 255, 255));
        btnAnaEkran.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnAnaEkran.setBounds(795, 319, 140, 52);
        contentPane.add(btnAnaEkran);
    }

    private void addDiagnosis() {
        try {
            String query = "INSERT INTO Diagnoses (PatId, Name, Symptoms, Diagnoses, Medications) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(comboBox_PatID.getSelectedItem().toString()));
            pstmt.setString(2, textField_Name.getText());
            pstmt.setString(3, textField_Symptoms.getText());
            pstmt.setString(4, textField_Diagnoses.getText());
            pstmt.setString(5, textField_Medications.getText());
            pstmt.executeUpdate();
            loadTableData();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateDiagnosis() {
        try {
            String query = "UPDATE Diagnoses SET Name = ?, Symptoms = ?, Diagnoses = ?, Medications = ? WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, textField_Name.getText());
            pstmt.setString(2, textField_Symptoms.getText());
            pstmt.setString(3, textField_Diagnoses.getText());
            pstmt.setString(4, textField_Medications.getText());
            pstmt.setInt(5, Integer.parseInt(textField_ID.getText()));
            pstmt.executeUpdate();
            loadTableData();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteDiagnosis() {
        try {
            String query = "DELETE FROM Diagnoses WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(textField_ID.getText()));
            pstmt.executeUpdate();
            loadTableData();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        textField_ID.setText("");
        comboBox_PatID.setSelectedIndex(0);
        textField_Name.setText("");
        textField_Symptoms.setText("");
        textField_Diagnoses.setText("");
        textField_Medications.setText("");
    }

    private void loadPatientIDs() {
        try {
            String query = "SELECT PatId FROM PatientInf";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                comboBox_PatID.addItem(String.valueOf(rs.getInt("PatId")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTableData() {
        try {
            String query = "SELECT * FROM Diagnoses";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("ID"),
                        rs.getInt("PatId"),
                        rs.getString("Name"),
                        rs.getString("Symptoms"),
                        rs.getString("Diagnoses"),
                        rs.getString("Medications")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
