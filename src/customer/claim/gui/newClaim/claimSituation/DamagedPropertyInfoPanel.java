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
	private JLabel 소유자이름라벨;
	private JLabel 소유자연락처라벨;
	private JTextField 소유자이름필드;
	private JTextField 소유자연락처필드;
	private JPanel 소유자정보_신체피해여부패널;
	private JLabel 소유자신체피해여부라벨;
	private JRadioButton 소유자정보_신체피해yesButton;
	private JRadioButton 소유자정보_신체피해noButton;
	private ButtonGroup 라디오버튼그룹;
	
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
	       소유자이름라벨 = new JLabel("소유자명 : ");
	       소유자연락처라벨 = new JLabel("휴대폰번호 : ");
	       소유자이름필드 = new JTextField(9);
	       소유자연락처필드 = new JTextField(11);
	     
	       소유자정보_신체피해여부패널 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
	       소유자신체피해여부라벨 = new JLabel("타인의 신체피해도 발생했나요? : ");
	       
	       라디오버튼그룹 = new ButtonGroup();
	       소유자정보_신체피해yesButton = new JRadioButton(" 예");
	       소유자정보_신체피해noButton = new JRadioButton(" 아니오");

	       라디오버튼그룹.add(소유자정보_신체피해yesButton);
	       라디오버튼그룹.add(소유자정보_신체피해noButton);
	       
	       소유자정보_신체피해여부패널.add(소유자정보_신체피해yesButton);
	       소유자정보_신체피해여부패널.add(소유자정보_신체피해noButton);

	       gbc.insets = new Insets(30, 30, 30, 30);
	       gbc.anchor = GridBagConstraints.CENTER;
	       gbc.gridx = 0; gbc.gridy = 0;
	       add(소유자이름라벨, gbc);
	       gbc.gridx = 1; gbc.gridy = 0;
	       add(소유자이름필드, gbc);
	       gbc.gridx = 0; gbc.gridy = 1;
	       add(소유자연락처라벨, gbc);
	       gbc.gridx = 1; gbc.gridy = 1;
	       add(소유자연락처필드, gbc);
	       gbc.gridx = 0; gbc.gridy = 2;
	       add(소유자신체피해여부라벨, gbc);
	       gbc.gridx = 1; gbc.gridy = 2;
	       add(소유자정보_신체피해여부패널, gbc);
	       
	}
	
	public void reset() {
		라디오버튼그룹.clearSelection();
		소유자이름필드.setText("");
		소유자연락처필드.setText("");
	}

	public JLabel get소유자이름라벨() {
		return 소유자이름라벨;
	}

	public void set소유자이름라벨(JLabel 소유자이름라벨) {
		this.소유자이름라벨 = 소유자이름라벨;
	}

	public JLabel get소유자연락처라벨() {
		return 소유자연락처라벨;
	}

	public void set소유자연락처라벨(JLabel 소유자연락처라벨) {
		this.소유자연락처라벨 = 소유자연락처라벨;
	}

	public JTextField get소유자이름필드() {
		return 소유자이름필드;
	}

	public void set소유자이름필드(JTextField 소유자이름필드) {
		this.소유자이름필드 = 소유자이름필드;
	}

	public JTextField get소유자연락처필드() {
		return 소유자연락처필드;
	}

	public void set소유자연락처필드(JTextField 소유자연락처필드) {
		this.소유자연락처필드 = 소유자연락처필드;
	}

	public JPanel get소유자정보_신체피해여부패널() {
		return 소유자정보_신체피해여부패널;
	}

	public void set소유자정보_신체피해여부패널(JPanel 소유자정보_신체피해여부패널) {
		this.소유자정보_신체피해여부패널 = 소유자정보_신체피해여부패널;
	}

	public JLabel get소유자신체피해여부라벨() {
		return 소유자신체피해여부라벨;
	}

	public void set소유자신체피해여부라벨(JLabel 소유자신체피해여부라벨) {
		this.소유자신체피해여부라벨 = 소유자신체피해여부라벨;
	}

	public JRadioButton get소유자정보_신체피해yesButton() {
		return 소유자정보_신체피해yesButton;
	}

	public void set소유자정보_신체피해yesButton(JRadioButton 소유자정보_신체피해yesButton) {
		this.소유자정보_신체피해yesButton = 소유자정보_신체피해yesButton;
	}

	public JRadioButton get소유자정보_신체피해noButton() {
		return 소유자정보_신체피해noButton;
	}

	public void set소유자정보_신체피해noButton(JRadioButton 소유자정보_신체피해noButton) {
		this.소유자정보_신체피해noButton = 소유자정보_신체피해noButton;
	}

	public ButtonGroup get라디오버튼그룹() {
		return 라디오버튼그룹;
	}

	public void set라디오버튼그룹(ButtonGroup 라디오버튼그룹) {
		this.라디오버튼그룹 = 라디오버튼그룹;
	}

	
	
}
