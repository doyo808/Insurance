package customer.contract.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import common.database.dao.ProductDAO;
import common.database.model.ProductModel;
import common.gui.OurColors;
import common.method.InsuranceTeamConnector;
import customer.contract.ContractMainPanel;
import customer.contract.method.CalculateAge;
import customer.contract.method.CalculatePremium;
import customer.contract.method.SelectedProductName;

public class CalculatePremiumPanel extends JPanel {
	// 입력내용 맞는지 물어보는 팝업 띄우기
	private ContractMainPanel contractMP;
	private Boolean calculated = false;
	
	private ProductModel product;
	private double monthlyPremiumInput;
	private double monthlyPremiumOutput;
	private int year;
	private int month;
	private int day;
	private LocalDate birthDate;
	private int age;
	private String gender = "F";
	
	private JLabel title;
	private JTextField textField;
	private JTextArea textArea;
	private JTextField textField_Year;
	private JTextField textField_Month;
	private JTextField textField_Date;
	private JButton btnNewButton_1;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	
	public CalculatePremiumPanel(ContractMainPanel contractMP) {
		this.contractMP = contractMP;
		setLayout(null);
		
		title = new JLabel("타이틀");
		title.setFont(new Font("굴림", Font.BOLD, 32));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(457, 10, 537, 56);
		add(title);
		
		/// FIXME: 왼쪽 패널
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(158, 73, 512, 574);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("예상 보험료");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(62, 29, 385, 49);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBackground(SystemColor.controlShadow);
		
		textField.setFont(new Font("굴림", Font.BOLD, 20));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(62, 92, 385, 71);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("보장내역");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 28));
		lblNewLabel_1.setBounds(62, 181, 385, 49);
		panel.add(lblNewLabel_1);
		
		// 버그발생주의 -> 텍스트에리어 대체 컴포넌트 찾기
		JTextArea textArea = new JTextArea();
		textArea.setBackground(SystemColor.controlShadow);
		textArea.setBounds(62, 240, 385, 296);
		textArea.setText("보장내역목록");
		panel.add(textArea);
		
		/// FIXME: 오른쪽 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBounds(701, 73, 512, 574);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("생년월일 입력");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 28));
		lblNewLabel_2.setBounds(65, 211, 385, 49);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_Year = new JLabel("년");
		lblNewLabel_Year.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_Year.setBounds(78, 291, 28, 23);
		panel_1.add(lblNewLabel_Year);
		
		JLabel lblNewLabel_Month = new JLabel("월");
		lblNewLabel_Month.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_Month.setBounds(212, 291, 28, 23);
		panel_1.add(lblNewLabel_Month);
		
		JLabel lblNewLabel_Date = new JLabel("일");
		lblNewLabel_Date.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_Date.setBounds(288, 291, 17, 23);
		panel_1.add(lblNewLabel_Date);
		
		textField_Year = new JTextField();
		textField_Year.setBounds(104, 292, 81, 21);
		panel_1.add(textField_Year);
		textField_Year.setColumns(4);
		
		textField_Month = new JTextField();
		textField_Month.setColumns(2);
		textField_Month.setBounds(248, 292, 28, 21);
		panel_1.add(textField_Month);
		
		textField_Date = new JTextField();
		textField_Date.setColumns(2);
		textField_Date.setBounds(317, 292, 28, 21);
		panel_1.add(textField_Date);
		
		btnNewButton_1 = new JButton("확인");
		btnNewButton_1.setBounds(364, 291, 97, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("성별");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("굴림", Font.BOLD, 28));
		lblNewLabel_2_1.setBounds(65, 83, 385, 49);
		panel_1.add(lblNewLabel_2_1);

		rdbtnMale = new JRadioButton("남성");
		rdbtnMale.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnMale.setBounds(167, 150, 64, 23);
		panel_1.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("여성");
		rdbtnFemale.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnFemale.setBounds(272, 150, 64, 23);
		panel_1.add(rdbtnFemale);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMale);
		group.add(rdbtnFemale);
		
		addBackButton(panel_1);
		addConfirmButton(panel_1);
		
		addActions();
	}
	
	ProductModel getProduct(String product_name) {
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
			return ProductDAO.getProductByName(product_name, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateProduct() {
		product = getProduct(SelectedProductName.getProduct_name());
		if (product != null) {
			title.setText(String.format("%s [보험료계산]", product.getProductName()));
			monthlyPremiumInput = product.getBasePremium();
			monthlyPremiumOutput = monthlyPremiumInput;
			textField.setText(String.format("월 %,.0f원", monthlyPremiumOutput));
		}
	}
	
	void addActions() {
		btnNewButton_1.addActionListener(e -> {
			try {
				this.year = Integer.parseInt(textField_Year.getText());
				this.month = Integer.parseInt(textField_Month.getText());
				this.day = Integer.parseInt(textField_Date.getText());
				this.birthDate = LocalDate.of(year, month, day);

				this.age = CalculateAge.CalculateAge(birthDate);
				monthlyPremiumOutput = CalculatePremium.CalculatePremiumByAge(monthlyPremiumInput, age);
				
				setSelectedGender();
				if (gender.equals("M")) monthlyPremiumOutput *= 1.05;
				
				textField.setText(String.format("월 %,.0f원", monthlyPremiumOutput));
				calculated = true;
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "잘못된 입력입니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		});
		
		
	}
	
	private void setSelectedGender() {
	    if (rdbtnMale.isSelected()) this.gender = "M";
	    else if (rdbtnFemale.isSelected()) this.gender = "F";
	}
	
	private void addBackButton(JPanel panel) {
		JButton btnNewButton = new JButton("이전");
		btnNewButton.setBackground(OurColors.PREVIOUS_BUTTON);
		btnNewButton.setForeground(OurColors.TITLE_TEXT);
		btnNewButton.setBounds(85, 407, 153, 59);
		
		btnNewButton.addActionListener(e -> {
			calculated = false;
			contractMP.ShowCard(contractMP.cardNames[1]);
		});
		panel.add(btnNewButton);
	}
	
	private void addConfirmButton(JPanel panel) {
		JButton btnNewButton_2 = new JButton("다음");
		btnNewButton_2.setBackground(OurColors.NEXT_BUTTON);
		btnNewButton_2.setForeground(OurColors.TITLE_TEXT);
		btnNewButton_2.setBounds(286, 407, 153, 59);
		
		btnNewButton_2.addActionListener(e -> {
			if (!calculated) {
				JOptionPane.showMessageDialog(this, "성별 및 생년월일을 입력하여 월보험료를 계산해주세요."
						, "알림", JOptionPane.WARNING_MESSAGE);
			} else {
				contractMP.ShowCard(contractMP.cardNames[3]);
			}
		});
		panel.add(btnNewButton_2);
	}
}



