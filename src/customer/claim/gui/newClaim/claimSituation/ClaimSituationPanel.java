package customer.claim.gui.newClaim.claimSituation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import common.database.model.NewClaimDataModel;
import customer.claim.gui.component.BottomButtonPanel;
import customer.claim.gui.component.TitlePanel;

// 고객이 입력을 중단한 걸 기록해 뒀다가 나중에 이어서 청구할 수 있는 기능을 넣을 건지..???

// 각각의 콤보박스 값을 선택했을 때 (addActionListener 클래스를 각각 만들 수 있으면 각각 만드는게 깔끔해서 좋을 듯)
public class ClaimSituationPanel extends JPanel {
	private JPanel parentCardPanel;
	// 첫 번째 상황 선택
    private String[] 상황_1 = {"청구상황을 선택해 주세요", "병원에 다녀왔어요(실손 등)", "다른 사람에게 피해를 입혔어요", "교통사고로 비용이 발생했어요", "사망/장해를 입었어요", "내 재산에 피해가 발생했어요"};
    // 두 번째 상황 선택
    private String[] 병원_2 = {"아팠어요(질병)", "다쳤어요(상해)"};
    private String[] 다른사람_2 = {"다쳤어요(신체)", "재산피해를 입혔어요(재물)"};
    private String[] 사망장해_2 = {"일반사망", "재해사망", "시력 상실", "청력 상실", "절단", "중추신경 마비", "언어장애", "장기기능 상실", "기타 장해"};
    // 
    private String[] 병원_아팠어요_3 = {"감기", "허리통증", "장염", "복통", "암", "해당하는 항목이 없어요(직접입력)"};
    private String[] 다친신체부위 = {"머리(얼굴포함)", "어깨/목", "허리", "팔", "손/손목", "다리", "무릎", "발/발목", "해당되는 신체부위가 없어요"};
    private String[] 부상유형선택 = {"상처가 났어요(손상)", "접질렀어요(염좌)", "부러졌어요(골절)", "데였어요(화상)", "해당되는 부상종류가 없어요"};
	
    private String[] 자동차보험사 = {"KB손해보험", "삼성화재", "현대해상", "DB손해보험", "메리츠화재", "롯데손해보험", "한화손해보험", "NH농협손해보험", "하나손해보험", "흥국화재", "MG손해보험", "AXA손해보험", "AIG손해보험", "에이스손해보험", "캐롯손해보험", "리젠트화재보험", "전세버스공제", "택시공제", "개인택시공제", "버스공제", "전국렌터카공제", "화물공제"};
    private	String[] 자동차사고_상황정보 = {"운전중", "탑승중", "보행중"};

    private JPanel centerPanel = new JPanel(new BorderLayout());
    
    // 콤보박스 생성
    private JComboBoxMaker 상황1콤 = new JComboBoxMaker(상황_1);
    private JComboBoxMaker 병원콤 = new JComboBoxMaker(병원_2);
    private JComboBoxMaker 다른사람콤 = new JComboBoxMaker(다른사람_2);
    private JComboBoxMaker 사망장해상황콤보박스 = new JComboBoxMaker(사망장해_2);
    private JComboBoxMaker 병원_아팠어요_진단내용콤 = new JComboBoxMaker(병원_아팠어요_3);
    private JComboBoxMaker 다친신체부위콤 = new JComboBoxMaker(다친신체부위);
    private JComboBoxMaker 부상유형선택콤 = new JComboBoxMaker(부상유형선택);
    private JComboBoxMaker 자동차보험사콤보박스 = new JComboBoxMaker(자동차보험사);
    private JComboBoxMaker 자동차사고상황콤보박스 = new JComboBoxMaker(자동차사고_상황정보);
    // 중앙 콤보박스 담을 패널 (Y축 방향으로 나열)
    private JPanel comboBoxContainer = new JPanel();
    private JTextField 다친상황_입력창 = new JTextField();
    
