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

public class DamagedPropertyInfoPanel extends JPanel {
	private JLabel ownerNameL;
	private JLabel ownerPhoneL;
	private JTextField ownNameF;
	private JTextField ownerPhoneF;
	private JPanel ownerInfoDPP;
	private JLabel ownerInfoDPL;
	private JRadioButton ownerInfoDPyesButton;
	private JRadioButton ownerInfoDPnoButton;
	private ButtonGroup raidoButtonG;
	
	// 재물피해입으신 분의 정보를 아시나요? 했을 때 나타나는 패널 - 다른사람_다쳤어요_피해정보패널(재물)
	 /*
		String 다른사람_재산피해_사고내용_어디서사고발생_피해정보
		
		피해를 입으신 분의 정보를 아시나요?
		예 -> 
		소유자 1 - 소유자명: "", 휴대폰번호: "" (소유자 추가 가능(추가버튼 누르면 소유자 2입력칸이 더생김))
		
		타인의 신체피해도 발생했나요?
		예/아니오 (선택만 하고 따로 입력하는 칸이 생성되지는 않음)
		
		아니오 ->
		입력칸 없이 서류등록으로 넘어감
       */
	
	public DamagedPropertyInfoPanel() {
		setLayout(new GridBagLayout());
	       setBorder(BorderFactory.createTitledBorder("피해정보"));
	       setMaximumSize(new Dimension(600, 400)); // 크기 제한
	       GridBagConstraints gbc = new GridBagConstraints();

	    // ★ 필드 초기화 (객체 생성)
	       ownerNameL = new JLabel("소유자명 : ");
	       ownerPhoneL = new JLabel("휴대폰번호 : ");
	       ownNameF = new JTextField(9);
	       ownerPhoneF = new JTextField(11);
	     
	       ownerInfoDPP = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
	       ownerInfoDPL = new JLabel("타인의 신체피해도 발생했나요? : ");
	       
	       raidoButtonG = new ButtonGroup();
	       ownerInfoDPyesButton = new JRadioButton(" 예");
	       ownerInfoDPnoButton = new JRadioButton(" 아니오");

	       raidoButtonG.add(ownerInfoDPyesButton);
	       raidoButtonG.add(ownerInfoDPnoButton);
	       
	       ownerInfoDPP.add(ownerInfoDPyesButton);
	       ownerInfoDPP.add(ownerInfoDPnoButton);

	       gbc.insets = new Insets(30, 30, 30, 30);
	       gbc.anchor = GridBagConstraints.CENTER;
	       gbc.gridx = 0; gbc.gridy = 0;
	       add(ownerNameL, gbc);
	       gbc.gridx = 1; gbc.gridy = 0;
	       add(ownNameF, gbc);
	       gbc.gridx = 0; gbc.gridy = 1;
	       add(ownerPhoneL, gbc);
	       gbc.gridx = 1; gbc.gridy = 1;
	       add(ownerPhoneF, gbc);
	       gbc.gridx = 0; gbc.gridy = 2;
	       add(ownerInfoDPL, gbc);
	       gbc.gridx = 1; gbc.gridy = 2;
	       add(ownerInfoDPP, gbc);
	       
	}
	
	public void reset() {
		raidoButtonG.clearSelection();
		ownNameF.setText("");
		ownerPhoneF.setText("");
	}

	public JLabel getOwnerNameL() {
		return ownerNameL;
	}

	public void setOwnerNameL(JLabel ownerNameL) {
		this.ownerNameL = ownerNameL;
	}

	public JLabel getOwnerPhoneL() {
		return ownerPhoneL;
	}

	public void setOwnerPhoneL(JLabel ownerPhoneL) {
		this.ownerPhoneL = ownerPhoneL;
	}

	public JTextField getOwnNameF() {
		return ownNameF;
	}

	public void setOwnNameF(JTextField ownNameF) {
		this.ownNameF = ownNameF;
	}

	public JTextField getOwnerPhoneF() {
		return ownerPhoneF;
	}

	public void setOwnerPhoneF(JTextField ownerPhoneF) {
		this.ownerPhoneF = ownerPhoneF;
	}

	public JPanel getOwnerInfoDPP() {
		return ownerInfoDPP;
	}

	public void setOwnerInfoDPP(JPanel ownerInfoDPP) {
		this.ownerInfoDPP = ownerInfoDPP;
	}

	public JLabel getOwnerInfoDPL() {
		return ownerInfoDPL;
	}

	public void setOwnerInfoDPL(JLabel ownerInfoDPL) {
		this.ownerInfoDPL = ownerInfoDPL;
	}

	public JRadioButton getOwnerInfoDPyesButton() {
		return ownerInfoDPyesButton;
	}

	public void setOwnerInfoDPyesButton(JRadioButton ownerInfoDPyesButton) {
		this.ownerInfoDPyesButton = ownerInfoDPyesButton;
	}

	public JRadioButton getOwnerInfoDPnoButton() {
		return ownerInfoDPnoButton;
	}

	public void setOwnerInfoDPnoButton(JRadioButton ownerInfoDPnoButton) {
		this.ownerInfoDPnoButton = ownerInfoDPnoButton;
	}

	public ButtonGroup getRaidoButtonG() {
		return raidoButtonG;
	}

	public void setRaidoButtonG(ButtonGroup raidoButtonG) {
		raidoButtonG = raidoButtonG;
	}

	
	
	
}
