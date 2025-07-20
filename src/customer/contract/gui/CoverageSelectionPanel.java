package customer.contract.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import common.gui.OurColors;
import common.method.ButtonPainter;
import customer.contract.ContractInfo;
import customer.contract.ContractMainPanel;
import customer.contract.method.SelectedProductName;
import customer.contract.textSources.Coverage;
import customer.contract.textSources.CoverageFactory;

public class CoverageSelectionPanel extends JPanel {

    private ContractMainPanel contractMP;
    private ContractInfo ci;
    private Double totalPremium;
    
    private List<JCheckBox> checkBoxes = new ArrayList<>();
    private JLabel premiumLabel;
    private boolean confirmed = false;
    private int cardNumber = 6;
    
    public CoverageSelectionPanel(ContractMainPanel contractMP) {
        this.contractMP = contractMP;
        initUI();
    }

    private void initUI() {
    	ci = contractMP.getContractInfo();
    	
        setLayout(null);
        setBackground(new Color(0xECECEC));

        JLabel titleLabel = new JLabel("보장항목을 선택하세요.");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
        titleLabel.setBounds(498, 88, 555, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        // 피보험자 정보
        JLabel insuredLabel = new JLabel(String.format("피보험자: %d세 %s", ci.getInsuredAge()
        									, ci.getInsuredGender() == "M" ? "남성" : "여성"));
        insuredLabel.setFont(new Font("굴림", Font.PLAIN, 16));
        insuredLabel.setBounds(901, 172, 200, 30);
        add(insuredLabel);

        // 선택한 보험상품명
        JLabel productLabel = new JLabel(SelectedProductName.getProduct_name());
        productLabel.setFont(new Font("굴림", Font.BOLD, 20));
        productLabel.setOpaque(true);
        productLabel.setBackground(new Color(220, 220, 220));
        productLabel.setBounds(488, 172, 580, 40);
        add(productLabel);

        // 보장항목 라벨
        JLabel coverageLabel = new JLabel("보장항목");
        coverageLabel.setFont(new Font("굴림", Font.BOLD, 18));
        coverageLabel.setBounds(488, 222, 200, 30);
        add(coverageLabel);

        // 보장항목 체크박스 리스트
        JPanel coveragePanel = new JPanel();
        coveragePanel.setLayout(new BoxLayout(coveragePanel, BoxLayout.Y_AXIS));
        coveragePanel.setBackground(Color.WHITE);

        List<Coverage> coverageList = CoverageFactory.getAllCoverages();
        for (Coverage c : coverageList) {
            JCheckBox cb = new JCheckBox(String.format("[%s] %,.0f원 지급 (%s)"
            		, c.getName(), c.getBenefit(), c.getDetails()));
            cb.setFont(new Font("굴림", Font.PLAIN, 15));
            
            cb.addItemListener(e -> updatePremium());
            checkBoxes.add(cb);
            coveragePanel.add(cb);
        }

        JScrollPane scrollPane = new JScrollPane(coveragePanel);
        scrollPane.setBounds(488, 252, 580, 200);
        add(scrollPane);
        
        premiumLabel = new JLabel(String.format("총 보험료: %,.0f원", ci.getPremium()));
        premiumLabel.setFont(new Font("굴림", Font.BOLD, 18));
        premiumLabel.setBounds(498, 464, 300, 30);
        add(premiumLabel);
        
        
        addConfirmButton();
        addNavigationButtons();
    }
    
    private void updatePremium() {
        List<Coverage> allCoverages = CoverageFactory.getAllCoverages();
        
        totalPremium = ci.getPremium();

        for (int i = 0; i < checkBoxes.size(); i++) {
            if (checkBoxes.get(i).isSelected()) {
                totalPremium += allCoverages.get(i).getCharge(); // 순서 일치 보장됨
            }
        }
        
        premiumLabel.setText(String.format("총 보험료: %,.0f원", totalPremium));
    }
    
    private void addConfirmButton() {
        JButton confirmButton = new JButton("확인");
        confirmButton.setFont(new Font("굴림", Font.PLAIN, 18));
        confirmButton.setBackground(new Color(0x4CAF50));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBounds(745, 462, 100, 35);

        confirmButton.addActionListener(e -> {
        	/// TODO: 여기서 생성된 커버리지리스트를 던져줘야함
            List<String> selectedCoverages = new ArrayList<>();
            for (JCheckBox cb : checkBoxes) {
                if (cb.isSelected()) {
                    selectedCoverages.add(cb.getText());
                }
            }

            if (selectedCoverages.isEmpty()) {
                JOptionPane.showMessageDialog(this, "최소 한 개의 보장항목을 선택해주세요.");
            } else {
            	
                ci.setSelectedCoverageNames(selectedCoverages);
                confirmed = true;
                JOptionPane.showMessageDialog(this, "보장항목 선택이 완료되었습니다.");
            }
        });

        add(confirmButton);
    }

    private void addNavigationButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(551, 530, 500, 50);
        buttonPanel.setOpaque(false);

        JButton prevButton = new JButton("이전");
        ButtonPainter.stylePrimaryButtonGray(prevButton, 16);
        prevButton.setPreferredSize(new Dimension(180, 33));
        prevButton.addActionListener(e -> {
        	confirmed = false;
            clearSelections();
        	contractMP.ShowCard(contractMP.cardNames[cardNumber - 1]);
        });

        JButton nextButton = new JButton("다음");
        ButtonPainter.stylePrimaryButtonCarrot(nextButton, 16);
        nextButton.setPreferredSize(new Dimension(180, 33));
        nextButton.addActionListener(e -> {
            if (!confirmed) {
                JOptionPane.showMessageDialog(this, "확인 버튼을 눌러주세요.");
                return;
            }
            
            ci.setPremium_final(totalPremium);
            confirmed = false;
            clearSelections();
            contractMP.ShowCard(contractMP.cardNames[cardNumber + 1]);
        });

        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        add(buttonPanel);
    }
    
    public void clearSelections() {
        for (JCheckBox cb : checkBoxes) {
            cb.setSelected(false);
        }
    }
}
