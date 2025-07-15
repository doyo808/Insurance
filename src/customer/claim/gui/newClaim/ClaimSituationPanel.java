package customer.claim.gui.newClaim;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import common.database.model.NewClaimDataModel;
import customer.claim.gui.TitlePanel;

// 고객이 입력을 중단한 걸 기록해 뒀다가 나중에 이어서 청구할 수 있는 기능을 넣을 건지..????
public class ClaimSituationPanel extends JPanel {
	
	private JPanel parentCardPanel;
	
	
    private String[] 상황_1 = {"병원에 다녀왔어요(실손 등)", "다른 사람에게 피해를 입혔어요", "교통사고로 비용이 발생했어요"}; //"여행 중 사고가 발생했어요"는 다른거 다 만들고 추가...
    private String[] 병원_2 = {"아팠어요(질병)", "다쳤어요(상해)"};
    private String[] 병원_아팠어요_진단내용 = {"감기", "허리통증", "장염", "복통", "해당하는 항목이 없어요(직접입력)"};
    private String[] 다친신체부위 = {"머리(얼굴포함)", "어깨/목", "허리", "팔", "손/손목", "다리", "무릎", "발/발목", "해당되는 신체부위가 없어요"};
    private String[] 부상유형선택 = {"상처가 났어요(손상)", "접질렀어요(염좌)", "부러졌어요(골절)", "데였어요(화상)", "해당되는 부상종류가 없어요"};
	
    private String[] 다른사람_2 = {"다쳤어요(신체)", "재산피해를 입혔어요(재물)"};
    
    private String[] 자동차보험사 = {"KB손해보험", "삼성화재", "현대해상", "DB손해보험", "메리츠화재", "롯데손해보험", "한화손해보험", "NH농협손해보험", "하나손해보험", "흥국화재", "MG손해보험", "AXA손해보험", "AIG손해보험", "에이스손해보험", "캐롯손해보험", "리젠트화재보험", "전세버스공제", "택시공제", "개인택시공제", "버스공제", "전국렌터카공제", "화물공제"};
    private	String[] 자동차사고_상황정보 = {"운전중", "탑승중", "보행중"};

    
    
    public ClaimSituationPanel(JPanel parentCardPanel, NewClaimDataModel claimData) {
		this.parentCardPanel = parentCardPanel;
	      CardLayout cl = (CardLayout) (parentCardPanel.getLayout());
	      setLayout(new BorderLayout());
	      
	        TitlePanel title = new TitlePanel("청구상황 선택");
	        add(title, BorderLayout.NORTH);
	        
	        // 중앙 콤보박스 영역을 담을 패널
	        JPanel comboBoxContainer = new JPanel();
	        comboBoxContainer.setLayout(new BoxLayout(comboBoxContainer, BoxLayout.Y_AXIS));
	        comboBoxContainer.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));
	       add(comboBoxContainer, BorderLayout.CENTER);
	        
	       
	       // 교통사고에서도 - 어디서 발생했는지 이후 적는칸 있음, 
	       
	       String 다른사람_다쳤어요_사고내용; // 고객이 직접 입력하는 JTextField 활용예정 (ex 자전거로 행인을 부딪쳐 다치게 했어요, 반려견이 행인을 물어 다치게 했어요, 엘리베이터 문에 손이 끼어 다쳤어요)
	       String[] 다른사람_다쳤어요_사고내용_어디서사고발생 = {}; // 국내면 주소 검색하면 주소가 뜨는 API 사용, 해외면 간단히 나라명만 입력인데 굳이 해외까지 할필요가 있을까.......??????
	       
