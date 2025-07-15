package common.method;

import java.time.LocalDate;

public class CalculateAge {
	public static int CalculateAge(LocalDate ld) {
		int age = 0;
		
		LocalDate now = LocalDate.now();
		
		age += now.getYear() - ld.getYear();
		if (now.isBefore(LocalDate.of(now.getYear(), ld.getMonth(), ld.getDayOfMonth()))) {
			age--;
		}
		
		return age;
	}
}
