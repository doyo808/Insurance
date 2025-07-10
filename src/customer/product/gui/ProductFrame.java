package customer.product.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Panel;
import javax.swing.table.DefaultTableModel;

import common.database.dao.ProductDAO;
import common.database.model.ProductModel;
import common.method.InsuranceTeamConnector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductFrame extends JFrame {

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
					ProductFrame frame = new ProductFrame();
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
	public ProductFrame() {
		setBounds(new Rectangle(5, 5, 1440, 1024));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel header_panel = new JPanel();
		header_panel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
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
		
		JButton btnNewButton_4 = new JButton("검색");
		panel.add(btnNewButton_4);
		
		Panel panel_1 = new Panel();
		body_panel.add(panel_1, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setShowVerticalLines(false);
		table.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 20));
		
		
		//------------------------------------------------------
		
		//상품 정보를 DB에서 담아서 오는 코드
		
		Object[][] models = null;
		ArrayList<ProductModel> pro = null;
		
		try (Connection conn = InsuranceTeamConnector.getConnection()){
			pro = (ArrayList<ProductModel>)ProductDAO.getAllProducts(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		models = new Object[pro.size()][5];

		for (int i = 0, size = pro.size(); i < size; i++) {
			models[i][0] = pro.get(i).getProductId();
			models[i][1] = pro.get(i).getDivision();
			models[i][2] = pro.get(i).getProductName();
			models[i][3] = pro.get(i).getTermAndConditionsPath();
			models[i][4] = pro.get(i).getProductManualPath();
		}
		
		//----------------------------------------------------
		table.setModel(new DefaultTableModel(
			models,
			new String[] {
				"\uBC88\uD638", "\uBD84\uB958", "\uC0C1\uD488\uBA85", "\uC57D\uAD00", "\uC0C1\uD488\uC124\uBA85\uC11C"
			}
		){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int col, int row) {
				return false;
			}
		});
		table.setRowHeight(30);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(800, 600));
		scrollpane.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		table.setDefaultEditor(String.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		panel_1.add(scrollpane);
		

	}

}
