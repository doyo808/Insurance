package customer.claim.gui.newClaim.claimSituation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.concurrent.BrokenBarrierException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import common.database.model.NewClaimDataModel;
import customer.claim.gui.TitlePanel;

// 고객이 입력을 중단한 걸 기록해 뒀다가 나중에 이어서 청구할 수 있는 기능을 넣을 건지..???

// 각각의 콤보박스 값을 선택했을 때 (addActionListener 클래스를 각각 만들 수 있으면 각각 만드는게 깔끔해서 좋을 듯)
public class ClaimSituationPanel extends JPanel {
	
	private JPanel parentCardPanel;
	
    private String[] 상황_1 = {"병원에 다녀왔어요(실손 등)", "다른 사람에게 피해를 입혔어요", "교통사고로 비용이 발생했어요", "사망/장해를 입었어요", "내 재산에 피해가 발생했어요"};

    private String[] 병원_2 = {"아팠어요(질병)", "다쳤어요(상해)"};
    private String[] 다른사람_2 = {"다쳤어요(신체)", "재산피해를 입혔어요(재물)"};
    private String[] 사망장해_2 = {"일반사망", "재해사망", "시력 상실", "청력 상실", "절단", "중추신경 마비", "언어장애", "장기기능 상실", "기타 장해"};
    
    private String[] 병원_아팠어요_3 = {"감기", "허리통증", "장염", "복통", "암", "해당하는 항목이 없어요(직접입력)"};
    private String[] 다친신체부위 = {"머리(얼굴포함)", "어깨/목", "허리", "팔", "손/손목", "다리", "무릎", "발/발목", "해당되는 신체부위가 없어요"};
    private String[] 부상유형선택 = {"상처가 났어요(손상)", "접질렀어요(염좌)", "부러졌어요(골절)", "데였어요(화상)", "해당되는 부상종류가 없어요"};
	
    private String[] 자동차보험사 = {"KB손해보험", "삼성화재", "현대해상", "DB손해보험", "메리츠화재", "롯데손해보험", "한화손해보험", "NH농협손해보험", "하나손해보험", "흥국화재", "MG손해보험", "AXA손해보험", "AIG손해보험", "에이스손해보험", "캐롯손해보험", "리젠트화재보험", "전세버스공제", "택시공제", "개인택시공제", "버스공제", "전국렌터카공제", "화물공제"};
    private	String[] 자동차사고_상황정보 = {"운전중", "탑승중", "보행중"};

    public ClaimSituationPanel(JPanel parentCardPanel, NewClaimDataModel claimData) {
		this.parentCardPanel = parentCardPanel;
	      CardLayout cl = (CardLayout) (parentCardPanel.getLayout());
	      setLayout(new BorderLayout());
	      
	        TitlePanel title = new TitlePanel("청구상황 선택");
	        add(title, BorderLayout.NORTH);

	        // 가운데 centerPanel ------------------------
	        JPanel centerPanel = new JPanel(new BorderLayout());
	        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
	        add(centerPanel, BorderLayout.CENTER);
	        
	        // 중앙 콤보박스 담을 패널 (Y축 방향으로 나열)
	        JPanel comboBoxContainer = new JPanel();
	        comboBoxContainer.setLayout(new BoxLayout(comboBoxContainer, BoxLayout.Y_AXIS));
	        comboBoxContainer.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));
	        centerPanel.add(comboBoxContainer, BorderLayout.NORTH);
	        
	       // 모든 상황에서 - 어디서 발생했는지 적는칸 있음 (근데 이건 주소 API가 있어야 할 것 같은데, 근데 우선 Table에는 주소까지는 안넣어서 지금은...)
