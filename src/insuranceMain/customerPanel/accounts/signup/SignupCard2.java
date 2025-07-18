package insuranceMain.customerPanel.accounts.signup;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.util.Random;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import common.database.model.CustomerModel;
import net.miginfocom.swing.MigLayout;

public class SignupCard2 extends JPanel {
    // 멤버변수 선언
    private boolean isIdentificated = false;
    private String generatedCode;
    private CardLayout cardLayout;
    private JPanel parentPanel;
    private JButton nextBtn;
    private JTextField inputField;

    public SignupCard2(CardLayout c, JPanel parentPanel, CustomerModel cm) {
        this.cardLayout = c;
        this.parentPanel = parentPanel;

        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));
        setPreferredSize(new Dimension(1440, 700));
        setBackground(Color.WHITE);

        // 상단 라벨
        JLabel titleLabel = new JLabel("본인확인");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, "dock north, align center, gapbottom 30");

        // 본인인증 버튼
        JButton authBtn = new JButton("본인인증");
        add(authBtn, "align center");

        authBtn.addActionListener(e -> showAuthPopup());

        // 다음 버튼
        nextBtn = new JButton("다음");
        nextBtn.setEnabled(isIdentificated);
        nextBtn.addActionListener(e -> {
            cardLayout.next(parentPanel);
        });
        add(nextBtn, "align center");
    }

    private void showAuthPopup() {
        JDialog popup = new JDialog((Frame) null, "본인 인증", true);
        ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource("signup/본인확인이미지.jpg"));
        Image originalImage = originalIcon.getImage();

        int fixedHeight = 750;
        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();
        int newWidth = (int) ((double) originalWidth / originalHeight * fixedHeight);

        // 오리지널 이미지 기준 라벨 위치 좌표
        int orix1 = 40, oriy1 = 196, orix2 = 360, oriy2 = 298;
        double ratio = (double)newWidth / (double)originalWidth;
        int newx1 = (int) (orix1 * ratio);
        int newy1 = (int) (oriy1 * ratio);
        int newx2 = (int) (orix2 * ratio);
        int newy2 = (int) (oriy2 * ratio);

        popup.setSize(newWidth + 30, fixedHeight + 110);
        popup.setLayout(new MigLayout("wrap 1", "[center]", "[][center]20[][]push"));
        popup.setLocationRelativeTo(null); // 화면 중앙

        // 인증번호 생성
        generatedCode = String.format("%06d", new Random().nextInt(1000000));

        // 이미지 리사이즈
        Image scaledImage = originalImage.getScaledInstance(newWidth, fixedHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        // 이미지 라벨 + 인증번호 라벨
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setPreferredSize(new Dimension(newWidth, fixedHeight));
        imageLabel.setLayout(null);

        String codeText = String.format("<html>[Web발신]<br>[KD손해보험]인증번호는<br>%s입니다.</html>", generatedCode); 
        JLabel codeLabel = new JLabel(codeText, SwingConstants.CENTER);
        codeLabel.setFont(new Font("맑은고딕", Font.PLAIN, 20));
        codeLabel.setForeground(Color.WHITE);
        codeLabel.setOpaque(true);
        codeLabel.setBackground(new Color(38, 37, 42, 255)); // 어두운 배경
        codeLabel.setSize(newx2 - newx1, newy2 - newy1);
        codeLabel.setLocation(newx1, newy1);

        imageLabel.add(codeLabel);
        popup.add(imageLabel, "align center");

        // 입력 필드 및 인증 버튼 (입력 필드는 멤버변수화)
        inputField = new JTextField(10);
        JButton verifyBtn = new JButton("인증");
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        inputPanel.add(inputField);
        inputPanel.add(verifyBtn);

        popup.add(Box.createVerticalStrut(10));
        popup.add(inputPanel, "align center");

        verifyBtn.addActionListener(e -> {
            if (inputField.getText().trim().equals(generatedCode)) {
                JOptionPane.showMessageDialog(popup, "인증이 완료되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
                isIdentificated = true;
                nextBtn.setEnabled(isIdentificated);
                popup.dispose();
            } else {
                JOptionPane.showMessageDialog(popup, "인증번호가 일치하지 않습니다.", "실패", JOptionPane.ERROR_MESSAGE);
            }
        });

        popup.setVisible(true);
    }
}