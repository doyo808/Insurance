package insurance.product;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Point;
import java.util.Locale;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Panel;
import java.awt.List;
import javax.swing.table.DefaultTableModel;

public class Product extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product frame = new Product();
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
	public Product() {
		setBounds(new Rectangle(5, 5, 1440, 1024));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel header_panel = new JPanel();
		header_panel.setLocale(Locale.KOREA);
		header_panel.setName("헤더");
		header_panel.setAutoscrolls(true);
		contentPane.add(header_panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("상품 조회 페이지");
		lblNewLabel.setLabelFor(header_panel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
		header_panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("버튼");
		btnNewButton.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
		btnNewButton.setBackground(new Color(0, 0, 64));
		btnNewButton.setForeground(new Color(255, 255, 255));
		header_panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("버튼");
		btnNewButton_1.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
		btnNewButton_1.setBackground(new Color(0, 0, 64));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		header_panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("버튼");
		btnNewButton_2.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
		btnNewButton_2.setBackground(new Color(0, 0, 64));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		header_panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("버튼");
		btnNewButton_3.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
		btnNewButton_3.setBackground(new Color(0, 0, 64));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		header_panel.add(btnNewButton_3);
		
		JPanel body_panel = new JPanel();
		contentPane.add(body_panel, BorderLayout.CENTER);
		body_panel.setLayout(new BorderLayout(0, 0));
		
		Panel panel = new Panel();
		body_panel.add(panel, BorderLayout.NORTH);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("New button");
		panel.add(btnNewButton_4);
		
		Panel panel_1 = new Panel();
		body_panel.add(panel_1, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"번호", "상품명", "약관", "상품설명서"
			}
		));
		panel_1.add(table);
		
		

	}

}
