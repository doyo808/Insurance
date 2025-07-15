package customer.claim.gui.newClaim;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import common.database.model.NewClaimDataModel;
import customer.claim.gui.TitlePanel;

public class ClaimTargetPanel extends JPanel {
	
	private JPanel parentCardPanel;
	

	public ClaimTargetPanel(JPanel parentCardPanel, NewClaimDataModel claimData) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) parentCardPanel.getLayout();
		setLayout(new BorderLayout());

		TitlePanel title = new TitlePanel("청구대상 선택");
		add(title, BorderLayout.NORTH);

		// 중앙 패널: 라디오 버튼과 입력창
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setVisible(true);

		// 라디오 버튼
		JPanel radioButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		JRadioButton customerChButton = new JRadioButton(" 계약자(본인)");
		JRadioButton insuredChButton = new JRadioButton(" 다른사람");

		ButtonGroup chButtonGroup = new ButtonGroup();
		chButtonGroup.add(customerChButton);
		chButtonGroup.add(insuredChButton);

		radioButtonPanel.add(customerChButton);
		radioButtonPanel.add(insuredChButton);
		centerPanel.add(radioButtonPanel, BorderLayout.NORTH);

		// 입력 폼 (다른 사람일 경우)
		JPanel insuredInfoPanel = new JPanel(new GridBagLayout());
		insuredInfoPanel.setBorder(BorderFactory.createTitledBorder("다른 사람 정보 입력"));
		insuredInfoPanel.setPreferredSize(new Dimension(400, 200));
		insuredInfoPanel.setVisible(false);

		centerPanel.add(insuredInfoPanel, BorderLayout.CENTER);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new java.awt.Insets(30, 30, 30, 10);
		gbc.anchor = GridBagConstraints.WEST;

		JLabel nameLabel = new JLabel("이름:");
		JTextField nameField = new JTextField(10);

//		nameField.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				String inputName = nameField.getText();
//				if (!Validators.isValidName(inputName)) {
//					JOptionPane.showMessageDialog(insuredInfoPanel, "이름이 유효하지 않습니다.", "안내", JOptionPane.WARNING_MESSAGE);
//					nameField.setText("");
//				}
//			}
//		});

		JLabel perNumLabel = new JLabel("주민등록번호:");
		JLabel dashLabel = new JLabel("-");
		JTextField perNumFieldF = new JTextField(6);
		JTextField perNumFieldB = new JTextField(7);
		
//		perNumField.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				String inputPersonal_id = perNumField.getText();
//				if (!Validators.isValidPersonal_id(inputPersonal_id)) {
//					JOptionPane.showMessageDialog(insuredInfoPanel, "주민등록번호가 유효하지 않습니다.", "안내", JOptionPane.WARNING_MESSAGE);
//					perNumField.setText("");
//				}
//			}
//		});

		JLabel phoneNumLabel = new JLabel("휴대폰 번호:");
		JTextField phoneNumField = new JTextField(10);

//		phoneNumField.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				String inputPersonal_id = perNumField.getText();
//				if (!Validators.isValidPhoneNumber(inputPersonal_id)) {
//					JOptionPane.showMessageDialog(insuredInfoPanel, "전화번호가 유효하지 않습니다.", "안내", JOptionPane.WARNING_MESSAGE);
//					perNumField.setText("");
//				}
//			}
//		});
		

		gbc.gridx = 0;
		gbc.gridy = 0;
		insuredInfoPanel.add(nameLabel, gbc);
		gbc.gridx = 1;
		insuredInfoPanel.add(nameField, gbc);

		

		gbc.gridx = 0;
		gbc.gridy = 2;
		insuredInfoPanel.add(phoneNumLabel, gbc);
		gbc.gridx = 1;
		insuredInfoPanel.add(phoneNumField, gbc);
		
		JPanel perNumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0)); // 간격 좁게
		perNumPanel.add(perNumFieldF);
		perNumPanel.add(dashLabel);
		perNumPanel.add(perNumFieldB);
		
		// 주민번호 라벨 + 입력패널
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(5, 30, 5, 5); // 위, 좌, 아래, 우
		insuredInfoPanel.add(perNumLabel, gbc);

		gbc.gridx = 1;
		insuredInfoPanel.add(perNumPanel, gbc);
		
		customerChButton.addActionListener(e -> {
			insuredInfoPanel.setVisible(false);
			revalidate();
			repaint();
		});

		insuredChButton.addActionListener(e -> {
			insuredInfoPanel.setVisible(true);
			revalidate();
			repaint();
		});
		
		// 하단 버튼
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
		JButton previousButton = new JButton("이전");
		JButton nextButton = new JButton("다음");

		previousButton.addActionListener((e) -> {
			cl.show(parentCardPanel, "ClaimFirstPanel");
			chButtonGroup.clearSelection();
			nameField.setText("");
			phoneNumField.setText("");
			perNumFieldB.setText("");
			insuredInfoPanel.setVisible(false);
		});

		nextButton.addActionListener((e) -> {
			if (chButtonGroup.getSelection() == null) {
				JOptionPane.showMessageDialog(this, "청구대상을 선택해주세요.", "안내", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (insuredChButton.isSelected()) {
				if ((nameField.getText().trim().isBlank()) || perNumFieldF.getText().trim().isBlank()
						|| perNumFieldB.getText().trim().isBlank() || phoneNumField.getText().trim().isBlank()) {
					JOptionPane.showMessageDialog(this, "모든 정보를 입력해주세요", "안내", JOptionPane.WARNING_MESSAGE);
					return;
				}

				claimData.setSelf(false);
				claimData.setName(nameField.getText());
				claimData.setPersonalId(perNumFieldF.getText() + "-" + perNumFieldB.getText());
				claimData.setPhoneNumber(phoneNumField.getText());
			} else if (customerChButton.isSelected()) {
				claimData.setSelf(true); // 로그인 정보에서 값을 가져와 저장한다.
			}

//			System.out.println(claimData.toString()); // 디버깅용

			cl.show(parentCardPanel, "AccidentDatePanel");
		});

		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}
}
