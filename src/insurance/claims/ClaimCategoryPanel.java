package insurance.claims;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;


public class ClaimCategoryPanel extends JPanel {
	private JPanel parentCardPanel;

	public ClaimCategoryPanel(JPanel parentCardPanel) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) (parentCardPanel.getLayout());
		setBounds(250, 0, 1440, 1024);
		setLayout(null);
		
		JPanel 청구유형선택패널 = new JPanel(new CardLayout());
		
		JLabel 청구유형선택라벨 = new JLabel("청구유형 선택") {
	         {
	            setFont(new Font("굴림", Font.PLAIN, 30));
	            setBounds(112, 137, 293, 63);
	         }
	      };
	      add(청구유형선택라벨);
		
		JRadioButton 질병선택버튼 = new JRadioButton() {
			{
				setBounds(200, 200, 30, 30);
				setVisible(true);
			}
		};
		질병선택버튼.setSize(20, 20);
		질병선택버튼.setLocation(352, 220);

		JRadioButton 상해선택버튼 = new JRadioButton() {
			{
				setBounds(100, 100, 30, 30);
				setVisible(true);
			}
		};
		상해선택버튼.setSize(20, 20);
		상해선택버튼.setLocation(352, 355);

		JRadioButton 교통사고선택버튼 = new JRadioButton() {
			{
				setBounds(200, 200, 30, 30);
				setVisible(true);
			}
		};
		교통사고선택버튼.setSize(20, 20);
		교통사고선택버튼.setLocation(352, 503);
		
		add(질병선택버튼);
		add(상해선택버튼);
		add(교통사고선택버튼);
		
		ButtonGroup 청구유형선택버튼그룹 = new ButtonGroup(); 
		청구유형선택버튼그룹.add(질병선택버튼);
		청구유형선택버튼그룹.add(상해선택버튼);
		청구유형선택버튼그룹.add(교통사고선택버튼);
		
		JTextArea 질병선택라벨 = new JTextArea("질병\n"
				+ "(신체 내부의 요인으로 몸이 불편하여 의사의 진단을 요하는 치료를 받는 경우\n"
				+ "[예시] 감기, 장염, 당뇨, 고혈압, 추간판탈출증, 암 등)");
		질병선택라벨.setFont(new Font("Monospaced", Font.PLAIN, 16));
				
				질병선택라벨.setBounds(449, 171, 921, 123);
				질병선택라벨.setVisible(true);
		
		add(질병선택라벨);
		
		JTextArea 상해선택라벨 = new JTextArea("일반상해\n"
				+ "(피보험자가 국내/외에서 급격하고도 우연한 외래의 사고로 신체에 부상을 입고 치료를 받는 경우\n"
				+ "[예시] 골절, 화상, 염좌, 절단, 상처 등)");
		상해선택라벨.setFont(new Font("Monospaced", Font.PLAIN, 16));
				
				상해선택라벨.setBounds(449, 312, 921, 123);
				상해선택라벨.setVisible(true);
		add(상해선택라벨);
		
		JTextArea 교통사고선택라벨 = new JTextArea("교통사고\n"
				+ "(운전 중 교통사고로 인한 손해를 입은 경우\n"
				+ "[예시] 경찰신고, 기소, 벌금, 형사합의, 면허정지 등)");
		교통사고선택라벨.setFont(new Font("Monospaced", Font.PLAIN, 16));
				
		교통사고선택라벨.setBounds(449, 457, 921, 123);
		교통사고선택라벨.setVisible(true);
		add(교통사고선택라벨);
		
		JButton 이전버튼 = new JButton("이전");
	      이전버튼.setBounds(470, 686, 100, 30);
	      add(이전버튼);
		
		이전버튼.addActionListener((e) -> {
	         cl.show(parentCardPanel, "AccidentDatePanel");
	         청구유형선택버튼그룹.clearSelection();
	      });

	      JButton 다음버튼 = new JButton("다음"); 
	      다음버튼.setBounds(853, 686, 100, 30);
	      add(다음버튼);

	      다음버튼.addActionListener((e) -> {
	         if (청구유형선택버튼그룹.getSelection() == null) {
	            JOptionPane.showMessageDialog(this, "청구유형을 선택해주세요.", "안내", JOptionPane.INFORMATION_MESSAGE);
	         } else {
	            cl.show(parentCardPanel, "ClaimSituationPanel");
	         }

	      });
	}
}
