package customer.contract.gui;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import common.method.ButtonPainter;
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
	private Double monthlyPremiumInput;
	private Double monthlyPremiumOutput;
	private int year;
	private int month;
	private int day;
	private LocalDate birthDate;
	private Integer age;
	private String gender = "F";
	
	private JLabel title;
	private JTextField textField;
	private JTextArea textArea;
	private JTextField textField_birth;
	private JButton btnNewButton_1;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	
	public CalculatePremiumPanel(ContractMainPanel contractMP) {
		this.contractMP = contractMP;
		setLayout(null);
		
		title = new JLabel("타이틀");
		title.setFont(new Font("Dialog", Font.BOLD, 32));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(513, 24, 537, 56);
		add(title);
		
		/// FIXME: 패널
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBounds(523, 103, 512, 510);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("예상 보험료");
		lblNewLabel.setBounds(67, 41, 385, 49);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField = new JTextField();
		textField.setBounds(67, 88, 385, 71);
		panel_1.add(textField);
		textField.setBackground(OurColors.SELECTED);
		
		textField.setFont(new Font("Dialog", Font.BOLD, 20));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
	
		rdbtnMale = new JRadioButton("남성");
		rdbtnMale.setBackground(SystemColor.controlHighlight);
		rdbtnMale.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnMale.setBounds(97, 222, 64, 49);
		panel_1.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("여성");
		rdbtnFemale.setBackground(SystemColor.controlHighlight);
		rdbtnFemale.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnFemale.setBounds(170, 222, 64, 49);
		panel_1.add(rdbtnFemale);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMale);
		group.add(rdbtnFemale);
		
		
		textField_birth = new JTextField();
		textField_birth.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_birth.setBounds(97, 291, 184, 21);
		textField_birth.setColumns(8);
		textField_birth.setText("생년월일 8자리");
		textField_birth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_birth.setText("");				
			}
		});
		panel_1.add(textField_birth);
		
		updateProduct();
		addVerificationButton(panel_1);
		addBackButton(panel_1);
		addConfirmButton(panel_1);
	}
	
	ProductModel getProduct(String product_name) {
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
			return ProductDAO.getProductByName(product_name, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	void updateProduct() {
		product = getProduct(SelectedProductName.getProduct_name());
		if (product != null) {
			title.setText(String.format("%s [보험료계산]", product.getProductName()));
			monthlyPremiumInput = product.getBasePremium();
			monthlyPremiumOutput = monthlyPremiumInput;
			textField.setText(String.format("월 %,.0f원", monthlyPremiumOutput));
		}
	}
	
	void addVerificationButton(JPanel panel) {
		btnNewButton_1 = new JButton("확인");
		btnNewButton_1.setBounds(333, 292, 97, 23);
		btnNewButton_1.addActionListener(e -> {
			try {
				String birth = textField_birth.getText();
				this.year = Integer.parseInt(birth.substring(0,4));
				this.month = Integer.parseInt(birth.substring(4,6));
				this.day = Integer.parseInt(birth.substring(6,8));
				this.birthDate = LocalDate.of(year, month, day);
				
				this.age = CalculateAge.CalculateAge(birthDate);
				if (age < 0 || age > 120) { throw new Exception("생년월일을 확인해주세요."); }
				monthlyPremiumOutput = CalculatePremium.CalculatePremiumByAge(monthlyPremiumInput, age);
				
				setSelectedGender();
				if (gender.equals("M")) monthlyPremiumOutput *= 1.1;
				
				textField.setText(String.format("월 %,.0f원", monthlyPremiumOutput));
				calculated = true;
				
				JOptionPane.showMessageDialog(this, "보험료가 계산되었습니다.");
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "잘못된 입력입니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		});
		panel.add(btnNewButton_1);
	}
	
	private void setSelectedGender() {
	    if (rdbtnMale.isSelected()) this.gender = "M";
	    else if (rdbtnFemale.isSelected()) this.gender = "F";
	}
	
	private void addBackButton(JPanel panel) {
		JButton btnNewButton = new JButton("이전");
		ButtonPainter.stylePrimaryButtonGray(btnNewButton, 16);
		btnNewButton.setBounds(67, 395, 184, 59);
		
		btnNewButton.addActionListener(e -> {
			setPanelToDefault();
			contractMP.ShowCard(contractMP.cardNames[1]);
		});
		panel.add(btnNewButton);
	}
	
	private void addConfirmButton(JPanel panel) {
		JButton btnNewButton_2 = new JButton("다음");
		ButtonPainter.stylePrimaryButtonCarrot(btnNewButton_2, 16);
		btnNewButton_2.setBounds(273, 395, 179, 59);
		
		btnNewButton_2.addActionListener(e -> {
			if (!calculated) {
				JOptionPane.showMessageDialog(this, "성별 및 생년월일을 입력하여 월보험료를 계산해주세요."
						, "알림", JOptionPane.WARNING_MESSAGE);
			} else {
				setPanelToDefault();
				contractMP.getContractInfo().setInsuredBirth(birthDate);
			    contractMP.getContractInfo().setInsuredGender(gender);
			    contractMP.getContractInfo().setInsuredAge(age);
			    contractMP.getContractInfo().setPremium(monthlyPremiumOutput);
				contractMP.ShowCard(contractMP.cardNames[3]);
			}
		});
		panel.add(btnNewButton_2);
	}
	
	void setPanelToDefault() {
		updateProduct();
		calculated = false;
		textField_birth.setText("");
	}
}



