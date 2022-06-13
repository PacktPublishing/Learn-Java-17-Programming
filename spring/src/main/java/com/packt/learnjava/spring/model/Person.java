package com.packt.learnjava.spring.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private int id;
    private LocalDate dob;
    private String firstName, lastName;
    public Person(){}
    public Person(int id, String firstName,
                  String lastName, LocalDate dob) {
        this.id = id;
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() { return id; }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDob() { return dob; }
    public String getFirstName() { return firstName;}
    public String getLastName() { return lastName; }

    public String validateForInsert(){
        List<String> errors = new ArrayList<>();
        if(this.dob == null){
            errors.add("DOB is required");
        }
        if(this.firstName == null || "".equals(this.firstName.trim())){
            errors.add("firstName is required");
        }
        if(this.lastName == null || "".equals(this.lastName.trim())){
            errors.add("lastName is required");
        }
        return errors.size() == 0 ? "" : "Errors:" + errors.stream().collect(Collectors.joining(", ", " ", "."));
    }

    public void prepareForUpdate(Person newPerson){
        if(newPerson.getDob() != null){
            setDob(newPerson.getDob());
        }
        if(newPerson.getFirstName() != null && !"".equals(newPerson.getFirstName().trim())){
            setFirstName(newPerson.getFirstName());
        }
        if(newPerson.getLastName() != null && !"".equals(newPerson.getLastName().trim())){
            setLastName(newPerson.getLastName());
        }
    }
    private static final String INSERT = "insert into person (first_name, last_name, dob) values (?, ?, ?::date) returning id";
    public static int insert(Connection conn, Person person) {
        try (PreparedStatement ps = conn.prepareStatement(INSERT)) {
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setString(3, person.getDob().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    private static final String SELECT_ALL = "select * from person";
    public static List<Person> selectAll(Connection conn) {
        System.out.println("Read from db " + LocalDateTime.now());
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
            throw new RuntimeException();
        }
        return list;
    }

    private static final String SELECT_BY_ID = "select * from person where id = ?";
    public static Person selectById(Connection conn, int id) {
        Person person = new Person();
        try (PreparedStatement st = conn.prepareStatement(SELECT_BY_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                person.setId(id);
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setDob(rs.getDate("dob").toLocalDate());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
        return person;
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
            throw new RuntimeException();
        }
    }

    private static final String DELETE_BY_ID = "delete from person where id = ?";
    public static void deleteById(Connection conn, int id) {
        try (PreparedStatement st = conn.prepareStatement(DELETE_BY_ID)) {
            st.setInt(1, id);
            st.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
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
