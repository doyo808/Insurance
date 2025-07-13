package customer.claim.gui;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.*;

public class AccidentDatePanel extends JPanel {

   private JPanel parentCardPanel;
   private JLabel 사고일확인라벨;

   public AccidentDatePanel(JPanel parentCardPanel) {
      this.parentCardPanel = parentCardPanel;
      CardLayout cl = (CardLayout) (parentCardPanel.getLayout());

      setLayout(new GridBagLayout());
      
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(20, 20, 20, 20);
      gbc.fill = GridBagConstraints.HORIZONTAL;

      JLabel 사고일선택라벨 = new JLabel("사고(발병)일 선택");
      사고일선택라벨.setFont(new Font("굴림", Font.PLAIN, 20));
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      gbc.anchor = GridBagConstraints.CENTER;
      add(사고일선택라벨, gbc);

      UtilDateModel model = new UtilDateModel();
      Properties p = new Properties(); //날짜 선택창에서 버튼에 표시될 텍스트를 한글로 설정하고 싶을 때 사용
      p.put("text.today", "오늘");
      p.put("text.month", "월");
      p.put("text.year", "년");

      JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
      JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
      datePicker.getJFormattedTextField().setFont(new Font("굴림", Font.PLAIN, 20));

      gbc.gridy = 1;
      gbc.gridwidth = 2;
      add(datePicker, gbc);

      사고일확인라벨 = new JLabel("");
      사고일확인라벨.setHorizontalAlignment(JLabel.CENTER);
      사고일확인라벨.setFont(new Font("굴림", Font.PLAIN, 20));
      gbc.gridy = 2;
      gbc.gridwidth = 2;
      add(사고일확인라벨, gbc);

      datePicker.addActionListener(e -> {
         Date selectedDate = (Date) datePicker.getModel().getValue();
         Date today = removeTime(new Date());

         if (selectedDate == null) return;

         Calendar cal = Calendar.getInstance();
         cal.setTime(selectedDate);
         int year = cal.get(Calendar.YEAR);
         int month = cal.get(Calendar.MONTH) + 1;
         int day = cal.get(Calendar.DAY_OF_MONTH);

         사고일확인라벨.setVisible(true);
         사고일확인라벨.setText(String.format("%d년 %d월 %d일이 맞습니까?", year, month, day));

         if (selectedDate.after(today)) {
            JOptionPane.showMessageDialog(this, "해당 일은 현재보다 미래입니다!", "안내", JOptionPane.INFORMATION_MESSAGE);
            datePicker.getModel().setValue(null);
            사고일확인라벨.setText("");
         }
      });

   // 하단 버튼
      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
      JButton 이전버튼 = new JButton("이전");
      JButton 다음버튼 = new JButton("다음");

      buttonPanel.add(이전버튼);
      buttonPanel.add(다음버튼);
      
      이전버튼.addActionListener(e -> {
         cl.show(parentCardPanel, "ClaimTargetPanel");
         datePicker.getModel().setValue(null);
         사고일확인라벨.setText("");
      });

      다음버튼.addActionListener(e -> {
         Date selectedDate = (Date) datePicker.getModel().getValue();
         if (selectedDate != null) {
            cl.show(parentCardPanel, "ClaimSituationPanel");
         } else {
            JOptionPane.showMessageDialog(this, "날짜를 선택해주세요", "안내", JOptionPane.INFORMATION_MESSAGE);
         }
      });
      gbc.gridy = 3;
      gbc.gridwidth = 2;
      gbc.anchor = GridBagConstraints.CENTER;
      add(buttonPanel, gbc);
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
