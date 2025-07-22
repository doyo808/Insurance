package customer.product.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import java.awt.Cursor;

public class ProductIntroducePanel extends JPanel {

    private int sharedProductId = 0;
	private MouseClickListener listener;
	private Object[][] tableData;
	final String[] columnNames = {"번호", "분류", "상품명", "약관", "상품설명서"};
	final String[] tagList = {"전체", "건강(종합)", "유병자(간편)", "운전자", "주택화재", "자녀(어린이)", "실손", "재물", "펫/기타"};
	
	JPanel inHeaderPanel;
	JPanel productTablePanel;
	
	JPanel panel;
	JLabel IntroLabel;
	
	JPanel searchBar;
//	JTextField searchTextField;
//	JButton searchBtn;
	JComboBox<String> searchTag;
	
	DefaultTableModel tableModel;
	
	JTable productInfoTable;
	JScrollPane scrollpane;
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ProductIntroducePanel() {
		setBackground(new Color(192, 192, 192));
		setBounds(new Rectangle(0, 0, 1440, 700));
		setLayout(new BorderLayout());
		
		inHeaderPanel = new JPanel(new BorderLayout());
		productTablePanel = new JPanel();
		
		add(inHeaderPanel, BorderLayout.NORTH);
		add(productTablePanel, BorderLayout.CENTER);
		
		
		panel = new JPanel();
		IntroLabel = new JLabel("상품소개");
		IntroLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		
		panel.add(IntroLabel);
		
		searchBar = new JPanel(new FlowLayout(FlowLayout.CENTER));

		inHeaderPanel.add(panel, BorderLayout.NORTH);
		inHeaderPanel.add(searchBar);
		
		
		// 상품명으로 검색바 기능 일단 보류
//		searchTextField = new JTextField(20);
//		searchTextField.setText("아직 미구현된기능");
//		searchBtn = new JButton("검색");
		
//		searchBar.add(searchTextField);
//		searchBar.add(searchBtn);
		
		// 상품태그로 테이블을 분류한다
		searchTag = new JComboBox<String>(tagList);
		searchBar.add(searchTag);
		
		tableModel = new DefaultTableModel(columnNames, 0);
		productInfoTable = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		productInfoTable.setShowVerticalLines(false);
		productInfoTable.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 20));
		productInfoTable.setRowHeight(30);
		productInfoTable.getTableHeader().setReorderingAllowed(false);
		productInfoTable.getTableHeader().setResizingAllowed(false);
		productInfoTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		productInfoTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		scrollpane = new JScrollPane(productInfoTable);
		scrollpane.setAutoscrolls(true);
		scrollpane.setPreferredSize(new Dimension(1100, 600));
		scrollpane.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		
		productTablePanel.add(scrollpane);
		
		setInitTableData();
		
		
		// 서치바의 콤보박스에서 옵션이 선택됐을때 발생하는 이벤트
		searchTag.addActionListener(e -> {
//			System.out.println("이벤트 발생");
			String selected = (String) searchTag.getSelectedItem();
//			System.out.println("선택된 값 : " + selected);
			setTable(1, selected);
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
            				sharedProductId = (int)productId;
            			} 
            			listener.onChildPanelClicked(e);  // 부모에게 알림
            		}
            	}
            }
        });
		
		
		//#############################------------------------------------------------------
		// 옛 코드 (Model없이 그냥 JTable() 생성자를 썻을때)
		//상품 정보를 DB에서 담아서 오는 코드
		
//		Object[][] columns = null;
//		ArrayList<ProductModel> products = null;
//		
//		try (Connection conn = InsuranceTeamConnector.getConnection()){
//			products = (ArrayList<ProductModel>)ProductDAO.getAllProducts(conn);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		int productSize = products.size();
//		
//		if(productSize != 0) {
//			columns = new Object[productSize][5];
//			for (int i = 0; i < productSize; i++) {
//				columns[i][0] = products.get(i).getProductId();
//				columns[i][1] = products.get(i).getDivision();
//				columns[i][2] = products.get(i).getProductName();
//				columns[i][3] = products.get(i).getTermAndConditionsPath();
//				columns[i][4] = products.get(i).getProductManualPath();
//			}
//		} else {
//			columns = new Object[][] {};
//		}
		
		//################################----------------------------------------------------
		
//		productInfoTable.setModel(new DefaultTableModel(
//			columns,
//			new String[] {
//				"\uBC88\uD638", "\uBD84\uB958", "\uC0C1\uD488\uBA85", "\uC57D\uAD00", "\uC0C1\uD488\uC124\uBA85\uC11C"
//			}
//		){
//			/**
//			 *
//			 */
//			private static final long serialVersionUID = 1L;
//
//			// 테이블을 수정할수있는지 여부를 비활성화해두는것
//			@Override
//			public boolean isCellEditable(int col, int row) {
//				return false;
//			}
//		});
		
	}
	
	public void setTable(int colIdx, String name) {
//		System.out.println("받은 값: " + name);
		if(tableModel.getRowCount() != 0) {
			tableModel.setRowCount(0);
//			System.out.println("테이블 행 초기화");
		}
		
		for(Object[] data : tableData) {
			// 매개변수로 받은 속성(컬럼)의 인덱스 그리고 그 값에 따라서 목록을 분류 후 테이블을 만든다
			if(data[colIdx].equals(name)) {
//				System.out.println(Arrays.toString(data));
				tableModel.addRow(data);
//				System.out.println("테이블에 행 추가");
			} else if(colIdx == 1 && name.equals("전체")) {
				tableModel.addRow(data);
			}
		}
	}
	
	public void setInitTableData() {
		if(tableModel.getRowCount() != 0) {
			tableModel.setColumnCount(0);
		}
		
		List<ProductModel> products = null;
		try (Connection conn = InsuranceTeamConnector.getConnection()){
			products = ProductDAO.getAllProducts(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int size = products.size();
		tableData = new Object[size][5];
				
		int idx = 0;
		for(ProductModel product : products) {
			Object[] rows = {product.getProductId(),
					product.getDivision(),
					product.getProductName(),
					product.getTermAndConditionsPath(),
					product.getProductManualPath()};
			tableModel.addRow(rows);
			tableData[idx] = rows;
			idx++;
		}
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
