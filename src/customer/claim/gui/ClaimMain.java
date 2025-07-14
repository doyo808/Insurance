package customer.claim.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import common.gui.HeaderBar;

public class ClaimMain {
	   public static void main(String[] args) {
		   
	      JFrame fr = new JFrame();
	      fr.setBounds(0, 0, 1440, 1024);
	      fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      
//	      fr.setExtendedState(JFrame.MAXIMIZED_BOTH); // 프레임 크기를 전체화면에 꽉차게 함
//	      fr.setUndecorated(false); //윈도우의 테두리(프레임), 타이틀 바, 최소화/최대화/닫기 버튼 등을 숨기거나 표시할지 결정합니다.
//	  
	      ClaimMainPanel 메인패널 = new ClaimMainPanel();
	      fr.add(메인패널);
	      fr.setVisible(true);
	   
//	   // 상단 헤더 바 추가
//	   fr.add(new HeaderBar(메인패널), BorderLayout.NORTH);
	   
//	   JScrollPane scrollPane = new ScrollWrapper(claimMainPanel); // ClaimMainPanel을 스크롤로 감싸기
//	   fr.add(new scrollPane(), BorderLayout.CENTER);
	   
	   
	   }
	}
