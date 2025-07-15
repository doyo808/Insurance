package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.database.model.ContractModel;

public class ContractDAO {

    public static ContractModel getById(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM CONTRACTS WHERE contract_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ContractModel(rs);
                }
                return null;
            }
        }
    }
    
    public static ContractModel getByContId(Integer contract_id, Connection conn) throws SQLException {
    	String sql = "Select * FROM contracts WHERE contract_id = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setInt(1, contract_id);
    		try (ResultSet rs = pstmt.executeQuery()) {
    			if (rs.next()) {
    				return new ContractModel(rs);
    			}
    			return null;
    		}
    	}
    }

    public static ArrayList<ContractModel> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM CONTRACTS";
        ArrayList<ContractModel> list = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ContractModel(rs));
            }
        }
        return list;
    }
    
    public static ArrayList<ContractModel> getAllById(Integer customer_id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM contracts WHERE customer_id = ?";
        ArrayList<ContractModel> list = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
        	pstmt.setInt(1, customer_id);
        	
        	try (ResultSet rs = pstmt.executeQuery()) {	
	           while (rs.next()) {
               list.add(new ContractModel(rs));
	           }
        	}	
        }
        return list;
    } 

    public static int insert(ContractModel model, Connection conn) throws SQLException {
        String sql = "INSERT INTO CONTRACTS (contract_id, customer_id, insured_id, beneficiary_id, product_id, signup_date, effective_date, created_date, payment_end_date, coverage_end_date, status, premium, product_payment_cycle_id) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getContract_id());
            pstmt.setInt(2, model.getCustomer_id());
            pstmt.setInt(3, model.getInsured_id());
            pstmt.setInt(4, model.getBeneficiary_id());
            pstmt.setInt(5, model.getProduct_id());
            pstmt.setDate(6, model.getSignup_date());
            pstmt.setDate(7, model.getEffective_date());
            pstmt.setDate(8, model.getCreated_date());
            pstmt.setDate(9, model.getPayment_end_date());
            pstmt.setDate(10, model.getCoverage_end_date());
            pstmt.setString(11, model.getStatus());
            pstmt.setDouble(12, model.getPremium());
            pstmt.setInt(13, model.getProduct_payment_cycle_id());

            return pstmt.executeUpdate();
        }
    }

    public static int update(ContractModel model, Connection conn) throws SQLException {
        String sql = "UPDATE CONTRACTS SET customer_id = ?, insured_id = ?, beneficiary_id = ?, product_id = ?, signup_date = ?, effective_date = ?, created_date = ?, payment_end_date = ?, coverage_end_date = ?, status = ?, premium = ?, product_payment_cycle_id = ? "
                   + "WHERE contract_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getCustomer_id());
            pstmt.setInt(2, model.getInsured_id());
            pstmt.setInt(3, model.getBeneficiary_id());
            pstmt.setInt(4, model.getProduct_id());
            pstmt.setDate(5, model.getSignup_date());
            pstmt.setDate(6, model.getEffective_date());
            pstmt.setDate(7, model.getCreated_date());
            pstmt.setDate(8, model.getPayment_end_date());
            pstmt.setDate(9, model.getCoverage_end_date());
            pstmt.setString(10, model.getStatus());
            pstmt.setDouble(11, model.getPremium());
            pstmt.setInt(12, model.getProduct_payment_cycle_id());
            pstmt.setInt(13, model.getContract_id());

            return pstmt.executeUpdate();
        }
    }

    public static int delete(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM CONTRACTS WHERE contract_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}
