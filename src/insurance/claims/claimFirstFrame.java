package insurance.claims;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import java.awt.Font;

public class claimFirstFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel 카드레이아웃Panel;
	private JRadioButton rdbtnNewRadioButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					claimFirstFrame frame = new claimFirstFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public claimFirstFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 0, 1440, 1024);
		카드레이아웃Panel = new JPanel(new CardLayout());
		setContentPane(카드레이아웃Panel);
		
		JPanel 청구메인Panel = new JPanel();
		카드레이아웃Panel.add(청구메인Panel, "name_2171556055398699");
		청구메인Panel.setLayout(null);
		
		JButton 청구내역조회버튼 = new JButton("보험금 청구내역 조회\n(진행상태 및 결과 조회)");
		청구내역조회버튼.setBounds(84, 316, 400, 350);
		청구메인Panel.add(청구내역조회버튼);
		
		
		
		JButton 추가청구버튼 = new JButton("이전에 청구했던 질병, 사고 추가 청구하기");
		추가청구버튼.setBounds(944, 316, 400, 180);
		청구메인Panel.add(추가청구버튼);
		
		추가청구버튼.addActionListener((e) -> {
			CardLayout cl2 = (CardLayout)(카드레이아웃Panel.getLayout());
			cl2.show(카드레이아웃Panel, "추가청구");
		});
		
		JButton 청구방법버튼 = new JButton("보험금 청구 방법");
		청구방법버튼.setBounds(532, 509, 400, 180);
		청구메인Panel.add(청구방법버튼);
		
		JButton 상황별필요서류버튼 = new JButton("상황별 필요서류 안내");
		상황별필요서류버튼.setBounds(944, 509, 400, 180);
		청구메인Panel.add(상황별필요서류버튼);
		
		JTextArea 보험금청구안내사항 = new JTextArea("보험금 청구 안내사항\n"
				+ "＊ 계약자, 피보험자 모두 이용가능합니다.(단, 미성년자 제외)\n"
				+ "＊ 계약자와 피보험자가 다른 계약의 보험금 청구는 피보험자가 접수해야 하며, 계약자가 접수할 경우\n"
				+ "     보상담당자가 피보험자의 개인정보동의서를 추가로 요청할 수 있습니다.(단, 14세 미만 미성년자 제외)\n"
				+ "＊ 각 사고별로 필요한 구비서류를 확인해 주세요."
				+ "＊ PC 또는 모바일로 보험금을 청구하시면 보상담당자가 보다 빠르게 서류를 확인할 수 있습니다.\n"
				+ "＊ 보험금은 사고일로부터 3년 이내에 청구할 수 있습니다."
				+ "＊ 5천 만원 이상 또는 사망보험금 청구건, 보험금을 위임하시는 경우에는 우편/방문을 통한 원본 서류 제출이 필요합니다.");
		보험금청구안내사항.setBounds(143, 711, 1179, 180);
		보험금청구안내사항.setEditable(false);
		청구메인Panel.add(보험금청구안내사항);
		
		JPanel 신규청구Panel = new JPanel();
		카드레이아웃Panel.add(신규청구Panel, "신규청구");
		신규청구Panel.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton(" 계약자(본인)");
		rdbtnNewRadioButton.setFont(new Font("굴림", Font.PLAIN, 45));
		rdbtnNewRadioButton.setBounds(520, 228, 528, 124);
		신규청구Panel.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton(" 다른사람");
		rdbtnNewRadioButton_1.setFont(new Font("굴림", Font.PLAIN, 45));
		rdbtnNewRadioButton_1.setBounds(520, 354, 528, 124);
		신규청구Panel.add(rdbtnNewRadioButton_1);
		
		JPanel 추가청구Panel = new JPanel();
		카드레이아웃Panel.add(추가청구Panel, "추가청구");
		

	}
}

//class 신규청구버튼 extends JButton {
//	public 신규청구버튼() {
//		init();
//	}
//	
//	private void init() {
//		JButton 신규청구버튼 = new JButton("보험금 신규 청구하기");
//		신규청구버튼.setBounds(532, 316, 400, 180);
//		청구메인Panel.add(신규청구버튼);
//		
//		신규청구버튼.addActionListener((e) -> {
//			CardLayout cl = (CardLayout)(카드레이아웃Panel.getLayout());
//			cl.show(카드레이아웃Panel, "신규청구");
//		});
//	}
//}
