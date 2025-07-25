package customer.payment.gui.autopayment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import common.account.login.Session;
import common.database.dao.AutoPaymentDAO;
import common.database.model.AutoPaymentModel;
import common.method.InsuranceTeamConnector;
import customer.payment.gui.PaymentMainPanel;
import customer.payment.gui.components.CardNavButton;
import customer.payment.gui.components.CardSwitcher;
import customer.payment.gui.components.PaymentDefaultButton;
import customer.payment.method.AccountRegistrator;
import net.miginfocom.swing.MigLayout; // MigLayout import

public class AutoPaymentPanel2 extends JPanel {

    private static final long serialVersionUID = 1L;
    public final static String[] BANKLIST = {"국민은행", "신한은행", "우리은행", "하나은행", "카카오뱅크", "토스뱅크"};
    // UI 컴포넌트에 사용할 폰트
    private static final Font LABEL_FONT = new Font("맑은 고딕", Font.PLAIN, 13);
    private static final Font VALUE_FONT = new Font("맑은 고딕", Font.BOLD, 13);
    private String[] selectedData = null;
    // 정보 표시 및 입력용 컴포넌트
    private JLabel valProductName, valContractId, valStartDate, valEndDate, valPaymentType, valPremium;
    private JLabel lblRegisteredBank, valRegisteredBank, lblRegisteredAccount, valRegisteredAccount;
    private JLabel lblNoAccountInfo;
    private JComboBox<String> cbBank;
    private JTextField tfAccountNumber;
    private JPasswordField tfPassword2Digits;
    private JButton regiBtn, cnbtn;
    private CardSwitcher pmp;

    public AutoPaymentPanel2(PaymentMainPanel pmp) {
    	
        // 1. 메인 패널 레이아웃: 1열로 구성, 아래쪽 패널이 남은 공간 모두 차지
        super(new MigLayout(
                "insets 20, fill", // 전체 여백 20, 컴포넌트는 할당된 셀 채움
                "[grow]",          // 컬럼 설정: 1개 컬럼이 가로로 늘어남
                "[]20[grow]"       // 로우 설정: 1번째 로우는 기본 크기, 20px 갭, 2번째 로우가 세로로 늘어남
        ));
        this.pmp = pmp;
        setBackground(new Color(255, 255, 255));
    	setPreferredSize(new Dimension(1440, 700));

        // 2. 기능별 패널 생성 및 메인 패널에 추가
        add(createContractInfoPanel(), "growx, wrap"); // 가로로 꽉 채우고 줄바꿈
        add(createAccountInputPanel(pmp), "grow");      // 가로/세로로 꽉 채움
        

    }

    /**
     * 보험 계약 및 등록 계좌 상세 정보 패널을 생성합니다.
     */
    private JPanel createContractInfoPanel() {
        // 패널 레이아웃: 4열 구성, 라벨 컬럼은 기본 크기, 값 컬럼은 늘어남
        JPanel panel = new JPanel(new MigLayout("wrap 4, fillx", "[][grow][][grow]"));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createTitledBorder("보험 계약 상세 정보"));
        
        // 값 표시용 라벨들 초기화
        valProductName = createValueLabel(); 
        valContractId = createValueLabel();
        valStartDate = createValueLabel(); 
        valEndDate = createValueLabel();
        valPaymentType = createValueLabel(); 
        valPremium = createValueLabel();


        // MigLayout은 컴포넌트와 제약조건을 번갈아 추가하는 직관적인 방식 사용
        panel.add(new JLabel("보험상품명"));
        panel.add(valProductName, "growx");
        panel.add(new JLabel("계약번호"));
        panel.add(valContractId, "growx");

        panel.add(new JLabel("가입일"));
        panel.add(valStartDate, "growx");
        panel.add(new JLabel("만기일"));
        panel.add(valEndDate, "growx");

        panel.add(new JLabel("납입방법"));
        panel.add(valPaymentType, "growx");
        panel.add(new JLabel("보험료"));
        panel.add(valPremium, "growx");


        // 등록 계좌 정보
        lblRegisteredBank = new JLabel("등록은행");
        valRegisteredBank = createValueLabel();
        lblRegisteredAccount = new JLabel("등록계좌");
        valRegisteredAccount = createValueLabel();
        lblNoAccountInfo = new JLabel("현재 등록된 계좌정보가 없습니다.", SwingConstants.LEFT);
        lblNoAccountInfo.setForeground(Color.RED);
        
        panel.add(lblRegisteredBank);
        panel.add(valRegisteredBank, "growx");
        panel.add(lblRegisteredAccount);
        panel.add(valRegisteredAccount, "growx");
        
        // 정보 없음 메시지는 조건부 노출을 위해 별도로 추가해 둠
        panel.add(lblNoAccountInfo, "skip 1, span 3, growx, hidemode 3"); // 1칸 건너뛰고 3칸 병합, 안 보일 때 공간도 차지하지 않음

