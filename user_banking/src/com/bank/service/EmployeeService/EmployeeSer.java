package com.bank.service.EmployeeService;

import com.bank.config.DBConnection;
import com.bank.model.EmployeeModel.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSer {

    public List<Employee> getAllEmployees() {

        List<Employee> list = new ArrayList<>();

        String sql = "SELECT employee_id, name, role, status, email, phone FROM employees";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("status"),
                        rs.getString("email"),
                        rs.getString("phone")
                );

                list.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
