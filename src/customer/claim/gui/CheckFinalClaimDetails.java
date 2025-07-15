package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import common.database.model.NewClaimDataModel;

public class CheckFinalClaimDetails extends JPanel {

	private JPanel parentCardPanel;
	
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
		String claimTarget = "";
		String accidentDate = "";
		String claimType = "";
		String claimSituation = "";
		String bankAccount = "";
		String documents = "";
		
		String[][] labelValuePairs = {
		    {"1. 청구대상:", claimTarget},
		    {"2. 사고(발병)일:", accidentDate},
		    {"3. 청구유형:", claimType},
		    {"4. 청구상황:", claimSituation},
		    {"5. 보험금 수령계좌:", bankAccount},
		    {"6. 등록서류:", documents}
		};

		for (int i = 0; i <labelValuePairs.length; ++i) {
			gbc.gridx = 0;
		    gbc.gridy = i;
		    centerPanel.add(new JLabel(labelValuePairs[i][0]), gbc);
		    
		    gbc.gridx = 1;
		    centerPanel.add(new JLabel(labelValuePairs[i][1]), gbc);
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
	    	  JOptionPane.showMessageDialog(this, "보험금 청구가 완료되었습니다.", "안내", JOptionPane.INFORMATION_MESSAGE);
	            cl.show(parentCardPanel, "ClaimFirstPanel");	// TODO: 다음 페이지 이름 넣기
	      });

	      buttonPanel.add(previousButton);
	      buttonPanel.add(actionButton);
	      add(buttonPanel, BorderLayout.SOUTH);
	   
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
