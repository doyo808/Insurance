package customer.contract.textSources;

import java.util.HashMap;
import java.util.Map;

public class InsuranceSummaryFactory {
	public static class InsuranceSummary {
        private final String productName;
        private final String line1;
        private final String line2;
        private final String premium;

        public InsuranceSummary(String productName, String line1, String line2, String premium) {
            this.productName = productName;
            this.line1 = line1;
            this.line2 = line2;
            this.premium = premium;
        }

        public String getProductName() {
            return productName;
        }

        public String getLine1() {
            return line1;
        }

        public String getLine2() {
            return line2;
        }
        
        public String getPremium() {
        	return premium;
        }
    }

    private static final Map<String, InsuranceSummary> summaryMap = new HashMap<>();

    static {
        summaryMap.put("든든암케어", new InsuranceSummary(
            "든든암케어",
            "암진단시 입원비, 수술비부터 요양비까지",
            "원플랜으로 전부 보장!",
            "월 60,000원 ~"
        ));

        summaryMap.put("행복건강플랜", new InsuranceSummary(
        	    "든든한건강보험",
        	    "암, 뇌졸중, 심근경색 등 중대질병 진단 시 보장",
        	    "입원, 수술, 통원 치료비까지 보장", 
        	    "월 50,000원 ~"
        ));

        summaryMap.put("스마일치아플랜", new InsuranceSummary(
            "스마트치아플랜 보장보험",
            "충전치료, 크라운, 임플란트 치료비 보장",
            "치주질환, 진단금 지급 포함", 
            "월 30,000원 ~"
        ));

    }

    public static InsuranceSummary getSummary(String productName) {
        return summaryMap.getOrDefault(productName, 
            new InsuranceSummary(productName, "설명 정보가 없습니다.", "", ""));
    }
}