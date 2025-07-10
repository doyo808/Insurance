package insurancePanelPractice;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class InsuranceMainFrame extends JFrame {
	private Customer c;
	
	public InsuranceMainFrame() {
		init();
		
		// 객체 생성
		String product1 = "튼튼보험";
		String product2 = "안심보험";
		List<String> products = new ArrayList<>();
		products.add(product1);
		products.add(product2);
		this.c = new Customer("홍길동", "010-1234-1234", products);
		
		// 패널 설정
		CenterPanel cp = new CenterPanel();
		add(cp, BorderLayout.CENTER);
		add(new TopMenuPanel(cp), BorderLayout.NORTH);
	}
	
	void init() {
		setLayout(new BorderLayout());
		
		setResizable(false);
		setVisible(true);
		setBounds(100, 50, 700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
