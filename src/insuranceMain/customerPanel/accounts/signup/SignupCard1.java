package insuranceMain.customerPanel.accounts.signup;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import common.database.dao.CustomerDAO;
import common.database.model.CustomerModel;
import common.method.DigitLimitDocumentFilter;
import common.method.InsuranceTeamConnector;
import common.method.KoreanNameDocumentFilter;
import net.miginfocom.swing.MigLayout;

public class SignupCard1 extends JPanel {
	
	private CustomerModel cm;
	private JButton confirmBtn;
    private final List<JRadioButton> agreeButtons = new ArrayList<>();
    private boolean isVerified = false;

    public SignupCard1(CardLayout c, JPanel jp, CustomerModel cm) {
    	this.cm = cm;
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));
        setPreferredSize(new Dimension(1440, 700));
        setBackground(Color.WHITE);

        JPanel mainPanel = new JPanel(new MigLayout("fillx, insets 10 50 10 50, wrap 1", "[grow]"));
        mainPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(Color.WHITE);

        // 약관 패널들
        mainPanel.add(createAgreementPanel("전자금융거래 이용약관", "/signup/전자금융거래이용약관.txt"), "growx");
        mainPanel.add(createAgreementPanel("홈페이지 이용약관", "/signup/홈페이지이용약관.txt"), "growx, gaptop 10");
        mainPanel.add(createAgreementPanel("개인(신용)정보 수집 및 이용", "/signup/개인정보수집및이용동의서.txt"), "growx, gaptop 10");
        mainPanel.add(createAgreementPanel("고유식별정보 처리에 관한 사항", "/signup/고유식별정보처리동의서.txt"), "growx, gaptop 10");

        // 실명확인 영역
        mainPanel.add(createIdentityPanel(), "growx, gaptop 20");

        add(scrollPane, "grow, span");

        JButton nextBtn = new JButton("다음");
        add(nextBtn, "dock south, align center, gaptop 10");

        nextBtn.addActionListener(e -> {
            if (!allAgreementsAccepted()) {
                JOptionPane.showMessageDialog(this, "모든 약관에 동의해야 합니다.", "동의 확인", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!isVerified) {
                JOptionPane.showMessageDialog(this, "실명확인을 진행해주세요.", "본인 확인", JOptionPane.WARNING_MESSAGE);
                return;
            }

            c.next(jp);
        });

        setVisible(true);
    }


	private JPanel createAgreementPanel(String title, String filename) {
        JPanel panel = new JPanel(new MigLayout("fillx, wrap 1", "[grow]"));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(title));

        JTextArea textArea = new JTextArea(6, 80);
        textArea.setText(readTextFromFile(filename));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, "growx");

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.setBackground(Color.WHITE);
        ButtonGroup group = new ButtonGroup();
        JRadioButton agreeBtn = new JRadioButton("동의함");
        JRadioButton disagreeBtn = new JRadioButton("동의하지 않음");
        agreeBtn.addActionListener(e -> {
            checkAllAgreed();
        });

        disagreeBtn.addActionListener(e -> {
            checkAllAgreed();
        });
        
        agreeBtn.setBackground(Color.WHITE);
        disagreeBtn.setBackground(Color.WHITE);

        group.add(agreeBtn);
        group.add(disagreeBtn);

        radioPanel.add(agreeBtn);
        radioPanel.add(disagreeBtn);
        panel.add(radioPanel, "align left");

        agreeButtons.add(agreeBtn);

        return panel;
    }
	
	private void checkAllAgreed() {
	    boolean allAgreed = true;
	    for (JRadioButton btn : agreeButtons) {
	        if (!btn.isSelected()) {
	            allAgreed = false;
	            break;
	        }
	    }
	    confirmBtn.setEnabled(allAgreed);
	}

    private String readTextFromFile(String filename) {
        StringBuilder sb = new StringBuilder();

        try (InputStream is = getClass().getResourceAsStream(filename)) {
            if (is == null) {
                return "약관 파일을 찾을 수 없습니다: " + filename;
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "약관 파일을 읽는 중 오류가 발생했습니다.";
        }

        return sb.toString();
    }

    private JPanel createIdentityPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap 3", "[][grow][right]", "[][][][]"));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("실명확인"));

        JTextField nameField = new JTextField(6);
        JTextField phoneField1 = new JTextField(4);
        JTextField phoneField2 = new JTextField(4);
        JTextField rrnFront = new JTextField(6);
        JTextField rrnBack = new JTextField(7);
        JComboBox<String> phonePrefix = new JComboBox<>(new String[]{"010", "011", "016", "017", "018", "019"});
        phonePrefix.setSelectedIndex(0);

        ((AbstractDocument) nameField.getDocument()).setDocumentFilter(new KoreanNameDocumentFilter());
        ((AbstractDocument) phoneField1.getDocument()).setDocumentFilter(new DigitLimitDocumentFilter(4));
        ((AbstractDocument) phoneField2.getDocument()).setDocumentFilter(new DigitLimitDocumentFilter(4));
        ((AbstractDocument) rrnFront.getDocument()).setDocumentFilter(new DigitLimitDocumentFilter(6));
        ((AbstractDocument) rrnBack.getDocument()).setDocumentFilter(new DigitLimitDocumentFilter(7));

        // 성명
        panel.add(new JLabel("성명:"));
        panel.add(nameField, "span 2, w 180!");

        // 휴대폰번호
        panel.add(new JLabel("휴대폰번호:"));
        JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        phonePanel.setBackground(Color.WHITE);
        phonePanel.add(phonePrefix);
        phonePanel.add(new JLabel("-"));
        phonePanel.add(phoneField1);
        phonePanel.add(new JLabel("-"));
        phonePanel.add(phoneField2);
        panel.add(phonePanel, "span 2, growx");

        // 주민등록번호 + 실명확인 버튼
        panel.add(new JLabel("주민등록번호:"));
        JPanel rrnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        rrnPanel.setBackground(Color.WHITE);
        rrnPanel.add(rrnFront);
        rrnPanel.add(new JLabel("-"));
        rrnPanel.add(rrnBack);
        panel.add(rrnPanel);

        confirmBtn = new JButton("실명확인");
        confirmBtn.setEnabled(false);
        panel.add(confirmBtn, "align right");

        confirmBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String first = (String) phonePrefix.getSelectedItem();
            String mid = phoneField1.getText().trim();
            String end = phoneField2.getText().trim();
            String front = rrnFront.getText().trim();
            String back = rrnBack.getText().trim();
            String phoneNum = first + "-" + mid + "-" + end;
            String personalId = front + "-" + back;

            if (name.isEmpty() || mid.length() != 4 || end.length() != 4 || front.length() != 6 || back.length() != 7) {
                JOptionPane.showMessageDialog(panel, "모든 정보를 올바르게 입력하세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
                isVerified = false;
                return;
            }
            try (Connection conn = InsuranceTeamConnector.getConnection()) {
            	if (CustomerDAO.getCustomerByPersonalId(personalId, conn) != null) {
            		JOptionPane.showMessageDialog(panel, "이미 등록된 주민등록번호입니다.", "알림", JOptionPane.WARNING_MESSAGE);
                    isVerified = false;
                    return;
            	} else if (CustomerDAO.getCustomerByPhone(phoneNum, conn) != null) {
            		JOptionPane.showMessageDialog(panel, "이미 등록된 휴대폰번호입니다.", "알림", JOptionPane.WARNING_MESSAGE);
                    isVerified = false;
                    return;
            	}
            } catch (SQLException e1) {
				e1.printStackTrace();
			}
            
            isVerified = true;
            JOptionPane.showMessageDialog(panel, "실명 확인이 완료되었습니다.", "확인", JOptionPane.INFORMATION_MESSAGE);
            cm.setCustomer_name(name);
            cm.setPhone_number(phoneNum);
            cm.setPersonal_id(personalId);
        });

        return panel;
    }

    private boolean allAgreementsAccepted() {
        for (JRadioButton btn : agreeButtons) {
            if (!btn.isSelected()) {
                return false;
            }
        }
        return true;
    }
    
    
}