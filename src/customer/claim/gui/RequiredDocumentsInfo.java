package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RequiredDocumentsInfo extends JPanel {

	private JPanel parentCardPanel;
	
	public RequiredDocumentsInfo(JPanel parentCardPanel, String previousPanelName) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) parentCardPanel.getLayout();
		setLayout(new BorderLayout());
		setVisible(true);
		
		TitlePanel title = new TitlePanel("상황별 필요서류 안내");
		add(title, BorderLayout.NORTH);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));

	      JButton previousButton = new JButton("이전");
	      buttonPanel.add(previousButton);

	      previousButton.addActionListener((e) -> {
	         cl.show(parentCardPanel, previousPanelName);
	      
	      });


	      buttonPanel.add(previousButton);
	      add(buttonPanel, BorderLayout.SOUTH);
	   
	}
}
