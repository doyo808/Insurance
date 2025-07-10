package customer.claim.gui;

import java.awt.CardLayout;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class AccidentDatePanel extends JPanel {

   private JPanel parentCardPanel;
   private JLabel 사고일확인라벨; // 라벨이 계속 누적 생성되는 걸 막기 위함

   public AccidentDatePanel(JPanel parentCardPanel) {
      this.parentCardPanel = parentCardPanel;
//      setLayout(new BorderLayout());
      setLayout(null);
      setBounds(250, 0, 1440, 1024);
      CardLayout cl = (CardLayout) (parentCardPanel.getLayout());

      사고일확인라벨 = new JLabel() { // 고객이 선택한 날짜를 다시 한번 알려줌
         {
            setHorizontalAlignment(JLabel.CENTER);
            setFont(new Font("굴림", Font.PLAIN, 30));
            setBounds(433, 426, 531, 63);
         }
      };
      add(사고일확인라벨);

      JLabel 사고일선택라벨 = new JLabel("사고(발병)일 선택") {
         {
            setFont(new Font("굴림", Font.PLAIN, 30));
            setBounds(112, 137, 293, 63);
         }
      };
      add(사고일선택라벨);

      UtilDateModel model = new UtilDateModel();
      Properties p = new Properties();
      p.put("text.today", "오늘");
      p.put("text.month", "월");
      p.put("text.year", "년");

      JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//           datePanel.setBounds(300, 200, 150, 50);

      JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
      datePicker.getJFormattedTextField().setFont(new Font("굴림", Font.PLAIN, 20));
//           datePicker.setPreferredSize(new Dimension(200, 30));

      datePicker.setBounds(560, 224, 264, 30);
      add(datePicker);

      
      datePicker.addActionListener((e) -> {
         Date selectedDate = (Date) datePicker.getModel().getValue();
         Date today = removeTime(new Date());

         if (selectedDate == null) {
            return;
         }

         Calendar cal = Calendar.getInstance();
         cal.setTime(selectedDate);
         int year = cal.get(Calendar.YEAR);
         int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH는 0부터 시작하기 때문에 1작게 나옴
         int day = cal.get(Calendar.DAY_OF_MONTH);

         // 내가 선택한 날짜 출력하기
         사고일확인라벨.setVisible(true);
         String 사고일확인 = String.format("%d년 %d월 %d일이 맞습니까?", year, month, day);
         사고일확인라벨.setText(사고일확인);
         if (selectedDate.after(today)) {
            JOptionPane.showMessageDialog(this, "해당 일은 현재보다 미래입니다!", "안내", JOptionPane.INFORMATION_MESSAGE);
            datePicker.getModel().setValue(null);
            사고일확인라벨.setText("");
         }
      });

      JButton 이전버튼 = new JButton("이전") {
         {
            setBounds(470, 686, 100, 30);
            addActionListener((e) -> {
               cl.show(parentCardPanel, "ClaimTargetPanel");
               datePicker.getModel().setValue(null);
               사고일확인라벨.setText("");
            });
         }
      };

      JButton 다음버튼 = new JButton("다음") {
         ;
         {
            setBounds(853, 686, 100, 30);
         }
      };
      
      다음버튼.addActionListener((e) -> {
    	  Date selectedDate = (Date) datePicker.getModel().getValue();
    	  if(selectedDate != null) {
    		  cl.show(parentCardPanel, "ClaimCategoryPanel");
    	  } else {
    		  JOptionPane.showMessageDialog(this, "날짜를 선택해주세요", "안내", JOptionPane.INFORMATION_MESSAGE);
    	  }
      });
      add(이전버튼);
      add(다음버튼);
   }

   public static Date removeTime(Date date) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.set(Calendar.HOUR_OF_DAY, 0);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.SECOND, 0);
      cal.set(Calendar.MILLISECOND, 0);
      return cal.getTime();
   }

   class DateLabelFormatter extends AbstractFormatter {
      private String datePattern = "yyyy년 MM월 dd일";
      private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

      @Override
      public Object stringToValue(String text) throws ParseException {
         return dateFormatter.parseObject(text);
      }

      @Override
      public String valueToString(Object value) throws ParseException {
         if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
         }
         return "";
      }

   }

}
