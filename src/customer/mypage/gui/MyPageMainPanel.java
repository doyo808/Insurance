package customer.mypage.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import common.account.login.Session;
import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;
import customer.mypage.method.MyPageUtil;
import insuranceMain.customerPanel.CustomerMainPanel;


//전체 화면 사이즈 : setBounds(100, 10, 1440, 1024);
// 상단판넬 사이즈 : setBounds(0, 0, 1440, 162);	
// 컨텐츠판넬 사이즈 : setBounds(0, 162, 1440, 700);
// 하단판텔 사이즈 : setBounds(0, 0, 1440, 162);	

public class MyPageMainPanel extends JPanel {
	
	private JLabel lbName, lbAddress, lbBirth, lbPhone, lbEmail, lbJob, lbCompany, lbWorkAddress, lbCompanyPhone;
	private JTextField tfName, tfAddress, tfBirth, tfPhone, tfEmail, tfJob, tfCompany, tfWorkAddress, tfCompanyPhone;
	private JTable contractTable, paymentTable, claimTable;
	private JButton btnEdit, btnExcel;
	private CustomerModel cm;

	
	public MyPageMainPanel(CustomerMainPanel cmp) {
		setPreferredSize(new Dimension(1440, 700));
		setBounds(0, 162, 1440, 700);
		
		setLayout(null);
		
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBounds(0, 0, 1440, 700); // x, y, width, height
        separator.setForeground(Color.RED); // 구분선 색상
        add(separator);	
        
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
        
        btnEdit.addActionListener(e -> {
            MyInfoModiDialog dialog = new MyInfoModiDialog(this, cm);
            dialog.setVisible(true);
        });       
        
        

        // ==== 테이블 UI 구성 ====
        
        // 보험 계약정보 JLabel
        JLabel contractLabel = new JLabel("보험 계약 정보");
        contractLabel.setBounds(50, 210, 300, 25);
        contractLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        add(contractLabel);
        
        // 계약정보 JTable
        contractTable = new JTable();
        contractTable.setRowHeight(23);
        JScrollPane contractScroll = new JScrollPane(contractTable);
        contractScroll.setBounds(50, 240, 1340, 130);
        add(contractScroll);
        
        // 납입 내역 라벨
        JLabel paymentLabel = new JLabel("납입 내역");
        paymentLabel.setBounds(50, 400, 300, 25);
        paymentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        add(paymentLabel);
        
        // 엑셀변환 버튼        
        btnExcel = new JButton("엑셀변환");
        btnExcel.setBounds(1299, 390, 90, 30);
        add(btnExcel);
        
        btnExcel.addActionListener(e -> exportExcel(paymentTable));
        
        // 납입 내역 JTable
        paymentTable = new JTable();
        paymentTable.setRowHeight(23);
        JScrollPane paymentScroll = new JScrollPane(paymentTable);
        paymentScroll.setBounds(50, 430, 1340, 130);
        add(paymentScroll);
        
        // 청구 내역 JLabel
        JLabel claimLabel = new JLabel("청구 내역");
        claimLabel.setBounds(50, 590, 300, 25);
        claimLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        add(claimLabel);
        
        // 청구 내역 JTable
        claimTable = new JTable();
        claimTable.setRowHeight(23);
        JScrollPane claimScroll = new JScrollPane(claimTable);
        claimScroll.setBounds(50, 620, 1340, 130);
        add(claimScroll);        
        

        // ==== 데이터 로드 ====
        this.cm = Session.getCustomer();
        loadPersonalInfo();        
        loadContractInfo();
        loadPaymentHistory();
        loadClaimInfo();
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
//		/// FIXME: 테스트용 연결과 고객모델
//        Connection conn = null;
//		try {
//			conn = InsuranceTeamConnector.getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//        CustomerModel cm = null;
//		try {
//			cm = CustomerDAO.getCustomerByLoginId("hong123", conn);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}	
				
		if (cm != null) {
		    tfName.setText(cm.getCustomer_name());
		    tfAddress.setText(cm.getAddress_1() + cm.getAddress_2());		    
		    tfBirth.setText(MyPageUtil.convertJuminToBirth(cm.getPersonal_id()));
		    tfPhone.setText(cm.getPhone_number());
		    tfEmail.setText(cm.getEmail());
		    tfJob.setText(cm.getJob());
		    tfCompany.setText(cm.getCompany_name());
		    tfCompanyPhone.setText(cm.getJob_phone_number());
		    tfWorkAddress.setText(cm.getJob_address1() + cm.getJob_address2());
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

        if (cm == null) {
            //System.out.println("로그인된 고객 정보가 없습니다.");
            return;
        }

        int customerId = cm.getCustomer_id(); // 고객 ID 가져오기   	
    	
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
						String.format("%,d", rs.getInt("premium")), 
						MyPageUtil.formatDate(rs.getString("signup_date")),
						MyPageUtil.formatDate(rs.getString("effective_date")),
						MyPageUtil.formatDate(rs.getString("payment_end_date")),
						MyPageUtil.formatDate(rs.getString("coverage_end_date")),
						MyPageUtil.getDisplayStatus("CONTRACT", rs.getString("status"))				
				});
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        contractTable.setModel(model);
    }

    private void loadPaymentHistory() {    	
    	DefaultTableModel model = new DefaultTableModel(new String[] {"선택", "계약번호", "납입월", "상품명", "납부금액", "납입상태"}, 0) {    		
    		
    		@Override
    		public boolean isCellEditable(int row, int column) {
    			return column == 0;
    		}
    		
    		@Override
    		public Class<?> getColumnClass(int columnIndex) {    			
    			return columnIndex == 0 ? Boolean.class : String.class;
    		}
    		
    		
    	};    	

        if (cm == null) {
            //System.out.println("로그인된 고객 정보가 없습니다.");
            return;
        }

        int customerId = cm.getCustomer_id(); // 고객 ID 가져오기
    	
    	try(Connection conn = InsuranceTeamConnector.getConnection();) {
    		
    		String sql = "SELECT p.contract_id, p.payment_date, (SELECT product_name FROM products WHERE product_id = c.product_id) AS product_name, paid_amount, pay_status "
    				+ "FROM payments p, contracts c "
    				+ "WHERE p.contract_id = c.contract_id AND p.customer_id = ? "
    				+ "ORDER BY payment_date DESC";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, customerId);
    		
    		ResultSet rs = pstmt.executeQuery();
    		while(rs.next()) {
    			model.addRow(new Object[] {
    					Boolean.FALSE,
    					rs.getInt("contract_id"),
    					MyPageUtil.formatToYearMonth(rs.getString("payment_date")),
    					rs.getString("product_name"),
    					String.format("%,d", rs.getInt("paid_amount")),
    					MyPageUtil.getDisplayStatus("PAYMNET", rs.getString("pay_status"))   					
    			});
    		}   				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}        
    	paymentTable.setModel(model);
    	
    	// 선택항목 너비 고정
    	TableColumn selectColumn = paymentTable.getColumnModel().getColumn(0);
    	selectColumn.setPreferredWidth(40);
    	selectColumn.setMinWidth(40);
    	selectColumn.setMaxWidth(40);
    	
    	JTableHeader header = paymentTable.getTableHeader();
        JCheckBox selectAllCheckBox = new JCheckBox();
        selectAllCheckBox.setOpaque(false);
        selectAllCheckBox.setHorizontalAlignment(SwingConstants.CENTER);

        // 현재 체크박스 상태를 반영하여 모든 행에 적용
        selectAllCheckBox.addActionListener(e -> {
            boolean selected = selectAllCheckBox.isSelected();
            for (int i = 0; i < paymentTable.getRowCount(); i++) {
                paymentTable.setValueAt(selected, i, 0);
            }
        });

        // 커스텀 헤더 렌더러 지정
        selectColumn.setHeaderRenderer((table, value, isSelected, hasFocus, row, column) -> selectAllCheckBox);
    	
        JTableHeader tableHeader = paymentTable.getTableHeader();
        tableHeader.addMouseListener(new MouseAdapter() {
        	@Override
            public void mouseClicked(MouseEvent e) {
                int col = paymentTable.columnAtPoint(e.getPoint());
                if (col == 0) { // 첫 번째 열 클릭 시
                    boolean isSelected = !selectAllCheckBox.isSelected();
                    selectAllCheckBox.setSelected(isSelected);
                    for (int i = 0; i < paymentTable.getRowCount(); i++) {
                        paymentTable.setValueAt(isSelected, i, 0);
                    }
                    tableHeader.repaint();
                }
        	}
               
        });
    	
    	
    }
    
    private void loadClaimInfo() {
    	DefaultTableModel model = new DefaultTableModel(new String[] {"접수번호","청구일자","진단명", "재해자/물 보상구분", "상품명", "담당자", "처리상태"}, 0) {	
    		@Override
    		public boolean isCellEditable(int row, int column){
    			return false;
    		}
    	};
    	
    	if (cm == null) {
            //System.out.println("로그인된 고객 정보가 없습니다.");
            return;
        }

        int customerId = cm.getCustomer_id(); // 고객 ID 가져오기
        
        try(Connection conn = InsuranceTeamConnector.getConnection()) {
        	
        	String sql = "SELECT claim_id, claim_date, "
        			+ "(SELECT diagnosis_name FROM diagnosis_codes WHERE diagnosis_cd = cl.diagnosis_cd) AS diagnosis_name, "
        			+ "(SELECT insured_name FROM insureds WHERE insured_id = con.insured_id) AS insured_name, "
        			+ "(SELECT product_name FROM products WHERE product_id = con.product_id) AS product_name, "
        			+ "(SELECT employee_name FROM employees WHERE employee_id = cl.employee_id) AS employee_name , "
        			+ "claim_status "
        			+ "FROM claims cl, contracts con "
        			+ "WHERE  cl.contract_id = con.contract_id "
        			+ "AND con.customer_id = ?";
        	PreparedStatement pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, customerId);
        	
        	
        	ResultSet rs = pstmt.executeQuery();
        	while(rs.next()) {
        		model.addRow(new Object[] {
        				rs.getInt("claim_id"),
        				rs.getDate("claim_date"),
        				rs.getString("diagnosis_name").trim(),        				
        				rs.getString("insured_name").trim(),
        				rs.getString("product_name").trim(),
        				rs.getString("employee_name").trim(),
        				MyPageUtil.getDisplayStatus("CLAIM", rs.getString("claim_status"))
        		});
        	}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
    	
    	
    	claimTable.setModel(model);
    }
    
    public void refreshCustomerData() {
    	//System.out.println(">>> refreshCustomerData 호출됨");
    	this.cm = Session.getCustomer();
    	loadPersonalInfo();        
        loadContractInfo();
        loadPaymentHistory();
    }
    
    private void exportExcel(JTable table) {
    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setDialogTitle("엑셀로 저장");
    	fileChooser.setSelectedFile(new File("납입내역.xlsx"));
    	
    	int userSelection = fileChooser.showSaveDialog(this);
    	if(userSelection != JFileChooser.APPROVE_OPTION) return;
    	
    	File fileToSave = fileChooser.getSelectedFile();
    	if(!fileToSave.getName().toLowerCase().endsWith(".xlsx")) {
    		fileToSave = new File(fileToSave.getAbsolutePath() + ".xlsx");
    	}
    	
    	try(Workbook workbook = new XSSFWorkbook()) {    		
    		Sheet sheet = workbook.createSheet("납입내역");
    		
    		DefaultTableModel model = (DefaultTableModel) table.getModel();
    		int columnCount = model.getColumnCount();
    		
    		//1. 헤더 작성(선택 열 제외)
    		Row headerRow = sheet.createRow(0);
    		for(int col = 1; col < columnCount; col++) {
    			Cell cell = headerRow.createCell(col - 1);
    			cell.setCellValue(model.getColumnName(col));    			
    		}
    		
    		//2. 체크된 행만 추가
    		int rowIndex = 1;
    		for(int row = 0; row < model.getRowCount(); row++) {
    			Boolean isChecked = (Boolean) model.getValueAt(row, 0);
    			if(isChecked != null && isChecked) {
    				Row excelRow = sheet.createRow(rowIndex++);
    				for(int col = 1; col < columnCount; col++) {
    					Object value = model.getValueAt(row, col);
    					Cell cell = excelRow.createCell(col - 1);
    					cell.setCellValue(value != null ? value.toString() : "");
    				}
    			}
    		}
    		
    		try(FileOutputStream fileOut = new FileOutputStream(fileToSave)) {
				workbook.write(fileOut);
			}
    		
    		JOptionPane.showMessageDialog(this, "엑셀 파일로 저장완료", "성공", JOptionPane.INFORMATION_MESSAGE);
    		
			
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "엑셀 저장 중 오류 발생", "오류", JOptionPane.ERROR_MESSAGE);
			
		}   	
    	
    }
	
	
}
	
