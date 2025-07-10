package common.database.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditModel {
    private Integer audit_id;
    private Integer contract_id;
    private Integer employee_id;
    private Date audit_date;
    private String audit_status;

    public AuditModel(Integer audit_id, Integer contract_id, Integer employee_id, Date audit_date, String audit_status) {
        this.audit_id = audit_id;
        this.contract_id = contract_id;
        this.employee_id = employee_id;
        this.audit_date = audit_date;
        this.audit_status = audit_status;
    }

    public AuditModel(ResultSet rs) throws SQLException {
        this.audit_id = rs.getInt("audit_id");
        this.contract_id = rs.getInt("contract_id");
        this.employee_id = rs.getInt("employee_id");
        this.audit_date = rs.getDate("audit_date");
        this.audit_status = rs.getString("audit_status");
    }

    public Integer getAudit_id() {
        return audit_id;
    }

    public void setAudit_id(Integer audit_id) {
        this.audit_id = audit_id;
    }

    public Integer getContract_id() {
        return contract_id;
    }

    public void setContract_id(Integer contract_id) {
        this.contract_id = contract_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Date getAudit_date() {
        return audit_date;
    }

    public void setAudit_date(Date audit_date) {
        this.audit_date = audit_date;
    }

    public String getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(String audit_status) {
        this.audit_status = audit_status;
    }

    @Override
    public String toString() {
        return "AuditModel [audit_id=" + audit_id + ", contract_id=" + contract_id + ", employee_id=" + employee_id +
               ", audit_date=" + audit_date + ", audit_status=" + audit_status + "]";
    }
}
