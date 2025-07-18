package employee.product.controller;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import employee.product.model.ProductInfoModel;
import employee.product.view.ProductManageMainPanel;
import common.method.InsuranceTeamConnector;
import common.database.dao.ProductDAO;
import common.database.model.ProductModel;

public class EventController {
	private final ProductInfoModel model;
	private final ProductManageMainPanel view;
	
	// TODO 이곳에서 상품 페이지의 이벤트들을 추가한다
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
			if(row != -1) {
				Object value = view.card1.center.table.getValueAt(row, 0);
				model.setProductId((int)value);
				
				view.showCard("edit");				
			} else {
				JOptionPane.showMessageDialog(view.card3.center, "선택된 상품이 없습니다");
			}
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
		// end
		//################################################################
		
		
		
		//################################################################
		//RegistPanel의 이벤트들
		view.card3.bottom.prev.addActionListener(e -> {
			view.showCard("show");
		});
		
		view.card3.center.termsBrowseButton.addActionListener(e -> {
			// 파일 탐색기에서 파일을 찾아 임시변수 file에 담아둔다
			File file = fileExplore(view.card3.center);
			// 파일 탐색기에서 선택된 파일정보(file)를 File view.card3.termAndConditions에 담아둔다
			view.card3.center.termAndConditions = file;
			// 해당 텍스트필드에 파일명을 적어둠
			view.card3.center.termsNameField.setText(file.getName());
		});
		
		view.card3.center.manualBrowseButton.addActionListener(e -> {
			// 파일 탐색기에서 파일을 찾아 임시변수 file에 담아둔다
			File file = fileExplore(view.card3.center);
			// 파일 탐색기에서 선택된 파일정보(file)를 File view.card3.productManual에 담아둔다
			view.card3.center.productManual = file;
			// 해당 텍스트필드에 파일명을 적어둠
			view.card3.center.manualNameField.setText(file.getName());
		});
		
		view.card3.center.imageUploadButton.addActionListener(e -> {
			// 파일 탐색기에서 파일을 찾아 임시변수 file에 담아둔다
			File file = fileExplore(view.card3.center);
			// 파일 탐색기에서 선택된 파일정보(file)를 File view.card3.image에 담아둔다
			view.card3.center.imageFile = file;
			// 해당 레이블에 이미지를 미리 보여주기
			ImageIcon image = new ImageIcon(file.getAbsolutePath());
			view.card3.center.imagePreview.setText("");
			view.card3.center.imagePreview.setIcon(image);
			
		});
		
		view.card3.bottom.regist.addActionListener(e -> {
			// card3.center패널에 있는 정보들을 DB에 보낸다
			
			// TODO 단 하나라도 빠진 정보가 있는지, 입력한 정보가 제대로 된건지 체크 후에 넘어간다
			
			String division = (String)view.card3.center.divisionField.getSelectedItem();
			String name = view.card3.center.productNameField.getText();
			int ageLow = (int)view.card3.center.joinAgeLowField.getValue();
			int ageHigh = (int)view.card3.center.joinAgeHighField.getValue();
			int limitLow = Integer.parseInt(view.card3.center.joinLimitLowField.getText());
			int limitHigh = Integer.parseInt(view.card3.center.joinLimitHighField.getText());

			String termPath = view.card3.center.productManual.getAbsolutePath();
			String manualPath = view.card3.center.termAndConditions.getAbsolutePath();
			
			double basic = Double.parseDouble(view.card3.center.basePremiumField.getText());
			double constant = Double.parseDouble(view.card3.center.premiumConstantField.getText());
			
			try (Connection conn = InsuranceTeamConnector.getConnection()){
				ProductDAO.addProduct(
						new ProductModel(null, division,name,ageLow,ageHigh,limitLow,limitHigh,termPath,manualPath,basic,constant,null)
						, view.card3.center.imageFile.getAbsolutePath()
						, conn);
				System.out.println("상품등록성공!");
				JOptionPane.showMessageDialog(view.card3.center, "상품등록 성공");
				view.showCard("show");
			} catch (Exception e1) {
				System.out.println("DB에 상품등록 하는중에 에러남");
				e1.printStackTrace();
			}
			
		});
		// end
		//################################################################
		
		
		
		//################################################################
		//EditPanel의 이벤트들
		view.card2.bottom.prev.addActionListener(e -> {
			view.showCard("show");
		});
		// end
		//################################################################
		
	}
	
	/***
	 * 파일탐색기에서 파일을 가져오는 메서드
	 * @param 컴포넌트
	 * @return 선택한 파일을 반환
	 */
	public File fileExplore(Component com) {
		// 파일 탐색기를 연다
		JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(com);
        
		// 파일 탐색기에서 선택된 이미지 파일경로를 String 변수에 담아둔다
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
        	return null;
        }
	}
}
