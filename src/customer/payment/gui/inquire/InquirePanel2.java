package customer.payment.gui.inquire;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Properties;
import java.util.Date;
import java.util.Vector;

public class InquirePanel2 extends JPanel {

    private JDatePickerImpl startDatePicker, endDatePicker;
    private JTable table;
    private DefaultTableModel tableModel;

    public InquirePanel2() {
        setPreferredSize(new Dimension(1440, 700));
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("보험금 납부 내역 조회", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        centerPanel.setBackground(Color.WHITE);

        JLabel subTitle = new JLabel("보험료 납부 내역");
        subTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        subTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(subTitle);
        centerPanel.add(Box.createVerticalStrut(10));

        // 날짜 + 버튼 패널
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.setBackground(Color.WHITE);

        // 🔹 JDatePicker 설정
        startDatePicker = createDatePicker();
        endDatePicker = createDatePicker();

        datePanel.add(startDatePicker);
        datePanel.add(endDatePicker);

        // 날짜 버튼
        String[] dateButtons = {"1일", "7일", "15일", "30일", "6개월", "1년", "전체"};
        for (String label : dateButtons) {
            JButton btn = new JButton(label);
            btn.setPreferredSize(new Dimension(70, 30));
            btn.addActionListener(e -> setDateRange(label));
            datePanel.add(btn);
        }

        // 조회 버튼
        JButton searchBtn = new JButton("조회");
        searchBtn.setPreferredSize(new Dimension(80, 30));
        searchBtn.addActionListener(e -> loadData());
        datePanel.add(searchBtn);

        centerPanel.add(datePanel);
        centerPanel.add(Box.createVerticalStrut(30));  // 날짜 필드 아래 간격

        // 테이블 생성
        String[] columns = {"구분", "납입월분", "입금일자", "납입횟수", "대상보험료", "실입금액", "입금방법"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 200));
        scrollPane.getViewport().setBackground(Color.WHITE);

        centerPanel.add(scrollPane);
        add(centerPanel, BorderLayout.CENTER);
    }

    // 🔹 JDatePickerImpl 생성 메서드
    private JDatePickerImpl createDatePicker() {
        UtilDateModel model = new UtilDateModel();
        model.setDate(2025, 6, 2); // 초기값: 2025.07.02 (month는 0부터 시작)
        model.setSelected(true);

        Properties p = new Properties();
        p.put("text.today", "오늘");
        p.put("text.month", "월");
        p.put("text.year", "년");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        datePicker.setPreferredSize(new Dimension(120, 30));
        return datePicker;
    }

    // 🔹 날짜 범위 설정 메서드
    private void setDateRange(String label) {
        LocalDate today = LocalDate.now();
        LocalDate from = switch (label) {
            case "1일" -> today.minusDays(1);
            case "7일" -> today.minusDays(7);
            case "15일" -> today.minusDays(15);
            case "30일" -> today.minusDays(30);
            case "6개월" -> today.minusMonths(6);
            case "1년" -> today.minusYears(1);
            default -> LocalDate.of(2000, 1, 1);
        };

        Date start = Date.from(from.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());

        ((UtilDateModel) startDatePicker.getModel()).setValue(start);
        ((UtilDateModel) endDatePicker.getModel()).setValue(end);
    }

    // 🔹 테이블에 예시 데이터 로딩
    private void loadData() {
        tableModel.setRowCount(0);

        Vector<Object[]> data = new Vector<>();
        data.add(new Object[]{"3", "2025.07", "2025.07.02", 2, "50,000", "30,000", "계좌이체"});
        data.add(new Object[]{"2", "2025.07", "2025.07.01", 2, "50,000", "20,000", "계좌이체"});
        data.add(new Object[]{"1", "2025.06", "2025.06.03", 1, "50,000", "50,000", "계좌이체"});

        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    // 🔹 날짜 포맷 지정 formatter
    public static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd");

        @Override
        public Object stringToValue(String text) throws java.text.ParseException {
            return dateFormatter.parse(text);
        }

        @Override
        public String valueToString(Object value) {
            if (value != null && value instanceof Date) {
                return dateFormatter.format((Date) value);
            }
            return "";
        }
    }
}