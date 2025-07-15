package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import common.database.model.NewClaimDataModel;

public class DocumentRegistrationPanel extends JPanel {
	private JPanel parentCardPanel;
	
	public DocumentRegistrationPanel(JPanel parentCardPanel, String previousPanelName, NewClaimDataModel claimData) {
		this.parentCardPanel = parentCardPanel;
	      CardLayout cl = (CardLayout) (parentCardPanel.getLayout());
	      setLayout(new BorderLayout());
	      setVisible(true);
	      
	      TitlePanel title = new TitlePanel("서류등록");
	      add(title, BorderLayout.NORTH);

	      JPanel centerPanel = new JPanel();
	      centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
	      centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
	      
	      
	      add(centerPanel, BorderLayout.CENTER);
	      centerPanel.setVisible(true);
	      
	   // 이미지 형식 안내 (좌측 정렬)
	      JLabel imageLabel = new JLabel("JPG, JPEG, PNG 유형의 파일을 등록해주세요.");
	      imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	      centerPanel.add(imageLabel);
	      
	      JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));

	      JButton fileButton = new JButton("이미지 파일 첨부");
	      fileButton.setPreferredSize(new Dimension(150, 40));

	      JButton 상황별필요서류버튼 = new JButton("상황별 필요서류 안내");
	      상황별필요서류버튼.setPreferredSize(new Dimension(150, 40)); // 사진처럼
	      
	      
	      buttonWrapper.add(fileButton);
	      buttonWrapper.add(상황별필요서류버튼);
	      buttonWrapper.setVisible(true);
	 
	      centerPanel.add(buttonWrapper);
	      
	      JTextArea 안내문 = new JTextArea(
	                "서류 등록시 안내사항\n\n"
	                        + "1. 이미지 파일첨부: 원본서류를 촬영하거나 컬러로 스캔하여 첨부해 주십시오. 첨부파일찾기 "
	                        + "버튼을 눌러 파일을 업로드 한 후 등록 버튼을 클릭하면 접수가 완료됩니다.\n"
	                        + "2. 의료비 청구 시 영수증 상 비급여 금액이 존재하는 경우 반드시 진료비세부내역서를 제출하셔야 합니다.\n"
	                        + "3. 카드매출전표(카드내역문자), 납입확인서는 보험금 청구서류로 사용할 수 없습니다.\n"
	                        + "4. 5천만원 이상 또는 사망보험금 청구건, 보험금을 위임하시는 경우에는 우편/방문을 통한 원본 서류 제출이 필요합니다.");
	        안내문.setEditable(false); // 텍스트 수정못함
	        안내문.setLineWrap(true); // 줄이 길어지면 자동 줄빠굼
	        안내문.setWrapStyleWord(true); // 단어단위로 줄바꿈
	        안내문.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
	        안내문.setBackground(new Color(245, 245, 245)); // 배경색
	        안내문.setMargin(new Insets(10, 10, 10, 10)); // 안쪽 여백(상,하,좌,우)
	        centerPanel.add(안내문);
	      
	      상황별필요서류버튼.addActionListener((e) -> {
	    		RequiredDocumentsInfo infoPanel = new RequiredDocumentsInfo(parentCardPanel, "DocumentRegistrationPanel");
	    		parentCardPanel.add(infoPanel, "RequiredDocumentsInfo");
	    		cl.show(parentCardPanel, "RequiredDocumentsInfo");
	    	});
	      
	      
	      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));

	      JButton previousButton = new JButton("이전");
	      JButton nextButton = new JButton("다음");
	      buttonPanel.add(previousButton);
	      buttonPanel.add(nextButton);

	      previousButton.addActionListener((e) -> {
	         cl.show(parentCardPanel, previousPanelName);
	      
	      });

	      nextButton.addActionListener((e) -> {
	            cl.show(parentCardPanel, "CheckFinalClaimDetails");
	      });

	      buttonPanel.add(previousButton);
	      buttonPanel.add(nextButton);
	      add(buttonPanel, BorderLayout.SOUTH);
	   
	}
}
