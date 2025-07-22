package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import common.account.login.Session;
import common.database.dao.ClaimDAO;
import common.database.model.ClaimsModel;
import common.database.model.CustomerModel;
import common.database.model.NewClaimDataModel;
import common.method.InsuranceTeamConnector;
import customer.claim.gui.component.BottomButtonPanel;
import customer.claim.gui.component.TitlePanel;
import insuranceMain.customerPanel.CustomerMainPanel;

public class FinalCheckPanel extends JPanel {

	private CustomerMainPanel cmp;
	private JPanel parentCardPanel;
	private List<JLabel> valueLabels = new ArrayList<>();
	private ClaimsModel claimModel;
	
	public FinalCheckPanel(JPanel parentCardPanel, NewClaimDataModel claimData, CustomerMainPanel cmp) {
		this.cmp = cmp;
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
		
		String claimType = claimData.getClaim_type_name();
		String accidentDescription = claimData.getAccident_description();
		String bankAccount = claimData.getBank_account();
		List<String> documents = claimData.getDocument_type_names();
		
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

		BottomButtonPanel bottomBP = new BottomButtonPanel(this);
		
	

	      bottomBP.getPreviousButton().addActionListener((e) -> {
	         cl.show(parentCardPanel, "DocumentRegistrationPanel");
	      
	      });

	      bottomBP.setActionButton("보험금 청구하기");
	      bottomBP.getNextButton().addActionListener((e) -> {
	    	// 청구 내역이 DB에 저장
	    	  try (Connection conn = InsuranceTeamConnector.getConnection()) {

	  			CustomerModel cm = Session.getCustomer();
	  			if (cm == null) {
	  				JOptionPane.showMessageDialog(this, "로그인 정보가 없습니다.");
	  				return;
	  			}
	  			
	  		  
	  			
	    	  int result = ClaimDAO.insertClaim(conn, claimData);

	    	  if (result > 0) {
	    	      JOptionPane.showMessageDialog(null, "청구가 정상적으로 등록되었습니다.");
	    	  } else {
	    	      JOptionPane.showMessageDialog(null, "청구 등록에 실패했습니다.");
	    	      return;
	    	  }
	    	  
	    	  // 메인패널로 넘어감
	    	  parentCardPanel.removeAll(); // 기존 패널들 제거
	    	  
	    	  cmp.showCard("accounts");
	    	  cmp.getAMP().showCard("회원_메인");
	    	  parentCardPanel.revalidate();
	    	  parentCardPanel.repaint();
	    	  
	    	  } catch (SQLException e1) {
				e1.printStackTrace();
			}
	     });
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
