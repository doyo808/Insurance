package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import common.gui.headerBar;
// 청구업무 메인부모패널 (카드레이아웃 패널의 부모패널)
public class ClaimMainPanel extends JPanel {

	public JPanel 카드패널;
	
   public ClaimMainPanel() {
	   
	   setLayout(new BorderLayout());
	  
	   JPanel 카드패널 = new JPanel(new CardLayout());
	   
	   // 카드패널을 가운데 추가
      add(카드패널);
      
      ClaimFirstPanel 청구첫번째패널 = new ClaimFirstPanel(카드패널);
      ClaimTargetPanel 청구대상패널 = new ClaimTargetPanel(카드패널);
      AccidentDatePanel 사고일선택패널 = new AccidentDatePanel(카드패널);
      ClaimCategoryPanel 청구유형선택패널 = new ClaimCategoryPanel(카드패널);
      ClaimSituationPanel 청구상황선택패널 = new ClaimSituationPanel(카드패널);
      EnterBankAccountPanel 계좌입력패널 = new EnterBankAccountPanel(카드패널);
//      
////      전체적인 연결 확인하려면 주석 해제! / 하나씩 볼거면 메인패널과 내가 수정중인 패널 제외 주석처리
      // 카드패널에 각 패널 등록(카드 이름 지정 필수)
      카드패널.add(청구첫번째패널, "ClaimMainPanel");
      카드패널.add(청구대상패널, "ClaimTargetPanel");
      카드패널.add(사고일선택패널, "AccidentDatePanel");
      카드패널.add(청구유형선택패널, "ClaimCategoryPanel");
      카드패널.add(청구상황선택패널, "ClaimSituationPanel");
      카드패널.add(계좌입력패널, "EnterBankAccountPanel");
      
//      
//      // 첫페이지에 보여지기 위함
      CardLayout cl = (CardLayout) 카드패널.getLayout();
//      cl.show(카드패널, "ClaimMainPanel");
//      cl.show(카드패널, "ClaimSituationPanel");
        cl.show(카드패널, "EnterBankAccountPanel");
      
      setVisible(true);

   }
}