package employee.product.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import employee.product.model.ProductInfoModel;
import employee.product.view.ProductManageMainPanel;

public class EventController {
	private final ProductInfoModel model;
	private final ProductManageMainPanel view;
	
	public EventController(ProductInfoModel model, ProductManageMainPanel view) {
		this.model = model;
		this.view = view;
		
		//##########################################################
		// ShowPanel의 이벤트들
		
		// ShowPanel의 아래쪽 버튼들
		view.card1.bottom.productRegistPage.addActionListener(e -> {
			// 새상품등록버튼 클릭시 상품을 등록하는 페이지로 넘어감
			view.showCard("regist");
		});
		
		view.card1.bottom.editProductPage.addActionListener(e -> {
			// 상품수정등록버튼 클릭시 위의 테이블에서 선택한 행의 상품번호를 들고옴
			int row = view.card1.center.table.getSelectedRow();
			Object value = view.card1.center.table.getValueAt(row, 0);
			model.setProductId((int)value);
			
			view.showCard("edit");
		});
		
		
		// ShowPanel의 테이블 클릭시
		view.card1.center.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 행을 두번 클릭했을때 발생하는 이벤트
				if(e.getClickCount() == 2) {
					// 여기서 선택된 행의 상품번호를 기반으로 상품수정 페이지로 넘어갈 수 있어야함
					int row = view.card1.center.table.getSelectedRow();
					Object value = view.card1.center.table.getValueAt(row, 0);
					System.out.println(value);
					
				}
			}
		});
		//################################################################
		
		
		
		//################################################################
		//RegistPanel의 이벤트들
		
		
		
		//################################################################
		
		
		
		//################################################################
		//EditPanel의 이벤트들
		
		
		
		//################################################################
		
	}
}
