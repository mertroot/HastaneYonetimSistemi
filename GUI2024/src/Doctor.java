import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Doctor extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField DocId;
    private JTextField DocName;
    private JTextField DocPass;
    private JTextField DocExp;
    private JTable DoctorTable;

    Statement St = null;
    ResultSet Rs = null;
    Connection conn = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Doctor frame = new Doctor();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void Selectional() {
        try {
            conn = Baglanti.Bagla();
            St = conn.createStatement();
            Rs = St.executeQuery("SELECT * FROM DoctorTbl");
            DoctorTable.setModel(DbUtils.resultSetToTableModel(Rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AddDoctor() {
        try {
            String sql = "INSERT INTO DoctorTbl (DocId, DocName, DocPass, DocExp) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(DocId.getText()));
            pst.setString(2, DocName.getText());
            pst.setString(3, DocPass.getText());
            pst.setInt(4, Integer.parseInt(DocExp.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Doktor başarıyla eklendi!");
            Selectional();
            ClearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Doktor eklenirken hata oluştu!");
        }
    }

    private void UpdateDoctor() {
        try {
            String sql = "UPDATE DoctorTbl SET DocName=?, DocPass=?, DocExp=? WHERE DocId=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, DocName.getText());
            pst.setString(2, DocPass.getText());
            pst.setInt(3, Integer.parseInt(DocExp.getText()));
            pst.setInt(4, Integer.parseInt(DocId.getText()));
            int updatedRows = pst.executeUpdate();
            if (updatedRows > 0) {
                JOptionPane.showMessageDialog(this, "Doktor başarıyla güncellendi!");
            } else {
                JOptionPane.showMessageDialog(this, "Güncellenecek doktor bulunamadı!");
            }
            Selectional();
            ClearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Doktor güncellenirken hata oluştu!");
        }
    }

    private void DeleteDoctor() {
        try {
            String sql = "DELETE FROM DoctorTbl WHERE DocId=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(DocId.getText()));
            int deletedRows = pst.executeUpdate();
            if (deletedRows > 0) {
                JOptionPane.showMessageDialog(this, "Doktor başarıyla silindi!");
            } else {
                JOptionPane.showMessageDialog(this, "Silinecek doktor bulunamadı!");
            }
            Selectional();
            ClearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Doktor silinirken hata oluştu!");
        }
    }

    private void ClearFields() {
        DocId.setText("");
        DocName.setText("");
        DocPass.setText("");
        DocExp.setText("");
    }

    public Doctor() {
        conn = Baglanti.Bagla();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1129, 857);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1119, 150);
        panel.setBackground(new Color(176, 196, 222));
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemi");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel.setBounds(368, 31, 352, 26);
        panel.add(lblNewLabel);

        JLabel lblDoktorlarYnet = new JLabel("Doktor Yonetim Sayfasi");
        lblDoktorlarYnet.setHorizontalAlignment(SwingConstants.CENTER);
        lblDoktorlarYnet.setForeground(Color.WHITE);
        lblDoktorlarYnet.setFont(new Font("Tahoma", Font.BOLD, 21));
        lblDoktorlarYnet.setBounds(408, 95, 263, 26);
        panel.add(lblDoktorlarYnet);

        JLabel lblDoktorlarBilgileri = new JLabel("Doktor Bilgileri");
        lblDoktorlarBilgileri.setBounds(338, 212, 263, 26);
        lblDoktorlarBilgileri.setHorizontalAlignment(SwingConstants.CENTER);
        lblDoktorlarBilgileri.setForeground(Color.BLACK);
        lblDoktorlarBilgileri.setFont(new Font("Tahoma", Font.BOLD, 21));
        contentPane.add(lblDoktorlarBilgileri);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(10, 310, 88, 26);
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        lblId.setForeground(Color.BLACK);
        lblId.setFont(new Font("Tahoma", Font.BOLD, 17));
        contentPane.add(lblId);

        JLabel lblName = new JLabel("İsim");
        lblName.setBounds(10, 367, 88, 26);
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setForeground(Color.BLACK);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 17));
        contentPane.add(lblName);

        JLabel lblPassword = new JLabel("Şifre");
        lblPassword.setBounds(557, 289, 88, 26);
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 17));
        contentPane.add(lblPassword);

        JLabel lblExperience = new JLabel("Deneyim");
        lblExperience.setBounds(557, 367, 88, 26);
        lblExperience.setHorizontalAlignment(SwingConstants.CENTER);
        lblExperience.setForeground(Color.BLACK);
        lblExperience.setFont(new Font("Tahoma", Font.BOLD, 17));
        contentPane.add(lblExperience);

      
        DocId = new JTextField();
        DocId.setBounds(116, 310, 177, 26);
        DocId.setFont(new Font("Tahoma", Font.BOLD, 17));
        contentPane.add(DocId);
        DocId.setColumns(10);

        DocName = new JTextField();
        DocName.setBounds(116, 367, 177, 26);
        DocName.setFont(new Font("Tahoma", Font.BOLD, 17));
        contentPane.add(DocName);
        DocName.setColumns(10);

        DocPass = new JTextField();
        DocPass.setBounds(669, 289, 177, 26);
        DocPass.setFont(new Font("Tahoma", Font.BOLD, 17));
        contentPane.add(DocPass);
        DocPass.setColumns(10);

        DocExp = new JTextField();
        DocExp.setBounds(669, 367, 177, 26);
        DocExp.setFont(new Font("Tahoma", Font.BOLD, 17));
        contentPane.add(DocExp);
        DocExp.setColumns(10);

        // Ekleme Butonu
        JButton Addbtn = new JButton("Ekle");
        Addbtn.setBackground(new Color(0, 128, 128));
        Addbtn.setForeground(new Color(255, 255, 255));
        Addbtn.setBounds(99, 434, 100, 40);
        Addbtn.setFont(new Font("Tahoma", Font.BOLD, 17));
        Addbtn.addActionListener(e -> AddDoctor());
        contentPane.add(Addbtn);

        // Silme Butonu
        JButton Deletebtn = new JButton("Sil");
        Deletebtn.setBackground(new Color(0, 128, 128));
        Deletebtn.setForeground(new Color(255, 255, 255));
        Deletebtn.setBounds(399, 434, 100, 40);
        Deletebtn.setFont(new Font("Tahoma", Font.BOLD, 17));
        Deletebtn.addActionListener(e -> DeleteDoctor());
        contentPane.add(Deletebtn);

        // Güncelleme Butonu
        JButton Updatebtn = new JButton("Güncelle");
        Updatebtn.setBackground(new Color(0, 128, 128));
        Updatebtn.setForeground(new Color(255, 255, 255));
        Updatebtn.setBounds(221, 434, 130, 40);
        Updatebtn.setFont(new Font("Tahoma", Font.BOLD, 17));
        Updatebtn.addActionListener(e -> UpdateDoctor());
        contentPane.add(Updatebtn);

        // Temzileme Butonu
        JButton Clearbtn = new JButton("Temizle");
        Clearbtn.setBackground(new Color(0, 128, 128));
        Clearbtn.setForeground(new Color(255, 255, 255));
        Clearbtn.setBounds(550, 434, 120, 40);
        Clearbtn.setFont(new Font("Tahoma", Font.BOLD, 17));
        Clearbtn.addActionListener(e -> ClearFields());
        contentPane.add(Clearbtn);

        // Ana Ekrana Dönme Butonu
        JButton Backbtn = new JButton("Ana Ekran");
        Backbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HomeForm homeform = new HomeForm();
        		homeform.setVisible(true);
        		dispose();
        	}
        });
        Backbtn.setBackground(new Color(0, 128, 128));
        Backbtn.setForeground(new Color(255, 255, 255));
        Backbtn.setBounds(743, 434, 162, 40);
        Backbtn.setFont(new Font("Tahoma", Font.BOLD, 17));
        contentPane.add(Backbtn);

        // Tablo
        DoctorTable = new JTable();
        DoctorTable.setFont(new Font("Tahoma", Font.BOLD, 10));
        JScrollPane scrollPane = new JScrollPane(DoctorTable);
        scrollPane.setBounds(10, 500, 1100, 300);
        contentPane.add(scrollPane);

        Selectional();
    }
}
