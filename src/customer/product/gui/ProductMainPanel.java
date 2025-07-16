package customer.product.gui;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import insuranceMain.customerPanel.CustomerMainPanel;

public class ProductMainPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout cl;
	
	public ProductMainPanel(CustomerMainPanel cmp) {
		cl = new CardLayout();
		setLayout(cl);
		
		ProductIntroDetailPanel detail = new ProductIntroDetailPanel();
		ProductIntroducePanel intro = new ProductIntroducePanel();
		
		add(intro, "intro");
		add(detail, "detail");
		
		intro.setMouseClickListener(new ProductIntroducePanel.MouseClickListener() {
            @Override
            public void onChildPanelClicked(MouseEvent e) {
            	// 상품 리스트에서 더블클릭한 열의 상품 번호를 가져옴
            	int num = intro.getProductId();
            	// 테스트용 상품번호 출력
//            	System.out.println(num);

            	// 해당 상품번호를 통해 세부정보를 조회함
            	detail.setSharedProductId(num);
            	
            	// 기존 컴포넌트 제거
            	detail.removeAll();
            	// 컴포넌트 다시 추가
            	detail.setHeaderButton();
            	detail.setCardlayout();
        		cl.show(ProductMainPanel.this, "detail");
        		
            }
        });
		
		detail.setMouseClickListener(new ProductIntroDetailPanel.MouseClickListener() {
			
			@Override
			public void onChildPanelClicked(MouseEvent e) {
				cl.show(ProductMainPanel.this, "intro");
			}
		});
		
//		detail.btn3.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				System.out.println("test");
//				cl.show(ProductMainPanel.this, "intro");
//			}
//		});
		
	}
}
