package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 31, 805, 442);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Oluştur", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Yıldızlı", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Çöp Kutusu", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Spam", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Ayarlar", null, panel_4, null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Gelen Kutusu", null, panel_5, null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.activeCaption);
		tabbedPane.addTab("Dashboard", null, panel_6, null);
		panel_6.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 128, 192));
		panel_7.setBounds(31, 59, 127, 108);
		panel_6.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Taslaklar");
		lblNewLabel.setBounds(10, 10, 107, 13);
		panel_7.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setText("34");
		textField.setBounds(10, 49, 96, 19);
		panel_7.add(textField);
		textField.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 128, 128));
		panel_8.setBounds(217, 59, 116, 108);
		panel_6.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("İletiler");
		lblNewLabel_1.setBounds(10, 10, 85, 13);
		panel_8.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setText("5.256");
		textField_1.setBounds(10, 54, 96, 19);
		panel_8.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(0, 128, 192));
		panel_9.setBounds(408, 59, 116, 108);
		panel_6.add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Yüklemeler");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 10, 86, 13);
		panel_9.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setText("2.256\r\n");
		textField_2.setBounds(10, 56, 96, 19);
		panel_9.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(101, 71, 120));
		panel_10.setBounds(588, 59, 116, 108);
		panel_6.add(panel_10);
		panel_10.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Silinenler");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(0, 10, 45, 13);
		panel_10.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setText("256");
		textField_3.setBounds(10, 57, 96, 19);
		panel_10.add(textField_3);
		textField_3.setColumns(10);
		
		JPanel panel_7_1 = new JPanel();
		panel_7_1.setLayout(null);
		panel_7_1.setBackground(new Color(128, 128, 192));
		panel_7_1.setBounds(31, 217, 127, 108);
		panel_6.add(panel_7_1);
		
		JLabel lblSpam = new JLabel("Spam");
		lblSpam.setBounds(10, 10, 107, 13);
		panel_7_1.add(lblSpam);
		
		textField_4 = new JTextField();
		textField_4.setText("528");
		textField_4.setColumns(10);
		textField_4.setBounds(10, 49, 96, 19);
		panel_7_1.add(textField_4);
	}
}
