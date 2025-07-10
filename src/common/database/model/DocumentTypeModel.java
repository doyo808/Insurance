package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentTypeModel {
    private String document_type_cd;
    private String document_type_name;
    private String document_description;

    public DocumentTypeModel(String document_type_cd, String document_type_name, String document_description) {
        this.document_type_cd = document_type_cd;
        this.document_type_name = document_type_name;
        this.document_description = document_description;
    }

    public DocumentTypeModel(ResultSet rs) throws SQLException {
        this.document_type_cd = rs.getString("document_type_cd");
        this.document_type_name = rs.getString("document_type_name");
        this.document_description = rs.getString("document_description");
    }

    public String getDocument_type_cd() {
        return document_type_cd;
    }

    public void setDocument_type_cd(String document_type_cd) {
        this.document_type_cd = document_type_cd;
    }

    public String getDocument_type_name() {
        return document_type_name;
    }

    public void setDocument_type_name(String document_type_name) {
        this.document_type_name = document_type_name;
    }

    public String getDocument_description() {
        return document_description;
    }

    public void setDocument_description(String document_description) {
        this.document_description = document_description;
    }

    @Override
    public String toString() {
        return "DocumentTypeModel [document_type_cd=" + document_type_cd + ", document_type_name=" + document_type_name
                + ", document_description=" + document_description + "]";
    }
}
