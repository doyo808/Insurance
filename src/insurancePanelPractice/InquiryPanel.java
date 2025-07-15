package insurancePanelPractice;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InquiryPanel extends JPanel {
	CardLayout c = new CardLayout();
	JPanel cardMain = new JPanel();
	int currPage = 1;
	
	public InquiryPanel() {
		setLayout(new BorderLayout());
		addComponents();
	}
	
	void addComponents() {
		cardMain.setLayout(c);
		
		
		// 카드1 (지금은 여기서 다 처리하지만, 추후 각 카드도 클래스화 한 뒤 카드들 패키징할 예정)
		JPanel card1 = new JPanel();
		card1.add(new JButton("조회 메인 페이지"));
		card1.setBackground(Color.DARK_GRAY);
		
		// 카드2
		JPanel card2 = new JPanel();
		card2.add(new JButton("조회 2 페이지"));
		card2.setBackground(Color.gray);
		
		// 카드3
		JPanel card3 = new JPanel();
		card3.add(new JButton("조회 3 페이지"));
		card3.setBackground(Color.DARK_GRAY);
		
		cardMain.add(card1, "조회_1");
		cardMain.add(card2, "조회_2");
		cardMain.add(card3, "조회_3");
		
		add(cardMain);
		addButtonPanel();
		
	}
	
	void addButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.gray);
		
		JButton btn1 = new JButton("이전");
		JButton btn2 = new JButton("다음");
		
		btn1.addActionListener(e -> {
			if (currPage == 1) return;
			
			c.previous(cardMain);
			currPage--;
		});
		btn2.addActionListener(e -> {
			if (currPage == 3) return;
			
			c.next(cardMain);
			currPage++;
		});
		
		buttonPanel.add(btn1);
		buttonPanel.add(btn2);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
}
