package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ClaimProcessLogModel {
    private Integer claim_process_log_id;
    private Integer claim_id;
    private Integer employee_id;
    private Date process_date;
    private String previous_status_cd;
    private String new_status_cd;
    private String action_description;

    public ClaimProcessLogModel(Integer claim_process_log_id, Integer claim_id, Integer employee_id, Date process_date,
                                String previous_status_cd, String new_status_cd, String action_description) {
        this.claim_process_log_id = claim_process_log_id;
        this.claim_id = claim_id;
        this.employee_id = employee_id;
        this.process_date = process_date;
        this.previous_status_cd = previous_status_cd;
        this.new_status_cd = new_status_cd;
        this.action_description = action_description;
    }

    public ClaimProcessLogModel(ResultSet rs) throws SQLException {
        this.claim_process_log_id = rs.getInt("claim_process_log_id");
        this.claim_id = rs.getInt("claim_id");
        this.employee_id = rs.getInt("employee_id");
        this.process_date = rs.getDate("process_date");
        this.previous_status_cd = rs.getString("previous_status_cd");
        this.new_status_cd = rs.getString("new_status_cd");
        this.action_description = rs.getString("action_description");
    }

    public Integer getClaim_process_log_id() {
        return claim_process_log_id;
    }

    public void setClaim_process_log_id(Integer claim_process_log_id) {
        this.claim_process_log_id = claim_process_log_id;
    }

    public Integer getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(Integer claim_id) {
        this.claim_id = claim_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Date getProcess_date() {
        return process_date;
    }

    public void setProcess_date(Date process_date) {
        this.process_date = process_date;
    }

    public String getPrevious_status_cd() {
        return previous_status_cd;
    }

    public void setPrevious_status_cd(String previous_status_cd) {
        this.previous_status_cd = previous_status_cd;
    }

    public String getNew_status_cd() {
        return new_status_cd;
    }

    public void setNew_status_cd(String new_status_cd) {
        this.new_status_cd = new_status_cd;
    }

    public String getAction_description() {
        return action_description;
    }

    public void setAction_description(String action_description) {
        this.action_description = action_description;
    }

    @Override
    public String toString() {
        return "ClaimProcessLogModel [claim_process_log_id=" + claim_process_log_id + ", claim_id=" + claim_id +
                ", employee_id=" + employee_id + ", process_date=" + process_date + ", previous_status_cd=" + previous_status_cd +
                ", new_status_cd=" + new_status_cd + ", action_description=" + action_description + "]";
    }
}
