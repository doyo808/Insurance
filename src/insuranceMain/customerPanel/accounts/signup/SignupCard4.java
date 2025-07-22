package insuranceMain.customerPanel.accounts.signup;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import customer.payment.gui.components.DefaultPanel;
import customer.payment.gui.components.PaymentDefaultButton;
import insuranceMain.customerPanel.accounts.AccountsMainPanel;
import net.miginfocom.swing.MigLayout;

public class SignupCard4 extends DefaultPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton loginButton;

	public SignupCard4(AccountsMainPanel amp) {
		setBounds(new Rectangle(0, 0, 1440, 700));
		// 전체를 1열로 구성, 행 간격 20px 줌
		setLayout(new MigLayout("", "[grow]", "20[][54px][39px][50px]"));

		loginButton = new PaymentDefaultButton("로그인");
		loginButton.addActionListener(e -> {
			amp.showCard("로그인");
		});

		JLabel lbln = new JLabel("<html><div style='text-align: center;'>회원가입이 완료되었습니다!<br>메인페이지로 돌아가 로그인후 서비스를 이용해주세요.</div></html>");
		lbln.setHorizontalAlignment(SwingConstants.CENTER);
		lbln.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbln.setBackground(Color.WHITE);

		// 레이블과 버튼 모두 수평, 수직 중앙 정렬
		add(lbln, "cell 0 1, growx, align center");
		add(loginButton, "cell 0 3, align center");

	}
}