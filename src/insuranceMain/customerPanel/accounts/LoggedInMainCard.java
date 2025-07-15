package insuranceMain.customerPanel.accounts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import common.account.login.Session;
import common.database.model.CustomerModel;
import common.gui.FooterImagePanel;
import common.gui.HomeButton;
import common.method.ButtonMaker;
import insuranceMain.customerPanel.CustomerMainPanel;
import insuranceMain.customerPanel.services.ServicesMainPanel;

public class LoggedInMainCard extends JPanel {
	// 회원 첫페이지
	private LoggedInPanelNorth pn;
	
	public LoggedInMainCard(AccountsMainPanel parentPanel, ServicesMainPanel smp, CustomerMainPanel cmp) {
		setLayout(new BorderLayout());
		setBackground(Color.black);
		
		pn = new LoggedInPanelNorth(parentPanel, smp, cmp);
		add(pn, BorderLayout.NORTH);
		add(new FooterImagePanel(), BorderLayout.SOUTH);
		add(new LoggedInCenterPanel(smp, cmp), BorderLayout.CENTER);
	}
	
	public LoggedInPanelNorth getPN() {
		return pn;
	}
}

class LoggedInCenterPanel extends JPanel {
	
    public LoggedInCenterPanel(ServicesMainPanel smp, CustomerMainPanel cmp) {
        setBackground(Color.darkGray);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 200)); // 가운데 정렬, 간격 조정

        String[] labels = {
            "보험상품조회", "보험상품가입", "보험금청구", "보험료납부", "마이페이지"
        };
        
        for (int i = 0; i < labels.length; i++) {
        	String label = labels[i];
        	
            JButton btn = new JButton(label);
            btn.setPreferredSize(new Dimension(200, 100)); // 버튼 크기 지정
            
            btn.addActionListener(e -> {
            	cmp.showCard("services");
            	smp.getSmcp().showCard(label);
            });
            
            add(btn);
        }
    }
}

class LoggedInPanelNorth extends JPanel {
	private AccountsMainPanel parentPanel;
	private JButton btn1;
	
	public LoggedInPanelNorth(AccountsMainPanel parentPanel, ServicesMainPanel smp, CustomerMainPanel cmp) {
		this.parentPanel = parentPanel;
	
		setBackground(Color.gray);
//		setPreferredSize(new Dimension(1440, 100));
		
		
		add(new HomeButton(parentPanel, 10, 10));
		ButtonMaker.addButton(1000, 1, this);
		
		btn1 = new JButton("고객성함");
		JButton btn2 = new JButton("로그아웃");
		
		btn1.addActionListener(e -> {
			cmp.showCard("services");
			smp.getSmcp().showCard("마이페이지");
		});
		btn2.addActionListener(e -> {
			Session.clear();
			parentPanel.showCard("로그인");
		});
		
		add(btn1);
		add(btn2);
	}
	public void updateUserName() {
		CustomerModel user = Session.getCustomer();
		if (user != null) {
			String name = user.getCustomer_name();
			btn1.setText(name);
		}
	}
}






