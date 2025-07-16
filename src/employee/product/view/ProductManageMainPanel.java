package employee.product.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class ProductManageMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private CardLayout layout = new CardLayout();
	public ShowPanel card1 = new ShowPanel();
	public EditPanel card2 = new EditPanel();
	public RegistPanel card3 = new RegistPanel();
	
	public ProductManageMainPanel() {
		setLayout(layout);
		add(card1, "show");
		add(card2, "edit");
		add(card3, "regist");
	}

	public void showCard(String name) {
		layout.show(this, name);
	}
}
