package customer.product.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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

    private int sharedProductId = 0;

	private MouseClickListener listener;
    
	JPanel inHeaderPanel;
	JPanel product_table;
	
	JPanel panel;
	JLabel IntroLabel;
	
	JPanel searchBar;
	JTextField searchTextField;
	JButton searchBtn;
	
	JTable productInfoTable;
	JScrollPane scrollpane;
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ProductIntroducePanel() {
		setBackground(new Color(192, 192, 192));
		setAutoscrolls(true);
		setBounds(new Rectangle(0, 0, 1440, 700));
		
		setLayout(new BorderLayout());
		inHeaderPanel = new JPanel();
		product_table = new JPanel();
		
		add(inHeaderPanel, BorderLayout.NORTH);
		add(product_table, BorderLayout.CENTER);
		inHeaderPanel.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		inHeaderPanel.add(panel, BorderLayout.NORTH);
		
		IntroLabel = new JLabel("상품소개");
		IntroLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		panel.add(IntroLabel);
		
		searchBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
		inHeaderPanel.add(searchBar);
		
		searchTextField = new JTextField();
		searchTextField.setText("아직 미구현된기능");
		searchBar.add(searchTextField);
		
		searchBtn = new JButton("검색");
		searchBar.add(searchBtn);
		
		
		productInfoTable = new JTable();
		productInfoTable.setShowVerticalLines(false);
		productInfoTable.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 20));
		productInfoTable.setRowHeight(30);
		productInfoTable.getTableHeader().setReorderingAllowed(false);
		productInfoTable.getTableHeader().setResizingAllowed(false);
		
		//#############################------------------------------------------------------
		
		//상품 정보를 DB에서 담아서 오는 코드
		
		Object[][] columns = null;
		ArrayList<ProductModel> products = null;
		
		try (Connection conn = InsuranceTeamConnector.getConnection()){
			products = (ArrayList<ProductModel>)ProductDAO.getAllProducts(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int productSize = products.size();
		
		if(productSize != 0) {
			columns = new Object[productSize][5];
			for (int i = 0; i < productSize; i++) {
				columns[i][0] = products.get(i).getProductId();
				columns[i][1] = products.get(i).getDivision();
				columns[i][2] = products.get(i).getProductName();
				columns[i][3] = products.get(i).getTermAndConditionsPath();
				columns[i][4] = products.get(i).getProductManualPath();
			}
		} else {
			columns = new Object[][] {};
		}
		
		//################################----------------------------------------------------
		
		productInfoTable.setModel(new DefaultTableModel(
			columns,
			new String[] {
				"\uBC88\uD638", "\uBD84\uB958", "\uC0C1\uD488\uBA85", "\uC57D\uAD00", "\uC0C1\uD488\uC124\uBA85\uC11C"
			}
		){
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			// 테이블을 수정할수있는지 여부를 비활성화해두는것
			@Override
			public boolean isCellEditable(int col, int row) {
				return false;
			}
		});
		
		// 테이블의 행마다 더블클릭을 하면 해당 상세페이지로 넘어가는 이벤트
		
		productInfoTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	if(e.getClickCount() == 2) {
            		if (listener != null) {
            			int row = productInfoTable.getSelectedRow(); // 선택된 행 인덱스
            			if (row >= 0) {
            				Object productId = productInfoTable.getValueAt(row, 0);
//            				System.out.println("테스트중 productId = " + productId);
            				sharedProductId = (int)productId;
            			} 
            			listener.onChildPanelClicked(e);  // 부모에게 알림
            		}
            	}
            }
        });
				
		productInfoTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		productInfoTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		productInfoTable.getColumnModel().getColumn(4).setPreferredWidth(80);
		scrollpane = new JScrollPane(productInfoTable);
		scrollpane.setAutoscrolls(true);
		scrollpane.setPreferredSize(new Dimension(1100, 600));
		scrollpane.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		product_table.add(scrollpane);
		
	}
	
	public int getProductId() {
		return sharedProductId;
	}
	
	// 부모가 등록할 리스너
    public void setMouseClickListener(MouseClickListener listener) {
        this.listener = listener;
    }

    // 리스너 인터페이스 정의
    public interface MouseClickListener {
        void onChildPanelClicked(MouseEvent e);
    }
}
