package common.gui;

import javax.swing.*;
import java.awt.*;

public class ScrollWrapper extends JScrollPane {

    public ScrollWrapper(JPanel contentPanel) {
        super(contentPanel); // JScrollPane에 전달
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // 스크롤 속도 개선 (선택)
        getVerticalScrollBar().setUnitIncrement(16);
    }
    
    public ScrollWrapper(JFrame frame) {
        super(frame); // JScrollPane에 전달
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // 스크롤 속도 개선 (선택)
        getVerticalScrollBar().setUnitIncrement(16);
    }


}
