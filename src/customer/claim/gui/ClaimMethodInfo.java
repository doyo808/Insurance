package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ClaimMethodInfo extends JPanel {

	private JPanel parentCardPanel;
	
	public ClaimMethodInfo(JPanel parentCardPanel) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) parentCardPanel.getLayout();
		setLayout(new BorderLayout());
		setVisible(true);
		
		TitlePanel title = new TitlePanel("보험금 청구 방법");
		add(title, BorderLayout.NORTH);
		
		
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));

	      JButton previousButton = new JButton("이전");
	      buttonPanel.add(previousButton);

	      previousButton.addActionListener((e) -> {
	         cl.show(parentCardPanel, "ClaimFirstPanel");
	      
	      });


	      buttonPanel.add(previousButton);
	      add(buttonPanel, BorderLayout.SOUTH);
	   
	}
}
