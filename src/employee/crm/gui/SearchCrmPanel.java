package employee.crm.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import common.method.InsuranceTeamConnector;

public class SearchCrmPanel extends JPanel {
	
	private JTextField tfName, tfBirth, tfUserId, tfPhone, tfContractId;
    private JTable resultTable;
    private DefaultTableModel tableModel;

	
	public SearchCrmPanel() {
		setPreferredSize(new Dimension(1440, 700));
		setBounds(0, 162, 1440, 700);
		
		setLayout(null);
		
		JSeparator seperator = new JSeparator(SwingConstants.HORIZONTAL);
		seperator.setBounds(0, 0, 1440, 700);
		seperator.setForeground(Color.RED);
		add(seperator);
		
		int labelW = 80, fieldW = 200, height = 25;
        int x1 = 30, x2 = 400, x3 = 770, x4 = 1140;
        int y = 30, spacingY = 40;
        
     // ====== 조건 입력 필드 ======
        JLabel lbName = new JLabel("이름");
        lbName.setBounds(x1, y, labelW, height);
        add(lbName);
        tfName = new JTextField();
        tfName.setBounds(x1 + labelW + 10, y, fieldW, height);
        add(tfName);

        JLabel lbBirth = new JLabel("생년월일");
        lbBirth.setBounds(x2, y, labelW, height);
        add(lbBirth);
        tfBirth = new JTextField();
        tfBirth.setBounds(x2 + labelW + 10, y, fieldW, height);
        add(tfBirth);

        JLabel lbUserId = new JLabel("아이디");
        lbUserId.setBounds(x3, y, labelW, height);
        add(lbUserId);
        tfUserId = new JTextField();
        tfUserId.setBounds(x3 + labelW + 10, y, fieldW, height);
        add(tfUserId);
        
        JLabel lbPhone = new JLabel("휴대폰");
        lbPhone.setBounds(x4, y, labelW, height);
        add(lbPhone);
        tfPhone = new JTextField();
        tfPhone.setBounds(x4 + labelW + 10, y, fieldW, height);
        add(tfPhone);

        y += spacingY;      

        JLabel lbContractId = new JLabel("계약번호");
        lbContractId.setBounds(x1, y, labelW, height);
        add(lbContractId);
        tfContractId = new JTextField();
        tfContractId.setBounds(x1 + labelW + 10, y, fieldW, height);
        add(tfContractId);

        JButton btnSearch = new JButton("조회");
        btnSearch.setBounds(x3 + labelW + 10, y, 100, height);
        add(btnSearch);

        // ====== 테이블 ======
        String[] columns = {
            "고객ID", "이름", "생년월일", "아이디", "휴대폰", "이메일", "주소", "계약번호"
        };
        tableModel = new DefaultTableModel(columns, 0);
        resultTable = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(resultTable);
        scroll.setBounds(30, 150, 1380, 500);
        add(scroll);

        // ====== 이벤트 ======
        btnSearch.addActionListener(e -> searchCustomers());
    }
	
	private void searchCustomers() {
        tableModel.setRowCount(0); // 기존 결과 초기화

        String name = tfName.getText().trim();
        String birth = tfBirth.getText().trim();
        String userId = tfUserId.getText().trim();
        String phone = tfPhone.getText().trim();
        String contractId = tfContractId.getText().trim();

        StringBuilder sql = new StringBuilder(
            "SELECT c.customer_id, c.customer_name, c.personal_id, c.user_id, c.phone_number, c.email, " +
            "CONCAT(c.address_1, c.address_2) AS full_address, ct.contract_id " +
            "FROM customers c " +
            "LEFT JOIN contracts ct ON c.customer_id = ct.customer_id WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        if (!name.isEmpty()) {
            sql.append(" AND c.customer_name LIKE ?");
            params.add("%" + name + "%");
        }
        if (!birth.isEmpty()) {
            sql.append(" AND c.personal_id LIKE ?");
            params.add(birth + "%");  // 앞자리 조회
        }
        if (!userId.isEmpty()) {
            sql.append(" AND c.user_id LIKE ?");
            params.add("%" + userId + "%");
        }
        if (!phone.isEmpty()) {
            sql.append(" AND c.phone_number LIKE ?");
            params.add("%" + phone + "%");
        }
        if (!contractId.isEmpty()) {
            sql.append(" AND ct.contract_id = ?");
            params.add(contractId);
        }

        try (Connection conn = InsuranceTeamConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("customer_id"),
                    rs.getString("customer_name"),
                    rs.getString("personal_id"),
                    rs.getString("user_id"),
                    rs.getString("phone_number"),
                    rs.getString("email"),
                    rs.getString("full_address"),
                    rs.getString("contract_id")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "조회 중 오류 발생", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }	

}
