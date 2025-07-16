package customer.contract.gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import common.gui.OurColors;
import customer.contract.ContractMainPanel;
import customer.contract.method.SelectedProductName;

public class ChoosePeriodsPanel extends JPanel {
	// 상품에 따라 기간선택지가 달라져야 하지만 선택지를 고정해놓은 상태
	private ContractMainPanel contractMP;
	
	public ChoosePeriodsPanel(ContractMainPanel contractMP) {
		this.contractMP = contractMP;
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
		
		JButton btn100 = new JButton("100세");
		btn100.setFont(new Font("굴림", Font.BOLD, 18));
		btn100.setBackground(OurColors.UNSELECTED);
		btn100.setBounds(248, 88, 175, 62);
		panel.add(btn100);
		
		JButton btnLifeTime = new JButton("평생");
		btnLifeTime.setFont(new Font("굴림", Font.BOLD, 18));
		btnLifeTime.setBackground(OurColors.SELECTED);
		btnLifeTime.setBounds(447, 88, 175, 62);
		panel.add(btnLifeTime);
		
		JButton[] btns = {btn80, btn100, btnLifeTime};

		for (int i = 0; i < btns.length; i++) {
		    final int index = i;

		    btns[i].addActionListener(e -> {
		        for (int j = 0; j < btns.length; j++) {
		            if (j == index) {
		                btns[j].setBackground(OurColors.SELECTED);
		            } else {
		                btns[j].setBackground(OurColors.UNSELECTED);
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
		
		JButton[] btns2 = {btn10, btn15, btn20, btn30};

		for (int i = 0; i < btns2.length; i++) {
		    final int index = i;

		    btns2[i].addActionListener(e -> {
		        for (int j = 0; j < btns2.length; j++) {
		            if (j == index) {
		                btns2[j].setBackground(OurColors.SELECTED);
		            } else {
		                btns2[j].setBackground(OurColors.UNSELECTED);
		            }
		        }
		    });
		}
		addBackButton();
		addConfirmButton();
	}
	
	private void addBackButton() {
		JButton btnNewButton = new JButton("이전");
		btnNewButton.setBackground(OurColors.PREVIOUS_BUTTON);
		btnNewButton.setForeground(OurColors.TITLE_TEXT);
		btnNewButton.setBounds(442, 543, 267, 33);
		
		btnNewButton.addActionListener(e -> {
			contractMP.ShowCard(contractMP.cardNames[2]);
		});
		add(btnNewButton);
	}
	
	private void addConfirmButton() {
		JButton btnNewButton_2 = new JButton("다음");
		btnNewButton_2.setBackground(OurColors.NEXT_BUTTON);
		btnNewButton_2.setForeground(OurColors.TITLE_TEXT);
		btnNewButton_2.setBounds(721, 543, 288, 33);
		
		btnNewButton_2.addActionListener(e -> {
			contractMP.ShowCard(contractMP.cardNames[4]);
		});
		add(btnNewButton_2);
	}
	
}
