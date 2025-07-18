package employee.crm.gui;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;





public class EmailSender {

    // ★ 본인의 Gmail 계정 정보로 수정 ★
    private static final String USERNAME = "your_email@gmail.com";  // 보내는 이메일 주소
    private static final String PASSWORD = "your_app_password"; // 앱 비밀번호 (2단계 인증 사용 시 필요)


    public static boolean sendEmail(List<String> recipients, String subject, String content, File attachment) {
        Properties props = new Properties();

        // SMTP 설정
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // 인증 정보
        Session session = Session.getInstance(props, new Authenticator() {
        	
        	@Override
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(USERNAME, PASSWORD);
        	}
		});
        
        try {
        	// 메시지 구성
        	Message message = new MimeMessage(session);
        	message.setFrom(new InternetAddress(USERNAME));
        	// 수신자 여러 명 처리
        	InternetAddress[] addresses = recipients.stream().map(email -> {
        		try {
					return new InternetAddress(email);
				} catch (AddressException e) {
					e.printStackTrace();
					return null;
				}
        	}).filter(addr -> addr != null).toArray(InternetAddress[]::new);
        	
        	message.setRecipients(Message.RecipientType.TO, addresses);
        	message.setSubject(subject);
        	
        	 // 본문 + 첨부파일 포함을 위한 multipart
            Multipart multipart = new MimeMultipart();

            // 1. 본문 파트
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(content, "utf-8");
            multipart.addBodyPart(textPart);

            
            // 2. 첨부파일 파트
            if (attachment != null) {
            	
            	MimeBodyPart filePart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                filePart.setDataHandler(new DataHandler(source));                
                filePart.setFileName(attachment.getName());
                multipart.addBodyPart(filePart);
            }
            
            // 메시지에 multipart 설정
            message.setContent(multipart);

            // 전송
            Transport.send(message);

            System.out.println("✅ 메일 전송 완료");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ 메일 전송 실패");
            return false;
        }
    }
}
