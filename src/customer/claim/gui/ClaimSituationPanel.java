package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import common.gui.CardSwitchButton;

// 고객이 입력을 중단한 걸 기록해 뒀다가 나중에 이어서 청구할 수 있는 기능을 넣을 건지..????
public class ClaimSituationPanel extends JPanel {
	
	private JPanel parentCardPanel;
	
	 // 콤보박스를 담는 패널
    private JPanel comboBoxContainer;
	
	public ClaimSituationPanel(JPanel parentCardPanel) {
		this.parentCardPanel = parentCardPanel;
	      CardLayout cl = (CardLayout) (parentCardPanel.getLayout());
	      setBounds(250, 0, 1440, 1024);
	      setLayout(new BorderLayout());
	      
	      // 상단 제목
	        JLabel titleLabel = new JLabel("청구상황 선택");
	        titleLabel.setFont(new Font("굴림", Font.BOLD, 30));
	        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        add(titleLabel, BorderLayout.NORTH);
	        
	        // 중앙 콤보박스 영역을 담을 패널
	        comboBoxContainer = new JPanel();
	        comboBoxContainer.setLayout(new BoxLayout(comboBoxContainer, BoxLayout.Y_AXIS));
	        comboBoxContainer.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));
	       add(comboBoxContainer);
	        
	       String[] 상황 = {"병원에 다녀왔어요(실손 등)", "다른 사람에게 피해를 입혔어요", "교통사고로 비용이 발생했어요"}; //"여행 중 사고가 발생했어요"는 다른거 다 만들고 추가...
	       
	       // 병원에 다녀왔어요(실손 등) 선택시
	       String[] 병원 = {"아팠어요(질병)", "다쳤어요(상해)"};
	       String[] 병원_아팠어요_진단내용 = {"감기", "허리통증", "장염", "복통", "해당하는 항목이 없어요(직접입력)"};
	       String[] 병원_다쳤어요_다친신체부위 = {"머리(얼굴포함)", "어깨/목", "허리", "팔", "손/손목", "다리", "무릎", "발/발목", "해당되는 신체부위가 없어요"};
	       String[] 병원_다쳤어요_다친신체부위_부상유형선택 = {"상처가 났어요(손상)", "접질렀어요(염좌)", "부러졌어요(골절)", "데였어요(화상)", "해당되는 부상종류가 없어요"};
	       String 병원_다쳤어요_다친신체부위_부상유형선택_다친상황; // 고객이 직접 입력하는 JTextField 활용예정 (ex 계단에서 넘어져 발목을 다쳤어요, 놀이기구에서 떨어져 타박상을 입었어요, 목욕탕에서 미끄러져 허리를 다쳤어요
	       
	       // 다른 사람에게 피해를 입혔어요 선택시
	       String[] 다른사람 = {"다쳤어요(신체)", "재산피해를 입혔어요(재물)"};
	       
	       // 다쳤어요(신체)선택시
	       String 다른사람_다쳤어요_사고내용; // 고객이 직접 입력하는 JTextField 활용예정 (ex 자전거로 행인을 부딪쳐 다치게 했어요, 반려견이 행인을 물어 다치게 했어요, 엘리베이터 문에 손이 끼어 다쳤어요)
	       String[] 다른사람_다쳤어요_사고내용_어디서사고발생 = {}; // 국내면 주소 검색하면 주소가 뜨는 API 사용, 해외면 간단히 나라명만 입력인데 굳이 해외까지 할필요가 있을까.......??????
	       /*
			String 다른사람_다쳤어요_사고내용_어디서사고발생_피해정보
			
			다치신 분의 정보를 아시나요? (라디오 버튼)
			예 -> 
			다치신 분1 - 피해자명: "", 휴대폰번호: "" (다치신 분 추가 가능)
			
			타인의 재물피해도 발생했나요?
			예/아니오 (선택만 하고 따로 입력하는 칸이 생성되지는 않음)
			
			아니오 ->
			입력칸 없이 서류등록으로 넘어감
	        */
	       
	       // 재산피해를 입혔어요(재물)
	       String 다른사람_재산피해_사고내용; // // 고객이 직접 입력하는 JTextField 활용예정 (ex 배관 공사 중 실수로 이웃집에 누수를 발생시켰어요, 주차 실수로 이웃차량을 심하게 파손했어요, 화재를 내서 이웃집 벽을 태웠어요)
	       String[] 다른사람_재산피해_사고내용_어디서사고발생 = {}; // 국내면 주소 검색하면 주소가 뜨는 API 사용, 해외면 간단히 나라명만 입력인데 굳이 해외까지 할필요가 있을까.......??????
	       /*
			String 다른사람_재산피해_사고내용_어디서사고발생_피해정보
			
			피해를 입으신 분의 정보를 아시나요?
			예 -> 
			소유자 1 - 소유자명: "", 휴대폰번호: "" (소유자 추가 가능(추가버튼 누르면 소유자 2입력칸이 더생김))
			
			타인의 재물피해도 발생했나요?
			예/아니오 (선택만 하고 따로 입력하는 칸이 생성되지는 않음)
			
			아니오 ->
			입력칸 없이 서류등록으로 넘어감
	        */
	       
	       // 교통사고로 비용이 발생했어요 선택시
	       String[] 교통사고_다쳤어요_다친신체부위 = {"머리(얼굴포함)", "어깨/목", "허리", "팔", "손/손목", "다리", "무릎", "발/발목", "해당되는 신체부위가 없어요"}; // 위에 병원_다쳤어요_신체부위선택이랑 같은 내용임
	       String[] 교통사고_다쳤어요_다친신체부위_부상유형선택 = {"상처가 났어요(손상)", "접질렀어요(염좌)", "부러졌어요(골절)", "데였어요(화상)", "해당되는 부상종류가 없어요"}; // 위에 2222
	       String 교통사고_다쳤어요_다친신체부위_부상유형선택_다친상황; // 고객이 직접 입력하는 JTextField 활용예정 (ex 보행 중 지나가는 오토바이에 치였어요, 운전 중 앞차량과 부딪혀 손목을 다쳤어요, 버스가 급정거하면서 넘어졌어요)
	       
	       /*
				피해 정보를 입력해주세요
				"자동차 보험으로 보상을 받으셨나요?"
				예->
				String[] 자동차_보험사_정보 = {"KB손해보험", "삼성화재", "현대해상", "DB손해보험", "메리츠화재", "롯데손해보험", "한화손해보험", "NH농협손해보험", "하나손해보험", "흥국화재", "MG손해보험", "AXA"손해보험", "AIG손해보험", "에이스손해보험", "캐롯손해보험", "리젠트화재보험", "전세버스공제", "택시공제", "개인택시공제", "버스공제", "전국렌터카공제", "화물공제"};
				String[] 사고_상황_정보 = {"운전중", "탑승중", "보행중"};
				String 차량번호정보 = ""; // 고객이 직접 입력하는 JTextField 활용예정
				
				아니오 -> 
				입력칸 없이 계좌이체로 넘어감
				
	        */
	       JComboBoxMaker 상황선택콤보박스 = new JComboBoxMaker(상황);
	       comboBoxContainer.add(상황선택콤보박스);
	       상황선택콤보박스.setVisible(true);
	       
	       JComboBoxMaker 다른사람콤보박스 = new JComboBoxMaker(다른사람);
	       JComboBoxMaker 병원콤보박스 = new JComboBoxMaker(병원);
	       JComboBoxMaker 병원_아팠어요_진단내용콤보박스 = new JComboBoxMaker(병원_아팠어요_진단내용);
	       JComboBoxMaker 병원_다쳤어요_다친신체부위콤보박스 = new JComboBoxMaker(병원_다쳤어요_다친신체부위);
	       JComboBoxMaker 병원_다쳤어요_다친신체부위_부상유형선택콤보박스 = new JComboBoxMaker(병원_다쳤어요_다친신체부위_부상유형선택);
	       JComboBoxMaker 교통사고_다쳤어요_다친신체부위콤보박스 = new JComboBoxMaker(교통사고_다쳤어요_다친신체부위);
	       JComboBoxMaker 교통사고_다쳤어요_다친신체부위_부상유형선택콤보박스 = new JComboBoxMaker(교통사고_다쳤어요_다친신체부위_부상유형선택);
	       
	       
	       // DB CLAIMS Table claim_category 컬럼 값에 넣는 예시
