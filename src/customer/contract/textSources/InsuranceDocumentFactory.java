package customer.contract.textSources;

import java.util.HashMap;
import java.util.Map;

public class InsuranceDocumentFactory {

	/*	사용예>
	 	InsuranceDocument doc = InsuranceDocumentFactory.getDocument("스마트치아플랜 보장보험");

		if (doc != null) {
		    guideText.setText(doc.getGuideText());
		    termsText.setText(doc.getTermsText());
		}
	 */
	
    private static final Map<String, InsuranceDocument> documentMap = new HashMap<>();

    static {
        documentMap.put("스마트치아플랜 보장보험", new InsuranceDocument(
            "스마트치아플랜 보장보험",
            "주요 보장 내역:\n" +
            "- 충전치료, 크라운치료, 임플란트 치료 비용 보장\n" +
            "- 치주질환, 치수질환 등 진단 시 진단금 지급\n" +
            "- 사고로 인한 치아손상 치료비 추가 보장\n\n" +
            "보험기간: 20년 만기 (갱신형)\n" +
            "납입기간: 20년\n" +
            "가입연령: 만 20세 ~ 60세\n\n" +
            "※ 실제 보험료 및 상세 내용은 약관을 참고하세요.",
            "주요 약관 요약:\n" +
            "- 보장개시일로부터 90일 이내 발생한 치주질환은 보장 제외\n" +
            "- 임플란트 치료 보장은 연간 1회 한도로 제한\n" +
            "- 고의적 사고, 범죄행위로 인한 손상은 보장 제외\n\n" +
            "보험금 지급사유, 면책사유, 지급제한 등은 약관 전문을 반드시 확인 바랍니다."
        ));

        documentMap.put("든든한건강보험", new InsuranceDocument(
            "든든한건강보험",
            "주요 보장 내역:\n" +
            "- 암, 뇌졸중, 심근경색 등 중대질병 진단 시 보장\n" +
            "- 입원, 수술, 통원 치료비 보장\n\n" +
            "보험기간: 30년 만기\n" +
            "납입기간: 20년\n" +
            "가입연령: 만 19세 ~ 65세",
            "약관 요약:\n" +
            "- 가입 후 1년 내 고지의무 위반 시 계약 해지 가능\n" +
            "- 면책사유 및 보장제외 사항 명시\n" +
            "- 정확한 내용은 약관 전문을 참조"
        ));
        
        documentMap.put("든든암케어", new InsuranceDocument(
                "든든암케어",
                "주요 보장 내역:\n" +
                "- 주요 암 진단 시 보험금 지급\n" +
                "- 암 치료 관련 입원 및 수술비 보장\n" +
                "- 통원 치료비 일부 지원\n\n" +
                "보험기간: 25년 만기\n" +
                "납입기간: 20년\n" +
                "가입연령: 만 25세 ~ 60세\n\n" +
                "※ 자세한 보험료 및 보장내용은 약관을 참고하세요.",
                "약관 요약:\n" +
                "- 암 진단은 전문의 진단서에 근거함\n" +
                "- 고의적 자해 및 범죄 행위로 인한 암은 보장 제외\n" +
                "- 가입 후 90일 이내 진단된 암은 보장 제외\n\n" +
                "보험금 지급 조건, 면책사항 등은 약관 전문 확인 필수"
            ));
    }

    public static InsuranceDocument getDocument(String productName) {
        return documentMap.getOrDefault(productName, null);
    }
}