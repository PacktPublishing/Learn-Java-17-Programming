package com.packt.learnjava.ch10_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PreparedStatementDemo {
    public static void main(String... args) {
        //Uncomment the following code only after
        // the database learnjava and table person are created

/*
        List<Person> persons = selectPersonsByFirstName("Bill");
        System.out.println(persons.size()); //prints count of records in the table person
                                            // that have first_name=='Bill'
*/
    }

    private static List<Person> selectPersonsByFirstName(String searchName) {
        List<Person> list = new ArrayList<>();
        Connection conn = getConnection();
        String sql = "select * from person where first_name = ?";
        try (conn; PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, searchName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Person(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("dob").toLocalDate()));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
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