    // "다른 사람에게 피해를 입혔어요" 中 "다쳤어요(신체)" or "재산피해를 입혔어요(재물)" 선택시 나타나는 패널
    private VictimInformationOrNotPanel 피해자정보묻는라디오버튼패널 = new VictimInformationOrNotPanel();
    private VictimInformationOrNotPanel 소유자정보묻는라디오버튼패널 = new VictimInformationOrNotPanel();
    private victimInfoPanel 다른사람_다쳤어요_피해정보패널 = new victimInfoPanel();
    private DamagedPropertyInfoPanel 다른사람_다쳤어요_피해정보패널_재물 = new DamagedPropertyInfoPanel();
    
    public ClaimSituationPanel(JPanel parentCardPanel, NewClaimDataModel claimData) {
		this.parentCardPanel = parentCardPanel;
	      CardLayout cl = (CardLayout) (parentCardPanel.getLayout());
	      setLayout(new BorderLayout());
	      
	        TitlePanel title = new TitlePanel("청구상황 선택");
	        add(title, BorderLayout.NORTH);
	        
	        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
	        add(centerPanel, BorderLayout.CENTER);
	        
	        comboBoxContainer.setLayout(new BoxLayout(comboBoxContainer, BoxLayout.Y_AXIS));
	        comboBoxContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        centerPanel.add(comboBoxContainer, BorderLayout.NORTH);
	        
	       // 모든 상황에서 - 어디서 발생했는지 적는칸 있음 (근데 이건 주소 API가 있어야 할 것 같은데, 근데 우선 Table에는 주소까지는 안넣어서 지금은...)
	       
	       /*
				피해 정보를 입력해주세요
				"자동차 보험으로 보상을 받으셨나요?"
				예->
				String 차량번호정보 = ""; // 고객이 직접 입력하는 JTextField 활용예정
				
				아니오 -> 
				입력칸 없이 계좌이체로 넘어감
	        */
	       
	       상황1콤.setVisible(true);
	       다친상황_입력창.setMaximumSize(new Dimension(600, 40));
	       
	       // 각각의 콤보박스 옆에 라벨이 있으면 좋을 것 같은데 이건 기능 다 넣고 생각하자... 이럴 경우에는 grid
//	       JLabel 어떤일발생라벨 = new JLabel("어떤 일이 발생했나요? ");
//	       JLabel 어떤진료라벨 = new JLabel("어떤 진료로 방문하셨나요? ");
	       
	       comboBoxContainer.add(상황1콤);
	       comboBoxContainer.add(병원콤);
	       comboBoxContainer.add(다른사람콤);
	       comboBoxContainer.add(병원_아팠어요_진단내용콤);
	       comboBoxContainer.add(다친신체부위콤);
	       comboBoxContainer.add(부상유형선택콤);
	       comboBoxContainer.add(자동차보험사콤보박스);
	       comboBoxContainer.add(다친상황_입력창);
	       다친상황_입력창.setVisible(false);
	       comboBoxContainer.add(사망장해상황콤보박스);
	       
	       피해자정보묻는라디오버튼패널.set피해자정보여부라벨("다치신 분의 정보를 아시나요? : ");
	       comboBoxContainer.add(피해자정보묻는라디오버튼패널);
	       피해자정보묻는라디오버튼패널.setVisible(false);
	       
	       피해자정보묻는라디오버튼패널.get피해자정보yesButton().addActionListener((e) -> {
	    	   다른사람_다쳤어요_피해정보패널.setVisible(true);
				revalidate();
				repaint();
			});
	       피해자정보묻는라디오버튼패널.get피해자정보noButton().addActionListener((e) -> {
	    	   다른사람_다쳤어요_피해정보패널.setVisible(false);
	    	   다른사람_다쳤어요_피해정보패널.reset();
				revalidate();
				repaint();
			});
	       
	       소유자정보묻는라디오버튼패널.set피해자정보여부라벨("피해를 입으신 분의 정보를 아시나요? : ");
	       comboBoxContainer.add(소유자정보묻는라디오버튼패널);
	       소유자정보묻는라디오버튼패널.setVisible(false);

	       comboBoxContainer.add(다른사람_다쳤어요_피해정보패널);
	       다른사람_다쳤어요_피해정보패널.setVisible(false);
	       
	       
	       comboBoxContainer.add(다른사람_다쳤어요_피해정보패널_재물);
	       다른사람_다쳤어요_피해정보패널_재물.setVisible(false);
	       
	       소유자정보묻는라디오버튼패널.get피해자정보yesButton().addActionListener((e) -> {
	    	   다른사람_다쳤어요_피해정보패널_재물.setVisible(true);
				revalidate();
				repaint();
	       });
	       
	       소유자정보묻는라디오버튼패널.get피해자정보noButton().addActionListener((e) -> {
	    	   다른사람_다쳤어요_피해정보패널_재물.setVisible(false);
	    	   다른사람_다쳤어요_피해정보패널_재물.reset();
				revalidate();
				repaint();
	       });
	   	      
	       
// resetComboBox(상황1콤, 병원콤, 다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스);
// notVisible(상황1콤, 병원콤, 다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스, 다친상황_입력창);	    	    
	       
	       상황1콤.addActionListener((e) -> {
	    	   Object selected = 상황1콤.getSelectedItem();
	    	    if (selected == null) return; // null이면 처리하지 않음
	    	    String 선택값 = selected.toString();
	    	    
	    	   if ("병원에 다녀왔어요(실손 등)".equals(선택값)) {
	    		   병원콤.setVisible(true);
	    		   resetComboBox(다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스);
	    		   notVisible(다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스, 다친상황_입력창, 피해자정보묻는라디오버튼패널, 다른사람_다쳤어요_피해정보패널);
	    		   
	    	   } else if ("다른 사람에게 피해를 입혔어요".equals(선택값)) {
	    		   다른사람콤.setVisible(true);
	    		   resetComboBox(병원콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스);
	    		   notVisible(병원콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스, 다친상황_입력창, 피해자정보묻는라디오버튼패널, 다른사람_다쳤어요_피해정보패널);
	    		   
	    	   } else if ("교통사고로 비용이 발생했어요".equals(선택값)) {
	    		   다친신체부위콤.setVisible(true);
	    		   resetComboBox(다른사람콤, 병원콤, 부상유형선택콤,  병원_아팠어요_진단내용콤, 사망장해상황콤보박스);
	    		   notVisible(다른사람콤, 병원콤, 부상유형선택콤,  병원_아팠어요_진단내용콤, 사망장해상황콤보박스, 다친상황_입력창, 피해자정보묻는라디오버튼패널, 다른사람_다쳤어요_피해정보패널);
	    		   
	    	   } else if ("사망/장해를 입었어요".equals(선택값)) {
	    		   사망장해상황콤보박스.setVisible(true);
	    		   resetComboBox(병원콤, 다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤);
	    		   notVisible(병원콤, 다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 다친상황_입력창, 피해자정보묻는라디오버튼패널, 다른사람_다쳤어요_피해정보패널);
	    		   
	    	   } else if ("내 재산에 피해가 발생했어요".equals(선택값)) {
	    		   다친상황_입력창.setVisible(true);
	    		   resetComboBox(병원콤, 다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스);
	    		   notVisible(병원콤, 다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스, 피해자정보묻는라디오버튼패널, 다른사람_다쳤어요_피해정보패널);
	    	   } else if ("청구상황을 선택해 주세요".equals(선택값)) {
	    		   resetComboBox(병원콤, 다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스);
	    		   notVisible(병원콤, 다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스, 다친상황_입력창, 피해자정보묻는라디오버튼패널, 다른사람_다쳤어요_피해정보패널);	
	    	   }
	       });
	       
	       부상유형선택콤.addActionListener((e) -> {
	    	    if (부상유형선택콤.getSelectedItem() == null) return;
	    	    다친상황_입력창.setVisible(true);
	    	});
	       
	       병원_아팠어요_진단내용콤.addActionListener((e) -> {
	    	   Object selected = 병원_아팠어요_진단내용콤.getSelectedItem();
	    	    if (selected == null) return; // null이면 처리하지 않음
	    	    String 선택값 = selected.toString();
	    	    
	    	    if ("해당하는 항목이 없어요(직접입력)".equals(선택값)) {
	    	    	다친상황_입력창.setVisible(true);
	    	    } else {
	    	    	다친상황_입력창.setVisible(false);
	    	    }
	       });
	       
	       다친신체부위콤.addActionListener((e) -> {
	    	   Object selected = 다친신체부위콤.getSelectedItem();
	    	    if (selected == null) return; // null이면 처리하지 않음
			   부상유형선택콤.setVisible(true); 
		   });
		   
		   부상유형선택콤.addActionListener((e) -> {
			   Object selected = 부상유형선택콤.getSelectedItem();
	    	    if (selected == null) return; 
			   다친상황_입력창.setVisible(true); 
		   });
	       
	       병원콤.addActionListener((e) -> {
	    	   Object selected = 병원콤.getSelectedItem();
	    	    if (selected == null) return; 
	    	    String 병원선택값 = selected.toString();
	    	   
	    	   if ("아팠어요(질병)".equals(병원선택값)) {
	    		   병원_아팠어요_진단내용콤.setVisible(true);
	    		   notVisible(다친신체부위콤, 부상유형선택콤, 다친상황_입력창);
	    		   
	    	   } else if ("다쳤어요(상해)".equals(병원선택값)) {
	    		   다친신체부위콤.setVisible(true);
	    		   notVisible(다른사람콤, 부상유형선택콤, 병원_아팠어요_진단내용콤, 다친상황_입력창);
	    	   }
	       });
	       
	       다른사람콤.addActionListener ((e) -> {
	    	   Object selected = 다른사람콤.getSelectedItem();
	    	   if (selected == null) return; 
	    	   
	    	   String 다른사람선택값 = selected.toString();
	    	    
	    	   if ("다쳤어요(신체)".equals(다른사람선택값)) {
	    		   다친상황_입력창.setVisible(true);
	    		   피해자정보묻는라디오버튼패널.setVisible(true);
	    		   다른사람_다쳤어요_피해정보패널.setVisible(false);
	    		   notVisible(소유자정보묻는라디오버튼패널, 다른사람_다쳤어요_피해정보패널_재물);
	    		   
	    	   } else if ("재산피해를 입혔어요(재물)".equals(다른사람선택값)) {
	    		   다친상황_입력창.setVisible(true);
	    		   소유자정보묻는라디오버튼패널.setVisible(true);
	    		   다른사람_다쳤어요_피해정보패널_재물.setVisible(false);
	    		   notVisible(피해자정보묻는라디오버튼패널, 다른사람_다쳤어요_피해정보패널);
	    	   }
	       });
	       
	       BottomButtonPanel BBP = new BottomButtonPanel(this);
	      
	      // 이전버튼 누르면 모든 내용 초기화되는 기능 계속 수정하기
			BBP.getPreviousButton().addActionListener((e) -> {
				cl.show(parentCardPanel, "AccidentDatePanel");
				allResetAndNotVisible();
			});
	       
			// 다음버튼 actionListener ===============================================================================================
			BBP.getNextButton().addActionListener((e) -> {
				Object selected = 상황1콤.getSelectedItem();
	    	    if (selected == null) return; // null이면 처리하지 않음
	    	    String 상황1선택 = (String) 상황1콤.getSelectedItem();
	    	    String 다른사람선택 = (String) 다른사람콤.getSelectedItem(); 
	    	    String 병원선택 = (String) 병원콤.getSelectedItem();
				
				// 고객이 모든 정보를 빠짐없이 입력했는지 확인해야 하고,
				if (selected == null || "청구상황을 선택해 주세요".equals(상황1선택)) {
					JOptionPane.showMessageDialog(this, "청구상황을 선택해주세요.", "안내", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (다친상황_입력창.isShowing() && 다친상황_입력창.getText().trim().isBlank()) {
					JOptionPane.showMessageDialog(this, "자세한 상황을 입력해주세요.", "안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if ("다른 사람에게 피해를 입혔어요".equals(상황1선택) && "다쳤어요(신체)".equals(다른사람선택)) {
					if (피해자정보묻는라디오버튼패널.getChButtonGroup().getSelection() == null) {
						JOptionPane.showMessageDialog(this, "피해자 정보 여부를 알려주세요.", "안내", JOptionPane.WARNING_MESSAGE);
						return;
					}
					if (다른사람_다쳤어요_피해정보패널.get피해자이름필드().getText().trim().isBlank() || 
						다른사람_다쳤어요_피해정보패널.get피해자연락처필드().getText().trim().isBlank()) {
						JOptionPane.showMessageDialog(this, "피해자 정보를 입력해주세요", "안내", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				if ("다른 사람에게 피해를 입혔어요".equals(상황1선택) && "재산피해를 입혔어요(재물)".equals(다른사람선택)) {
					if (소유자정보묻는라디오버튼패널.getChButtonGroup().getSelection() == null) {
						JOptionPane.showMessageDialog(this, "소유자 정보 여부를 알려주세요.", "안내", JOptionPane.WARNING_MESSAGE);
						return;
					}
					if (다른사람_다쳤어요_피해정보패널_재물.get소유자이름필드().getText().trim().isBlank() || 
						다른사람_다쳤어요_피해정보패널_재물.get소유자연락처필드().getText().trim().isBlank()) {
						JOptionPane.showMessageDialog(this, "소유자 정보를 입력해주세요", "안내", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				
				// 기록된 정보가 객체에 저장되어야 한다.
				if ("다른 사람에게 피해를 입혔어요".equals(상황1선택) &&	"재산피해를 입혔어요(재물)".equals(다른사람선택) ||
					"내 재산에 피해가 발생했어요".equals(상황1선택)) {
					claimData.setCompensation_type('P');
				} else {
					claimData.setCompensation_type('H');
				}

				switch (상황1선택) {
					case "병원에 다녀왔어요(실손 등)":
						if ("아팠어요(질병)".equals(병원선택)) {
							claimData.setClaim_category("질병");
							break;
						} else if ("다쳤어요(상해)".equals(병원선택)) {
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

				// 진단코드 입력하기 (각 콤보박스를 하나씩 연결해서 그 진단코드를 다 입력해야하는거겠지...)

				if (다친상황_입력창 != null && 다친상황_입력창.isShowing()) {
					claimData.setAccident_description(다친상황_입력창.getText());
				}

				// 상황에 맞는 페이지로 넘어갈 수 있도록 하고,
				if ("다른 사람에게 피해를 입혔어요".equals(상황1선택)) {
					cl.show(parentCardPanel, "DocumentRegistrationPanel"); // 배상책임의 경우 보험금을 받는 것이 아니기 때문에 바로 서류첨부 패널로 이동
				}
				cl.show(parentCardPanel, "ClaimTypePanel");

				System.out.println(claimData.toString()); // 디버깅용
			});
			
			// 다음버튼 actionListener ===============================================================================================
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
	
	private void allResetAndNotVisible() {
		resetComboBox(상황1콤, 병원콤, 다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스);
		 notVisible(병원콤, 다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스, 다친상황_입력창);
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