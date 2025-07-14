package common.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FooterImagePanel extends JPanel {
	// 바닥의 로고부분 작업중입니다.
	// 예시코드: add(new FooterImagePanel(), BorderLayout.SOUTH);
	// 레이아웃이 null이면 밑에처럼
	//	FooterImagePanel fip = new FooterImagePanel();
	//	fip.setBounds(0, 850, 1440, 174);
	//	add(fip);
	
	private String imagePath = "src/images/footer.png";
	private Image backgroundImage;
	private int width = 1440;
	private int height = 150;
	
	
	public FooterImagePanel() {
		setBackground(new Color(222, 222, 222));
		
		ImageIcon icon = new ImageIcon(imagePath);
		backgroundImage = icon.getImage();
		setPreferredSize(new java.awt.Dimension(width, height));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int imgWidth = backgroundImage.getWidth(this);
        int imgHeight = backgroundImage.getHeight(this);

        // 원본 비율
        double imgRatio = (double) imgWidth / imgHeight;
        double panelRatio = (double) panelWidth / panelHeight;

        int drawWidth, drawHeight;

        if (panelRatio > imgRatio) {
            // 패널이 더 넓음: 이미지 높이에 맞추고 가로를 줄임
            drawHeight = panelHeight;
            drawWidth = (int)(imgRatio * drawHeight);
        } else {
            // 패널이 더 좁음: 이미지 너비에 맞추고 세로를 줄임
            drawWidth = panelWidth;
            drawHeight = (int)(drawWidth / imgRatio);
        }

        int x = (panelWidth - drawWidth) / 2;
        int y = (panelHeight - drawHeight) / 2;

        g.drawImage(backgroundImage, x, y, drawWidth, drawHeight, this);
	}
}