//	       JTextField 다친상황_입력창 = new JTextField();
//	       String 다친상황; // 고객이 직접 입력하는 JTextField 활용예정 (ex 보행 중 지나가는 오토바이에 치였어요, 운전 중 앞차량과 부딪혀 손목을 다쳤어요, 버스가 급정거하면서 넘어졌어요)
	       
	       /*
				피해 정보를 입력해주세요
				"자동차 보험으로 보상을 받으셨나요?"
				예->
				String 차량번호정보 = ""; // 고객이 직접 입력하는 JTextField 활용예정
				
				아니오 -> 
				입력칸 없이 계좌이체로 넘어감
	        */

	       // ⬅️ 가운데 패널 구성
	       JPanel centerPanel = new JPanel(new BorderLayout()); // comboBoxContainer.setVisible(false);이 되어야 하는데 그럼 이전 버튼으로 전 패널로 돌아가게 해야겠지
	       centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
	       add(centerPanel, BorderLayout.CENTER);
	       
	       centerPanel.add(comboBoxContainer, BorderLayout.NORTH);
	       
	       
	       JComboBoxMaker 상황콤보박스 = new JComboBoxMaker(상황_1);
	       comboBoxContainer.add(상황콤보박스);
	       상황콤보박스.setVisible(true);
	       
	       JComboBoxMaker 다른사람콤보박스 = new JComboBoxMaker(다른사람_2);
	       JComboBoxMaker 병원콤보박스 = new JComboBoxMaker(병원_2);
	       JComboBoxMaker 병원_아팠어요_진단내용콤보박스 = new JComboBoxMaker(병원_아팠어요_진단내용);
	       JComboBoxMaker 다친신체부위콤보박스 = new JComboBoxMaker(다친신체부위);
	       JComboBoxMaker 부상유형선택콤보박스 = new JComboBoxMaker(부상유형선택);
	       
	       JComboBoxMaker 자동차보험사콤보박스 = new JComboBoxMaker(자동차보험사);
	       JComboBoxMaker 자동차사고상황콤보박스 = new JComboBoxMaker(자동차사고_상황정보);
	       
	       JTextField 다친상황_입력창 = new JTextField();
	       
	       comboBoxContainer.add(병원콤보박스);
	       comboBoxContainer.add(병원_아팠어요_진단내용콤보박스);
	       comboBoxContainer.add(다친신체부위콤보박스);
	       comboBoxContainer.add(다른사람콤보박스);
	       comboBoxContainer.add(부상유형선택콤보박스);
	       comboBoxContainer.add(자동차보험사콤보박스);
	       comboBoxContainer.add(부상유형선택콤보박스);
	       
	       comboBoxContainer.add(다친상황_입력창); 
	       다친상황_입력창.setMaximumSize(new Dimension(600, 40));
	       다친상황_입력창.setVisible(false);
	       
	       
	       
	       부상유형선택콤보박스.addActionListener((e) -> {
	    	    if (부상유형선택콤보박스.getSelectedItem() == null) return;
	    	    다친상황_입력창.setVisible(true);
	    	});
	       
	       // DB CLAIMS Table claim_category 컬럼 값에 넣는 예시
