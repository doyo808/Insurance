package customer.claim.gui;

import javax.swing.JFrame;

public class ClaimMain {
	   public static void main(String[] args) {
		   
	      JFrame fr = new JFrame();
	      
	      fr.setExtendedState(JFrame.MAXIMIZED_BOTH); // 프레임 크기를 전체화면에 꽉차게 함
	      fr.setUndecorated(false); //윈도우의 테두리(프레임), 타이틀 바, 최소화/최대화/닫기 버튼 등을 숨기거나 표시할지 결정합니다.
	  
	      fr.add(new LastClaimMainPanel());
	   fr.setVisible(true);
	   
	   }
	}
