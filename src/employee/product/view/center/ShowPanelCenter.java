package employee.product.view.center;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShowPanelCenter extends JPanel{
	public JTable table, coverTable;
	public DefaultTableModel tableModel, coverTableModel;
	public JScrollPane jpane, coverScroll;
	
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
		
	}
}
