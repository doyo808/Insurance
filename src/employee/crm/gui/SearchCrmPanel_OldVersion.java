package employee.crm.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import common.method.InsuranceTeamConnector;
import customer.mypage.method.MyPageUtil;

public class SearchCrmPanel_OldVersion extends JPanel {
	
	private JTextField tfName, tfBirth, tfUserId, tfPhone, tfContractId;
    private JTable resultTable;    
    private DefaultTableModel tableModel;
    private int currentPage = 1;
    private int rowsPerPage = 10;
    private int totalRows = 0;

	
	public SearchCrmPanel_OldVersion() {
		setPreferredSize(new Dimension(1440, 700));
		setBounds(0, 162, 1440, 700);
		
		setLayout(null);
		
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setBounds(0, 0, 1440, 700);
		separator.setForeground(Color.RED);
		add(separator);
		
		int labelW = 80, fieldW = 200, height = 25;
        int x1 = 30, x2 = 380, x3 = 750, x4 = 1120;
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
        btnSearch.setBounds(x4 + labelW + 10, y, 100, height);
        add(btnSearch);
        
        
        

        // ====== 테이블 ======
        String[] columns = {
            "고객ID", "이름", "생년월일", "아이디", "휴대폰", "이메일", "주소", "계약번호"
        };
        tableModel = new DefaultTableModel(columns, 0) {       	
        	
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}    
        	
        	
        	
        };
        
        resultTable = new JTable(tableModel);        
        JScrollPane scroll = new JScrollPane(resultTable);
        scroll.setBounds(30, 150, 1380, 500);
        add(scroll);
        
        // ====== 이벤트 ======
        resultTable.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		int selectedRow = resultTable.getSelectedRow();
        		
        		if(selectedRow != -1) {
        			int customerId = (int) resultTable.getValueAt(selectedRow, 0);
        			
        			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(SearchCrmPanel_OldVersion.this);
        			for(Component comp : frame.getContentPane().getComponents()) {
        				if(comp instanceof JPanel) {
        					JPanel parentPanel = (JPanel) comp;
        					if(BorderLayout.CENTER.equals(((BorderLayout) frame.getContentPane().getLayout()).getConstraints(comp))) {
        						
        						parentPanel.removeAll();
        						parentPanel.setLayout(new BorderLayout());
        						parentPanel.add(new DetailCrmPanel(customerId), BorderLayout.CENTER);
        						parentPanel.revalidate();
        						parentPanel.repaint();
        						break;
        					}
        				}
        			}
        		}
        	}
		});

        // ====== 이벤트 ======
        btnSearch.addActionListener(e -> {
        	currentPage = 1;
        	setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        	searchCustomers();
        	setCursor(Cursor.getDefaultCursor());
        });
        
        
        //화면이 열리자 마자 데이터가 조회되게 하는 방법
        currentPage = 1;
        searchCustomers();
        
        
    }
	
	private void searchCustomers() {
        tableModel.setRowCount(0); // 기존 결과 초기화

        //조회조건
        String name = tfName.getText().trim();
        String birth = tfBirth.getText().trim();
        String userId = tfUserId.getText().trim();
        String phone = tfPhone.getText().trim();
        String contractId = tfContractId.getText().trim();

        StringBuilder sql = new StringBuilder(
            "SELECT c.customer_id, c.customer_name, c.personal_id, c.login_id, c.phone_number, c.email, " +
            "CONCAT(c.address_1, c.address_2) AS full_address, ct.contract_id " +
            "FROM customers c " +
            "LEFT JOIN contracts ct ON c.customer_id = ct.customer_id WHERE 1 = 1"
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
            sql.append(" AND c.login_id LIKE ?");
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
        
        sql.append(" ORDER BY c.customer_id");

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
                    MyPageUtil.convertJuminToBirth(rs.getString("personal_id")),                    
                    rs.getString("login_id"),
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