//	       String[] 다른사람_다쳤어요_사고내용_어디서사고발생 = {}; // 국내면 주소 검색하면 주소가 뜨는 API 사용, 해외면 간단히 나라명만 입력인데 굳이 해외까지 할필요가 있을까.......??????
	       
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
	       
	       JComboBoxMaker 상황1콤 = new JComboBoxMaker(상황_1);
	       상황1콤.setVisible(true);
	       JComboBoxMaker 병원콤 = new JComboBoxMaker(병원_2);
	       JComboBoxMaker 다른사람콤 = new JComboBoxMaker(다른사람_2);
	       JComboBoxMaker 병원_아팠어요_진단내용콤 = new JComboBoxMaker(병원_아팠어요_3);
	       JComboBoxMaker 다친신체부위콤 = new JComboBoxMaker(다친신체부위);
	       JComboBoxMaker 부상유형선택콤 = new JComboBoxMaker(부상유형선택);
	       JComboBoxMaker 자동차보험사콤보박스 = new JComboBoxMaker(자동차보험사);
	       JComboBoxMaker 자동차사고상황콤보박스 = new JComboBoxMaker(자동차사고_상황정보);
	       
	       JTextField 다친상황_입력창 = new JTextField();
	       다친상황_입력창.setMaximumSize(new Dimension(600, 40));
	       
	       // 각각의 콤보박스 옆에 라벨이 있으면 좋을 것 같은데 이건 기능 다 넣고 생각하자... 이럴 경우에는 grid
//	       JLabel 어떤일발생라벨 = new JLabel("어떤 일이 발생했나요? ");
//	       JLabel 어떤진료라벨 = new JLabel("어떤 진료로 방문하셨나요? ");
	       
//	       String 다른사람_다쳤어요_사고내용; // 고객이 직접 입력하는 JTextField 활용예정 (ex 자전거로 행인을 부딪쳐 다치게 했어요, 반려견이 행인을 물어 다치게 했어요, 엘리베이터 문에 손이 끼어 다쳤어요)
	       
	       comboBoxContainer.add(상황1콤);
	       comboBoxContainer.add(병원콤);
	       comboBoxContainer.add(다른사람콤);
	       comboBoxContainer.add(병원_아팠어요_진단내용콤);
	       comboBoxContainer.add(다친신체부위콤);
	       comboBoxContainer.add(부상유형선택콤);
	       comboBoxContainer.add(자동차보험사콤보박스);
	       comboBoxContainer.add(다친상황_입력창);
	       
	       
