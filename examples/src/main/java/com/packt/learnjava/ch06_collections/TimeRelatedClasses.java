package com.packt.learnjava.ch06_collections;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class TimeRelatedClasses {
    public static void main(String... args) {
        localDate();
        localTime();
        localDateTime();
        periodAndDuration();
        periodOfDay();
    }

    private static void localDate(){
        System.out.println("\nlocalDate(): current date");
        System.out.println(LocalDate.now());
                     //prints: current date in format yyyy-MM-dd

        System.out.println("\nlocalDate(): all zoneIds");
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        for(String zoneId: zoneIds){
            System.out.println(zoneId);
        }

        System.out.println("\nlocalDate(): current date in Tokyo");
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        System.out.println(LocalDate.now(zoneId));
             //prints: current date in Tokyo in format yyyy-MM-dd

        System.out.println("\nlocalDate(): date 2023-02-23");
        LocalDate lc1 =  LocalDate.parse("2023-02-23");
        System.out.println(lc1);                     //prints: 2023-02-23

        LocalDate lc2 =  LocalDate.parse("20230223", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(lc2);                     //prints: 2023-02-23

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate lc3 =  LocalDate.parse("23/02/2023", formatter);
        System.out.println(lc3);                     //prints: 2023-02-23

        LocalDate lc4 =  LocalDate.of(2023, 2, 23);
        System.out.println(lc4);                     //prints: 2023-02-23

        LocalDate lc5 =  LocalDate.of(2023, Month.FEBRUARY, 23);
        System.out.println(lc5);                     //prints: 2023-02-23

        LocalDate lc6 = LocalDate.ofYearDay(2023, 54);
        System.out.println(lc6);                     //prints: 2023-02-23

        System.out.println(lc6.getYear());        //prints: 2023
        System.out.println(lc6.getMonth());       //prints: FEBRUARY
        System.out.println(lc6.getMonthValue());  //prints: 2
        System.out.println(lc6.getDayOfMonth());  //prints: 23
        System.out.println(lc6.getDayOfWeek());   //prints: THURSDAY
        System.out.println(lc6.isLeapYear());     //prints: false
        System.out.println(lc6.lengthOfMonth());  //prints: 28
        System.out.println(lc6.lengthOfYear());   //prints: 365

        System.out.println("\nlocalDate(): date 2023-02-23 modified");
        System.out.println(lc6.withYear(2024));     //prints: 2024-02-23
        System.out.println(lc6.withMonth(5));       //prints: 2023-05-23
        System.out.println(lc6.withDayOfMonth(5));  //prints: 2023-02-05
        System.out.println(lc6.withDayOfYear(53));  //prints: 2023-02-22
        System.out.println(lc6.plusDays(10));       //prints: 2020-03-05
        System.out.println(lc6.plusMonths(2));      //prints: 2020-04-23
        System.out.println(lc6.plusYears(2));       //prints: 2025-02-23
        System.out.println(lc6.minusDays(10));      //prints: 2023-02-13
        System.out.println(lc6.minusMonths(2));     //prints: 2022-12-23
        System.out.println(lc6.minusYears(2));      //prints: 2021-02-23

        System.out.println("\nlocalDate(): compare 2023-02-23 and 2023-02-22");
        LocalDate lc7 =  LocalDate.parse("2023-02-22");
        System.out.println(lc6.isAfter(lc7));       //prints: true
        System.out.println(lc6.isBefore(lc7));      //prints: false
    }

    private static void localTime(){
        System.out.println("\nlocalTime():");

        System.out.println(LocalTime.now());        //prints: 21:15:46.360904

        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        System.out.println(LocalTime.now(zoneId));  //prints: 12:15:46.364378

        LocalTime lt1 =  LocalTime.parse("20:23:12");
        System.out.println(lt1);                    //prints: 20:23:12

        LocalTime lt2 =  LocalTime.of(20, 23, 12);
        System.out.println(lt2);                    //prints: 20:23:12

        System.out.println(lt2.getHour());          //prints: 20
        System.out.println(lt2.getMinute());        //prints: 23
        System.out.println(lt2.getSecond());        //prints: 12
        System.out.println(lt2.getNano());          //prints: 0

        System.out.println(lt2.withHour(3));        //prints: 03:23:12
        System.out.println(lt2.withMinute(10));     //prints: 20:10:12
        System.out.println(lt2.withSecond(15));     //prints: 20:23:15
        System.out.println(lt2.withNano(300));      //prints: 20:23:12.000000300
        System.out.println(lt2.plusHours(10));      //prints: 06:23:12
        System.out.println(lt2.plusMinutes(2));     //prints: 20:25:12
        System.out.println(lt2.plusSeconds(2));     //prints: 20:23:14
        System.out.println(lt2.plusNanos(200));     //prints: 20:23:12.000000200
        System.out.println(lt2.minusHours(10));     //prints: 10:23:12
        System.out.println(lt2.minusMinutes(2));    //prints: 20:21:12
        System.out.println(lt2.minusSeconds(2));    //prints: 20:23:10
        System.out.println(lt2.minusNanos(200));    //prints: 20:23:11.999999800

        LocalTime lt4 =  LocalTime.parse("20:25:12");
        System.out.println(lt2.isAfter(lt4));       //prints: false
        System.out.println(lt2.isBefore(lt4));      //prints: true
    }

    private static void localDateTime() {
        System.out.println("\nlocalDateTime():");

        System.out.println(LocalDateTime.now());       //prinst: 2019-03-04T21:59:00.142804

        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        System.out.println(LocalDateTime.now(zoneId)); //prints: 2019-03-05T12:59:00.146038
        LocalDateTime ldt1 =  LocalDateTime.parse("2020-02-23T20:23:12");
        System.out.println(ldt1);                 //prints: 2020-02-23T20:23:12
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime ldt2 =
                LocalDateTime.parse("23/02/2020 20:23:12", formatter);
        System.out.println(ldt2);                 //prints: 2020-02-23T20:23:12
        LocalDateTime ldt3 = LocalDateTime.of(2020, 2, 23, 20, 23, 12);
        System.out.println(ldt3);                 //prints: 2020-02-23T20:23:12
        LocalDateTime ldt4 =
                LocalDateTime.of(2020, Month.FEBRUARY, 23, 20, 23, 12);
        System.out.println(ldt4);                     //prints: 2020-02-23T20:23:12
        LocalDate ld = LocalDate.of(2020, 2, 23);
        LocalTime lt =  LocalTime.of(20, 23, 12);
        LocalDateTime ldt5 = LocalDateTime.of(ld, lt);
        System.out.println(ldt5);                     //prints: 2020-02-23T20:23:12
    }

    private static void periodAndDuration() {
        System.out.println("\nperiodAndDuration():");

        LocalDateTime ldt1 = LocalDateTime.parse("2023-02-23T20:23:12");
        LocalDateTime ldt2 = ldt1.plus(Period.ofYears(2));
        System.out.println(ldt2);      //prints: 2025-02-23T20:23:12

        LocalDateTime ldt = LocalDateTime.parse("2023-02-23T20:23:12");
        ldt.minus(Period.ofYears(2));
        ldt.plus(Period.ofMonths(2));
        ldt.minus(Period.ofMonths(2));
        ldt.plus(Period.ofWeeks(2));
        ldt.minus(Period.ofWeeks(2));
        ldt.plus(Period.ofDays(2));
        ldt.minus(Period.ofDays(2));
        ldt.plus(Duration.ofHours(2));
        ldt.minus(Duration.ofHours(2));
        ldt.plus(Duration.ofMinutes(2));
        ldt.minus(Duration.ofMinutes(2));
        ldt.plus(Duration.ofMillis(2));
        ldt.minus(Duration.ofMillis(2));

        LocalDate ld1 =  LocalDate.parse("2023-02-23");
        LocalDate ld2 =  LocalDate.parse("2023-03-25");
        Period period = Period.between(ld1, ld2);
        System.out.println(period.getDays());       //prints: 2
        System.out.println(period.getMonths());     //prints: 1
        System.out.println(period.getYears());      //prints: 0
        System.out.println(period.toTotalMonths()); //prints: 1
        period = Period.between(ld2, ld1);
        System.out.println(period.getDays());       //prints: -2

        LocalTime lt1 =  LocalTime.parse("10:23:12");
        LocalTime lt2 =  LocalTime.parse("20:23:14");
        Duration duration = Duration.between(lt1, lt2);
        System.out.println(duration.toDays());     //prints: 0
        System.out.println(duration.toHours());    //prints: 10
        System.out.println(duration.toMinutes());  //prints: 600
        System.out.println(duration.toSeconds());  //prints: 36002
        System.out.println(duration.getSeconds()); //prints: 36002
        System.out.println(duration.toNanos());    //prints: 36002000000000
        System.out.println(duration.getNano());    //prints: 0
    }

    private static void periodOfDay() {
        System.out.println("\ndayPeriod():");

        System.out.print("1: ");
        periodOfDayFromDateTime("2023-03-23T05:05:18.123456", "MM-dd-yyyy h a");
                                                                  //prints: 03-23-2023 5 AM

        System.out.print("\n2: ");
        periodOfDayFromDateTime("2023-03-23T05:05:18.123456", "MM-dd-yyyy h B");
                                                             //prints: 03-23-2023 5 at night

        System.out.print("\n3: ");
        periodOfDayFromDateTime("2023-03-23T06:05:18.123456", "h B");
                                                                   //prints: 6 in the morning

        System.out.print("\n4: ");
        periodOfDayFromTime("11:05:18.123456", "h B"); //prints: 11 in the morning

        System.out.print("\n5: ");
        periodOfDayFromTime("12:05:18.123456", "h B"); //prints: 12 in the afternoon

        System.out.print("\n6: ");
        periodOfDayFromTime("17:05:18.123456", "h B"); //prints: 5 in the afternoon

        System.out.print("\n7: ");
        periodOfDayFromTime("18:05:18.123456", "h B"); //prints: 6 in the evening

        System.out.print("\n8: ");
        periodOfDayFromTime("20:05:18.123456", "h B"); //prints: 8 in the evening

        System.out.print("\n9: ");
        periodOfDayFromTime("21:05:18.123456", "h B"); //prints: 9 at night
    }

    private static void periodOfDayFromDateTime(String time, String pattern){
        LocalDateTime date = LocalDateTime.parse(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        System.out.print(date.format(formatter));
    }

    private static void periodOfDayFromTime(String time, String pattern){
        LocalTime date = LocalTime.parse(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        System.out.print(date.format(formatter));
    }
}
