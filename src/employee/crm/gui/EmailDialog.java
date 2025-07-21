package employee.crm.gui;

import java.awt.Font;
import java.awt.Frame;
import java.io.File;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EmailDialog extends JDialog {
	
	private JTextField tfSubject;
	private JTextArea taContent;
	private JButton btnSend, btnCancel, btnAttach;
	private JLabel lbAttach;
	private File attachedFile = null;
	private List<String> recipients;
	private JList<String> recipientList;
	
	public EmailDialog(Frame parent, List<String> recipientEmails, List<String> recipientsNames) {
        super(parent, "메일 발송", true);
        this.recipients = recipientEmails;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(700, 700);
        setLocationRelativeTo(parent);
        
        // ====== 받는 사람 목록 =======
        JLabel lbRecipients = new JLabel("받는사람\n (" + recipientEmails.size() + "명) :");
        lbRecipients.setBounds(30, 10, 120, 30);
        lbRecipients.setFont(new Font (lbRecipients.getFont().getName(), Font.BOLD, 14));
        add(lbRecipients);
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(int i = 0; i < recipientEmails.size(); i++) {
        	String name = i < recipientsNames.size() ? recipientsNames.get(i) : "";
        	listModel.addElement(name + " [" + recipientEmails.get(i) + "]");
        }
        
        recipientList = new JList<>(listModel);
        recipientList.setEnabled(false);
        recipientList.setFont(new Font (recipientList.getFont().getName(), Font.BOLD, 14));
        JScrollPane scrollRecipients = new JScrollPane(recipientList);
        scrollRecipients.setBounds(150, 10, 500, 100);
        add(scrollRecipients);
        

        // ===== 제목 =====
        JLabel lbSubject = new JLabel("제 목:");
        lbSubject.setBounds(30, 120, 100, 30);
        lbSubject.setFont(new Font (lbSubject.getFont().getName(), Font.BOLD, 14));
        add(lbSubject);

        tfSubject = new JTextField();
        tfSubject.setBounds(80, 120, 570, 30);
        tfSubject.setFont(new Font(tfSubject.getFont().getName(), Font.PLAIN, 14));
        add(tfSubject);

        // ===== 내용 =====
        JLabel lbContent = new JLabel("내 용:");
        lbContent.setBounds(30, 160, 100, 30);
        lbContent.setFont(new Font (lbContent.getFont().getName(), Font.BOLD, 14));
        add(lbContent);

        taContent = new JTextArea();
        JScrollPane scrollContent = new JScrollPane(taContent);
        scrollContent.setBounds(80, 160, 570, 400);
        taContent.setFont(new Font(taContent.getFont().getName(), Font.PLAIN, 14));
        add(scrollContent);

        // ===== 첨부파일 =====
        btnAttach = new JButton("첨부파일 선택");
        btnAttach.setBounds(80, 570, 160, 30);
        add(btnAttach);

        lbAttach = new JLabel("선택된 파일 없음");
        lbAttach.setBounds(270, 570, 390, 30);
        add(lbAttach);

        btnAttach.addActionListener(e -> chooseFile());

        // ===== 전송 버튼 =====
        btnSend = new JButton("전송");
        btnSend.setBounds(390, 610, 120, 40);
        add(btnSend);

        btnSend.addActionListener(e -> sendEmail());

        // ===== 취소 버튼 =====
        btnCancel = new JButton("취소");
        btnCancel.setBounds(530, 610, 120, 40);
        add(btnCancel);

        btnCancel.addActionListener(e -> dispose());

    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
        	attachedFile = fileChooser.getSelectedFile();
            lbAttach.setText(attachedFile.getName());
        }
    }

    private void sendEmail() {
        String subject = tfSubject.getText().trim();
        String content = taContent.getText().trim();

        if (subject.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "제목과 내용을 입력하세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 실제 이메일 발송 로직 연결
        boolean success = EmailSender.sendEmail(recipients, subject, content, attachedFile);

        if (success) {
            JOptionPane.showMessageDialog(this, "메일이 성공적으로 전송되었습니다.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "메일 전송에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}

