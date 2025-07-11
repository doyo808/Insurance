package insuranceMain.customerPanel.accounts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import insuranceMain.customerPanel.CustomerMainPanel;
import insuranceMain.customerPanel.services.ServicesMainPanel;

public class AccountsCard1 extends JPanel {
	// 비회원 첫페이지(로그인, 회원가입 버튼들 포함)
	
	public AccountsCard1(AccountsMainPanel parentPanel) {
		setLayout(new BorderLayout());
		setBackground(Color.black);
		
		add(new PanelNorth(parentPanel), BorderLayout.NORTH);
		add(new PanelSouth(), BorderLayout.SOUTH);
		add(new PanelCenter(parentPanel), BorderLayout.CENTER);
	}
}

class PanelCenter extends JPanel {
	private AccountsMainPanel parentPanel;
	
    public PanelCenter(AccountsMainPanel parentPanel) {
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

class PanelNorth extends JPanel {
	private AccountsMainPanel parentPanel;
	
	public PanelNorth(AccountsMainPanel parentPanel) {
		this.parentPanel = parentPanel;
		setBackground(Color.gray);
		
		JButton btn2 = new JButton("로그인");
		JButton btn3 = new JButton("회원가입");

		btn2.addActionListener(e -> {
			parentPanel.showCard("로그인");
		});
		btn3.addActionListener(e -> {
			parentPanel.showCard("회원가입_메인");
		});
		
		add(btn2);
		add(btn3);
	}
}

class PanelSouth extends JPanel {
	public PanelSouth() {
		setBackground(Color.gray);
		
		// imageIcon으로 이미지로 채우기
	}
}




