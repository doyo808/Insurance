package customer.payment.method;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import javax.swing.JFormattedTextField;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

    @Override
    public Object stringToValue(String text) throws java.text.ParseException {
        return formatter.parse(text);
    }

    @Override
    public String valueToString(Object value) {
        if (value != null) {
            if (value instanceof Date) {
                return formatter.format((Date) value);
            } else if (value instanceof Calendar) {
                return formatter.format(((Calendar) value).getTime());
            }
        }
        return "";
    }
}