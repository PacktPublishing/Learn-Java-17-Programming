package com.packt.learnjava.ch10_database;

import org.postgresql.ds.PGConnectionPoolDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Connect {
    public static void main(String... args) {
        //Uncomment the following code only after
        // the database learnjava is created
/*
        driverManager();
        dataSourceSimple();
        dataSourcePool();
*/
    }

    private static void driverManager() {
        String URL = "jdbc:postgresql://localhost/learnjava";
        Properties prop = new Properties();
        prop.put("user", "student");
        // prop.put( "password", "secretPass123" );
        try {
            Connection conn = DriverManager.getConnection(URL, prop);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void dataSourceSimple() {
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerName("localhost");
        source.setDatabaseName("learnjava");
        source.setUser("student");
        //source.setPassword("password");
        source.setLoginTimeout(10);
        try {
            Connection conn = source.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void dataSourcePool() {
        PGConnectionPoolDataSource source = new PGConnectionPoolDataSource();
        source.setServerName("localhost");
        source.setDatabaseName("learnjava");
        source.setUser("student");
        //source.setPassword("password");
        source.setLoginTimeout(10);
        try {
            PooledConnection conn = source.getPooledConnection();
            Set<Connection> pool = new HashSet<>();
            for(int i = 0; i < 10; i++){
                pool.add(conn.getConnection());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



}
