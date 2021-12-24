package com.packt.learnjava.ch10_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Crud {
    public static void main(String... args) {
        //Uncomment the following code only after
        // the database learnjava and table person are created
        //To create table person, first connect to the database
        // using the following psql command: \c learnjava
/*
        execute();
        executeQuery();
        executeUpdates();
*/
    }

    private static void execute(){
        cleanTablePerson();
        executeInsert();
        executeSelect();
        executeUpdate();
        executeDelete();
    }

    private static void executeQuery(){
        cleanTablePerson();
        executeQueryInsert();
        executeQuerySelect();
        executeQueryUpdate();
        executeQueryDelete();
    }

    private static void executeUpdates(){
        cleanTablePerson();
        executeUpdateInsert();
        executeUpdateSelect();
        executeUpdateUpdate();
        executeUpdateDelete();
    }

    private static void cleanTablePerson() {
        System.out.println("\ncleanTablePerson():");
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            st.execute("delete from person");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static String selectAllFirstNames() {
        String result = "";
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("select first_name from person");
            while (rs.next()) {
                result += rs.getString(1) + " ";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static void executeInsert() {
        System.out.println("\nexecuteInsert():");
        String sql = "insert into person (first_name, last_name, dob) values ('Bill', 'Grey', '1980-01-27')";
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            System.out.println(st.execute(sql));             //prints: false
            System.out.println(st.getResultSet() == null);   //prints: true
            System.out.println(st.getUpdateCount());         //prints: 1
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(selectAllFirstNames());           //prints: Bill
    }

    private static void executeSelect() {
        System.out.println("\nexecuteSelect():");
        String sql = "select first_name from person";
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            System.out.println(st.execute(sql));             //prints: false
            ResultSet rs = st.getResultSet();
            System.out.println(rs == null);                  //prints: false
            System.out.println(st.getUpdateCount());         //prints: -1
            while (rs.next()) {
                System.out.println(rs.getString(1)); //prints all first names
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void executeUpdate() {
        System.out.println("\nexecuteUpdate():");
        String sql = "update person set first_name = 'Adam'";
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            System.out.println(st.execute(sql));             //prints: false
            System.out.println(st.getResultSet() == null);   //prints: true
            System.out.println(st.getUpdateCount());         //prints: 1
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(selectAllFirstNames());           //prints: Adam
    }

    private static void executeDelete() {
        System.out.println("\nexecuteDelete():");
        String sql = "delete from person";
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            System.out.println(st.execute(sql));             //prints: false
            System.out.println(st.getResultSet() == null);   //prints: true
            System.out.println(st.getUpdateCount());         //prints: 1
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(selectAllFirstNames());           //prints:
    }

    private static void executeQueryInsert() {
        System.out.println("\nexecuteQueryInsert():");
        String sql = "insert into person (first_name, last_name, dob) values ('Bill', 'Grey', '1980-01-27')";
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            st.executeQuery(sql);   //PSQLException: No results were returned by the query.
        } catch (SQLException ex) {
            ex.printStackTrace();   //prints the stack trace
        }
        System.out.println(selectAllFirstNames());           //prints: Bill
    }

    private static void executeQuerySelect() {
        System.out.println("\nexecuteQuerySelect():");
        String sql = "select first_name from person";
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            ResultSet rs1 = st.executeQuery(sql);
            System.out.println(rs1 == null);          //prints: false
            ResultSet rs2 = st.getResultSet();
            System.out.println(rs2 == null);          //prints: false
            System.out.println(st.getUpdateCount());  //prints: -1
            while (rs1.next()) {
                System.out.println(rs1.getString(1)); //prints all first names
            }
            while (rs2.next()) {
                System.out.println(rs2.getString(1)); //prints:
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void executeQueryUpdate() {
        System.out.println("\nexecuteQueryUpdate():");
        String sql = "update person set first_name = 'Adam'";
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            st.executeQuery(sql);   //PSQLException: No results were returned by the query.
        } catch (SQLException ex) {
            ex.printStackTrace();     //prints: stack trace
        }
        System.out.println(selectAllFirstNames());           //prints: Adam
    }

    private static void executeQueryDelete() {
        System.out.println("\nexecuteQueryDelete():");
        String sql = "delete from person";
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            st.executeQuery(sql);   //PSQLException: No results were returned by the query.
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(selectAllFirstNames());           //prints:
    }

    private static void executeUpdateInsert() {
        System.out.println("\nexecuteUpdateInsert():");
        String sql = "insert into person (first_name, last_name, dob) values ('Bill', 'Grey', '1980-01-27')";
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            System.out.println(st.executeUpdate(sql));  //prints: 1
            System.out.println(st.getResultSet());      //prints: null
            System.out.println(st.getUpdateCount());    //prints: 1
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(selectAllFirstNames());      //prints: Bill
    }

    private static void executeUpdateSelect() {
        System.out.println("\nexecuteUpdateSelect():");
        String sql = "select first_name from person";
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            st.executeUpdate(sql);  //PSQLException: A result was returned when none was expected.
        } catch (SQLException ex) {
            ex.printStackTrace();    //prints: stack trace
        }
    }

    private static void executeUpdateUpdate() {
        System.out.println("\nexecuteUpdateUpdate():");
        String sql = "update person set first_name = 'Adam'";
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            System.out.println(st.executeUpdate(sql));  //prints: 1
            System.out.println(st.getResultSet());      //prints: null
            System.out.println(st.getUpdateCount());    //prints: 1
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(selectAllFirstNames());           //prints: Adam
    }

    private static void executeUpdateDelete() {
        System.out.println("\nexecuteUpdateDelete():");
        String sql = "delete from person";
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            System.out.println(st.executeUpdate(sql));  //prints: 1
            System.out.println(st.getResultSet());      //prints: null
            System.out.println(st.getUpdateCount());    //prints: 1
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(selectAllFirstNames());      //prints:
    }

    private static Connection getConnection() {
        String URL = "jdbc:postgresql://localhost/learnjava";
        Properties prop = new Properties();
        prop.put("user", "student");
        // prop.put( "password", "secretPass123" );
        try {
            return DriverManager.getConnection(URL, prop);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
