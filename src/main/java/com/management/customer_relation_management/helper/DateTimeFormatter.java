package com.management.customer_relation_management.helper;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DateTimeFormatter {

    public static String format(LocalDateTime dateTime) {

        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

    public static LocalDateTime deformat(String dateTimeString) {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

}