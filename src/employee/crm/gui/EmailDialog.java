package employee.crm.gui;

import java.awt.Frame;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
	
	public EmailDialog(Frame parent, List<String> recipientEmails) {
        super(parent, "메일 발송", true);
        this.recipients = recipientEmails;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(700, 800);
        setLocationRelativeTo(parent);

        // ===== 제목 =====
        JLabel lblSubject = new JLabel("제목:");
        lblSubject.setBounds(30, 30, 100, 30);
        add(lblSubject);

        tfSubject = new JTextField();
        tfSubject.setBounds(130, 30, 520, 30);
        add(tfSubject);

        // ===== 내용 =====
        JLabel lblContent = new JLabel("내용:");
        lblContent.setBounds(30, 80, 100, 30);
        add(lblContent);

        taContent = new JTextArea();
        JScrollPane scrollContent = new JScrollPane(taContent);
        scrollContent.setBounds(130, 80, 520, 400);
        add(scrollContent);

        // ===== 첨부파일 =====
        btnAttach = new JButton("첨부파일 선택");
        btnAttach.setBounds(130, 500, 160, 30);
        add(btnAttach);

        lbAttach = new JLabel("선택된 파일 없음");
        lbAttach.setBounds(310, 500, 340, 30);
        add(lbAttach);

        btnAttach.addActionListener(e -> chooseFile());

        // ===== 전송 버튼 =====
        btnSend = new JButton("전송");
        btnSend.setBounds(390, 700, 120, 40);
        add(btnSend);

        btnSend.addActionListener(e -> sendEmail());

        // ===== 취소 버튼 =====
        btnCancel = new JButton("취소");
        btnCancel.setBounds(530, 700, 120, 40);
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

