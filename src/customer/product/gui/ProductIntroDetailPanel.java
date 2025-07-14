package customer.product.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class ProductIntroDetailPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ProductIntroDetailPanel() {
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		add(panel, BorderLayout.NORTH);
		
		JButton btn1 = new JButton("상품소개");
		btn1.setBackground(new Color(128, 128, 128));
		btn1.setForeground(new Color(255, 255, 255));
		btn1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		panel.add(btn1);
		
		JButton btn2 = new JButton("보장내용");
		btn2.setBackground(new Color(128, 128, 128));
		btn2.setForeground(new Color(255, 255, 255));
		btn2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		panel.add(btn2);
		
		CardLayout cl = new CardLayout();
		JPanel displayDetails = new JPanel(cl);
		add(displayDetails);
		JPanel cardPanel1 = new JPanel(new BorderLayout());
		
		JPanel cardPanel2 = new JPanel(new BorderLayout());
		
		displayDetails.add(cardPanel1, "card1");
		displayDetails.add(cardPanel2, "card2");
		
		// TODO 나중에 데이터 베이스에서 이미지파일 및 텍스트내용들에 대한 정보를 담아올 것
		JLabel lblNewLabel = new JLabel("상품소개 내용");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		JLabel lblNewLabel_1 = new JLabel("보장내용들");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		
		cardPanel1.add(lblNewLabel, BorderLayout.CENTER);
		cardPanel2.add(lblNewLabel_1, BorderLayout.CENTER);
		
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cl.show(displayDetails, "card1");
			}
		});
		
		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cl.show(displayDetails, "card2");
			}
		});
	}
}
