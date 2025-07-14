package customer.payment.method;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

public class AccountVerifier {

    // 1. DATA STRUCTURES (데이터 구조 정의)
    // =================================================================

    public static class YCodeRange {
        private final int from;
        private final int to;

        public YCodeRange(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public boolean contains(int code) {
            return code >= from && code <= to;
        }
    }

    public static class BasicRule {
        private final List<String> patterns;
        private final List<Object> yCodes; // String 또는 YCodeRange
        private final List<Predicate<String>> additionalRules;

        public BasicRule(List<String> patterns, List<Object> yCodes, List<Predicate<String>> additionalRules) {
            this.patterns = patterns;
            this.yCodes = yCodes == null ? new ArrayList<>() : yCodes;
            this.additionalRules = additionalRules == null ? new ArrayList<>() : additionalRules;
        }

        public BasicRule(List<String> patterns, List<Object> yCodes) {
            this(patterns, yCodes, null);
        }
        
        public BasicRule(List<String> patterns) {
            this(patterns, null, null);
        }
    }

    public static class Detector {
        private final String bank;
        private final List<BasicRule> basicRules;

        public Detector(String bank, List<BasicRule> basicRules) {
            this.bank = bank;
            this.basicRules = basicRules;
        }
    }

    // 2. DETECTORS INITIALIZATION (은행별 검증 규칙 초기화)
    // =================================================================

    private static final List<Detector> ALL_DETECTORS = new ArrayList<>();

