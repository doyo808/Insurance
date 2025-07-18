package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import common.database.model.NewClaimDataModel;
import customer.claim.gui.newClaim.AccidentDatePanel;
import customer.claim.gui.newClaim.ClaimSituationPanel;
import customer.claim.gui.newClaim.ClaimTargetPanel;
import customer.claim.gui.newClaim.ClaimTypePanel;
import insuranceMain.customerPanel.CustomerMainPanel;
// 청구업무 메인부모패널 (카드레이아웃 패널의 부모패널)
public class ClaimMainPanel extends JPanel {

	public JPanel 카드패널;
	private ClaimFirstPanel 청구첫번째패널;
	private ClaimTargetPanel 청구대상패널;
    private AccidentDatePanel 사고일선택패널;
    private ClaimSituationPanel 청구상황선택패널;
    private EnterBankAccountPanel 계좌입력패널;
    private ClaimTypePanel 청구타입패널;
    private DocumentRegistrationPanel 서류등록패널_계좌입력;
    private DocumentRegistrationPanel 서류등록패널_청구상황;
    private CheckFinalClaimDetails 최종청구내역확인패널;
    private RequiredDocumentsInfo 필수서류_청구첫패널;
    private RequiredDocumentsInfo 필수서류_서류등록패널;
	private ClaimMethodInfo 보험금청구방법패널;
	private ClaimHistoryPanel 청구내역조회패널;
	
   public ClaimMainPanel(CustomerMainPanel cmp) {

	   NewClaimDataModel claimdata = new NewClaimDataModel();
	   
	   setLayout(new BorderLayout());
	   setSize(1440, 700);
	
	   카드패널 = new JPanel(new CardLayout());
	   
	   // 카드패널을 가운데 추가
      add(카드패널, BorderLayout.CENTER);
      
      청구첫번째패널 = new ClaimFirstPanel(카드패널); 
      청구대상패널 = new ClaimTargetPanel(카드패널, claimdata);
      사고일선택패널 = new AccidentDatePanel(카드패널, claimdata);
      청구상황선택패널 = new ClaimSituationPanel(카드패널, claimdata);
      계좌입력패널 = new EnterBankAccountPanel(카드패널, claimdata);
      청구타입패널 = new ClaimTypePanel(카드패널, claimdata);
      서류등록패널_계좌입력 = new DocumentRegistrationPanel(카드패널, "EnterBankAccountPanel",claimdata);
      서류등록패널_청구상황 = new DocumentRegistrationPanel(카드패널, "ClaimSituationPanel",claimdata);
      최종청구내역확인패널 = new CheckFinalClaimDetails(카드패널, claimdata);
      필수서류_청구첫패널 = new RequiredDocumentsInfo(카드패널, "ClaimFirstPanel");
      필수서류_서류등록패널 = new RequiredDocumentsInfo(카드패널, "DocumentRegistrationPanel");
      보험금청구방법패널 = new ClaimMethodInfo(카드패널);
      청구내역조회패널 = new ClaimHistoryPanel(카드패널, claimdata);
      
//      전체적인 연결 확인하려면 주석 해제! / 하나씩 볼거면 메인패널과 내가 수정중인 패널 제외 주석처리
      // 카드패널에 각 패널 등록(카드 이름 지정 필수)
      카드패널.add(청구첫번째패널, "ClaimFirstPanel");
      카드패널.add(청구대상패널, "ClaimTargetPanel");
      카드패널.add(사고일선택패널, "AccidentDatePanel");
      카드패널.add(청구상황선택패널, "ClaimSituationPanel");
      카드패널.add(청구타입패널, "ClaimTypePanel");
      카드패널.add(계좌입력패널, "EnterBankAccountPanel");
      카드패널.add(서류등록패널_계좌입력, "DocumentRegistrationPanel");
      카드패널.add(서류등록패널_청구상황, "DocumentRegistrationPanel");
      카드패널.add(최종청구내역확인패널, "CheckFinalClaimDetails");
  
      카드패널.add(필수서류_청구첫패널, "RequiredDocumentsInfoFromClaimFirstPanel");
      카드패널.add(필수서류_서류등록패널, "RequiredDocumentsInfoFromDocReg");
      카드패널.add(보험금청구방법패널, "ClaimMethodInfo");
      
      카드패널.add(청구내역조회패널, "ClaimHistoryPanel");
      
//      // 첫페이지에 보여지기 위함
		CardLayout cl = (CardLayout) 카드패널.getLayout();
		cl.show(카드패널, "ClaimFirstPanel");
//		cl.show(카드패널, "ClaimTargetPanel");
//		cl.show(카드패널, "AccidentDatePanel");
//		cl.show(카드패널, "ClaimSituationPanel");
//		cl.show(카드패널, "ClaimTypePanel");
//		cl.show(카드패널, "EnterBankAccountPanel");
//		cl.show(카드패널, "DocumentRegistrationPanel");
//		cl.show(카드패널, "CheckFinalClaimDetails");
		
      setVisible(true);

   }
   
