package customer.mypage.gui;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import common.account.login.Session;
import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;

public class MyInfoModiDialog extends JDialog {

    private JTextField tfName, tfBirth, tfPhone, tfEmail, tfJob, tfCompany, tfCompanyPhone, tfAddress1, tfAddress2, tfWorkAddress1, tfWorkAddress2;
    private JButton btnSave, btnCancel;
    private MyPageMainPanel parentPanel;
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
        addLabelAndField("이메일", tfEmail = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndField("전화번호", tfPhone = new JTextField(), 20, y += spacingY, labelW, fieldW);        
        addLabelAndField("기본주소", tfAddress1 = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndField("상세주소", tfAddress2 = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndField("직업", tfJob = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndField("회사명", tfCompany = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndField("회사연락처", tfCompanyPhone = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndField("회사 기본주소", tfWorkAddress1 = new JTextField(), 20, y += spacingY, labelW, fieldW);
        addLabelAndField("회사 상세주소", tfWorkAddress2 = new JTextField(), 20, y += spacingY, labelW, fieldW);
        

        // 기존 정보 설정
        tfName.setText(cm.getCustomer_name());
        tfBirth.setText(cm.getPersonal_id());        
        tfEmail.setText(cm.getEmail());
        tfPhone.setText(cm.getPhone_number());
        tfAddress1.setText(cm.getAddress_1());
        tfAddress2.setText(cm.getAddress_2());        
        tfJob.setText(cm.getJob());
        tfCompany.setText(cm.getCompany_name());
        tfCompanyPhone.setText(cm.getJob_phone_number());        
        tfWorkAddress1.setText(cm.getJob_address1());
        tfWorkAddress2.setText(cm.getJob_address2());
        

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
                
                pstmt.setString(1, tfEmail.getText());
                pstmt.setString(2, tfPhone.getText());
                pstmt.setString(3, tfAddress1.getText());
                pstmt.setString(4, tfAddress2.getText());                
                pstmt.setString(5, tfJob.getText());
                pstmt.setString(6, tfCompany.getText());
                pstmt.setString(7, tfCompanyPhone.getText());                
                pstmt.setString(8, tfWorkAddress1.getText());
                pstmt.setString(9, tfWorkAddress2.getText());
                pstmt.setInt(10, cm.getCustomer_id());

                int updated = pstmt.executeUpdate();

                if (updated > 0) {                 

                    JOptionPane.showMessageDialog(this, "정보가 성공적으로 수정되었습니다.");
                    
                	// DB 업데이트 성공 후
                    cm.setEmail(tfEmail.getText());
                    cm.setPhone_number(tfPhone.getText());
                    cm.setAddress_1(tfAddress1.getText());
                    cm.setAddress_2(tfAddress2.getText());                    
                    cm.setJob(tfJob.getText());
                    cm.setCompany_name(tfCompany.getText());
                    cm.setJob_phone_number(tfCompanyPhone.getText());
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
    
    

    
}
