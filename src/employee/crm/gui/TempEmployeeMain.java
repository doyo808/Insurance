package employee.crm.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import employee.product.view.ProductManageMainPanel;

public class TempEmployeeMain extends JFrame implements ActionListener {
	
	private JPanel contentPanel;
 
    public TempEmployeeMain() {
        setTitle("KB손해보험 다이렉트 센터");
        setBounds(0, 0, 1440, 1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);        
        setLayout(new BorderLayout());

        initTopMenu();
        initContentPanel();

        setVisible(true);
    }
    
    //메뉴버튼 사이즈 150, 50

    private void initTopMenu() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 30));
        
        JLabel lbTitle = new JLabel("KD손해보험 다이렉트 센터");
        lbTitle.setBounds(20, 30, 300, 25);
        lbTitle.setFont(new Font("", Font.BOLD, 25));
        lbTitle.setForeground(Color.BLUE);
        add(lbTitle); 
        
        String[] menuItems = {"보험상품관리", "보험계약관리", "보험료납부관리", "보험금청구관리", "고객관리", "사용자관리"};

        for (String item : menuItems) {
            JButton btn = new JButton(item);                        
            btn.setActionCommand(item);
            btn.addActionListener(this);
            topPanel.add(btn);
            
        }     

        add(topPanel, BorderLayout.NORTH);
    }

    private void initContentPanel() {
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(new JLabel("메뉴를 선택해주세요.", SwingConstants.CENTER), BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void setContentPanel(JPanel newPanel) {
        contentPanel.removeAll();
        contentPanel.add(newPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "보험상품관리":
                setContentPanel(new ProductManageMainPanel());
                break;
            case "보험계약관리":
                //setContentPanel(new ProductJoinPanel());
                break;
            case "보험료납부관리":
                //setContentPanel(new ClaimPanel());
                break;
            case "보험금청구관리":
                //setContentPanel(new PaymentPanel());
                break;
            case "고객관리":
            	setContentPanel(new SearchCrmPanel());
                break;
            case "사용자관리":
            	//setContentPanel(new MyPageMainPanel());
                break;
            default:
                setContentPanel(new JPanel());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TempEmployeeMain::new);
    }
}

