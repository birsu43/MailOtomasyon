package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Class.Mails;
import Class.Users;

import java.awt.SystemColor;
import java.awt.TextArea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Mail extends JFrame {

	private JPanel contentPane;
	private JTextField txtAlici;
	private JTextField txtKonu;
	private Connection con = null;
	private java.sql.Statement stmt = null;
	private ResultSet rs = null;
	private Users user;
	private Mails mails;
	private PreparedStatement pstmt = null;
	private JTextArea txtIcerik;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mail frame = new Mail();
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
	public Mail() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				goster();
				gosterGonderilenler();
				gosterCop();
				gosterSpam();
				gosterYildizli();
				gosterTaslak();
			}
		});
		setTitle("Mail");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 650, 386);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtAlici = new JTextField();
		txtAlici.setBounds(167, 54, 219, 19);
		panel.add(txtAlici);
		txtAlici.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Alıcı");
		lblNewLabel.setBounds(95, 57, 45, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Konu");
		lblNewLabel_1.setBounds(95, 104, 45, 13);
		panel.add(lblNewLabel_1);
		
		txtKonu = new JTextField();
		txtKonu.setBounds(167, 101, 219, 19);
		panel.add(txtKonu);
		txtKonu.setColumns(10);
		
		 txtIcerik = new JTextArea();
		txtIcerik.setBounds(75, 182, 346, 155);
		panel.add(txtIcerik);
	}
	
	public void Db() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mail", "postgres", "1234");

			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void goster() {
		 try {
	        Db();
	        
	            String sql = "SELECT  gonderen, konu, icerik \r\n"
	            		+ "	FROM public.\"gelen_Kutu\" Where konu='"+mails.getMail_konu()+"';";
	            rs = stmt.executeQuery(sql);
	            while(rs.next()) {
	               
	                String alici = rs.getString("gonderen");
	                String konu = rs.getString("konu");
	                String icerik = rs.getString("icerik");

	                txtAlici.setText(alici);
	                txtKonu.setText(konu);
	                txtIcerik.setText(icerik);
	            }
	        } catch (Exception e2) {
	            JOptionPane.showMessageDialog(null, e2.getMessage());
	        }
	    }
	
	//-----------------------------------------------------------------------
	
	public void gosterGonderilenler() {
		 try {
	        Db();
	        
	            String sql = "SELECT  kime, konu, icerik \r\n"
	            		+ "	FROM public.gonderilenler where konu='"+mails.getMail_konu()+"';";
	            rs = stmt.executeQuery(sql);
	            while(rs.next()) {
	               
	                String alici = rs.getString("kime");
	                String konu = rs.getString("konu");
	                String icerik = rs.getString("icerik");

	                txtAlici.setText(alici);
	                txtKonu.setText(konu);
	                txtIcerik.setText(icerik);
	            }
	        } catch (Exception e2) {
	            JOptionPane.showMessageDialog(null, e2.getMessage());
	        }
	    }
	
	//-----------------------------------------------------------------------
	
	public void gosterYildizli() {
		 try {
	        Db();
	        
	            String sql = "SELECT   kimden, konu, icerik \r\n"
	            		+ "	FROM public.yildizli where konu='"+mails.getMail_konu()+"';";
	            rs = stmt.executeQuery(sql);
	            while(rs.next()) {
	               
	                String alici = rs.getString("kimden");
	                String konu = rs.getString("konu");
	                String icerik = rs.getString("icerik");

	                txtAlici.setText(alici);
	                txtKonu.setText(konu);
	                txtIcerik.setText(icerik);
	            }
	        } catch (Exception e2) {
	            JOptionPane.showMessageDialog(null, e2.getMessage());
	        }
	    }
	//------------------------------------------------------------------
	
	public void gosterCop() {
		 try {
	        Db();
	        
	            String sql = "SELECT  kimden, konu, icerik \r\n"
	            		+ "	FROM public.silinen where konu='"+mails.getMail_konu()+"';";
	            rs = stmt.executeQuery(sql);
	            while(rs.next()) {
	               
	                String alici = rs.getString("kimden");
	                String konu = rs.getString("konu");
	                String icerik = rs.getString("icerik");

	                txtAlici.setText(alici);
	                txtKonu.setText(konu);
	                txtIcerik.setText(icerik);
	            }
	        } catch (Exception e2) {
	            JOptionPane.showMessageDialog(null, e2.getMessage());
	        }
	    }
	
	//-------------------------------------------------------------------------------
	
	
	public void gosterSpam() {
		 try {
	        Db();
	        
	            String sql = "SELECT kimden, konu, icerik \r\n"
	            		+ "	FROM public.spam where konu='"+mails.getMail_konu()+"';";
	            rs = stmt.executeQuery(sql);
	            while(rs.next()) {
	               
	                String alici = rs.getString("kimden");
	                String konu = rs.getString("konu");
	                String icerik = rs.getString("icerik");

	                txtAlici.setText(alici);
	                txtKonu.setText(konu);
	                txtIcerik.setText(icerik);
	            }
	        } catch (Exception e2) {
	            JOptionPane.showMessageDialog(null, e2.getMessage());
	        }
	    }
	
	
	//-------------------------------------------------------------------------------------------------
	public void gosterTaslak() {
		 try {
	        Db();
	        
	            String sql = "SELECT   kime, konu, icerik \r\n"
	            		+ "	FROM public.taslaklar where konu='"+mails.getMail_konu()+"';";
	            rs = stmt.executeQuery(sql);
	            while(rs.next()) {
	               
	                String alici = rs.getString("kime");
	                String konu = rs.getString("konu");
	                String icerik = rs.getString("icerik");

	                txtAlici.setText(alici);
	                txtKonu.setText(konu);
	                txtIcerik.setText(icerik);
	            }
	        } catch (Exception e2) {
	            JOptionPane.showMessageDialog(null, e2.getMessage());
	        }
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
