package employee.crm.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EmailDialog extends JDialog {
	
	private JTextField tfSubject;
	private JTextArea taBody;
	private JButton btnSend, btnCancel, btnAttach;
	private File attatchmentFile;
	
	public EmailDialog(Frame parent, List<String> recipientEmails) {
		super(parent, "메일 보내기", true);
		setLayout(new BorderLayout(10, 10));
		setSize(700, 800);
		setLocationRelativeTo(parent);
		
		JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
		
		tfSubject = new JTextField();
        taBody = new JTextArea(10, 40);
        JScrollPane scroll = new JScrollPane(taBody);

        inputPanel.add(new JLabel("제목:"), BorderLayout.NORTH);
        inputPanel.add(tfSubject, BorderLayout.CENTER);
        inputPanel.add(scroll, BorderLayout.SOUTH);

        btnAttach = new JButton("첨부파일 선택");
        btnAttach.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                //attachmentFile = fileChooser.getSelectedFile();
                //btnAttach.setText("첨부됨: " + attachmentFile.getName());
            }
        });

        btnSend = new JButton("전송");
        btnCancel = new JButton("취소");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAttach);
        buttonPanel.add(btnSend);
        buttonPanel.add(btnCancel);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());
        btnSend.addActionListener(e -> {
            String subject = tfSubject.getText().trim();
            String body = taBody.getText().trim();
            if (subject.isEmpty() || body.isEmpty()) {
                JOptionPane.showMessageDialog(this, "제목과 내용을 모두 입력하세요.");
                return;
            }

            try {
                for (String email : recipientEmails) {
                    //MailSender.sendEmail(email, subject, body, attachmentFile);
                }
                JOptionPane.showMessageDialog(this, "메일이 전송되었습니다.");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "메일 전송 실패: " + ex.getMessage());
            }
        });
    }
}