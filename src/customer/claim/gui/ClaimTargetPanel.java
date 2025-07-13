package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import common.method.Validators;

public class ClaimTargetPanel extends JPanel {
   private JPanel parentCardPanel;

   public ClaimTargetPanel(JPanel parentCardPanel) {
      this.parentCardPanel = parentCardPanel;
      CardLayout cl = (CardLayout) parentCardPanel.getLayout();
      setLayout(new BorderLayout());

      // 상단 제목
      JLabel titleLabel = new JLabel("청구대상 선택");
      titleLabel.setFont(new Font("굴림", Font.PLAIN, 30));
      titleLabel.setHorizontalAlignment(JLabel.CENTER);
      add(titleLabel, BorderLayout.NORTH);

      // 중앙 패널: 라디오 버튼과 입력창
      JPanel centerPanel = new JPanel();
      centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
      add(centerPanel, BorderLayout.CENTER);

      // 라디오 버튼
      JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
      JRadioButton 계약자선택버튼 = new JRadioButton(" 계약자(본인)");
      계약자선택버튼.setFont(new Font("굴림", Font.PLAIN, 25));
      JRadioButton 다른사람선택버튼 = new JRadioButton(" 다른사람");
      다른사람선택버튼.setFont(new Font("굴림", Font.PLAIN, 25));

      ButtonGroup 선택버튼그룹 = new ButtonGroup();
      선택버튼그룹.add(계약자선택버튼);
      선택버튼그룹.add(다른사람선택버튼);

      radioPanel.add(계약자선택버튼);
      radioPanel.add(다른사람선택버튼);
      centerPanel.add(radioPanel);

      // 입력 폼 (다른 사람일 경우)
      JPanel 다른사람정보입력패널 = new JPanel(new GridBagLayout());
      다른사람정보입력패널.setBorder(BorderFactory.createTitledBorder("다른 사람 정보 입력"));
      다른사람정보입력패널.setVisible(false);
      다른사람정보입력패널.setPreferredSize(new Dimension(600, 200));
      centerPanel.add(다른사람정보입력패널);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new java.awt.Insets(10, 10, 10, 10);
      gbc.anchor = GridBagConstraints.WEST;

      JLabel 이름라벨 = new JLabel("이름:");
      JTextField 이름필드 = new JTextField(15);
      이름필드.setFont(new Font("굴림", Font.PLAIN, 18));

      이름필드.addFocusListener(new FocusAdapter() {
         @Override
         public void focusLost(FocusEvent e) {
            String inputName = 이름필드.getText();
            if (!Validators.isValidName(inputName)) {
               JOptionPane.showMessageDialog(null, "이름은 한글 2~9글자 가능합니다.");
               이름필드.setText("");
            }
         }
      });

      JLabel 주민번호라벨 = new JLabel("주민등록번호:");
      JTextField 주민번호필드 = new JTextField(15);

      주민번호필드.addFocusListener(new FocusAdapter() {
         @Override
         public void focusLost(FocusEvent e) {
            String inputPersonal_id = 주민번호필드.getText();
            if (!Validators.isValidPersonal_id(inputPersonal_id)) {
               JOptionPane.showMessageDialog(null, "주민등록번호가 유효하지 않습니다.");
               주민번호필드.setText("");
            }
         }
      });

      JLabel 연락처라벨 = new JLabel("휴대폰 번호:");
      JTextField 연락처필드 = new JTextField(15);

      gbc.gridx = 0; gbc.gridy = 0;
      다른사람정보입력패널.add(이름라벨, gbc);
      gbc.gridx = 1;
      다른사람정보입력패널.add(이름필드, gbc);

      gbc.gridx = 0; gbc.gridy = 1;
      다른사람정보입력패널.add(주민번호라벨, gbc);
      gbc.gridx = 1;
      다른사람정보입력패널.add(주민번호필드, gbc);

      gbc.gridx = 0; gbc.gridy = 2;
      다른사람정보입력패널.add(연락처라벨, gbc);
      gbc.gridx = 1;
      다른사람정보입력패널.add(연락처필드, gbc);

      계약자선택버튼.addActionListener(e -> {
         다른사람정보입력패널.setVisible(false);
         revalidate(); repaint();
      });

      다른사람선택버튼.addActionListener(e -> {
         다른사람정보입력패널.setVisible(true);
         revalidate(); repaint();
      });

      // 하단 버튼
      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
      JButton 이전버튼 = new JButton("이전");
      JButton 다음버튼 = new JButton("다음");

      이전버튼.addActionListener((e) -> {
         cl.show(parentCardPanel, "ClaimMainPanel");
         선택버튼그룹.clearSelection();
         이름필드.setText("");
         연락처필드.setText("");
         주민번호필드.setText("");
         다른사람정보입력패널.setVisible(false);
      });

      다음버튼.addActionListener((e) -> {
         if (선택버튼그룹.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "청구대상을 선택해주세요.", "안내", JOptionPane.INFORMATION_MESSAGE);
         } else if (다른사람선택버튼.isSelected() &&
               (이름필드.getText().trim().isEmpty() ||
                연락처필드.getText().trim().isEmpty() ||
                주민번호필드.getText().trim().isEmpty())) {
            JOptionPane.showMessageDialog(this, "모든 정보를 입력해주세요", "안내", JOptionPane.INFORMATION_MESSAGE);
         } else {
            cl.show(parentCardPanel, "AccidentDatePanel");
         }
      });

      buttonPanel.add(이전버튼);
      buttonPanel.add(다음버튼);
      add(buttonPanel, BorderLayout.SOUTH);
   }
}
