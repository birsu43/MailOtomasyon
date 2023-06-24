package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField txtad;
	private JTextField txtSoyad;
	private JTextField txtKulAd;
	private JTextField txtSifre;
	private Connection con=null;
	private  java.sql.Statement stmt=null;
	private ResultSet rs=null;
	private JComboBox cmbCinsiyet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 543, 489);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\birsu\\Downloads\\icons8-sign-up-64.png"));
		lblNewLabel.setBounds(215, 10, 70, 69);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kayıt Ol");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(215, 101, 70, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ad");
		lblNewLabel_2.setBounds(69, 161, 27, 13);
		panel.add(lblNewLabel_2);
		
		txtad = new JTextField();
		txtad.setBounds(148, 158, 96, 19);
		panel.add(txtad);
		txtad.setColumns(10);
		
		JLabel lbl = new JLabel("Soyad ");
		lbl.setBounds(260, 161, 45, 13);
		panel.add(lbl);
		
		JLabel lbl2 = new JLabel("Kullanıcı Adı");
		lbl2.setBounds(52, 200, 96, 13);
		panel.add(lbl2);
		
		txtSoyad = new JTextField();
		txtSoyad.setBounds(315, 158, 96, 19);
		panel.add(txtSoyad);
		txtSoyad.setColumns(10);
		
		txtKulAd = new JTextField();
		txtKulAd.setBounds(148, 197, 263, 19);
		panel.add(txtKulAd);
		txtKulAd.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Şifre");
		lblNewLabel_5.setBounds(69, 241, 45, 13);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Cinsiyet");
		lblNewLabel_6.setBounds(69, 290, 71, 13);
		panel.add(lblNewLabel_6);
		
		txtSifre = new JTextField();
		txtSifre.setBounds(148, 238, 263, 19);
		panel.add(txtSifre);
		txtSifre.setColumns(10);
		
		 cmbCinsiyet = new JComboBox();
		cmbCinsiyet.setModel(new DefaultComboBoxModel(new String[] {" ", "Kadın", "Erkek", "Belirtmek İstemiyorum"}));
		cmbCinsiyet.setBounds(150, 286, 155, 21);
		panel.add(cmbCinsiyet);
		
		JButton btnKayit = new JButton("Kayıt Ol");
		btnKayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kayit();
				
				
			}
		});
		btnKayit.setBounds(215, 352, 85, 21);
		panel.add(btnKayit);
	}
	public void Db() {
		try {
			 Class.forName("org.postgresql.Driver");
	         con = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/mail",
	            "postgres", "1234");
	         
	          stmt= con.createStatement();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void Kayit() {
		String ad=txtad.getText();
		String soyad=txtSoyad.getText();
		String sifre=txtSifre.getText();
		String mail=txtKulAd.getText();
		String cinsiyet = (String) cmbCinsiyet.getSelectedItem();
       
		try {
			Db();
			String sql="INSERT INTO public.users(\r\n  id,ad, soyad, mail,sifre,cinsiyet)\r\n  VALUES (nextval('m_users'),'"+ad+"', '"+soyad+"', '"+mail+"','"+sifre+"', '"+cinsiyet+"');";
			
			int a=stmt.executeUpdate(sql);
			if (a>0) {
				JOptionPane.showMessageDialog(null, "Kayıt başarılı!");
				LogIn login=new LogIn();
				login.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Kayıt başarısız!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
