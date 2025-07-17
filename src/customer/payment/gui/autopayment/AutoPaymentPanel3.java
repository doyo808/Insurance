package customer.payment.gui.autopayment;

import common.gui.MainPageButton;
import customer.payment.gui.PaymentMainPanel;
import customer.payment.gui.components.DefaultPanel;
import insuranceMain.customerPanel.CustomerMainPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import net.miginfocom.swing.MigLayout;

public class AutoPaymentPanel3 extends DefaultPanel {
	
	private static final long serialVersionUID = 1L;
	private MainPageButton mainPageButton;

	public AutoPaymentPanel3(PaymentMainPanel pmp, CustomerMainPanel cmp) {
		setBounds(new Rectangle(0, 0, 1440, 700));
		// 전체를 1열로 구성, 행 간격 20px 줌
		setLayout(new MigLayout("", "[grow]", "20[][54px][39px][50px]"));

		mainPageButton = new MainPageButton(cmp);
		

		JLabel lbln = new JLabel("<html><div style='text-align: center;'>자동이체 등록이 완료되었습니다.<br>메인페이지로 돌아가시려면 버튼을 눌러주세요.</div></html>");
		lbln.setHorizontalAlignment(SwingConstants.CENTER);
		lbln.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbln.setBackground(Color.WHITE);

		// 레이블과 버튼 모두 수평, 수직 중앙 정렬
		add(lbln, "cell 0 1, growx, align center");
		add(mainPageButton, "cell 0 3, align center");

	}
}