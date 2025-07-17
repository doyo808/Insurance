package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import common.database.model.NewClaimDataModel;

public class ClaimDetailPanel extends JPanel {
	private JPanel parentCardPanel;

	public ClaimDetailPanel(JPanel parentCardPanel, NewClaimDataModel claimData) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) parentCardPanel.getLayout();
		setLayout(new BorderLayout());
	}
}