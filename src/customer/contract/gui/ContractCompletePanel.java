package customer.contract.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import customer.contract.ContractInfo;
import customer.contract.ContractMainPanel;
import customer.contract.method.SelectedProductName;

public class ContractCompletePanel extends JPanel {
    
	private ContractMainPanel contractMP;
    private ContractInfo contractInfo;

    public ContractCompletePanel(ContractMainPanel contractMP) {
    	this.contractMP = contractMP;
        this.contractInfo = contractMP.getContractInfo();
        contractInfo.setCreatedDate(LocalDate.now().toString());
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("가입완료!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 28));
        add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        contentPanel.setBackground(Color.WHITE);

        // 1. 가입상품 정보
        JPanel productPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        productPanel.setBorder(BorderFactory.createTitledBorder("가입상품 정보"));
        productPanel.setBackground(new Color(245, 245, 245));
        productPanel.add(new JLabel("상품명:"));
        productPanel.add(new JLabel(nonNull(SelectedProductName.getProduct_name())));

        productPanel.add(new JLabel("월보험료:"));
        productPanel.add(new JLabel(nonNull(String.format("%,.0f원", contractInfo.getPremium_final()))));
        
        productPanel.add(new JLabel("납입기간:"));
        productPanel.add(new JLabel(nonNull(contractInfo.getProduct_payment_period()) + " 년"));

        productPanel.add(new JLabel("보장기간:"));
        productPanel.add(new JLabel(nonNull(contractInfo.getProduct_coverage_period()) + " 년"));

        // 2. 피보험자 정보
        JPanel insuredPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        insuredPanel.setBorder(BorderFactory.createTitledBorder("피보험자 정보"));
        insuredPanel.setBackground(new Color(245, 245, 245));
        insuredPanel.add(new JLabel("이름:"));
        insuredPanel.add(new JLabel(nonNull(contractInfo.getInsuredName())));

        insuredPanel.add(new JLabel("성별:"));
        insuredPanel.add(new JLabel(nonNull(contractInfo.getInsuredGender())));

        insuredPanel.add(new JLabel("나이:"));
        insuredPanel.add(new JLabel(nonNull(contractInfo.getInsuredAge())));

        insuredPanel.add(new JLabel("흡연/음주 여부:"));
        String smokeDrink = (contractInfo.getInsuredSmoke() != null ? (contractInfo.getInsuredSmoke().equalsIgnoreCase("Y") ? "흡연 " : "비흡연 ") : "") +
                            (contractInfo.getInsuredDrink() != null ? (contractInfo.getInsuredDrink().equalsIgnoreCase("Y") ? "/ 음주" : "/ 비음주") : "");
        insuredPanel.add(new JLabel(smokeDrink.trim()));

        // 3. 수익자 정보
        JPanel beneficiaryPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        beneficiaryPanel.setBorder(BorderFactory.createTitledBorder("수익자 정보"));
        beneficiaryPanel.setBackground(new Color(245, 245, 245));
        beneficiaryPanel.add(new JLabel("수익자 이름:"));
        beneficiaryPanel.add(new JLabel(nonNull(contractInfo.getBeneficiaryName())));

        beneficiaryPanel.add(new JLabel("관계:"));
        beneficiaryPanel.add(new JLabel(nonNull(contractInfo.getRelationship())));

        beneficiaryPanel.add(new JLabel("은행명:"));
        beneficiaryPanel.add(new JLabel(nonNull(contractInfo.getBeneficiaryBank())));

        beneficiaryPanel.add(new JLabel("계좌번호:"));
        beneficiaryPanel.add(new JLabel(nonNull(contractInfo.getBeneficiaryAccount())));

        // 패널들을 contentPanel에 추가
        contentPanel.add(productPanel);
        contentPanel.add(insuredPanel);
        contentPanel.add(beneficiaryPanel);

        add(contentPanel, BorderLayout.CENTER);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton myContractBtn = new JButton("내 계약 조회하기");
        JButton mainBtn = new JButton("메인화면");

        myContractBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "내 계약 조회 화면으로 이동합니다.");
            // 실제 화면 전환 코드 넣기
        });

        mainBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "메인 화면으로 이동합니다.");
            // 실제 화면 전환 코드 넣기
        });

        buttonPanel.add(myContractBtn);
        buttonPanel.add(mainBtn);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // null일 경우 빈 문자열로 변환
    private String nonNull(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}
