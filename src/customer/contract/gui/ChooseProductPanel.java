package customer.contract.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import common.gui.OurColors;
import common.method.ButtonPainter;
import customer.contract.ContractMainPanel;
import customer.contract.method.GetProductNamesFromKeyword;
import customer.contract.method.SelectedProductName;
import customer.contract.textSources.InsuranceSummaryFactory;

public class ChooseProductPanel extends JPanel {
	private ContractMainPanel contractMP;
	private int cardNumber = 1;
	private int width = 500;
	
	public ChooseProductPanel(ContractMainPanel contractMP) {
		this.contractMP = contractMP;
		setLayout(null);
		setPreferredSize(new Dimension(1440, 700));
	}

	public void updateProducts() {	
		removeAll();
		
		
		
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BorderLayout());
		innerPanel.setBounds(500, 50, width, 500);
		
		
			JLabel title = new JLabel("보험상품가입 [상품선택]");
			title.setFont(new Font("Dialog", Font.BOLD, 32));
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setPreferredSize(new Dimension(537, 56));
			innerPanel.add(title, BorderLayout.NORTH);
			
			
			JPanel scrollContentPanel = new JPanel();
			scrollContentPanel.setLayout(new BoxLayout(scrollContentPanel, BoxLayout.Y_AXIS));
			scrollContentPanel.setOpaque(false); 
			scrollContentPanel.setBackground(new Color(0,0,0,0)); 
			scrollContentPanel.add(Box.createVerticalStrut(50));
			
			Set<String> product_names = GetProductNamesFromKeyword.getNames();
			for (String name : product_names) {
				addProductPanel(name, scrollContentPanel);
			}
	
			// 스크롤 가능하게
			JScrollPane scrollPane = new JScrollPane(scrollContentPanel);
			scrollPane.setOpaque(false);
			scrollPane.getViewport().setOpaque(false); 
			scrollPane.setBorder(null); 
			scrollPane.getVerticalScrollBar().setUnitIncrement(16); 
	
			innerPanel.add(scrollPane, BorderLayout.CENTER);
		
		
		
		add(innerPanel);
		addPrevButton();
		revalidate();
		repaint();
	}
	
	private void addProductPanel(String name, JPanel parentPanel) {
	    InsuranceSummaryFactory.InsuranceSummary summary = InsuranceSummaryFactory.getSummary(name);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new BorderLayout());
	    panel.setMaximumSize(new Dimension(width, 100));
	    panel.setPreferredSize(new Dimension(width, 100));
	    panel.setBackground(Color.WHITE);
	    panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

	    JLabel titleLabel = new JLabel(summary.getProductName());
	    titleLabel.setFont(new Font("Dialog", Font.BOLD, 18));
	    titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));

	    JLabel line1 = new JLabel(summary.getLine1());
	    JLabel line2 = new JLabel(summary.getLine2());
	    JLabel line3 = new JLabel(summary.getPremium());

	    JPanel textPanel = new JPanel();
	    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
	    textPanel.setOpaque(false);
	    textPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 0));
	    textPanel.add(line1);
	    textPanel.add(line2);
	    textPanel.add(line3);

	    panel.add(titleLabel, BorderLayout.NORTH);
	    panel.add(textPanel, BorderLayout.CENTER);

	    // 클릭 시 상품 선택 및 다음 카드로 이동
	    panel.addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override
	        public void mouseClicked(java.awt.event.MouseEvent e) {
	            SelectedProductName.setProduct_name(name);
	            contractMP.ShowCard(contractMP.cardNames[cardNumber + 1]);
	        }
	        
	        @Override
	        public void mouseEntered(MouseEvent e) {
	        	panel.setBackground(OurColors.SELECTED);
	        }
	        
	        @Override
	        public void mouseExited(MouseEvent e) {
	        	panel.setBackground(Color.white);
	        }
	        
	    });
	    parentPanel.add(panel);
	    parentPanel.add(Box.createVerticalStrut(10));
	}
	
	
	private void addPrevButton() {
		
        JButton prevButton = new JButton("이전");
        ButtonPainter.stylePrimaryButtonGray(prevButton, 16);
        prevButton.setBounds(623, 617, 279, 41);
        prevButton.addActionListener(e -> {
        	contractMP.ShowCard(contractMP.cardNames[cardNumber - 1]);
        });

        add(prevButton, BorderLayout.SOUTH);
        
    }
}



