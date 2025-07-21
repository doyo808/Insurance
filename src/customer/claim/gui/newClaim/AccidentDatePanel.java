package customer.claim.gui.newClaim;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import common.database.model.NewClaimDataModel;
import customer.claim.gui.component.BottomButtonPanel;
import customer.claim.gui.component.TitlePanel;

public class AccidentDatePanel extends JPanel {

	private JPanel parentCardPanel;
	private JLabel accidentDateChLabel;
	private JDatePickerImpl datePicker;

	public AccidentDatePanel(JPanel parentCardPanel, NewClaimDataModel claimData) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) (parentCardPanel.getLayout());
		setLayout(new BorderLayout());

		TitlePanel title = new TitlePanel("사고(발병)일 선택");
		add(title, BorderLayout.NORTH);

		// -------------------- 가운데 날짜 선택 패널 --------------------
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "오늘");
		p.put("text.month", "월");
		p.put("text.year", "년");

		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setFont(new Font("굴림", Font.PLAIN, 20));

		accidentDateChLabel = new JLabel("");
		accidentDateChLabel.setHorizontalAlignment(JLabel.CENTER);
		accidentDateChLabel.setFont(new Font("굴림", Font.PLAIN, 20));

		// DatePickerPanel 내부만 GridBagLayout으로 구성
		JPanel DatePickerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		// 1. 날짜 선택기 추가
		gbc.gridy = 0;
		DatePickerPanel.add(datePicker, gbc);

		// 2. 사고일확인라벨 초기화 및 추가
		accidentDateChLabel = new JLabel("");
		accidentDateChLabel.setHorizontalAlignment(JLabel.CENTER);
		accidentDateChLabel.setFont(new Font("굴림", Font.PLAIN, 20));

		gbc.gridy = 1;
		DatePickerPanel.add(accidentDateChLabel, gbc);

		// DatePickerPanel 전체를 가운데에 추가
		add(DatePickerPanel, BorderLayout.CENTER);

		// 날짜 선택 이벤트
		datePicker.addActionListener(e -> {
			Date selectedDate = (Date) datePicker.getModel().getValue();

			if (selectedDate == null)
				return;

			Date selectedDateWithoutTime = removeTime(selectedDate);
			Date today = removeTime(new Date());

			Calendar cal = Calendar.getInstance();
			cal.setTime(selectedDate);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);

			accidentDateChLabel.setVisible(true);
			accidentDateChLabel.setText(String.format("%d년 %d월 %d일이 맞습니까?", year, month, day));

			if (selectedDateWithoutTime.after(today)) {
				JOptionPane.showMessageDialog(this, "해당 일은 현재보다 미래입니다!", "안내", JOptionPane.WARNING_MESSAGE);
				datePicker.getModel().setValue(null);
				accidentDateChLabel.setText("");
				return;
			}
		});

		BottomButtonPanel bottomBP = new BottomButtonPanel(this);

		bottomBP.getPreviousButton().addActionListener((e) -> {
			datePicker.getModel().setValue(null);
			accidentDateChLabel.setText("");			
			cl.show(parentCardPanel, "ClaimTargetPanel");
		});

		bottomBP.getNextButton().addActionListener((e) -> {
			Date selectedDate = (Date) datePicker.getModel().getValue();
			if (selectedDate == null) {
				JOptionPane.showMessageDialog(this, "날짜를 선택해주세요", "안내", JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				claimData.setAccident_date(selectedDate);
			}
//			System.out.println(claimData.toString()); // 디버깅용

			cl.show(parentCardPanel, "ClaimSituationPanel");

		});
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
