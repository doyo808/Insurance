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
    private String[] firstS = {"청구상황을 선택해 주세요", "병원에 다녀왔어요(실손 등)", "다른 사람에게 피해를 입혔어요", "교통사고로 비용이 발생했어요", "사망/장해를 입었어요", "내 재산에 피해가 발생했어요"};
    // 두 번째 상황 선택
    private String[] hospitalS = {"아팠어요(질병)", "다쳤어요(상해)"};
    private String[] otherPeoS = {"다쳤어요(신체)", "재산피해를 입혔어요(재물)"};
    private String[] deathS = {"일반사망", "재해사망", "시력 상실", "청력 상실", "절단", "중추신경 마비", "언어장애", "장기기능 상실", "기타 장해"};
    // 
    private String[] diagnosisS = {"감기", "허리통증", "장염", "복통", "암", "해당하는 항목이 없어요(직접입력)"};
    private String[] bodyPart = {"머리(얼굴포함)", "어깨/목", "허리", "팔", "손/손목", "다리", "무릎", "발/발목", "해당되는 신체부위가 없어요"};
    private String[] injuryS = {"상처가 났어요(손상)", "접질렀어요(염좌)", "부러졌어요(골절)", "데였어요(화상)", "해당되는 부상종류가 없어요"};
	
    private String[] carInsucompanies = {"KB손해보험", "삼성화재", "현대해상", "DB손해보험", "메리츠화재", "롯데손해보험", "한화손해보험", "NH농협손해보험", "하나손해보험", "흥국화재", "MG손해보험", "AXA손해보험", "AIG손해보험", "에이스손해보험", "캐롯손해보험", "리젠트화재보험", "전세버스공제", "택시공제", "개인택시공제", "버스공제", "전국렌터카공제", "화물공제"};
    private	String[] carAccidentS = {"운전중", "탑승중", "보행중"};

    private JPanel centerPanel = new JPanel(new BorderLayout());
    
    // 콤보박스 생성
    private JComboBoxMaker firstSCB = new JComboBoxMaker(firstS);
    private JComboBoxMaker hospitalSCB = new JComboBoxMaker(hospitalS);
    private JComboBoxMaker otherPeoSCB = new JComboBoxMaker(otherPeoS);
    private JComboBoxMaker deathSCB = new JComboBoxMaker(deathS);
    private JComboBoxMaker diagnosisSCB = new JComboBoxMaker(diagnosisS);
    private JComboBoxMaker bodyPartCB = new JComboBoxMaker(bodyPart);
    private JComboBoxMaker injurySCB = new JComboBoxMaker(injuryS);
    private JComboBoxMaker carInsucompaniesCB = new JComboBoxMaker(carInsucompanies);
    private JComboBoxMaker carAccidentSCB = new JComboBoxMaker(carAccidentS);
    
    // 중앙 콤보박스 담을 패널 (Y축 방향으로 나열)
    private JPanel comboBoxContainer = new JPanel();
    private JTextField detailSituationF = new JTextField();
    
    // "다른 사람에게 피해를 입혔어요" 中 "다쳤어요(신체)" or "재산피해를 입혔어요(재물)" 선택시 나타나는 패널
    private VictimInformationOrNotPanel victimInfoONP = new VictimInformationOrNotPanel();
    private VictimInformationOrNotPanel ownerInfONP = new VictimInformationOrNotPanel();
    private victimInfoPanel victimInfoP = new victimInfoPanel();
    private DamagedPropertyInfoPanel propertyInfoP = new DamagedPropertyInfoPanel();
    
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
	       
	       firstSCB.setVisible(true);
	       detailSituationF.setMaximumSize(new Dimension(600, 40));
	       
	       // 각각의 콤보박스 옆에 라벨이 있으면 좋을 것 같은데 이건 기능 다 넣고 생각하자... 이럴 경우에는 grid
