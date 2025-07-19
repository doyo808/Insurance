package customer.claim.gui;

import java.awt.*;
import javax.swing.*;

import common.gui.CardSwitchButton;
import customer.claim.gui.component.TitlePanel;

public class ClaimFirstPanel extends JPanel {

    private JPanel parentCardPanel;

    public ClaimFirstPanel(JPanel parentCardPanel) {
        this.parentCardPanel = parentCardPanel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        TitlePanel title = new TitlePanel("보험금 청구");
        add(title, BorderLayout.NORTH);
        
        JPanel 버튼패널 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));

        // 패널이름추가하기!!!!!!!!
        CardSwitchButton 청구내역조회버튼 = new CardSwitchButton("<html>보험금 청구내역 조회<br>(진행상태 및 결과 조회)</html>", parentCardPanel, "ClaimHistoryPanel", 200, 80);
        CardSwitchButton 신규청구버튼 = new CardSwitchButton("보험금 신규 청구", parentCardPanel, "ClaimTargetPanel", 200, 80);
        CardSwitchButton 추가청구버튼 = new CardSwitchButton("<html>이전에 청구했던 <br>질병, 사고 추가 청구하기<html>", parentCardPanel, "ClaimDetailPanel", 200, 80);
        CardSwitchButton 청구방법버튼 = new CardSwitchButton("보험금 청구 방법", parentCardPanel, "ClaimMethodInfo", 200, 80);
        CardSwitchButton 상황별필요서류버튼 = new CardSwitchButton("상황별 필요서류 안내", parentCardPanel, "RequiredDocumentsInfoFromClaimFirstPanel", 200, 80);
        
        
        버튼패널.add(청구내역조회버튼);
        버튼패널.add(신규청구버튼);
        버튼패널.add(추가청구버튼);
        버튼패널.add(청구방법버튼);
        버튼패널.add(상황별필요서류버튼);
        버튼패널.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));  // 위 30px 여백 추가

        add(버튼패널, BorderLayout.CENTER);

        // ▶ 하단 안내문
        JTextArea 안내문 = new JTextArea(
                "보험금 청구 안내사항\n\n"
                        + "＊ 계약자, 피보험자 모두 이용 가능합니다. (단, 미성년자 제외)\n"
                        + "＊ 계약자와 피보험자가 다른 계약의 보험금 청구는 피보험자가 접수해야 하며,\n"
                        + "     계약자가 접수할 경우 보상담당자가 피보험자의 개인정보동의서를 추가로 요청할 수 있습니다.\n"
                        + "     (단, 14세 미만 미성년자 제외)\n"
                        + "＊ 각 사고별로 필요한 구비서류를 확인해 주세요.\n"
                        + "＊ PC 또는 모바일로 보험금을 청구하시면 보상담당자가 보다 빠르게 서류를 확인할 수 있습니다.\n"
                        + "＊ 보험금은 사고일로부터 3년 이내에 청구할 수 있습니다.\n"
                        + "＊ 5천만 원 이상 또는 사망보험금 청구건, 보험금을 위임하시는 경우에는 우편/방문을 통한 원본 서류 제출이 필요합니다.");
        안내문.setEditable(false); // 텍스트 수정못함
        안내문.setLineWrap(true); // 줄이 길어지면 자동 줄빠굼
        안내문.setWrapStyleWord(true); // 단어단위로 줄바꿈
        안내문.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        안내문.setBackground(new Color(245, 245, 245)); // 배경색
        안내문.setMargin(new Insets(10, 10, 10, 10)); // 안쪽 여백(상,하,좌,우)

        JScrollPane scroll = new JScrollPane(안내문); // 스크롤
        scroll.setPreferredSize(new Dimension(0, 300)); // 너비(자동맞춤), 높이
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30)); // 바깥쪽 여백(상, 좌, 하, 우)
        add(scroll, BorderLayout.SOUTH);
    }
}
