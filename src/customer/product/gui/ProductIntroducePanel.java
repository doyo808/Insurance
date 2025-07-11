package customer.product.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import common.database.dao.ProductDAO;
import common.database.model.ProductModel;
import common.method.InsuranceTeamConnector;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Color;

public class ProductIntroducePanel extends JPanel {

    private JPanel parentCardPanel;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ProductIntroducePanel(JPanel parentCardPanel) {
		setBackground(new Color(192, 192, 192));
		setAutoscrolls(true);
		setBounds(new Rectangle(0, 0, 1440, 700));
		this.parentCardPanel = parentCardPanel;
		
		setLayout(new BorderLayout());
		Panel inHeaderPanel = new Panel();
		Panel product_table = new Panel();
		
		add(inHeaderPanel, BorderLayout.NORTH);
		add(product_table, BorderLayout.CENTER);
		inHeaderPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		inHeaderPanel.add(panel, BorderLayout.NORTH);
		
		JLabel IntroLabel = new JLabel("상품소개");
		IntroLabel.setLabelFor(this);
		IntroLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		IntroLabel.setDoubleBuffered(true);
		panel.add(IntroLabel);
		
		JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
		inHeaderPanel.add(searchBar);
		
		JTextField searchTextField = new JTextField();
		searchBar.add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton searchBtn = new JButton("검색");
		searchBar.add(searchBtn);
		
		
		JTable table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setShowVerticalLines(false);
		table.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 20));
		
		
		//#############################------------------------------------------------------
		
		//상품 정보를 DB에서 담아서 오는 코드
		
		Object[][] models = null;
		ArrayList<ProductModel> products = null;
		
		try (Connection conn = InsuranceTeamConnector.getConnection()){
			products = (ArrayList<ProductModel>)ProductDAO.getAllProducts(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int productSize = products.size();
		
		if(productSize != 0) {
			models = new Object[productSize][5];
			for (int i = 0; i < productSize; i++) {
				models[i][0] = products.get(i).getProductId();
				models[i][1] = products.get(i).getDivision();
				models[i][2] = products.get(i).getProductName();
				models[i][3] = products.get(i).getTermAndConditionsPath();
				models[i][4] = products.get(i).getProductManualPath();
			}
		} else {
			models = new Object[][] {};
		}
		
		//################################----------------------------------------------------
		
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
		scrollpane.setAutoscrolls(true);
		scrollpane.setPreferredSize(new Dimension(800, 600));
		scrollpane.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		table.setDefaultEditor(String.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		product_table.add(scrollpane);
		
	}
}
