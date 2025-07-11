package insuranceMain.customerPanel.accounts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import common.account.login.Session;
import common.database.model.CustomerModel;
import insuranceMain.customerPanel.CustomerMainPanel;
import insuranceMain.customerPanel.services.ServicesMainPanel;

public class AccountsCard2 extends JPanel {
	// 회원 첫페이지
	public static PanelNorth2 n2;
	
	public AccountsCard2(AccountsMainPanel parentPanel, ServicesMainPanel smp, CustomerMainPanel cmp) {
		setLayout(new BorderLayout());
		setBackground(Color.black);
		
		n2 = new PanelNorth2(parentPanel, smp, cmp);
		add(n2, BorderLayout.NORTH);
		add(new PanelSouth2(), BorderLayout.SOUTH);
		add(new PanelCenter2(parentPanel), BorderLayout.CENTER);
	}
}

class PanelCenter2 extends JPanel {
	private AccountsMainPanel parentPanel;
	
    public PanelCenter2(AccountsMainPanel parentPanel) {
    	this.parentPanel = parentPanel;
        setBackground(Color.darkGray);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 200)); // 가운데 정렬, 간격 조정

        String[] labels = {
            "보험상품조회", "보험상품가입", "보험금청구", "보험료납부", "마이페이지"
        };
        for (String label : labels) {
            JButton btn = new JButton(label);
            btn.setPreferredSize(new Dimension(200, 100)); // 버튼 크기 지정
            
            btn.addActionListener(e -> {
            	parentPanel.showCard("로그인");
            });
            add(btn);
        }
    }
}

class PanelNorth2 extends JPanel {
	private AccountsMainPanel parentPanel;
	private JButton btn1;
	
	public PanelNorth2(AccountsMainPanel parentPanel, ServicesMainPanel smp, CustomerMainPanel cmp) {
		this.parentPanel = parentPanel;
		setBackground(Color.gray);
		
		btn1 = new JButton("고객성함");
		JButton btn2 = new JButton("로그아웃");
		
		btn1.addActionListener(e -> {
			cmp.showCard("services");
			smp.showCard("마이페이지");
		});
		btn2.addActionListener(e -> {
			Session.clear();
			parentPanel.showCard("로그인");
		});
		
		add(btn1);
		add(btn2);
	}
	public void updateUserName(String name) {
		btn1.setText(name);
	}
}

class PanelSouth2 extends JPanel {
	public PanelSouth2() {
		setBackground(Color.gray);
		
		// imageIcon으로 이미지로 채우기
	}
}




