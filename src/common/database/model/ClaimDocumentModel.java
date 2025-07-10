package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ClaimDocumentModel {
    private Integer claim_document_id;
    private Integer claim_id;
    private String document_type_cd;
    private String file_path;
    private Date submission_date;
    private String document_status;
    private String remarks;

    public ClaimDocumentModel(Integer claim_document_id, Integer claim_id, String document_type_cd, String file_path,
                              Date submission_date, String document_status, String remarks) {
        this.claim_document_id = claim_document_id;
        this.claim_id = claim_id;
        this.document_type_cd = document_type_cd;
        this.file_path = file_path;
        this.submission_date = submission_date;
        this.document_status = document_status;
        this.remarks = remarks;
    }

    public ClaimDocumentModel(ResultSet rs) throws SQLException {
        this.claim_document_id = rs.getInt("claim_document_id");
        this.claim_id = rs.getInt("claim_id");
        this.document_type_cd = rs.getString("document_type_cd");
        this.file_path = rs.getString("file_path");
        this.submission_date = rs.getDate("submission_date");
        this.document_status = rs.getString("document_status");
        this.remarks = rs.getString("remarks");
    }

    public Integer getClaim_document_id() {
        return claim_document_id;
    }

    public void setClaim_document_id(Integer claim_document_id) {
        this.claim_document_id = claim_document_id;
    }

    public Integer getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(Integer claim_id) {
        this.claim_id = claim_id;
    }

    public String getDocument_type_cd() {
        return document_type_cd;
    }

    public void setDocument_type_cd(String document_type_cd) {
        this.document_type_cd = document_type_cd;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Date getSubmission_date() {
        return submission_date;
    }

    public void setSubmission_date(Date submission_date) {
        this.submission_date = submission_date;
    }

    public String getDocument_status() {
        return document_status;
    }

    public void setDocument_status(String document_status) {
        this.document_status = document_status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "ClaimDocumentModel [claim_document_id=" + claim_document_id + ", claim_id=" + claim_id +
               ", document_type_cd=" + document_type_cd + ", file_path=" + file_path + ", submission_date=" + submission_date +
               ", document_status=" + document_status + ", remarks=" + remarks + "]";
    }
}