//	       JLabel 어떤일발생라벨 = new JLabel("어떤 일이 발생했나요? ");
//	       JLabel 어떤진료라벨 = new JLabel("어떤 진료로 방문하셨나요? ");
	       
	       comboBoxContainer.add(firstSCB);
	       comboBoxContainer.add(hospitalSCB);
	       comboBoxContainer.add(otherPeoSCB);
	       comboBoxContainer.add(diagnosisSCB);
	       comboBoxContainer.add(bodyPartCB);
	       comboBoxContainer.add(injurySCB);
	       comboBoxContainer.add(carInsucompaniesCB);
	       comboBoxContainer.add(detailSituationF);
	       detailSituationF.setVisible(false);
	       comboBoxContainer.add(deathSCB);
	       
	       victimInfoONP.setVictimInfoL("다치신 분의 정보를 아시나요? : ");
	       comboBoxContainer.add(victimInfoONP);
	       victimInfoONP.setVisible(false);
	       
	       victimInfoONP.getYesB().addActionListener((e) -> {
	    	   victimInfoP.setVisible(true);
				revalidate();
				repaint();
			});
	       victimInfoONP.getNoB().addActionListener((e) -> {
	    	   victimInfoP.setVisible(false);
	    	   victimInfoP.reset();
				revalidate();
				repaint();
			});
	       
	       ownerInfONP.setVictimInfoL("피해를 입으신 분의 정보를 아시나요? : ");
	       comboBoxContainer.add(ownerInfONP);
	       ownerInfONP.setVisible(false);

	       comboBoxContainer.add(victimInfoP);
	       victimInfoP.setVisible(false);
	       
	       
	       comboBoxContainer.add(propertyInfoP);
	       propertyInfoP.setVisible(false);
	       
	       ownerInfONP.getYesB().addActionListener((e) -> {
	    	   propertyInfoP.setVisible(true);
				revalidate();
				repaint();
	       });
	       
	       ownerInfONP.getNoB().addActionListener((e) -> {
	    	   propertyInfoP.setVisible(false);
	    	   propertyInfoP.reset();
				revalidate();
				repaint();
	       });
	   	      
	       
