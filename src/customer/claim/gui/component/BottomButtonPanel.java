package customer.claim.gui.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomButtonPanel extends JPanel {
	private JButton previousButton;
	private JButton nextButton;
	
	public BottomButtonPanel(JPanel panel) {
	   setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
	   previousButton = new JButton("이전");
	   nextButton = new JButton("다음");
       add(previousButton);
       add(nextButton);
       panel.add(this, BorderLayout.SOUTH);
	}

	public JButton getPreviousButton() {
		return previousButton;
	}

	public void setPreviousButton(JButton previousButton) {
		this.previousButton = previousButton;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public void setNextButton(JButton nextButton) {
		this.nextButton = nextButton;
	}
	
	public void setActionButton(String str) {
		nextButton.setText(str);
	}
	
	
}
