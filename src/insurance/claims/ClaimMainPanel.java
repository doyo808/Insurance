package insurance.claims;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ClaimMainPanel extends JPanel {
   
   private JPanel parentCardPanel; //카드 전환에 사용될 부모 패널
   
   public ClaimMainPanel(JPanel parentCardPanel) {
   this.parentCardPanel = parentCardPanel;
   
   setBounds(250, 0, 1440, 1024);
   setLayout(null);
   
   JButton 청구내역조회버튼 = new JButton("보험금 청구내역 조회\n(진행상태 및 결과 조회)") {
      {
         setBounds(84, 316, 400, 350);
      }
   };
   
   JButton 신규청구버튼 = new JButton("보험금 신규 청구하기") {
      {
         setBounds(532, 316, 400, 180);
      }
   };
   
   신규청구버튼.addActionListener((e) -> {
      CardLayout cl = (CardLayout)(parentCardPanel.getLayout());
      cl.show(parentCardPanel, "ClaimTargetPanel");
   });
   
   JButton 추가청구버튼 = new JButton("이전에 청구했던 질병, 사고 추가 청구하기") {
      {
         setBounds(944, 316, 400, 180);
      }
   };
   
//   추가청구버튼.addActionListener((e) -> {
//      CardLayout cl2 = (CardLayout)(getLayout());
//      cl2.show(this, "ClaimTargetPanel");
//   });
//   
   JButton 청구방법버튼 = new JButton("보험금 청구 방법") {
      {
         setBounds(532, 509, 400, 180);
      }
   };
   
   JButton 상황별필요서류버튼 = new JButton("상황별 필요서류 안내") {
      {
         setBounds(944, 509, 400, 180);
      }
   };
   
   JTextArea 보험금청구안내사항 = new JTextArea("보험금 청구 안내사항\n"
         + "＊ 계약자, 피보험자 모두 이용가능합니다.(단, 미성년자 제외)\n"
         + "＊ 계약자와 피보험자가 다른 계약의 보험금 청구는 피보험자가 접수해야 하며, 계약자가 접수할 경우\n"
         + "     보상담당자가 피보험자의 개인정보동의서를 추가로 요청할 수 있습니다.(단, 14세 미만 미성년자 제외)\n"
         + "＊ 각 사고별로 필요한 구비서류를 확인해 주세요."
         + "＊ PC 또는 모바일로 보험금을 청구하시면 보상담당자가 보다 빠르게 서류를 확인할 수 있습니다.\n"
         + "＊ 보험금은 사고일로부터 3년 이내에 청구할 수 있습니다."
         + "＊ 5천 만원 이상 또는 사망보험금 청구건, 보험금을 위임하시는 경우에는 우편/방문을 통한 원본 서류 제출이 필요합니다.") {
      {
         setBounds(143, 711, 1179, 180);
         setEditable(false);
      }
   };
   
   add(청구내역조회버튼);
   add(신규청구버튼);
   add(추가청구버튼);
   add(청구방법버튼);
   add(상황별필요서류버튼);
   add(보험금청구안내사항);
   }
}

