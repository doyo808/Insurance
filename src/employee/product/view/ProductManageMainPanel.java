package employee.product.view;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ProductManageMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private CardLayout layout;
	public ShowPanel card1;
	public EditPanel card2;
	public RegistPanel card3;
	
	public ProductManageMainPanel() {
		layout = new CardLayout();
		setLayout(layout);
		init();
	}
	
	public void init() {
		card1 = new ShowPanel();
		card2 = new EditPanel();
		card3 = new RegistPanel();
		JScrollPane showScroll = new JScrollPane(card1);
		JScrollPane regScroll = new JScrollPane(card3);
		JScrollPane editScroll = new JScrollPane(card2);
		regScroll.getVerticalScrollBar().setUnitIncrement(20);
		editScroll.getVerticalScrollBar().setUnitIncrement(20);
		
		add(showScroll, "show");
		add(editScroll, "edit");
		add(regScroll, "regist");
		setPreferredSize(new Dimension(1440,700));
	}
	
	public void showCard(String name) {
		layout.show(this, name);
	}
}
