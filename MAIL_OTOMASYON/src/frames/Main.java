package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Class.Mails;
import Class.Users;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTable tableGelen;
	private JTextField txtkonu;
	private JTextField txtkime;
	private JTable table_1;
	private JTable table_yildiz;
	private JTable table_silinen;
	private JTable table_spam;
	private JTextField txtAd;
	private JTextField txtSoyad;
	private JTextField txtSoyad1;
	private JTextField txtKulAdi;
	private JTextField txtsifre;
	private JTextField txtTelefon;
	private JTextArea txtIcerik;
	private Connection con = null;
	private java.sql.Statement stmt = null;
	private ResultSet rs = null;
	private Users user;
	private Mails mails;
	private PreparedStatement pstmt = null;
	private JTable table_gonderilen;
	private JTable table_Taslak;
	private JTextField txtAd1;
	private JTextField txtSifre1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				gelenler();
				silTable();
				yildiz();
				taslaklar();
				spamgetir();
				ayarlar();
				gonderilenler();

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 800, 539);
		contentPane.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Oluştur", null, panel_1, null);
		panel_1.setLayout(null);

		txtIcerik = new JTextArea();
		txtIcerik.setBounds(10, 103, 775, 268);
		panel_1.add(txtIcerik);

		txtkonu = new JTextField();
		txtkonu.setBounds(131, 66, 654, 19);
		panel_1.add(txtkonu);
		txtkonu.setColumns(10);

		JLabel lblNewLabel = new JLabel("Konu");
		lblNewLabel.setBounds(51, 69, 45, 13);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Kime");
		lblNewLabel_1.setBounds(51, 28, 45, 13);
		panel_1.add(lblNewLabel_1);

		txtkime = new JTextField();
		txtkime.setBounds(131, 25, 654, 19);
		panel_1.add(txtkime);
		txtkime.setColumns(10);

		JButton btnGonder = new JButton("Gönder");
		btnGonder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				olustur();
				DefaultTableModel dtm = (DefaultTableModel) table_gonderilen.getModel();
				table_gonderilen.repaint();
				gonderilenler();
	

			}
		});
		btnGonder.setBounds(505, 400, 85, 21);
		panel_1.add(btnGonder);

		JButton btnTaslak = new JButton("Taslak");
		btnTaslak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taslakKaydet();
				DefaultTableModel dtm = (DefaultTableModel) table_Taslak.getModel();
				table_Taslak.repaint();
				taslaklar();
			}
		});
		btnTaslak.setBounds(641, 400, 85, 21);
		panel_1.add(btnTaslak);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Gelen Kutusu", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 75, 670, 297);
		panel.add(scrollPane);

		tableGelen = new JTable();
		tableGelen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) tableGelen.getModel();
				 String konu = (String) dtm.getValueAt(tableGelen.getSelectedRow(), 1);
	                mails.setMail_Konu(konu);
	                try {
	                	Db();
	                    Mail mvf = new Mail();
	                    mvf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	                    mvf.setVisible(true);
	                   
	                } catch (Exception e2) {
	                    System.out.println(e2.getMessage());
	                }
	            }
			
		});
		tableGelen
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "G\u00F6nderen", "Konu", "Tarih" }));
		tableGelen.getColumnModel().getColumn(0).setMinWidth(12);
		scrollPane.setViewportView(tableGelen);

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) tableGelen.getModel();
				String konu = (String) dtm.getValueAt(tableGelen.getSelectedRow(), 1);
				silInsert(konu);
				table_silinen.repaint();
				gelenler();
				

			}
		});
		btnSil.setBounds(173, 427, 78, 21);
		panel.add(btnSil);

		JButton btnYildiz = new JButton("Yıldızla");
		btnYildiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yildizla();
			}
		});
		btnYildiz.setBounds(331, 427, 74, 21);
		panel.add(btnYildiz);

		JButton btnSpam = new JButton("Spam");
		btnSpam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spamla();
				DefaultTableModel dtm=(DefaultTableModel) table_spam.getModel();
				table_spam.repaint();
				spamgetir();
			}
		});
		btnSpam.setBounds(478, 427, 85, 21);
		panel.add(btnSpam);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Gönderilenler", null, panel_4, null);
		panel_4.setLayout(null);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(SystemColor.activeCaption);
		panel_7.setBounds(0, 0, 795, 512);
		panel_4.add(panel_7);
		panel_7.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(28, 75, 670, 297);
		panel_7.add(scrollPane_1);

		table_gonderilen = new JTable();
		table_gonderilen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table_gonderilen.getModel();
				 String konu = (String) dtm.getValueAt(table_gonderilen.getSelectedRow(), 1);
	                mails.setMail_Konu(konu);
	                try {
	                	Db();
	                    Mail mvf = new Mail();
	                    mvf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	                    mvf.setVisible(true);
	                   
	                } catch (Exception e2) {
	                    System.out.println(e2.getMessage());
	                }
			}
		});
		table_gonderilen.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Kime", "Konu", "Tarih" }));
		scrollPane_1.setViewportView(table_gonderilen);

		JButton btnSil2 = new JButton("Sil");
		btnSil2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table_gonderilen.getModel();
				String konu = (String) dtm.getValueAt(table_gonderilen.getSelectedRow(), 1);
				try {
					Db();
					String sql = "DELETE FROM public.gonderilenler\r\n" + "	WHERE konu='" + konu + "';";
					pstmt = con.prepareStatement(sql);
					int returnedValue = pstmt.executeUpdate();

					if (returnedValue > 0) {
						JOptionPane.showMessageDialog(null, "Mail Has Been Deleted");
						table_gonderilen.validate();
						if (table_gonderilen.getSelectedRow() != -1) {
							dtm.removeRow(table_gonderilen.getSelectedRow());
						}
					} else
						JOptionPane.showMessageDialog(null, "Hata oluştu!");
					;

				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}

			}
		});
		btnSil2.setBounds(302, 428, 85, 21);
		panel_7.add(btnSil2);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Yıldızlı", null, panel_2, null);
		panel_2.setLayout(null);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(30, 52, 684, 327);
		panel_2.add(scrollPane_1_1);

		table_yildiz = new JTable();
		table_yildiz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table_yildiz.getModel();
				 String konu = (String) dtm.getValueAt(table_yildiz.getSelectedRow(), 1);
	                mails.setMail_Konu(konu);
	                try {
	                	Db();
	                    Mail mvf = new Mail();
	                    mvf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	                    mvf.setVisible(true);
	                   
	                } catch (Exception e2) {
	                    System.out.println(e2.getMessage());
	                }
			}
		});
		table_yildiz.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Kimden", "Konu", "Saat" }));
		scrollPane_1_1.setViewportView(table_yildiz);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Çöp Kutusu", null, panel_3, null);
		panel_3.setLayout(null);

		JScrollPane scrollPane_1_1_1 = new JScrollPane();
		scrollPane_1_1_1.setBounds(51, 49, 684, 327);
		panel_3.add(scrollPane_1_1_1);

		table_silinen = new JTable();
		table_silinen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table_silinen.getModel();
				 String konu = (String) dtm.getValueAt(table_silinen.getSelectedRow(), 1);
	                mails.setMail_Konu(konu);
	                try {
	                	Db();
	                    Mail mvf = new Mail();
	                    mvf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	                    mvf.setVisible(true);
	                   
	                } catch (Exception e2) {
	                    System.out.println(e2.getMessage());
	                }
			}
		});
		table_silinen.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Kimden", "Konu", "Saat" }));
		scrollPane_1_1_1.setViewportView(table_silinen);

		JButton btnSilTable = new JButton("Sil\r\n");
		btnSilTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cop();

			}
		});
		btnSilTable.setBounds(337, 415, 85, 21);
		panel_3.add(btnSilTable);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Spam", null, panel_6, null);
		panel_6.setLayout(null);

		JScrollPane scrollPane_1_1_2 = new JScrollPane();
		scrollPane_1_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table_spam.getModel();
				 String konu = (String) dtm.getValueAt(table_spam.getSelectedRow(), 1);
	                mails.setMail_Konu(konu);
	                try {
	                	Db();
	                    Mail mvf = new Mail();
	                    mvf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	                    mvf.setVisible(true);
	                   
	                } catch (Exception e2) {
	                    System.out.println(e2.getMessage());
	                }
			}
		});
		scrollPane_1_1_2.setBounds(28, 75, 670, 297);
		panel_6.add(scrollPane_1_1_2);

		table_spam = new JTable();
		table_spam.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table_spam.getModel();
				 String konu = (String) dtm.getValueAt(table_spam.getSelectedRow(), 1);
	                mails.setMail_Konu(konu);
	                try {
	                	Db();
	                    Mail mvf = new Mail();
	                    mvf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	                    mvf.setVisible(true);
	                   
	                } catch (Exception e2) {
	                    System.out.println(e2.getMessage());
	                }
			}
		});
		table_spam.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Kimden", "Konu", "Saat" }));
		scrollPane_1_1_2.setViewportView(table_spam);

		JButton btnSil3 = new JButton("Sil");
		btnSil3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table_spam.getModel();
				String getsil = (String) dtm.getValueAt(table_spam.getSelectedRow(), 1);
				spamSil(getsil);

			}
		});
		btnSil3.setBounds(217, 430, 85, 21);
		panel_6.add(btnSil3);

		JButton btnCikar = new JButton("Çıkar");
		btnCikar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spamCikar();
				DefaultTableModel dtm=(DefaultTableModel) tableGelen.getModel();
				tableGelen.repaint();
			}
		});
		btnCikar.setBounds(392, 430, 85, 21);
		panel_6.add(btnCikar);

		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Taslaklar", null, panel_8, null);
		panel_8.setLayout(null);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(SystemColor.activeCaption);
		panel_9.setBounds(0, 0, 795, 512);
		panel_8.add(panel_9);
		panel_9.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(49, 71, 670, 293);
		panel_9.add(scrollPane_2);

		table_Taslak = new JTable();
		table_Taslak.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) table_Taslak.getModel();
				 String konu = (String) dtm.getValueAt(table_Taslak.getSelectedRow(), 1);
	                mails.setMail_Konu(konu);
	                try {
	                	Db();
	                    Mail mvf = new Mail();
	                    mvf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	                    mvf.setVisible(true);
	                   
	                } catch (Exception e2) {
	                    System.out.println(e2.getMessage());
	                }
			}
		});
		table_Taslak.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Kime", "Konu", "Tarih" }));
		scrollPane_2.setViewportView(table_Taslak);

		JButton btngonder = new JButton("Gönder");
		btngonder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taslakGonder();
				DefaultTableModel tbl = (DefaultTableModel) table_Taslak.getModel();
				table_Taslak.repaint();
				
				
			}
		});
		btngonder.setBounds(127, 424, 85, 21);
		panel_9.add(btngonder);

		JButton btnsil = new JButton("Sil");
		btnsil.setBounds(293, 424, 85, 21);
		panel_9.add(btnsil);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Ayarlar", null, panel_5, null);
		panel_5.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Ad");
		lblNewLabel_2.setBounds(173, 163, 45, 13);
		panel_5.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Soyad");
		lblNewLabel_3.setBounds(405, 163, 45, 13);
		panel_5.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Kullanıcı Adı");
		lblNewLabel_4.setBounds(152, 214, 89, 13);
		panel_5.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Şifre");
		lblNewLabel_5.setBounds(159, 255, 45, 13);
		panel_5.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Telefon");
		lblNewLabel_6.setBounds(159, 301, 45, 13);
		panel_5.add(lblNewLabel_6);

		txtAd = new JTextField();
		txtAd.setBounds(253, 160, 132, 19);
		panel_5.add(txtAd);
		txtAd.setColumns(10);

		txtSoyad1 = new JTextField();
		txtSoyad1.setBounds(460, 160, 116, 19);
		panel_5.add(txtSoyad1);
		txtSoyad1.setColumns(10);

		txtKulAdi = new JTextField();
		txtKulAdi.setBounds(253, 211, 323, 19);
		panel_5.add(txtKulAdi);
		txtKulAdi.setColumns(10);

		txtsifre = new JTextField();
		txtsifre.setBounds(253, 252, 323, 19);
		panel_5.add(txtsifre);
		txtsifre.setColumns(10);

		txtTelefon = new JTextField();
		txtTelefon.setBounds(253, 298, 323, 19);
		panel_5.add(txtTelefon);
		txtTelefon.setColumns(10);

		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnKaydet.setBounds(183, 428, 101, 21);
		panel_5.add(btnKaydet);

		JButton btnCikis = new JButton("Çıkış Yap");
		btnCikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LogIn login = new LogIn();
				login.setVisible(true);
			}
		});
		btnCikis.setBounds(341, 428, 109, 21);
		panel_5.add(btnCikis);

		JButton btnHesapSil = new JButton("Hesabı Sil");
		btnHesapSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user=new Users();
				try{
					Db();
					
					String sql="DELETE FROM public.users\r\n"
							+ "	WHERE mail='"+user.getKulAd()+"';";
					int rs=stmt.executeUpdate(sql);
					if(rs>0) {
						 int cevap = JOptionPane.showConfirmDialog(null, "Hesabınızı silmek istediğinize emin misiniz?", "Evet", JOptionPane.YES_NO_OPTION);

					        if (cevap == JOptionPane.YES_OPTION) {
					            System.out.println("Evet seçildi.");
					            dispose();
					            SignUp sign=new SignUp();
					            sign.setVisible(true);
					        } else if (cevap == JOptionPane.NO_OPTION) {
					            System.out.println("Hayır seçildi.");
					        } else if (cevap == JOptionPane.CLOSED_OPTION) {
					            System.out.println("Pencere kapatıldı.");
					        }
					}
				}catch(Exception e1){
					System.out.println(e1.getMessage());
				}
			}
		});
		btnHesapSil.setBounds(495, 428, 109, 21);
		panel_5.add(btnHesapSil);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\birsu\\Downloads\\user-gear (2).png"));
		lblNewLabel_7.setBounds(374, 10, 82, 139);
		panel_5.add(lblNewLabel_7);

		txtAd1 = new JTextField();
		txtSoyad = new JTextField();

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

