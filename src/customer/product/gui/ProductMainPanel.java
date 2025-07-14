package customer.product.gui;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class ProductMainPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductMainPanel() {
		JPanel panel = new JPanel();
		CardLayout cl = new CardLayout();
		panel.setLayout(cl);
		
		ProductIntroDetailPanel detail = new ProductIntroDetailPanel();
		ProductIntroducePanel intro = new ProductIntroducePanel();
		
		intro.setMouseClickListener(new ProductIntroducePanel.MouseClickListener() {
            @Override
            public void onChildPanelClicked(MouseEvent e) {
            	// 상품 리스트에서 더블클릭한 열의 상품 번호를 가져옴
            	int num = intro.getProductId();
            	// 테스트용 상품번호 출력
            	System.out.println(num);
            	
            	// TODO 해당 상품번호를 통해 세부정보를 조회해야함
            	cl.show(panel, "detail");
            }
        });
		
		panel.add(intro, "intro");
		panel.add(detail, "detail");
		
		add(panel);
	}
}