        return panel;
    }

    /**
     * 자동이체 계좌 정보 입력 패널을 생성합니다.
     */
    private JPanel createAccountInputPanel(PaymentMainPanel pmp) {
    	JPanel panel = new JPanel(new MigLayout("fillx", "[][grow][][grow][][grow]"));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createTitledBorder("자동이체 계좌 변경/신규 등록"));

        cbBank = new JComboBox<>(BANKLIST);
        cbBank.setPreferredSize(new Dimension(500, 30));
        tfAccountNumber = new JTextField();
        tfAccountNumber.setPreferredSize(new Dimension(500, 30));
        tfPassword2Digits = new JPasswordField(2);
        tfPassword2Digits.setEchoChar('●');
        tfPassword2Digits.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        tfPassword2Digits.setPreferredSize(new Dimension(100, 30));
        tfPassword2Digits.setMargin(new Insets(0, 5, 5, 0));
		cnbtn = new CardNavButton("이전", this.pmp, "AutoPayment1");
        regiBtn = new PaymentDefaultButton("등록");
        regiBtn.setEnabled(false); // 처음엔 비활성화
        regiBtn.addActionListener(e -> {
            String selectedBank = (String) cbBank.getSelectedItem();
            String accountNumber = tfAccountNumber.getText();
            String password = new String(tfPassword2Digits.getPassword());
            System.out.println(selectedBank);
            System.out.println(accountNumber);
            if(AccountRegistrator.register(Session.getCustomer(), Integer.valueOf(selectedData[2]), accountNumber, selectedBank)) {
                pmp.showCard("AutoPayment3");
            } else {
            	JOptionPane.showMessageDialog(panel,
                    "계좌번호가 유효하지않습니다. 다시입력해주세요",
                    "등록 실패",
                    JOptionPane.WARNING_MESSAGE);
            }
        });

        // 입력 필드 상태 감시 리스너 등록
        DocumentListener inputListener = new DocumentListener() {
            private void checkInputFields() {
                String accountNumber = tfAccountNumber.getText().trim();
                String password = new String(tfPassword2Digits.getPassword()).trim();
                boolean allFilled = !accountNumber.isEmpty() && password.length() == 2;
                regiBtn.setEnabled(allFilled);
            }

            public void insertUpdate(DocumentEvent e) { checkInputFields(); }
            public void removeUpdate(DocumentEvent e) { checkInputFields(); }
            public void changedUpdate(DocumentEvent e) { checkInputFields(); }
        };

        tfAccountNumber.getDocument().addDocumentListener(inputListener);
        tfPassword2Digits.getDocument().addDocumentListener(inputListener);

        // 컴포넌트 추가 (예시, MigLayout 기준)
        panel.add(new JLabel("은행"));
        panel.add(cbBank, "wrap");
        panel.add(new JLabel("계좌번호"));
        panel.add(tfAccountNumber, "wrap");
        panel.add(new JLabel("비밀번호 앞 2자리"));
        panel.add(tfPassword2Digits, "wrap");	
       

        panel.add(cnbtn, "span, split 2, center, gapright 20");
        panel.add(regiBtn);

        return panel;
        
        
    }
    
    private JLabel createValueLabel() {
        JLabel label = new JLabel();
        label.setFont(VALUE_FONT);
        label.setForeground(Color.DARK_GRAY);
        return label;
    }
    
    public void displayContractInfo(String[] contractData) {
    	
    	if (contractData != null) {
        valProductName.setText(contractData[1]);
        valContractId.setText(contractData[2]);
        valStartDate.setText(contractData[3]);
        valEndDate.setText(contractData[4]);
        valPaymentType.setText(contractData[5]);
        valPremium.setText(String.format("%,d원", Integer.valueOf(contractData[6]))); 
    	}
    }
    
    public void displayRegisteredAccount(Integer contract_id) {
    	try(Connection conn = InsuranceTeamConnector.getConnection()) {
    		AutoPaymentModel account = AutoPaymentDAO.getAutoPaymentsByContId(contract_id, conn);
        	String bank = account.getBank_name();
        	String accountNumber = account.getBank_account();
        	boolean hasAccount = bank != null && !bank.trim().isEmpty() &&
                    accountNumber != null && !accountNumber.trim().isEmpty();

            
            lblRegisteredBank.setVisible(true);
            valRegisteredBank.setVisible(hasAccount);
            lblRegisteredAccount.setVisible(true);
            valRegisteredAccount.setVisible(hasAccount);
            lblNoAccountInfo.setVisible(!hasAccount);

            if (hasAccount) {
                valRegisteredBank.setText(bank);
                valRegisteredAccount.setText(accountNumber);
            }
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    
    public void setSelectedData(String[] selectedData) {
		this.selectedData = selectedData;
	}
}