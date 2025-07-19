package customer.claim.gui.claimsRelatedInfo;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import customer.claim.gui.component.BottomButtonPanel;
import customer.claim.gui.component.TitlePanel;

public class RequiredDocumentsInfo extends JPanel {

	private JPanel parentCardPanel;
	
	public RequiredDocumentsInfo(JPanel parentCardPanel, String previousPanelName) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) parentCardPanel.getLayout();
		setLayout(new BorderLayout());
		setVisible(true);
		
		TitlePanel title = new TitlePanel("상황별 필요서류 안내");
		add(title, BorderLayout.NORTH);
		
		BottomButtonPanel bottomBP = new BottomButtonPanel(this);
		bottomBP.getNextButton().setVisible(false);

		bottomBP.getPreviousButton().addActionListener((e) -> {
	         cl.show(parentCardPanel, previousPanelName);
	      
	      });
	}
}
