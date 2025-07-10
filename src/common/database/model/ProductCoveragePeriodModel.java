package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCoveragePeriodModel {
    private Integer product_coverage_period_id;
    private Integer product_id;
    private char coverage_period; // 'A', 'B', 'C', 'D'

    public ProductCoveragePeriodModel(Integer product_coverage_period_id, Integer product_id, char coverage_period) {
        this.product_coverage_period_id = product_coverage_period_id;
        this.product_id = product_id;
        this.coverage_period = coverage_period;
    }

    public ProductCoveragePeriodModel(ResultSet rs) throws SQLException {
        this.product_coverage_period_id = rs.getInt("product_coverage_period_id");
        this.product_id = rs.getInt("product_id");
        String cp = rs.getString("coverage_period");
        this.coverage_period = (cp != null && cp.length() > 0) ? cp.charAt(0) : '\0';
    }

    public Integer getProduct_coverage_period_id() {
        return product_coverage_period_id;
    }

    public void setProduct_coverage_period_id(Integer product_coverage_period_id) {
        this.product_coverage_period_id = product_coverage_period_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public char getCoverage_period() {
        return coverage_period;
    }

    public void setCoverage_period(char coverage_period) {
        this.coverage_period = coverage_period;
    }

    @Override
    public String toString() {
        return "ProductCoveragePeriodModel [product_coverage_period_id=" + product_coverage_period_id 
            + ", product_id=" + product_id 
            + ", coverage_period=" + coverage_period + "]";
    }
}
