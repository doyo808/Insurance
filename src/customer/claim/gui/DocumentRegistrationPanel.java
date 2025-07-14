package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import common.gui.CardSwitchButton;

public class DocumentRegistrationPanel extends JPanel {
	private JPanel parentCardPanel;
	
	public DocumentRegistrationPanel(JPanel parentCardPanel) {
		this.parentCardPanel = parentCardPanel;
	      CardLayout cl = (CardLayout) (parentCardPanel.getLayout());
	      setLayout(new BorderLayout());
	      setVisible(true);
	      
	      TitlePanel title = new TitlePanel("서류등록");
	      add(title, BorderLayout.NORTH);

	      JPanel centerPanel = new JPanel(new BorderLayout());
	      add(centerPanel, BorderLayout.CENTER);
	      centerPanel.setVisible(true);
	      
	      
	      JButton 상황별필요서류버튼 = new JButton("상황별 필요서류 안내");
	      상황별필요서류버튼.setSize(300, 80);
	      상황별필요서류버튼.setVisible(true);
	      
	      
	      상황별필요서류버튼.addActionListener((e) -> {
	    		RequiredDocumentsInfo infoPanel = new RequiredDocumentsInfo(parentCardPanel, "DocumentRegistrationPanel");
	    		parentCardPanel.add(infoPanel, "RequiredDocumentsInfo");
	    		cl.show(parentCardPanel, "RequiredDocumentsInfo");
	    	});
	      
	      
	      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));

	      JButton 이전 = new JButton("이전");
	      JButton 다음 = new JButton("다음");
	      buttonPanel.add(이전);
	      buttonPanel.add(다음);

	      이전.addActionListener((e) -> {
	         cl.show(parentCardPanel, "EnterBankAccountPanel");
	      
	      });

	      다음.addActionListener((e) -> {
	            cl.show(parentCardPanel, ""); // TODO: 다음 패널 이름으로 변경
	      });

	      buttonPanel.add(이전);
	      buttonPanel.add(다음);
	      add(buttonPanel, BorderLayout.SOUTH);
	   
	}
}
