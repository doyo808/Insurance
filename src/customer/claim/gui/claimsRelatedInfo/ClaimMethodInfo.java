package customer.claim.gui.claimsRelatedInfo;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import customer.claim.gui.component.BottomButtonPanel;
import customer.claim.gui.component.TitlePanel;

public class ClaimMethodInfo extends JPanel {

	private JPanel parentCardPanel;
	
	public ClaimMethodInfo(JPanel parentCardPanel) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) parentCardPanel.getLayout();
		setLayout(new BorderLayout());
		setVisible(true);
		
		TitlePanel title = new TitlePanel("보험금 청구 방법");
		add(title, BorderLayout.NORTH);
		
		
		BottomButtonPanel bottomBP = new BottomButtonPanel(this);
		bottomBP.getNextButton().setVisible(false);
		
	      bottomBP.getPreviousButton().addActionListener((e) -> {
	         cl.show(parentCardPanel, "ClaimFirstPanel");
	      
	      });
	   
	}
}
