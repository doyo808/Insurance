package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import common.database.model.NewClaimDataModel;
import insuranceMain.customerPanel.CustomerMainPanel;

public class CheckFinalClaimDetails extends JPanel {

	private JPanel parentCardPanel;
	private List<JLabel> valueLabels = new ArrayList<>();
	
	public CheckFinalClaimDetails(JPanel parentCardPanel, NewClaimDataModel claimData) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout)parentCardPanel.getLayout();
		setLayout(new BorderLayout());
		
		
		TitlePanel title = new TitlePanel("보험금 청구내역 확인");
		add(title, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 30, 10); // 여백
		gbc.anchor = GridBagConstraints.WEST;

		// 입력한 정보 가져오기(이름, 계좌는 로그인 정보 통해서)
		String claimTarget = claimData.getCustomer_name();
		java.sql.Date accidentDate = null;
		
		if (claimData.getAccident_date() != null) {
		    accidentDate = new Date(claimData.getAccident_date().getTime());
		}
		
		List<String> claimType = claimData.getClaim_type_name();
		String accidentDescription = claimData.getAccident_description();
		String bankAccount = claimData.getBank_account();
		List<String> documents = claimData.getDocument_type_name();
		
		Object[][] labelValuePairs = {
			    {"1. 청구대상: ", claimTarget},
			    {"2. 사고(발병)일: ", accidentDate != null ? accidentDate.toString() : "입력되지 않음"},
			    {"3. 청구유형: ", claimType != null ? String.join(", ", claimType) : "입력되지 않음"},
			    {"4. 청구상황: ", accidentDescription != null && !accidentDescription.isBlank() ? accidentDescription : "입력되지 않음"},
			    {"5. 보험금 수령계좌: ", bankAccount != null ? bankAccount : "입력되지 않음"},
			    {"6. 등록서류: ", documents != null ? String.join(", ", documents) : "입력되지 않음"}
			};

		for (int i = 0; i <labelValuePairs.length; ++i) {
			gbc.gridx = 0; gbc.gridy = i;
			JLabel label = new JLabel((String) labelValuePairs[i][0]);
		    centerPanel.add(label, gbc);
		    
		    gbc.gridx = 1; gbc.gridy = i;
		    JLabel valueLabel = new JLabel(String.valueOf(labelValuePairs[i][1]));
		    valueLabels.add(valueLabel); 
		    centerPanel.add(valueLabel, gbc);
		}
	      
		add(centerPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));

	      JButton previousButton = new JButton("이전");
	      JButton actionButton = new JButton("보험금 청구하기");
	      buttonPanel.add(previousButton);
	      buttonPanel.add(actionButton);

	      previousButton.addActionListener((e) -> {
	         cl.show(parentCardPanel, "DocumentRegistrationPanel");
	      
	      });

	      actionButton.addActionListener((e) -> {
	    	// 청구 내역이 DB에 저장되면서 청구 첫페이지로 넘어감
	    	  
	    	  JOptionPane.showMessageDialog(this, "보험금 청구가 완료되었습니다.", "안내", JOptionPane.INFORMATION_MESSAGE);
	    	  
	    	  CustomerMainPanel cmp = new CustomerMainPanel();
	    	  ClaimMainPanel claimMainPanel = new ClaimMainPanel(cmp);
	    	  
//	    	  LoggedInCenterPanel locp = new LoggedInCenterPanel();
	    	  
	    	  
	    	  parentCardPanel.removeAll(); // 기존 패널들 제거
	    	  parentCardPanel.add(claimMainPanel, "MainPanel");
	    	  cl.show(parentCardPanel, "MainPanel");
	    	  
	    	  parentCardPanel.revalidate();
	    	  parentCardPanel.repaint();
	      });

	      buttonPanel.add(previousButton);
	      buttonPanel.add(actionButton);
	      add(buttonPanel, BorderLayout.SOUTH);
	}
	
	 public void resetPanel() {
		 for (JLabel valueLabel : valueLabels) {
			 valueLabel.setText("");
		 }
	 }
}

class CheckLabel extends JLabel {
	public CheckLabel(String LabelName, JPanel panel) {
		super(LabelName);
		setAlignmentX(Component.CENTER_ALIGNMENT);
//		setVisible(true);
		panel.add(this);
	}
}
