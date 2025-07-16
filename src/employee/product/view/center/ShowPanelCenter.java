package employee.product.view.center;

import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import common.database.dao.ProductDAO;
import common.database.model.ProductModel;
import common.method.InsuranceTeamConnector;

public class ShowPanelCenter extends JPanel{
	public JTable table;
	private DefaultTableModel model;
	public JScrollPane jpane;

	
	Object[][] columns = null;
	List<ProductModel> products = null;
	
	public ShowPanelCenter() {
		// 초기 테이블 세팅
		String[] columnNames = {"\uBC88\uD638", "\uBD84\uB958", "\uC0C1\uD488\uBA85", "\uC57D\uAD00", "\uC0C1\uD488\uC124\uBA85\uC11C"};
		model = new DefaultTableModel(columnNames, 0);
		// 일단은 빈테이블
		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setShowVerticalLines(false);
		table.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 20));
		table.setRowHeight(30);
		table.getTableHeader().setReorderingAllowed(false);
		
		jpane = new JScrollPane(table);
		jpane.setAutoscrolls(true);
		jpane.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		add(jpane);
		
		setTableData();
	}
	
	/***
	 * DB에서 데이터를 받아서 테이블에 추가해주는 메서드
	 */
	public void setTableData() {
		// 기존에 테이블에 데이터가있다면 내용제거
		if (model.getRowCount() != 0) {
			model.setRowCount(0);			
		}
		// db에서 데이터불러오고 테이블에 추가
		try (Connection conn = InsuranceTeamConnector.getConnection()){
			products = ProductDAO.getAllProducts(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (ProductModel product : products) {
			Object[] rows = {product.getProductId(),
					product.getDivision(),
					product.getProductName(),
					product.getTermAndConditionsPath(),
					product.getProductManualPath()};
			model.addRow(rows);
		}
	}
}
