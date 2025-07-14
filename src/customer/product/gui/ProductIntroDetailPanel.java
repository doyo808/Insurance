package customer.product.gui;

import javax.swing.JPanel;

import common.database.dao.ProductDAO;
import common.database.model.ProductModel;
import common.method.InsuranceTeamConnector;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;

import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;

public class ProductIntroDetailPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	
	public ProductIntroDetailPanel() {
		setPreferredSize(new Dimension(1440, 700));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(1440, 100));
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
		displayDetails.setPreferredSize(new Dimension(800, 700));
		add(displayDetails);
		JPanel cardPanel1 = new JPanel();
		JPanel cardPanel2 = new JPanel();
		
		displayDetails.add(cardPanel1, "card1");
		displayDetails.add(cardPanel2, "card2");
		
		//############################################
		// 데이터베이스에서 이미지파일 및 텍스트내용들에 대한 정보를 담아옴
		
		ProductModel product = null;
		BufferedImage image = null;
		
		try (Connection conn = InsuranceTeamConnector.getConnection()){
			
			// TODO 이전 페이지에서 올바른 상품번호를 가져와야함
			product = ProductDAO.getProduct(1, conn);
			
			if(product != null) {
				InputStream input = product.getProduct_introduce().getBinaryStream();
				image = ImageIO.read(input);				
			} else {
				System.out.println("상품이 존재하지않음");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//#############################################
		
		ImageIcon icon = null;
		JLabel lblNewLabel = null;
		
		if (image != null) {
			icon = new ImageIcon(image);
		} else {
			System.out.println("이미지가 비어있음");
			lblNewLabel = new JLabel("이미지가 비어있음");
		}
		
		// 상품소개란에 이미지 띄우기
		lblNewLabel = new JLabel(icon);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));

		// 상품 보장내용들 보여주기
		JLabel lblNewLabel_1 = new JLabel("보장내용들");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		cardPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		
		
		cardPanel1.add(lblNewLabel);
		cardPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		cardPanel2.add(lblNewLabel_1);
		
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
