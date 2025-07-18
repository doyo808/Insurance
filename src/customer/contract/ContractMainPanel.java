package customer.contract;

import java.awt.CardLayout;

import javax.swing.JPanel;

import customer.contract.gui.BeneficiaryInfoPanel;
import customer.contract.gui.CalculatePremiumPanel;
import customer.contract.gui.ChoosePeriodsPanel;
import customer.contract.gui.ChooseProductPanel;
import customer.contract.gui.ContractCompletePanel;
import customer.contract.gui.CoverageSelectionPanel;
import customer.contract.gui.InsuredInfoPanel;
import customer.contract.gui.KeywordPanel;
import customer.contract.gui.NoticePanel;
import insuranceMain.MainFrame;
import insuranceMain.customerPanel.CustomerMainPanel;

public class ContractMainPanel extends JPanel {
	private int testPage = 0;
	
	private CustomerMainPanel cmp;
	public CardLayout c = new CardLayout();
	
	private CalculatePremiumPanel calPre;
	private ChooseProductPanel choosePP;
	public String[] cardNames = {"키워드선택", "보험선택", "보험료계산", "보험기간선택", "피보험자정보입력"
			,"수익자정보입력", "보장항목선택" , "약관확인", "최종확인"};
	
	private ContractInfo contractInfo = new ContractInfo();
	
	public ContractMainPanel(CustomerMainPanel cmp) {
		this.cmp = cmp;
		setLayout(c);
		
		add(new KeywordPanel(this), cardNames[0]);
		add(choosePP = new ChooseProductPanel(this), cardNames[1]);
		add(calPre = new CalculatePremiumPanel(this), cardNames[2]);
		    
			addInfoCards();
		
		ShowCard(cardNames[MainFrame.TEST ? testPage : 0]);
	}
	
	public ContractMainPanel() {
	}

	public void ShowCard(String name) {
		if (name.equals("보험료계산")) {
			calPre.updateProduct();
			addCard();
		} else if (name.equals("보험선택")) {
			choosePP.updateProducts();
		} else if (name.equals("보험기간선택")) {
			addInfoCards();
		} else if (name.equals("약관확인")) {
			add(new ContractCompletePanel(this), cardNames[8]);
		}
		c.show(this, name);
	}
	
	void addCard() {
		add(new ChoosePeriodsPanel(this), cardNames[3]);
	}
	void addInfoCards() {
		add(new InsuredInfoPanel(this), cardNames[4]);
		add(new BeneficiaryInfoPanel(this), cardNames[5]);
		add(new CoverageSelectionPanel(this), cardNames[6]);
		add(new NoticePanel(this), cardNames[7]);
	}
	
	public ContractInfo getContractInfo() {
        return contractInfo;
    }
	public CustomerMainPanel getCmp() {
		return cmp;
	}
	
}
