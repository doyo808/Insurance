package customer.contract.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import customer.contract.ContractMainPanel;
import customer.contract.method.GetProductNamesFromKeyword;
import customer.contract.method.SelectedProductName;

public class ChooseProductPanel extends JPanel {
	private ContractMainPanel contractMP;
	private ButtonGroup btnGroup = new ButtonGroup();
	
	public ChooseProductPanel(ContractMainPanel contractMP) {
		this.contractMP = contractMP;
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		setPreferredSize(new Dimension(1440, 700));
	}

	public void updateProducts() {	
		removeAll();
		
		JLabel title = new JLabel("보험상품가입 [상품선택]");
		title.setFont(new Font("굴림", Font.BOLD, 32));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(457, 10, 537, 56);
		add(title);
		
		
		Set<String> product_names = GetProductNamesFromKeyword.getNames();
		
		for (String name : product_names) {
			addProductPanel(name);
		}
		addBackButton();
		addConfirmButton();
		
		revalidate();
		repaint();
	}
	
	private void addProductPanel(String name) {
		JPanel panel = new JPanel();
		
		JLabel head = new JLabel();
		JLabel detail = new JLabel();
		
		head.setText(name);
		detail.setText("자세한 설명입니다.");
		
		
		JRadioButton btn = new JRadioButton();
		btn.setActionCommand(name);
		btnGroup.add(btn);
		
		panel.add(btn);
		panel.add(head);
		panel.add(detail);
		
		add(panel);
	}
	
	private void addBackButton() {
		JButton back = new JButton("이전");
		back.addActionListener(e -> {
			contractMP.ShowCard(contractMP.cardNames[0]);
		});
		add(back);
	}
	
	private void addConfirmButton() {
		JButton confirm = new JButton("다음");
		
		confirm.addActionListener(e -> {
			
			if (btnGroup.getSelection() != null) {
				String SelectedName = btnGroup.getSelection().getActionCommand();
				SelectedProductName.setProduct_name(SelectedName);
				contractMP.ShowCard(contractMP.cardNames[2]);
			} else {
				JOptionPane.showMessageDialog(this, "상품을 선택하세요", "알림", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		add(confirm);
	}
}





