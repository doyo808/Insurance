package customer.claim.gui.claimsRelatedInfo;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import customer.claim.gui.component.BottomButtonPanel;
import customer.claim.gui.component.TitlePanel;

public class ClaimMethodInfo extends JPanel {

    private JPanel parentCardPanel;

    public ClaimMethodInfo(JPanel parentCardPanel) {
        this.parentCardPanel = parentCardPanel;
        CardLayout cl = (CardLayout) parentCardPanel.getLayout();

        setLayout(new BorderLayout());
        setVisible(true);

        TitlePanel title = new TitlePanel("보험금 청구 방법");
        add(title, BorderLayout.NORTH);

        // 설명 텍스트 영역 생성
        JTextArea explanationText = new JTextArea();
        explanationText.setEditable(false);  // 읽기 전용
        explanationText.setLineWrap(true);
        explanationText.setWrapStyleWord(true);
        explanationText.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        explanationText.setFont(new Font("Dialog", Font.PLAIN, 15));
        explanationText.setText(
        	    "보험금 청구 절차 안내\n\n" +
        	    "1. 보험금 청구서를 작성합니다.\n" +
        	    "   - 보험사 지정 양식을 사용하거나 홈페이지에서 다운로드할 수 있습니다.\n\n" +
        	    "2. 사고 관련 서류를 준비합니다.\n" +
        	    "   - 입원/통원확인서, 진단서, 진료비 영수증 등 필요 서류는 보험 종류에 따라 다릅니다.\n\n" +
        	    "3. 접수 방법을 선택합니다.\n" +
        	    "   - 보험사 고객센터 방문, 모바일 앱, 홈페이지, 팩스 등 다양한 방법이 있습니다.\n\n" +
        	    "4. 접수 후 보험사 심사가 진행됩니다.\n" +
        	    "   - 서류 누락 시 보완 요청이 올 수 있으므로, 연락을 자주 확인하세요.\n\n" +
        	    "5. 보상 결과가 통보되고 보험금이 지급됩니다.\n" +
        	    "   - 지급까지는 평균 3~7일 정도 소요되며, 보험사마다 차이가 있을 수 있습니다.\n\n" +
        	    "※ 궁금한 사항은 보험사 콜센터나 홈페이지 FAQ를 통해 확인하세요."
        	);

        // 스크롤 패널에 텍스트 영역 추가
        JScrollPane scrollPane = new JScrollPane(explanationText);
        scrollPane.setPreferredSize(new Dimension(500, 180));  // 크기 조정 가능
        scrollPane.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        
        add(scrollPane, BorderLayout.CENTER);

        BottomButtonPanel bottomBP = new BottomButtonPanel(this);
        bottomBP.getNextButton().setVisible(false);

        bottomBP.getPreviousButton().addActionListener(e -> {
            cl.show(parentCardPanel, "ClaimFirstPanel");
        });

    }
}
