package customer.product.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import common.gui.headerBar;

public class ProductFrame extends JFrame {

	// ******* 테스트를 위한 상품페이지의 메인프레임입니다.
	
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductFrame frame = new ProductFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductFrame() {
		setBounds(new Rectangle(5, 5, 1440, 1024));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		/*
		
		// 개인적으로 만들어본 헤더 패널
		
		JPanel header_panel = new JPanel();
		header_panel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
		header_panel.setLocale(Locale.KOREA);
		header_panel.setName("헤더");
		header_panel.setAutoscrolls(true);
		contentPane.add(header_panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("상품 조회 페이지");
		lblNewLabel.setLabelFor(header_panel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
		header_panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("버튼");
		btnNewButton.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
		btnNewButton.setBackground(new Color(0, 0, 64));
		btnNewButton.setForeground(new Color(255, 255, 255));
		header_panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("버튼");
		btnNewButton_1.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
		btnNewButton_1.setBackground(new Color(0, 0, 64));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		header_panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("버튼");
		btnNewButton_2.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
		btnNewButton_2.setBackground(new Color(0, 0, 64));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		header_panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("버튼");
		btnNewButton_3.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 12));
		btnNewButton_3.setBackground(new Color(0, 0, 64));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		header_panel.add(btnNewButton_3);
		
		*/
		
		contentPane.add(new headerBar(contentPane), BorderLayout.NORTH);
		
//		ProductIntroducePanel pip = new ProductIntroducePanel(contentPane);
//		contentPane.add(pip);

		contentPane.add(new ProductIntroDetailPanel(), BorderLayout.CENTER);
		
//		contentPane.setVisible(true);
	}

}
