package customer.mypage.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import common.account.login.Session;
import common.database.dao.CustomerDAO;
import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;


//전체 화면 사이즈 : setBounds(100, 10, 1440, 1024);
// 상단판넬 사이즈 : setBounds(0, 0, 1440, 162);	
// 컨텐츠판넬 사이즈 : setBounds(0, 162, 1440, 700);
// 하단판텔 사이즈 : setBounds(0, 0, 1440, 162);	

public class MyPageMainPanel extends JPanel {
	
	private JLabel lbName, lbAddress, lbBirth, lbPhone, lbEmail, lbJob, lbCompany, lbWorkAddress, lbCompanyPhone;
	private JTextField tfName, tfAddress, tfBirth, tfPhone, tfEmail, tfJob, tfCompany, tfWorkAddress, tfCompanyPhone;
	private JTable contractTable, paymentTable;
	private JButton btnEdit;

	
	public MyPageMainPanel() {
		setPreferredSize(new Dimension(1440, 700));
		setBounds(0, 162, 1440, 700);
		
		setLayout(null);
		
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBounds(0, 0, 1440, 700); // x, y, width, height
        separator.setForeground(Color.RED); // 구분선 색상
        add(separator);		
		
//		JLabel lblNewLabel = new JLabel("New label");
//		lblNewLabel.setBounds(20, 10, 57, 15);
//		add(lblNewLabel);
		
		
        
		int labelW = 80;
        int fieldW = 250;
        int h = 25;
        int spacingY = 15;
        int x1 = 50, x2 = 530, x3 = 1010;
        int y = 20;

        // 1행: 이름, 주소
        tfName = addLabeledField(lbName = new JLabel("이름"), x1, y, labelW, fieldW);
        tfAddress = addLabeledField(lbAddress = new JLabel("주소"), x2, y, labelW, fieldW + 480);

        // 2행: 생년월일, 휴대폰, 이메일
        y += h + spacingY;
        tfBirth = addLabeledField(lbBirth = new JLabel("생년월일"), x1, y, labelW, fieldW);
        tfPhone = addLabeledField(lbPhone = new JLabel("휴대폰"), x2, y, labelW, fieldW);
        tfEmail = addLabeledField(lbEmail = new JLabel("이메일"), x3, y, labelW, fieldW);

        // 3행: 직업, 회사명, 직장연락처
        y += h + spacingY;
        tfJob = addLabeledField(lbJob = new JLabel("직업"), x1, y, labelW, fieldW);
        tfCompany = addLabeledField(lbCompany = new JLabel("회사명"), x2, y, labelW, fieldW);
        tfCompanyPhone = addLabeledField(lbCompanyPhone = new JLabel("회사연락처"), x3, y, labelW, fieldW);

        // 4행: 회사주소 (중앙 정렬)
        y += h + spacingY;       
        tfWorkAddress = addLabeledField(lbWorkAddress = new JLabel("회사주소"), x1, y, labelW, fieldW + 960);       

        // 5행: 수정 버튼
        y += h + spacingY;
        btnEdit = new JButton("수정");
        btnEdit.setBounds(1300, y, 80, 30);
        add(btnEdit);

        // ==== 테이블 UI 구성 ====
        JLabel contractLabel = new JLabel("보험 계약 정보");
        contractLabel.setBounds(50, 250, 300, 25);
        contractLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        add(contractLabel);

        contractTable = new JTable();
        JScrollPane contractScroll = new JScrollPane(contractTable);
        contractScroll.setBounds(50, 280, 1340, 150);
        add(contractScroll);

        JLabel paymentLabel = new JLabel("납입 내역");
        paymentLabel.setBounds(50, 440, 300, 25);
        paymentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        add(paymentLabel);

        paymentTable = new JTable();
        JScrollPane paymentScroll = new JScrollPane(paymentTable);
        paymentScroll.setBounds(50, 470, 1340, 180);
        add(paymentScroll);

        // ==== 데이터 로드 ====
        loadPersonalInfo();
        //loadContractInfo();
        //loadPaymentHistory();
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
		/// FIXME: 테스트용 연결과 고객모델
        Connection conn = null;
		try {
			conn = InsuranceTeamConnector.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        CustomerModel cm = null;
		try {
			cm = CustomerDAO.getCustomerByLoginId("hong123", conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}    
        
//		CustomerModel cm = Session.getCustomer(); //공통 로그인 세션 사용시
		
	    tfName.setText(cm.getCustomer_name());
	    tfAddress.setText(cm.getAddress_1() + cm.getAddress_2());
	    tfBirth.setText(cm.getPersonal_id());
	    tfPhone.setText(cm.getPhone_number());
	    tfEmail.setText(cm.getEmail());
	    tfJob.setText(cm.getJob());
	    tfCompany.setText(cm.getCompany_name());
	    tfCompanyPhone.setText(cm.getJob_phone_number());
	    tfWorkAddress.setText(cm.getJob_address1() + cm.getJob_address2());
    }
//
//    private void loadContractInfo() {
//        DefaultTableModel model = new DefaultTableModel(new String[]{"보험상품명", "계약번호", "가입일", "만기일"}, 0);
//        try (Connection conn = DBManager.getConnection()) {
//            String sql = "SELECT 보험상품명, 계약번호, 가입일, 만기일 FROM 계약 WHERE 사용자ID = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, 1);
//
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                    rs.getString("보험상품명"),
//                    rs.getString("계약번호"),
//                    rs.getDate("가입일"),
//                    rs.getDate("만기일")
//                });
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        contractTable.setModel(model);
//    }
//
//    private void loadPaymentHistory() {
//        DefaultTableModel model = new DefaultTableModel(new String[]{"납입년월", "보험상품명"}, 0);
//        try (Connection conn = DBManager.getConnection()) {
//            String sql = "SELECT 납입년월, 보험상품명 FROM 납입내역 WHERE 사용자ID = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, 1);
//
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                    rs.getString("납입년월"),
//                    rs.getString("보험상품명")
//                });
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        paymentTable.setModel(model);
//    }
	
	
}
	
