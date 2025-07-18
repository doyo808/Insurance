package common.method;

import javax.swing.text.*;

public class KoreanNameDocumentFilter extends DocumentFilter {
    private static final int MAX_LENGTH = 6;

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (string == null) return;

        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.insert(offset, string);

        if (isPartiallyValid(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr)
            throws BadLocationException {
        if (string == null) return;

        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.replace(offset, offset + length, string);

        if (isPartiallyValid(sb.toString())) {
            super.replace(fb, offset, length, string, attr);
        }
    }

    private boolean isPartiallyValid(String text) {
        // 1. 길이 제한
        if (text.length() > MAX_LENGTH) return false;

        // 2. 자음/모음/완성형 한글까지 포함하는 정규식
        return text.matches("[가-힣ㄱ-ㅎㅏ-ㅣ]{0," + MAX_LENGTH + "}");
    }
}