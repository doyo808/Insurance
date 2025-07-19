package customer.claim.gui.newClaim.claimSituation;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class VictimInformationOrNotPanel extends JPanel {
	
	private JLabel 피해자정보여부라벨;
	private JRadioButton 피해자정보yesButton;
	private JRadioButton 피해자정보noButton;
	private ButtonGroup chButtonGroup;
	
	public VictimInformationOrNotPanel() { // 피해자정보묻는라디오버튼패널
			setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			
	       setPreferredSize(new Dimension(400, 40));
	       피해자정보여부라벨 = new JLabel();
	       피해자정보yesButton = new JRadioButton(" 예");
	       피해자정보noButton = new JRadioButton(" 아니오");
	       
	       // 나중에 선택안한거 있으면 다음으로 못넘어가게 하는 그룹묶음
	       chButtonGroup = new ButtonGroup();
	       chButtonGroup.add(피해자정보yesButton);
	       chButtonGroup.add(피해자정보noButton);
	       
	       add(피해자정보여부라벨);
	       add(피해자정보yesButton);
	       add(피해자정보noButton);
	}

	public JLabel get피해자정보여부라벨() {
		return 피해자정보여부라벨;
	}

	public void set피해자정보여부라벨(String 정보여부질문) {
		this.피해자정보여부라벨.setText(정보여부질문);
	}

	public JRadioButton get피해자정보yesButton() {
		return 피해자정보yesButton;
	}

	public void set피해자정보yesButton(JRadioButton 피해자정보yesButton) {
		this.피해자정보yesButton = 피해자정보yesButton;
	}

	public JRadioButton get피해자정보noButton() {
		return 피해자정보noButton;
	}

	public void set피해자정보noButton(JRadioButton 피해자정보noButton) {
		this.피해자정보noButton = 피해자정보noButton;
	}

	public ButtonGroup getChButtonGroup() {
		return chButtonGroup;
	}

	public void setChButtonGroup(ButtonGroup chButtonGroup) {
		this.chButtonGroup = chButtonGroup;
	}
	
	
}