    static {
    	// 모든 은행 등록 코드
    	ALL_DETECTORS.add(new Detector("kdb", List.of(
    	    new BasicRule(List.of("XXX-YY-ZZZZZC"), List.of("13", "20", "19", "11", "22")),
    	    new BasicRule(List.of("YYY-ZZZZZZZC-XXX"), List.of("013", "020", "019", "011", "022"))
    	)));

    	ALL_DETECTORS.add(new Detector("ibk", List.of(
    	    new BasicRule(List.of("BBBBBBBB-ZZ", "AAA-BBBBBBBB")),
    	    new BasicRule(List.of("XXX-YY-ZZZZZZC", "XXX-BBBBBB-YY-ZZC"), List.of("01", "02", "03", "13", "07", "06", "04"))
    	)));

    	ALL_DETECTORS.add(new Detector("kb", List.of(
    	    new BasicRule(List.of("XXXX-YY-ZZZZZC", "XXXX-YY-ZZZZZZZC"), List.of("01", "02", "25", "06", "18", "37", "90", "07")),
    	    new BasicRule(List.of("XXX-YY-ZZZZ-ZZC", "XXXXYY-ZZ-ZZZZZC"), List.of("01", "02", "24", "05", "04", "25", "26", "92", "07"),
    	        List.of(accountNumber -> accountNumber.startsWith("0")))
    	)));

    	ALL_DETECTORS.add(new Detector("hana", List.of(
    	    new BasicRule(List.of("XXX-YY-ZZZZZ-C"), List.of("13", "33", "18", "38", "19", "39", "26", "11", "22", "15", "23", "24", "29", "70", "73", "74", "75", "77")),
    	    new BasicRule(List.of("YYY-ZZZZZZ-ZZC"), List.of("611", "610", "620", "600", "601", "630", "621", "631", "810", "811", "817", "818", "814", "815", "704", "705", "707", "700", "703", new YCodeRange(710, 716))),
    	    new BasicRule(List.of("XXX-ZZZZZZ-ZZCYY"), List.of("05", "07", "08", "02", "01", "04", "94", "37", "32", "60"))
    	)));

    	ALL_DETECTORS.add(new Detector("suhyup", List.of(
    	    new BasicRule(List.of("XXX-YY-ZZZZZ-C"), List.of("01", "02", "06", "08")),
    	    new BasicRule(List.of("YYYZ-ZZZZ-ZZZC"), List.of("101", "201", "102", "202", "209", "103", "208", "106", "108", "113", "114", "206"),
    	        List.of(accountNumber -> accountNumber.startsWith("0") && accountNumber.replace("-", "").length() == 12)),
    	    new BasicRule(List.of("XXX-YY-ZZZZZZZZ-C"), List.of("40"))
    	)));

    	ALL_DETECTORS.add(new Detector("nh", List.of(
    	    new BasicRule(List.of("XXX-YY-ZZZZZC", "XXXX-YY-ZZZZZC"), List.of("01", "02", "12", "06", "05", "17", "04", "10", "14", "21", "24", "34", "45", "47", "49", "59", "80", "28", "31", "43", "46", "79", "81", "86", "87", "88")),
    	    new BasicRule(List.of("YYY-ZZZZ-ZZZZ-CT"), List.of("301", "302", "312", "306", "305", "317", "304", "310", "314", "321", "324", "334", "345", "347", "349", "359", "380", "028", "031", "043", "046", "079", "081", "086", "087", "088")),
    	    new BasicRule(List.of("XXXXXX-YY-ZZZZZC", "YYY-ZZZZ-ZZZZ-ZZC"), List.of("64", "65", "790", "791"))
    	)));

    	ALL_DETECTORS.add(new Detector("nh2", List.of(
    	    new BasicRule(List.of("XXXXXX-YY-ZZZZZC", "YYY-ZZZZ-ZZZZ-CT"), List.of("51", "52", "56", "55", "351", "352", "356", "355", "354", "360", "384", "394", "398", "028")),
    	    new BasicRule(List.of("XXXXXX-YY-ZZZZZC", "YYY-ZZZZ-ZZZZ-ZZC"), List.of("66", "67", "792"))
    	)));

    	ALL_DETECTORS.add(new Detector("woori", List.of(
    	    new BasicRule(List.of("SYYY-CZZ-ZZZZZZ"), List.of("006", "007", "002", "004", "003", "005"), List.of(accountNumber -> accountNumber.startsWith("1"))),
    	    new BasicRule(List.of("XXX-BBBBBC-YY-ZZC"), List.of("18", "92")),
    	    new BasicRule(List.of("XXX-YY-ZZZZZC"), List.of("006", "007", "002", "004", "003", "005")),
    	    new BasicRule(List.of("XXX-BBBBBB-YY-ZZC"), List.of("01", "15", "02", "12", "04", "03", "13")),
    	    new BasicRule(List.of("XXX-YY-ZZZZZZC"), List.of("01", "21", "24", "05", "04", "25", "09"))
    	)));

    	ALL_DETECTORS.add(new Detector("sc", List.of(
    	    new BasicRule(List.of("XXX-YY-ZZZZZC"), List.of("10", "20", "30", "85")),
    	    new BasicRule(List.of("XXX-YY-ZZZZZZZZC"), List.of("15", "16"))
    	)));

    	ALL_DETECTORS.add(new Detector("shinhan", List.of(
    	    new BasicRule(List.of("YYY-ZZZ-ZZZZZC"), List.of(new YCodeRange(100, 109), "160", "161", new YCodeRange(110, 139), new YCodeRange(155, 159), new YCodeRange(150, 154), new YCodeRange(140, 149), "180", "298", "268", "269")),
    	    new BasicRule(List.of("YYY-TTT-ZZZZZZZC"), List.of("560", "561", "562")),
    	    new BasicRule(List.of("XXX-YY-ZZZZZC", "XXX-YY-ZZZZZZZC"), List.of("01", "09", "61", "04", "05", "06", "08", "02", "07", "03", "81", "82")),
    	    new BasicRule(List.of("XXX-YY-ZZZZZC"), List.of("01", "02", "11", "13", "12", "03", "04", "05", "99")),
    	    new BasicRule(List.of("XXX-YYY-ZZZZZZZC"), List.of("901"))
    	)));

    	ALL_DETECTORS.add(new Detector("citi", List.of(
    	    new BasicRule(List.of("XXX-ZZZZZ-YYC-ZZ"), List.of("01", "11", "21", "25", "31", "42", "51", "71", "81", "23", "05", "06", "15", "26", "29", "07", "27", "55", "99", "03", "13", "33", "41", "43", "53", "63", "24")),
    	    new BasicRule(List.of("XX-YY-ZZZZZC", "Y-ZZZZZZ-ZZC"), List.of("20", "21", "32", "34", new YCodeRange(36, 38), "42", "46", "70", "71", new YCodeRange(72, 78), "80", "81", new YCodeRange(83, 88), new YCodeRange(91, 96), "99", "30", "33", "35", "41", new YCodeRange(43, 45), new YCodeRange(50, 58), "63", "64", new YCodeRange(60, 69), "40", "48", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", new YCodeRange(10, 19), "59")),
    	    new BasicRule(List.of("T-BBBBBB-CYY-ZZ"), List.of("25", "41", "24", "18"))
    	)));

    	ALL_DETECTORS.add(new Detector("daegu", List.of(
    	    new BasicRule(List.of("YY-ZZZZZZZZZZZ", "XXX-YY-ZZZZZZC", "YYY-ZZ-ZZZZZZC", "XXX-YY-ZZZZZZ-ZZZ"),
    	        List.of("05", new YCodeRange(91, 94), "96", "08", "02", "01", "04", "505", "508", "502", "501", "504", "06", "13", "14", "19", "519", "20", "520", "21", "521", "524", "25", "525", "27", "527", "28", "528", "937"))
    	)));

    	ALL_DETECTORS.add(new Detector("busan", List.of(
    	    new BasicRule(List.of("XXX-YYY-ZZZZZC", "ZYYY-ZZZ-ZZZZZZC"),
    	        List.of("107", "108", "109", "121", "123", "124", "122", "103", "101", "127", "716"))
    	)));

    	ALL_DETECTORS.add(new Detector("kfcc", List.of(
    	    new BasicRule(List.of("XXXX-YY-ZZZZZZ-C"), List.of("09", "10", "13", "37")),
    	    new BasicRule(List.of("XXXX-YYY-ZZZZZZ-C"), List.of(new YCodeRange(801, 810), new YCodeRange(851, 860))),
    	    new BasicRule(List.of("9YYY-ZZZZ-ZZZZ-C"), List.of("002", "003", "004", "072", "090", "091", "092", "093", "200", "202", "205", new YCodeRange(207, 210), "212", "005"),
    	        List.of(accountNumber -> accountNumber.startsWith("9")))
    	)));

    	ALL_DETECTORS.add(new Detector("kbank", List.of(
    	    new BasicRule(List.of("YYY-YNN-NNZZZZ"), List.of("1002", "1005"))
    	)));

    	ALL_DETECTORS.add(new Detector("kakao", List.of(
    	    new BasicRule(List.of("TYYY-ZZ-ZZZZZZZ"), List.of("333", "388", "355", "310"), List.of(accountNumber -> accountNumber.startsWith("3"))),
    	    new BasicRule(List.of("TYYY-ZZ-ZZZZZZZ"), List.of("777", "979"), List.of(accountNumber -> accountNumber.startsWith("7"))),
    	    new BasicRule(List.of("TYYY-ZZ-ZZZZZZZ"), List.of("101"), List.of(accountNumber -> accountNumber.startsWith("9")))
    	)));

    	ALL_DETECTORS.add(new Detector("toss", List.of(
    	    new BasicRule(List.of("YYYZ-ZZZZ-ZZZC"), List.of("100", "106", "300", "150", "700"), List.of(accountNumber -> accountNumber.charAt(3) == '8' || accountNumber.charAt(3) == '0')),
    	    new BasicRule(List.of("17ZZ-ZZZZ-ZZZZ", "19ZZ-ZZZZ-ZZZZ"), null, List.of(accountNumber -> accountNumber.startsWith("17") || accountNumber.startsWith("19")))
    	)));
    }

    // 3. VALIDATION LOGIC (검증 로직)
    // =================================================================

    public String detectBank(String accountNumber) {
        String cleanAccountNumber = accountNumber.replaceAll("-", "");

        for (Detector detector : ALL_DETECTORS) {
            for (BasicRule rule : detector.basicRules) {
                if (matchesRule(cleanAccountNumber, rule)) {
                    return detector.bank;
                }
            }
        }
        return "Unknown";
    }

    private boolean matchesRule(String cleanAccountNumber, BasicRule rule) {
        for (String pattern : rule.patterns) {
            // *** 수정된 부분: 정규식 대신 문자열 길이로 직접 비교 ***
            if (cleanAccountNumber.length() == pattern.replace("-", "").length()) {
                boolean yCodeMatch = checkYCodes(cleanAccountNumber, pattern, rule.yCodes);
                boolean additionalRulesMatch = checkAdditionalRules(cleanAccountNumber, rule.additionalRules);

                if (yCodeMatch && additionalRulesMatch) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkYCodes(String cleanAccountNumber, String pattern, List<Object> yCodes) {
        if (yCodes.isEmpty()) {
            return true;
        }

        String patternNoHyphen = pattern.replace("-", "");
        int yStartIndex = patternNoHyphen.indexOf('Y');
        if (yStartIndex == -1) {
            return true; // 패턴에 Y가 없으면 검사할 필요 없음
        }
        int yEndIndex = patternNoHyphen.lastIndexOf('Y');

        // Y-Code 부분의 길이가 계좌번호보다 길면 잘못된 접근이므로 실패 처리
        if (yEndIndex + 1 > cleanAccountNumber.length()) {
            return false;
        }

        String extractedYCodeStr = cleanAccountNumber.substring(yStartIndex, yEndIndex + 1);
        
        try {
            int extractedYCode = Integer.parseInt(extractedYCodeStr);
            for (Object yCode : yCodes) {
                if (yCode instanceof String && yCode.equals(extractedYCodeStr)) {
                    return true;
                } else if (yCode instanceof YCodeRange && ((YCodeRange) yCode).contains(extractedYCode)) {
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            return false; // Y-Code 부분이 숫자가 아니면 실패
        }
        return false;
    }

    private boolean checkAdditionalRules(String cleanAccountNumber, List<Predicate<String>> additionalRules) {
        return additionalRules.stream().allMatch(rule -> rule.test(cleanAccountNumber));
    }


    // 4. USAGE EXAMPLE (사용 예제)
    // =================================================================
    
    public static void main(String[] args) {
        AccountVerifier validator = new AccountVerifier();

        // *** 각 은행 규칙에 맞게 검증된 테스트 계좌번호 목록 ***
        String[] accountNumbers = {
            // 신한은행 (신) - YYY(110)가 110-139 범위에 속함
            "110-123-456789",
            "110123456789",
            // 기업은행 (구) - YY(01)가 코드 목록에 있음
            "123-01-1234561",
            "123011234561",
            // 산업은행 (신) - YYY(013)가 코드 목록에 있음
            "013-12345671-123",
            "01312345671123",
            // 국민은행 (현행) - YY(04)가 코드 목록에 있고, 0으로 시작함
            "012304-12-123451",
            "01230412123451",
            // 규칙에 없는 계좌 (실패 예상)
            "999-99-999999",
            "999999999999",
            
            "1002145651178"
        };
        
        System.out.println("===== Bank Account Validation Test (Revised) =====");
        for (String acc : accountNumbers) {
            String bank = validator.detectBank(acc);
            System.out.printf("Account: %-25s -> Bank: %s%n", acc, bank);
        }
    }
}
