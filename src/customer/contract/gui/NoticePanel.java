package customer.contract.gui;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import customer.contract.ContractInfo;
import customer.contract.ContractMainPanel;
import customer.contract.method.SelectedProductName;
import customer.contract.textSources.InsuranceDocument;
import customer.contract.textSources.InsuranceDocumentFactory;

public class NoticePanel extends JPanel {

    private ContractMainPanel contractMP;
    private int cardNumber = 7;

    private JTextArea guideTextArea;
    private JTextArea termsTextArea;
    private JButton guideDownloadBtn;
    private JButton termsDownloadBtn;

    
    public NoticePanel(ContractMainPanel contractMP) {
        this.contractMP = contractMP;
        initUI();
    }

    private void initUI() {
        setLayout(null);
        setBackground(new Color(0xECECEC));

        JLabel titleLabel = new JLabel("보험가입 안내사항을 확인하세요.");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
        titleLabel.setBounds(480, 80, 580, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        ContractInfo ci = contractMP.getContractInfo();
        String productName = SelectedProductName.getProduct_name();
        
        InsuranceDocument doc = InsuranceDocumentFactory.getDocument(productName);
        InsuranceDocument docTemp = InsuranceDocumentFactory.getDocument("든든암케어");
        
        // 상품설명서 라벨
        JLabel guideLabel = new JLabel("상품설명서");
        guideLabel.setFont(new Font("굴림", Font.BOLD, 18));
        guideLabel.setBounds(480, 140, 200, 30);
        add(guideLabel);

        guideTextArea = new JTextArea();
        guideTextArea.setEditable(false);
        guideTextArea.setLineWrap(true);
        guideTextArea.setWrapStyleWord(true);
        guideTextArea.setFont(new Font("굴림", Font.PLAIN, 15));

        if (doc != null) {
            guideTextArea.setText(doc.getGuideText());
        } else {
            guideTextArea.setText(docTemp.getGuideText());
        }

        JScrollPane guideScroll = new JScrollPane(guideTextArea);
        guideScroll.setBounds(480, 175, 580, 150);
        add(guideScroll);

        guideDownloadBtn = new JButton("설명서 다운로드");
        guideDownloadBtn.setFont(new Font("굴림", Font.PLAIN, 14));
        guideDownloadBtn.setBounds(480, 330, 580, 30);
        guideDownloadBtn.addActionListener(e -> { 
        	downloadTextFile(doc != null ? doc.getGuideText() : null, productName, "상품설명서");
        });
        add(guideDownloadBtn);

        // 보험약관 라벨
        JLabel termsLabel = new JLabel("보험약관");
        termsLabel.setFont(new Font("굴림", Font.BOLD, 18));
        termsLabel.setBounds(480, 370, 200, 30);
        add(termsLabel);

        termsTextArea = new JTextArea();
        termsTextArea.setEditable(false);
        termsTextArea.setLineWrap(true);
        termsTextArea.setWrapStyleWord(true);
        termsTextArea.setFont(new Font("굴림", Font.PLAIN, 15));

        if (doc != null) {
            termsTextArea.setText(doc.getTermsText());
        } else {
            termsTextArea.setText(docTemp.getTermsText());
        }

        JScrollPane termsScroll = new JScrollPane(termsTextArea);
        termsScroll.setBounds(480, 405, 580, 150);
        add(termsScroll);

        termsDownloadBtn = new JButton("약관 다운로드");
        termsDownloadBtn.setFont(new Font("굴림", Font.PLAIN, 14));
        termsDownloadBtn.setBounds(480, 560, 580, 30);
        termsDownloadBtn.addActionListener(e -> { 
        	downloadTextFile(doc != null ? doc.getTermsText() : null, productName, "보험약관");
        });
        add(termsDownloadBtn);

        // 이전 버튼
        JButton prevBtn = new JButton("이전");
        prevBtn.setFont(new Font("굴림", Font.PLAIN, 18));
        prevBtn.setBackground(new Color(0xDDDDDD));
        prevBtn.setBounds(480, 610, 150, 40);
        prevBtn.addActionListener(e -> contractMP.ShowCard(contractMP.cardNames[cardNumber - 1])); 
        add(prevBtn);

        // 확인 버튼
        JButton confirmBtn = new JButton("확인");
        confirmBtn.setFont(new Font("굴림", Font.PLAIN, 18));
        confirmBtn.setBackground(new Color(0x4CAF50));
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.setBounds(910, 610, 150, 40);
        confirmBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "보험가입 안내사항 확인을 완료했습니다.");
            contractMP.ShowCard(contractMP.cardNames[cardNumber + 1]);
        });
        add(confirmBtn);
    }

    private void downloadTextFile(String text, String productName, String docType) {
        if (text == null || text.isEmpty()) {
            JOptionPane.showMessageDialog(this, docType + " 내용이 없습니다.");
            return;
        }

        try {
            String userHome = System.getProperty("user.home");
            String fileName = productName + "_" + docType + ".txt";
            File file = new File(userHome, fileName);

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(text);
            }
            JOptionPane.showMessageDialog(this, file.getAbsolutePath() + " 에 저장되었습니다.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "파일 저장 중 오류가 발생했습니다: " + ex.getMessage());
        }
    }
}
