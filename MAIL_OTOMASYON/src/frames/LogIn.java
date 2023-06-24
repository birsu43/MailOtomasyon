package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Class.Users;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField txtAd;
	private JTextField txtSifre;
    private Connection con=null;
    private  java.sql.Statement stmt=null;
    private ResultSet rs=null;
    private Users user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
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
	public LogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 734, 441);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\birsu\\Downloads\\icons8-login-64.png"));
		lblNewLabel.setBounds(278, 26, 78, 88);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kayıt Ol");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(288, 124, 101, 20);
		panel.add(lblNewLabel_1);
		
		txtAd = new JTextField();
		txtAd.setBounds(248, 181, 216, 19);
		panel.add(txtAd);
		txtAd.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Kullanıcı Adı");
		lblNewLabel_2.setBounds(162, 184, 76, 13);
		panel.add(lblNewLabel_2);
		
		txtSifre = new JTextField();
		txtSifre.setBounds(248, 230, 216, 19);
		panel.add(txtSifre);
		txtSifre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Şifre");
		lblNewLabel_3.setBounds(179, 233, 45, 13);
		panel.add(lblNewLabel_3);
		
		
		
		JLabel lblNewLabel_4 = new JLabel("Hesabınız yok mu?");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SignUp sign=new SignUp();
				sign.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				sign.setVisible(true);
			}
		});
		lblNewLabel_4.setBounds(278, 338, 171, 13);
		panel.add(lblNewLabel_4);
		
		
		//--------------------------Buton-----------------------------------
		JButton btnGiris = new JButton("Giriş Yap");
		btnGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Giris();
				
			}
		});

			
		btnGiris.setBounds(280, 286, 109, 21);
		panel.add(btnGiris);
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
	
	public void Giris() {
		user=new Users();
		String kulAd=txtAd.getText();
		user.setKulAd(kulAd);
		String sifre=txtSifre.getText();
		try {
			Db();
			String sql="SELECT  mail, sifre\r\n FROM public.users where mail='"+kulAd+"' and sifre='"+sifre+"';";
			rs=stmt.executeQuery(sql);
			if (rs.next()) {
				JOptionPane.showMessageDialog(null,"Giriş başarılı!");
				 Main main=new Main();
				 main.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null,"Kullanıcı adı veya şifre hatalı!");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
