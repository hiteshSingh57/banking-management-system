package com.bank.dao.EmployeeDAO;

import com.bank.config.DBConnection;
import com.bank.model.EmployeeModel.EmployeeAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {
    public EmployeeAccount getEmployeeByEmployeeEmail(String email) {

        String sql = "SELECT employee_id, name, password FROM employees WHERE employee_id = ? AND status = 'ACTIVE'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email.trim());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new EmployeeAccount(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("password")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return null;
    }

}
