package insurance.claims;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class ClaimSituationPanel extends JPanel {
	
	private JPanel parentCardPanel;
	
	public ClaimSituationPanel(JPanel parentCardPanel) {
		this.parentCardPanel = parentCardPanel;
	      CardLayout cl = (CardLayout) (parentCardPanel.getLayout());
	      setBounds(250, 0, 1440, 1024);
	      setLayout(null);
	      
	      JPanel 청구상황선택패널 = new JPanel(new CardLayout());
	      JLabel 청구상황선택라벨 = new JLabel("청구상황 선택");
	          
    	  청구상황선택라벨.setFont(new Font("굴림", Font.PLAIN, 30));
    	  청구상황선택라벨.setBounds(112, 137, 293, 63);
	       add(청구상황선택라벨);

	       String[] 청구상황 = {"병원에 다녀왔어요(실손 등)", "다른 사람에게 피해를 입혔어요", "교통사고로 비용이 발생했어요", "여행 중 사고가 발생했어요"};
	       
	       String[] 병원 = {"아팠어요", "다쳤어요"};
	       String[] 병원_아팠어요 = {"감기", "허리통증", "장염", "복통", "해당하는 항목이 없어요(직접입력)"};
	       String[] 병원_다쳤어요_다친신체부위 = {"머리(얼굴포함)", "어깨/목", "허리", "팔", "손/손목", "다리", "무릎", "발/발목", "해당되는 신체부위가 없어요"};
	       String[] 병원_다쳤어요_부상유형선택 = {"상처가 났어요(손상)", "접질렀어요(염좌)", "부러졌어요(골절)", "데였어요(화상)", "해당되는 부상종류가 없어요"};
	       String 병원_다쳤어요_다친상황; // 고객이 직접 입력하는 JTextField 활용예정
	      
	       String[] 다른사람 = {"다쳤어요(신체)", "재산피해를 입혔어요(재물)"};
	       String 다른사람_사고내용; // 
	       // 앱 통해서 한번더 자세한 내용확인후 작성....
	       
	       
	       JComboBox<String> comboBox = new JComboBox<>();
	       
	       comboBox.setBounds(487, 133, 470, 45);
	       setVisible(true);
	       add(comboBox);
	   
	       for (String item: 청구상황) {
	    	   comboBox.addItem(item);
	       }
	       comboBox.setSelectedItem(null); // 아무것도 선택되지 않은 상태에서 시작
	       
	       comboBox.addActionListener((e) -> {
	    	   String output1 = comboBox.getSelectedItem().toString();
//	    	   if () {
//	    		// 첫번 째 청구유형에서 뭘 선택하냐에 따라 다음 콤보박스부터 값이 달라진다   
//	    	   }
	       });
	       
	     
	       
//	       JComboBox<String> comboBox_1 = new JComboBox();
//	       comboBox_1.setBounds(487, 311, 470, 45);
//	       add(comboBox_1);
//	       
//	       JComboBox<String> comboBox_2 = new JComboBox();
//	       comboBox_2.setBounds(487, 489, 470, 45);
//	       add(comboBox_2);
//	       
//	       JComboBox<String> comboBox_3 = new JComboBox();
//	       comboBox_3.setBounds(487, 667, 470, 45);
//	       add(comboBox_3);
//	       
//	       JComboBox<String> comboBox_4 = new JComboBox();
//	       comboBox_4.setBounds(487, 845, 470, 45);
//	       add(comboBox_4);
	      
	      
	}
}