//	    		   Map<String, String> 상황매핑 = new HashMap<>();
//	    		   상황매핑.put("병원에 다녀왔어요(실손 등)", "질병");
//	    		   상황매핑.put("다른 사람에게 피해를 입혔어요", "일반상해");
//	    		   상황매핑.put("교통사고로 비용이 발생했어요", "교통사고");

	       상황콤보박스.addActionListener((e) -> {
	    	   Object selected = 상황콤보박스.getSelectedItem();
	    	    if (selected == null) return; // null이면 처리하지 않음
	    	    String 선택값 = selected.toString();
	    	    
	    	 // 1. 병원에 다녀왔어요(실손 등) 선택시
	    	   if ("병원에 다녀왔어요(실손 등)".equals(선택값)) {
//	    		   String DB저장값 = 상황매핑.get(선택값);
//	    		   // DB에 저장
//	    		   preparedStatement.setString(1, DB저장값);  // 예: "질병"

	    		   병원콤보박스.setVisible(true);
	    		   resetComboBox(다른사람콤보박스, 다친신체부위콤보박스, 부상유형선택콤보박스);
	    		   notVisible(다른사람콤보박스, 다친신체부위콤보박스, 부상유형선택콤보박스, 다친상황_입력창);
	    		   
	    		// 2. 다른 사람에게 피해를 입혔어요 선택시
	    	   } else if ("다른 사람에게 피해를 입혔어요".equals(선택값)) {
	    		   다른사람콤보박스.setVisible(true);
	    		   resetComboBox(병원콤보박스, 병원_아팠어요_진단내용콤보박스, 다친신체부위콤보박스, 부상유형선택콤보박스);
	    		   notVisible(병원콤보박스, 병원_아팠어요_진단내용콤보박스, 다친신체부위콤보박스, 부상유형선택콤보박스, 다친상황_입력창);
	    		   
	    		 // 3. 교통사고로 비용이 발생했어요 선택시
	    	   } else if ("교통사고로 비용이 발생했어요".equals(선택값)) {
	    		   다친신체부위콤보박스.setVisible(true);
	    		   resetComboBox(다른사람콤보박스, 부상유형선택콤보박스, 병원콤보박스, 병원_아팠어요_진단내용콤보박스);
	    		   notVisible(다른사람콤보박스, 부상유형선택콤보박스, 병원콤보박스, 병원_아팠어요_진단내용콤보박스, 다친상황_입력창);
	    		   
	    		   다친신체부위콤보박스.addActionListener((e1) -> {
	    			   부상유형선택콤보박스.setVisible(true); 
	    		   });
	    		   
	    		   부상유형선택콤보박스.addActionListener((e2) -> {
	    			   다친상황_입력창.setVisible(true); 
	    		   });
	    	   }
	    	
	       });
	       
	       병원콤보박스.addActionListener((e) -> {
	    	   Object selected = 병원콤보박스.getSelectedItem();
	    	    if (selected == null) return; // null이면 처리하지 않음
	    	    String 병원선택값 = selected.toString();
	    	   
	    	   notVisible(다른사람콤보박스,다친신체부위콤보박스, 부상유형선택콤보박스);
	    	   resetComboBox(병원_아팠어요_진단내용콤보박스, 다친신체부위콤보박스, 부상유형선택콤보박스);
	    	   
	    	   if ("아팠어요(질병)".equals(병원선택값)) {
	    		   병원_아팠어요_진단내용콤보박스.setVisible(true);
	    		   notVisible(다친신체부위콤보박스, 부상유형선택콤보박스, 다친상황_입력창);
	    		   
	    	   } else if ("다쳤어요(상해)".equals(병원선택값)) {
	    		   다친신체부위콤보박스.setVisible(true);
	    		   notVisible(다른사람콤보박스, 부상유형선택콤보박스, 병원_아팠어요_진단내용콤보박스, 다친상황_입력창);
	    		   
	    		   다친신체부위콤보박스.addActionListener((e1) -> {
	    			   부상유형선택콤보박스.setVisible(true);
	    		   });
	    	   }
	       });
	       
	       // 입력 패널 (직접입력 선택 시만 보여짐)
	       JPanel 다른사람_다쳤어요_사고내용_어디서사고발생_피해정보 = new JPanel(new GridBagLayout());
	       다른사람_다쳤어요_사고내용_어디서사고발생_피해정보.setBorder(BorderFactory.createTitledBorder("피해정보"));
	       다른사람_다쳤어요_사고내용_어디서사고발생_피해정보.setMaximumSize(new Dimension(400, 200)); // 크기 제한
	       다른사람_다쳤어요_사고내용_어디서사고발생_피해정보.setVisible(false);
	       
	       centerPanel.add(다른사람_다쳤어요_사고내용_어디서사고발생_피해정보, BorderLayout.CENTER);
	       
	       
	       다른사람콤보박스.addActionListener ((e) -> {
	    	   Object selected = 다른사람콤보박스.getSelectedItem();
	    	    if (selected == null) return; // null이면 처리하지 않음

	    	    String 다른사람선택값 = selected.toString();
	    	    
	    	   notVisible(병원콤보박스, 병원_아팠어요_진단내용콤보박스, 다친신체부위콤보박스, 부상유형선택콤보박스,
	    			   다친신체부위콤보박스, 부상유형선택콤보박스);
	    	   
	    	   if ("다쳤어요(신체)".equals(다른사람선택값)) {
	    		   다른사람_다쳤어요_사고내용_어디서사고발생_피해정보.setVisible(true);
	    	   } else if ("재산피해를 입혔어요(재물)".equals(다른사람선택값)) {
	    		   
	    	   }
	    	   
	       });
	       
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
	       
	       
//	    // 라디오 버튼
//			JPanel radioButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
//			JRadioButton customerChButton = new JRadioButton(" 계약자(본인)");
//			JRadioButton insuredChButton = new JRadioButton(" 다른사람");
//
//			ButtonGroup chButtonGroup = new ButtonGroup();
//			chButtonGroup.add(customerChButton);
//			chButtonGroup.add(insuredChButton);
//
//			radioButtonPanel.add(customerChButton);
//			radioButtonPanel.add(insuredChButton);
//			centerPanel.add(radioButtonPanel, BorderLayout.NORTH);
//	       
//	       GridBagConstraints gbc = new GridBagConstraints();
//	       gbc.insets = new Insets(30, 30, 30, 10);
//	       gbc.anchor = GridBagConstraints.WEST;
//
//	       JLabel 은행명라벨 = new JLabel("은행명:");
//	       JTextField 은행명필드 = new JTextField(15);
//	       은행명필드.setFont(new Font("굴림", Font.PLAIN, 18));
//	       
//	       JLabel 계좌번호라벨 = new JLabel("계좌번호:");
//	       JTextField 계좌번호필드 = new JTextField(15);
//	       계좌번호필드.setFont(new Font("굴림", Font.PLAIN, 18));

