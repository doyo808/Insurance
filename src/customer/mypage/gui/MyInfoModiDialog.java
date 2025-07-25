package customer.mypage.gui;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import common.account.login.Session;
import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;
import customer.mypage.method.MyPageUtil;

public class MyInfoModiDialog extends JDialog {

    private JTextField tfName, tfBirth, tfPhoneMid, tfPhoneLast, tfEmailId, tfJob, tfCompany, tfCompanyPhoneMid, tfCompanyPhoneLast, tfAddress1, tfAddress2, tfWorkAddress1, tfWorkAddress2;
    private JButton btnSave, btnCancel;
    private MyPageMainPanel parentPanel;
    private JComboBox<String> cbPhoneLocal, cbEmailDomain, cbJob, cbCompanyPhoneLocal;
    private CustomerModel cm;

    public MyInfoModiDialog(MyPageMainPanel parentPanel, CustomerModel cm) {
        super(SwingUtilities.getWindowAncestor(parentPanel), "기본정보 수정", ModalityType.APPLICATION_MODAL);
        this.parentPanel = parentPanel;
        this.cm = cm;

        setLayout(null);
        setSize(600, 550);
        setLocationRelativeTo(parentPanel);
        
        JLabel lbTitle = new JLabel("KD손해보험 다이렉트 센터");
        lbTitle.setBounds(20, 10, 200, 25);
        lbTitle.setFont(new Font("", Font.BOLD, 16));
        lbTitle.setForeground(Color.RED);
        add(lbTitle);       
        

        int labelW = 100, fieldW = 400, h = 25, y = 20, spacingY = 35;
        addLabelAndField("이름", tfName = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndField("생년월일", tfBirth = new JTextField(), 20, y += spacingY, labelW, fieldW);        
        //addLabelAndField("이메일", tfEmail = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndField("이메일", tfEmailId = new JTextField(), 20, y += spacingY, labelW, 200);
        
        JLabel lbAt = new JLabel("@");
        lbAt.setBounds(333, y, 19, 25);
        add(lbAt);
        
        // 이메일 도메인 콤보박스        
        cbEmailDomain = new JComboBox<>(new String[] {
        		"gmail.com", "naver.com", "hanmail.net", "hotmail.com", "직접입력"
        });
        cbEmailDomain.setEditable(false);
        cbEmailDomain.setBounds(351, y, 179, 25);
        add(cbEmailDomain);
        
        // '직접입력' 선택 시 도메인 수동 입력 가능하도록 처리
        cbEmailDomain.addActionListener(e -> {
        	String selected = (String) cbEmailDomain.getSelectedItem();
        	if ("직접입력".equals(selected)) {
        		cbEmailDomain.setEditable(true);
        		cbEmailDomain.setSelectedItem("");
        	} else {
        		cbEmailDomain.setEditable(false);
        		
        	}
        });        
        
        //addLabelAndField("전화번호", tfPhone = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndCombo("전화번호", cbPhoneLocal = new JComboBox<>(new String[] {
        		"010", "011", "016", "017", "018", "019", "02", "031", "032", "033", "041", "042", "043", "044", "051", "052", "053", "054", "055", "061", "062", "063", "064"
        }), 20, y += spacingY, labelW, 60);
        
        JLabel lbDash1 = new JLabel("-");
        lbDash1.setBounds(20 + labelW + 78, y, 5, h);
        add(lbDash1);

        tfPhoneMid = new JTextField();
        tfPhoneMid.setBounds(20 + labelW + 90, y, 60, h);
        add(tfPhoneMid);
        
        JLabel lbDash2 = new JLabel("-");
        lbDash2.setBounds(20 + labelW + 155, y, 5, h);
        add(lbDash2);      

        tfPhoneLast = new JTextField();
        tfPhoneLast.setBounds(20 + labelW + 165, y, 60, h);
        add(tfPhoneLast);        
        
        addLabelAndField("기본주소", tfAddress1 = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndField("상세주소", tfAddress2 = new JTextField(), 20, y += spacingY, labelW, fieldW);
        //addLabelAndField("직업", tfJob = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndCombo("직업", cbJob = new JComboBox<>(new String[] {
        		"공무원", "프리랜서", "회사원", "디자이너", "의사", "개발자", "마케터", "기획자", "교수", "방송인", "코미디언", "기타" 
        }), 20, y += spacingY, labelW, fieldW);
        addLabelAndField("회사명", tfCompany = new JTextField(), 20, y += spacingY, labelW, fieldW);
        //addLabelAndField("회사연락처", tfCompanyPhone = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndCombo("회사연락처", cbCompanyPhoneLocal = new JComboBox<>(new String[] {
        		"010", "011", "016", "017", "018", "019", "02", "031", "032", "033", "041", "042", "043", "044", "051", "052", "053", "054", "055", "061", "062", "063", "064"
        }), 20, y += spacingY, labelW, 60);
        
        JLabel lbDash3 = new JLabel("-");
        lbDash3.setBounds(20 + labelW + 78, y, 5, h);
        add(lbDash3);

        tfCompanyPhoneMid = new JTextField();
        tfCompanyPhoneMid.setBounds(20 + labelW + 90, y, 60, h);
        add(tfCompanyPhoneMid);
        
        JLabel lbDash4 = new JLabel("-");
        lbDash4.setBounds(20 + labelW + 155, y, 5, h);
        add(lbDash4);      

        tfCompanyPhoneLast = new JTextField();
        tfCompanyPhoneLast.setBounds(20 + labelW + 165, y, 60, h);
        add(tfCompanyPhoneLast);
        
        addLabelAndField("회사 기본주소", tfWorkAddress1 = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndField("회사 상세주소", tfWorkAddress2 = new JTextField(), 20, y += spacingY, labelW, fieldW);
        

        // 기존 정보 설정
        tfName.setText(cm.getCustomer_name().trim());
        tfBirth.setText(MyPageUtil.convertJuminToBirth(cm.getPersonal_id()).trim());        
        //tfEmail.setText(cm.getEmail());
        MyPageUtil.splitEmailToFields(cm.getEmail(), tfEmailId, cbEmailDomain);
        //tfPhone.setText(cm.getPhone_number());
        MyPageUtil.splitPhoneToFields(cm.getPhone_number(), cbPhoneLocal, tfPhoneMid, tfPhoneLast);        
        tfAddress1.setText(cm.getAddress_1().trim());
        tfAddress2.setText(cm.getAddress_2().trim());        
        //tfJob.setText(cm.getJob());
        cbJob.setSelectedItem(cm.getJob().trim());
        tfCompany.setText(cm.getCompany_name().trim());
        //tfCompanyPhone.setText(cm.getJob_phone_number().trim());
        MyPageUtil.splitPhoneToFields(cm.getJob_phone_number(), cbCompanyPhoneLocal, tfCompanyPhoneMid, tfCompanyPhoneLast);
        tfWorkAddress1.setText(cm.getJob_address1().trim());
        tfWorkAddress2.setText(cm.getJob_address2().trim());
        

        // 이름, 생년월일은 수정 불가
        tfName.setEditable(false);
        tfBirth.setEditable(false);

        btnSave = new JButton("저장");
        btnSave.setBounds(380, y + 50, 80, 30);
        add(btnSave);

        btnCancel = new JButton("취소");
        btnCancel.setBounds(470, y + 50, 80, 30);
        add(btnCancel);  
        
        btnCancel.addActionListener(e -> dispose());

        btnSave.addActionListener(e -> {
            try (Connection conn = InsuranceTeamConnector.getConnection()) {
                String sql = "UPDATE customers SET email = ?, phone_number = ?, address_1 = ?, address_2 = ?, job = ?, " +
                             "company_name = ?, job_phone_number = ?, job_address1 = ?, job_address2 = ? WHERE customer_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                
                //pstmt.setString(1, tfEmail.getText());
                pstmt.setString(1, MyPageUtil.combineEmail(tfEmailId.getText(), cbEmailDomain.getSelectedItem()));
                //pstmt.setString(2, tfPhone.getText());
                pstmt.setString(2, MyPageUtil.combinePhone(cbPhoneLocal.getSelectedItem(), tfPhoneMid.getText(), tfPhoneLast.getText()));
                pstmt.setString(3, tfAddress1.getText());
                pstmt.setString(4, tfAddress2.getText());                
                //pstmt.setString(5, tfJob.getText());
                pstmt.setString(5, cbJob.getSelectedItem().toString());
                pstmt.setString(6, tfCompany.getText());
                //pstmt.setString(7, tfCompanyPhone.getText());
                pstmt.setString(7, MyPageUtil.combinePhone(cbCompanyPhoneLocal.getSelectedItem(), tfCompanyPhoneMid.getText(), tfCompanyPhoneLast.getText()));
                pstmt.setString(8, tfWorkAddress1.getText());
                pstmt.setString(9, tfWorkAddress2.getText());
                pstmt.setInt(10, cm.getCustomer_id());

                int updated = pstmt.executeUpdate();

                if (updated > 0) {                 

                    JOptionPane.showMessageDialog(this, "정보가 성공적으로 수정되었습니다.");
                    
                	// DB 업데이트 성공 후
                    //cm.setEmail(tfEmail.getText());
                    cm.setEmail(MyPageUtil.combineEmail(tfEmailId.getText(), cbEmailDomain.getSelectedItem()));
                    //cm.setPhone_number(tfPhone.getText());
                    cm.setPhone_number(MyPageUtil.combinePhone(cbPhoneLocal.getSelectedItem(), tfPhoneMid.getText(), tfPhoneLast.getText()));                    
                    cm.setAddress_1(tfAddress1.getText());
                    cm.setAddress_2(tfAddress2.getText());                    
                    //cm.setJob(tfJob.getText());
                    cm.setJob(cbJob.getSelectedItem().toString());
                    cm.setCompany_name(tfCompany.getText());
                    //cm.setJob_phone_number(tfCompanyPhone.getText());
                    cm.setJob_phone_number(MyPageUtil.combinePhone(cbPhoneLocal.getSelectedItem(), tfCompanyPhoneMid.getText(), tfCompanyPhoneLast.getText()));                    
                    cm.setJob_address1(tfWorkAddress1.getText());
                    cm.setJob_address2(tfWorkAddress2.getText());
                    
                    Session.setCustomer(cm); // (만약 세션 저장소에 다시 설정해야 한다면)
                    
                    // 새로고침
                    parentPanel.refreshCustomerData();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "수정에 실패했습니다.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "오류 발생: " + ex.getMessage());
            }
        });
        
        
        
        
    }

    private void addLabelAndField(String label, JTextField field, int x, int y, int labelWidth, int fieldWidth) {
        JLabel lb = new JLabel(label);
        lb.setBounds(x, y, labelWidth, 25);
        add(lb);

        field.setBounds(x + labelWidth + 10, y, fieldWidth, 25);
        add(field);
    }
    
    private void addLabelAndCombo(String label, JComboBox<String> cbo, int x, int y, int labelWidth, int fieldWidth) {
        JLabel lb = new JLabel(label);
        lb.setBounds(x, y, labelWidth, 25);
        add(lb);

        cbo.setBounds(x + labelWidth + 10, y, fieldWidth, 25);
        add(cbo);
    }
    
    
    

    
}
