package customer.claim.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TmpClaimFrame {
	   public static void main(String[] args) {
		   
		  // 청구페이지 확인용 프레임
	      JFrame fr = new JFrame();
	      fr.setBounds(0, 0, 1440, 700);
	      fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      
	      ClaimMainPanel 메인패널 = new ClaimMainPanel();
	      fr.add(메인패널);
	      fr.setVisible(true);
	   
//	   // 상단 헤더 바 추가
//	   fr.add(new HeaderBar(메인패널), BorderLayout.NORTH);
	   
//	   JScrollPane scrollPane = new ScrollWrapper(claimMainPanel); // ClaimMainPanel을 스크롤로 감싸기
//	   fr.add(new scrollPane(), BorderLayout.CENTER);
	   
	   
	   }
	}
