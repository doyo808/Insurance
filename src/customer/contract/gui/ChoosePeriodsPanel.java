
package customer.contract.gui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import common.gui.OurColors;
import common.method.ButtonPainter;
import customer.contract.ContractInfo;
import customer.contract.ContractMainPanel;
import customer.contract.method.SelectedProductName;

public class ChoosePeriodsPanel extends JPanel {
	// 상품에 따라 기간선택지가 달라져야 하지만 선택지를 고정해놓은 상태
	private ContractMainPanel contractMP;
	private ContractInfo ci;
	private int cardNumber = 3;
	
	private List<JButton> coverageButtons = new ArrayList<>();
	private List<JButton> paymentButtons = new ArrayList<>();
	
	public ChoosePeriodsPanel(ContractMainPanel contractMP) {
		this.contractMP = contractMP;
		ci = contractMP.getContractInfo();
		
		setLayout(null);
		addComponents();
	}
	
	void addComponents() {
		JLabel title = new JLabel(SelectedProductName.getProduct_name() + " [기간선택]");
		title.setFont(new Font("굴림", Font.BOLD, 32));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(457, 10, 537, 56);
		add(title);
		
		/// FIXME: 보장 패널
		JPanel panel = new JPanel();
		panel.setBounds(377, 107, 669, 167);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("보장");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(129, 21, 203, 57);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("기간");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(339, 21, 203, 57);
		panel.add(lblNewLabel_1);
		
		JButton btn80 = new JButton("80세");
		btn80.setFont(new Font("굴림", Font.BOLD, 18));
		btn80.setBackground(OurColors.UNSELECTED);
		btn80.setBounds(52, 88, 175, 62);
		panel.add(btn80);
		
		JButton btn90 = new JButton("90세");
		btn90.setFont(new Font("굴림", Font.BOLD, 18));
		btn90.setBackground(OurColors.UNSELECTED);
		btn90.setBounds(248, 88, 175, 62);
		panel.add(btn90);
		
		JButton btn100 = new JButton("100세");
		btn100.setFont(new Font("굴림", Font.BOLD, 18));
		btn100.setBackground(OurColors.SELECTED);
		btn100.setBounds(447, 88, 175, 62);
		panel.add(btn100);
		
		coverageButtons.add(btn80);
		coverageButtons.add(btn90);
		coverageButtons.add(btn100);
		
		for (int i = 0; i < coverageButtons.size(); i++) {
			final int index = i;
			
			coverageButtons.get(index).addActionListener(e -> {
				for (int j = 0; j < coverageButtons.size(); j++) {
					if (j == index) {
						coverageButtons.get(j).setBackground(OurColors.SELECTED);
					} else {
						coverageButtons.get(j).setBackground(OurColors.UNSELECTED);
					}
				}
			});
		}
		
		
		/// FIXME: 납입 패널
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(377, 308, 669, 167);
		add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("납입");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 26));
		lblNewLabel_2.setBounds(129, 21, 203, 57);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("기간");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(339, 21, 203, 57);
		panel_1.add(lblNewLabel_1_1);
		
		JButton btn10 = new JButton("10년");
		btn10.setFont(new Font("굴림", Font.BOLD, 18));
		btn10.setBackground(OurColors.UNSELECTED);
		btn10.setBounds(34, 80, 142, 62);
		panel_1.add(btn10);
		
		JButton btn15 = new JButton("15년");
		btn15.setFont(new Font("굴림", Font.BOLD, 18));
		btn15.setBackground(OurColors.SELECTED);
		btn15.setBounds(188, 80, 142, 62);
		panel_1.add(btn15);
		
		JButton btn20 = new JButton("20년");
		btn20.setFont(new Font("굴림", Font.BOLD, 18));
		btn20.setBackground(OurColors.UNSELECTED);
		btn20.setBounds(340, 80, 142, 62);
		panel_1.add(btn20);
		
		JButton btn30 = new JButton("30년");
		btn30.setFont(new Font("굴림", Font.BOLD, 18));
		btn30.setBackground(OurColors.UNSELECTED);
		btn30.setBounds(494, 80, 142, 62);
		panel_1.add(btn30);
		
		paymentButtons.add(btn10);
		paymentButtons.add(btn15);
		paymentButtons.add(btn20);
		paymentButtons.add(btn30);
		
		for (int i = 0; i < paymentButtons.size(); i++) {
			final int index = i;
			
			paymentButtons.get(index).addActionListener(e -> {
				for (int j = 0; j < paymentButtons.size(); j++) {
					if (j == index) {
						paymentButtons.get(j).setBackground(OurColors.SELECTED);
					} else {
						paymentButtons.get(j).setBackground(OurColors.UNSELECTED);
					}
				}
			});
		}
		addBackButton();
		addConfirmButton();
	}
	
	private void addBackButton() {
		JButton btnNewButton = new JButton("이전");
		ButtonPainter.stylePrimaryButtonGray(btnNewButton, 16);
		btnNewButton.setBounds(442, 543, 267, 33);
		
		btnNewButton.addActionListener(e -> {
			contractMP.ShowCard(contractMP.cardNames[cardNumber - 1]);
		});
		add(btnNewButton);
	}
	
	private void addConfirmButton() {
		JButton btnNewButton_2 = new JButton("다음");
		ButtonPainter.stylePrimaryButtonCarrot(btnNewButton_2, 16);
		btnNewButton_2.setBounds(721, 543, 288, 33);
		
		btnNewButton_2.addActionListener(e -> {
			updateContractInfo();
			contractMP.ShowCard(contractMP.cardNames[cardNumber + 1]);
		});
		add(btnNewButton_2);
	}

	private void updateContractInfo() {
		for (JButton j : coverageButtons) {
			if (j.getBackground() == OurColors.SELECTED) {
				Integer period = Integer.parseInt(j.getText().substring(0,2));
				ci.setProduct_coverage_period(period);
			}
		}
		
		for (JButton j : paymentButtons) {
			if (j.getBackground() == OurColors.SELECTED) {
				Integer period = Integer.parseInt(j.getText().substring(0,2));
				ci.setProduct_payment_period(period);
			}
		}
	}
	
	
}