package common.method;

import javax.swing.text.*;

public class DigitLimitDocumentFilter extends DocumentFilter {
    private final int maxLength;

    public DigitLimitDocumentFilter(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (string == null) return;

        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.insert(offset, string);
        
        if (isValid(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr)
            throws BadLocationException {
        if (string == null) return;

        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.replace(offset, offset + length, string);

        if (isValid(sb.toString())) {
            super.replace(fb, offset, length, string, attr);
        }
    }

    private boolean isValid(String text) {
        return text.matches("\\d{0," + maxLength + "}");
    }
}