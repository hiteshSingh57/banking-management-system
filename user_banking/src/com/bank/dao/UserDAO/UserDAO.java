package com.bank.dao.UserDAO;

import com.bank.config.DBConnection;
import com.bank.model.UserModel.UserAccount;

import java.sql.*;

public class UserDAO {
    public UserAccount getUserByAccountNumber(long account_Number) {
        String Sql = "SELECT u.user_id, u.password_hash, u.full_name, a.account_number " +
                "FROM accounts a JOIN users u ON a.user_id = u.user_id " +
                "WHERE a.account_number = ? AND a.status = 'ACTIVE' ";

        try  {
            Connection conn = DBConnection.getConnection();
            PreparedStatement state = conn.prepareStatement(Sql);

            state.setLong(1, account_Number);
            ResultSet rs = state.executeQuery();

            if (rs.next()) {
                return new UserAccount(
                        rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getLong("account_number"),
                        rs.getString("password_hash")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }
}