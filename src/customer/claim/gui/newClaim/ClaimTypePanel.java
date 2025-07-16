package customer.claim.gui.newClaim;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import common.database.model.NewClaimDataModel;
import customer.claim.gui.TitlePanel;

public class ClaimTypePanel extends JPanel {
	private JPanel parentCardPanel;
	
	private List<ClaimTypeCheckBox> claimTypeList = new ArrayList<>();
	
	   public ClaimTypePanel(JPanel parentCardPanel, NewClaimDataModel claimData) {
		   
	      this.parentCardPanel = parentCardPanel;
	      CardLayout cl = (CardLayout) (parentCardPanel.getLayout());
	      setLayout(new BorderLayout());
	      
	        TitlePanel title = new TitlePanel("청구유형 선택 (중복선택 가능)");
	        add(title, BorderLayout.NORTH);
	        
	        JPanel chckPanel = new JPanel();
	        add(chckPanel, BorderLayout.CENTER);
	        
	        JLabel 입원비 = new JLabel("질병이나 상해로 인한 입원비 보상을 청구하는 경우");
	        JLabel 통원비 = new JLabel("병원 외래 진료로 발생한 비용을 청구하는 경우");
	        JLabel 수술비 = new JLabel("수술에 대한 비용을 청구하는 경우");
	        JLabel 진단비 = new JLabel("질병 또는 특정 조건 진단 시 지급되는 보험금 청구");
	        JLabel 사망보험금 = new JLabel("피보험자의 사망에 따라 수익자가 청구하는 보험금");
	        JLabel 후유장해 = new JLabel("상해나 질병으로 인해 장해(장기적 장애)가 발생한 경우");

	        
	        claimTypeList.add(new ClaimTypeCheckBox(chckPanel, "입원비"));
	        chckPanel.add(입원비);
	        claimTypeList.add(new ClaimTypeCheckBox(chckPanel, "통원비"));
	        chckPanel.add(통원비);
	        claimTypeList.add(new ClaimTypeCheckBox(chckPanel, "수술비"));
	        chckPanel.add(수술비);
	        claimTypeList.add(new ClaimTypeCheckBox(chckPanel, "진단비"));
	        chckPanel.add(진단비);
	        claimTypeList.add(new ClaimTypeCheckBox(chckPanel, "사망보험금"));
	        chckPanel.add(사망보험금);
	        claimTypeList.add(new ClaimTypeCheckBox(chckPanel, "후유장해"));
	        chckPanel.add(후유장해);

	        
	        chckPanel.setLayout(new GridLayout(6, 2, 0, 0)); // 수직 간격 10px 추가
	        chckPanel.setBorder(BorderFactory.createEmptyBorder(30, 500, 30, 450)); // 상좌하우 여백 추가
	        chckPanel.setVisible(true);
	        
	        // ===== 하단 영역 (이전 / 다음 버튼) =====
	        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
	        bottomPanel.setBounds(96, 227, 264, 63);
	        JButton previousButton = new JButton("이전");
	        JButton nextButton = new JButton("다음");
	        bottomPanel.add(previousButton);
	        bottomPanel.add(nextButton);
	        add(bottomPanel, BorderLayout.SOUTH);

	        previousButton.addActionListener(e -> {
	        	for (ClaimTypeCheckBox ctcb : claimTypeList) {
	        		ctcb.setSelected(false);
	        	}
	            cl.show(parentCardPanel, "ClaimSituationPanel");
	        });

	        nextButton.addActionListener(e -> {
	        	boolean selected = claimTypeList.stream().anyMatch(JCheckBox::isSelected);
	            if (!selected) {
	                JOptionPane.showMessageDialog(chckPanel, "청구유형을 하나 이상 선택해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
	                return;
	            }
	            
	           List<String> selectedTypes = new ArrayList<>();
	           for (ClaimTypeCheckBox ctcb : claimTypeList) {
	        	   if (ctcb.isSelected()) {
	        		   selectedTypes.add(ctcb.getText().trim());
	        	   }
	           }
	            // 클레임 타입 리스트에서 선택된 값들을 객체에 저장 
	             claimData.setClaimType(selectedTypes);
	            	
	        	cl.show(parentCardPanel, "EnterBankAccountPanel");
	        	
	        	System.out.println(claimData.toString()); // 디버깅용
	        });
	    }
}

class ClaimTypeCheckBox extends JCheckBox {
	public ClaimTypeCheckBox(JPanel parentPanel, String text) {
		setText(" " + text);
		setFont(new Font("굴림", Font.BOLD, 15));
		parentPanel.add(this);
	}
}
