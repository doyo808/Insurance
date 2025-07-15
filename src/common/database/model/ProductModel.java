package common.database.model;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductModel {
    
    private Integer product_id;
    private String division;
    private String product_name;
    private Integer join_age_low;
    private Integer join_age_high;
    private Integer join_limit_low;
    private Integer join_limit_high;
    private String term_and_conditions_path;
    private String product_manual_path;
    private Double base_premium;
    private Double premium_constant;
    private Blob product_introduce;

    // 전체 필드를 이용한 생성자
    public ProductModel(Integer product_id, String division, String product_name,
                   Integer join_age_low, Integer join_age_high,
                   Integer join_limit_low, Integer join_limit_high,
                   String term_and_conditions_path, String product_manual_path,
                   Double base_premium, Double premium_constant, Blob product_introduce) {
        this.product_id = product_id;
        this.division = division;
        this.product_name = product_name;
        this.join_age_low = join_age_low;
        this.join_age_high = join_age_high;
        this.join_limit_low = join_limit_low;
        this.join_limit_high = join_limit_high;
        this.term_and_conditions_path = term_and_conditions_path;
        this.product_manual_path = product_manual_path;
        this.base_premium = base_premium;
        this.premium_constant = premium_constant;
        this.setProduct_introduce(product_introduce);
    }

    // ResultSet으로부터 객체를 생성하는 생성자
    public ProductModel(ResultSet rs) throws SQLException {
        this.product_id = rs.getInt("product_id");
        this.division = rs.getString("division");
        this.product_name = rs.getString("product_name");
        this.join_age_low = rs.getInt("join_age_low");
        this.join_age_high = rs.getInt("join_age_high");
        this.join_limit_low = rs.getInt("join_limit_low");
        this.join_limit_high = rs.getInt("join_limit_high");
        this.term_and_conditions_path = rs.getString("term_and_conditions_path");
        this.product_manual_path = rs.getString("product_manual_path");
        this.base_premium = rs.getDouble("base_premium");
        this.premium_constant = rs.getDouble("premium_constant");
        this.setProduct_introduce(rs.getBlob("product_introduce"));
    }

    // Getter & Setter
    public Integer getProductId() {
        return product_id;
    }

    public void setProductId(Integer product_id) {
        this.product_id = product_id;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public Integer getJoinAgeLow() {
        return join_age_low;
    }

    public void setJoinAgeLow(Integer join_age_low) {
        this.join_age_low = join_age_low;
    }

    public Integer getJoinAgeHigh() {
        return join_age_high;
    }

    public void setJoinAgeHigh(Integer join_age_high) {
        this.join_age_high = join_age_high;
    }

    public Integer getJoinLimitLow() {
        return join_limit_low;
    }

    public void setJoinLimitLow(Integer join_limit_low) {
        this.join_limit_low = join_limit_low;
    }

    public Integer getJoinLimitHigh() {
        return join_limit_high;
    }

    public void setJoinLimitHigh(Integer join_limit_high) {
        this.join_limit_high = join_limit_high;
    }

    public String getTermAndConditionsPath() {
        return term_and_conditions_path;
    }

    public void setTermAndConditionsPath(String term_and_conditions_path) {
        this.term_and_conditions_path = term_and_conditions_path;
    }

    public String getProductManualPath() {
        return product_manual_path;
    }

    public void setProductManualPath(String product_manual_path) {
        this.product_manual_path = product_manual_path;
    }

    public Double getBasePremium() {
        return base_premium;
    }

    public void setBasePremium(Double base_premium) {
        this.base_premium = base_premium;
    }

    public Double getPremiumConstant() {
        return premium_constant;
    }

    public void setPremiumConstant(Double premium_constant) {
        this.premium_constant = premium_constant;
    }
    
    public Blob getProduct_introduce() {
		return product_introduce;
	}

	public void setProduct_introduce(Blob product_introduce) {
		this.product_introduce = product_introduce;
	}

    @Override
    public String toString() {
        return "Product [product_id=" + product_id
                + ", division=" + division
                + ", product_name=" + product_name
                + ", join_age_low=" + join_age_low
                + ", join_age_high=" + join_age_high
                + ", join_limit_low=" + join_limit_low
                + ", join_limit_high=" + join_limit_high
                + ", term_and_conditions_path=" + term_and_conditions_path
                + ", product_manual_path=" + product_manual_path
                + ", base_premium=" + base_premium
                + ", premium_constant=" + premium_constant
                + ", product_introduce=" + product_introduce + "]";
    }

	
}
