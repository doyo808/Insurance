package customer.claim.gui.newClaim;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import common.database.model.NewClaimDataModel;
import customer.claim.gui.component.BottomButtonPanel;
import customer.claim.gui.component.TitlePanel;

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

		JLabel hospitalL = new JLabel("질병이나 상해로 인한 입원비 보상을 청구하는 경우");
		JLabel outpatientL = new JLabel("병원 외래 진료로 발생한 비용을 청구하는 경우");
		JLabel surgeryL = new JLabel("수술에 대한 비용을 청구하는 경우");
		JLabel diagnosticL = new JLabel("질병 또는 특정 조건 진단 시 지급되는 보험금 청구");
		JLabel deathL = new JLabel("피보험자의 사망에 따라 수익자가 청구하는 보험금");
		JLabel disorderL = new JLabel("상해나 질병으로 인해 장해(장기적 장애)가 발생한 경우");

		claimTypeList.add(new ClaimTypeCheckBox(chckPanel, "입원비"));
		chckPanel.add(hospitalL);
		claimTypeList.add(new ClaimTypeCheckBox(chckPanel, "통원비"));
		chckPanel.add(outpatientL);
		claimTypeList.add(new ClaimTypeCheckBox(chckPanel, "수술비"));
		chckPanel.add(surgeryL);
		claimTypeList.add(new ClaimTypeCheckBox(chckPanel, "진단비"));
		chckPanel.add(diagnosticL);
		claimTypeList.add(new ClaimTypeCheckBox(chckPanel, "사망보험금"));
		chckPanel.add(deathL);
		claimTypeList.add(new ClaimTypeCheckBox(chckPanel, "후유장해"));
		chckPanel.add(disorderL);

		chckPanel.setLayout(new GridLayout(6, 2, 0, 0)); // 수직 간격 10px 추가
		chckPanel.setBorder(BorderFactory.createEmptyBorder(30, 500, 30, 450)); // 상좌하우 여백 추가
		chckPanel.setVisible(true);

		BottomButtonPanel bottomBP = new BottomButtonPanel(this);

		bottomBP.getPreviousButton().addActionListener(e -> {
			resetPanel();
			cl.show(parentCardPanel, "ClaimSituationPanel");
		});

		bottomBP.getNextButton().addActionListener(e -> {
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
			claimData.setClaim_type_names(selectedTypes);

			cl.show(parentCardPanel, "EnterBankAccountPanel");

//	        	System.out.println(claimData.toString()); // 디버깅용
		});
	}
	
	public void resetPanel() {
		for (ClaimTypeCheckBox ctcb : claimTypeList) {
			ctcb.setSelected(false);
		}
	}
}

class ClaimTypeCheckBox extends JCheckBox {
	public ClaimTypeCheckBox(JPanel parentPanel, String text) {
		setText(" " + text);
		setFont(new Font("굴림", Font.BOLD, 15));
		parentPanel.add(this);
	}

}
