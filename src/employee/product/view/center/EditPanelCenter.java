package employee.product.view.center;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import common.database.dao.ProductDAO;
import common.database.model.ProductModel;
import common.method.InsuranceTeamConnector;
import employee.product.model.ProductInfoModel;

public class EditPanelCenter extends JPanel {
	
	public JTextField productNameField, joinLimitLowField, joinLimitHighField, basePremiumField, premiumConstantField;
	public JTextField productIdField;
	public JTextField termsNameField;
	public JTextField manualNameField;
	public JTextField divisionField;
//	public JComboBox<String> divisionField;
    public JSpinner joinAgeLowField ,joinAgeHighField;
    public JButton termsBrowseButton, manualBrowseButton, imageUploadButton;

	public File termAndConditions;
	public File productManual;
	public File imageFile;
	public JLabel imagePreview;
//	public ImageIcon image;
	public int productId;
	ProductModel product;
	
	public EditPanelCenter() {
		setLayout(new GridBagLayout());
		setAutoscrolls(true);
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        try (Connection conn = InsuranceTeamConnector.getConnection()){
        	product = ProductDAO.getProduct(productId, conn);
//			System.out.println("DB상품정보 불러오기성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // 각 입력 필드 정의
        productIdField = new JTextField(20);
        divisionField = new JTextField(20);
//        divisionField = new JComboBox<>(new String[]{"전체", "건강(종합)", "유병자(간편)", "운전자", "주택화재", "자녀(어린이)", "실손", "재물", "펫/기타"});
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
        imagePreview = new JLabel("");

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
        
        productIdField.setText("");
        productIdField.setFocusable(false);
        productIdField.setEditable(false);
        
        divisionField.setText("");
        divisionField.setFocusable(false);
        divisionField.setEditable(false);
        
        productNameField.setText("");
        joinAgeLowField.setValue(0);
        joinAgeHighField.setValue(0);
        joinLimitLowField.setText("");
        joinLimitHighField.setText("");
        termsNameField.setText("");
        manualNameField.setText("");
        basePremiumField.setText("");
        premiumConstantField.setText("");
        
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("상품 소개 이미지:"), gbc);
        gbc.gridx = 1;
        add(imageUploadButton, gbc);
        gbc.gridy++;
        gbc.gridx = 1;
        add(imagePreview, gbc);
        
        imagePreview.setIcon(null);
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
