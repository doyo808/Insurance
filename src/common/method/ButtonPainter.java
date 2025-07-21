package common.method;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

import common.gui.OurColors;

public class ButtonPainter {
	public static void blackAndWhite(JButton btn, int size) {
		back_front(btn, size, Color.black, Color.white);
	}
	
	public static void back_front(JButton btn, int size, Color back, Color front) {
		btn.setBackground(back);
		btn.setForeground(front);
		btn.setFont(new Font("Dialog", Font.BOLD, size));
		btn.setBorderPainted(false);     	// 버튼 테두리 제거
		btn.setContentAreaFilled(false); 	// 버튼 색 제거(투명)
		btn.setOpaque(false);				// 버튼 자체 투명 처리
		btn.setFocusPainted(false);			// 포커스 테두리 제거
		
		
		btn.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        btn.setBackground(Color.DARK_GRAY); // hover 색
		        btn.setOpaque(true);
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        btn.setBackground(back); // 원래 색
		        btn.setOpaque(true);
		    }
		});
	}
	
	public static void stylePrimaryButtonCarrot(JButton button, int size) {
		stylePrimaryButton(button, size, OurColors.UNHOVER, OurColors.HOVER);
	}
	
	public static void stylePrimaryButtonGray(JButton button, int size) {
		stylePrimaryButton(button, size, Color.LIGHT_GRAY, Color.GRAY);
	}
	
	public static void stylePrimaryButton(JButton button, int size, Color baseC, Color hoverC) {
	    // 기본 설정
	    button.setFont(new Font("Dialog", Font.BOLD, size));
	    button.setFocusPainted(false);
	    button.setContentAreaFilled(false); // 우리가 배경을 그릴거니까
	    button.setOpaque(false);
	    button.setForeground(Color.black);
	    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30)); // 여백

	    // 롤오버(hover) 스타일 적용을 위해 마우스 리스너 추가
	    button.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            button.putClientProperty("hover", true);
	            button.repaint();
	        }

	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            button.putClientProperty("hover", false);
	            button.repaint();
	        }
	    });

	    // 버튼 배경 커스텀 페인터
	    button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
	        @Override
	        public void paint(Graphics g, JComponent c) {
	            Graphics2D g2 = (Graphics2D) g.create();
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	            JButton b = (JButton) c;
	            boolean isHovered = Boolean.TRUE.equals(b.getClientProperty("hover"));

	            Color bg = isHovered ? hoverC : baseC;

	            g2.setColor(bg);
	            g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 30, 30);

	            super.paint(g2, c); // 텍스트와 테두리는 기존 방식 사용
	            g2.dispose();
	        }
	    });
	}
	
	public static void styleKeywordButton(JButton button, Color baseC, Color selectedC) {
	    button.setContentAreaFilled(false);
	    button.setFocusPainted(false);
	    button.setBorderPainted(false);
	    button.setFont(new Font("Dialog", Font.PLAIN, 16));
	    button.setForeground(Color.BLACK);
	    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    
	    // 선택 상태 저장
	    button.putClientProperty("selected", false);

	    // 배경 UI 커스터마이징
	    button.setUI(new BasicButtonUI() {
	        @Override
	        public void paint(Graphics g, JComponent c) {
	            Graphics2D g2 = (Graphics2D) g.create();
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	            JButton b = (JButton) c;
	            boolean selected = Boolean.TRUE.equals(b.getClientProperty("selected"));

	            int arc = 20;

	            // 그림자
	            g2.setColor(new Color(0, 0, 0, 30));
	            g2.fillRoundRect(2, 4, b.getWidth() - 4, b.getHeight() - 4, arc, arc);

	            // 배경
	            g2.setColor(selected ? selectedC : baseC);
	            g2.fillRoundRect(0, 0, b.getWidth() - 4, b.getHeight() - 4, arc, arc);

	            // 테두리
	            g2.setColor(Color.DARK_GRAY);
	            g2.drawRoundRect(0, 0, b.getWidth() - 4, b.getHeight() - 4, arc, arc);

	            super.paint(g2, c);
	            g2.dispose();
	        }
	    });

	    // 토글 동작
	    button.addActionListener(e -> {
	        boolean selected = Boolean.TRUE.equals(button.getClientProperty("selected"));
	        button.putClientProperty("selected", !selected);
	        button.repaint();
	    });
	}

	
}
