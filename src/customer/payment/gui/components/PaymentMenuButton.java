package customer.payment.gui.components;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

public class PaymentMenuButton extends JButton {
    public PaymentMenuButton(String txt) {
        setText(txt);
        setFont(new Font("맑은 고딕", Font.BOLD, 14));
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);

        setFocusPainted(false);
        setContentAreaFilled(false); // 내부 배경 직접 그림
        setOpaque(false);            // 배경 투명 (우리가 직접 그림)

        setBorder(new RoundedBorder(20));
        setPreferredSize(new Dimension(350, 35));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // 배경 그리기 (라운드)
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 배경 채우기 (라운드)
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // 텍스트 등 기본 그리기
        super.paintComponent(g);
        g2.dispose();
    }

    // 테두리 그리기 (라운드)
    static class RoundedBorder implements Border {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 2, radius);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE); // 테두리 색상 변경 가능
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }
}
