package employee.product.view.center;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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

import common.database.dao.ProductDAO;
import common.database.model.ProductModel;
import common.method.InsuranceTeamConnector;
import employee.product.model.ProductInfoModel;

public class EditPanelCenter extends JPanel {
	private DefaultTableModel tableModel;
	
	public JTextField productNameField, joinLimitLowField, joinLimitHighField, basePremiumField, premiumConstantField;
	public JTextField productIdField;
	public JTextField termsNameField;
	public JTextField manualNameField;
	
	public JTextField coverNameField;
	public JTextArea coverDeatailField;
	
	public JTextField divisionField;
    public JSpinner joinAgeLowField ,joinAgeHighField;
    public JButton termsBrowseButton, manualBrowseButton, imageUploadButton, addCoverButton, delCoverButton;

    public JScrollPane tableScroll;
    public JScrollPane coverTextScroll;
    public JTable coverageTable;
    
	public File termAndConditions;
	public File productManual;
	public JLabel imagePreview;
	public int productId;
	ProductModel product;
	
	public EditPanelCenter() {
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
        productIdField = new JTextField(20);
        divisionField = new JTextField(20);
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
        imagePreview = new JLabel("");
        addCoverButton = new JButton("보장내역 추가");
        delCoverButton = new JButton("선택한 항목 지우기");
        coverageTable = new JTable(tableModel);
        tableScroll = new JScrollPane(coverageTable);
        coverNameField = new JTextField(8);
        coverDeatailField = new JTextArea(15,8);
        coverTextScroll = new JScrollPane(coverDeatailField);

        // 레이블과 필드를 행으로 추가
        addRow(this, gbc, row++, "상품 ID:", productIdField);
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
        

        
        
        productIdField.setFocusable(false);
        productIdField.setEditable(false);

        divisionField.setFocusable(false);
        divisionField.setEditable(false);
        
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
        add(coverTextScroll, gbc);
        gbc.gridy = ++row;
        add(delCoverButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = row + 1;
        add(new JLabel("상품 소개 이미지:"), gbc);
        gbc.gridx = 1;
        add(imageUploadButton, gbc);
        gbc.gridy++;
        gbc.gridx = 1;
        add(imagePreview, gbc);
	}
	
	private void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, Component field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void addRowWithButton(JPanel panel, GridBagConstraints gbc, int row, String label, JTextField field, JButton button) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        field.setEditable(false);
        field.setFocusable(false);
        panel.add(field, gbc);
        gbc.gridx = 2;
        panel.add(button, gbc);
    }
    
    
}
