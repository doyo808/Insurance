package customer.contract;

import java.awt.CardLayout;

import javax.swing.JPanel;

import customer.contract.gui.CalculatePremiumPanel;
import customer.contract.gui.ChoosePeriodsPanel;
import customer.contract.gui.ChooseProductPanel;
import customer.contract.gui.KeywordPanel;
import customer.contract.gui.ReviewPanel;
import insuranceMain.customerPanel.CustomerMainPanel;

public class ContractMainPanel extends JPanel {
	public CardLayout c = new CardLayout();
	private CalculatePremiumPanel calPre;
	private ChooseProductPanel choosePP;
	public String[] cardNames = {"키워드선택", "보험선택", "보험료계산", "보험기간선택", "상품확인"};
	
	public ContractMainPanel(CustomerMainPanel cmp) {
		setLayout(c);
		
		
		add(new KeywordPanel(this), cardNames[0]);
		add(choosePP = new ChooseProductPanel(this), cardNames[1]);
		add(calPre = new CalculatePremiumPanel(this), cardNames[2]);
		
		ShowCard(cardNames[0]);
	}
	
	void addCards() {
		add(new ChoosePeriodsPanel(this), cardNames[3]);
		add(new ReviewPanel(this), cardNames[4]);
	}
	
	public void ShowCard(String name) {
		if (name.equals("보험료계산")) {
			calPre.updateProduct();
			addCards();
		} else if (name.equals("보험선택")) {
			choosePP.updateProducts();
		}
		c.show(this, name);
	}
}
