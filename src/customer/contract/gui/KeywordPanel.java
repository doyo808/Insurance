package customer.contract.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import common.gui.OurColors;
import common.method.ButtonPainter;
import customer.contract.ContractMainPanel;
import customer.contract.method.GetProductNamesFromKeyword;

public class KeywordPanel extends JPanel {
	
	Color c1 = OurColors.SELECTED;		// 초록
	Color c2 = OurColors.UNSELECTED;	// 회색
	
	public KeywordPanel(ContractMainPanel contractMP) {
		setLayout(null);
		
		JLabel title = new JLabel("보험상품가입 [키워드선택]");
		title.setFont(new Font("Dialog", Font.BOLD, 32));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(500, 10, 537, 56);
		add(title);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(290, 93, 946, 476);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("인기 키워드 (중복 선택 가능)");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 28));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(250, 38, 442, 72);
		panel.add(lblNewLabel_1);
		
		JButton btn0 = new JButton("암");
		btn0.setBackground(c2);
		btn0.setBounds(194, 166, 102, 78);
		panel.add(btn0);
		
		JButton btn1 = new JButton("생활보장");
		btn1.setBackground(c2);
		btn1.setBounds(333, 166, 102, 78);
		panel.add(btn1);
		
		JButton btn4 = new JButton("입원");
		btn4.setBackground(c2);
		btn4.setBounds(194, 279, 102, 78);
		panel.add(btn4);
		
		JButton btn5 = new JButton("진단금");
		btn5.setBackground(c2);
		btn5.setBounds(333, 279, 102, 78);
		panel.add(btn5);
		
		JButton btn6 = new JButton("보철");
		btn6.setBackground(c2);
		btn6.setBounds(499, 279, 102, 78);
		panel.add(btn6);
		
		JButton btn7 = new JButton("수술");
		btn7.setBackground(c2);
		btn7.setBounds(638, 279, 102, 78);
		panel.add(btn7);
		
		JButton btn3 = new JButton("스케일링");
		btn3.setBackground(c2);
		btn3.setBounds(638, 166, 102, 78);
		panel.add(btn3);
		
		JButton btn2 = new JButton("치아");
		btn2.setBackground(c2);
		btn2.setBounds(499, 166, 102, 78);
		panel.add(btn2);
		
		JButton[] btns = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7};
		for (JButton b : btns) {
			ButtonPainter.styleKeywordButton(b, c2, c1);
		}
		
		JButton confirm = new JButton("조회");
		ButtonPainter.stylePrimaryButtonCarrot(confirm, 16);
		confirm.setBounds(623, 617, 279, 41);
		add(confirm);
		
		addConfirmAction(contractMP, confirm, btns);
		
	}
	
	
	void addConfirmAction(ContractMainPanel contractMP, JButton btn, JButton[] btns) {
		btn.addActionListener(e -> {
			
			collectSelectedKeywords(btns);
			if (GetProductNamesFromKeyword.list.size() == 0) {
				JOptionPane.showMessageDialog(this, "최소 하나의 키워드를 선택해주세요."
						, "알림", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			contractMP.ShowCard(contractMP.cardNames[1]);
		});
	}
	
	void collectSelectedKeywords(JButton[] btns) {
		GetProductNamesFromKeyword.list.clear();
		for (JButton b : btns) {
			 if (Boolean.TRUE.equals(b.getClientProperty("selected"))) {
		            GetProductNamesFromKeyword.list.add(b.getText());
		        }
			
		}
	}
}
