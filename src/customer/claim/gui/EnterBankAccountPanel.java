package customer.claim.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import common.gui.CardSwitchButton;
import common.method.Validators;

public class EnterBankAccountPanel extends JPanel {
	private JPanel parentCardPanel;

	// 수정할 예정
	   public EnterBankAccountPanel(JPanel parentCardPanel) {
	      this.parentCardPanel = parentCardPanel;
	      CardLayout cl = (CardLayout) (parentCardPanel.getLayout());
	      setBounds(250, 0, 1440, 1024);
	      setLayout(null);
	      
	      JPanel 계좌번호입력패널 = new JPanel(new CardLayout());

	      JLabel 계좌번호입력라벨 = new JLabel("보험금 수령계좌 입력") {
	         {
	            setFont(new Font("굴림", Font.PLAIN, 30));
	            setBounds(112, 137, 293, 63);
	         }
	      };
	      add(계좌번호입력라벨);
	      
	      JRadioButton 자동이체선택버튼 = new JRadioButton(" 자동이체(계좌번호 자동입력)") {
	         {
	            setFont(new Font("굴림", Font.PLAIN, 35));
	            setBounds(589, 168, 500, 80);
	         }
	      };
	      
	      JRadioButton 계좌번호직접입력 = new JRadioButton(" 최근이용계좌") {
	         {
	            setBounds(589, 239, 500, 80);
	            setFont(new Font("굴림", Font.PLAIN, 35));
	         }
	      };
	      
	      

	      add(자동이체선택버튼);
	      add(계좌번호직접입력);

	      ButtonGroup 선택버튼그룹 = new ButtonGroup();
	      선택버튼그룹.add(자동이체선택버튼);
	      선택버튼그룹.add(계좌번호직접입력);
	      
	      

	      JPanel 계좌번호직접입력패널 = new JPanel() {
	         {
	            setBounds(302, 337, 734, 304);
	            setBorder(BorderFactory.createLineBorder(Color.GRAY));
	            setLayout(null);
	            setVisible(false);
	         }
	      };
	      add(계좌번호직접입력패널);

	      JLabel 은행명라벨 = new JLabel("은행명: ") {
	         {
	            setFont(new Font("굴림", Font.PLAIN, 20));
	            setBounds(230, 68, 150, 30);
	         }
	      };
	      
	      JTextField 은행명필드 = new JTextField() {
	         {
	            setFont(new Font("굴림", Font.PLAIN, 18));
	            setBounds(322, 68, 200, 30);
	         }
	      };
	      
			은행명필드.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					String inputName = 은행명필드.getText();
					if (!Validators.isValidName(inputName)) {
						JOptionPane.showMessageDialog(null, "이름은 한글 2~9글자 가능합니다.");
						은행명필드.setText("");
					}
				}

			});  
	      

	      JLabel 계좌번호라벨 = new JLabel("계좌번호: ") {
	         {
	            setBounds(176, 140, 150, 30);
	            setFont(new Font("굴림", Font.PLAIN, 20));
	         }
	      };
	      
	      JTextField 계좌번호필드 = new JTextField() {
	         { // 우선 주민번호는 '-'넣어야함 -> 추후에 
	            setFont(new Font("굴림", Font.PLAIN, 18));
	            setBounds(322, 140, 200, 30);
	         }
	      };
	      
			if (은행명필드.isValid()) {
				
			};

	      JLabel 예금주라벨 = new JLabel("예금주: ") {
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

	      계좌번호직접입력패널.add(은행명라벨);
	      계좌번호직접입력패널.add(은행명필드);
	      계좌번호직접입력패널.add(계좌번호라벨);
	      계좌번호직접입력패널.add(계좌번호필드);
	      계좌번호직접입력패널.add(예금주라벨);
	      계좌번호직접입력패널.add(연락처필드);
	      
	      자동이체선택버튼.addActionListener((e) -> {
	         계좌번호직접입력패널.setVisible(false);
	         revalidate(); // 레이아웃 재계산
	         repaint(); // 화면 다시 그림
	      });
	      
	      계좌번호직접입력.addActionListener((e) -> {
	         계좌번호직접입력패널.setVisible(true);
	         revalidate(); // 레이아웃 재계산
	         repaint(); // 화면 다시 그림
	      });

	      CardSwitchButton 다음 = new CardSwitchButton("다음", this, parentCardPanel, "", 100, 30);     
		   다음.setLocation(853, 686);   
		   
		   다음.addActionListener((e) -> {
		         if (선택버튼그룹.getSelection() == null) {
		            JOptionPane.showMessageDialog(this, "청구대상을 선택해주세요.", "안내", JOptionPane.INFORMATION_MESSAGE);
		         } else if (계좌번호직접입력.isSelected() && 
		               (은행명필드.getText().trim().isEmpty() || 
		                연락처필드.getText().trim().isEmpty() || 
		                계좌번호필드.getText().trim().isEmpty())) {
		            JOptionPane.showMessageDialog(this, "모든 계좌정보를 입력해주세요", "안내", JOptionPane.INFORMATION_MESSAGE); 
		            // this를 기준으로 메세지창 위치가 정해짐                                                               
		         } else {
		            cl.show(parentCardPanel, "AccidentDatePanel");
		         }
		      });
		   
		   CardSwitchButton 이전 = new CardSwitchButton("이전", this, parentCardPanel, "ClaimCategoryPanel", 100, 30);
		   이전.setLocation(470, 686);
		   
		   이전.addActionListener((e) -> {
		         cl.show(parentCardPanel, "ClaimMainPanel");
		         선택버튼그룹.clearSelection();
		         은행명필드.setText("");
		         연락처필드.setText("");
		         계좌번호필드.setText("");
		         계좌번호직접입력패널.setVisible(false);
		      });
	   }
}
