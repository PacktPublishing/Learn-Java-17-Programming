package com.packt.learnjava.ch04_exceptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Throws {

    private static void tryWithRecources() {
        Connection conn;
        ResultSet rs;
        try {
            conn = DriverManager.getConnection("dburl", "username", "password");
            rs = conn.createStatement().executeQuery("select * from some_table");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        try (conn; rs) {
            while (rs.next()) {
                //process the retrieved data
            }
        } catch (SQLException ex) {
            //Do something
            //The exception was probably caused by incorrect SQL statement
        }
    }

    private static void throwsDemo() throws SQLException{
        Connection conn = DriverManager.getConnection("dburl", "username", "password");
        ResultSet rs = conn.createStatement().executeQuery("select * from some_table");

        try (conn; rs) {
            while (rs.next()) {
                //process the retrieved data
            }
        } finally { }
    }


}
