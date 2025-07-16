package employee.crm.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import common.account.login.Session;
import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;


public class DetailCrmPanel extends JPanel {
	
	private JLabel lbName, lbAddress, lbBirth, lbPhone, lbEmail, lbJob, lbCompany, lbWorkAddress, lbCompanyPhone;
	private JTextField tfName, tfAddress, tfBirth, tfPhone, tfEmail, tfJob, tfCompany, tfWorkAddress, tfCompanyPhone;
	private JTable contractTable, paymentTable;
	private JButton btnBack, btnEdit;
	private CustomerModel cm;
	private int customerId;

	
	public DetailCrmPanel(int customerId) {
		this.customerId = customerId;
		
		setPreferredSize(new Dimension(1440, 700));
		setBounds(0, 162, 1440, 700);
		
		setLayout(null);
		
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBounds(0, 0, 1440, 700); // x, y, width, height
        separator.setForeground(Color.RED); // 구분선 색상
        add(separator);	
        
		        
        int labelW = 80, fieldW = 200, height = 25;        
        int x1 = 30, x2 = 530, x3 = 1010;        
        int y = 30, spacingY = 15;

        // 1행: 이름, 주소
        tfName = addLabeledField(lbName = new JLabel("이름"), x1, y, labelW, fieldW);
        tfAddress = addLabeledField(lbAddress = new JLabel("주소"), x2, y, labelW, fieldW + 480);

        // 2행: 생년월일, 휴대폰, 이메일
        y += height + spacingY;
        tfBirth = addLabeledField(lbBirth = new JLabel("생년월일"), x1, y, labelW, fieldW);
        tfPhone = addLabeledField(lbPhone = new JLabel("휴대폰"), x2, y, labelW, fieldW);
        tfEmail = addLabeledField(lbEmail = new JLabel("이메일"), x3, y, labelW, fieldW);

        // 3행: 직업, 회사명, 직장연락처
        y += height + spacingY;
        tfJob = addLabeledField(lbJob = new JLabel("직업"), x1, y, labelW, fieldW);
        tfCompany = addLabeledField(lbCompany = new JLabel("회사명"), x2, y, labelW, fieldW);
        tfCompanyPhone = addLabeledField(lbCompanyPhone = new JLabel("회사연락처"), x3, y, labelW, fieldW);

        // 4행: 회사주소 (중앙 정렬)
        y += height + spacingY;       
        tfWorkAddress = addLabeledField(lbWorkAddress = new JLabel("회사주소"), x1, y, labelW, fieldW + 960);       

        // 5행: 수정 버튼
        y += height + spacingY;
        btnBack = new JButton("LIST");
        btnBack.setBounds(1200, y, 80, 30);
        add(btnBack);
        
        btnEdit = new JButton("수정");
        btnEdit.setBounds(1300, y, 80, 30);       
        add(btnEdit);
        
        
        btnBack.addActionListener(e -> goBackToSearch());
                
        btnEdit.addActionListener(e -> {
            //MyInfoModiDialog dialog = new MyInfoModiDialog(this, cm);
            //dialog.setVisible(true);
        });        
        
        

        // ==== 테이블 UI 구성 ====
        JTabbedPane tabDetailInfo = new JTabbedPane();
        tabDetailInfo.setBounds(50, 250, 1340, 400);
        
        //보험 계약 정보 탭
        contractTable = new JTable();
        JScrollPane contraJScroll = new JScrollPane(contractTable);
        JPanel contractPanel = new JPanel(new BorderLayout());
        contractPanel.add(contraJScroll, BorderLayout.CENTER);
        tabDetailInfo.addTab("보험 계약 정보", contractPanel);
        
        // 납입 내역 탭
        paymentTable = new JTable();
        JScrollPane paymentScroll = new JScrollPane(paymentTable);
        JPanel paymentPanel = new JPanel(new BorderLayout());
        paymentPanel.add(paymentScroll, BorderLayout.CENTER);
        tabDetailInfo.addTab("납입 내역", paymentPanel);
        
        add(tabDetailInfo);
        
       
        
//구버전        
//        JLabel contractLabel = new JLabel("보험 계약 정보");
//        contractLabel.setBounds(50, 250, 300, 25);
//        contractLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
//        add(contractLabel);
//
//        contractTable = new JTable();
//        JScrollPane contractScroll = new JScrollPane(contractTable);
//        contractScroll.setBounds(50, 280, 1340, 150);
//        add(contractScroll);
//
//        JLabel paymentLabel = new JLabel("납입 내역");
//        paymentLabel.setBounds(50, 440, 300, 25);
//        paymentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
//        add(paymentLabel);
//
//        paymentTable = new JTable();
//        JScrollPane paymentScroll = new JScrollPane(paymentTable);
//        paymentScroll.setBounds(50, 470, 1340, 180);
//        add(paymentScroll);

        // ==== 데이터 로드 ====
        //this.cm = Session.getCustomer();
        loadPersonalInfo();        
        loadContractInfo();
        loadPaymentHistory();
    }
	
