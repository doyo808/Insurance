package customer.contract.textSources;

public class InsuranceDocument {
	private String productName;
    private String guideText;
    private String termsText;

    public InsuranceDocument(String productName, String guideText, String termsText) {
        this.productName = productName;
        this.guideText = guideText;
        this.termsText = termsText;
    }

    public String getProductName() {
        return productName;
    }

    public String getGuideText() {
        return guideText;
    }

    public String getTermsText() {
        return termsText;
    }
}