// resetComboBox(상황1콤, 병원콤, 다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스);
// notVisible(상황1콤, 병원콤, 다른사람콤, 병원_아팠어요_진단내용콤, 다친신체부위콤, 부상유형선택콤, 사망장해상황콤보박스, 다친상황_입력창);	    	    
	       
	       firstSCB.addActionListener((e) -> {
	    	   Object selected = firstSCB.getSelectedItem();
	    	    if (selected == null) return; // null이면 처리하지 않음
	    	    String 선택값 = selected.toString();
	    	    
	    	   if ("병원에 다녀왔어요(실손 등)".equals(선택값)) {
	    		   hospitalSCB.setVisible(true);
	    		   resetComboBox(otherPeoSCB, diagnosisSCB, bodyPartCB, injurySCB, deathSCB);
	    		   notVisible(otherPeoSCB, diagnosisSCB, bodyPartCB, injurySCB, deathSCB, detailSituationF, victimInfoONP, victimInfoP);
	    		   
	    	   } else if ("다른 사람에게 피해를 입혔어요".equals(선택값)) {
	    		   otherPeoSCB.setVisible(true);
	    		   resetComboBox(hospitalSCB, diagnosisSCB, bodyPartCB, injurySCB, deathSCB);
	    		   notVisible(hospitalSCB, diagnosisSCB, bodyPartCB, injurySCB, deathSCB, detailSituationF, victimInfoONP, victimInfoP);
	    		   
	    	   } else if ("교통사고로 비용이 발생했어요".equals(선택값)) {
	    		   bodyPartCB.setVisible(true);
	    		   resetComboBox(otherPeoSCB, hospitalSCB, injurySCB,  diagnosisSCB, deathSCB);
	    		   notVisible(otherPeoSCB, hospitalSCB, injurySCB,  diagnosisSCB, deathSCB, detailSituationF, victimInfoONP, victimInfoP);
	    		   
	    	   } else if ("사망/장해를 입었어요".equals(선택값)) {
	    		   deathSCB.setVisible(true);
	    		   resetComboBox(hospitalSCB, otherPeoSCB, diagnosisSCB, bodyPartCB, injurySCB);
	    		   notVisible(hospitalSCB, otherPeoSCB, diagnosisSCB, bodyPartCB, injurySCB, detailSituationF, victimInfoONP, victimInfoP);
	    		   
	    	   } else if ("내 재산에 피해가 발생했어요".equals(선택값)) {
	    		   detailSituationF.setVisible(true);
	    		   resetComboBox(hospitalSCB, otherPeoSCB, diagnosisSCB, bodyPartCB, injurySCB, deathSCB);
	    		   notVisible(hospitalSCB, otherPeoSCB, diagnosisSCB, bodyPartCB, injurySCB, deathSCB, victimInfoONP, victimInfoP);
	    	   } else if ("청구상황을 선택해 주세요".equals(선택값)) {
	    		   resetComboBox(hospitalSCB, otherPeoSCB, diagnosisSCB, bodyPartCB, injurySCB, deathSCB);
	    		   notVisible(hospitalSCB, otherPeoSCB, diagnosisSCB, bodyPartCB, injurySCB, deathSCB, detailSituationF, victimInfoONP, victimInfoP);	
	    	   }
	       });
	       
	       injurySCB.addActionListener((e) -> {
	    	    if (injurySCB.getSelectedItem() == null) return;
	    	    detailSituationF.setVisible(true);
	    	});
	       
	       diagnosisSCB.addActionListener((e) -> {
	    	   Object selected = diagnosisSCB.getSelectedItem();
	    	    if (selected == null) return; // null이면 처리하지 않음
	    	    String 선택값 = selected.toString();
	    	    
	    	    if ("해당하는 항목이 없어요(직접입력)".equals(선택값)) {
	    	    	detailSituationF.setVisible(true);
	    	    } else {
	    	    	detailSituationF.setVisible(false);
	    	    }
	       });
	       
	       bodyPartCB.addActionListener((e) -> {
	    	   Object selected = bodyPartCB.getSelectedItem();
	    	    if (selected == null) return; // null이면 처리하지 않음
			   injurySCB.setVisible(true); 
		   });
		   
		   injurySCB.addActionListener((e) -> {
			   Object selected = injurySCB.getSelectedItem();
	    	    if (selected == null) return; 
			   detailSituationF.setVisible(true); 
		   });
	       
	       hospitalSCB.addActionListener((e) -> {
	    	   Object selected = hospitalSCB.getSelectedItem();
	    	    if (selected == null) return; 
	    	    String 병원선택값 = selected.toString();
	    	   
	    	   if ("아팠어요(질병)".equals(병원선택값)) {
	    		   diagnosisSCB.setVisible(true);
	    		   notVisible(bodyPartCB, injurySCB, detailSituationF);
	    		   
	    	   } else if ("다쳤어요(상해)".equals(병원선택값)) {
	    		   bodyPartCB.setVisible(true);
	    		   notVisible(otherPeoSCB, injurySCB, diagnosisSCB, detailSituationF);
	    	   }
	       });
	       
	       otherPeoSCB.addActionListener ((e) -> {
	    	   Object selected = otherPeoSCB.getSelectedItem();
	    	   if (selected == null) return; 
	    	   
	    	   String 다른사람선택값 = selected.toString();
	    	    
	    	   if ("다쳤어요(신체)".equals(다른사람선택값)) {
	    		   detailSituationF.setVisible(true);
	    		   victimInfoONP.setVisible(true);
	    		   victimInfoP.setVisible(false);
	    		   notVisible(ownerInfONP, propertyInfoP);
	    		   
	    	   } else if ("재산피해를 입혔어요(재물)".equals(다른사람선택값)) {
	    		   detailSituationF.setVisible(true);
	    		   ownerInfONP.setVisible(true);
	    		   propertyInfoP.setVisible(false);
	    		   notVisible(victimInfoONP, victimInfoP);
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
				Object selected = firstSCB.getSelectedItem();
	    	    if (selected == null) {
	    	    	JOptionPane.showMessageDialog(this, "상황을 선택해주세요", "안내", JOptionPane.WARNING_MESSAGE);	    	    	
	    	    	return; // null이면 처리하지 않음
	    	    }
	    	    String firstSCB_C = (String) selected;
	    	    String otherPeoSCB_C = (String) otherPeoSCB.getSelectedItem(); 
	    	    String hospitalSCB_C = (String) hospitalSCB.getSelectedItem();
	    	    String diagnosisSCB_C = (String) diagnosisSCB.getSelectedItem();
	    	    String bodyPartCB_C = (String) bodyPartCB.getSelectedItem();
	    	    String injurySCB_C = (String) injurySCB.getSelectedItem();
	    	    String deathSCB_C = (String) deathSCB.getSelectedItem();
	    	    
				// 고객이 모든 정보를 빠짐없이 입력했는지 확인해야 하고,
				if (selected == null || "청구상황을 선택해 주세요".equals(firstSCB_C)) {
					JOptionPane.showMessageDialog(this, "청구상황을 선택해주세요.", "안내", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (detailSituationF.isShowing() && detailSituationF.getText().trim().isBlank()) {
					JOptionPane.showMessageDialog(this, "자세한 상황을 입력해주세요.", "안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if ("다른 사람에게 피해를 입혔어요".equals(firstSCB_C) && "다쳤어요(신체)".equals(otherPeoSCB_C)) {
					if (victimInfoONP.getChButtonG().getSelection() == null) {
						JOptionPane.showMessageDialog(this, "피해자 정보 여부를 알려주세요.", "안내", JOptionPane.WARNING_MESSAGE);
						return;
					}
					if (victimInfoP.getNameF().getText().trim().isBlank() || 
						victimInfoP.getPhoneF().getText().trim().isBlank()) {
						JOptionPane.showMessageDialog(this, "피해자 정보를 입력해주세요", "안내", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				if ("다른 사람에게 피해를 입혔어요".equals(firstSCB_C) && "재산피해를 입혔어요(재물)".equals(otherPeoSCB_C)) {
					if (ownerInfONP.getChButtonG().getSelection() == null) {
						JOptionPane.showMessageDialog(this, "소유자 정보 여부를 알려주세요.", "안내", JOptionPane.WARNING_MESSAGE);
						return;
					}
					if (propertyInfoP.getOwnNameF().getText().trim().isBlank() || 
						propertyInfoP.getOwnerPhoneF().getText().trim().isBlank()) {
						JOptionPane.showMessageDialog(this, "소유자 정보를 입력해주세요", "안내", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				
				// 기록된 정보가 객체에 저장되어야 한다.
				if ("다른 사람에게 피해를 입혔어요".equals(firstSCB_C) &&	"재산피해를 입혔어요(재물)".equals(otherPeoSCB_C) ||
					"내 재산에 피해가 발생했어요".equals(firstSCB_C)) {
					claimData.setCompensation_type('P');
				} else {
					claimData.setCompensation_type('H');
				}

				
				switch (firstSCB_C) {
					case "병원에 다녀왔어요(실손 등)":
						if ("아팠어요(질병)".equals(hospitalSCB_C)) {
							claimData.setClaim_category("질병");
							break;
						} else if ("다쳤어요(상해)".equals(hospitalSCB_C)) {
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
				
				
				// 진단코드 입력하기 
				
				if (diagnosisSCB_C != null) {
					switch (diagnosisSCB_C) {
					case "감기":
						claimData.setDiagnosis_name("급성 비인두염 (감기)");
						claimData.setDiagnosis_cd("J00");
						break;
					case "허리통증":
						claimData.setDiagnosis_name("요통");
						claimData.setDiagnosis_cd("M54.5");
						break;
					case "장염":
						claimData.setDiagnosis_name("감염성 장염");
						claimData.setDiagnosis_cd("A09");
						break;
					case "복통":
						claimData.setDiagnosis_name("배의 통증, 미만성");
						claimData.setDiagnosis_cd("R10.4");
						break;
					default:
						break;
					}
				}

				if (detailSituationF != null && detailSituationF.isShowing()) {
					claimData.setAccident_description(detailSituationF.getText());
				}

				// 상황에 맞는 페이지로 넘어갈 수 있도록 하고,
				if ("다른 사람에게 피해를 입혔어요".equals(firstSCB_C)) {
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
		resetComboBox(firstSCB, hospitalSCB, otherPeoSCB, diagnosisSCB, bodyPartCB, injurySCB, deathSCB);
		 notVisible(hospitalSCB, otherPeoSCB, diagnosisSCB, bodyPartCB, injurySCB, deathSCB, detailSituationF);
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