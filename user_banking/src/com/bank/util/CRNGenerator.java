package com.bank.util;

import java.sql.*;

public class CRNGenerator {

    public static String generateCRN(Connection conn) throws SQLException {

        String lastCrn = null;

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT crn FROM accounts ORDER BY CAST(crn AS UNSIGNED) DESC LIMIT 1");

        if (rs.next()) {
            lastCrn = rs.getString("crn");
        }

        if (lastCrn == null) {
            return "508010450";
        } else {
            long num = Long.parseLong(lastCrn);
            num++;
            return String.valueOf(num);
        }
    }
}