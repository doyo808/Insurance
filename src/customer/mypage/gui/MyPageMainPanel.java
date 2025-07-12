package customer.mypage.gui;

import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


//전체 화면 사이즈 : setBounds(100, 10, 1440, 1024);
// 상단판넬 사이즈 : setBounds(0, 0, 1440, 162);	
// 컨텐츠판넬 사이즈 : setBounds(0, 162, 1440, 700);
// 하단판텔 사이즈 : setBounds(0, 0, 1440, 162);	

public class MyPageMainPanel extends JPanel {
	
	public MyPageMainPanel() {
		setPreferredSize(new Dimension(1440, 700));
		setBounds(0, 162, 1440, 700);
		
		setLayout(null);
		
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBounds(0, 0, 1440, 700); // x, y, width, height
        separator.setForeground(Color.RED); // 구분선 색상
        add(separator);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(20, 10, 57, 15);
		add(lblNewLabel);
		
		
		
		
		
	}

}