//	    		   Map<String, String> 상황매핑 = new HashMap<>();
//	    		   상황매핑.put("병원에 다녀왔어요(실손 등)", "질병");
//	    		   상황매핑.put("다른 사람에게 피해를 입혔어요", "일반상해");
//	    		   상황매핑.put("교통사고로 비용이 발생했어요", "교통사고");

	       상황선택콤보박스.addActionListener((e) -> {
	    	   String 선택값 = 상황선택콤보박스.getSelectedItem().toString();
	    	   
	    	   if ("병원에 다녀왔어요(실손 등)".equals(선택값)) {
//	    		   String DB저장값 = 상황매핑.get(선택값);
//	    		   // DB에 저장
//	    		   preparedStatement.setString(1, DB저장값);  // 예: "질병"

	    		   병원콤보박스.setVisible(true);
	    		   notVisible(다른사람콤보박스);
	    		   notVisible(교통사고_다쳤어요_다친신체부위콤보박스, 교통사고_다쳤어요_다친신체부위_부상유형선택콤보박스);
	    	   } else if ("다른 사람에게 피해를 입혔어요".equals(선택값)) {
	    		   다른사람콤보박스.setVisible(true);
	    		   notVisible(병원콤보박스, 병원_아팠어요_진단내용콤보박스, 병원_다쳤어요_다친신체부위콤보박스, 병원_다쳤어요_다친신체부위_부상유형선택콤보박스);
	    		   notVisible(교통사고_다쳤어요_다친신체부위콤보박스, 교통사고_다쳤어요_다친신체부위_부상유형선택콤보박스);
	    	   } else if ("교통사고로 비용이 발생했어요".equals(선택값)) {
	    		   교통사고_다쳤어요_다친신체부위콤보박스.setVisible(true);
	    		   notVisible(병원콤보박스, 병원_아팠어요_진단내용콤보박스, 병원_다쳤어요_다친신체부위콤보박스, 병원_다쳤어요_다친신체부위_부상유형선택콤보박스);
	    		   notVisible(다른사람콤보박스);
	    	   }
	       });
	       
	       병원콤보박스.addActionListener((e) -> {
	    	   notVisible(다른사람콤보박스,교통사고_다쳤어요_다친신체부위콤보박스, 교통사고_다쳤어요_다친신체부위_부상유형선택콤보박스);
	    	   String 병원선택값 =  병원콤보박스.getSelectedItem().toString();
	    	   if ("아팠어요(질병)".equals(병원선택값)) {
	    		   병원_아팠어요_진단내용콤보박스.setVisible(true);
	    	   } else if ("다쳤어요(상해)".equals(병원선택값)) {
	    		   병원_다쳤어요_다친신체부위콤보박스.setVisible(true);
	    		   병원_다쳤어요_다친신체부위콤보박스.setVisible(true);
	    	   }
	       });
	       
	       다른사람콤보박스.addActionListener ((e) ->{
	    	   notVisible(병원콤보박스, 병원_아팠어요_진단내용콤보박스, 병원_다쳤어요_다친신체부위콤보박스, 병원_다쳤어요_다친신체부위_부상유형선택콤보박스,
	    			   교통사고_다쳤어요_다친신체부위콤보박스, 교통사고_다쳤어요_다친신체부위_부상유형선택콤보박스);
	    	   String 다른사람선택값 = 다른사람콤보박스.getSelectedItem().toString();
	    	   if ("다쳤어요(신체)".equals(다른사람선택값)) {
	    	   } else if ("재산피해를 입혔어요(재물)".equals(다른사람선택값)) {
	    		   
	    	   }
	    	   
	       });
	       
	       JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
	       
	       JButton 이전 = new JButton("이전");
	       JButton 다음 = new JButton("다음");
	       buttonPanel.add(이전);
	      buttonPanel.add(다음);
	      
	      다음.addActionListener((e) -> {
	    	  cl.show(parentCardPanel, "EnterBankAccountPanel");
	      });
	      add(buttonPanel, BorderLayout.SOUTH);
	   
	}
	
	private void notVisible (JComponent... components) {
	    for (JComponent comp : components) {
	        comp.setVisible(false);
	    }
	}
}

class JComboBoxMaker extends JComboBox<String> {
	
	public JComboBoxMaker(String[] items) {
		super(items);
		
		setMaximumSize(new Dimension(600, 40));
		setSelectedItem(null); // 아무것도 선택되지 않은 상태에서 시작
		setVisible(false);
		
	}
	

}

