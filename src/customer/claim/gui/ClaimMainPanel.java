package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import common.database.model.NewClaimDataModel;
import customer.claim.gui.claimsRelatedInfo.ClaimMethodInfo;
import customer.claim.gui.claimsRelatedInfo.RequiredDocumentsInfo;
import customer.claim.gui.newClaim.AccidentDatePanel;
import customer.claim.gui.newClaim.ClaimTargetPanel;
import customer.claim.gui.newClaim.ClaimTypePanel;
import customer.claim.gui.newClaim.claimSituation.ClaimSituationPanel;
import insuranceMain.customerPanel.CustomerMainPanel;
// 청구업무 메인부모패널 (카드레이아웃 패널의 부모패널)
public class ClaimMainPanel extends JPanel {

	public JPanel claimCardP;
	private ClaimFirstPanel firstP;
	private ClaimTargetPanel targetP;
    private AccidentDatePanel accidentDateP;
    private ClaimSituationPanel situationP;
    private EnterBankAccountPanel bankAccountP;
    private ClaimTypePanel claimTypeP;
    private DocumentRegistrationPanel documentRegistrationP_bank;
    private DocumentRegistrationPanel documentRegistrationP_situ;
    private FinalCheckPanel finalCheckP;
    private RequiredDocumentsInfo requiredDoInfo_first;
    private RequiredDocumentsInfo requiredDoInfo_regist;
	private ClaimMethodInfo methodInfoP;
	private ClaimHistoryPanel claimHistoryP;
	
	private CustomerMainPanel cmp;
	
   public ClaimMainPanel(CustomerMainPanel cmp) {
	   this.cmp = cmp;
	   NewClaimDataModel newClaimData = new NewClaimDataModel();
	   
	   setLayout(new BorderLayout());
	   setPreferredSize(new Dimension(1400, 600));
	   
	   claimCardP = new JPanel(new CardLayout());
	   
      add(claimCardP, BorderLayout.CENTER);
      
      firstP = new ClaimFirstPanel(claimCardP); 
      targetP = new ClaimTargetPanel(claimCardP, newClaimData);
      accidentDateP = new AccidentDatePanel(claimCardP, newClaimData);
      situationP = new ClaimSituationPanel(claimCardP, newClaimData);
      bankAccountP = new EnterBankAccountPanel(claimCardP, newClaimData);
      claimTypeP = new ClaimTypePanel(claimCardP, newClaimData);
      documentRegistrationP_bank = new DocumentRegistrationPanel(claimCardP, "EnterBankAccountPanel",newClaimData, cmp);
      documentRegistrationP_situ = new DocumentRegistrationPanel(claimCardP, "ClaimSituationPanel",newClaimData, cmp);
      finalCheckP = new FinalCheckPanel(claimCardP, newClaimData, cmp);
      requiredDoInfo_first = new RequiredDocumentsInfo(claimCardP, "ClaimFirstPanel");
      requiredDoInfo_regist = new RequiredDocumentsInfo(claimCardP, "DocumentRegistrationPanel");
      methodInfoP = new ClaimMethodInfo(claimCardP);
      claimHistoryP = new ClaimHistoryPanel(claimCardP, newClaimData);
      
//      전체적인 연결 확인하려면 주석 해제! / 하나씩 볼거면 메인패널과 내가 수정중인 패널 제외 주석처리
      claimCardP.add(firstP, "ClaimFirstPanel");
      claimCardP.add(targetP, "ClaimTargetPanel");
      claimCardP.add(accidentDateP, "AccidentDatePanel");
      claimCardP.add(situationP, "ClaimSituationPanel");
      claimCardP.add(claimTypeP, "ClaimTypePanel");
      claimCardP.add(bankAccountP, "EnterBankAccountPanel");
      claimCardP.add(documentRegistrationP_bank, "DocumentRegistrationPanel");
      claimCardP.add(documentRegistrationP_situ, "DocumentRegistrationPanel");
      claimCardP.add(finalCheckP, "CheckFinalClaimDetails");
      claimCardP.add(requiredDoInfo_first, "RequiredDocumentsInfoFromClaimFirstPanel");
      claimCardP.add(requiredDoInfo_regist, "RequiredDocumentsInfoFromDocReg");
      claimCardP.add(methodInfoP, "ClaimMethodInfo");
      claimCardP.add(claimHistoryP, "ClaimHistoryPanel");
      
//      // 첫페이지에 보여지기 위함
		CardLayout cl = (CardLayout) claimCardP.getLayout();
		cl.show(claimCardP, "ClaimFirstPanel");
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