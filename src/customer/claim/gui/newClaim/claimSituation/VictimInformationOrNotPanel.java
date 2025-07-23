package customer.claim.gui.newClaim.claimSituation;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class VictimInformationOrNotPanel extends JPanel {
	
	private JLabel victimInfoL;
	private JRadioButton yesB;
	private JRadioButton noB;
	private ButtonGroup chButtonG;
	
	public VictimInformationOrNotPanel() { // 피해자정보묻는라디오버튼패널
			setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			
	       setPreferredSize(new Dimension(400, 40));
	       victimInfoL = new JLabel();
	       yesB = new JRadioButton(" 예");
	       noB = new JRadioButton(" 아니오");
	       
	       chButtonG = new ButtonGroup();
	       chButtonG.add(yesB);
	       chButtonG.add(noB);
	       
	       add(victimInfoL);
	       add(yesB);
	       add(noB);
	}

	public JLabel getVictimInfoL() {
		return victimInfoL;
	}

	public void setVictimInfoL(String str) {
		this.victimInfoL.setText(str);
	}

	public JRadioButton getYesB() {
		return yesB;
	}

	public void setYesB(JRadioButton yesB) {
		this.yesB = yesB;
	}

	public JRadioButton getNoB() {
		return noB;
	}

	public void setNoB(JRadioButton noB) {
		this.noB = noB;
	}

	public ButtonGroup getChButtonG() {
		return chButtonG;
	}

	public void setChButtonG(ButtonGroup chButtonG) {
		this.chButtonG = chButtonG;
	}

	
	
	
}
