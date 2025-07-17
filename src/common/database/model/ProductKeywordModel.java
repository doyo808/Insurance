package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductKeywordModel {
    private Integer product_keyword_id;
    private String keyword_name;

    public ProductKeywordModel(Integer product_keyword_id, String keyword_name) {
        this.product_keyword_id = product_keyword_id;
        this.keyword_name = keyword_name;
    }

    public ProductKeywordModel(ResultSet rs) throws SQLException {
        this.product_keyword_id = rs.getInt("product_keyword_id");
        this.keyword_name = rs.getString("keyword_name");
    }

    public Integer getProduct_keyword_id() {
        return product_keyword_id;
    }

    public void setProduct_keyword_id(Integer product_keyword_id) {
        this.product_keyword_id = product_keyword_id;
    }

    public String getKeyword_name() {
        return keyword_name;
    }

    public void setKeyword_name(String keyword_name) {
        this.keyword_name = keyword_name;
    }

	@Override
	public String toString() {
		return "ProductKeywordModel [product_keyword_id=" + product_keyword_id + ", keyword_name=" + keyword_name + "]";
	}

}
