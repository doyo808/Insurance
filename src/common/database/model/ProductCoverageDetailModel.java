package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCoverageDetailModel {
	int productCoverageId;
	int productId;
	String productCoverName;
	String productCoverDetail;
	
	
	
	public ProductCoverageDetailModel(int productCoverageId, int productId, String productCoverName,
			String productCoverDetail) {
		super();
		this.productCoverageId = productCoverageId;
		this.productId = productId;
		this.productCoverName = productCoverName;
		this.productCoverDetail = productCoverDetail;
	}
	
	public ProductCoverageDetailModel(ResultSet rs) throws SQLException {
		this.productCoverageId = rs.getInt("product_coverage_id");
		this.productId = rs.getInt("product_id");
		this.productCoverName = rs.getString("product_cover_name");
		this.productCoverDetail = rs.getString("product_cover_detail");
	}
	
	public int getProductCoverageId() {
		return productCoverageId;
	}
	public void setProductCoverageId(int productCoverageId) {
		this.productCoverageId = productCoverageId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductCoverName() {
		return productCoverName;
	}
	public void setProductCoverName(String productCoverName) {
		this.productCoverName = productCoverName;
	}
	public String getProductCoverDetail() {
		return productCoverDetail;
	}
	public void setProductCoverDetail(String productCoverDetail) {
		this.productCoverDetail = productCoverDetail;
	}
	
	@Override
	public String toString() {
		return "ProductCoverageDetailModel [productCoverageId=" + productCoverageId + ", productId=" + productId
				+ ", productCoverName=" + productCoverName + ", productCoverDetail=" + productCoverDetail + "]";
	}
	
	
}
