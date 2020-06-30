package com.study.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {

    public static void main(String[] args){

        String date = "2020-06-23";
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(localDate.getDayOfMonth());

    }

}
