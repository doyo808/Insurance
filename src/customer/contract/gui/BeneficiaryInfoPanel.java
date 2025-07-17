package customer.contract.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import common.gui.OurColors;
import customer.contract.ContractInfo;
import customer.contract.ContractMainPanel;
import customer.contract.method.SelectedProductName;

public class BeneficiaryInfoPanel extends JPanel {
    private ContractMainPanel contractMP;
    
    private JTextField nameField;
    private JComboBox<String> relationshipCombo;
    private JTextField bankField;
    private JTextField accountField;

    private boolean confirmed = false;
    private int cardNumber = 5;
    
    public BeneficiaryInfoPanel(ContractMainPanel contractMP) {
        this.contractMP = contractMP;
        initUI();
    }

    private void initUI() {
        this.setBackground(new Color(0xECECEC));
        this.setLayout(null);

        JLabel titleLabel = new JLabel(SelectedProductName.getProduct_name() + " [수익자 정보 입력]");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 32));
        titleLabel.setBounds(316, 80, 900, 57);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel);

        // 좌표 설정
        int labelX = 590;
        int fieldX = 748;
        int y = 122;
        int gap = 40;

        // 이름
        JLabel nameLabel = new JLabel("이름:");
        nameLabel.setFont(new Font("굴림", Font.PLAIN, 18));
        nameLabel.setBounds(590, 230, 100, 30);
        this.add(nameLabel);
        nameField = new JTextField();
        nameField.setFont(new Font("굴림", Font.PLAIN, 18));
        nameField.setBounds(748, 230, 220, 30);
        this.add(nameField);

        // 관계 (콤보박스)
        y += gap;
        JLabel relationLabel = new JLabel("관계:");
        relationLabel.setFont(new Font("굴림", Font.PLAIN, 18));
        relationLabel.setBounds(590, 287, 100, 30);
        this.add(relationLabel);
        String[] relationships = { "본인", "배우자", "자녀", "부모님" };
        relationshipCombo = new JComboBox<>(relationships);
        relationshipCombo.setFont(new Font("굴림", Font.PLAIN, 18));
        relationshipCombo.setBounds(748, 287, 220, 30);
        this.add(relationshipCombo);

        // 은행명
        y += gap;
        JLabel bankLabel = new JLabel("은행명:");
        bankLabel.setFont(new Font("굴림", Font.PLAIN, 18));
        bankLabel.setBounds(590, 347, 100, 30);
        this.add(bankLabel);
        bankField = new JTextField();
        bankField.setFont(new Font("굴림", Font.PLAIN, 18));
        bankField.setBounds(748, 347, 220, 30);
        this.add(bankField);

        // 계좌번호
        y += gap;
        JLabel accountLabel = new JLabel("계좌번호:");
        accountLabel.setFont(new Font("굴림", Font.PLAIN, 18));
        accountLabel.setBounds(590, 402, 100, 30);
        this.add(accountLabel);
        accountField = new JTextField();
        accountField.setFont(new Font("굴림", Font.PLAIN, 18));
        accountField.setBounds(748, 402, 220, 30);
        this.add(accountField);

        addConfirmButton(y + gap);
        addNavigationButtons(y + gap + 50);
    }

    private void addConfirmButton(int y) {
        JButton confirmButton = new JButton("확인");
        confirmButton.setFont(new Font("굴림", Font.PLAIN, 18));
        confirmButton.setBounds(734, 501, 100, 30);
        confirmButton.setBackground(Color.DARK_GRAY);
        confirmButton.setForeground(Color.WHITE);

        confirmButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String bank = bankField.getText().trim();
            String account = accountField.getText().trim();

            StringBuilder errors = new StringBuilder();

            if (name.isEmpty()) errors.append("이름을 입력해주세요.\n");
            if (bank.isEmpty()) errors.append("은행명을 입력해주세요.\n");
            if (account.isEmpty()) errors.append("계좌번호를 입력해주세요.\n");

            if (errors.length() > 0) {
                JOptionPane.showMessageDialog(this, errors.toString(), "입력 오류", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "입력이 확인되었습니다!", "확인", JOptionPane.INFORMATION_MESSAGE);
                confirmed = true;
            }
        });

        this.add(confirmButton);
    }

    private void addNavigationButtons(int y) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(447, 565, 700, 50);

        JButton prevButton = new JButton("이전");
        prevButton.setFont(new Font("굴림", Font.PLAIN, 18));
        prevButton.setBackground(OurColors.PREVIOUS_BUTTON);
        prevButton.setForeground(OurColors.TITLE_TEXT);
        prevButton.setPreferredSize(new java.awt.Dimension(267, 33));
        prevButton.addActionListener(e -> {
            confirmed = false;
            clearInputs();
            contractMP.ShowCard(contractMP.cardNames[cardNumber - 1]);
        });

        JButton nextButton = new JButton("다음");
        nextButton.setFont(new Font("굴림", Font.PLAIN, 18));
        nextButton.setBackground(OurColors.NEXT_BUTTON);
        nextButton.setForeground(OurColors.TITLE_TEXT);
        nextButton.setPreferredSize(new java.awt.Dimension(288, 33));
        nextButton.addActionListener(e -> {
            if (!confirmed) {
                JOptionPane.showMessageDialog(this, "확인버튼을 눌러주세요.");
                return;
            }

            ContractInfo ci = contractMP.getContractInfo();
            ci.setBeneficiaryName(nameField.getText().trim());
            ci.setRelationship((String) relationshipCombo.getSelectedItem());
            ci.setBeneficiaryBank(bankField.getText().trim());
            ci.setBeneficiaryAccount(accountField.getText().trim());

            confirmed = false;
            clearInputs();
            contractMP.ShowCard(contractMP.cardNames[cardNumber + 1]);
        });

        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        this.add(buttonPanel);
    }

    private void clearInputs() {
        nameField.setText("");
        relationshipCombo.setSelectedIndex(0);
        bankField.setText("");
        accountField.setText("");
    }
}
