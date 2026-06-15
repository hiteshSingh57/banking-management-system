package com.bank.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    private static String URL ;
    private static String USER ;
    private static String PASS ;
    static {
        try {
            Properties pro = new Properties();
            InputStream input = DBConnection.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");
            if (input==null) {
                throw new RuntimeException("db.properties file not found");
            }
            pro.load(input);

            URL = pro.getProperty("db.url");
            USER = pro.getProperty("db.user");
            PASS = pro.getProperty("db.password");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
        public static Connection getConnection() throws Exception {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return  DriverManager.getConnection(URL,USER,PASS);
        }
}
