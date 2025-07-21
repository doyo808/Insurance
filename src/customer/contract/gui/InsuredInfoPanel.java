package customer.contract.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import common.method.ButtonPainter;
import common.method.Validators;
import customer.contract.ContractInfo;
import customer.contract.ContractMainPanel;
import customer.contract.method.CalculateAge;
import customer.contract.method.SelectedProductName;
import insuranceMain.MainFrame;

public class InsuredInfoPanel extends JPanel {
    private ContractMainPanel contractMP;
    
    private JTextField nameField;
    private JTextField juminField;
    private JTextField birthField;
    private JTextField ageField;
    private JTextField diseaseField;

    private JComboBox<String> genderCombo;
    private JComboBox<String> smokeCombo;
    private JComboBox<String> drinkCombo;
    private JComboBox<String> driveCombo;
    
    public InsuredInfoPanel(ContractMainPanel contractMP) {
        this.contractMP = contractMP;
        initUI();
    }

    private void initUI() {
        this.setBackground(new Color(0xECECEC));
        this.setLayout(null);

        // 상단 타이틀 (가운데 정렬, 크기 증가)
        String titleText = SelectedProductName.getProduct_name() + " [피보험자 정보 입력]";
        JLabel titleLabel = new JLabel(titleText);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        titleLabel.setBounds(328, 57, 900, 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel);

        // 컴포넌트 위치 중앙 맞춤 위해 시작 x 좌표 변경
        int labelX = 340;
        int fieldX = 460;
        int startY = 80;
        int gapY = 40;

        // 이름
        JLabel nameLabel = new JLabel("이름:");
        nameLabel.setBounds(590, 122, 100, 30);
        this.add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(748, 122, 220, 30);
        this.add(nameField);

        // 주민번호
        JLabel juminLabel = new JLabel("주민번호:");
        juminLabel.setBounds(590, 162, 100, 30);
        this.add(juminLabel);
        juminField = new JTextField();
        juminField.setBounds(748, 162, 220, 30);
        if (MainFrame.TEST) juminField.setText("901010-1234567");
        juminField.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		if (juminField.getText().isBlank()) juminField.setText("901010-1234567");
        	}
        	@Override
        	public void focusGained(FocusEvent e) {
        		if (juminField.getText().equals("901010-1234567")) juminField.setText("");
        	}
		});
        this.add(juminField);

        // 성별
        JLabel genderLabel = new JLabel("성별:");
        genderLabel.setBounds(590, 202, 100, 30);
        this.add(genderLabel);
        String[] genders = {"M", "F"};
        genderCombo = new JComboBox<>(genders);
        genderCombo.setBounds(748, 202, 220, 30);
        this.add(genderCombo);

        // 생년월일
        JLabel birthLabel = new JLabel("생년월일(yyyy-mm-dd):");
        birthLabel.setBounds(590, 242, 140, 30);
        this.add(birthLabel);
        birthField = new JTextField();
        birthField.setBounds(748, 242, 220, 30);
        this.add(birthField);
        
        birthField.addFocusListener(new FocusAdapter() {
    	    @Override
    	    public void focusLost(FocusEvent e) {
    	        String birthText = birthField.getText().trim();
    	        if (!birthText.isEmpty()) {
    	            try {
    	                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	                LocalDate birthDate = LocalDate.parse(birthText, dtf);
    	
    	                int age = CalculateAge.CalculateAge(birthDate);
    	                ageField.setText(String.valueOf(age));
    	            } catch (DateTimeParseException ex) {
    	                // 잘못된 날짜 형식인 경우, 나이 필드 비워두거나 경고를 줄 수 있음
    	                ageField.setText("");
    	                JOptionPane.showMessageDialog(InsuredInfoPanel.this
    	                		, "생년월일 형식이 올바르지 않습니다. yyyy-MM-dd 형식이어야 합니다."
    	                		, "입력 오류", JOptionPane.ERROR_MESSAGE);
    	            }
    	        }
    	    }
        });
        
        
        // 나이
        JLabel ageLabel = new JLabel("나이:");
        ageLabel.setBounds(590, 282, 100, 30);
        this.add(ageLabel);
        ageField = new JTextField();
        ageField.setBounds(748, 282, 220, 30);
        this.add(ageField);

        // 흡연여부
        JLabel smokeLabel = new JLabel("흡연여부:");
        smokeLabel.setBounds(590, 322, 100, 30);
        this.add(smokeLabel);
        String[] yesNo = {"Y", "N"};
        smokeCombo = new JComboBox<>(yesNo);
        smokeCombo.setBounds(748, 322, 220, 30);
        this.add(smokeCombo);

        // 음주여부
        JLabel drinkLabel = new JLabel("음주여부:");
        drinkLabel.setBounds(590, 362, 100, 30);
        this.add(drinkLabel);
        drinkCombo = new JComboBox<>(yesNo);
        drinkCombo.setBounds(748, 362, 220, 30);
        this.add(drinkCombo);

        // 운전상태
        JLabel driveLabel = new JLabel("운전상태:");
        driveLabel.setBounds(590, 402, 100, 30);
        this.add(driveLabel);
        String[] driveStatus = {"0: 안함", "1: 소형차", "2: 이륜차", "3: 화물차"};
        driveCombo = new JComboBox<>(driveStatus);
        driveCombo.setBounds(748, 402, 220, 30);
        this.add(driveCombo);

        // 병력(첨부파일 경로)
        JLabel diseaseLabel = new JLabel("병력(첨부파일 경로):");
        diseaseLabel.setBounds(590, 442, 140, 30);
        this.add(diseaseLabel);
        diseaseField = new JTextField();
        diseaseField.setBounds(748, 442, 220, 30);
        this.add(diseaseField);
        
        ContractInfo ci = contractMP.getContractInfo();
        if (ci.getInsuredGender() != null) {
            genderCombo.setSelectedItem(ci.getInsuredGender());
        }

        if (ci.getInsuredBirth() != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            birthField.setText(ci.getInsuredBirth().format(dtf));
            
            // 나이 계산 및 설정
            int age = CalculateAge.CalculateAge(ci.getInsuredBirth());
            ageField.setText(String.valueOf(age));
        }
        
        addNextPrevButtons();
    }
    
    
    /// FIXME: 다음/이전버튼 추가
    void addNextPrevButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(445, 557, 700, 50);

        JButton prevButton = new JButton("이전");
        ButtonPainter.stylePrimaryButtonGray(prevButton, 16);
        prevButton.setPreferredSize(new java.awt.Dimension(267, 33));
        prevButton.addActionListener(e -> {
        	clearInputs();
            contractMP.ShowCard(contractMP.cardNames[3]);
        });

        JButton nextButton = new JButton("다음");
        ButtonPainter.stylePrimaryButtonCarrot(nextButton, 16);
        nextButton.setPreferredSize(new java.awt.Dimension(288, 33));
        addNextAction(nextButton);

        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        add(buttonPanel);
    }
    
    
    void addNextAction(JButton btn) {
    	
        btn.addActionListener(e -> {
        		
    		String name = nameField.getText().trim();
            String jumin = juminField.getText().trim();
            String birth = birthField.getText().trim();
            String ageStr = ageField.getText().trim();

            StringBuilder errorMsg = new StringBuilder();

            // 이름
            if (name.isEmpty()) {
                errorMsg.append("이름을 입력해주세요.\n");
            }
            
            if (!Validators.isValidName(name)) {
            	errorMsg.append("이름을 확인하세요.\n");
            }
            
            // 주민번호: 6자리-7자리 형식
            if (!jumin.matches("\\d{6}-\\d{7}")) {
                errorMsg.append("주민번호 형식이 올바르지 않습니다. 예: 901231-1234567\n");
            }

            // 생년월일
            if (!birth.matches("\\d{4}-\\d{2}-\\d{2}")) {
                errorMsg.append("생년월일 형식은 yyyy-MM-dd 이어야 합니다.\n");
            }

            // 나이
            try {
                int age = Integer.parseInt(ageStr);
                if (age < 0 || age > 120) {
                    errorMsg.append("나이는 0~120 사이여야 합니다.\n");
                }
            } catch (NumberFormatException ex) {
                errorMsg.append("나이는 숫자로 입력해주세요.\n");
            }

            // 결과 처리
            if (errorMsg.length() > 0) {
                JOptionPane.showMessageDialog(this, errorMsg.toString(), "입력 오류", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(this, "입력이 확인되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
            }
     
        	ContractInfo ci = contractMP.getContractInfo();
            ci.setInsuredName(nameField.getText().trim());
            ci.setInsuredJumin(juminField.getText().trim());
            ci.setInsuredGender((String) genderCombo.getSelectedItem());

            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate birthDate = LocalDate.parse(birthField.getText().trim(), dtf);
                ci.setInsuredBirth(birthDate);
            } catch (Exception ex) {
                ci.setInsuredBirth(null);  // 혹은 기본값 처리
            }

            try {
                int age = Integer.parseInt(ageField.getText().trim());
                ci.setInsuredAge(age);
            } catch (NumberFormatException ex) {
                ci.setInsuredAge(0); // 기본값 처리
            }

            ci.setInsuredSmoke((String) smokeCombo.getSelectedItem());
            ci.setInsuredDrink((String) drinkCombo.getSelectedItem());

            // driveCombo문자열에서 숫자만 추출
            String driveStr = (String) driveCombo.getSelectedItem();
            int driveStatus = 0;
            if (driveStr != null) {
                driveStatus = Integer.parseInt(driveStr.split(":")[0].trim());
            }
            ci.setInsuredDrive(driveStatus);
            ci.setInsuredDisease(diseaseField.getText().trim());
            
            clearInputs();
            contractMP.ShowCard(contractMP.cardNames[5]);
        });
    }

    /// FIXME: 입력값 초기화
    void clearInputs() {
    	  // 텍스트 필드 초기화
        nameField.setText("");
        juminField.setText("");
        birthField.setText("");
        ageField.setText("");
        diseaseField.setText("");

        // 콤보박스 초기화 (index 0으로 설정)
        genderCombo.setSelectedIndex(0); // M
        smokeCombo.setSelectedIndex(0);  // Y
        drinkCombo.setSelectedIndex(0);  // Y
        driveCombo.setSelectedIndex(0);  // 0: 안함
    }
}
