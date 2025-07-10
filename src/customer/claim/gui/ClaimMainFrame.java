package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import common.gui.headerBar;

public class ClaimMainFrame extends JFrame {

   public ClaimMainFrame() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      setBounds(250, 0, 1440, 1024);
      setLayout(new BorderLayout());

      setExtendedState(JFrame.MAXIMIZED_BOTH); // 프레임 크기를 전체화면에 꽉차게 함
      setUndecorated(false); //윈도우의 테두리(프레임), 타이틀 바, 최소화/최대화/닫기 버튼 등을 숨기거나 표시할지 결정합니다.
      
      add(new headerBar(), BorderLayout.NORTH);
      
      JPanel 카드패널 = new JPanel(new CardLayout());
      
      add(카드패널, BorderLayout.CENTER);
//      
      ClaimMainPanel 메인패널 = new ClaimMainPanel(카드패널);
//      ClaimTargetPanel 청구대상패널 = new ClaimTargetPanel(카드패널);
//      AccidentDatePanel 사고일선택패널 = new AccidentDatePanel(카드패널);
//      ClaimCategoryPanel 청구유형선택패널 = new ClaimCategoryPanel(카드패널);
//      ClaimSituationPanel 청구상황선택패널 = new ClaimSituationPanel(카드패널);
//      
////      전체적인 연결 확인하려면 주석 해제! / 하나씩 볼거면 메인패널과 내가 수정중인 패널 제외 주석처리
      카드패널.add(메인패널, "ClaimMainPanel");
//      카드패널.add(청구대상패널, "ClaimTargetPanel");
//      카드패널.add(사고일선택패널, "AccidentDatePanel");
//      카드패널.add(청구유형선택패널, "ClaimCategoryPanel");
//      카드패널.add(청구상황선택패널, "ClaimSituationPanel");
//      
//      // 첫페이지에 보여지기 위함
      CardLayout cl = (CardLayout) 카드패널.getLayout();
      cl.show(카드패널, "ClaimMainPanel");
////      cl.show(카드패널, "ClaimSituationPanel");
      
      setVisible(true);

   }
}