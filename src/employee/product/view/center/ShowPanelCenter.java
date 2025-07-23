package employee.product.view.center;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import common.database.dao.ProductCoverageDetailDAO;
import common.database.dao.ProductDAO;
import common.database.model.ProductCoverageDetailModel;
import common.database.model.ProductModel;
import common.method.InsuranceTeamConnector;

public class ShowPanelCenter extends JPanel{
	public JTable table, coverTable;
	public DefaultTableModel tableModel, coverTableModel;
	public JScrollPane jpane, coverScroll;

	List<ProductModel> products = null;
	List<ProductCoverageDetailModel> covers = null;
	
	public ShowPanelCenter() {
		setLayout(new FlowLayout());
		init();
	}
	
	public void init() {
		// 초기 테이블 세팅
		String[] columnNames = {"\uBC88\uD638", "\uBD84\uB958", "\uC0C1\uD488\uBA85", "\uC57D\uAD00", "\uC0C1\uD488\uC124\uBA85\uC11C"};
		tableModel = new DefaultTableModel(columnNames, 0);
		// 일단은 빈테이블
		table = new JTable(tableModel) {
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
		jpane.setPreferredSize(new Dimension(900, 400));
		jpane.setAutoscrolls(true);
		add(jpane);
		
		coverTableModel = new DefaultTableModel(new String[]{"담보명","보장내용"}, 0);
		coverTable = new JTable(coverTableModel) {
			public boolean isCellEditable(int row, int column) {return false;};
		};
		coverTable.setShowVerticalLines(false);
		coverTable.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		coverTable.setRowHeight(30);
		coverTable.getTableHeader().setReorderingAllowed(false);
		
		coverScroll = new JScrollPane(coverTable);
		coverScroll.setPreferredSize(new Dimension(200, 400));
		coverScroll.setAutoscrolls(true);
		add(coverScroll);
		
		setTableData();
	}
	
	// 이벤트 컨트롤러로 분리하기에는 초기에 패널들 구성할때 테이블 띄우기가 힘들거같아서 그대로 두기로함
	/***
	 * ShowPanelCenter 클래스 전용메서드
	 * DB에서 데이터를 받아서 테이블에 추가해주는 메서드
	 */
	public void setTableData() {
		// 기존에 테이블에 데이터가있다면 내용제거
		if (tableModel.getRowCount() != 0) {
			tableModel.setRowCount(0);
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
			tableModel.addRow(rows);
		}
	}
}
