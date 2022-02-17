package com.packt.learnjava.ch10_database;

import com.packt.learnjava.database.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

public class UseDatabaseJar {
    public static void main(String[] args) {
        try(Connection conn = getConnection()){
            System.out.println("\nclean table person");
            cleanTablePerson(conn);

            Person mike = new Person("Mike", "Brown", LocalDate.of(2002, 8, 14));
            Person jane = new Person("Jane", "McDonald", LocalDate.of(2000, 3, 21));
            Person jill = new Person("Jill", "Grey", LocalDate.of(2001, 4, 1));
            Person.insert(conn, mike);
            Person.insert(conn, jane);
            Person.insert(conn, jane);

            List<Person> persons = Person.selectByFirstName(conn, jill.getFirstName());
            System.out.println(jill.getFirstName() + " count: " + persons.size());    //prints: 0
            persons = Person.selectByFirstName(conn, jane.getFirstName());
            System.out.println(jane.getFirstName() + " count: " + persons.size());    //prints: 2
            for(Person p: persons){
                System.out.println(p.getId() + ": " + p.getFirstName());
            }

            Person person = persons.get(0);
            System.out.println("\nchange first name (id=" + person.getId() + ") to " + jill.getFirstName());
            Person.updateFirstNameById(conn, person.getId(), jill.getFirstName());
            persons = Person.selectByFirstName(conn, jane.getFirstName());
            System.out.println(jane.getFirstName() + " count: " + persons.size());    //prints: 1
            for(Person p: persons){
                System.out.println(p.getId() + ": " + p.getFirstName());
            }

            persons = Person.selectByFirstName(conn, jill.getFirstName());
            System.out.println(jill.getFirstName() + " count: " + persons.size());    //prints: 1
            for(Person p: persons){
                System.out.println(p.getId() + ": " + p.getFirstName());
            }

            persons = Person.selectByFirstName(conn, mike.getFirstName());
            System.out.println("\n" + mike.getFirstName() + " count: " + persons.size());    //prints: 1
            for(Person p: persons){
                System.out.println("delete " + mike.getFirstName() + " (id=" + p.getId() + ")");
                Person.deleteById(conn, p.getId());
            }
            persons = Person.selectByFirstName(conn, mike.getFirstName());
            System.out.println(mike.getFirstName() + " count: " + persons.size());    //prints: 0
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private static void cleanTablePerson(Connection conn) {
        try (Statement st = conn.createStatement()) {
            st.execute("delete from person");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