//	    
//
//	       // 은행명 필드 이벤트(필요시 추가)
//	       은행명필드.addFocusListener(new FocusAdapter() {
//	          @Override
//	          public void focusLost(FocusEvent e) {
//	             String inputBankName = 은행명필드.getText();
//	             // 유효성 검사 추가 가능
//	          }
//	       });
//	       
	       
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
	       
	       JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
	       
	       JButton previousButton = new JButton("이전");
	       JButton nextButton = new JButton("다음");
	       buttonPanel.add(previousButton);
	       buttonPanel.add(nextButton);
	       add(buttonPanel, BorderLayout.SOUTH);
	      
	      // 이전버튼 누르면 모든 내용 초기화되는 기능 추가
	      previousButton.addActionListener((e) -> {
	    	  cl.show(parentCardPanel, "AccidentDatePanel");
//	    	  notVisible(null)
//	    	  resetComboBox(상황콤보박스);
	      });
	       
	      nextButton.addActionListener((e) -> {
	    	  
	    	  Object selected = 상황콤보박스.getSelectedItem();
	    	    if (selected == null) return; // null이면 처리하지 않음
	    	    
	    	    if ("다른 사람에게 피해를 입혔어요".equals(selected)) {
	    	    	cl.show(parentCardPanel, "DocumentRegistrationPanel");
	    	    } else {
	    	    	cl.show(parentCardPanel, "ClaimTypePanel");
	    	    	
	    	    }
	      });
	}
	
	private void notVisible (JComponent... components) {
	    for (JComponent comp : components) {
	        comp.setVisible(false);
	    }
	}
	
	private void resetComboBox(JComboBox... comboBoxes) {
	    for (JComboBox cb : comboBoxes) {
	        cb.setSelectedItem(null); // 선택값 초기화
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

