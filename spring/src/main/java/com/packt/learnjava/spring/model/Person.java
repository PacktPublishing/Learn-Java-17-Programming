package com.packt.learnjava.spring.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private int id;
    private LocalDate dob;
    private String firstName, lastName;
    public Person(){}
    public Person(String firstName, String lastName, LocalDate dob){
        if (dob == null) {
            throw new RuntimeException("Date of birth cannot be null");
        }
        this.dob = dob;
        this.firstName = firstName == null ? "" : firstName;
        this.lastName = lastName == null ? "" : lastName;
    }
    public Person(int id, String firstName,
                  String lastName, LocalDate dob) {
        this(firstName, lastName, dob);
        this.id = id;
    }
    public int getId() { return id; }
    public LocalDate getDob() { return dob; }
    public String getFirstName() { return firstName;}
    public String getLastName() { return lastName; }

    private static final String INSERT = "insert into person (first_name, last_name, dob) values (?, ?, ?::date)";
    public static void insert(Connection conn, Person person) {
        try (PreparedStatement st = conn.prepareStatement(INSERT)) {
            st.setString(1, person.getFirstName());
            st.setString(2, person.getLastName());
            st.setString(3, person.getDob().toString());
            st.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static final String SELECT_ALL = "select * from person";
    public static List<Person> selectAll(Connection conn) {
        List<Person> list = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(SELECT_ALL)) {
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

    private static final String SELECT_BY_ID = "select * from person where id = ?";
    public static List<Person> selectById(Connection conn, int id) {
        List<Person> list = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(SELECT_BY_ID)) {
            st.setInt(1, id);
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

    private static final String UPDATE_BY_ID = "update person set first_name = ?, last_name = ?, dob = ?::date where id = ?";
    public static void updateById(Connection conn, Person person) {
        try (PreparedStatement st = conn.prepareStatement(UPDATE_BY_ID)) {
            st.setString(1, person.getFirstName());
            st.setString(2, person.getLastName());
            st.setString(3, person.getDob().toString());
            st.setInt(4, person.getId());
            st.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static final String DELETE_BY_ID = "delete from person where id = ?";
    public static void deleteById(Connection conn, int id) {
        try (PreparedStatement st = conn.prepareStatement(DELETE_BY_ID)) {
            st.setInt(1, id);
            st.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", dob=" + dob +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
