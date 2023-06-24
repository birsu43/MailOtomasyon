package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class example extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					example frame = new example();
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
	public example() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 119, 414);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JMenu mnNewMenu = new JMenu("Oluştur");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		mnNewMenu.setBounds(0, 0, 111, 24);
		panel.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Yıldızlı");
		mnNewMenu_1.setBounds(0, 26, 111, 24);
		panel.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Çöp Kutusu");
		mnNewMenu_2.setBounds(0, 50, 111, 24);
		panel.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Spam");
		mnNewMenu_3.setBounds(0, 76, 111, 24);
		panel.add(mnNewMenu_3);
		
		JMenu mnNewMenu_4 = new JMenu("Ayarlar");
		mnNewMenu_4.setBounds(0, 104, 111, 24);
		panel.add(mnNewMenu_4);
		
		JMenu mnNewMenu_5 = new JMenu("Gelen Kutusu");
		mnNewMenu_5.setBounds(0, 131, 111, 24);
		panel.add(mnNewMenu_5);
		
		JMenu mnNewMenu_6 = new JMenu("Dashboard");
		mnNewMenu_6.setBounds(0, 156, 111, 24);
		panel.add(mnNewMenu_6);
	}

}
