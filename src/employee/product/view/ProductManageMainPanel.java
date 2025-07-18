package employee.product.view;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ProductManageMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private CardLayout layout = new CardLayout();
	public ShowPanel card1 = new ShowPanel();
	public EditPanel card2 = new EditPanel();
	public RegistPanel card3 = new RegistPanel();
	
	public ProductManageMainPanel() {
		setLayout(layout);
		JScrollPane jspane = new JScrollPane(card3);
		jspane.getVerticalScrollBar().setUnitIncrement(20);
		
		add(card1, "show");
		add(card2, "edit");
		add(jspane, "regist");
		setPreferredSize(new Dimension(1440,700));
	}

	public void showCard(String name) {
		layout.show(this, name);
	}
}
