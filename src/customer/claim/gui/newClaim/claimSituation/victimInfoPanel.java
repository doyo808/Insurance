package customer.claim.gui.newClaim.claimSituation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class victimInfoPanel extends JPanel {
	private JLabel nameL;
	private JLabel phoneL;
	private JTextField nameF;
	private JTextField phoneF;
	private JPanel damagedPropertyP;
	private JLabel damagedPropertyL;
	private JRadioButton yesButton;
	private JRadioButton noButton;
	private ButtonGroup radioButtonG;
	
	// 피해입으신 분의 정보를 아시나요? 했을 때 나타나는 패널 - 다른사람_다쳤어요_피해정보패널
	public victimInfoPanel() {
		setLayout(new GridBagLayout());
	       setBorder(BorderFactory.createTitledBorder("피해정보"));
	       setMaximumSize(new Dimension(600, 400)); // 크기 제한
	       GridBagConstraints gbc = new GridBagConstraints();

	    // ★ 필드 초기화 (객체 생성)
	       nameL = new JLabel("피해자명 : ");
	       phoneL = new JLabel("휴대폰번호 : ");
	       nameF = new JTextField(9);
	       phoneF = new JTextField(11);
	       radioButtonG = new ButtonGroup();
	       
	       damagedPropertyP = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
	       damagedPropertyL = new JLabel("타인의 재물피해도 발생했나요? : ");
	       yesButton = new JRadioButton(" 예");
	       noButton = new JRadioButton(" 아니오");

	       radioButtonG.add(yesButton);
	       radioButtonG.add(noButton);
	       
	       damagedPropertyP.add(yesButton);
	       damagedPropertyP.add(noButton);

	       gbc.insets = new Insets(30, 30, 30, 30);
	       gbc.anchor = GridBagConstraints.CENTER;
	       gbc.gridx = 0; gbc.gridy = 0;
	       add(nameL, gbc);
	       gbc.gridx = 1; gbc.gridy = 0;
	       add(nameF, gbc);
	       gbc.gridx = 0; gbc.gridy = 1;
	       add(phoneL, gbc);
	       gbc.gridx = 1; gbc.gridy = 1;
	       add(phoneF, gbc);
	       gbc.gridx = 0; gbc.gridy = 2;
	       add(damagedPropertyL, gbc);
	       gbc.gridx = 1; gbc.gridy = 2;
	       add(damagedPropertyP, gbc);
	       
	}
	
	public void reset() {
		radioButtonG.clearSelection();
		nameF.setText("");
		phoneF.setText("");
	}

	public JLabel getNameL() {
		return nameL;
	}

	public void setNameL(JLabel nameL) {
		this.nameL = nameL;
	}

	public JLabel getPhoneL() {
		return phoneL;
	}

	public void setPhoneL(JLabel phoneL) {
		this.phoneL = phoneL;
	}

	public JTextField getNameF() {
		return nameF;
	}

	public void setNameF(JTextField nameF) {
		this.nameF = nameF;
	}

	public JTextField getPhoneF() {
		return phoneF;
	}

	public void setPhoneF(JTextField phoneF) {
		this.phoneF = phoneF;
	}

	public JPanel getDamagedPropertyP() {
		return damagedPropertyP;
	}

	public void setDamagedPropertyP(JPanel damagedPropertyP) {
		this.damagedPropertyP = damagedPropertyP;
	}

	public JLabel getDamagedPropertyL() {
		return damagedPropertyL;
	}

	public void setDamagedPropertyL(JLabel damagedPropertyL) {
		this.damagedPropertyL = damagedPropertyL;
	}

	public JRadioButton getYesButton() {
		return yesButton;
	}

	public void setYesButton(JRadioButton yesButton) {
		this.yesButton = yesButton;
	}

	public JRadioButton getNoButton() {
		return noButton;
	}

	public void setNoButton(JRadioButton noButton) {
		this.noButton = noButton;
	}

	public ButtonGroup getRadioButtonG() {
		return radioButtonG;
	}

	public void setRadioButtonG(ButtonGroup radioButtonG) {
		this.radioButtonG = radioButtonG;
	}

	
	
	
}
