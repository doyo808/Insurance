package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ClaimTargetPanel extends JPanel {

   private JPanel parentCardPanel;

   public ClaimTargetPanel(JPanel parentCardPanel) {
      this.parentCardPanel = parentCardPanel;
      CardLayout cl = (CardLayout) (parentCardPanel.getLayout());
      setBounds(250, 0, 1440, 1024);
      setLayout(null);
      
      JPanel 청구대상패널 = new JPanel(new CardLayout());

      JLabel 청구대상선택라벨 = new JLabel("청구대상 선택") {
         {
            setFont(new Font("굴림", Font.PLAIN, 30));
            setBounds(112, 137, 293, 63);
         }
      };
      add(청구대상선택라벨);
      
      JRadioButton 계약자선택버튼 = new JRadioButton(" 계약자(본인)") {
         {
            setFont(new Font("굴림", Font.PLAIN, 35));
            setBounds(589, 168, 500, 80);
         }
      };
      
      JRadioButton 다른사람선택버튼 = new JRadioButton(" 다른사람") {
         {
            setBounds(589, 239, 500, 80);
            setFont(new Font("굴림", Font.PLAIN, 35));
         }
      };

      add(계약자선택버튼);
      add(다른사람선택버튼);

      ButtonGroup 선택버튼그룹 = new ButtonGroup();
      선택버튼그룹.add(계약자선택버튼);
      선택버튼그룹.add(다른사람선택버튼);
      
      

      JPanel 다른사람정보입력패널 = new JPanel() {
         {
            setBounds(302, 337, 734, 304);
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
            setLayout(null);
            setVisible(false);
         }
      };
      add(다른사람정보입력패널);

      JLabel 이름라벨 = new JLabel("이름: ") {
         {
            setFont(new Font("굴림", Font.PLAIN, 20));
            setBounds(230, 68, 150, 30);
         }
      };
      
      JTextField 이름필드 = new JTextField() {
         {
            setFont(new Font("굴림", Font.PLAIN, 18));
            setBounds(322, 68, 200, 30);
         }
      };

      JLabel 주민번호라벨 = new JLabel("주민등록번호: ") {
         {
            setBounds(176, 140, 150, 30);
            setFont(new Font("굴림", Font.PLAIN, 20));
         }
      };
      
      JTextField 주민번호필드 = new JTextField() {
         {
            setFont(new Font("굴림", Font.PLAIN, 18));
            setBounds(322, 140, 200, 30);
         }
      };

      JLabel 연락처라벨 = new JLabel("휴대폰 번호: ") {
         {
            setFont(new Font("굴림", Font.PLAIN, 20));
            setBounds(176, 212, 150, 30);
         }
      };
      
      JTextField 연락처필드 = new JTextField() {
         {
            setFont(new Font("굴림", Font.PLAIN, 18));
            setBounds(322, 211, 200, 30);
         }
      };

      다른사람정보입력패널.add(이름라벨);
      다른사람정보입력패널.add(이름필드);
      다른사람정보입력패널.add(주민번호라벨);
      다른사람정보입력패널.add(주민번호필드);
      다른사람정보입력패널.add(연락처라벨);
      다른사람정보입력패널.add(연락처필드);
      
      계약자선택버튼.addActionListener((e) -> {
         다른사람정보입력패널.setVisible(false);
         revalidate(); // 레이아웃 재계산
         repaint(); // 화면 다시 그림
      });
      
      다른사람선택버튼.addActionListener((e) -> {
         다른사람정보입력패널.setVisible(true);
         revalidate(); // 레이아웃 재계산
         repaint(); // 화면 다시 그림
      });


      JButton 이전버튼 = new JButton("이전") {
         {
            setBounds(470, 686, 100, 30);
         }
      };
      add(이전버튼);
      
      이전버튼.addActionListener((e) -> {
         cl.show(parentCardPanel, "ClaimMainPanel");
         선택버튼그룹.clearSelection();
         이름필드.setText("");
         연락처필드.setText("");
         주민번호필드.setText("");
         다른사람정보입력패널.setVisible(false);
      });

      JButton 다음버튼 = new JButton("다음") {
         {
            setBounds(853, 686, 100, 30);
         }
      };
      add(다음버튼);

      다음버튼.addActionListener((e) -> {
         if (선택버튼그룹.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "청구대상을 선택해주세요.", "안내", JOptionPane.INFORMATION_MESSAGE);
         } else if (다른사람선택버튼.isSelected() && 
               (이름필드.getText().trim().isEmpty() || 
                연락처필드.getText().trim().isEmpty() || 
                주민번호필드.getText().trim().isEmpty())) {
            JOptionPane.showMessageDialog(this, "모든 정보를 입력해주세요", "안내", JOptionPane.INFORMATION_MESSAGE); 
            // this를 기준으로 메세지창 위치가 정해짐                                                               
         } else {
            cl.show(parentCardPanel, "AccidentDatePanel");
         }

      });
      
   }
}

