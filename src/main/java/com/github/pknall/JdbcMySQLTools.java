package com.github.pknall;

// SPI - Service Provider Interface
// https://docs.oracle.com/javase/tutorial/ext/basics/spi.html
// https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html
// https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-installing.html
// https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html

import java.sql.*;

public class JdbcMySQLTools {
    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String url = "jdbc:mysql://localhost:65082/test";
            //  Class.forName("com.mysql.jdbc.Driver").newInstance(); - Automatically registered by SPI as com.mysql.cj.jdbc.Driver
            conn =
                    DriverManager.getConnection(url,"root", "test");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from test.foo");

            if (stmt.execute("SELECT * FROM foo")) {
                rs = stmt.getResultSet();
            }
            System.out.println("Result Set:");
            while (((rs != null) && rs.next())) {
                System.out.println(rs.getString(1));
            }
            // The newInstance() call is a work around for some
            // broken Java implementations
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------------------");
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception ex) {
            System.out.printf("Exception: " + ex.getMessage());
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {}
            rs = null;
        }

        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (SQLException ex) {}
            stmt = null;
        }

    }
}