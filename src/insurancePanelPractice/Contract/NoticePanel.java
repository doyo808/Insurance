package insurancePanelPractice.Contract;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NoticePanel extends JPanel {
    public NoticePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // 타이틀
        JLabel titleLabel = new JLabel("보험가입 안내사항을 확인하세요.");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(titleLabel, gbc);

        // 상품설명서 영역
        gbc.gridy++;
        JPanel guidePanel = new JPanel(new BorderLayout(10, 5));
        guidePanel.setBorder(BorderFactory.createTitledBorder("상품설명서: 스마트치아플랜 보장보험"));

        JTextArea guideText = new JTextArea(
            "주요 보장 내역:\n" +
            "- 충전치료, 크라운치료, 임플란트 치료 비용 보장\n" +
            "- 치주질환, 치수질환 등 진단 시 진단금 지급\n" +
            "- 사고로 인한 치아손상 치료비 추가 보장\n\n" +
            "보험기간: 20년 만기 (갱신형)\n" +
            "납입기간: 20년\n" +
            "가입연령: 만 20세 ~ 60세\n\n" +
            "※ 실제 보험료 및 상세 내용은 약관을 참고하세요."
        );
        guideText.setLineWrap(true);
        guideText.setWrapStyleWord(true);
        guideText.setEditable(false);
        guideText.setBackground(new Color(250, 250, 250));

        JScrollPane guideScroll = new JScrollPane(guideText);
        guideScroll.setPreferredSize(new Dimension(400, 150));  // 크기를 크게

        guidePanel.add(guideScroll, BorderLayout.CENTER);

        JButton guideDownload = new JButton("설명서 다운로드");
        guidePanel.add(guideDownload, BorderLayout.SOUTH);
        add(guidePanel, gbc);

        // 보험약관 영역
        gbc.gridy++;
        JPanel termsPanel = new JPanel(new BorderLayout(10, 5));
        termsPanel.setBorder(BorderFactory.createTitledBorder("보험약관: 스마트치아플랜 보장보험"));

        JTextArea termsText = new JTextArea(
            "주요 약관 요약:\n" +
            "- 보장개시일로부터 90일 이내 발생한 치주질환은 보장 제외\n" +
            "- 임플란트 치료 보장은 연간 1회 한도로 제한\n" +
            "- 고의적 사고, 범죄행위로 인한 손상은 보장 제외\n\n" +
            "보험금 지급사유, 면책사유, 지급제한 등은 약관 전문을 반드시 확인 바랍니다."
        );
        termsText.setLineWrap(true);
        termsText.setWrapStyleWord(true);
        termsText.setEditable(false);
        termsText.setBackground(new Color(250, 250, 250));

        JScrollPane termsScroll = new JScrollPane(termsText);
        termsScroll.setPreferredSize(new Dimension(400, 150));

        termsPanel.add(termsScroll, BorderLayout.CENTER);

        JButton termsDownload = new JButton("약관 다운로드");
        termsPanel.add(termsDownload, BorderLayout.SOUTH);
        add(termsPanel, gbc);

        // 하단 버튼
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        JButton prevButton = new JButton("이전");
        JButton confirmButton = new JButton("확인");
        buttonPanel.add(prevButton);
        buttonPanel.add(confirmButton);
        add(buttonPanel, gbc);
    }
}