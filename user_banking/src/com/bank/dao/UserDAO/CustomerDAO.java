package com.bank.dao.UserDAO;

import com.bank.config.DBConnection;
import com.bank.model.UserModel.Customer;

import java.sql.*;
import java.util.*;
public class CustomerDAO {
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT a.created_at, a.account_number, u.full_name, a.balance, a.status, u.mobile " +
                    "FROM accounts a " +
                    "JOIN users u ON a.user_id = u.user_id ";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer c = new Customer(
                        rs.getString("created_at"),
                        rs.getString("account_number"),
                        rs.getString("full_name"),
                        rs.getString("mobile"),
                        rs.getString("status"),
                        rs.getDouble("balance")
                );
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Search by account number
    public Customer getCustomerByAccount(String accNo) {
        Customer c = null;

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT a.created_at, a.account_number, u.full_name, a.balance, a.status, u.mobile " +
                            "FROM accounts a " +
                            "JOIN users u ON a.user_id = u.user_id " +
                            "WHERE account_number = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, accNo.trim());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Customer(
                        rs.getString("created_at"),
                        rs.getString("account_number"),
                        rs.getString("full_name"),
                        rs.getString("mobile"),
                        rs.getString("status"),
                        rs.getDouble("balance")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }
}