//--------------------------Mail Olustur-------------------------------------
	public void olustur() {

		String kime = txtkime.getText();
		user = new Users();
		String konu = txtkonu.getText();
		String yazi = txtIcerik.getText();

		try {
			Db();
			String sql = "INSERT INTO public.gonderilenler(\r\n id, kime, konu, icerik, tarih, kimden)\r\n"
					+ " VALUES (nextval('m_gonderilen'), '" + kime + "', '" + konu + "','" + yazi + "', '"
					+ LocalDate.now() + "','" + user.getKulAd() + "');";

			int inserted = stmt.executeUpdate(sql);

			if (inserted > 0) {

				JOptionPane.showMessageDialog(null, "Başarılı", "Detay",
						JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "hata", "Message",
						JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	// -------------------------------Bitis-----------------------

	// ---------------------------Gelen Mailler---------------------------

	public void gelenler() {

		try {
			Db();

			String sql = "SELECT id, gonderen, konu, icerik, (SELECT tarih FROM gonderilenler g WHERE g.id = gk.id  ) as Tarih  FROM public.\"gelen_Kutu\" gk;";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String gonderen = rs.getString("gonderen");
				String konu = rs.getString("konu");
				String tarih = rs.getString("Tarih");
				DefaultTableModel tbl = (DefaultTableModel) tableGelen.getModel();

				tbl.addRow(new Object[] { gonderen, konu, tarih });

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
//---------------------------------------------------------------------------

	// --------------------------Mail Yıldızla------------------------------------------

	public void yildizla() {

		DefaultTableModel tbl = (DefaultTableModel) tableGelen.getModel();
		user = new Users();
		String gonderen = (String) tbl.getValueAt(tableGelen.getSelectedRow(), 0);
		String konu = (String) tbl.getValueAt(tableGelen.getSelectedRow(), 1);
		String tarih = (String) tbl.getValueAt(tableGelen.getSelectedRow(), 2);
		mails.getMail_konu();

		try {
			Db();
			String sql = "INSERT INTO public.yildizli(\r\n" + "	id, kime, kimden, konu, icerik, saat)\r\n"
					+ "	VALUES (nextval('m_yildizla'), '" + user.getKulAd() + "', '" + gonderen + "', '" + konu + "',"
					+ " (SELECT  icerik FROM public.\"gelen_Kutu\" where konu='" + konu + "' ), '" + tarih + "');";
			int a = stmt.executeUpdate(sql);
			if (a > 0) {
				JOptionPane.showMessageDialog(null, "Kayıt başarılı!");

			} else {
				JOptionPane.showMessageDialog(null, "Kayıt başarısız!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// -----------------------------------------------------------------------

	// ----------------------------Gelen-Mail --> Silinen tablosuna İnsert et--------------------------------------------

	public void silInsert(String mail_Konu) {
		DefaultTableModel tbl = (DefaultTableModel) tableGelen.getModel();

		String gonderen = (String) tbl.getValueAt(tableGelen.getSelectedRow(), 0);
		String konu = (String) tbl.getValueAt(tableGelen.getSelectedRow(), 1);
		String tarih = (String) tbl.getValueAt(tableGelen.getSelectedRow(), 2);

		try {
			Db();
			String sql = "INSERT INTO public.silinen(\r\n" 
			        + "	id, kimden, konu, icerik, tarih)\r\n"
					+ "	VALUES (nextval('m_silinen'), '" + gonderen + "', '" + konu + "',"
					+ " (SELECT  icerik FROM public.\"gelen_Kutu\" where konu='" + konu + "' ), '" + tarih + "');";

			int a = stmt.executeUpdate(sql);

			if (a > 0) {
				JOptionPane.showMessageDialog(null, "Çöp kutusunda!");

				
				if (tableGelen.getSelectedRow() != -1) {

					tbl.removeRow(tableGelen.getSelectedRow());

				}

			} else {
				JOptionPane.showMessageDialog(null, "Başarısız!");
			}
			DefaultTableModel tbl2 = (DefaultTableModel) table_silinen.getModel();
			tbl2.setRowCount(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// ---------------------------------------------------------------

	// --------------------------Silinenler tablosuna getir---------------------------

	public void silTable() {
		try {
			Db();
			String sql2 = "SELECT  kimden, konu, tarih\r\n" + "	FROM public.silinen;";
			rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				String gonderen1 = rs.getString("kimden");
				String konu1 = rs.getString("konu");
				String tarih1 = rs.getString("tarih");
				DefaultTableModel tbl1 = (DefaultTableModel) table_silinen.getModel();
				tbl1.addRow(new Object[] { gonderen1, konu1, tarih1 });

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	// -----------------------------------------------------------------------------

	// -------------------Silinenleri kalıcı olaraksil---------------------------------------

	public void silTablebtn(String mail_Konu) {

		
		try {
			Db();
			String sql = "DELETE FROM public.silinen Where konu = '" + mail_Konu + "'";
			pstmt = con.prepareStatement(sql);
			int returnedValue = pstmt.executeUpdate();
			if (returnedValue > 0) {
				JOptionPane.showMessageDialog(null, "Başarılı!");
				DefaultTableModel dtm = (DefaultTableModel) table_silinen.getModel();
				;
			} else
				JOptionPane.showMessageDialog(null, "Hata", "Detay", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}
	// ------------------------------------------------------------------------------------------

	// ------------------------------------------gelenler tablsounda silinen
	// mailleri db den sil-----------------------------
	public void gelenSil(String mail_Konu) {
	

		try {
			Db();
			String sql = "DELETE FROM public.\"gelen_Kutu\" Where konu = '" + mail_Konu + "'";
			pstmt = con.prepareStatement(sql);
			int returnedValue = pstmt.executeUpdate();
			if (returnedValue > 0) {
				JOptionPane.showMessageDialog(null, "Başarılı");
				
			} else
				JOptionPane.showMessageDialog(null, "Hata", "DEtay", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	// --------------------------------------------------------------------------

	// ----------------------------Silinen maillerin
	// kayıtları-----------------------------------

	public void cop() {

		try {
			Db();
			user = new Users();
			DefaultTableModel tbl = (DefaultTableModel) table_silinen.getModel();

			String gonderen = (String) tbl.getValueAt(table_silinen.getSelectedRow(), 0);
			String konu = (String) tbl.getValueAt(table_silinen.getSelectedRow(), 1);
			String tarih = (String) tbl.getValueAt(table_silinen.getSelectedRow(), 2);
			String sql2 = "INSERT INTO public.cop(\r\n" + "	id, kime, kimden, konu, icerik, tarih,silinmetarih)\r\n"
					+ "	VALUES (nextval('m_cop'),'" + user.getKulAd() + "' ,'" + gonderen + "', '" + konu + "',"
					+ " (SELECT  icerik FROM public.silinen where konu='" + konu + "' ),'" + tarih + "', '"
					+ LocalDate.now() + "');";
			int a = stmt.executeUpdate(sql2);

			if (a > 0) {
				JOptionPane.showMessageDialog(null, "Kalıcı olarak silindi!");

				table_silinen.repaint();
				if (table_silinen.getSelectedRow() != -1) {
					// remove selected row from the model
					tbl.removeRow(table_silinen.getSelectedRow());
				}
			}
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
	}

	// ----------------------------Yildizlananlar Sayfası-----------------------------------------
	public void yildiz() {
		try {
			Db();
			String sql = "SELECT  kimden, konu,  saat\r\n" + "	FROM public.yildizli;";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String gonderen1 = rs.getString("kimden");
				String konu1 = rs.getString("konu");
				String tarih1 = rs.getString("saat");
				DefaultTableModel tbl1 = (DefaultTableModel) table_yildiz.getModel();
				tbl1.addRow(new Object[] { gonderen1, konu1, tarih1 });

			}
		} catch (Exception e) {

		}
	}

	// -------------------------------------------------------------------------------------
	public void taslakKaydet() {
	
		DefaultTableModel tbl = (DefaultTableModel) table_Taslak.getModel();
		String kime = txtkime.getText();
		user = new Users();
		String konu = txtkonu.getText();
		String yazi = txtIcerik.getText();

		try {
			Db();
			String sql = "INSERT INTO public.taslaklar(\r\n"
					+ "	id, kime, kimden, konu, icerik, tarih)\r\n"
					+ "	VALUES (nextval('m_taslak'), '" + kime + "','"+user.getKulAd()+"', '" + konu + "' , '"+yazi+"', '" + LocalDate.now() + "');"; 

			int a = stmt.executeUpdate(sql);

			if (a > 0) {
				JOptionPane.showMessageDialog(null, "Taslaklarda!");

				table_Taslak.validate();
				if (table_Taslak.getSelectedRow() != -1) {
					// remove selected row from the model
					tbl.removeRow(table_Taslak.getSelectedRow());
				}

			} else {
				JOptionPane.showMessageDialog(null, "Başarısız!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// ---------------------------------------------------------------------------

	public void taslaklar() {
		try {
			Db();
			String sql1 = "SELECT  kime, konu, tarih FROM public.taslaklar ;";
			rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				String gonderen1 = rs.getString("kime");
				String konu1 = rs.getString("konu");
				String tarih1 = rs.getString("tarih");
				DefaultTableModel tbl1 = (DefaultTableModel) table_Taslak.getModel();
				tbl1.addRow(new Object[] { gonderen1, konu1, tarih1 });
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	public void taslakGonder() {
		try {
			Db();
			
			DefaultTableModel tbl = (DefaultTableModel) tableGelen.getModel();

			String kime = (String) tbl.getValueAt(table_Taslak.getSelectedRow(), 0);
			String konu = (String) tbl.getValueAt(table_Taslak.getSelectedRow(), 1);
			String tarih = (String) tbl.getValueAt(table_Taslak.getSelectedRow(), 2);
			String sql2 = "INSERT INTO public.gonderilenler(id, kime, konu, icerik, tarih, kimden)\r\n"
					+ "	VALUES (nextval('m_gonderilen'),'" + kime + "', '" + konu + "',"
					+ " (SELECT  icerik FROM public.taslaklar where konu='" + konu + "') ,'" + LocalDate.now() + "','"+user.getKulAd()+"');";
			int a = stmt.executeUpdate(sql2);

			if (a > 0) {
				JOptionPane.showMessageDialog(null, "Taslak gönderildi!");

				if (tableGelen.getSelectedRow() != -1) {

					tbl.removeRow(tableGelen.getSelectedRow());
				}
			}
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}	}
	
	
	public void taslakSil(String mail_Konu) {
		
		
		try {
			Db();
			String sql = "DELETE FROM public.taslaklar Where konu = '" + mail_Konu + "'";
			pstmt = con.prepareStatement(sql);
			int returnedValue = pstmt.executeUpdate();
			if (returnedValue > 0) {
				JOptionPane.showMessageDialog(null, "Mail gönderildi!");

				if (table_spam.getSelectedRow() != -1) {
					DefaultTableModel tbl = (DefaultTableModel) table_Taslak.getModel();
					tbl.removeRow(table_Taslak.getSelectedRow());
				}
			} else
				JOptionPane.showMessageDialog(null, "Hata oluştu!", "Detay", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	// ----------------------------------------------------------------------------------

	public void spamla() {
		try {
			Db();
			user = new Users();
			DefaultTableModel tbl = (DefaultTableModel) tableGelen.getModel();

			String kimden = (String) tbl.getValueAt(tableGelen.getSelectedRow(), 0);
			String konu = (String) tbl.getValueAt(tableGelen.getSelectedRow(), 1);
			String tarih = (String) tbl.getValueAt(tableGelen.getSelectedRow(), 2);
			String sql2 = "INSERT INTO public.spam(\r\n" + "	id, kimden, konu, icerik, tarih)\r\n"
					+ "	VALUES (nextval('m_spam'),'" + kimden + "', '" + konu + "',"
					+ " (SELECT  icerik FROM public.\"gelen_Kutu\" where konu='" + konu + "' ),'" + tarih + "');";
			int a = stmt.executeUpdate(sql2);

			if (a > 0) {
				JOptionPane.showMessageDialog(null, "Spam klasörüne kaydedildi!");

				if (tableGelen.getSelectedRow() != -1) {

					tbl.removeRow(tableGelen.getSelectedRow());
				}
			}
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
	}

	public void spamSil(String mail_Konu) {
		
	
		try {
			Db();
			String sql = "DELETE FROM public.spam Where konu = '" + mail_Konu + "'";
			pstmt = con.prepareStatement(sql);
			int returnedValue = pstmt.executeUpdate();
			if (returnedValue > 0) {
				JOptionPane.showMessageDialog(null, "Mail silindi!");

				if (table_spam.getSelectedRow() != -1) {
					DefaultTableModel tbl = (DefaultTableModel) table_spam.getModel();
					tbl.removeRow(table_spam.getSelectedRow());
				}
			} else
				JOptionPane.showMessageDialog(null, "Hata oluştu!", "Detay", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	// ------------------------------------------------------------------------------

	public void spamgetir() {
	
		try {
			Db();
			String sql1 = "SELECT  kimden, konu, tarih \r\n" + "	FROM public.spam;";
			rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				String gonderen1 = rs.getString("kimden");
				String konu1 = rs.getString("konu");
				String tarih1 = rs.getString("tarih");
				DefaultTableModel tbl1 = (DefaultTableModel) table_spam.getModel();
				tbl1.addRow(new Object[] { gonderen1, konu1, tarih1 });
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// ---------------------------------Spam
	// Çıkar-------------------------------------------------

	public void spamCikar() {
		this.revalidate();
		this.repaint();
		try {
			Db();
			user = new Users();
			DefaultTableModel tbl = (DefaultTableModel) table_spam.getModel();

			String kimden = (String) tbl.getValueAt(table_spam.getSelectedRow(), 0);
			String konu = (String) tbl.getValueAt(table_spam.getSelectedRow(), 1);
			String tarih = (String) tbl.getValueAt(table_spam.getSelectedRow(), 2);
			String sql2 = "INSERT INTO public.\"gelen_Kutu\"(\r\n" + "	id, gonderen, konu, icerik, tarih)\r\n"
					+ "	VALUES (nextval('m_gelen'),'" + kimden + "', '" + konu + "',"
					+ " (SELECT  icerik FROM public.spam where konu='" + konu + "' ),'" + tarih + "');";
			int a = stmt.executeUpdate(sql2);

			if (a > 0) {
				JOptionPane.showMessageDialog(null, "Spam klasörüne çıkarıldı!");

				if (table_spam.getSelectedRow() != -1) {

					tbl.removeRow(table_spam.getSelectedRow());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// -------------------------------------------------------------------------------------

	public void ayarlar() {
		this.revalidate();
		this.repaint();
		try {
			Db();
			user = new Users();

			String sql = "SELECT   ad, soyad, mail, telefon, sifre\r\n" + "	FROM public.users where mail='"
					+ user.getKulAd() + "';";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {

				String ad = rs.getString("ad");
				txtAd.setText(ad);

				String soyad = rs.getString("soyad");
				txtSoyad1.setText(soyad);

				String kulAd = rs.getString("mail");
				txtKulAdi.setText(kulAd);

				String telefon = rs.getString("telefon");
				txtTelefon.setText(telefon);

				String sifre = rs.getString("sifre");
				txtsifre.setText(sifre);

			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	// ---------------------------------------------------------------------------------------

	public void update() {

		try {
			Db();
			String sql = "UPDATE public.users\r\n" + "	SET  ad='" + txtAd.getText() + "', soyad='"
					+ txtSoyad1.getText() + "', mail='" + txtKulAdi.getText() + "', telefon='" + txtTelefon.getText()
					+ "', sifre='" + txtsifre.getText() + "'\r\n" + "	WHERE mail='" + user.getKulAd() + "';";
			int rs = stmt.executeUpdate(sql);
			if (rs > 0) {
				JOptionPane.showMessageDialog(null, "Güncelleme başarılı!");
			} else {
				JOptionPane.showMessageDialog(null, "Güncelleme başarısız!");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void gonderilenler() {
		try {
			Db();
			String sql = "SELECT  kime, konu, tarih \r\n" + "	FROM public.gonderilenler;";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String kime = rs.getString("kime");
				String konu = rs.getString("konu");
				String tarih = rs.getString("tarih");
				DefaultTableModel tbl = (DefaultTableModel) table_gonderilen.getModel();

				tbl.addRow(new Object[] { kime, konu, tarih });
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Hata!");
		}
	}
}
