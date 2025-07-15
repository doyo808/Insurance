package customer.claim.gui;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.*;

import common.database.model.NewClaimDataModel;
import common.gui.CardSwitchButton;
import common.method.Validators;

public class EnterBankAccountPanel extends JPanel {
   private JPanel parentCardPanel;

   public EnterBankAccountPanel(JPanel parentCardPanel, NewClaimDataModel claimData) {
      this.parentCardPanel = parentCardPanel;
      CardLayout cl = (CardLayout)(parentCardPanel.getLayout());
      setLayout(new BorderLayout());
      
      TitlePanel title = new TitlePanel("보험금 수령계좌 입력");
      add(title, BorderLayout.NORTH);

      // ⬅️ 가운데 패널 구성
      JPanel centerPanel = new JPanel(new BorderLayout());
      centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
      add(centerPanel, BorderLayout.CENTER);
      
      // 라디오 버튼 패널
      JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
      JRadioButton 자동이체선택버튼 = new JRadioButton(" 자동이체(계좌번호 자동입력)");
      자동이체선택버튼.setFont(new Font("굴림", Font.PLAIN, 15));
      자동이체선택버튼.setVisible(true);
      
      JRadioButton 계좌번호직접입력버튼 = new JRadioButton(" 직접입력");
      계좌번호직접입력버튼.setFont(new Font("굴림", Font.PLAIN, 15));
      계좌번호직접입력버튼.setVisible(true);
      
      ButtonGroup 선택버튼그룹 = new ButtonGroup();
      선택버튼그룹.add(자동이체선택버튼);
      선택버튼그룹.add(계좌번호직접입력버튼);
      
      radioPanel.add(자동이체선택버튼);
      radioPanel.add(계좌번호직접입력버튼);
 
      centerPanel.add(radioPanel, BorderLayout.NORTH);
      
      // 입력 패널 (직접입력 선택 시만 보여짐)
      JPanel 계좌번호직접입력패널 = new JPanel(new GridBagLayout());
      계좌번호직접입력패널.setBorder(BorderFactory.createTitledBorder("계좌 정보 입력"));
      계좌번호직접입력패널.setMaximumSize(new Dimension(400, 200)); // 크기 제한
      계좌번호직접입력패널.setVisible(false);
     
      centerPanel.add(계좌번호직접입력패널, BorderLayout.CENTER);
      
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(30, 30, 30, 10);
      gbc.anchor = GridBagConstraints.WEST;

      JLabel 은행명라벨 = new JLabel("은행명:");
      JTextField 은행명필드 = new JTextField(15);
      은행명필드.setFont(new Font("굴림", Font.PLAIN, 18));
      
      JLabel 계좌번호라벨 = new JLabel("계좌번호:");
      JTextField 계좌번호필드 = new JTextField(15);
      계좌번호필드.setFont(new Font("굴림", Font.PLAIN, 18));

      JLabel 예금주라벨 = new JLabel("예금주:");
      JTextField 연락처필드 = new JTextField(15);
      연락처필드.setFont(new Font("굴림", Font.PLAIN, 18));

      // 은행명 필드 이벤트(필요시 추가)
      은행명필드.addFocusListener(new FocusAdapter() {
         @Override
         public void focusLost(FocusEvent e) {
            String inputBankName = 은행명필드.getText();
            // 유효성 검사 추가 가능
         }
      });

      // 계좌 입력 배치
      gbc.gridx = 0; gbc.gridy = 0;
      계좌번호직접입력패널.add(은행명라벨, gbc);
      gbc.gridx = 1;
      계좌번호직접입력패널.add(은행명필드, gbc);

      gbc.gridx = 0; gbc.gridy = 1;
      계좌번호직접입력패널.add(계좌번호라벨, gbc);
      gbc.gridx = 1;
      계좌번호직접입력패널.add(계좌번호필드, gbc);

      gbc.gridx = 0; gbc.gridy = 2;
      계좌번호직접입력패널.add(예금주라벨, gbc);
      gbc.gridx = 1;
      계좌번호직접입력패널.add(연락처필드, gbc);

      // 선택 이벤트
      자동이체선택버튼.addActionListener((e) -> {
         계좌번호직접입력패널.setVisible(false);
         revalidate();
         repaint();
      });

      계좌번호직접입력버튼.addActionListener((e) -> {
         계좌번호직접입력패널.setVisible(true);
         revalidate();
         repaint();
      });

      // ⬇️ 하단 버튼 영역
      
      
      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));

      JButton previousButton = new JButton("이전");
      JButton nextButton = new JButton("다음");
      buttonPanel.add(previousButton);
      buttonPanel.add(nextButton);

      previousButton.addActionListener((e) -> {
         cl.show(parentCardPanel, "ClaimTypePanel");
         선택버튼그룹.clearSelection();
         은행명필드.setText("");
         연락처필드.setText("");
         계좌번호필드.setText("");
         계좌번호직접입력패널.setVisible(false);
      });

      nextButton.addActionListener((e) -> {
         if (선택버튼그룹.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "계좌정보를 선택해주세요.", "안내", JOptionPane.WARNING_MESSAGE);
         } else if (계좌번호직접입력버튼.isSelected() &&
               (은행명필드.getText().trim().isEmpty() ||
                연락처필드.getText().trim().isEmpty() ||
                계좌번호필드.getText().trim().isEmpty())) {
            JOptionPane.showMessageDialog(this, "모든 계좌정보를 입력해주세요", "안내", JOptionPane.WARNING_MESSAGE);
         } else {
            cl.show(parentCardPanel, "DocumentRegistrationPanel"); // TODO: 다음 패널 이름으로 변경
         }
      });

      buttonPanel.add(previousButton);
      buttonPanel.add(nextButton);
      add(buttonPanel, BorderLayout.SOUTH);
   }
}
