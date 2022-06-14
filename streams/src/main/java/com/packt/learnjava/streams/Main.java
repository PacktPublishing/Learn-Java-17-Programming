package com.packt.learnjava.streams;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private static final String EXPECTED = "Expected";
    public static void main(String[] args) {
        try {
            Set<String> cities = new HashSet<>();
            Set<String> states = new HashSet<>();
            Set<Integer> zips = new HashSet<>();
            Map<Integer, Integer> oldestByZip = new HashMap<>();
            Map<Integer, String> oldestNameByZip = new HashMap<>();

            URL url = Main.class.getClassLoader().getResource("input.csv");
            File file = new File(url.toURI());
            List<Person> list = getInputPersonList(file);
            list.stream().parallel().forEach(p -> {
                cities.add(p.getCity());
                states.add(p.getState());
                zips.add(p.getZip());
                int age = oldestByZip.getOrDefault(p.getZip(), 0);
                if(p.getAge() > age){
                    oldestByZip.put(p.getZip(), p.getAge());
                    oldestNameByZip.put(p.getZip(), p.getAge() + ": " + p.getName());
                } else if (p.getAge() == age){
                    oldestNameByZip.put(p.getZip(), oldestNameByZip.get(p.getZip()) + ", " + p.getName());
                }
            });
            System.out.println("cities: " + cities.stream().sorted().collect(Collectors.joining(", ")));
            System.out.println("states: " + states.stream().sorted().collect(Collectors.joining(", ")));
            System.out.println("zips: " + zips.stream().sorted().map(i -> String.valueOf(i)).collect(Collectors.joining(", ")));
            System.out.println("Oldest in each zip: " + oldestNameByZip.keySet().stream().sorted()
                    .map(i -> i + "=>" + oldestNameByZip.get(i)).collect(Collectors.joining("; ")));
        } catch (Exception e) {
            if(e.getMessage() != null && e.getMessage().startsWith(EXPECTED)){
                System.out.println("Input error. " + e.getMessage());
            } else {
                e.printStackTrace();
            }
        }
    }

    private static List<Person> getInputPersonList(File file) throws IOException {
        return Files.lines(file.toPath())
                .skip(1)
                .parallel()
                .map(Main::validLine)
                .map(l -> {
                    Person person = new Person(Integer.parseInt(l.get(2)), l.get(0), l.get(1));
                    person.setAddress(l.get(3), l.get(4), l.get(5), Integer.parseInt(l.get(6)));
                    return person;
                }).toList();
    }

    private static List<String> validLine(String line){
        String[] arr = line.split(",");
        if(arr.length != 7){
            throw new RuntimeException(EXPECTED + " 7 column: " + line);
        }

        List<String> values = Arrays.stream(arr).parallel().map(s -> {
            String val = s.trim();
            if(val.isEmpty()){
                throw new RuntimeException(EXPECTED + " only non-empty values: " + line);
            }
            return val;
        }).toList();

        try {
            Integer.valueOf(values.get(2));
            Integer.valueOf(values.get(6));
        } catch (Exception e) {
            throw new RuntimeException(EXPECTED + " numbers in columns 3 and 7: " + line);
        }
        if(values.get(6).length() != 5){
            throw new RuntimeException(EXPECTED + " zip code 5 digits only: " + line);
        }
        return values;
    }
}
