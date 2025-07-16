package insurancePanelPractice.Contract;

import javax.swing.JFrame;

public class TestFrame extends JFrame {
	public static void main(String[] args) {
		TestFrame m = new TestFrame();
		m.add(new BeneficiaryInfoPanel());
//		m.add(new ConfirmInfoPanel());
//		m.add(new InsuredInfoPanel());
//		m.add(new NoticePanel());
		
		m.setVisible(true);
		m.setBounds(0,0,1000,700);
	}
}
