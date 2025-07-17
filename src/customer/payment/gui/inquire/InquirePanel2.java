package customer.payment.gui.inquire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import common.gui.MainPageButton;
import customer.payment.gui.PaymentMainPanel;
import customer.payment.gui.components.PaymentDefaultButton;
import customer.payment.method.DateLabelFormatter;
import customer.payment.method.InquirePanel2Handler;
import insuranceMain.customerPanel.CustomerMainPanel;
import javax.swing.border.EmptyBorder;

public class InquirePanel2 extends JPanel {

    private static final long serialVersionUID = 1L;

    public JDatePickerImpl startDatePicker;
    public JDatePickerImpl endDatePicker;
    private String[] selectedData;
    public DefaultTableModel tableModel;
    public JTable table;
    private JLabel valProductName, valContractId, valStartDate, valEndDate, valPaymentType, valPremium;
    private static final Font VALUE_FONT = new Font("맑은 고딕", Font.BOLD, 13);
    
    public InquirePanel2(PaymentMainPanel pmp, CustomerMainPanel cmp) {
    	setPreferredSize(new Dimension(1440, 700));
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 제목
        JLabel title = new JLabel("보험금 납부 내역 조회", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // 센터 컨테이너
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        center.setBackground(Color.WHITE);
        center.add(Box.createVerticalStrut(10));

        // 날짜 선택 필드
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.setBackground(Color.WHITE);

        startDatePicker = createDatePicker();
        endDatePicker = createDatePicker();
        datePanel.add(startDatePicker);
        datePanel.add(endDatePicker);

     // 날짜 상호 제약
        startDatePicker.getModel().addChangeListener(e -> {
            Date start = (Date) startDatePicker.getModel().getValue();
            Date end = (Date) endDatePicker.getModel().getValue();

            if (start != null && end != null && end.before(start)) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(start);
                endDatePicker.getModel().setDate(
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                );
                endDatePicker.getModel().setSelected(true);
            }

            if (start != null && start.after(new Date())) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                startDatePicker.getModel().setDate(
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                );
                startDatePicker.getModel().setSelected(true);
            }
        });

        endDatePicker.getModel().addChangeListener(e -> {
            Date end = (Date) endDatePicker.getModel().getValue();
            if (end != null && end.after(new Date())) {
                Calendar cal = Calendar.getInstance(); // 오늘 날짜
                cal.setTime(new Date());
                endDatePicker.getModel().setDate(
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                );
                endDatePicker.getModel().setSelected(true); // 선택된 상태로 만들기
            }
        });

        // 날짜 버튼
        String[] buttons = {"1일", "7일", "15일", "30일", "6개월", "1년", "전체"};
        for (String label : buttons) {
            JButton btn = new PaymentDefaultButton(label);
            btn.setPreferredSize(new Dimension(70, 30));
            btn.addActionListener(e -> InquirePanel2Handler.setDateRange(this, label));
            datePanel.add(btn);
        }

        // 조회 버튼
        JButton searchBtn = new PaymentDefaultButton("조회");
        searchBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        searchBtn.setPreferredSize(new Dimension(100, 40));
        searchBtn.addActionListener(e -> {
            Date start = (Date) startDatePicker.getModel().getValue();
            Date end = (Date) endDatePicker.getModel().getValue();

            if (start == null || end == null) {
                JOptionPane.showMessageDialog(this, "날짜를 모두 선택해 주세요.", "날짜 오류", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (end.before(start)) {
                JOptionPane.showMessageDialog(this, "종료 날짜는 시작 날짜 이후여야 합니다.", "날짜 오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (start.after(new Date()) || end.after(new Date())) {
                JOptionPane.showMessageDialog(this, "미래 날짜는 선택할 수 없습니다.", "날짜 오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            
            updateTable(InquirePanel2Handler.loadData(start, end, Integer.valueOf(selectedData[2])));
        });
        datePanel.add(searchBtn);

        center.add(datePanel);
        
                JLabel subTitle = new JLabel("보험료 납부 내역");
                subTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
                subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
                center.add(subTitle);
        center.add(Box.createVerticalStrut(30));
        
        // 메인페이지 버튼
        JButton mainPageButton = new MainPageButton(cmp);
        // 1. 메인페이지 버튼 생성 및 크기 설정
        mainPageButton = new MainPageButton(cmp);
        mainPageButton.setPreferredSize(new Dimension(150, 50)); // 크기 설정

        // 2. 하단 패널에 버튼 중앙 정렬
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setPreferredSize(new Dimension(1440, 70)); // 높이는 여유 있게 조정
        bottomPanel.add(mainPageButton);

        // 3. 하단에 패널 추가
        add(bottomPanel, BorderLayout.SOUTH);
        
     // 테이블 생성
        String[] columns = {"구분", "납입월분", "입금일자", "납입횟수", "대상보험료", "실입금액", "입금방법"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // 셀 편집 불가
            }
        };
        
        
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setBackground(Color.WHITE);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        table.setFocusable(false);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 200));
        scrollPane.getViewport().setBackground(Color.WHITE);

        center.add(scrollPane);
        
        add(center, BorderLayout.CENTER);
    }
    
    private JDatePickerImpl createDatePicker() {
        UtilDateModel model = new UtilDateModel();
        Calendar today = Calendar.getInstance();
        model.setDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);

        Properties p = new Properties();
        p.put("text.today", "오늘");
        p.put("text.month", "월");
        p.put("text.year", "년");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl picker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        JFormattedTextField tf = picker.getJFormattedTextField();
        tf.setForeground(Color.BLACK);
        tf.setBackground(Color.WHITE);
        tf.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        tf.setColumns(12);
        tf.setPreferredSize(new Dimension(140, 30));

        // 전체 Picker 크기 조정
        picker.setPreferredSize(new Dimension(200, 30)); // 🔹 전체 박스 크기 증가

        // 버튼 텍스트 및 크기 변경
        if (picker.getComponent(1) instanceof JButton button) {
            button.setText("달력");
            button.setPreferredSize(new Dimension(60, 30));
        }

        return picker;
    }

    public void updateTable(List<String[]> datas) {
        // TODO: 실제 데이터 받아서 테이블 갱신 (예: List<Object[]> 형식일 경우 아래와 같이)
        tableModel.setRowCount(0);
//         예시:
    	
    	// 데이터가 null일 경우
    	if (datas == null) {
    		JOptionPane.showMessageDialog(
    		    this,
    		    "조회 결과가 없습니다.",
    		    "알림",
    		    JOptionPane.INFORMATION_MESSAGE
    		    
    		);
    		return;
    	}
    	
    	// 데이터가 null이 아닐경우    	
         List<String[]> rows = datas;
         for (String[] row : rows) {
             tableModel.addRow(row);
         }
         
    }
    
    public void setSelectedData(String[] selectedData) {
		this.selectedData = selectedData;
	}
}