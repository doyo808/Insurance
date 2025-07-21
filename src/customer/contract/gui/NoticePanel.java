package customer.contract.gui;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import common.account.login.Session;
import common.method.ButtonPainter;
import common.method.InsuranceTeamConnector;
import customer.contract.ContractInfo;
import customer.contract.ContractMainPanel;
import customer.contract.method.Insert;
import customer.contract.method.SelectedProductName;
import customer.contract.textSources.InsuranceDocument;
import customer.contract.textSources.InsuranceDocumentFactory;

public class NoticePanel extends JPanel {

    private ContractMainPanel contractMP;
    private ContractInfo ci;
    private int cardNumber = 7;
    
    private String productName;
    private JTextArea guideTextArea;
    private JTextArea termsTextArea;
    private JButton guideDownloadBtn;
    private JButton termsDownloadBtn;

    
    public NoticePanel(ContractMainPanel contractMP) {
        this.contractMP = contractMP;
        this.ci = contractMP.getContractInfo();
        ci.setCreatedDate(LocalDate.now());
        
        initUI();
    }

    private void initUI() {
        setLayout(null);
        setBackground(new Color(0xECECEC));

        JLabel titleLabel = new JLabel("보험가입 안내사항을 확인하세요.");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 26));
        titleLabel.setBounds(480, 80, 580, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        productName = SelectedProductName.getProduct_name();
        ci.setSelectedProductName(productName);
        InsuranceDocument doc = InsuranceDocumentFactory.getDocument(productName);
        InsuranceDocument docTemp = InsuranceDocumentFactory.getDocument("든든암케어");
        
        // 상품설명서 라벨
        JLabel guideLabel = new JLabel("상품설명서");
        guideLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        guideLabel.setBounds(480, 140, 200, 30);
        add(guideLabel);

        guideTextArea = new JTextArea();
        guideTextArea.setEditable(false);
        guideTextArea.setLineWrap(true);
        guideTextArea.setWrapStyleWord(true);
        guideTextArea.setFont(new Font("Dialog", Font.PLAIN, 15));

        if (doc != null) {
            guideTextArea.setText(doc.getGuideText());
        } else {
            guideTextArea.setText(docTemp.getGuideText());
        }

        JScrollPane guideScroll = new JScrollPane(guideTextArea);
        guideScroll.setBounds(480, 175, 580, 150);
        add(guideScroll);

        guideDownloadBtn = new JButton("설명서 다운로드");
        guideDownloadBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
        guideDownloadBtn.setBounds(480, 330, 580, 30);
        guideDownloadBtn.addActionListener(e -> { 
        	downloadTextFile(doc != null ? doc.getGuideText() : null, productName, "상품설명서");
        });
        add(guideDownloadBtn);

        // 보험약관 라벨
        JLabel termsLabel = new JLabel("보험약관");
        termsLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        termsLabel.setBounds(480, 370, 200, 30);
        add(termsLabel);

        termsTextArea = new JTextArea();
        termsTextArea.setEditable(false);
        termsTextArea.setLineWrap(true);
        termsTextArea.setWrapStyleWord(true);
        termsTextArea.setFont(new Font("Dialog", Font.PLAIN, 15));

        if (doc != null) {
            termsTextArea.setText(doc.getTermsText());
        } else {
            termsTextArea.setText(docTemp.getTermsText());
        }

        JScrollPane termsScroll = new JScrollPane(termsTextArea);
        termsScroll.setBounds(480, 405, 580, 150);
        add(termsScroll);

        termsDownloadBtn = new JButton("약관 다운로드");
        termsDownloadBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
        termsDownloadBtn.setBounds(480, 560, 580, 30);
        termsDownloadBtn.addActionListener(e -> { 
        	downloadTextFile(doc != null ? doc.getTermsText() : null, productName, "보험약관");
        });
        add(termsDownloadBtn);

        // 이전 버튼
        JButton prevBtn = new JButton("이전");
        ButtonPainter.stylePrimaryButtonGray(prevBtn, 16);
        prevBtn.setBounds(493, 610, 256, 40);
        prevBtn.addActionListener(e -> contractMP.ShowCard(contractMP.cardNames[cardNumber - 1])); 
        add(prevBtn);

        // 확인 버튼
        JButton confirmBtn = new JButton("확인");
        ButtonPainter.stylePrimaryButtonCarrot(confirmBtn, 16);
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.setBounds(791, 610, 239, 40);
        confirmBtn.addActionListener(e -> {
        	
        	int choice = JOptionPane.showOptionDialog(
        	        this,
        	        "보험가입 안내사항 확인을 완료했습니다.\n계약을 신청하시겠습니까?",
        	        "계약 신청 확인",
        	        JOptionPane.YES_NO_OPTION,
        	        JOptionPane.QUESTION_MESSAGE,
        	        null,
        	        new String[]{"계약신청", "뒤로가기"},
        	        "계약신청"
        	    );
        	
        	if (choice == JOptionPane.OK_OPTION) {
            
				try (Connection conn = InsuranceTeamConnector.getConnection()) {
					int insu_id = Insert.insertInsured(ci, conn);
		            int bene_id = Insert.insertBeneficiary(ci, conn);
		            
		            Insert.insertContract(ci, conn, Session.getCustomer().getCustomer_id(), insu_id, bene_id);
		            
		            JOptionPane.showMessageDialog(this, "계약 신청이 완료되었습니다.");
		            ci.clear();
		            contractMP.ShowCard(contractMP.cardNames[cardNumber + 1]);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this, "서버와의 연결이 실패하였습니다. 다시 시도해주세요.");
					e1.printStackTrace();
					return;
				}
        	} else {
        		
        	}
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
