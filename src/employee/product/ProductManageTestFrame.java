package employee.product;

import java.awt.EventQueue;

import javax.swing.JFrame;

import employee.product.controller.EventController;
import employee.product.model.ProductInfoModel;
import employee.product.view.ProductManageMainPanel;

public class ProductManageTestFrame extends JFrame {
	public ProductManageTestFrame() {
	}

	private static final long serialVersionUID = 1L;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/***
					// 모델자리
						이곳에서는 아래 메인패널에 필요한 데이터들을 관리한다
						(데이터 저장/관리)
					*/
					ProductInfoModel model = new ProductInfoModel();
					
					/***
					// 메인패널자리
						이곳에서는 화면을 볼수있는 View를 둔다
						(사용자 인터페이스)
					*/
					ProductManageMainPanel mainPanel = new ProductManageMainPanel();
					
					/***
					// 컨트롤러자리
						이곳에서는 이벤트들이 작동하는 기능들을 모아둔다
						(사용자 동작 처리, 로직 수행)
					*/
					new EventController(model, mainPanel);
					
					ProductManageTestFrame frame = new ProductManageTestFrame();
					frame.setSize(1440,700);
					frame.setVisible(true);
					frame.getContentPane().add(mainPanel);
					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
