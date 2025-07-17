package employee.product.view.buttons;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ShowPanelButtons extends JPanel{
	
	public JButton productRegistPage = new JButton("새상품등록");
	public JButton editProductPage = new JButton("상품수정");
	
	public ShowPanelButtons() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
		add(productRegistPage);
		add(editProductPage);
	}
}