//	       JLabel 사고내용입력라벨 = new JLabel("상황을 자세히 알려주세요: ");
	       
	       
	       // 입력 패널 (피해자 정보 입력칸)
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
	       
	       JPanel 피해자정보묻는라디오버튼패널 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
	       JLabel 피해자정보여부라벨 = new JLabel("다치신 분의 정보를 아시나요? : ");
	       JRadioButton yesButton = new JRadioButton(" 예");
	       JRadioButton noButton = new JRadioButton(" 아니오");
	       // 나중에 선택안한거 있으면 다음으로 못넘어가게 하는 그룹묶음
	       ButtonGroup chButtonGroup = new ButtonGroup();
	       chButtonGroup.add(yesButton);
	       chButtonGroup.add(noButton);
	       피해자정보묻는라디오버튼패널.add(피해자정보여부라벨);
	       피해자정보묻는라디오버튼패널.add(yesButton);
	       피해자정보묻는라디오버튼패널.add(noButton);
	       comboBoxContainer.add(피해자정보묻는라디오버튼패널);
	       
	       
	       // ------------- 여기부터

	       JPanel 다른사람_다쳤어요_피해정보패널 = new JPanel(new GridBagLayout());
	       다른사람_다쳤어요_피해정보패널.setBorder(BorderFactory.createTitledBorder("피해정보"));
	       다른사람_다쳤어요_피해정보패널.setMaximumSize(new Dimension(400, 200)); // 크기 제한
	       

	       JLabel 피해자이름라벨 = new JLabel("피해자명 : ");
	       JLabel 피해자연락처라벨 = new JLabel("휴대폰번호 : ");
	       JTextField 피해자이름필드 = new JTextField(9);
	       JTextField 피해자연락처필드 = new JTextField(11);
	       JLabel 피해자재물피해여부라벨 = new JLabel("타인의 재물피해도 발생했나요? : ");
	       JPanel 피해자정보_재물피해여부패널 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
	       피해자정보_재물피해여부패널.add(피해자재물피해여부라벨);
	       피해자정보_재물피해여부패널.add(yesButton);
	       피해자정보_재물피해여부패널.add(noButton);

	       GridBagConstraints gbc = new GridBagConstraints();
	       gbc.insets = new Insets(30, 30, 30, 10);
	       gbc.anchor = GridBagConstraints.WEST;
	       gbc.gridx = 0; gbc.gridy = 0;
	       다른사람_다쳤어요_피해정보패널.add(피해자이름라벨, gbc);
	       gbc.gridx = 1; gbc.gridy = 0;
	       다른사람_다쳤어요_피해정보패널.add(피해자이름필드, gbc);
	       gbc.gridx = 0; gbc.gridy = 1;
	       다른사람_다쳤어요_피해정보패널.add(피해자연락처라벨, gbc);
	       gbc.gridx = 1; gbc.gridy = 1;
	       다른사람_다쳤어요_피해정보패널.add(피해자연락처필드, gbc);
	       gbc.gridx = 0; gbc.gridy = 2;
	       다른사람_다쳤어요_피해정보패널.add(피해자재물피해여부라벨, gbc);
	       gbc.gridx = 1; gbc.gridy = 2;
	       다른사람_다쳤어요_피해정보패널.add(피해자정보_재물피해여부패널, gbc);
	       
	       다른사람_다쳤어요_피해정보패널.setVisible(false);
	       
	       comboBoxContainer.add(다른사람_다쳤어요_피해정보패널, BorderLayout.CENTER);
	       // ----------- 여기까지는 재물피해시 내용만 조금 바꿔서 새로 만들어야한다.
	       
	       
	       yesButton.addActionListener((e) -> {
	    	   다른사람_다쳤어요_피해정보패널.setVisible(true);
				revalidate();
				repaint();
			});
	       
	       noButton.addActionListener((e) -> {
	    	   다른사람_다쳤어요_피해정보패널.setVisible(false);
				revalidate();
				repaint();
			});
	       
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
	       
	       
	       
	       부상유형선택콤.addActionListener((e) -> {
	    	    if (부상유형선택콤.getSelectedItem() == null) return;
	    	    다친상황_입력창.setVisible(true);
	    	});
	       
	       
	       setVisible(false);

	       상황1콤.addActionListener((e) -> {
	    	   Object selected = 상황1콤.getSelectedItem();
	    	    if (selected == null) return; // null이면 처리하지 않음
	    	   
	    	    String 선택값 = selected.toString();
	    	    
	    	 // 1. 병원에 다녀왔어요(실손 등) 선택시
	    	   if ("병원에 다녀왔어요(실손 등)".equals(선택값)) {

	    		   병원콤.setVisible(true);
	    		   resetComboBox(다른사람콤, 다친신체부위콤, 부상유형선택콤);
	    		   notVisible(다른사람콤, 다친신체부위콤, 부상유형선택콤, 다친상황_입력창);
	    		   
	    		// 2. 다른 사람에게 피해를 입혔어요 선택시
	    	   } else if ("다른 사람에게 피해를 입혔어요".equals(선택값)) {
	    		   다른사람콤.setVisible(true);
	    		   resetComboBox(병원콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤);
	    		   notVisible(병원콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 다친상황_입력창);
	    		   
	    		 // 3. 교통사고로 비용이 발생했어요 선택시
	    	   } else if ("교통사고로 비용이 발생했어요".equals(선택값)) {
	    		   다친신체부위콤.setVisible(true);
	    		   resetComboBox(다른사람콤, 부상유형선택콤, 병원콤, 병원_아팠어요_진단내용콤);
	    		   notVisible(다른사람콤, 부상유형선택콤, 병원콤, 병원_아팠어요_진단내용콤, 다친상황_입력창);
	    		   
	    		   다친신체부위콤.addActionListener((e1) -> {
	    			   부상유형선택콤.setVisible(true); 
	    		   });
	    		   
	    		   부상유형선택콤.addActionListener((e2) -> {
	    			   다친상황_입력창.setVisible(true); 
	    		   });
	    	   }
	       });
	       
	       병원콤.addActionListener((e) -> {
	    	   Object selected = 병원콤.getSelectedItem();
	    	    if (selected == null) return; // null이면 처리하지 않음
	    	    
	    	    String 병원선택값 = selected.toString();
	    	   notVisible(다른사람콤,다친신체부위콤, 부상유형선택콤);
	    	   resetComboBox(병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤);
	    	   
	    	   if ("아팠어요(질병)".equals(병원선택값)) {
	    		   병원_아팠어요_진단내용콤.setVisible(true);
	    		   notVisible(다친신체부위콤, 부상유형선택콤, 다친상황_입력창);
	    		   
	    	   } else if ("다쳤어요(상해)".equals(병원선택값)) {
	    		   다친신체부위콤.setVisible(true);
	    		   notVisible(다른사람콤, 부상유형선택콤, 병원_아팠어요_진단내용콤, 다친상황_입력창);
	    		   
	    		   다친신체부위콤.addActionListener((e1) -> {
	    			   부상유형선택콤.setVisible(true);
	    		   });
	    	   }
	       });
	       
	       다른사람콤.addActionListener ((e) -> {
	    	   Object selected = 다른사람콤.getSelectedItem();
	    	    
	    	   if (selected == null) {
	    		   System.out.println("선택된 항목이 없습니다."); // 디버깅용
	    		   return; // null이면 처리하지 않음
	    	   }
	    	   String 다른사람선택값 = selected.toString();
	    	    
	    	   notVisible(병원콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤,
	    			   다친신체부위콤, 부상유형선택콤);
	    	   if ("다쳤어요(신체)".equals(다른사람선택값)) {
	    		   다친상황_입력창.setVisible(true);
	    		   피해자정보묻는라디오버튼패널.setVisible(true);
	    		   피해자정보_재물피해여부패널.setVisible(false);
	    	   } else if ("재산피해를 입혔어요(재물)".equals(다른사람선택값)) {
	    		   다른사람_다쳤어요_피해정보패널.setVisible(false);
	    		   피해자정보묻는라디오버튼패널.setVisible(false);
	    		   피해자정보_재물피해여부패널.setVisible(true);
	    	   }
	    	   
	       });
	       
