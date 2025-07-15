package common.method;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonMaker {
	public static void addButton(int width, int height, JPanel jp) {
		JButton emptyButton = new JButton();

		// 모든 시각 효과 제거
		emptyButton.setBorderPainted(false);   // 테두리 제거
		emptyButton.setContentAreaFilled(false); // 배경 채우기 제거
		emptyButton.setFocusPainted(false);    // 포커스 테두리 제거
		emptyButton.setOpaque(false);          // 투명하게 설정

		// 크기설정
		emptyButton.setPreferredSize(new Dimension(width, height));
		
		jp.add(emptyButton);
	}
}
