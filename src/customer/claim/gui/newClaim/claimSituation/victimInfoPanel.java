package customer.claim.gui.newClaim.claimSituation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class victimInfoPanel extends JPanel {
	private JLabel 피해자이름라벨;
	private JLabel 피해자연락처라벨;
	private JTextField 피해자이름필드;
	private JTextField 피해자연락처필드;
	private JPanel 피해자정보_재물피해여부패널;
	private JLabel 피해자재물피해여부라벨;
	private JRadioButton 피해자정보_재물피해yesButton;
	private JRadioButton 피해자정보_재물피해noButton;
	
	// 피해입으신 분의 정보를 아시나요? 했을 때 나타나는 패널 - 다른사람_다쳤어요_피해정보패널
	public victimInfoPanel() {
		setLayout(new GridBagLayout());
	       setBorder(BorderFactory.createTitledBorder("피해정보"));
	       setMaximumSize(new Dimension(600, 400)); // 크기 제한
	       GridBagConstraints gbc = new GridBagConstraints();

	    // ★ 필드 초기화 (객체 생성)
	       피해자이름라벨 = new JLabel("피해자명 : ");
	       피해자연락처라벨 = new JLabel("휴대폰번호 : ");
	       피해자이름필드 = new JTextField(9);
	       피해자연락처필드 = new JTextField(11);
	     
	       피해자정보_재물피해여부패널 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
	       피해자재물피해여부라벨 = new JLabel("타인의 재물피해도 발생했나요? : ");
	       피해자정보_재물피해yesButton = new JRadioButton(" 예");
	       피해자정보_재물피해noButton = new JRadioButton(" 아니오");

	       피해자정보_재물피해여부패널.add(피해자재물피해여부라벨);
	       피해자정보_재물피해여부패널.add(피해자정보_재물피해yesButton);
	       피해자정보_재물피해여부패널.add(피해자정보_재물피해noButton);

	       gbc.insets = new Insets(30, 30, 30, 30);
	       gbc.anchor = GridBagConstraints.CENTER;
	       gbc.gridx = 0; gbc.gridy = 0;
	       add(피해자이름라벨, gbc);
	       gbc.gridx = 1; gbc.gridy = 0;
	       add(피해자이름필드, gbc);
	       gbc.gridx = 0; gbc.gridy = 1;
	       add(피해자연락처라벨, gbc);
	       gbc.gridx = 1; gbc.gridy = 1;
	       add(피해자연락처필드, gbc);
	       gbc.gridy = 2;
	       add(피해자정보_재물피해여부패널, gbc);
	       
	}

	public JLabel get피해자이름라벨() {
		return 피해자이름라벨;
	}

	public void set피해자이름라벨(JLabel 피해자이름라벨) {
		this.피해자이름라벨 = 피해자이름라벨;
	}

	public JLabel get피해자연락처라벨() {
		return 피해자연락처라벨;
	}

	public void set피해자연락처라벨(JLabel 피해자연락처라벨) {
		this.피해자연락처라벨 = 피해자연락처라벨;
	}

	public JTextField get피해자이름필드() {
		return 피해자이름필드;
	}

	public void set피해자이름필드(JTextField 피해자이름필드) {
		this.피해자이름필드 = 피해자이름필드;
	}

	public JTextField get피해자연락처필드() {
		return 피해자연락처필드;
	}

	public void set피해자연락처필드(JTextField 피해자연락처필드) {
		this.피해자연락처필드 = 피해자연락처필드;
	}

	public JPanel get피해자정보_재물피해여부패널() {
		return 피해자정보_재물피해여부패널;
	}

	public void set피해자정보_재물피해여부패널(JPanel 피해자정보_재물피해여부패널) {
		this.피해자정보_재물피해여부패널 = 피해자정보_재물피해여부패널;
	}

	public JLabel get피해자재물피해여부라벨() {
		return 피해자재물피해여부라벨;
	}

	public void set피해자재물피해여부라벨(JLabel 피해자재물피해여부라벨) {
		this.피해자재물피해여부라벨 = 피해자재물피해여부라벨;
	}

	public JRadioButton get피해자정보_재물피해yesButton() {
		return 피해자정보_재물피해yesButton;
	}

	public void set피해자정보_재물피해yesButton(JRadioButton 피해자정보_재물피해yesButton) {
		this.피해자정보_재물피해yesButton = 피해자정보_재물피해yesButton;
	}

	public JRadioButton get피해자정보_재물피해noButton() {
		return 피해자정보_재물피해noButton;
	}

	public void set피해자정보_재물피해noButton(JRadioButton 피해자정보_재물피해noButton) {
		this.피해자정보_재물피해noButton = 피해자정보_재물피해noButton;
	}
	
	
}