//	       ----- 하단 버튼 패널 -------------------------------------
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
				// 고객이 모든 정보를 빠짐없이 입력했는지 확인해야 하고,
				if (상황1콤.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(this, "청구상황을 선택해주세요.", "안내", JOptionPane.WARNING_MESSAGE);
					return; // null이면 처리하지 않음
				}

				// 필수적인 항목을 여기서 다시 한번 확인 ex
				if (상황1콤.getSelectedItem().equals("다른 사람에게 피해를 입혔어요") && 다른사람콤.getSelectedItem().equals("다쳤어요(신체)")) {
					if (chButtonGroup.getSelection() == null) {
						JOptionPane.showMessageDialog(this, "피해자 정보 여부를 알려주세요.", "안내", JOptionPane.WARNING_MESSAGE);
						return;
					}
					if (피해자이름필드.getText().trim().isBlank() || 피해자연락처필드.getText().trim().isBlank()) {
						JOptionPane.showMessageDialog(this, "피해자 정보를 입력해주세요", "안내", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				
				// 기록된 정보가 객체에 저장되어야 한다.
				if (상황1콤.getSelectedItem().equals("다른 사람에게 피해를 입혔어요")
						&& 다른사람콤.getSelectedItem().equals("재산피해를 입혔어요(재물)")) {
					claimData.setCompensation_type('P');
				} else {
					claimData.setCompensation_type('H');
				}

				String selectedSituation = (String) 상황1콤.getSelectedItem();
				switch (selectedSituation) {
					case "병원에 다녀왔어요(실손 등)":
						if (병원콤.getSelectedItem().equals("아팠어요(질병)")) {
							claimData.setClaim_category("질병");
							break;
						} else if (병원콤.getSelectedItem().equals("다쳤어요(상해)")) {
							claimData.setClaim_category("상해");
							break;
						}
					case "다른 사람에게 피해를 입혔어요":
						claimData.setClaim_category("배상책임");
						break;
					case "교통사고로 비용이 발생했어요":
						claimData.setClaim_category("교통사고");
						break;
					case "사망/장해를 입었어요":
						claimData.setClaim_category("사망/장해");
						break;
					case "내 재산에 피해가 발생했어요":
						claimData.setClaim_category("재산손해");
						break;
					default:
						break;
				}

				// 진단코드 (각 콤보박스를 하나씩 연결해서 그 진단코드를 다 입력해야하는거겠지...)

				if (다친상황_입력창 != null && 다친상황_입력창.isValid()) {
					claimData.setAccident_description(다친상황_입력창.getText());
				}

				// 상황에 맞는 페이지로 넘어갈 수 있도록 하고,
				Object selected = 상황1콤.getSelectedItem();
				if ("다른 사람에게 피해를 입혔어요".equals(selected)) {
					cl.show(parentCardPanel, "DocumentRegistrationPanel");
				}

				cl.show(parentCardPanel, "ClaimTypePanel");

				System.out.println(claimData.toString()); // 디버깅용
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
	// 모든 콤보박스 초기화 언제하냐...
	public void resetPanel() {
		
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

class RButton extends JRadioButton {
	
}
