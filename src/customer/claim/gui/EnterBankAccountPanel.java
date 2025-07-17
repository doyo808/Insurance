package customer.claim.gui;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;

import common.account.login.Session;
import common.database.dao.ClaimDAO;
import common.database.model.CustomerModel;
import common.database.model.NewClaimDataModel;
import common.gui.CardSwitchButton;
import common.method.InsuranceTeamConnector;
import common.method.Validators;

public class EnterBankAccountPanel extends JPanel {
   private JPanel parentCardPanel;

   public EnterBankAccountPanel(JPanel parentCardPanel, NewClaimDataModel claimData) {
      this.parentCardPanel = parentCardPanel;
      CardLayout cl = (CardLayout)(parentCardPanel.getLayout());
      setLayout(new BorderLayout());
      
      TitlePanel title = new TitlePanel("보험금 수령계좌 입력");
      add(title, BorderLayout.NORTH);

      // ⬅️ 가운데 패널 구성
      JPanel centerPanel = new JPanel(new BorderLayout());
      centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
      add(centerPanel, BorderLayout.CENTER);
      
      // 라디오 버튼 패널
      JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
      JRadioButton AutomaticChButton = new JRadioButton(" 자동이체(계좌번호 자동입력)");
      AutomaticChButton.setFont(new Font("굴림", Font.PLAIN, 15));
      AutomaticChButton.setVisible(true);
      
      JRadioButton accountDirectInputB = new JRadioButton(" 직접입력");
      accountDirectInputB.setFont(new Font("굴림", Font.PLAIN, 15));
      accountDirectInputB.setVisible(true);
//      accountDirectInputB
      
      ButtonGroup ChButtonGroup = new ButtonGroup();
      ChButtonGroup.add(AutomaticChButton);
      ChButtonGroup.add(accountDirectInputB);
      
      radioPanel.add(AutomaticChButton);
      radioPanel.add(accountDirectInputB);
 
      centerPanel.add(radioPanel, BorderLayout.NORTH);
      
      // 입력 패널 (직접입력 선택 시만 보여짐)
      JPanel bankAccountEnterP = new JPanel(new GridBagLayout());
      bankAccountEnterP.setBorder(BorderFactory.createTitledBorder("계좌 정보 입력"));
      bankAccountEnterP.setMaximumSize(new Dimension(400, 200)); // 크기 제한
      bankAccountEnterP.setVisible(false);
     
      centerPanel.add(bankAccountEnterP, BorderLayout.CENTER);
      
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(30, 30, 30, 10);
      gbc.anchor = GridBagConstraints.WEST;

      JLabel bnakNameL = new JLabel("은행명:");
      JTextField bankNameF = new JTextField(15);
      bankNameF.setFont(new Font("굴림", Font.PLAIN, 18));
      
      JLabel bankAccountL = new JLabel("계좌번호:");
      JTextField bankAccountF = new JTextField(15);
      bankAccountF.setFont(new Font("굴림", Font.PLAIN, 18));

      JLabel beneficiaryNameL = new JLabel("예금주:");
      JTextField beneficiaryNameF = new JTextField(15);
      beneficiaryNameF.setFont(new Font("굴림", Font.PLAIN, 18));

      // 은행명 필드 이벤트(필요시 추가)
      bankNameF.addFocusListener(new FocusAdapter() {
         @Override
         public void focusLost(FocusEvent e) {
            String inputBankName = bankNameF.getText();
            // 유효성 검사 추가 가능
         }
      });

      // 계좌 입력 배치
      gbc.gridx = 0; gbc.gridy = 0;
      bankAccountEnterP.add(bnakNameL, gbc);
      gbc.gridx = 1;
      bankAccountEnterP.add(bankNameF, gbc);

      gbc.gridx = 0; gbc.gridy = 1;
      bankAccountEnterP.add(bankAccountL, gbc);
      gbc.gridx = 1;
      bankAccountEnterP.add(bankAccountF, gbc);

      gbc.gridx = 0; gbc.gridy = 2;
      bankAccountEnterP.add(beneficiaryNameL, gbc);
      gbc.gridx = 1;
      bankAccountEnterP.add(beneficiaryNameF, gbc);

      // 선택 이벤트
      AutomaticChButton.addActionListener((e) -> {
         bankAccountEnterP.setVisible(false);
         revalidate();
         repaint();
      });

      accountDirectInputB.addActionListener((e) -> {
         bankAccountEnterP.setVisible(true);
         revalidate();
         repaint();
      });

      // ⬇️ 하단 버튼 영역
      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));

      JButton previousButton = new JButton("이전");
      JButton nextButton = new JButton("다음");
      buttonPanel.add(previousButton);
      buttonPanel.add(nextButton);

      previousButton.addActionListener((e) -> {
         cl.show(parentCardPanel, "ClaimTypePanel");
         ChButtonGroup.clearSelection();
         bankNameF.setText("");
         beneficiaryNameF.setText("");
         bankAccountF.setText("");
         bankAccountEnterP.setVisible(false);
      });

      nextButton.addActionListener((e) -> {
         if (ChButtonGroup.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "계좌정보를 선택해주세요.", "안내", JOptionPane.WARNING_MESSAGE);
            return;
         } 
         
         if (AutomaticChButton.isSelected()) {
	        	 try (Connection conn = InsuranceTeamConnector.getConnection()) {
					CustomerModel cm = Session.getCustomer();
					
					NewClaimDataModel customerBankInfo = ClaimDAO.getCustomerBankInfo(cm.getLogin_id(), conn);
					if (customerBankInfo != null) {
						claimData.setBank_name(customerBankInfo.getBank_name());
						claimData.setBank_account(customerBankInfo.getBank_account());
						claimData.setBeneficiary_name(customerBankInfo.getBeneficiary_name());
					} else {
						JOptionPane.showMessageDialog(this, "고객 정보를 찾을 수 없습니다.");
						return;
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
			}
		}
         
         if (accountDirectInputB.isSelected() &&
            (bankNameF.getText().trim().isBlank() ||
            beneficiaryNameF.getText().trim().isBlank() ||
            bankAccountF.getText().trim().isBlank())) {
        	JOptionPane.showMessageDialog(this, "모든 계좌정보를 입력해주세요", "안내", JOptionPane.WARNING_MESSAGE);
         }

         // 은행명을 콤보박스로 선택하는건 이후에 선택...
         if (accountDirectInputB.isSelected() &&
        		 bankNameF.isValid() && 
        		 bankAccountF.isValid() && 
        		 beneficiaryNameF.isValid()) {
		    	 claimData.setBank_name(bankNameF.getText());
		    	 claimData.setBank_account(bankAccountF.getText());
		    	 claimData.setBeneficiary_name(beneficiaryNameF.getText());
         }
       
         
         
         cl.show(parentCardPanel, "DocumentRegistrationPanel"); 
         
         System.out.println(claimData.toString()); // 디버깅용
      });

      buttonPanel.add(previousButton);
      buttonPanel.add(nextButton);
      add(buttonPanel, BorderLayout.SOUTH);
   }
}
