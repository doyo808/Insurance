package employee.product.view.center;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class RegistPanelCenter extends JPanel {
	
	public RegistPanelCenter() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // 각 입력 필드 정의
        JTextField productIdField = new JTextField(20);
        JComboBox<String> divisionField = new JComboBox<>(new String[]{"보험", "대출", "적금"});
        JTextField productNameField = new JTextField(20);
        JSpinner joinAgeLowField = new JSpinner(new SpinnerNumberModel(18, 0, 120, 1));
        JSpinner joinAgeHighField = new JSpinner(new SpinnerNumberModel(65, 0, 120, 1));
        JTextField joinLimitLowField = new JTextField(20);
        JTextField joinLimitHighField = new JTextField(20);
        JTextField termsPathField = new JTextField(20);
        JButton termsBrowseButton = new JButton("Browse...");
        JTextField manualPathField = new JTextField(20);
        JButton manualBrowseButton = new JButton("Browse...");
        JTextField basePremiumField = new JTextField(20);
        JTextField premiumConstantField = new JTextField(20);
        JButton imageUploadButton = new JButton("Upload Image");
        JLabel imagePreview = new JLabel("이미지 미리보기");

        // 레이블과 필드를 행으로 추가
        addRow(this, gbc, row++, "상품 ID:", productIdField);
        addRow(this, gbc, row++, "구분:", divisionField);
        addRow(this, gbc, row++, "상품명:", productNameField);
        addRow(this, gbc, row++, "가입 나이 (최저):", joinAgeLowField);
        addRow(this, gbc, row++, "가입 나이 (최고):", joinAgeHighField);
        addRow(this, gbc, row++, "가입한도 (최저):", joinLimitLowField);
        addRow(this, gbc, row++, "가입한도 (최고):", joinLimitHighField);
        addRowWithButton(this, gbc, row++, "약관 파일:", termsPathField, termsBrowseButton);
        addRowWithButton(this, gbc, row++, "설명서 파일:", manualPathField, manualBrowseButton);
        addRow(this, gbc, row++, "기본 지급 금액:", basePremiumField);
        addRow(this, gbc, row++, "보장 상수:", premiumConstantField);

        gbc.gridx = 0;
        gbc.gridy = row;
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
        panel.add(field, gbc);
        gbc.gridx = 2;
        panel.add(button, gbc);
    }
}