	private JTextField addLabeledField(JLabel label, int x, int y, int labelWidth, int fieldWidth) {
        label.setBounds(x, y, labelWidth, 25);
        add(label);

        JTextField field = new JTextField();
        field.setBounds(x + labelWidth + 10, y, fieldWidth, 25);
        field.setEditable(false);
        add(field);
        return field;
    }
	
	
	private void loadPersonalInfo() {
		try(Connection conn = InsuranceTeamConnector.getConnection()) {
			String sql = "SELECT * FROM customers WHERE customer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				tfName.setText(rs.getString("customer_name"));
			    tfAddress.setText(rs.getString("address_1") + rs.getString("address_2"));
			    tfBirth.setText(rs.getString("personal_id"));
			    tfPhone.setText(rs.getString("phone_number"));
			    tfEmail.setText(rs.getString("email"));
			    tfJob.setText(rs.getString("job"));
			    tfCompany.setText(rs.getString("company_name"));
			    tfCompanyPhone.setText(rs.getString("job_phone_number"));
			    tfWorkAddress.setText(rs.getString("job_address1") + rs.getString("job_address2"));
			}					
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		
    }

    private void loadContractInfo() {    	
    	DefaultTableModel model = new DefaultTableModel(new String[] {"계약번호", "상품명", "보험료", "가입일", "효력일", "결제만기일", "보장만기일", "계약상태"}, 0) {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    			return false;
    			//특정 컬럼을 수정하고 싶을 때
    			//return column == 0;
    		}
    	};
    	
    	try (Connection conn = InsuranceTeamConnector.getConnection()){			
			
			String sql = "SELECT contract_id, product_name, premium, signup_date, effective_date, payment_end_date, coverage_end_date, status "
					+ "FROM contracts c, products p "
					+ "WHERE c.product_id = p.product_id AND customer_id = ?";			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customerId);		
			
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				model.addRow(new Object[]{
						rs.getInt("contract_id"),
						rs.getString("product_name"),
						rs.getInt("premium"),
						rs.getString("signup_date"),
						rs.getString("effective_date"),
						rs.getString("payment_end_date"),
						rs.getString("coverage_end_date"),
						rs.getString("status")				
				});
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        contractTable.setModel(model);
    }

    private void loadPaymentHistory() {    	
    	DefaultTableModel model = new DefaultTableModel(new String[] {"계약번호", "납입월", "상품명", "납부금액", "납입상태"}, 0) {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    			return false;
    		}
    	};
    	
    	try(Connection conn = InsuranceTeamConnector.getConnection();) {
    		
    		String sql = "SELECT p.contract_id, p.payment_date, (SELECT product_name FROM products WHERE product_id = c.product_id) AS product_name, paid_amount, pay_status "
    				+ "FROM payments p, contracts c "
    				+ "WHERE p.contract_id = c.contract_id AND p.customer_id = ?";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, customerId);
    		
    		ResultSet rs = pstmt.executeQuery();
    		while(rs.next()) {
    			model.addRow(new Object[] {
    					rs.getInt("contract_id"),
    					rs.getString("payment_date"),
    					rs.getString("product_name"),
    					rs.getInt("paid_amount"),
    					rs.getString("pay_status")   					
    			});
    		}   				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}        paymentTable.setModel(model);
    }
    
    public void refreshCustomerData() {
    	//System.out.println(">>> refreshCustomerData 호출됨");
    	this.cm = Session.getCustomer();
    	loadPersonalInfo();        
        loadContractInfo();
        loadPaymentHistory();
    }
    
    private void goBackToSearch() {
        // 1. 현재 패널이 포함된 JFrame 참조 얻기
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

        // 2. contentPanel을 찾아서 기존 DetailCrmPanel 제거 후 새로운 SearchCrmPanel 추가
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JPanel) {
                JPanel parentPanel = (JPanel) comp;
                if (BorderLayout.CENTER.equals(((BorderLayout) frame.getContentPane().getLayout()).getConstraints(comp))) {

                    // 패널 내부 초기화 및 교체
                    parentPanel.removeAll();
                    parentPanel.setLayout(new BorderLayout());
                    parentPanel.add(new SearchCrmPanel(), BorderLayout.CENTER);
                    parentPanel.revalidate();
                    parentPanel.repaint();
                    break;
                }
            }
        }
    }

    
	
	
}