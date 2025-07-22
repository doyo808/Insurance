package employee.product.view.center;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class RegistPanelCenter extends JPanel {
	
	private DefaultTableModel tableModel;
	
	public JTextField productNameField, joinLimitLowField, joinLimitHighField, basePremiumField, premiumConstantField;
//	public JTextField productIdField;
	public JTextField termsNameField;
	public JTextField manualNameField;
	public JTextField coverNameField;
	public JTextArea coverDeatailField;
	public JComboBox<String> divisionField;
    public JSpinner joinAgeLowField ,joinAgeHighField;
    public JButton termsBrowseButton, manualBrowseButton, imageUploadButton, addCoverButton, delCoverButton;
    
    public JScrollPane tableScroll;
    public JTable coverageTable;
    
	public File termAndConditions;
	public File productManual;
	public JLabel imagePreview;
//	public ImageIcon image;
	
	public RegistPanelCenter() {
		setLayout(new GridBagLayout());
		setAutoscrolls(true);
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 테이블 헤더 미리 정의
        tableModel = new DefaultTableModel(new Object[]{"담보명","보장내용"}, 0);
        
        int row = 0;

        // 각 입력 필드 정의
        JLabel text = new JLabel("모든항목 필수 입력");
//        productIdField = new JTextField(20);
        divisionField = new JComboBox<>(new String[]{"전체", "건강(종합)", "유병자(간편)", "운전자", "주택화재", "자녀(어린이)", "실손", "재물", "펫/기타"});
        //JComboBox는 getSelectedItem() 메서드로 안에 담긴 정보를 가져올수있음
        productNameField = new JTextField(20);
        joinAgeLowField = new JSpinner(new SpinnerNumberModel(18, 0, 120, 1));
        joinAgeHighField = new JSpinner(new SpinnerNumberModel(65, 0, 120, 1));
        joinLimitLowField = new JTextField(20);
        joinLimitHighField = new JTextField(20);
        termsNameField = new JTextField(20);
        termsBrowseButton = new JButton("찾기");
        manualNameField = new JTextField(20);
    	manualBrowseButton = new JButton("찾기");
    	basePremiumField = new JTextField(20);
    	premiumConstantField = new JTextField(20);
    	imageUploadButton = new JButton("이미지 업로드");
        imagePreview = new JLabel("이미지 미리보기");
        coverageTable = new JTable(tableModel);
        tableScroll = new JScrollPane(coverageTable);
        addCoverButton = new JButton("보장내역 추가");
        delCoverButton = new JButton("선택한 항목 지우기");
        coverNameField = new JTextField(8);
        coverDeatailField = new JTextArea(15,8);

        // 레이블과 필드를 행으로 추가
        text.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        gbc.gridx = 1;
        gbc.gridy = row++;
        add(text, gbc);
//        addRow(this, gbc, row++, "상품 ID:", productIdField);
        addRow(this, gbc, row++, "구분:", divisionField);
        addRow(this, gbc, row++, "상품명:", productNameField);
        addRow(this, gbc, row++, "가입 나이 (최저):", joinAgeLowField);
        addRow(this, gbc, row++, "가입 나이 (최고):", joinAgeHighField);
        addRow(this, gbc, row++, "가입한도 (최저):", joinLimitLowField);
        addRow(this, gbc, row++, "가입한도 (최고):", joinLimitHighField);
        addRowWithButton(this, gbc, row++, "약관 파일:", termsNameField, termsBrowseButton);
        addRowWithButton(this, gbc, row++, "설명서 파일:", manualNameField, manualBrowseButton);
        addRow(this, gbc, row++, "기본 지급 금액:", basePremiumField);
        addRow(this, gbc, row++, "보장 상수:", premiumConstantField);
        
        // 테이블을 UI에 추가함
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridheight = 4;
        add(new JLabel("보장 내용:"), gbc);
        gbc.gridx = 1;
        add(tableScroll, gbc);
        gbc.gridx = 2;
        gbc.gridy = row;
        gbc.gridheight = 1;	// 병합한 열 초기화
        add(addCoverButton, gbc);
        gbc.gridy = ++row;
        add(coverNameField, gbc);
        gbc.gridy = ++row;
        add(coverDeatailField, gbc);
        gbc.gridy = ++row;
        add(delCoverButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = ++row;
        add(new JLabel("상품 소개 이미지:"), gbc);
        gbc.gridx = 1;
        add(imageUploadButton, gbc);
        gbc.gridy++;
        gbc.gridx = 1;
        add(imagePreview, gbc);
        
        
        termsNameField.setEditable(false);
        termsNameField.setFocusable(false);
        manualNameField.setEditable(false);
        manualNameField.setFocusable(false);
	}
	
	private void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, Component field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void addRowWithButton(JPanel panel, GridBagConstraints gbc, int row, String label, Component field, JButton button) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
        gbc.gridx = 2;
        panel.add(button, gbc);
    }
    
    public void setTable() {
    	
    	tableModel.addRow(new Object[] {});
    }
    
    public void setInitText() {
    	
    	divisionField.setSelectedItem("전체");
    	productNameField.setText("");
    	joinAgeLowField.setValue(18);
    	joinAgeHighField.setValue(65);
    	joinLimitLowField.setText("");
    	joinLimitHighField.setText("");
    	termsNameField.setText("");
    	manualNameField.setText("");
    	basePremiumField.setText("");
    	premiumConstantField.setText("");
    	imagePreview.setText("이미지 미리보기");
    	
    	termAndConditions = null;
    	productManual = null;
    	imagePreview.setIcon(null);
    }
}