   // 메인패널을 새로 갈아끼울거라 필요없당
   public void resetAllClaimPanels() {
	   청구대상패널.resetPanel();
	      사고일선택패널.resetPanel();
//	      청구상황선택패널.resetPanel(); // 아직 
	      계좌입력패널.resetPanel();
	      청구타입패널.resetPanel();
	      서류등록패널_계좌입력.resetPanel();
	      서류등록패널_청구상황.resetPanel();
	      최종청구내역확인패널.resetPanel();
	      // 추가 청구할 때 생기는 패널들도 추가해야해 ㅠㅠㅠㅠ
   }
   
   
   
   
   
//   -------------------------------------------------------------------------------------------------------------------------
   // 집에서 진행할 때 사용하는 패널 (CustomerMainPanel cmp가 없음)
	public ClaimMainPanel() {
		NewClaimDataModel claimdata = new NewClaimDataModel();

		setLayout(new BorderLayout());
		setSize(1440, 700);

		카드패널 = new JPanel(new CardLayout());

		// 카드패널을 가운데 추가
		add(카드패널, BorderLayout.CENTER);

		ClaimFirstPanel 청구첫번째패널 = new ClaimFirstPanel(카드패널);
		ClaimTargetPanel 청구대상패널 = new ClaimTargetPanel(카드패널, claimdata);
		AccidentDatePanel 사고일선택패널 = new AccidentDatePanel(카드패널, claimdata);
		ClaimSituationPanel 청구상황선택패널 = new ClaimSituationPanel(카드패널, claimdata);
		EnterBankAccountPanel 계좌입력패널 = new EnterBankAccountPanel(카드패널, claimdata);
		ClaimTypePanel 청구타입패널 = new ClaimTypePanel(카드패널, claimdata);
		DocumentRegistrationPanel 서류등록패널_계좌입력 = new DocumentRegistrationPanel(카드패널, "EnterBankAccountPanel", claimdata);
		DocumentRegistrationPanel 서류등록패널_청구상황 = new DocumentRegistrationPanel(카드패널, "ClaimSituationPanel", claimdata);

		CheckFinalClaimDetails 최종청구내역확인패널 = new CheckFinalClaimDetails(카드패널, claimdata);

		RequiredDocumentsInfo 필수서류_청구첫패널 = new RequiredDocumentsInfo(카드패널, "ClaimFirstPanel");
		RequiredDocumentsInfo 필수서류_서류등록패널 = new RequiredDocumentsInfo(카드패널, "DocumentRegistrationPanel");
		ClaimMethodInfo 보험금청구방법패널 = new ClaimMethodInfo(카드패널);

		ClaimHistoryPanel 청구내역조회패널 = new ClaimHistoryPanel(카드패널, claimdata); // 아마 여기는 데이터 값으로 다른 클래스가 들어가야하지
																				// 않을까........!!

//      전체적인 연결 확인하려면 주석 해제! / 하나씩 볼거면 메인패널과 내가 수정중인 패널 제외 주석처리
		// 카드패널에 각 패널 등록(카드 이름 지정 필수)
		카드패널.add(청구첫번째패널, "ClaimFirstPanel");
		카드패널.add(청구대상패널, "ClaimTargetPanel");
		카드패널.add(사고일선택패널, "AccidentDatePanel");
		카드패널.add(청구상황선택패널, "ClaimSituationPanel");
		카드패널.add(청구타입패널, "ClaimTypePanel");
		카드패널.add(계좌입력패널, "EnterBankAccountPanel");
		카드패널.add(서류등록패널_계좌입력, "DocumentRegistrationPanel");
		카드패널.add(서류등록패널_청구상황, "DocumentRegistrationPanel");
//		카드패널.add(최종청구내역확인패널, "CheckFinalClaimDetails");

		카드패널.add(필수서류_청구첫패널, "RequiredDocumentsInfoFromClaimFirstPanel");
		카드패널.add(필수서류_서류등록패널, "RequiredDocumentsInfoFromDocReg");
		카드패널.add(보험금청구방법패널, "ClaimMethodInfo");

		카드패널.add(청구내역조회패널, "ClaimHistoryPanel");

//      // 첫페이지에 보여지기 위함
		CardLayout cl = (CardLayout) 카드패널.getLayout();
		cl.show(카드패널, "ClaimFirstPanel");
//		cl.show(카드패널, "ClaimTargetPanel");
//		cl.show(카드패널, "AccidentDatePanel");
//		cl.show(카드패널, "ClaimSituationPanel");
//		cl.show(카드패널, "ClaimTypePanel");
//		cl.show(카드패널, "EnterBankAccountPanel");
//		cl.show(카드패널, "DocumentRegistrationPanel");
//		cl.show(카드패널, "CheckFinalClaimDetails");

		setVisible(true);
	}
   
}