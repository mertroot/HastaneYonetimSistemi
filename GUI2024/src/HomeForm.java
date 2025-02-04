import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeForm frame = new HomeForm();
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
	public HomeForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1068, 580);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(0, 0, 1044, 120);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemi");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(324, 10, 376, 33);
		panel.add(lblNewLabel);
		
		JLabel lblAnaEkran = new JLabel("Ana Ekrana Hoşgeldin");
		lblAnaEkran.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnaEkran.setForeground(Color.WHITE);
		lblAnaEkran.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblAnaEkran.setBounds(324, 66, 376, 33);
		panel.add(lblAnaEkran);
		
		JLabel lblDoctors = new JLabel("Doktorlarımız");
		lblDoctors.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Doctor doktorform= new Doctor();
        		doktorform.setVisible(true);
        		dispose();
			}
		});
		lblDoctors.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctors.setForeground(Color.GRAY);
		lblDoctors.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblDoctors.setBounds(404, 169, 230, 48);
		contentPane.add(lblDoctors);
		
		JLabel lblHastalar = new JLabel("Hastalarımız");
		lblHastalar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Patient hastaform= new Patient();
        		hastaform.setVisible(true);
        		dispose();
			}
		});
		lblHastalar.setHorizontalAlignment(SwingConstants.CENTER);
		lblHastalar.setForeground(Color.GRAY);
		lblHastalar.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblHastalar.setBounds(404, 268, 230, 48);
		contentPane.add(lblHastalar);
		
		JLabel lblHastalklar = new JLabel("Teşhislerimiz");
		lblHastalklar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Teshisler teshisform= new Teshisler();
        		teshisform.setVisible(true);
        		dispose();
			}
		});
		lblHastalklar.setHorizontalAlignment(SwingConstants.CENTER);
		lblHastalklar.setForeground(Color.GRAY);
		lblHastalklar.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblHastalklar.setBounds(404, 355, 230, 48);
		contentPane.add(lblHastalklar);
		
		JLabel lblk = new JLabel("Çıkış");
		lblk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblk.setHorizontalAlignment(SwingConstants.CENTER);
		lblk.setForeground(Color.GRAY);
		lblk.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblk.setBounds(404, 445, 230, 48);
		contentPane.add(lblk);
	}
}
