package customer.product.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import common.database.dao.ProductDAO;
import common.database.dao.ProductCoverageDetailDAO;
import common.database.model.ProductModel;
import common.database.model.ProductCoverageDetailModel;
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
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class ProductIntroDetailPanel extends JPanel {

	private int sharedProductId = 0;
	private static final long serialVersionUID = 1L;
	private MouseClickListener listener;
	
	JPanel headerButtons;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	
	JPanel displayDetails;
	CardLayout cl;
	JPanel introduce_image_card;
	JPanel product_details_card;
	
	ImageIcon icon;
	JLabel productIntroImage;
	JTable coverDetailTable;
	JScrollPane scrollpane;
	
	public ProductIntroDetailPanel() {
		setPreferredSize(new Dimension(1440, 700));
		setLayout(new BorderLayout());
		setHeaderButton();
		setCardlayout();
	}
	
	public void setHeaderButton() {
		headerButtons = new JPanel();
		headerButtons.setSize(new Dimension(1440, 100));
		headerButtons.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		add(headerButtons, BorderLayout.NORTH);
		
		btn1 = new JButton("상품소개");
		btn1.setBackground(new Color(128, 128, 128));
		btn1.setForeground(new Color(255, 255, 255));
		btn1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		headerButtons.add(btn1);
		
		btn2 = new JButton("보장내용");
		btn2.setBackground(new Color(128, 128, 128));
		btn2.setForeground(new Color(255, 255, 255));
		btn2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		headerButtons.add(btn2);
		
		btn3 = new JButton("상품목록으로");
		btn3.setBackground(new Color(128, 128, 128));
		btn3.setForeground(new Color(255, 255, 255));
		btn3.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		headerButtons.add(btn3);
		
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
		
		btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.onChildPanelClicked(e);
			}
		});
	}
	
	/***
	 * 이 객체(ProductIntroducePanel)에
	 * 상품 상세 정보를 담은 카드레이아웃을 만듬
	 */
	public void setCardlayout() {
		cl = new CardLayout();
		displayDetails = new JPanel(cl);
		displayDetails.setPreferredSize(new Dimension(800, 700));
		add(displayDetails);
		introduce_image_card = new JPanel();
		product_details_card = new JPanel();
		introduce_image_card.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		product_details_card.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		displayDetails.add(introduce_image_card, "card1");
		displayDetails.add(product_details_card, "card2");
		
		//############################################
		// 데이터베이스에서 이미지파일 및 텍스트내용들에 대한 정보를 담아옴
		
		ProductModel product = null;
		ArrayList<ProductCoverageDetailModel> productDetail = null;
		BufferedImage image = null;
		
		try (Connection conn = InsuranceTeamConnector.getConnection()){
			// 이 객체의 인스턴스에 저장된 상품번호를 통해 PRODUCTS테이블,PRODUCT_COVERAGE_DETAILS테이블의 정보를 가져옴
			product = ProductDAO.getProduct(sharedProductId, conn);
			productDetail = (ArrayList<ProductCoverageDetailModel>) ProductCoverageDetailDAO.getProductDetails(sharedProductId, conn);
			
			if(product != null) {
				InputStream input = product.getProduct_introduce().getBinaryStream();
				image = ImageIO.read(input);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//#############################################
		
		
		if (image != null) {
			icon = new ImageIcon(image);
		} else {
//			System.out.println("이미지가 비어있음");
			productIntroImage = new JLabel("이미지가 비어있음");
			introduce_image_card.add(productIntroImage);
		}
		
		// 상품소개란에 이미지 띄우기
		productIntroImage = new JLabel(icon);
		productIntroImage.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		
		coverDetailTable = new JTable();
		coverDetailTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		coverDetailTable.setShowVerticalLines(false);
		coverDetailTable.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 20));
		
		Object[][] columns = null;
		
		int tableSize = productDetail.size();
		
		if(tableSize != 0) {
			columns = new Object[tableSize][5];
			for (int i = 0; i < tableSize; i++) {
				columns[i][0] = productDetail.get(i).getProductCoverName();
				columns[i][1] = productDetail.get(i).getProductCoverDetail();
			}
		} else {
			columns = new Object[][] {};
		}
		
		coverDetailTable.setModel(new DefaultTableModel(
			columns,
			new String[] {
				"담보명", "보장내용"
			}
		){
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			// 테이블을 수정할수있는지 여부를 비활성화해두는것
			@Override
			public boolean isCellEditable(int col, int row) {
				return false;
			}
		});
		
		coverDetailTable.setRowHeight(30);
		scrollpane = new JScrollPane(coverDetailTable);
		scrollpane.setAutoscrolls(true);
		scrollpane.setPreferredSize(new Dimension(1100, 600));
		scrollpane.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		coverDetailTable.getTableHeader().setReorderingAllowed(false);
		
		// 각 패널에 컴포넌트 추가
		introduce_image_card.add(productIntroImage);
		product_details_card.add(scrollpane);
	}
	
	/***
	 * 이 객체의 인스턴스에 상품번호를 저장 함 
	 */
	public void setSharedProductId(int productId) {
		this.sharedProductId = productId;
	}
	
	// 부모가 등록할 리스너
    public void setMouseClickListener(MouseClickListener listener) {
        this.listener = listener;
    }

    // 리스너 인터페이스 정의
    public interface MouseClickListener {
        void onChildPanelClicked(MouseEvent e);
    }
}
