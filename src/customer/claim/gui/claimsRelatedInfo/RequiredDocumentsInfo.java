package customer.claim.gui.claimsRelatedInfo;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import customer.claim.gui.component.BottomButtonPanel;
import customer.claim.gui.component.TitlePanel;

public class RequiredDocumentsInfo extends JPanel {

	private JPanel parentCardPanel;

	public RequiredDocumentsInfo(JPanel parentCardPanel, String previousPanelName) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) parentCardPanel.getLayout();
		setLayout(new BorderLayout());
		setVisible(true);

		// 제목
		TitlePanel title = new TitlePanel("상황별 필요서류 안내");
		add(title, BorderLayout.NORTH);

		// 가운데 패널에 스크롤 추가
		JPanel centerPanel = createRequiredDocsPanel();
		JScrollPane scrollPane = new JScrollPane(centerPanel);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		add(scrollPane, BorderLayout.CENTER);

		BottomButtonPanel bottomBP = new BottomButtonPanel(this);
		bottomBP.getNextButton().setVisible(false);

		bottomBP.getPreviousButton().addActionListener((e) -> {
			cl.show(parentCardPanel, previousPanelName);
		});
		add(bottomBP, BorderLayout.SOUTH);
	}

	// 상황별 필요서류 패널 구성
	private JPanel createRequiredDocsPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1, 20, 10)); // 0이면 자동으로 행 개수 조절
		panel.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));

		panel.add(createDocBox(" <입원 치료> ", new String[]{
			"① 보험금 청구서",
			"② 진단서 또는 입퇴원확인서",
			"③ 진료비 계산서",
			"④ 통장 사본"
		}));

		panel.add(createDocBox(" <통원 치료> ", new String[]{
			"① 보험금 청구서",
			"② 진료비 계산서",
			"③ 진료비 세부내역서",
			"④ 통장 사본"
		}));

		panel.add(createDocBox(" <사망 사고> ", new String[]{
			"① 보험금 청구서",
			"② 사망진단서 또는 사체검안서",
			"③ 기본증명서 및 가족관계증명서",
			"④ 통장 사본 (수익자 명의)"
		}));

		panel.add(createDocBox(" <질병 치료> ", new String[]{
			"① 보험금 청구서",
			"② 진단서",
			"③ 진료비 계산서",
			"④ 약제비 영수증"
		}));

		panel.add(createDocBox(" <수술> ", new String[]{
			"① 보험금 청구서",
			"② 수술확인서",
			"③ 수술 관련 진료비 계산서",
			"④ 통장 사본"
		}));

		panel.add(createDocBox(" <치과 진료> ", new String[]{
			"① 보험금 청구서",
			"② 진료비 계산서",
			"③ 진료소견서",
			"④ X-ray 또는 진단서"
		}));

		return panel;
	}

	// 각 상황별 문서 박스를 생성
	private JPanel createDocBox(String title, String[] items) {
		JPanel box = new JPanel(new BorderLayout());
		box.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createLineBorder(Color.GRAY),
			title,
			TitledBorder.LEFT,
			TitledBorder.TOP,
			new Font("Dialog", Font.BOLD, 14),
			Color.DARK_GRAY
		));

		JPanel itemPanel = new JPanel(new GridLayout(items.length, 1, 5, 5));
		for (String item : items) {
			JLabel label = new JLabel("   " + item);
			label.setFont(new Font("Dialog", Font.PLAIN, 13));
			itemPanel.add(label);
		}

		box.add(itemPanel, BorderLayout.CENTER);
		return box;
	}
}
