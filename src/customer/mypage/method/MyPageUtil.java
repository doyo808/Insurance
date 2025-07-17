package customer.mypage.method;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class MyPageUtil {
	
	// 주민번호를 생년월일로 변화하는 Method
	public static String convertJuminToBirth(String juminNumber) {
		if(juminNumber == null || juminNumber.length() < 7) {
			return "잘못된 형식입니다!";
		}
		
		try {
			String birthPart = juminNumber.substring(0, 6);
			char genderCode = juminNumber.charAt(7);
			
			int yearPrefix;
			switch(genderCode) {
				case '1' : case '2' : yearPrefix = 1900; break;
				case '3' : case '4' : yearPrefix = 2000; break;
				default: return "잘못된 형식입니다!";
			}
			
			int year = yearPrefix + Integer.parseInt(birthPart.substring(0, 2));
			String month = birthPart.substring(2, 4);
			String day = birthPart.substring(4, 6);
			return String.format("%04d년 %s월 %s일", year, month, day);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "변환 오류";
		}		
	}
	
	
	// 이메일 분리/합치기
	
	// 이메일을 아이디/도메인으로 분할하여 필드에 설정
	public static void splitEmailToFields(String email, JTextField idField, JComboBox<String> domainBox) {
		if(email == null || !email.contains("@")) return;
		
		String[] parts = email.split("@");
		idField.setText(parts[0]);
		
		String domain = parts[1];
		boolean found = false;
		
		for(int i = 0; i < domainBox.getItemCount(); i++) {
			if(domainBox.getItemAt(i).equalsIgnoreCase(domain)) {
				domainBox.setSelectedIndex(i);
				found = true;
				break;
			}
		}
		
		if(!found) {
			domainBox.addItem(domain);
			domainBox.setSelectedItem(domain);
		}
	}
	
	// 아이디 + 도메인 조합하여 전체 이메일 반환
	public static String combineEmail(String idPart, Object domainPart) {
		if(idPart == null || domainPart == null) return "";
		return idPart.trim() + "@" + domainPart.toString().trim();
	}
	
	//전화번호 분리
	public static void splitPhoneToFields(String phoneNumber, JComboBox<String> cbPhoneLocal, JTextField tfPhoneMid, JTextField tfPhoneLast) {
		if ( phoneNumber == null || phoneNumber.isEmpty()) return;
		
		String[] parts = phoneNumber.split("-");
		if(parts.length == 3) {
			cbPhoneLocal.setSelectedItem(parts[0]);
			tfPhoneMid.setText(parts[1]);
			tfPhoneLast.setText(parts[2]);		
		}
	}
	
	//전화번호 병합
	
	public static String combinePhone(Object cbValue, String mid, String last) {
		String localNum = cbValue != null ? cbValue.toString().trim() : "";
		mid = mid !=null ? mid.trim() : "";
		last = last !=null ? last.trim() : "";
		return localNum + "-" + mid + "-" + last;
	}
	
	//날짜 포맷 보정
	
	public static String formatDate(String rawDateTime) {
		
		if(rawDateTime == null || rawDateTime.isEmpty()) {
			return "";
		}
		
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = inputFormat.parse(rawDateTime);
			return outputFormat.format(date);		
			
		} catch (Exception e) {
			e.printStackTrace();
			return rawDateTime;
		}

	}
	
	// 납입월 형식: 2025년 7월    
    public static String formatToYearMonth(String rawDateTime) {
        if (rawDateTime == null || rawDateTime.isEmpty()) return "";

        try {
            // 시간 소수점 이하 제거
            String trimmed = rawDateTime.split("\\.")[0];

            LocalDateTime dateTime;
            DateTimeFormatter inputFormatter;
            
            if (trimmed.contains("/")) {
                // 예: 25/07/11 14:18:31
                inputFormatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
            } else if (trimmed.contains("-")) {
                // 예: 2025-07-11 14:18:31
                inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            } else {
                return rawDateTime;
            }

            dateTime = LocalDateTime.parse(trimmed, inputFormatter);

            // 출력 포맷: 2025년 7월
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy년 M월");
            return outputFormatter.format(dateTime);

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return rawDateTime;
        }
    }

}

